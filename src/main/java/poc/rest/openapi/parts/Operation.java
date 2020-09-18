package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Operation {
    public List<String> tags;
    public String summary;
    public String description;
    public ExternalDocumentation externalDocs;
    public String operationId;
    public List<Parameter> parameters; // or Reference
    public RequestBody requestBody; // or Reference
    public Responses responses;
    public Map<String, Callback> callbacks; // or Reference
    public Boolean deprecated;
    public SecurityRequirement security;
    public Server servers;
}
