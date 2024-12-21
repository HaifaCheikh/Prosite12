package prosite12;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main implements management {

    public static void main(String[] args) {
        Main app = new Main();

        List<student> students = new ArrayList<>(Arrays.asList(
                new student(3, "Alice", 21),
                new student(1, "Bob", 23),
                new student(2, "Charlie", 20)
        ));

        app.displayStudents(students, System.out::println);


        System.out.println("Filtered Students:");
        app.displayStudentsByFilter(students, student -> student.getAge() > 20, System.out::println);

        // Renvoyer les noms des étudiants
        String names = app.returnStudentsNames(students, student::getName);
        System.out.println("Student Names: " + names);


        student newStudent = app.createStudent(() -> new student(4, "David", 22));
        System.out.println("Created Student: " + newStudent);


        List<student> sortedStudents = app.sortStudentsById(students, Comparator.comparingInt(student::getId));
        System.out.println("Sorted Students: " + sortedStudents);


        Stream<student> studentStream = app.convertToStream(students);
        System.out.println("Stream of Students:");
        studentStream.forEach(System.out::println);
    }

    @Override
    public void displayStudents(List<student> students, Consumer<student> con) {
        students.forEach(con);
    }

    @Override
    public void displayStudentsByFilter(List<student> students, Predicate<student> pre, Consumer<student> con) {
        students.stream().filter(pre).forEach(con);
    }

    @Override
    public String returnStudentsNames(List<student> students, Function<student, String> fun) {
        return students.stream()
                .map(fun)
                .collect(Collectors.joining(", "));
    }

    @Override
    public student createStudent(Supplier<student> sup) {
        return sup.get();
    }

    @Override
    public List<student> sortStudentsById(List<student> students, Comparator<student> com) {
        return students.stream()
                .sorted(com)
                .collect(Collectors.toList());
    }

    @Override
    public Stream<student> convertToStream(List<student> students) {
        return students.stream();
    }
}

