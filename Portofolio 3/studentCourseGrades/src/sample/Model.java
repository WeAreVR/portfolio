package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

import java.util.ArrayList;

public class Model {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    String url;


    public Model(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        conn = getConnection(this.url);
    }

    public void createStatement() throws SQLException {
        this.stmt = conn.createStatement();
    }

    public ArrayList<String> studentsFirstNameQueryStmt() {
        ArrayList<String> studentNames = new ArrayList<String>();
        String sql = "SELECT firstName FROM students;";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String name = rs.getString(1);
                studentNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return studentNames;
    }

    public ObservableList<String> getStudentInfo() {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT firstName, lastName, studentID FROM students;";
        String ret = "";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                for (int i = 1; i <= 3; i++) {
                    ret += rs.getString(i) + " ";
                }
                list.add(ret);
                ret = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<String> slut = FXCollections.observableArrayList(list);
        return slut;
    }


    //Prepared Statement for making a list of a students courses. Is used in getStudentCourses()
    public void preparedStmtStudentCourses() {
        String sql = "SELECT C.courseID FROM grades as G " +
                "INNER JOIN students as S on G.studentID = S.studentID " +
                "INNER JOIN courses as C on G.courseID = C.courseID " +
                "WHERE G.studentID = ?;";
        try {
            pstmt = conn.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //Returns an ArrayList of courseID for a students courses
    public ArrayList<Integer> getStudentCourses(Integer studentID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs != null && rs.next()) {
                int course = rs.getInt(1);
                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    // returns an ArrayList of grades for the selected student
    public ArrayList<String> getStudentGrade(int studentID, ArrayList<Integer> courses) {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "";

        for (int i = 0; i < courses.size(); i++) {
            sql = "SELECT grade " +
                    "From grades as G " +
                    "INNER JOIN students as S on G.studentID = S.studentID " +
                    "INNER JOIN courses as C on G.courseID = C.courseID " +
                    "WHERE G.studentID = " + studentID + " AND C.courseID = " + courses.get(i) + ";";
            try {
                ResultSet rs = stmt.executeQuery(sql);
                String grade;
                while (rs != null && rs.next()) {
                    grade = rs.getString(1);
                    list.add(grade);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return list;
    }


    // List of all course names
    public ArrayList<String> coursesNameQueryStmt() {
        ArrayList<String> courseNames = new ArrayList<String>();
        String sql = "SELECT name FROM courses;";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String name = rs.getString(1);
                courseNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return courseNames;
    }


}
