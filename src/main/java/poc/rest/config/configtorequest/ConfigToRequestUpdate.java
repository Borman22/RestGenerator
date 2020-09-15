package poc.rest.config.configtorequest;

import poc.rest.config.configparcer.update.ParsedConfigUpdate;
import poc.rest.request.HttpRequestUpdate;

import java.util.ArrayList;
import java.util.List;

public class ConfigToRequestUpdate {

    public List<HttpRequestUpdate> convert(List<ParsedConfigUpdate> config) {
        List<HttpRequestUpdate> requests = new ArrayList<>();
        for(ParsedConfigUpdate configUpdate : config){
            requests.add(new HttpRequestUpdate(configUpdate.getUpdateEntries()));
        }
        return requests;
    }
}
