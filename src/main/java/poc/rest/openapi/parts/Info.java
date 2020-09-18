package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Info {
    public String title;
    public String description;
    public String termsOfService;
    public Contact contact;
    public License license;
    public String version;
}
