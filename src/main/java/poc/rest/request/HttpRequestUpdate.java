package poc.rest.request;

import poc.rest.config.configparcer.update.ParsedConfigUpdate;
import poc.rest.request.parameters.RequestParam;

import java.util.List;

public class HttpRequestUpdate{

    private String mapping = "/";

    public HttpRequestUpdate(ParsedConfigUpdate parsedConfigUpdate) {}

    public String getMapping() {
        return mapping;
    }


    public List<RequestParam> getRequestParams() {
        return null;
    }
}
