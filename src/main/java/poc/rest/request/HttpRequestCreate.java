package poc.rest.request;

import poc.rest.config.configparcer.create.ParsedConfigCreate;
import poc.rest.request.parameters.RequestParam;

import java.util.List;

public class HttpRequestCreate implements HttpRequest {
    public HttpRequestCreate(ParsedConfigCreate parsedConfigCreate) {}

    @Override
    public String getMapping() {
        return "";
    }

    @Override
    public List<RequestParam> getRequestParams() {
        return null;
    }
}
