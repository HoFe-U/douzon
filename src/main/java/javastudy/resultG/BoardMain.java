package javastudy.resultG;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardMain {
    private Scanner scanner;
    private BoardDAO boardDAO;
    
    public BoardMain() {
        scanner = new Scanner(System.in);
        ConnectionManager connectionManager = new ConnectionManager();
        boardDAO = new BoardDAO(connectionManager.getConnection());
    }
    
    public void listBoards() {
        List<BoardDTO> boardList = boardDAO.getAllBoards();
        
        System.out.println("[전체 게시물 조회]");
        System.out.println("-------------------------------------------------------------------");
        
        for (BoardDTO board : boardList) {
            System.out.println("번호: " + board.getBno());
            System.out.println("제목: " + board.getBtitle());
            System.out.println("내용: " + board.getBcontent());
            System.out.println("글쓴이: " + board.getBwriter());
            System.out.println("등록일: " + board.getBdate());
            System.out.println("-------------------------------------------------------------------");
        }
        
        menu();
    }
    
    public void createBoard() {
        BoardDTO board = new BoardDTO();
        
        System.out.println("[게시물 등록]");
        System.out.print("제목: ");     
        board.setBtitle(scanner.nextLine());
        System.out.print("내용: ");     
        board.setBcontent(scanner.nextLine());
        System.out.print("글쓴이: ");     
        board.setBwriter(scanner.nextLine());
        
        boardDAO.insertBoard(board);
        
        listBoards();
    }

    public void createBoard2() {
        List<BoardDTO> list = new ArrayList<>();

        System.out.println("몇개의 게시물을 등록할 건가요?");
        int inputValue = scanner.nextInt();

        for (int i = 0; i < inputValue; i++) {
            BoardDTO board = new BoardDTO();
            System.out.println("[게시물 등록]");
            System.out.print("제목: ");
            board.setBtitle(scanner.nextLine());
            System.out.print("내용: ");
            board.setBcontent(scanner.nextLine());
            System.out.print("글쓴이: ");
            board.setBwriter(scanner.nextLine());
            list.add(board);
        }
        boardDAO.insertBoards(list);
        listBoards();
    }
    
    public void readBoard() {
        System.out.print("조회할 게시물 번호: ");
        int bno = Integer.parseInt(scanner.nextLine());
        
        BoardDTO board = boardDAO.getBoardById(bno);
        
        System.out.println("[게시물 조회]");
        System.out.println("-------------------------------------------------------------------");
        
        if (board != null) {
            System.out.println("번호: " + board.getBno());
            System.out.println("제목: " + board.getBtitle());
            System.out.println("내용: " + board.getBcontent());
            System.out.println("글쓴이: " + board.getBwriter());
            System.out.println("등록일: " + board.getBdate());
        } else {
            System.out.println("해당 번호의 게시물이 없습니다.");
        }
        
        System.out.println("-------------------------------------------------------------------");
        
        menu();
    }
    
    public void updateBoard() {
        System.out.print("수정할 게시물 번호: ");
        int bno = Integer.parseInt(scanner.nextLine());
        
        BoardDTO board = boardDAO.getBoardById(bno);
        
        System.out.println("[게시물 수정]");
        System.out.println("-------------------------------------------------------------------");
        
        if (board != null) {
            System.out.println("번호: " + board.getBno());
            System.out.println("제목: " + board.getBtitle());
            System.out.println("내용: " + board.getBcontent());
            System.out.println("글쓴이: " + board.getBwriter());
            System.out.println("등록일: " + board.getBdate());
            
            System.out.print("새로운 제목: ");
            board.setBtitle(scanner.nextLine());
            System.out.print("새로운 내용: ");
            board.setBcontent(scanner.nextLine());
            System.out.print("새로운 글쓴이: ");
            board.setBwriter(scanner.nextLine());
            
            boardDAO.updateBoard(board);
            
            System.out.println("게시물이 수정되었습니다.");
        } else {
            System.out.println("해당 번호의 게시물이 없습니다.");
        }
        
        System.out.println("-------------------------------------------------------------------");
        
        menu();
    }
    
    public void deleteBoard() {
        System.out.print("삭제할 게시물 번호: ");
        int bno = Integer.parseInt(scanner.nextLine());
        
        BoardDTO board = boardDAO.getBoardById(bno);
        
        System.out.println("[게시물 삭제]");
        System.out.println("-------------------------------------------------------------------");
        
        if (board != null) {
            System.out.println("번호: " + board.getBno());
            System.out.println("제목: " + board.getBtitle());
            System.out.println("내용: " + board.getBcontent());
            System.out.println("글쓴이: " + board.getBwriter());
            System.out.println("등록일: " + board.getBdate());
            
            System.out.print("정말로 삭제하시겠습니까? (Y/N) ");
            String answer = scanner.nextLine();
            
            if (answer.equalsIgnoreCase("Y")) {
                boardDAO.deleteBoard(board);
                System.out.println("게시물이 삭제되었습니다.");
            } else {
                System.out.println("삭제가 취소되었습니다.");
            }
        } else {
            System.out.println("해당 번호의 게시물이 없습니다.");
        }
        
        System.out.println("-------------------------------------------------------------------");
        
        menu();
    }
    
    public void deleteAllBoards() {
        System.out.print("모든 게시물을 삭제하시겠습니까? (Y/N) ");
        String answer = scanner.nextLine();
        
        if (answer.equalsIgnoreCase("Y")) {
            boardDAO.deleteAllBoards();
            System.out.println("모든 게시물이 삭제되었습니다.");
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
        
        System.out.println("-------------------------------------------------------------------");
        
        menu();
    }
    
    public void menu() {
        System.out.println("1. 전체 게시물 조회");
        System.out.println("2. 게시물 등록");
        System.out.println("3. 게시물 조회");
        System.out.println("4. 게시물 수정");
        System.out.println("5. 게시물 삭제");
        System.out.println("6. 모든 게시물 삭제");
        System.out.println("7. 다중 게시물 작성");
        System.out.println("0. 종료");
        
        System.out.print("메뉴 선택: ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        switch (choice) {
            case 1:
                listBoards();
                break;
            case 2:
                createBoard();
                break;
            case 3:
                readBoard();
                break;
            case 4:
                updateBoard();
                break;
            case 5:
                deleteBoard();
                break;
            case 6:
                deleteAllBoards();
                break;
            case 7:
                createBoard2();
                break;
            case 0:
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                ConnectionManager connectionManager = new ConnectionManager();
                connectionManager.closeConnection(boardDAO.getConn());
                break;
            default:
                System.out.println("잘못된 메뉴 선택입니다.");
                menu();
                break;
        }
    }
    
    public static void main(String[] args) {
        BoardMain example = new BoardMain();
        example.menu();
    }
}
