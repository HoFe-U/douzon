package javastudy.file;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import javax.swing.filechooser.FileFilter;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void service() {
        List<Student> students = studentRepository.getStudents();
        students.sort(new StudentUpperCompare());
        Collections.reverseOrder();
    }
}

class StudentUpperCompare implements Comparator<Student> {
    List<String> list = new ArrayList<>();
    @Override
    public int compare(Student o1, Student o2) {
        if ((o1.getKoreanScore() + o1.getEnglishScore()) < o2.getKoreanScore() + o2.getEnglishScore()) {
            return 1;
        } else if ((o1.getKoreanScore() + o1.getEnglishScore()) == o2.getKoreanScore() + o2.getEnglishScore()) {
            return 0;
        }
        return -1;
    }
}
class StudentLowerCompare implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return 0;
    }
}