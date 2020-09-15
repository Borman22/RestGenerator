package poc.rest.config.configparcer.create;


import java.util.List;

public class CreateEntry {

    private String tableName;
    private String generatedColumn;
    private List<ColumnRef> fields;
    private String returnedIdAlias;

    public String getTableName() {
        return tableName;
    }

    public String getTableNameQuotes() {
        return "\"" + tableName + "\"";
    }

    public String getGeneratedColumn() {
        return generatedColumn;
    }

    public List<ColumnRef> getFields() {
        return fields;
    }

    public String getReturnedIdAlias() {
        return returnedIdAlias;
    }
}
