package poc.rest.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import poc.rest.config.ParsedYamlConfig;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.request.*;
import poc.rest.request.parameters.RequestParam;
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

        get("/", (request, response) -> {
            String allMappings = "";
//            for(HttpRequestCreate temp : requestsCreate)
//                allMappings += "Create:\t" + temp.getMappingWithCurlyBraces() + " <br> ";

            for(HttpRequestRead temp : requestsRead)
                allMappings += "Read:\t" + temp.getMappingWithCurlyBraces() + " <br> ";

//            for(HttpRequestUpdate temp : requestsUpdate)
//                allMappings += "Update:\t" + temp.getMappingWithCurlyBraces() + " <br> ";
//
            for(HttpRequestDelete temp : requestsDelete)
                allMappings += "Delete:\t" + temp.getMapping() + " <br> ";

            return allMappings;
        });

//        for(HttpRequestCreate create : requestsCreate){
//            put(create.getMapping(), (request, response) -> {return null;});
//        }


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
            put(delete.getMapping(), (request, response) -> {
                response.type("application/json");
                ServiceDelete serviceDelete = new ServiceDelete("jdbc:postgresql://localhost:5432/test", "root", "root");

                Map<String, Integer> resultMessage = new HashMap<>();
                resultMessage.put("Удалено строк", serviceDelete.execute(delete, request.body()));

                return new Gson().toJson(resultMessage);
            });
        }


    }
}
