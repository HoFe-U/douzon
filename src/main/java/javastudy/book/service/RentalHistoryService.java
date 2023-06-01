
package javastudy.book.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javastudy.book.dao.RentalHistoryDAO;
import javastudy.book.dto.ReturnDTO;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class RentalHistoryService {
    private final RentalHistoryDAO rentalHistoryDAO;
    private final Scanner scanner;

    public RentalHistoryService() {
        this.scanner = new Scanner(System.in);
        this.rentalHistoryDAO = new RentalHistoryDAO();
    }

    public void findRentalHistoriesByUserNo(int userNo) {
        try {
            List<ReturnDTO> rentalHistories = rentalHistoryDAO.selectRentalHistories(userNo);

            System.out.println("대출번호\t작가\t대출일자\t\t반납예정일");
            if (rentalHistories.isEmpty()) {
                System.out.println("==========================================");
                System.out.println("대출한 도서 내역이 없습니다.");
            } else {
                System.out.println("==========================================");
                for (ReturnDTO dto : rentalHistories) {
                    System.out.printf("%d\t%s\t%s\t%s\n",
                        dto.getHistoryNo(),
                        dto.getAuthor(),
                        dto.getRentalDate(),
                        dto.getExpectedReturnDate());
                }

                System.out.println("==========================================");
                System.out.print("반납할 도서의 번호를 선택하세요.>>");
                int historyNo = scanner.nextInt();

                for (ReturnDTO dto : rentalHistories) {
                    if (historyNo == dto.getHistoryNo()) {
                        returnBook(userNo, historyNo, dto.getBookNo());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int userNo, int historyNo, int bookNo) {
        System.out.print("정말로 반납하시겠습니까? y/n>>");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("y")) {
            try {
                boolean success = rentalHistoryDAO.updateRentalHistories(historyNo, bookNo);

                if (!success) {
                    System.out.println("반납 실패!!");
                    findRentalHistoriesByUserNo(userNo);
                } else {
                    int overdueCount = rentalHistoryDAO.checkOverDue(historyNo);
                    if (overdueCount > 0) {
                        System.out.println("연체일자: " + overdueCount);
                        System.out.println("반납 완료되었습니다. 연체일자만큼 대출 불가합니다!!");
                        System.out.println();
                        System.out.println("==========================================");

                        rentalHistoryDAO.insertOverDue(overdueCount, userNo);
                        findRentalHistoriesByUserNo(userNo);
                    } else {
                        System.out.println("반납 완료되었습니다.");
                        System.out.println();
                        System.out.println("==========================================");
                        findRentalHistoriesByUserNo(userNo);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println();
            System.out.println("==========================================");
            findRentalHistoriesByUserNo(userNo);
        }
    }
}
