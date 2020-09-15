package poc.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poc.rest.config.configparcer.Column;
import poc.rest.config.configparcer.update.UpdateEntry;
import poc.rest.persistance.DataSource;
import poc.rest.persistance.SQLExecutor;
import poc.rest.request.HttpRequestUpdate;
import poc.rest.request.parameters.RequestParam;

import java.io.IOException;
import java.util.*;

public class ServiceUpdate {

    private DataSource dataSource;
    private SQLExecutor sqlExecutor;

    public ServiceUpdate(String url, String user, String password) {
        dataSource = new DataSource(url, user, password);
        sqlExecutor = new SQLExecutor(dataSource);
    }

    public int execute(HttpRequestUpdate httpRequestUpdate, String requestBody) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(requestBody);
        } catch (IOException e) {
            throw new RuntimeException("ServiceDelete.class: не удалось распарсить JSON. ", e);
        }

        List<String> sqlQueries = new ArrayList<>();
        for(UpdateEntry updateEntry : httpRequestUpdate.getUpdateEntries()){

// TODO подумать, что делать, если меняются несколько записей в той же самой таблице в одной транзакции. Как передавать в http несколько значений одного и того же параметра.
            List<RequestParam> requestParams = new ArrayList<>();
            for(Column column : updateEntry.getUpdateColumns()){
                requestParams.add(new RequestParam("set_" + column.toString(), column.getColumnType()));
            }

            for(Column column : updateEntry.getConditionColumns()){
                requestParams.add(new RequestParam(column.toString(), column.getColumnType()));
            }

            Map<RequestParam, String> paramsValues = new LinkedHashMap<>();
            for(RequestParam requestParam : requestParams){
                paramsValues.put(requestParam, root.get(requestParam.getName()).asText());
            }

            sqlQueries.add(buildSqlQuery(updateEntry, paramsValues));
        }

        return sqlExecutor.executeUpdateDelete(sqlQueries);
    }


    private String buildSqlQuery(UpdateEntry updateEntry, Map<RequestParam, String> paramsValues) {

        List<RequestParam> requestParams = new ArrayList<>(paramsValues.keySet());

        String sqlQuery = "UPDATE " + updateEntry.getTableNameQuotes() + " SET ";

        int i = 0;
        for(; i < updateEntry.getUpdateColumns().size(); i++){
            sqlQuery += updateEntry.getUpdateColumns().get(i).getColumnNameQuotes() + " = '" + paramsValues.get(requestParams.get(i)) + "'";
            sqlQuery += (i == updateEntry.getUpdateColumns().size() - 1) ? " WHERE " : ", ";
        }

        for(int j = 0; i < requestParams.size(); i++, j++) {
            sqlQuery += updateEntry.getConditionColumns().get(j).getColumnNameQuotes() + " = '" + paramsValues.get(requestParams.get(i)) + "'";
            sqlQuery += (i == requestParams.size() - 1) ? ";" : " AND ";
        }

        return sqlQuery;
    }
    
}
