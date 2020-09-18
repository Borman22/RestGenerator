package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PathItem {
    public String pathNameMapping; // вынужденная мера
    public String $ref;
    public String summary;
    public String description;
    public Operation get;
    public Operation put;
    public Operation post;
    public Operation delete;
    public Operation options;
    public Operation head;
    public Operation patch;
    public Operation trace;
    public Server [] servers;
    public List<Parameter> parameters; // or Reference Object
//    public Parameter [] parameters; // or Reference Object

}
