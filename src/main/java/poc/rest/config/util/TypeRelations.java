package poc.rest.config.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TypeRelations {

    private static Map<String, String> types = new HashMap<>();
    private static Map<String, Function<String, ? extends Object>> typeConverter = new HashMap<>();

    static {
        types.put("varchar", "java.lang.String");
        types.put("char", "java.lang.String");
        types.put("longvarchar", "java.lang.String");
        types.put("bit", "boolean");
//        types.put("numeric", "java.math.BigDecimal");
        types.put("tinyint", "byte");
        types.put("smallint", "short");
        types.put("integer", "int");
        types.put("int", "int");
        types.put("bigint", "long");
        types.put("real", "float");
        types.put("float", "float");
        types.put("double", "double");
//        types.put("varbinary", "byte[]");
//        types.put("binary", "byte[]");
//        types.put("date", "java.sql.Date");
//        types.put("time", "java.sql.Time");
//        types.put("timestamp", "java.sql.Timestamp");
//        types.put("clob", "java.sql.Clob");
//        types.put("blob", "java.sql.Blob");
//        types.put("array", "java.sql.Array");
//        types.put("ref", "java.sql.Ref");
//        types.put("struct", "java.sql.Struct");

        typeConverter.put("java.lang.String", s -> s);
        typeConverter.put("boolean", s -> Boolean.parseBoolean(s));
        typeConverter.put("byte", s -> Byte.parseByte(s));
        typeConverter.put("short", s -> Short.parseShort(s));
        typeConverter.put("int", s -> Integer.parseInt(s));
        typeConverter.put("long", s -> Long.parseLong(s));
        typeConverter.put("float", s -> Float.parseFloat(s));
        typeConverter.put("double", s -> Double.parseDouble(s));
    }



    public static String convertConfigTypeToJavaType(String configType) {
        return types.get(configType.toLowerCase());
    }

    public static <String> Function<java.lang.String, ?> convertToApropriateType(String str){
        return typeConverter.get(str);
    }
}

