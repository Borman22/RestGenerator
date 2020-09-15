package poc.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import poc.rest.config.configparcer.create.ColumnRef;
import poc.rest.config.configparcer.create.CreateEntry;
import poc.rest.persistance.DataSource;
import poc.rest.persistance.SQLExecutor;
import poc.rest.request.HttpRequestCreate;
import poc.rest.request.parameters.RequestParam;

import java.io.IOException;
import java.util.*;

public class ServiceCreate {

    private DataSource dataSource;
    private SQLExecutor sqlExecutor;

    public ServiceCreate(String url, String user, String password) {
        dataSource = new DataSource(url, user, password);
        sqlExecutor = new SQLExecutor(dataSource);
    }


    public int execute(HttpRequestCreate httpRequestCreate, String requestBody) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(requestBody);
        } catch (IOException e) {
            throw new RuntimeException("ServiceCreate.class: не удалось распарсить JSON. ", e);
        }

        List<Map<String, Boolean>> sqlQueries = new ArrayList<>();


        for(CreateEntry createEntry : httpRequestCreate.getCreateEntries()){

            // SELECT setval('users_id_seq', max(id)) FROM users;
            // SELECT pg_get_serial_sequence('patients', 'id')
            String queryForChangingSequence = String.format("SELECT setval((SELECT pg_get_serial_sequence('%s', '%s')), max(%s)) FROM \"user\";",
                    createEntry.getTableName(), createEntry.getGeneratedColumn(), createEntry.getGeneratedColumn());
            Map<String, Boolean> queryMapForChangingSequence = new HashMap<>();
            queryMapForChangingSequence.put(queryForChangingSequence, true); // true means that the query uses to change sequence (we don't need count this operations)
            sqlQueries.add(queryMapForChangingSequence);

            List<RequestParam> requestParams = new ArrayList<>();
            for(ColumnRef column : createEntry.getFields()){
                requestParams.add(new RequestParam(column.toString(), column.getColumnType()));
            }

            Map<RequestParam, String> paramsValues = new LinkedHashMap<>();
            for(RequestParam requestParam : requestParams){
                paramsValues.put(requestParam, root.get(requestParam.getName()).asText());
            }

            sqlQueries.add(buildSqlQuery(createEntry, paramsValues));
        }

        return sqlExecutor.executeCreate(sqlQueries);
    }


    // TODO сделать так, чтобы сгенерированный ID из одной таблицы использовался в запросе при записи в другую таблицу
    private Map<String, Boolean> buildSqlQuery(CreateEntry createEntry, Map<RequestParam, String> paramsValues) {

        String sqlQuery = "INSERT INTO " + createEntry.getTableNameQuotes() + " (";

        for(int i = 0; i < createEntry.getFields().size(); i++){
            sqlQuery += createEntry.getFields().get(i).getColumnName();
            sqlQuery += (i == createEntry.getFields().size() - 1) ? ") VALUES (" : ", ";
        }

        List<RequestParam> requestParams = new ArrayList<>(paramsValues.keySet());
        for(int i = 0; i < requestParams.size(); i++) {
            sqlQuery += "'" + paramsValues.get(requestParams.get(i)) + "'";
            sqlQuery += (i == requestParams.size() - 1) ? ");" : ", ";
        }

        Map<String, Boolean> queryMap = new HashMap<>();
        queryMap.put(sqlQuery, false);
        return queryMap;
    }

}
