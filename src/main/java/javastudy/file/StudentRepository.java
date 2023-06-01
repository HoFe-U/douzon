package javastudy.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public StudentRepository() {
        ready();
    }

    private void ready() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("Abc1115.txt")));) {
            Student student = readStudent(reader);
            students.add(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Student student) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 예시
            String url = "jdbc:mysql://localhost:3306/basic"; // 데이터베이스 URL
            String username = "hofe"; // 사용자명
            String password = "hofe"; // 암호

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sql = "insert into `Students` values ( " + student.toString() + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            connection.close();
            statement.close();
            resultSet.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student readStudent(BufferedReader reader) throws IOException {
        String value = reader.readLine();
        return new Student.Builder()
            .studentNo(Integer.parseInt(value.substring(0, 5).trim()))
            .email(value.substring(6, 9).trim())
            .koreanScore(Integer.parseInt(value.substring(10, 12).trim()))
            .englishScore(Integer.parseInt(value.substring(13, 15).trim()))
            .mathScore(Integer.parseInt(value.substring(16, 18).trim()))
            .nationalHistoryScore(Integer.parseInt(value.substring(19, 21).trim()))
            .teacherCode(value.substring(22))
            .achievement(value.substring(23))
            .localeCode(value.substring(24))
            .build();
    }

    public List<Student> getStudents() {
        return students;
    }

}
