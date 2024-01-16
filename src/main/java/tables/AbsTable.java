package tables;

import db.IDBConnector;
import db.MySqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class AbsTable {
    private String tableName;
    private Map<String, String> columns;
    IDBConnector db;

    public AbsTable(String tableName, Map<String, String> columns,IDBConnector connector) {
        this.tableName = tableName;
        this.columns = columns;
        this.db = connector;


    }

    // Создание таблицы
    public void create() {
        this.columns = columns;
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
                this.tableName, convertMapColumnsToString());
        db.executeRequest(sqlRequest);
    }

    private String convertMapColumnsToString() {
        final String result = columns.entrySet().stream()
                .map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;
    }


    // Выборка данных из таблицы
    public String selectAll(List<String> columns) {
        StringBuilder selectAll = new StringBuilder();
        for (int i = 0; i < columns.size(); i++){
            selectAll.append(tableName).append(".").append(columns.get(i));
            if (i < columns.size() - 1){
                selectAll.append(", ");
            }
        }
        //String string = selectAll.toString();
        final String sqlRequest = String.format("SELECT %s FROM %s ", selectAll, tableName);
        return select(sqlRequest);
    }


    // Метод на проверку
    protected String select(String sqlRequest) {
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        // колличество колонок в результирующем запросе
        try {
            int columnCount = rs.getMetaData().getColumnCount();
            // перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //db.close();
        }
        return sqlRequest;
    }


}
