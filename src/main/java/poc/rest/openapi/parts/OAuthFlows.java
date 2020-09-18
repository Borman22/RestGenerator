package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuthFlows {
    public OAuthFlow implicit;
    public OAuthFlow password;
    public OAuthFlow clientCredentials;
    public OAuthFlow authorizationCode;
}
