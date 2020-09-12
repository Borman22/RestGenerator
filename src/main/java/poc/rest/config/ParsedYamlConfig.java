package poc.rest.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import poc.rest.config.configparcer.create.ParsedConfigCreate;
import poc.rest.config.configparcer.delete.ParsedConfigDelete;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.config.configparcer.update.ParsedConfigUpdate;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedYamlConfig {

    private List<ParsedConfigCreate> requestsCreate;
    private List<ParsedConfigRead> requestsRead;
    private List<ParsedConfigUpdate> requestsUpdate;
    private List<ParsedConfigDelete> requestsDelete;

    public List<ParsedConfigCreate> getRequestsCreate() {
        return requestsCreate;
    }

    public List<ParsedConfigRead> getRequestsRead() {
        return requestsRead;
    }

    public List<ParsedConfigUpdate> getRequestsUpdate() {
        return requestsUpdate;
    }

    public List<ParsedConfigDelete> getRequestsDelete() {
        return requestsDelete;
    }
}
