package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paths {
//    public PathItem /{path};  // вынужденная мера
    public List<PathItem> pathsListPathItem;
}
