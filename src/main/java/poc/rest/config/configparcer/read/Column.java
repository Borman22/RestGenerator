package poc.rest.config.configparcer.read;




public class Column {
    String tableName;
    String columnName;
    String columnType;

    public String getColumnName() {
        return columnName;
    }

    public String getColumnNameQuotes() {
        return "\"" + columnName + "\"";
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableNameQuotes() {
        return "\"" + tableName + "\"";
    }

    public String getColumnType() {
        return columnType;
    }


    @Override
    public String toString() {
        return tableName + "_" + columnName;
    }

    public String toStringQuotes(){
        return "\"" + tableName + "\".\"" + columnName + "\"";
    }

}
