package poc.rest.config.configtorequest;

import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.request.HttpRequestRead;

import java.util.ArrayList;
import java.util.List;

public class ConfigToRequestRead {

    public List<HttpRequestRead> convert(List<ParsedConfigRead> config) {
        List<HttpRequestRead> requests = new ArrayList<>();
        for(ParsedConfigRead configRead : config){
            requests.add(new HttpRequestRead(configRead.getSelectColumns(), configRead.getJoins(), configRead.getConditionColumns()));
        }
        return requests;
    }
}
