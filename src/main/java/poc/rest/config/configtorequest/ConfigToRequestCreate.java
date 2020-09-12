package poc.rest.config.configtorequest;

import poc.rest.config.configparcer.create.ParsedConfigCreate;
import poc.rest.config.configparcer.read.ParsedConfigRead;
import poc.rest.request.HttpRequestCreate;

import java.util.ArrayList;
import java.util.List;

public class ConfigToRequestCreate{

    public List<HttpRequestCreate> convert(List<ParsedConfigCreate> config) {

            List<HttpRequestCreate> requests = new ArrayList<>();
//            for(ParsedConfigCreate configCreate : config){
//                requests.add(new HttpRequestCreate(configCreate.getSelectColumns(), configCreate.getJoins(), configCreate.getConditionColumns()));
//            }
            return requests;

    }
}
