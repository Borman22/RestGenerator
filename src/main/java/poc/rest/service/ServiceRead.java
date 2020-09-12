package poc.rest.service;

import poc.rest.config.configparcer.read.Column;
import poc.rest.config.util.Separator;
import poc.rest.config.util.StringProcessor;
import poc.rest.persistance.DataSource;
import poc.rest.persistance.SQLExecutor;
import poc.rest.request.HttpRequestRead;
import poc.rest.request.parameters.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServiceRead {

    private DataSource dataSource;
    private SQLExecutor sqlExecutor;

    public ServiceRead(String url, String user, String password) {
        dataSource = new DataSource(url, user, password);
        sqlExecutor = new SQLExecutor(dataSource);
    }

    public List<List<Object>> execute(HttpRequestRead httpRequestRead, Map<RequestParam, String> parameters) {
        String sqlQuery = buildSqlQuery(httpRequestRead);
        return sqlExecutor.executeSelect(sqlQuery, httpRequestRead.getSelectColumns(), parameters);
    }


    private String buildSqlQuery(HttpRequestRead httpRequestRead) {
        String request = "";

        request = "SELECT ";

        for(int i = 0; i < httpRequestRead.getSelectColumnsInQuotes().size(); i++){
            request += httpRequestRead.getSelectColumnsInQuotes().get(i) + " AS " + httpRequestRead.getSelectColumns().get(i);
            if(i != httpRequestRead.getSelectColumnsInQuotes().size() - 1)
                request += ", ";
        }
//        request += StringProcessor.separateCollection(httpRequestRead.getSelectColumnsInQuotes(), Separator.COMMA_SPACE);

        request += " FROM ";
        if (httpRequestRead.getJoins() != null && httpRequestRead.getJoins().size() > 0) { // TODO предусмотреть возможность перестановки таблиц в join-е в yaml
            for (int i = 0; i < httpRequestRead.getJoins().size(); i++) {
                List<Column> pairOfColumns = httpRequestRead.getJoins().get(i).getColumns();
                if (i == 0) request += pairOfColumns.get(0).getTableNameQuotes();
                request += " " + httpRequestRead.getJoins().get(i).getJoinType() + " " + pairOfColumns.get(1).getTableNameQuotes() + " ON "
                        + pairOfColumns.get(0).toStringQuotes() + "=" + pairOfColumns.get(1).toStringQuotes();
            }
        } else {
            Set<String> tableName = new HashSet<>();
            for (Column column : httpRequestRead.getSelectColumns()) {
                tableName.add(column.getTableNameQuotes());
            }
            request += StringProcessor.separateCollection(Separator.SPACE, tableName, Separator.COMMA_SPACE);
        }

        request += " WHERE ";
        request += StringProcessor.separateCollection(httpRequestRead.getConditionColumnsInQuotes(), Separator.CONDITION_BETWEEN, Separator.CONDITION_END);

        System.out.println("request = " + request);
        return request;
    }


}
