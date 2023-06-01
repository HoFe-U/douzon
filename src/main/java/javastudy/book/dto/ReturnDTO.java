package javastudy.book.dto;

import java.time.LocalDate;

public class ReturnDTO {
	
	private int bookNo;
	
	private String author;
	
	private int historyNo;
	
	private LocalDate rentalDate;
	
	private LocalDate expectedReturnDate;
	
	public ReturnDTO() {}

	
	public ReturnDTO(int bookNo, String author, int historyNo, LocalDate rentalDate, LocalDate expectedReturnDate) {
		super();
		this.bookNo = bookNo;
		this.author = author;
		this.historyNo = historyNo;
		this.rentalDate = rentalDate;
		this.expectedReturnDate = expectedReturnDate;
	}

	

	public int getBookNo() {
		return bookNo;
	}


	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getHistoryNo() {
		return historyNo;
	}


	public void setHistoryNo(int historyNo) {
		this.historyNo = historyNo;
	}


	public LocalDate getRentalDate() {
		return rentalDate;
	}


	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}


	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}


	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}


	@Override
	public String toString() {
		return historyNo  +"\t"  + author + "\t" + rentalDate
				+ "\t" + expectedReturnDate+"\n";
	}
	
	
	
	
	
	
	

}
