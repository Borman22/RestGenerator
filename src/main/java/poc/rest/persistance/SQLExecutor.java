package poc.rest.persistance;

import com.google.gson.Gson;
import poc.rest.config.configparcer.read.Column;
import poc.rest.config.util.TypeRelations;
import poc.rest.request.HttpRequestRead;
import poc.rest.request.parameters.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class SQLExecutor {

    Connection connection;

    public SQLExecutor(DataSource dataSource) {
        connection = dataSource.getConnection();
    }

    public void close() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Не удалось закрыть connection");
            }
    }

//    public static int executeInsertUpdateDelete(Connection connection, String query, Object... args) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            for (int i = 0; i < args.length; i++) {
//                preparedStatement.setObject(i + 1, args[i]);
//            }
//            int rowsAffected = preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                return resultSet.getInt(1);
//            } else {
//                return rowsAffected;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return -1;
//    }
//
//    public List<List<Object>> executeSelect(String query, List<Column> selectColumns) {
//        System.out.println("query = " + query);
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            for (int i = 0; i < selectColumns.size(); i++) {
//                preparedStatement.setObject(i + 1, selectColumns.get(i).toString());
//                System.out.println("parameter = " + selectColumns.get(i).toString() + " type = " + selectColumns.get(i).getClass());
//            }
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            List<List<Object>> resultList = new ArrayList<>();
//            while (resultSet.next()){
//                resultList.add(createResultRow(resultSet, selectColumns));
//            }
//
//            return resultList;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<List<Object>> executeSelect(String query, List<Column> selectColumns, Map<RequestParam, String> parameters) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            List<RequestParam> paramList = new ArrayList<>(parameters.keySet());
                    System.out.println("----------------------");paramList.stream().forEach(System.out::println);
            for (int i = 0; i < paramList.size(); i++) {
                System.out.println("parameter = " + TypeRelations.convertToApropriateType(paramList.get(i).getJavaType()).apply(parameters.get(paramList.get(i))));
                preparedStatement.setObject(i + 1, TypeRelations.convertToApropriateType(paramList.get(i).getJavaType()).apply(parameters.get(paramList.get(i))));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
//
            List<List<Object>> resultList = new ArrayList<>();
            while (resultSet.next()){
                resultList.add(createResultRow(resultSet, selectColumns));
            }
//
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Object> createResultRow(ResultSet rs, List<Column> selectColumns) {
        List<Object> resultEntry = new ArrayList<>();

        for(Column column : selectColumns){
            try {
                resultEntry.add(rs.getObject(column.toString()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultEntry;
    }



//    private void someMeth(){
//
//
//        for(HttpRequestRead httpRequestRead : requestsRead){
//            get(httpRequestRead.getMapping(), (request, response) -> {
//                List<String> params = new ArrayList<>();
//                for(Column column : httpRequestRead.getConditionColumns())
//                    params.add(request.params(column.toString()));
//
//                List<List<Object>> result = serviceRead.readData(httpRequestRead.getRequest(), httpRequestRead.getSelectColumns(), params);
//                String resultString = "";
//                for(List<Object> row : result){
//                    for(Object element : row){
//                        resultString += "{" + element.getClass() + " = " + element + "}  ";
//                    }
//                    resultString += "\n";
//                }
//
//                String json = new Gson().toJson(result);
//
//                return json;
//
//            });

//        }
//    }
}
