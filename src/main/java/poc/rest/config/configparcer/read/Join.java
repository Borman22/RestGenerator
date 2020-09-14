package poc.rest.config.configparcer.read;

import poc.rest.config.configparcer.Column;

import java.util.List;

public class Join {
    String joinType;
    List<Column> columns;

    public String getJoinType() {
        return joinType;
    }

    public List<Column> getColumns() {
        return columns;
    }
}
