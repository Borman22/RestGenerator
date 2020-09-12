package poc.rest.request;

import poc.rest.config.configparcer.delete.ParsedConfigDelete;
import poc.rest.request.parameters.RequestParam;

import java.util.List;

public class HttpRequestDelete implements HttpRequest {
    public HttpRequestDelete(ParsedConfigDelete parsedConfigDelete){}

    @Override
    public String getMapping() {
        return "";
    }

    @Override
    public List<RequestParam> getRequestParams() {
        return null;
    }
}
