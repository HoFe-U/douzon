package javastudy.book.dto;

import java.util.Date;

public class UserDTO {

    private int userNo;
    private int gradeCode;
    private Date overdueDate;

	public UserDTO(int userNo, int gradeCode, Date overdueDate) {
		this.userNo = userNo;
		this.gradeCode = gradeCode;
		this.overdueDate = overdueDate;
	}

	public Date getOverdueDate() {
        return overdueDate;
    }
    public int getUserNo() {
        return userNo;
    }
    public int getGradeCode() {
        return gradeCode;
    }

}
