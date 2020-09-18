package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Server {
    public String url;
    public String description;
    public Map<String, ServerVariable> variables;
    public PathItem paths;
}
