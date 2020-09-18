package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerVariable {
    public List<String> enum_; // TODO Enum https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md#serverVariableObject
    public String default_;
    public String description;
}
