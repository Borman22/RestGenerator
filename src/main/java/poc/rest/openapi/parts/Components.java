package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Components {
    public Map<String, Schema> schemas;
    public Map<String, Response> responses;
    public Map<String, Parameter> parameters;
    public Map<String, Example> examples;
    public Map<String, RequestBody> requestBodies;
    public Map<String, Header> headers;
    public Map<String, SecurityScheme> securitySchemes;
    public Map<String, Link> links;
    public Map<String, Callback> callbacks;
}
