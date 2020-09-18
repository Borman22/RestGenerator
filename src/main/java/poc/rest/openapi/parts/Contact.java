package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {
    public String name;
    public String url;
    public String email;
}
