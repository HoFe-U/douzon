package javastudy.file;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }
    public void start(){
        StudentRepository studentRepository = new StudentRepository();
        StudentService service = new StudentService(studentRepository);
        service.service();

    }
}
