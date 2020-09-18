package poc.rest.openapi;

import java.util.HashMap;
import java.util.Map;

public class TypeRelationsOpenApi {

    // In the OpenApi allowed next formats
    // array, boolean, integer, number, object, string

    private static Map<String, String> types = new HashMap<>();

    static {
        types.put("int32", "integer");
        types.put("int64", "integer");
        types.put("long", "integer");
        types.put("int", "integer");
        types.put("float", "number");
        types.put("double", "number");
        types.put("byte", "string");
        types.put("short", "string"); // ?
        types.put("binary", "string");
        types.put("date", "string");
        types.put("date-time", "string");
        types.put("password", "string");
        types.put("password", "string");
        types.put("java.lang.String", "string");
        types.put("boolean", "boolean");
    }



    public static String convertJavaTypeToOpenApiType(String javaType) {
        return types.get(javaType);
    }
}
