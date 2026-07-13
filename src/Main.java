import java.util.ArrayList;
import java.util.Scanner;

class Book {

    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    public String describe() {
        String status = isBorrowed ? "Borrowed" : "Available";
        return "Title: " + title + " | Author: " + author + " | Status: " + status;
    }
}

class Library {

    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void listBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n========== Library Books ==========");

        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).describe());
        }
    }

    public void borrowBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                if (!book.isBorrowed()) {
                    book.borrow();
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is already borrowed.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void returnBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }

                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void searchBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found!");
                System.out.println(book.describe());
                return;
            }
        }

        System.out.println("Book not found.");
    }
}

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        try (Scanner sc = new Scanner(System.in)) {

            int choice;

            do {

                System.out.println("\n========== Library Information System ==========");
                System.out.println("1. Add a Book");
                System.out.println("2. List All Books");
                System.out.println("3. Borrow a Book");
                System.out.println("4. Return a Book");
                System.out.println("5. Search a Book");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                while (!sc.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    sc.next();
                }

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:

                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();

                        library.addBook(new Book(title, author));
                        break;

                    case 2:
                        library.listBooks();
                        break;

                    case 3:

                        System.out.print("Enter Book Title to Borrow: ");
                        title = sc.nextLine();

                        library.borrowBook(title);
                        break;

                    case 4:

                        System.out.print("Enter Book Title to Return: ");
                        title = sc.nextLine();

                        library.returnBook(title);
                        break;

                    case 5:

                        System.out.print("Enter Book Title to Search: ");
                        title = sc.nextLine();

                        library.searchBook(title);
                        break;

                    case 0:
                        System.out.println("Thank you for using the Library Information System!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 0);
        }
    }
}