package poc.rest.config.configtorequest;

import poc.rest.config.configparcer.delete.ParsedConfigDelete;
import poc.rest.request.HttpRequestDelete;

import java.util.ArrayList;
import java.util.List;

public class ConfigToRequestDelete {

    public List<HttpRequestDelete> convert(List<ParsedConfigDelete> config) {
        List<HttpRequestDelete> requests = new ArrayList<>();
        for(ParsedConfigDelete configDelete : config){
            requests.add(new HttpRequestDelete(configDelete.getDeleteEntries()));
        }
        return requests;
    }
}
