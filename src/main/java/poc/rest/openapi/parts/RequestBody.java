package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestBody {
    public String description;
    public Map<String, MediaType> content;
    public Boolean required;
}
