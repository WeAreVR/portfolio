package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    public ComboBox comboBoxStudents;
    static String url = "jdbc:sqlite:C:/Users/Public/Documents/Databases/studentGrades.db";
    public Label LblStudentInfoGrade1;
    public Label LblStudentInfoGrade2;
    public Label LblStudentInfoCourse2;
    public Label LblStudentInfoCourse1;
    public Label LblStudentInfoName;
    Model model = new Model(url);


    public ObservableList<String> getStudentNames() {
        ArrayList<String> names = model.studentsFirstNameQueryStmt();
        ObservableList<String> studentNames = FXCollections.observableArrayList(names);
        return studentNames;
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
        comboBoxStudents.setItems(model.getStudentInfo());
    }


    public void getGrade() {
        model.preparedStmtStudentCourses();

        // fetches the selected student
        String currentStudent = (String) comboBoxStudents.getSelectionModel().getSelectedItem();

        // gets the last char of the string (which is equivalent to the studentID) AND converts it to an int
        char studentID = currentStudent.charAt(currentStudent.length() - 2);
        int studentIDint = Integer.parseInt(String.valueOf(studentID));

        ArrayList<String> courseNames = model.coursesNameQueryStmt();
        ArrayList<String> studentNames = model.studentsFirstNameQueryStmt();
        ArrayList<Integer> courseIDs = model.getStudentCourses(studentIDint);


        String courseName1 = courseNames.get(courseIDs.get(0) - 1);
        String courseName2 = courseNames.get(courseIDs.get(1) - 1);


        System.out.println(courseNames);
        System.out.println(courseName2);


        ArrayList<String> grades = model.getStudentGrade(studentIDint, model.getStudentCourses(studentIDint));

        // Changes the labels in Student Info, so it shows the grades and courses
        LblStudentInfoName.setText(studentNames.get(studentIDint - 1));

        LblStudentInfoGrade1.setText("" + grades.get(0));
        LblStudentInfoGrade2.setText("" + grades.get(1));

        LblStudentInfoCourse1.setText(courseName1 + ":");
        LblStudentInfoCourse2.setText(courseName2 + ":");
    }


}
