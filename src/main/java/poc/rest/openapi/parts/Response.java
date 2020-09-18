package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    public String description;
    public Map<String, Header> headers;
    public Map<String, MediaType> content;
    public Map<String, Link> links;
}
