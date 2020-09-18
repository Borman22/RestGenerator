package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class XML {
    public String name;
    public String namespace;
    public String prefix;
    public Boolean attribute;
    public Boolean wrapped;
}
