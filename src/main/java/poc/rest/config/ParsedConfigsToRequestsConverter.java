package poc.rest.config;

import poc.rest.config.configparcer.create.ParsedConfigCreate;
import poc.rest.config.configparcer.delete.ParsedConfigDelete;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.config.configparcer.update.ParsedConfigUpdate;
import poc.rest.config.configtorequest.ConfigToRequestCreate;
import poc.rest.config.configtorequest.ConfigToRequestDelete;
import poc.rest.config.configtorequest.ConfigToRequestRead;
import poc.rest.config.configtorequest.ConfigToRequestUpdate;
import poc.rest.request.HttpRequestCreate;
import poc.rest.request.HttpRequestDelete;
import poc.rest.request.HttpRequestRead;
import poc.rest.request.HttpRequestUpdate;

import java.util.List;

public class ParsedConfigsToRequestsConverter {

    public List<HttpRequestCreate> getRequestsCreate(List<ParsedConfigCreate> config) {
        ConfigToRequestCreate configToRequest = new ConfigToRequestCreate();
        return configToRequest.convert(config);
    }

    public List<HttpRequestRead> getRequestsRead(List<ParsedConfigRead> config) {
        ConfigToRequestRead configToRequest = new ConfigToRequestRead();
        return configToRequest.convert(config);
    }

    public List<HttpRequestUpdate> getRequestsUpdate(List<ParsedConfigUpdate> config) {
        ConfigToRequestUpdate configToRequest = new ConfigToRequestUpdate();
        return configToRequest.convert(config);
    }

    public List<HttpRequestDelete> getRequestsDelete(List<ParsedConfigDelete> config) {
        ConfigToRequestDelete configToRequest = new ConfigToRequestDelete();
        return configToRequest.convert(config);
    }

}
