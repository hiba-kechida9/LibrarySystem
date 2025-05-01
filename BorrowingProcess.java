import java.time.LocalDate;

public class BorrowingProcess {
    private Book book;
    private Borrower borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingProcess(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.returnBook();
        borrower.returnBook(book);
    }

    public Book getBook() { return book; }
    public Borrower getBorrower() { return borrower; }

    @Override
    public String toString() {
        return borrower + " borrowed \"" + book.getTitle() + "\" on " + borrowDate +
               (returnDate != null ? ", returned on " + returnDate : ", not returned yet");
    }
}