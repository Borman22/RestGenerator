package poc.rest.config.configparcer.read;

import poc.rest.config.configparcer.Column;

import java.util.List;

public class ParsedConfigRead {
    private List<Column> selectColumns;
    private List<Join> joins;
    private List<Column> conditionColumns;

    public List<Column> getSelectColumns() {
        return selectColumns;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public List<Column> getConditionColumns() {
        return conditionColumns;
    }
}
