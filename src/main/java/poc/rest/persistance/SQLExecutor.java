package poc.rest.persistance;

import poc.rest.config.configparcer.Column;
import poc.rest.config.util.TypeRelations;
import poc.rest.request.parameters.RequestParam;

import java.sql.*;
import java.util.*;

public class SQLExecutor {

    DataSource dataSource;

    public SQLExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void close() {
        dataSource.close();
    }

//    public int executeInsertUpdate(String query, List<RequestParam> requestParams) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
////            for (int i = 0; i < args.length; i++) {
////                preparedStatement.setObject(i + 1, args[i]);
////            }
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

    public int executeDelete(List<String> queries) {
        Connection connection = dataSource.getConnection();

        int affectedColumns = 0;
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            for(String query : queries){
                affectedColumns += statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                System.out.println("Выполняется rollback. " + e.getMessage());
                connection.rollback();
                dataSource.close();
                return 0;
            } catch (SQLException throwables) {
                System.out.println("Не удалось выполнить rollback: " + throwables.getMessage());
                dataSource.close();
                return -1 * affectedColumns;
            }
        }
        dataSource.close();
        return affectedColumns;
    }


    public List<Map<String, Object>> executeSelect(String query, List<Column> selectColumns, Map<RequestParam, String> parameters) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            List<RequestParam> paramList = new ArrayList<>(parameters.keySet());

            for (int i = 0; i < paramList.size(); i++) {
                preparedStatement.setObject(i + 1, TypeRelations.convertToApropriateType(paramList.get(i).getJavaType()).apply(parameters.get(paramList.get(i))));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> resultList = new ArrayList<>();
            while (resultSet.next()){
                resultList.add(createResultRow(resultSet, selectColumns));
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dataSource.close();
        }
    }

    private static Map<String, Object> createResultRow(ResultSet rs, List<Column> selectColumns) {
        Map<String, Object> resultEntry = new LinkedHashMap<>();
        for(Column column : selectColumns){
            try {
                resultEntry.put(column.toString(), rs.getObject(column.toString()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultEntry;
    }
}
