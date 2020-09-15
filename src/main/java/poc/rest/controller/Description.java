package poc.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Description {
    private String operation;
    private String mapping;
    private String httpMethod;
    private List<Map<String,String>> parameters;
    private List<Map<String, String>> result = new ArrayList<>();

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setParameters(List<Map<String, String>> parameters) {
        this.parameters = parameters;
    }

    public String getOperation() {
        return operation;
    }

    public String getMapping() {
        return mapping;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public List<Map<String, String>> getParameters() {
        return parameters;
    }

    public void setResult(List<Map<String, String>> result) {
        this.result = result;
    }
}
