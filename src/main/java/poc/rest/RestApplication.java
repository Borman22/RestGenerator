package poc.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import poc.rest.config.ParsedConfigsToRequestsConverter;
import poc.rest.config.ParsedYamlConfig;
import poc.rest.config.YamlConfigParser;
import poc.rest.controller.MainController;
import poc.rest.openapi.OpenApiDocument;
import poc.rest.openapi.TypeRelationsOpenApi;
import poc.rest.openapi.parts.*;
import poc.rest.request.HttpRequestCreate;
import poc.rest.request.HttpRequestDelete;
import poc.rest.request.HttpRequestRead;
import poc.rest.request.HttpRequestUpdate;
import poc.rest.request.parameters.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestApplication {
	private static int port = 8080;
	private static String pathToConfig = "src/main/resources/changelogrest.yaml";
//	private static String pathToConfig = "changelog.yaml";

	private static String URL = "jdbc:postgresql://localhost:5432/poc";
	private static String user = "root";
	private static String password = "root";

	public static void main(String[] args) {

		if(args.length == 3){
			URL = args[0];
			user = args[1];
			password = args[2];
		}

		YamlConfigParser yamlConfigParser = new YamlConfigParser();
		ParsedYamlConfig parsedYamlConfig = yamlConfigParser.getParsedYamlConfig(pathToConfig);

		ParsedConfigsToRequestsConverter converter = new ParsedConfigsToRequestsConverter();
		List<HttpRequestCreate> requestsCreate = converter.getRequestsCreate(parsedYamlConfig.getRequestsCreate());
		List<HttpRequestRead> requestsRead = converter.getRequestsRead(parsedYamlConfig.getRequestsRead());
		List<HttpRequestUpdate> requestsUpdate = converter.getRequestsUpdate(parsedYamlConfig.getRequestsUpdate());
		List<HttpRequestDelete> requestsDelete = converter.getRequestsDelete(parsedYamlConfig.getRequestsDelete());



		MainController controller = new MainController(port, requestsCreate, requestsRead, requestsUpdate, requestsDelete, URL, user, password);
		controller.start();

		try {
			createOpenApiDocumentation(requestsCreate, requestsRead, requestsUpdate, requestsDelete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createOpenApiDocumentation(List<HttpRequestCreate> requestsCreate,
												   List<HttpRequestRead> requestsRead,
												   List<HttpRequestUpdate> requestsUpdate,
												   List<HttpRequestDelete> requestsDelete) throws IOException {


		OpenApiDocument openApiDocument = new OpenApiDocument();
		openApiDocument.openapi = "3.0.1";

		Info info = new Info();
		info.title = "defaultTitle";
		info.version = "0.1";
		openApiDocument.info = info;

		Server[] servers = new Server[1];
		servers[0] = new Server();
		servers[0].url = "http://localhost:8080";
		openApiDocument.servers = servers;

		Paths paths = new Paths();
		openApiDocument.paths = paths;
		List<PathItem> path = new ArrayList<>();
		paths.pathsListPathItem = path;

		makeOpenApiForCreate(requestsCreate, path);
		makeOpenApiForRead(requestsRead, path);
		makeOpenApiForUpdate(requestsUpdate, path);
		makeOpenApiForDelete(requestsDelete, path);

		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		String validOpenApiDocument = convertToValidOpenAPI(openApiDocument);

		FileWriter fw = new FileWriter(new File("./OpenApi.yaml"));
		fw.write(validOpenApiDocument);
		fw.close();
	}

	private static void makeOpenApiForCreate(List<HttpRequestCreate> requestsCreate, List<PathItem> path) {
		for (HttpRequestCreate temp : requestsCreate) {
			PathItem pathItem = new PathItem();
			path.add(pathItem);
			pathItem.pathNameMapping = temp.getMapping();
			Operation post = new Operation();
			pathItem.post = post;
//			List<Parameter> parametersPathItem = new ArrayList<>();
//			pathItem.parameters = parametersPathItem;
			addResponces(pathItem.post);
//			for (CreateEntry createEntry : temp.getCreateEntries()) {
//				for(ColumnRef requestParam : createEntry.getFields()) {
//					Parameter parameterOpenApi = new Parameter(requestParam.toString(), "query", true,
//							new Schema(TypeRelationsOpenApi.convertJavaTypeToOpenApiType(TypeRelations.convertConfigTypeToJavaType(requestParam.getColumnType()))));
//					parametersPathItem.add(parameterOpenApi);
//				}
//			}
		}
	}

	private static void makeOpenApiForRead(List<HttpRequestRead> requestsRead, List<PathItem> path) {
		for (HttpRequestRead temp : requestsRead) {
			PathItem pathItem = new PathItem();
			path.add(pathItem);
			pathItem.pathNameMapping = temp.getMappingWithCurlyBraces();
			Operation get = new Operation();
			pathItem.get = get;
			List<Parameter> parametersPathItem = new ArrayList<>();
			pathItem.parameters = parametersPathItem;
			addResponces(pathItem.get);
			for (RequestParam requestParam : temp.getRequestParams()) {
				Parameter parameterOpenApi = new Parameter(requestParam.getName(), "path", true, new Schema(TypeRelationsOpenApi.convertJavaTypeToOpenApiType(requestParam.getJavaType())));
				parametersPathItem.add(parameterOpenApi);
			}
		}
	}

	private static void makeOpenApiForUpdate(List<HttpRequestUpdate> requestsUpdate, List<PathItem> path) {
		for (HttpRequestUpdate temp : requestsUpdate) {
			PathItem pathItem = new PathItem();
			path.add(pathItem);
			pathItem.pathNameMapping = temp.getMapping();
			Operation put = new Operation();
			pathItem.put = put;
//			List<Parameter> parametersPathItem = new ArrayList<>();
//			pathItem.parameters = parametersPathItem;
			addResponces(pathItem.put);
//			for (UpdateEntry updateEntry : temp.getUpdateEntries()) {
//				for(Column requestParam : updateEntry.getUpdateColumns()) {
//					Parameter parameterOpenApi = new Parameter("set_" + requestParam.toString(), "query", true,
//							new Schema(TypeRelationsOpenApi.convertJavaTypeToOpenApiType(TypeRelations.convertConfigTypeToJavaType(requestParam.getColumnType()))));
//					parametersPathItem.add(parameterOpenApi);
//				}
//				for(Column requestParam : updateEntry.getConditionColumns()) {
//					Parameter parameterOpenApi = new Parameter(requestParam.toString(), "query", true,
//							new Schema(TypeRelationsOpenApi.convertJavaTypeToOpenApiType(TypeRelations.convertConfigTypeToJavaType(requestParam.getColumnType()))));
//					parametersPathItem.add(parameterOpenApi);
//				}
//			}
		}
	}

	private static void makeOpenApiForDelete(List<HttpRequestDelete> requestDelete, List<PathItem> path) {
		for (HttpRequestDelete temp : requestDelete) {
			PathItem pathItem = new PathItem();
			path.add(pathItem);
			pathItem.pathNameMapping = temp.getMapping();
			pathItem.delete = new Operation();
//			List<Parameter> parametersPathItem = new ArrayList<>();
//			pathItem.parameters = parametersPathItem;
			addResponces(pathItem.delete);
//			for (DeleteEntry deleteEntry : temp.getDeleteEntries()) {
//				for(Column requestParam : deleteEntry.getConditionColumns()) {
//					Parameter parameterOpenApi = new Parameter(requestParam.toString(), "query", true,
//							new Schema(TypeRelationsOpenApi.convertJavaTypeToOpenApiType(TypeRelations.convertConfigTypeToJavaType(requestParam.getColumnType()))));
//					parametersPathItem.add(parameterOpenApi);
//				}
//			}
		}
	}


	private static String convertToValidOpenAPI(OpenApiDocument openApiDocument) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		String result = om.writeValueAsString(openApiDocument);
		result = result.replaceAll("pathsListPathItem:\n  - pathNameMapping: ", "");
		result = result.replaceAll("- pathNameMapping: ", "");
		result = result.replaceAll("responseCode200", "\"200\"");
		result = result.replaceAll("\"\\{}\"", "{}");
		result = result.replaceAll("\"\n" +
				"    get:", "\":\n" +
				"    get:");
		result = result.replaceAll("\"\n" +
				"    put:", "\":\n" +
				"    put:");
		result = result.replaceAll("\"\n" +
				"    post:", "\":\n" +
				"    post:");
		result = result.replaceAll("\"\n" +
				"    delete:", "\":\n" +
				"    delete:");
		result = result.replaceAll("\"\n" +
				"    options:", "\":\n" +
				"    options:");
		result = result.replaceAll("\"\n" +
				"    head:", "\":\n" +
				"    head:");
		result = result.replaceAll("\"\n" +
				"    head:", "\":\n" +
				"    head:");
		result = result.replaceAll("\"\n" +
				"    patch:", "\":\n" +
				"    patch:");
		result = result.replaceAll("\"\n" +
				"    trace:", "\":\n" +
				"    trace:");
		return result;
	}

	private static void addResponces(Operation pathItemOperation){

			Responses responses = new Responses();
			pathItemOperation.responses = responses;

			Response responseCode200 = new Response();
			responses.responseCode200 = responseCode200;
			responseCode200.description = "Auto generated using custom generator";

			Map<String, MediaType> content = new HashMap<>();
			responseCode200.content = content;

			MediaType mediaType = new MediaType();
			Schema schema = new Schema("object", "{}");
			mediaType.schema = schema;

			content.put("application/json", mediaType);
		}

}
