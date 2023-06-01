package javastudy.resultG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoardDAO {
    private Connection conn;

    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardList = new ArrayList<>();

        try {
            String sql = "SELECT bno, btitle, bcontent, bwriter, bdate FROM boards ORDER BY bno DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));

                boardList.add(board);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boardList;
    }

    public void insertBoard(BoardDTO board) {
        try {
            String sql = "INSERT INTO boards (btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, now())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBoards(List<BoardDTO> list) {
        String sql = "INSERT INTO boards (btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, now())";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (BoardDTO board : list) {
                pstmt.setString(1, board.getBtitle());
                pstmt.setString(2, board.getBcontent());
                pstmt.setString(3, board.getBwriter());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(pstmt!=null){
                    pstmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void scheduler(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.schedule(() -> {

            String sql = "INSERT INTO boards (btitle, bcontent, bwriter, bdate) VALUES (?, ?, ?, now())";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }, 10, TimeUnit.HOURS);

    }

    public BoardDTO getBoardById(int bno) {
        BoardDTO board = null;

        try {
            String sql = "SELECT bno, btitle, bcontent, bwriter, bdate FROM boards WHERE bno=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bno);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                board = new BoardDTO();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return board;
    }

    public void updateBoard(BoardDTO board) {
        try {
            String sql = "UPDATE boards SET btitle=?, bcontent=?, bwriter=? WHERE bno=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());
            pstmt.setInt(4, board.getBno());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBoard(BoardDTO board) {
        try {
            String sql = "DELETE FROM boards WHERE bno=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getBno());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllBoards() {
        try {
            String sql = "TRUNCATE TABLE boards";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
