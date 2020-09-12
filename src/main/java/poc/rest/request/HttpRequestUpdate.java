package poc.rest.request;

import poc.rest.config.configparcer.update.ParsedConfigUpdate;
import poc.rest.request.parameters.RequestParam;

import java.util.List;

public class HttpRequestUpdate implements HttpRequest {
    public HttpRequestUpdate(ParsedConfigUpdate parsedConfigUpdate) {}

    @Override
    public String getMapping() {
        return "";
    }

    @Override
    public List<RequestParam> getRequestParams() {
        return null;
    }
}
