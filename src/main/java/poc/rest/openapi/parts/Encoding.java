package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Encoding {
    public String contentType;
    public Map<String, Header> headers;
    public String style;
    public Boolean explode;
    public Boolean allowReserved;
}
