package poc.rest;

import poc.rest.config.ParsedConfigsToRequestsConverter;
import poc.rest.config.ParsedYamlConfig;
import poc.rest.config.YamlConfigParser;
import poc.rest.controller.MainController;
import poc.rest.request.HttpRequestCreate;
import poc.rest.request.HttpRequestDelete;
import poc.rest.request.HttpRequestRead;
import poc.rest.request.HttpRequestUpdate;

import java.util.List;

public class RestApplication {
	private static int port = 8080;
	private static String pathToConfig = "src/main/resources/changelogrest.yaml";

	public static void main(String[] args) {

		YamlConfigParser yamlConfigParser = new YamlConfigParser();
		ParsedYamlConfig parsedYamlConfig = yamlConfigParser.getParsedYamlConfig(pathToConfig);

		ParsedConfigsToRequestsConverter converter = new ParsedConfigsToRequestsConverter();
		List<HttpRequestCreate> requestsCreate = converter.getRequestsCreate(parsedYamlConfig.getRequestsCreate());
		List<HttpRequestRead> requestsRead = converter.getRequestsRead(parsedYamlConfig.getRequestsRead());
		List<HttpRequestUpdate> requestsUpdate = converter.getRequestsUpdate(parsedYamlConfig.getRequestsUpdate());
		List<HttpRequestDelete> requestsDelete = converter.getRequestsDelete(parsedYamlConfig.getRequestsDelete());



		MainController controller = new MainController(port, requestsCreate, requestsRead, requestsUpdate, requestsDelete);
		controller.start();
	}
}
