package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link {
    public String operationRef;
    public String operationId;
    public Map<String, Object> parameters;
    public Object requestBody;
    public String description;
    public Server server;
}
