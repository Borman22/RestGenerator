package poc.rest.openapi.parts;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuthFlow {
    public String authorizationUrl;
    public String tokenUrl;
    public String refreshUrl;
    public Map<String, String> scopes;
}
