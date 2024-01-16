package tables;

import dataobject.Groop;
import db.IDBConnector;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


public class GroopTable extends AbsTable {
    private final static String GROOP_TABLE = "Groop";
    private final static String CURATOR_TABLE = "Curator";

    public static Map<String, String> userColumns = new LinkedHashMap<>(){{
        put("id","varchar(10)");
        put("name","varchar(10)");
        put("id_curator","varchar(10)");
    }};

    public GroopTable(IDBConnector connector){
        super(GROOP_TABLE,userColumns,connector);
    }
    @Override
    public String toString() {
        return ("-----------------Успешно создана таблица--------------- " + GROOP_TABLE);
    }

    public void insert (Groop groop){
        String sqlRequest_s2 = String.format("INSERT INTO groop (id,name,id_curator) VALUES ('%s','%s','%s')",
                groop.getId(), groop.getName(), groop.getId_curator());
        System.out.println(sqlRequest_s2);
        db.executeRequest(sqlRequest_s2);
    }

    //"Задание№9/Вывести список групп с их кураторами"
    public String selectGroopCurator(List<String>groopColumnsCurator, List<String>curatorColumnsGroop) {
        StringBuilder selectAllGroop = new StringBuilder();
        for (int i = 0; i < groopColumnsCurator.size(); i++){
            selectAllGroop.append(GROOP_TABLE).append(".").append(groopColumnsCurator.get(i));
            if (i < groopColumnsCurator.size() - 1){
                selectAllGroop.append(", ");
            }
        }

        StringBuilder selectAllCurator = new StringBuilder();
        for (int i = 0; i < curatorColumnsGroop.size(); i++){
            selectAllCurator.append(CURATOR_TABLE).append(".").append(curatorColumnsGroop.get(i));
            if (i < curatorColumnsGroop.size() - 1){
                selectAllCurator.append(", ");
            }
        }

        final String sqlRequest = String.format("SELECT %s,%s " +
                "FROM %s JOIN %s on %s.id_curator = %s.id",selectAllGroop,
                selectAllCurator,GROOP_TABLE,CURATOR_TABLE,GROOP_TABLE,CURATOR_TABLE);
        System.out.println(selectAllGroop + ", " + selectAllCurator);
        return select(sqlRequest);
    }


}
