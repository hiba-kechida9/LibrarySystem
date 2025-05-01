import java.util.*;

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();
    private static List<Borrower> borrowers = new ArrayList<>();
    private static List<BorrowingProcess> history = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Book\n2. Add Borrower\n3. Borrow Book\n4. Return Book\n5. Search Book\n6. Search Borrower\n7. Show Borrowed Books\n8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addBorrower();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> searchBorrower();
                case 7 -> showBorrowedBooks();
                case 8 -> System.exit(0);
            }
        }
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Type (1=Paper, 2=EBook): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        Book book = (type == 1) ? new PaperBook(title, author, isbn) : new EBook(title, author, isbn);
        books.add(book);
        System.out.println("Book added.");
    }

    private static void addBorrower() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Student ID: ");
        String id = scanner.nextLine();
        borrowers.add(new Borrower(name, id));
        System.out.println("Borrower added.");
    }

    private static void borrowBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        Book book = findBook(isbn);
        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Borrower borrower = findBorrower(id);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        book.borrow();
        borrower.borrowBook(book);
        history.add(new BorrowingProcess(book, borrower));
        System.out.println("Book borrowed.");
    }

    private static void returnBook() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        Book book = findBook(isbn);
        if (book == null || book.isAvailable()) {
            System.out.println("Book is not currently borrowed.");
            return;
        }

        for (BorrowingProcess process : history) {
            if (process.getBook() == book && book.isAvailable() == false) {
                process.returnBook();
                System.out.println("Book returned.");
                return;
            }
        }
    }

    private static void searchBook() {
        System.out.print("Enter title or ISBN: ");
        String input = scanner.nextLine();
        books.stream()
             .filter(b -> b.getTitle().equalsIgnoreCase(input) || b.getIsbn().equalsIgnoreCase(input))
             .forEach(System.out::println);
    }

    private static void searchBorrower() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        borrowers.stream()
                 .filter(b -> b.getStudentId().equalsIgnoreCase(id))
                 .forEach(System.out::println);
    }

    private static void showBorrowedBooks() {System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Borrower borrower = findBorrower(id);
        if (borrower != null) {
            borrower.getBorrowedBooks().forEach(System.out::println);
        } else {
            System.out.println("Borrower not found.");
        }
    }

    private static Book findBook(String isbn) {
        return books.stream()
                    .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                    .findFirst()
                    .orElse(null);
    }

    private static Borrower findBorrower(String id) {
        return borrowers.stream()
                        .filter(b -> b.getStudentId().equalsIgnoreCase(id))
                        .findFirst()
                        .orElse(null);
    }
}