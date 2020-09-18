package poc.rest.openapi.parts;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Schema {
    public String type;
    public String properties; // Schema object!
    public Boolean nullable;
    public Discriminator discriminator;
    public Boolean readOnly;
    public Boolean writeOnly;
    public XML xml;
    public ExternalDocumentation externalDocs;
    public Object example;
    public Boolean deprecated;

    public Schema(String type){
        this.type = type;
    }

    public Schema(String type, String properties){
        this.type = type;
        this.properties = properties;
    }
}
