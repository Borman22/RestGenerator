package poc.rest.config.configparcer.create;

public class ColumnRef {

    private String tableName;
    private String columnName;
    private String columnType;
    private String assingAliasValue;

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

    public String getAssingAliasValue() {
        return assingAliasValue;
    }

    @Override
    public String toString() {
        return tableName + "_" + columnName;
    }

    public String toStringQuotes(){
        return "\"" + tableName + "\".\"" + columnName + "\"";
    }
}
