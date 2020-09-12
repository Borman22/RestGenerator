package poc.rest.request;

import poc.rest.request.parameters.RequestParam;

import java.util.List;

public interface HttpRequest {
    String getMapping();
    List<RequestParam> getRequestParams();
}
