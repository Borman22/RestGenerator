package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parameter {
    public String name;
    public String in;
    public String description;
    public Boolean required;
    public Boolean deprecated;
    public Boolean allowEmptyValue;
    public Schema schema;

    public Parameter(String name, String in, boolean required, Schema schema){
        this.name = name;
        this.in = in;
        this.required = required;
        this.schema = schema;
    }

}
