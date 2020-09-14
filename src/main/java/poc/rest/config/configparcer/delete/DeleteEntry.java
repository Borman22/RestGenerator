package poc.rest.config.configparcer.delete;

import poc.rest.config.configparcer.Column;

import java.util.List;

public class DeleteEntry {
    private String tableName;
    private List<Column> conditionColumns;

    public String getTableName() {
        return tableName;
    }

    public String getTableNameQuotes() {
        return "\"" + tableName + "\"";
    }

    public List<Column> getConditionColumns() {
        return conditionColumns;
    }
}
