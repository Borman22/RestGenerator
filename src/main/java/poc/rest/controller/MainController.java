package poc.rest.controller;

import com.google.gson.Gson;
import poc.rest.config.ParsedYamlConfig;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.request.*;
import poc.rest.request.parameters.RequestParam;
import poc.rest.service.ServiceRead;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
//                allMappings += "Create:\t" + temp.getMapping() + "\n";

            for(HttpRequestRead temp : requestsRead)
                allMappings += "Read:\t" + temp.getMapping() + "\n";

//            for(HttpRequestUpdate temp : requestsUpdate)
//                allMappings += "Update:\t" + temp.getMapping() + "\n";
//
//            for(HttpRequestDelete temp : requestsDelete)
//                allMappings += "Delete:\t" + temp.getMapping() + "\n";

            return allMappings;
        });

//        for(HttpRequestCreate create : requestsCreate){
//            put(create.getMapping(), (request, response) -> {return null;});
//        }

        for(HttpRequestRead read : requestsRead){
            get(read.getMapping(), (request, response) -> {
                Map<RequestParam, String> paramsValues = new LinkedHashMap<>();
                for(RequestParam requestParam : read.getRequestParams())
                    paramsValues.put(requestParam, request.params(requestParam.getName()));
                ServiceRead serviceRead = new ServiceRead("jdbc:postgresql://localhost:5432/test", "root", "root");
//                return serviceRead.execute(read, paramsValues);});
                return new Gson().toJson(serviceRead.execute(read, paramsValues));});
        }

//        for(HttpRequestUpdate update : requestsUpdate){
//            put(update.getMapping(), (request, response) -> {return null;});
//        }
//
//        for(HttpRequestDelete delete : requestsDelete){
//            put(delete.getMapping(), (request, response) -> {return null;});
//        }

//        for(CreateConfig createConfig : restConfig.getRequestCreate())
//            requestAndMappingCreateList.add(new RequestAndMappingCreate(createConfig));

//        for(ParsedConfigRead parsedConfigRead : parsedYamlConfig.getRequestsRead())
//            requestsRead.add(new HttpRequestRead(parsedConfigRead.getSelectColumns(), parsedConfigRead.getJoins(), parsedConfigRead.getConditionColumns()));

//        for(UpdateConfig updateConfig : restConfig.getRequestUpdate())
//            requestAndMappingUpdateList.add(new RequestAndMappingUpdate(updateConfig));
//
//        for(DeleteConfig deleteConfig : restConfig.getRequestDelete())
//            requestAndMappingDeleteList.add(new RequestAndMappingDelete(deleteConfig));

    }
}
