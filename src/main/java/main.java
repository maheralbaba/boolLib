import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.*;

public class main {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = format.format(new Date());
    static Date date;
    static boolean couldBorrow;
    void before() {
        try {
            date = format.parse("2018-05-11");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        //String[] authorsList = {"first","second"};
        //Book mathe1 = new Book(123456789,"Mathematik1", authorsList, 2019, "Stuttgart" , "Uni Stuttgart", 1);
        //Bookcopy mathe1copy= new Bookcopy(1, 123456789, "Shelf1", date, false, date);


        showMenu();
        //book();
        //customer();
    }

    // this method shows the menu with its options and allows the user to select an
    // option by writing the number of the option
    public static void showMenu()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("Welcome to this library sofrware!");
        System.out.println();
        System.out.println();
        System.out.println("Please select one of the following options from the menu.");
        System.out.println("----------------------------------------------------------");
        System.out.println("1. Import a file with customers.");
        System.out.println("2. Import a file with books.");
        System.out.println("3. Import a file with book copies.");
        System.out.println("4. Show all customers.");
        System.out.println("5. Show all books.");
        System.out.println("6. Show all lent book copies.");
        System.out.println("7. Show all not lent book copies.");
        System.out.println("8. Show all book copies per publisher. ");
        System.out.println("9. Search for a book.");
        System.out.println("10. Delete a customer.");
        System.out.println("11. Delete a book.");
        System.out.println("12. Delete a book copy.");
        System.out.println("13. Borrow a book copy.");
        System.out.println("14. Return a book copy.");
        System.out.println("15. Show which books a customer has lent.");
        System.out.println();
        System.out.println("Type here the number of the option you want to select or q to exit: ");
        String option = scanner.nextLine();
        try {


            if (option.equals("q")) {
                System.exit(0);
            } else if (Integer.parseInt(option) == 1) {
                System.out.println("You selected the option to import a file with customers!");
                System.out.print("Please specify the path of the file: ");
                String path = scanner.nextLine();
                System.out.println("Importing " + path + " ...");
                Customer.AddCustomer(path);
                System.out.println("The file with customers was successfully imported!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 2) {
                System.out.println("You selected the option to import a file with books!");
                System.out.print("Please specify the path of the file: ");
                String path = scanner.nextLine();
                System.out.println("Uploading " + path + " ...");
                Book.AddBook(path);
                System.out.println("The books were successfully uploaded!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 3) {
                System.out.println("You selected the option to import a file with book copies!");
                System.out.print("Please specify the path of the file: ");
                String path = scanner.nextLine();
                System.out.println("Importing " + path + " ...");
                Bookcopy.AddBookcopy(path);
                System.out.println("The book copies were successfully imported!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 4) {
                System.out.println("You selected the option to show all customers!");
                System.out.println("Customers: ");
                Customer.outputAllCustomers();
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 5) {
                System.out.println("You selected the option to show all books.");
                System.out.println("Books: ");
                Book.outputAllBooks();
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 6) {
                System.out.println("You selected the option to show all lent book copies.");
                System.out.println("Book copies: ");
                Bookcopy.outputAllLentBookCopies();
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 7) {
                System.out.println("You selected the option to show all not yet lent book copies.");
                System.out.println("Book copies: ");
                Bookcopy.outputAllNotLentBookCopies();
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 8) {
                System.out.println("You selected the option to show all books per publisher.");
                Bookcopy.ShowAllBookcopiesByPublisher();
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 9) {
                System.out.println("You selected the option to search for a book!");
                System.out.print("Write the name/author/isbn of the book you want to search for: ");
                String searchedValue = scanner.nextLine();
                Bookcopy.searchForABookCopy(searchedValue);
                showMenu();

            } else if (Integer.parseInt(option) == 10) {
                System.out.println("You selected the option to delete a customer!");
                System.out.print("Write the ID of the customer you want to delete: ");
                Long CustomerID = scanner.nextLong();

                System.out.println("Deleting the customer with ID: " + CustomerID + " ...");
                Customer.deleteCustomer(Customer.customers.get(CustomerID));
                System.out.println("The customer with ID: \"" + CustomerID + "\" was succesfully deleted!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 11) {
                System.out.println("You selected the option to delete a book!");
                System.out.print("Write the ISBN of the book you want to delete: ");
                String ISBN = scanner.nextLine();
                System.out.println("Deleting the book" + ISBN + " ...");
                Book.deleteBook(Book.Books.get(Long.parseLong(ISBN)));
                System.out.println("The book \"" + ISBN + "\" was succesfully deleted!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 12) {
                System.out.println("You selected the option to delete a book copy!");
                System.out.print("Write the isbn of the book copy you want to delete: ");
                String ISBN = scanner.nextLine();
                System.out.print("Write the ID of the book copy you want to delete: ");
                String boockcopyId = scanner.nextLine();
                System.out.println("Deleting the book copy " + ISBN + " ...");

                Bookcopy.deleteBookcopy(Bookcopy.bookcopies.get(Long.parseLong(ISBN)).get(Long.parseLong(boockcopyId)));
                System.out.println("The book copy \"" + ISBN + "\" was successfully deleted!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 13) {
                System.out.println("You selected the option to borrow a book copy!");
                System.out.print("Write the ISBN of the book copy you want to borrow: ");
                String bookISBN = scanner.nextLine();
                System.out.print("Write the ID of the customer who wants to borrow the book copy: ");
                String customerID = scanner.nextLine();
                Customer aCustomer = Customer.customers.get(Long.parseLong(customerID));
                long isbn = Long.parseLong(bookISBN);
                HashMap<Long, Bookcopy> bookcopiesMap = Bookcopy.bookcopies.get(isbn);
                for (Map.Entry<Long, Bookcopy> bookcopy : bookcopiesMap.entrySet()) {
                    couldBorrow = false;
                    if (aCustomer.lendBookcopy(bookcopy.getValue())) {
                        System.out.println("The book \"" + bookISBN + "\" was succesfully borrowed!");
                        System.out.println();
                        couldBorrow = true;
                        break;
                    }
                }
                if (!couldBorrow) {
                    System.out.println("The book \"" + bookISBN + "\" could not be borrowed!");
                    System.out.println();
                }
                //resetting for the next customer borrowing book
                couldBorrow = false;
                showMenu();

            } else if (Integer.parseInt(option) == 14) {
                System.out.println("You selected the option to return a book copy!");
                System.out.print("Write the id of the book copy you want to return: ");
                String bookId = scanner.nextLine();
                System.out.print("Write the ID of the customer who wants to borrow the book copy: ");
                String customerID = scanner.nextLine();
                Customer aCustomer = Customer.customers.get(Long.parseLong(customerID));
                Long id = Long.parseLong(bookId);
                for (Map.Entry<Long, HashMap<Long, Bookcopy>> books : Bookcopy.bookcopies.entrySet()) {
                    HashMap<Long, Bookcopy> bookcopiesMap = books.getValue();
                    Bookcopy bookcopy = bookcopiesMap.get(id);
                    if (bookcopy != null) {
                        aCustomer.returnBookcopy(bookcopy);
                        break;
                    }
                }
                System.out.println("The book copy \"" + bookId + "\" was succesfully returned!");
                System.out.println();
                showMenu();

            } else if (Integer.parseInt(option) == 15) {
                System.out.println("You selected the option to show all books a customer has lent!");
                System.out.print("Write the ID of the customer: ");
                Long customerID = scanner.nextLong();
                Customer.showAllLentBooksBy(Customer.customers.get(customerID));
                System.out.println("Books: ");
                System.out.println();
                showMenu();

            } else {
                System.err.println("There is no such option! "
                        + "You can only choose between the numbers of the options in the manu or choose q to exit.");
                showMenu();
            }
        }
        catch (Exception e){

        }
    }
















}
