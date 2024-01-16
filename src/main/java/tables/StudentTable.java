package tables;

import dataobject.Student;
import db.IDBConnector;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class StudentTable extends AbsTable {
    private final static String STUDENT_TABLE = "Student";
    private final static String GROOP_TABLE = "Groop";
    private final static String CURATOR_TABLE = "Curator";
    private  final static Map<String, String> USER_COLUMNS = new LinkedHashMap<>(){{
        put("id","varchar(10)");
        put("fio","varchar(100)");
        put("sex","varchar(10)");
        put("id_group","varchar(10)");
    }};

    public StudentTable(IDBConnector connector){
                    super(STUDENT_TABLE, USER_COLUMNS,connector);
    }

    @Override
    public String toString() {
                  return ("-----------Успешно создана таблица-------- " + STUDENT_TABLE);
    }

    public void insert(Student student){
        String sqlRequest_s1 = String.format("INSERT INTO student(id,fio,sex,id_group) VALUES ('%s','%s','%s','%s')",
                student.getId(),student.getFio(),student.getSex(),student.getId_group());
        System.out.println(sqlRequest_s1);
        db.executeRequest(sqlRequest_s1);
    }


    //"Задание№5/Информация о всех студентах включая название группы и имя куратора"
    public String selectStudentTable(List<String>studentColumnsGroopCurator,
                                     List<String>groopColumnsStudentCurator,
                                     List<String>curatorColumnsStudentGroop) {
        StringBuilder selectAllStudent = new StringBuilder();
        for (int i = 0; i < studentColumnsGroopCurator.size(); i++){
            selectAllStudent.append(STUDENT_TABLE).append(".").append(studentColumnsGroopCurator.get(i));
            if (i < studentColumnsGroopCurator.size() - 1){
                selectAllStudent.append(", ");
            }
        }

        StringBuilder selectAllGroop = new StringBuilder();
        for (int i = 0; i < groopColumnsStudentCurator.size(); i++){
            selectAllGroop.append(GROOP_TABLE).append(".").append(groopColumnsStudentCurator.get(i));
            if (i < groopColumnsStudentCurator.size() - 1){
                selectAllGroop.append(", ");
            }
        }

        StringBuilder selectAllCurator = new StringBuilder();
        for (int i = 0; i < curatorColumnsStudentGroop.size(); i++){
            selectAllCurator.append(CURATOR_TABLE).append(".").append(curatorColumnsStudentGroop.get(i));
            if (i < curatorColumnsStudentGroop.size() - 1){
                selectAllCurator.append(", ");
            }
        }

        final String sqlRequest = String.format("SELECT %s,%s,%s " +
                "FROM %s join %s on %s.id_group = %s.id join %s on %s.id_curator = %s.id",selectAllStudent,
                selectAllGroop,selectAllCurator,STUDENT_TABLE,GROOP_TABLE,STUDENT_TABLE,GROOP_TABLE,
                CURATOR_TABLE,GROOP_TABLE,CURATOR_TABLE);
        System.out.println(selectAllStudent + ", " + selectAllGroop + "," + selectAllCurator);
        return select(sqlRequest);
    }

    //"Задание№6/Вывести на экран количество студентов"
    public String selectCountStudent(List<String> studentColumnsCount) {
        StringBuilder selectAll = new StringBuilder();
        for (int i = 0; i < studentColumnsCount.size(); i++){
            selectAll.append(STUDENT_TABLE).append(".").append(studentColumnsCount.get(i));
            if (i < studentColumnsCount.size() - 1){
                selectAll.append(", ");
            }
        }
        final String sqlRequest = String.format("SELECT count(%s)  FROM %s",selectAll, STUDENT_TABLE);
        System.out.println(selectAll);
        return select(sqlRequest);
    }

    // "Задание№7/Вывести студенток"
    public String selectStudentWoman(List<String> studentColumnsWoman) {
        StringBuilder selectAll = new StringBuilder();
        for (int i = 0; i < studentColumnsWoman.size(); i++){
            selectAll.append(STUDENT_TABLE).append(".").append(studentColumnsWoman.get(i));
            if (i < studentColumnsWoman.size() - 1){
                selectAll.append(", ");
            }
        }
        final String sqlRequest = String.format("SELECT %s FROM %s WHERE sex in (\"Ж\")",selectAll, STUDENT_TABLE);
        System.out.println(selectAll);
        return select(sqlRequest);
    }

    // "Задание№10/Вывести на экран студентов из определенной группы(поиск по имени группы)"
    public String selectStudentGroop(List<String> studentColumnsGroup,List<String> groopColumnsStudent) {
        StringBuilder selectAllStudent = new StringBuilder();
        for (int i = 0; i < studentColumnsGroup.size(); i++){
            selectAllStudent.append(STUDENT_TABLE).append(".").append(studentColumnsGroup.get(i));
            if (i < studentColumnsGroup.size() - 1){
                selectAllStudent.append(", ");
            }
        }

        StringBuilder selectAllGroop = new StringBuilder();
        for (int i = 0; i < groopColumnsStudent.size(); i++){
            selectAllGroop.append(GROOP_TABLE).append(".").append(groopColumnsStudent.get(i));
            if (i < groopColumnsStudent.size() - 1){
                selectAllGroop.append(", ");
            }
        }
        final String sqlRequest = String.format("SELECT %s, %s  FROM %s " +
                "JOIN %s  on %s.id_group = %s.id WHERE %s.name in (\"математика\",\"русский\",\"физика\") "
                ,selectAllStudent,selectAllGroop, STUDENT_TABLE, GROOP_TABLE, STUDENT_TABLE, GROOP_TABLE, GROOP_TABLE);
        System.out.println(selectAllStudent + ", " + selectAllGroop);
        return select(sqlRequest);
    }
}
