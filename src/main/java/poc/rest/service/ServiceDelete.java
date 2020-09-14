package poc.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poc.rest.config.configparcer.Column;
import poc.rest.config.configparcer.delete.DeleteEntry;
import poc.rest.persistance.DataSource;
import poc.rest.persistance.SQLExecutor;
import poc.rest.request.HttpRequestDelete;
import poc.rest.request.parameters.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServiceDelete {

    private DataSource dataSource;
    private SQLExecutor sqlExecutor;

    public ServiceDelete(String url, String user, String password) {
        dataSource = new DataSource(url, user, password);
        sqlExecutor = new SQLExecutor(dataSource);
    }


    public int execute(HttpRequestDelete httpRequestDelete, String requestBody) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(requestBody);
        } catch (IOException e) {
            throw new RuntimeException("ServiceDelete.class: не удалось распарсить JSON. ", e);
        }

        List<String> sqlQueries = new ArrayList<>();
        for(DeleteEntry deleteEntry : httpRequestDelete.getDeleteEntries()){

            List<RequestParam> requestParams = new ArrayList<>();
            for(Column column : deleteEntry.getConditionColumns()){
                requestParams.add(new RequestParam(column.toString(), column.getColumnType()));
            }

            Map<RequestParam, String> paramsValues = new LinkedHashMap<>();
            for(RequestParam requestParam : requestParams){
                paramsValues.put(requestParam, root.get(requestParam.getName()).asText());
            }

            sqlQueries.add(buildSqlQuery(deleteEntry, paramsValues));
        }

        return sqlExecutor.executeDelete(sqlQueries);
    }


    private String buildSqlQuery(DeleteEntry deleteEntry, Map<RequestParam, String> paramsValues) {

        String sqlQuery = "DELETE FROM " + deleteEntry.getTableNameQuotes() + " WHERE ";

        List<RequestParam> requestParams = new ArrayList<>(paramsValues.keySet());
        for(int i = 0; i < requestParams.size(); i++) {
            sqlQuery += deleteEntry.getConditionColumns().get(i).toStringQuotes() + " = '" + paramsValues.get(requestParams.get(i)) + "'";
            sqlQuery += (i == requestParams.size() - 1) ? ";" : " AND ";
        }

        return sqlQuery;
    }
}
