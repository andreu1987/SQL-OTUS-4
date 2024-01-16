package tables;

import dataobject.Curator;
import db.IDBConnector;
import db.MySqlConnector;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CuratorTable extends AbsTable {
    private final static String CURATOR_TABLE = "Curator";

    public static Map<String, String> userColumns = new LinkedHashMap<>() {{
        put("id", "varchar(10)");
        put("fio", "varchar(10)");
    }};

    public CuratorTable(IDBConnector connector) {
        super(CURATOR_TABLE, userColumns,connector);

    }
    @Override
    public String toString() {
        return ("----------Успешно создана таблица------ " + CURATOR_TABLE);
    }

    public void insert(Curator curator) {
        String sqlRequest_s3 = String.format("INSERT INTO curator (id,fio) VALUES ('%s','%s')",
                curator.getId(), curator.getFio());
        System.out.println(sqlRequest_s3);
        db.executeRequest(sqlRequest_s3);
    }

    // "Задание№8/Обновить данные по группе сменив куратора
    public void updateCurator(List<String> curatorColumnsUpdate){
        StringBuilder updateAll = new StringBuilder();
        for (int i = 0; i < curatorColumnsUpdate.size(); i++){
            updateAll.append(CURATOR_TABLE).append(".").append(curatorColumnsUpdate.get(i));
            if (i < curatorColumnsUpdate.size() - 1){
                updateAll.append(", ");
            }
        }
        String StringsqlRequest_s1 = ("UPDATE curator SET fio = (case " +
                "when id = 1 then 'Один' " +
                "when id = 2 then 'Два' " +
                "when id = 3 then 'три' " +
                "when id = 4 then 'четыре' " +
                "end ) " +
                "where id  in ('1','2','3','4') ");
        db = new MySqlConnector();
        db.executeRequest(StringsqlRequest_s1);
    }

}
