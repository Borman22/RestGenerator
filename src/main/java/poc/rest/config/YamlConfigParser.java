package poc.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlConfigParser {

    public ParsedYamlConfig getParsedYamlConfig(String pathToConfigYaml){

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        ParsedYamlConfig parsedYamlConfig = null;
        File configFile = new File(pathToConfigYaml);

        try {
            parsedYamlConfig = mapper.readValue(configFile, ParsedYamlConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedYamlConfig;
    }

}
