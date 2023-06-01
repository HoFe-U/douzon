package javastudy.book;

import java.sql.SQLException;
import java.util.Scanner;

import javastudy.book.dao.UserDao;
import javastudy.book.dto.UserDTO;
import javastudy.book.service.BookService;
import javastudy.book.service.RentalHistoryService;

public class BookSystem {
    private static final Scanner scanner = new Scanner(System.in);
    public static ThreadLocal<Integer> THREAD_LOCAL_USER_NO = new ThreadLocal<>();
    private final BookService bookService;
    private final UserDao userDao;
    private final RentalHistoryService rentalHistoryService;

    public BookSystem() {
        bookService = new BookService();
        userDao = new UserDao();
        rentalHistoryService = new RentalHistoryService();
    }

    public static void main(String[] args) {
        BookSystem bs = new BookSystem();

        try {
            while (true) {
                bs.login();
                if (!bs.start()) {
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    public void login() throws SQLException {
        System.out.print("아이디를 입력하세요: ");
        int userNo = scanner.nextInt();
        THREAD_LOCAL_USER_NO.set(userNo);
        UserDTO user = userDao.login(userNo);
        System.out.println("로그인 되었습니다.");
        System.out.print("유저 이름: " + user.getUserNo() + "\t");
        System.out.print("유저 등급: " + user.getGradeCode() + "\t");
        System.out.println("연체 일자: " + user.getOverdueDate() + "\t");
    }

    public boolean start() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("1. 카테고리 조회" + "| \t" + "2. 검색" + "| \t" + "3. 반납" + "| \t" + "4. 로그아웃" + "| \t" + "5. 종료" + "\t");
        int menuNo = scanner.nextInt();

        switch (menuNo) {
            case 1:
                bookService.findCategories();
                break;
            case 2:
                bookService.searchType();
                break;
            case 3:
                rentalHistoryService.findRentalHistoriesByUserNo(THREAD_LOCAL_USER_NO.get());
                break;
            case 4:
                logout();
                return true;
            case 5:
                return false;
            default:
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                break;
        }

        return true;
    }

    public void logout() {
        THREAD_LOCAL_USER_NO.remove();
        System.out.println("로그아웃 되었습니다.");
    }
}
