package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityScheme {
    public String type;
    public String description;
    public String name;
    public String in;
    public String scheme;
    public String bearerFormat;
    public OAuthFlows flows;
    public String openIdConnectUrl;
}
