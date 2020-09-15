package poc.rest.config.configparcer.update;

import poc.rest.config.configparcer.Column;

import java.util.List;

public class UpdateEntry {

    private String tableName;
    private List<Column> updateColumns;
    private List<Column> conditionColumns;

    public String getTableName() {
        return tableName;
    }

    public String getTableNameQuotes() {
        return "\"" + tableName + "\"";
    }

    public List<Column> getUpdateColumns() {
        return updateColumns;
    }

    public List<Column> getConditionColumns() {
        return conditionColumns;
    }

}
