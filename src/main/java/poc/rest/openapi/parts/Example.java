package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Example {
    public String summary;
    public String description;
    public Object value;
    public String externalValue;

}
