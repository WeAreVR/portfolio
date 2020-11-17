package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    static String url = "jdbc:sqlite:C:/Users/Public/Documents/Databases/studentGrades.db";
    Model model = new Model(url);

    //labels and combobox for student tab
    public ComboBox comboBoxStudents;
    public Label LblStudentInfoGrade1;
    public Label LblStudentInfoGrade2;
    public Label LblStudentInfoCourse2;
    public Label LblStudentInfoCourse1;
    public Label LblStudentInfoName;
    public Label LblStudentInfoAvgGrade;

    //labels and combobox for course tab
    public ComboBox comboBoxCourses;
    public Label LblCourseInfoName;
    public Label LblCourseInfoTeacher;
    public Label LblCourseInfoAvgGrade;

    //labels and comboboxes for insert grade tab
    public ComboBox comboBoxGradeGrade;
    public ComboBox comboBoxGradeStudent;
    public ComboBox comboBoxGradeCourse;
    public Label insertedGradeInfo;


    public ObservableList<String> getStudentNames() {
        ArrayList<String> names = model.studentsFirstNameQueryStmt();
        return FXCollections.observableArrayList(names);
    }

    public void connectToDatabase() {
        try {
            model.connect();
            model.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void initialize() {
        connectToDatabase();
        ObservableList<String> studentInfo = model.getStudentInfo();
        ObservableList<String> courseInfo = model.getCourseInfo();
        ObservableList<Integer> listOfGrades = getListOfGrades();

        //set items in course tab to course names and ids
        comboBoxCourses.setItems(courseInfo);

        //set items in student tab to student names and ids
        comboBoxStudents.setItems(studentInfo);

        //set item in grade tab with list of courses, student and grades
        comboBoxGradeGrade.setItems(listOfGrades);
        comboBoxGradeCourse.setItems(courseInfo);

    }


    //void for using the student tab
    public void getStudentGrades() {
        model.preparedStmtStudentCourses();

        // Fetches the selected student
        String currentStudent = (String) comboBoxStudents.getSelectionModel().getSelectedItem();

        // gets the last word of the string (which is equivalent to the studentID) AND converts it to an int
        String lastWord = currentStudent.substring(currentStudent.lastIndexOf(" ") + 1);
        int studentID = Integer.parseInt(lastWord);

        ArrayList<String> courseNames = model.coursesNameQueryStmt();
        ArrayList<String> studentNames = model.studentsFirstNameQueryStmt();
        ArrayList<Integer> courseIDs = model.getStudentCourses(studentID);

        String infoCourse1 = courseNames.get(courseIDs.get(0) - 1);
        String infoCourse2 = courseNames.get(courseIDs.get(1) - 1);
        float averageGradeStudent = model.getAverageGradeStudent(studentID);

        ArrayList<String> grades = model.getStudentGrade(studentID, model.getStudentCourses(studentID));

        // Changes the labels in Student Info, so it shows the grades and courses
        LblStudentInfoName.setText(studentNames.get(studentID - 1) + " (" + studentID + ")");

        LblStudentInfoGrade1.setText(grades.get(0));
        LblStudentInfoGrade2.setText(grades.get(1));

        LblStudentInfoCourse1.setText(infoCourse1 + ":");
        LblStudentInfoCourse2.setText(infoCourse2 + ":");

        LblStudentInfoAvgGrade.setText("" + averageGradeStudent);
    }


    public void getCourseGrades() {
        // Fetches the selected student
        String currentCourse = (String) comboBoxCourses.getSelectionModel().getSelectedItem();

        ArrayList<String> courseNames = model.coursesNameQueryStmt();
        ArrayList<String> courseTeachers = model.coursesTeacherQueryStmt();

        char courseIDCurrentCourse = currentCourse.charAt(0);
        int courseID = Integer.parseInt(String.valueOf(courseIDCurrentCourse));

        float averageGradeCourse = model.getAverageGradeCourse(courseID);


        LblCourseInfoName.setText(courseNames.get(courseID - 1));
        LblCourseInfoTeacher.setText(courseTeachers.get(courseID - 1));
        LblCourseInfoAvgGrade.setText(String.valueOf(averageGradeCourse));

    }

    public void insertGradeStudent() {
        String currentCourse = (String) comboBoxGradeCourse.getSelectionModel().getSelectedItem();

        //gets courseID
        char firstChar = currentCourse.charAt(0);
        int courseID = Integer.parseInt(String.valueOf(firstChar));

        ArrayList<Integer> studentsInCourse = model.getCourseStudents(courseID);
        ObservableList<String> studentInfo = FXCollections.observableArrayList();

        String student;
        for (Integer s : studentsInCourse) {
            student = model.getStudentInfo(s);
            studentInfo.add(student);
        }
        comboBoxGradeStudent.setItems(studentInfo);

    }

    public void insertGrade() {
        String currentCourse = (String) comboBoxGradeCourse.getSelectionModel().getSelectedItem();
        String currentStudent = (String) comboBoxGradeStudent.getSelectionModel().getSelectedItem();

        // gets the last word of the string (which is equivalent to the studentID) AND converts it to an int
        String lastWord = currentStudent.substring(currentStudent.lastIndexOf(" ") + 1);
        int studentID = Integer.parseInt(lastWord);

        //gets courseID
        char firstChar = currentCourse.charAt(0);
        int courseID = Integer.parseInt(String.valueOf(firstChar));

        int currentGrade = (int) comboBoxGradeGrade.getSelectionModel().getSelectedItem();


        model.insertGradeInDB(currentGrade, studentID, courseID);
        System.out.println("grade: " + currentGrade + "\ncourseID: " + courseID + "\nstudentID: " + studentID);
    }

    public ObservableList<Integer> getListOfGrades() {
        ObservableList<Integer> listOfGrades = FXCollections.observableArrayList();
        listOfGrades.add(-3);
        listOfGrades.add(00);
        listOfGrades.add(02);
        listOfGrades.add(4);
        listOfGrades.add(7);
        listOfGrades.add(10);
        listOfGrades.add(12);

        return listOfGrades;
    }

}
