package poc.rest.openapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import poc.rest.openapi.parts.*;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenApiDocument {
    public String openapi;
    public Info info;
    public Server[] servers;
    public Paths paths;
    public Components components;
    public List<SecurityRequirement> security;
    public List<Tag>tags;
    public ExternalDocumentation externalDocs;
}
