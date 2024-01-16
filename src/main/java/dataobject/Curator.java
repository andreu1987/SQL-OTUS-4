package dataobject;

import java.util.List;

public class Curator {
    private final String  id;
    private final String fio;

    public Curator(String id,String fio){
        this.id = id;
        this.fio = fio;
    }

    // Список колонок в таблицы curators
    public static List<Curator> curators = List.of(
            new Curator("1","Тащи"),
            new Curator("2","тарасов"),
            new Curator("3","Алексеев"),
            new Curator("4","Рыбаков")
    );

    public String getId(){
        return id;
    }


    public String getFio(){
        return fio;
    }

}
