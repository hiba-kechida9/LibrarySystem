public abstract class Book implements Borrowable {
    protected String title;
    protected String author;
    protected String isbn;
    protected boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void borrow() { available = false; }

    @Override
    public void returnBook() { available = true; }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - " + title + " by " + author + " (ISBN: " + isbn + ") - " + (available ? "Available" : "Borrowed");
    }
}