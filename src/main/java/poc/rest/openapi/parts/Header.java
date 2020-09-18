package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {
    public String name;
    public String description;
    public ExternalDocumentation externalDocs;

}
