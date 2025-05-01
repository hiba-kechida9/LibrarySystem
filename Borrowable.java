public interface Borrowable {
    boolean isAvailable();
    void borrow();
    void returnBook();
}