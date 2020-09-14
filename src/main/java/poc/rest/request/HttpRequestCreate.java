package poc.rest.request;

import poc.rest.config.configparcer.create.ParsedConfigCreate;
import poc.rest.request.parameters.RequestParam;

import java.util.List;

public class HttpRequestCreate {

    private String mapping = "/";

    public HttpRequestCreate(ParsedConfigCreate parsedConfigCreate) {}

    public String getMapping() {
        return mapping;
    }




}
