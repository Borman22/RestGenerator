package poc.rest.request.parameters;

import poc.rest.config.util.TypeRelations;

public class RequestParam {
    String name;
    String type;
    String javaType;

    public RequestParam(String name, String type){
        this.name = name;
        this.type = type;
        javaType = TypeRelations.convertConfigTypeToJavaType(type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getJavaType() {
        return javaType;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", javaType='" + javaType + '\'' +
                '}';
    }
}
