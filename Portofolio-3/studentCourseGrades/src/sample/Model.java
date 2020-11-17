package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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


    //Returns ArrayList containing all students firstNames
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

    //Returns an Observable ArrayList containing Strings with Students' name and ID
    public ObservableList<String> getStudentInfo() {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT firstName, lastName, studentID FROM students;";
        String string = "";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                for (int i = 1; i <= 3; i++) {
                    //for every selected item, add it to the string (with a space between)
                    string += " " + rs.getString(i);
                }
                list.add(string);
                //Set to empty, so it doesn't save the info of the previous student
                string = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<String> result = FXCollections.observableArrayList(list);
        return result;
    }

    //Returns an Observable ArrayList containing Strings with Students' name and ID
    public String getStudentInfo(int studentID) {
        String sql = "SELECT firstName, lastName, studentID " +
                "FROM students " +
                "WHERE studentID = " + studentID + ";";
        String string = "";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                for (int i = 1; i <= 3; i++) {
                    //for every selected item, add it to the string (with a space between)
                    string += " " + rs.getString(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return string;
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

    //Returns an ArrayList of student info for a course's student
    public ArrayList<Integer> getCourseStudents(Integer courseID) {
        ArrayList<Integer> list = new ArrayList<>();
        String sql;

        sql = "SELECT S.studentID FROM grades as G " +
                "INNER JOIN students as S on G.studentID = S.studentID " +
                "INNER JOIN courses as C on G.courseID = C.courseID " +
                "WHERE G.courseID = " + courseID + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                int student = rs.getInt(1);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

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


    // List of all course teachers
    public ArrayList<String> coursesTeacherQueryStmt() {
        ArrayList<String> courseNames = new ArrayList<String>();
        String sql = "SELECT teacher FROM courses;";

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


    //Returns the average grade of a selected student
    public float getAverageGradeStudent(int studentID) {
        float avgGrade = 0;
        String sql;

        sql = "SELECT AVG(grade) as averageGradeStudent " +
                "FROM grades " +
                "WHERE studentID = " + studentID + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                avgGrade = rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return avgGrade;
    }


    //Returns the average grade of a selected course
    public float getAverageGradeCourse(int courseID) {
        float avgGrade = 0;
        String sql;

        sql = "SELECT AVG(grade) as averageGradeCourse " +
                "FROM grades " +
                "WHERE courseID = " + courseID + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                avgGrade = rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return avgGrade;
    }


    //Returns an Observable ArrayList containing Strings with Students' name and ID
    public ObservableList<String> getCourseInfo() {
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT courseID, name, semester FROM courses;";
        String string = "";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                for (int i = 1; i <= 3; i++) {
                    //for every selected item, add it to the string (with a space between)
                    string += rs.getString(i) + " ";
                }
                list.add(string);
                //Set to empty, so it doesn't save the info of the previous student
                string = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<String> result = FXCollections.observableArrayList(list);
        return result;
    }


    public void insertGradeInDB(int grade, int studentID, int courseID) {
        String sql = "UPDATE grades " +
                "set grade = " + grade + " " +
                "WHERE studentID = " + studentID + " AND courseID = " + courseID + " AND grade IS NULL;";
        try {
            stmt.executeUpdate(sql);
            System.out.println(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
