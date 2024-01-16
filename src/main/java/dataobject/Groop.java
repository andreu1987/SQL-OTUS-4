package dataobject;

import java.util.List;

public class Groop {
    private final String id;
    private final String name;
    private final String id_curator;

    public Groop(String id, String name, String id_curator) {
        this.id = id;
        this.name = name;
        this.id_curator = id_curator;

    }

    // Список колонок в таблицы Groop
    public static List<Groop> groops = List.of(
            new Groop("1","математика","1"),
            new Groop("2","русский","2"),
            new Groop("3","физика","3")

    );

    public String getId(){
        return id;
    }


    public String getName(){
        return name;
    }


    public String getId_curator(){
        return id_curator;
    }

}
