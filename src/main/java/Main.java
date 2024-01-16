import dataobject.Curator;
import dataobject.Groop;
import dataobject.Student;
import db.IDBConnector;
import db.MySqlConnector;
import tables.*;

import java.util.List;

import static dataobject.Curator.curators;
import static dataobject.Groop.groops;
import static dataobject.Student.students;

public class Main {

    public static void main(String[] args) {
        /// создание обьектов
        IDBConnector mySqlConnector = new MySqlConnector();
        StudentTable studentTable = new StudentTable(mySqlConnector);
        GroopTable groupTable = new GroopTable(mySqlConnector);
        CuratorTable curatorTable = new CuratorTable(mySqlConnector);


        // обьектам присваеваем метод создания таблиц
        studentTable.create();
        groupTable.create();
        curatorTable.create();

        System.out.println(studentTable);
            for (Student student: students) {
            studentTable.insert(student);
        }

            System.out.println("------------------Все данные из таблицы studentTable------------------");
            List<String> studentColumns = List.of("id","fio","sex","id_group");
            studentTable.selectAll(studentColumns);

        System.out.println(groupTable);
            for (Groop groop : groops) {
            groupTable.insert(groop);
        }

        System.out.println("-------------------Все данные из таблицы groopTable--------------------");
            List<String> groopColumns = List.of("id","name","id_curator");
        groupTable.selectAll(groopColumns);

        System.out.println(curatorTable);
            for (Curator curator:curators) {
            curatorTable.insert(curator);
        }

        System.out.println("-------------------Все данные из таблицы curatorTable--------------------");
            List<String>curatorColumns = List.of("id","fio");
        curatorTable.selectAll(curatorColumns);

        System.out.println("--Задание№5/-----Информация о всех студентах включая название группы и имя куратора-----");
        List<String>studentColumnsGroopCurator = List.of("id","fio","sex","id_group");
        List<String>groopColumnsStudentCurator = List.of("id","name","id_curator");
        List<String>curatorColumnsStudentGroop = List.of("id","fio");
        studentTable.selectStudentTable(
                studentColumnsGroopCurator,
                groopColumnsStudentCurator,
                curatorColumnsStudentGroop);

        System.out.println("--Задание№6/-----Вывести на экран количество студентов---------------------------------");
        List<String> studentColumnsCount = List.of("id");
        studentTable.selectCountStudent(studentColumnsCount);

        System.out.println("--Задание№7/-----Вывести студенток------------------------------------------------------");
        List<String> studentColumnsWoman = List.of("id","fio","sex","id_group");
        studentTable.selectStudentWoman(studentColumnsWoman);

        System.out.println("--Задание№8/-----Обновить данные по группе сменив куратора------------------------------");
        List<String> curatorColumnsUpdate = List.of("id","fio");
        curatorTable.updateCurator(curatorColumnsUpdate);

        System.out.println("---------------------Обновленные данные по таблицы Curator------------------------------");
        curatorTable.selectAll(curatorColumns);

        System.out.println("--Задание№9/------Вывести список групп с их кураторами----------------------------------");
        List<String>groopColumnsCurator = List.of("id","name","id_curator");
        List<String>curatorColumnsGroop = List.of("id","fio");
        groupTable.selectGroopCurator(groopColumnsCurator,curatorColumnsGroop);

        System.out.println("--Задание№10/--Вывести на экран студентов из определенной группы(поиск по имени группы)-");
        List<String> studentColumnsGroop = List.of("id","fio","sex","id_group");
        List<String> groopColumnsStudent = List.of("id","name","id_curator");
        studentTable.selectStudentGroop(studentColumnsGroop,groopColumnsStudent);

        // Закрываем соединения
        mySqlConnector.close();
        System.out.println("------------Соединение успешно закрыто---------------------");
    }

}
