import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private String title; // book title
    private String author; // book author
    private String isbn; // book ISBN
    private boolean isAvailable; // availability status

    // constructor to initialize book details
    public Book(String title, String author, String isbn) {
        this.title = title; // set title
        this.author = author; // set author
        this.isbn = isbn; // set ISBN
        this.isAvailable = true; // set availability to true by default
    }

    // getter for title
    public String getTitle() { 
        return title; 
    }

    // getter for author
    public String getAuthor() { 
        return author; 
    }

    // getter for ISBN
    public String getIsbn() { 
        return isbn; 
    }

    // getter for availability
    public boolean isAvailable() { 
        return isAvailable; 
    }

    // setter for availability
    public void setAvailable(boolean available) {
        isAvailable = available; // update availability
    }

    // tostring method
    public String toString() {
        String availability = "No"; // default to "No"
        if (isAvailable) { // check if the book is available
            availability = "Yes"; // set to "Yes" if available
        }
    return "Title: " + title + " | Author: " + author + " | ISBN: " + isbn + " | Available: " + availability;
    }
}

// Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>(); // list to store books

    // method to add a book to the library
    public boolean addBook(Book book) {
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getIsbn().equals(book.getIsbn())) { // check for duplicate ISBN
                return false; // return false if duplicate found
            }
        }
        books.add(book); // add book to the list
        return true; // return true if book added successfully
    }

    // method to remove a book by ISBN
    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getIsbn().equals(isbn)) { // check if ISBN matches
                books.remove(i); // remove book from the list
                return true; // return true if book removed
            }
        }
        return false; // return false if book not found
    }

    // method to display all books in the library
    public void displayAllBooks() {
        if (books.isEmpty()) { // check if the list is empty
            System.out.println("No books in the library."); // print message if no books
            return; // exit method
        }
        for (int i = 0; i < books.size(); i++) { // loop through books
            System.out.println(books.get(i)); // print each book
        }
    }

    // method to search for books by title
    public void searchByTitle(String title) {
        boolean found = false; // flag to track if book is found
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getTitle().equalsIgnoreCase(title)) { // check if title matches
                System.out.println(books.get(i)); // print book details
                found = true; // set flag to true
            }
        }
        if (!found) { // if no book found
            System.out.println("No books found with that title."); // print message
        }
    }

    // method to search for books by author
    public void searchByAuthor(String author) {
        boolean found = false; // flag to track if book is found
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getAuthor().equalsIgnoreCase(author)) { // check if author matches
                System.out.println(books.get(i)); // print book details
                found = true; // set flag to true
            }
        }
        if (!found) { // if no book found
            System.out.println("No books found by that author."); // print message
        }
    }

    // method to check out a book by ISBN
    public boolean checkOutBook(String isbn) {
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getIsbn().equals(isbn)) { // check if ISBN matches
                if (!books.get(i).isAvailable()) { // check if book is already checked out
                    return false; // return false if not available
                }
                books.get(i).setAvailable(false); // set availability to false
                return true; // return true if book checked out
            }
        }
        return false; // return false if book not found
    }

    // method to return a book by ISBN
    public boolean returnBook(String isbn) {
        for (int i = 0; i < books.size(); i++) { // loop through books
            if (books.get(i).getIsbn().equals(isbn)) { // check if ISBN matches
                if (books.get(i).isAvailable()) { // check if book is already available
                    return false; // return false if already available
                }
                books.get(i).setAvailable(true); // set availability to true
                return true; // return true if book returned
            }
        }
        return false; // return false if book not found
    }
}

// Main class with menu
// Main class with menu
public class Main {
    public static void main(String[] args) {
        Library library = new Library(); // create library object
        Scanner sc = new Scanner(System.in); // create scanner for input
        int choice = 0; // variable to store user choice, initialized to 0

        // loop until the user chooses to exit
        while (choice != 8) {
            // display menu
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            // validate input
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: "); // prompt for valid input
                sc.next(); // clear invalid input
            }

            choice = sc.nextInt(); // read user choice
            sc.nextLine(); // clear newline

            // handle user choice
            if (choice == 1) { // add book
                System.out.print("Enter Title: "); // prompt for title
                String title = sc.nextLine(); // read title
                System.out.print("Enter Author: "); // prompt for author
                String author = sc.nextLine(); // read author
                System.out.print("Enter ISBN: "); // prompt for ISBN
                String isbn = sc.nextLine(); // read ISBN
                boolean added = library.addBook(new Book(title, author, isbn)); // add book
                if (added) {
                    System.out.println("Book added."); // print success message
                } else {
                    System.out.println("Duplicate ISBN. Book not added."); // print error message
                }
            } else if (choice == 2) { // remove book
                System.out.print("Enter ISBN to remove: "); // prompt for ISBN
                String isbn = sc.nextLine(); // read ISBN
                boolean removed = library.removeBook(isbn); // remove book
                if (removed) {
                    System.out.println("Book removed."); // print success message
                } else {
                    System.out.println("Book not found."); // print error message
                }
            } else if (choice == 3) { // display all books
                library.displayAllBooks(); // display all books
            } else if (choice == 4) { // search by title
                System.out.print("Enter title to search: "); // prompt for title
                String title = sc.nextLine(); // read title
                library.searchByTitle(title); // search by title
            } else if (choice == 5) { // search by author
                System.out.print("Enter author to search: "); // prompt for author
                String author = sc.nextLine(); // read author
                library.searchByAuthor(author); // search by author
            } else if (choice == 6) { // check out book
                System.out.print("Enter ISBN to check out: "); // prompt for ISBN
                String isbn = sc.nextLine(); // read ISBN
                boolean checkedOut = library.checkOutBook(isbn); // check out book
                if (checkedOut) {
                    System.out.println("Book checked out."); // print success message
                } else {
                    System.out.println("Book not available or not found."); // print error message
                }
            } else if (choice == 7) { // return book
                System.out.print("Enter ISBN to return: "); // prompt for ISBN
                String isbn = sc.nextLine(); // read ISBN
                boolean returned = library.returnBook(isbn); // return book
                if (returned) {
                    System.out.println("Book returned."); // print success message
                } else {
                    System.out.println("Book not found or not checked out."); // print error message
                }
            } else if (choice == 8) { // exit
                System.out.println("Exiting..."); // print exit message
            } else { // invalid option
                System.out.println("Invalid option."); // print error message
            }
        }

        sc.close(); // close scanner
    }
}