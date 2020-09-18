package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaType {
    public Schema schema;
    public Object example;
    public Map<String, Example> examples;
    public Map<String, Encoding> encoding;

}
