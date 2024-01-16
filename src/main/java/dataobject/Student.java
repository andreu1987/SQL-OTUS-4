package dataobject;

import java.util.List;

public class Student {
    private  String id;
    private  String fio;
    private  String sex;
    private  String id_group;

   public Student (String id,String fio,String sex,String id_group){
       this.id = id;
       this.fio = fio;
       this.sex = sex;
       this.id_group = id_group;
   }

    // Список колонок в таблицы students
    public static List<Student> students = List.of(
            new Student("1","Иванов","m","1"),
            new Student("2","Петров","m","2"),
            new Student("3","Сидоров","m","3"),
            new Student("4","Макаров","m","1"),
            new Student("5","Первый","m","2"),
            new Student("6","Второй","m","3"),
            new Student("7","Пятая","ж","1"),
            new Student("8","Белая","ж","2"),
            new Student("9","Черная","ж","3"),
            new Student("10","Красная","ж","1"),
            new Student("11","Оршова","m","2"),
            new Student("12","Егоров","m","3"),
            new Student("13","Сурова","ж","1"),
            new Student("14","Ухова","ж","2"),
            new Student("15","Носова","ж","3")
    );

   public String getId(){
       return id;
   }

   public void setId(String id){
       this.id = id;
   }

    public String getFio(){
        return fio;
    }

    public void setFio(String fio){
        this.fio = fio;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getId_group(){
        return id_group;
    }

    public void setId_group(String id_group){
        this.id_group = id_group;
    }


}

