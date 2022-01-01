//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompile



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Customer {
    private long id;
    String name;
    String firstName;
    String address;
    int zipCode;
    String city;
    boolean feesPaid;
    HashMap<Long, Long> bookslent = new HashMap();
    public static HashMap<Long, Customer> customers = new HashMap();

    public Customer(long id, String name, String firstName, String address, int zipCode, String city, boolean feesPaid) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.feesPaid = feesPaid;
        if (id != -1L) {
            customers.put(this.id, this);
        }

    }

    public static long getId(Customer customer) {
        return customer.id;
    }

    public String getName() {
        return this.name;
    }

    public static void deleteCustomer(Customer customer) {
        if (customer.feesPaid && customer.LentBooksNumber() == 0) {
            customers.remove(customer.id);
            customer = null;
        }

    }

    public int LentBooksNumber() {
        return this.bookslent.size();
    }

    public boolean lendBookcopy(Bookcopy book) {
        if (!book.lent && this.LentBooksNumber() < 5 && this.feesPaid && !this.bookslent.containsKey(book.bookIsbn)) {
            book.lent = true;
            book.lentDate = new Date();
            this.bookslent.put(book.bookIsbn, book.id);
            return true;
        } else {
            return false;
        }
    }

    public void returnBookcopy(Bookcopy book) {
        if (book.lent && this.LentBooksNumber() > 0) {
            this.bookslent.remove(book.bookIsbn);
            Date now = new Date();
            long differenceMS = now.getTime() - book.lentDate.getTime();
            long daysSince = TimeUnit.DAYS.convert(differenceMS, TimeUnit.MILLISECONDS);
            long overdue = daysSince - 21L;
            book.lentDate.setTime(book.lentDate.getTime() + TimeUnit.MILLISECONDS.convert(21L, TimeUnit.DAYS));
            if (daysSince >= 21L) {
                System.out.println("This bookcopy should have been returned until the " + book.lentDate + " and is overdue for " + overdue + " days");
            }

            book.lent = false;
            book.lentDate = null;
        }

    }

    public static void outputAllCustomers() {
        System.out.println("Customers:");
        System.out.println();
        Iterator var0 = customers.entrySet().iterator();

        while(var0.hasNext()) {
            Entry<Long, Customer> customer = (Entry)var0.next();
            Customer customer1 = (Customer)customer.getValue();
            System.out.println("ID: " + customer1.id + "Name: " + customer1.name + ", First Name: " + customer1.firstName + ", feesPaid: " + customer1.feesPaid + "Number of lent books: " + customer1.bookslent.size());
            System.out.println();
        }

    }

    public static void AddCustomer(String csvFile) {
        BufferedReader br = null;
        String line = "";
        int linenumber = 0;
        String cvsSplitBy = ";";

        try {
            for(br = new BufferedReader(new FileReader(csvFile)); (line = br.readLine()) != null; ++linenumber) {
                if (linenumber > 0) {
                    String[] part = line.split(cvsSplitBy);
                    new Customer(Long.parseLong(part[0]), part[1], part[2], part[3], Integer.parseInt(part[4]), part[5], Boolean.parseBoolean(part[6]));
                }
            }
        } catch (FileNotFoundException var16) {
            var16.printStackTrace();
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }

        }

    }

    public static void showAllLentBooksBy(Customer customer) {
        Iterator var1 = customer.bookslent.entrySet().iterator();

        while(var1.hasNext()) {
            Entry<Long, Long> lentBook = (Entry)var1.next();
            Long bookcopyID = (Long)lentBook.getValue();
            Long bookISBN = (Long)lentBook.getKey();
            Iterator var5 = Bookcopy.bookcopies.entrySet().iterator();

            while(var5.hasNext()) {
                Entry<Long, HashMap<Long, Bookcopy>> books = (Entry)var5.next();
                HashMap<Long, Bookcopy> bookcopiesMap = (HashMap)books.getValue();
                Long number = (Long)books.getKey();
                String title = ((Book)Book.Books.get(number)).title;
                String[] authors = ((Book)Book.Books.get(number)).authors;
                Iterator var11 = bookcopiesMap.entrySet().iterator();

                while(var11.hasNext()) {
                    Entry<Long, Bookcopy> bookcopy = (Entry)var11.next();
                    Bookcopy bookcopy1 = (Bookcopy)bookcopy.getValue();
                    if (bookcopy1.id == bookcopyID && bookcopy1.bookIsbn == bookISBN) {
                        System.out.println("Title: " + title + ", Authors: " + Arrays.toString(authors) + ", Isbn: " + bookcopy1.bookIsbn + ", Id: " + bookcopy1.id + ", Shelf Number: " + bookcopy1.shelfLocation + "Is it lent:" + bookcopy1.isLent() + ", Added to library: " + bookcopy1.addedToLibrary);
                        System.out.println();
                    }
                }
            }
        }

    }
}
