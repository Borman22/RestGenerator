package poc.rest.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import poc.rest.config.ParsedYamlConfig;
import poc.rest.config.configparcer.Column;
import poc.rest.config.configparcer.create.ColumnRef;
import poc.rest.config.configparcer.create.CreateEntry;
import poc.rest.config.configparcer.delete.DeleteEntry;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.request.*;
import poc.rest.request.parameters.RequestParam;
import poc.rest.service.ServiceCreate;
import poc.rest.service.ServiceDelete;
import poc.rest.service.ServiceRead;

import java.util.*;

import static spark.Spark.*;

public class MainController {

    private ParsedYamlConfig parsedYamlConfig;
    private int port;

    List<HttpRequestCreate> requestsCreate;
    List<HttpRequestRead> requestsRead;
    List<HttpRequestUpdate> requestsUpdate;
    List<HttpRequestDelete> requestsDelete;

    // C: Create - POST
    // R: Read - GET
    // U: Update - PUT
    // D: Delete - Delete
    public MainController(int port,
                          List<HttpRequestCreate> requestsCreate,
                          List<HttpRequestRead> requestsRead,
                          List<HttpRequestUpdate> requestsUpdate,
                          List<HttpRequestDelete> requestsDelete) {
        this.port = port;
        this.requestsCreate = requestsCreate;
        this.requestsRead = requestsRead;
        this.requestsUpdate = requestsUpdate;
        this.requestsDelete = requestsDelete;
    }

    public void start(){
        port(port);

        // Description for all methods
        get("/", (request, response) -> {
            response.type("application/json");


            // CREATE description
            List<Description> resultDescription = new ArrayList<>();
            for(HttpRequestCreate temp : requestsCreate) {

                Description description = new Description();
                resultDescription.add(description);
                description.setOperation("CREATE");
                description.setMapping(temp.getMapping());
                description.setHttpMethod("POST");

                List<Map<String, String>> parameters = new ArrayList<>();
                description.setParameters(parameters);
                for(CreateEntry createEntry : temp.getCreateEntries()) {
                    for (ColumnRef columnRef : createEntry.getFields()) {
                        Map<String, String> parameter = new LinkedHashMap<>();
                        parameter.put("name", columnRef.toString());
                        parameter.put("type", columnRef.getColumnType());
                        parameters.add(parameter);
                    }
                }
                List<Map<String, String>> result = new ArrayList<>();
                description.setResult(result);
                Map<String, String> resultEntry = new HashMap<>();
                result.add(resultEntry);
                resultEntry.put("name", "createdRows");
                resultEntry.put("type", "INT");

            }

            // READ description
            for(HttpRequestRead temp : requestsRead) {

                Description description = new Description();
                resultDescription.add(description);
                description.setOperation("READ");
                description.setMapping(temp.getMappingWithCurlyBraces());
                description.setHttpMethod("GET");

                List<Map<String, String>> parameters = new ArrayList<>();
                description.setParameters(parameters);
                for(RequestParam requestParam : temp.getRequestParams()) {
                    Map<String, String> parameter = new LinkedHashMap<>();
                    parameter.put("name", requestParam.getName());
                    parameter.put("type", requestParam.getType());
                    parameters.add(parameter);
                }
                List<Map<String, String>> result = new ArrayList<>();
                description.setResult(result);
                for(Column column : temp.getSelectColumns()) {
                    Map<String, String> resultEntry = new HashMap<>();
                    result.add(resultEntry);
                    resultEntry.put("name", column.toString());
                    resultEntry.put("type", column.getColumnType());
                }
            }


            // DELETE description
            for(HttpRequestDelete temp : requestsDelete) {

                Description description = new Description();
                resultDescription.add(description);
                description.setOperation("DELETE");
                description.setMapping(temp.getMapping());
                description.setHttpMethod("DELETE");

                List<Map<String, String>> parameters = new ArrayList<>();
                description.setParameters(parameters);
                for(DeleteEntry deleteEntry : temp.getDeleteEntries()) {
                    for (Column column : deleteEntry.getConditionColumns()) {
                        Map<String, String> parameter = new LinkedHashMap<>();
                        parameter.put("name", column.toString());
                        parameter.put("type", column.getColumnType());
                        parameters.add(parameter);
                    }
                }
                List<Map<String, String>> result = new ArrayList<>();
                description.setResult(result);
                Map<String, String> resultEntry = new HashMap<>();
                result.add(resultEntry);
                resultEntry.put("name", "deletedRows");
                resultEntry.put("type", "INT");
            }

            return new Gson().toJson(resultDescription);

        });





//  CREATE
        for(HttpRequestCreate create : requestsCreate){
            post(create.getMapping(), (request, response) -> {
                response.type("application/json");
                ServiceCreate serviceCreate = new ServiceCreate("jdbc:postgresql://localhost:5432/test", "root", "root");

                Map<String, Integer> resultMessage = new HashMap<>();
                resultMessage.put("createdRows", serviceCreate.execute(create, request.body()));

                return new Gson().toJson(resultMessage);
            });
        }

// READ
        // SELECT "user"."id" AS user_id, "user"."name" AS user_name, "orders"."id" AS orders_id, "orders"."orders" AS orders_orders
        // FROM "user_order"
        // JOIN "user" ON "user_order"."user_id"="user"."id"
        // JOIN "orders" ON "user_order"."orders_id"="orders"."id"
        // WHERE "user"."id" = ?;
        for(HttpRequestRead read : requestsRead){
            get(read.getMapping(), (request, response) -> {
                response.type("application/json");
                Map<RequestParam, String> paramsValues = new LinkedHashMap<>();
                for(RequestParam requestParam : read.getRequestParams())
                    paramsValues.put(requestParam, request.params(requestParam.getName()));
                ServiceRead serviceRead = new ServiceRead("jdbc:postgresql://localhost:5432/test", "root", "root");
                return new Gson().toJson(serviceRead.execute(read, paramsValues));});
        }

//        for(HttpRequestUpdate update : requestsUpdate){
//            put(update.getMapping(), (request, response) -> {return null;});
//        }


//  DELETE
        for(HttpRequestDelete delete : requestsDelete){
            delete(delete.getMapping(), (request, response) -> {
                response.type("application/json");
                ServiceDelete serviceDelete = new ServiceDelete("jdbc:postgresql://localhost:5432/test", "root", "root");

                Map<String, Integer> resultMessage = new HashMap<>();
                resultMessage.put("deletedRows", serviceDelete.execute(delete, request.body()));

                return new Gson().toJson(resultMessage);
            });
        }


    }
}
