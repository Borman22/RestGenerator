package poc.rest.config.configparcer;




public class Column {
    private String tableName;
    private String columnName;
    private String columnType;

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
