import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler



        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.TreeMap;
        import java.util.Map.Entry;

public class Bookcopy {
    long id;
    long bookIsbn;
    String shelfLocation;
    Date addedToLibrary;
    boolean lent;
    Date lentDate;
    public static HashMap<Long, HashMap<Long, Bookcopy>> bookcopies = new HashMap();

    public Bookcopy(long id, long bookIsbn, String shelfLocation, Date addedToLibrary, boolean lent, Date lentDate) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.shelfLocation = shelfLocation;
        this.lentDate = lentDate;
        this.lent = lent;
        this.addedToLibrary = addedToLibrary;
        if (bookcopies.get(bookIsbn) == null) {
            HashMap<Long, Bookcopy> specificBook = new HashMap();
            specificBook.put(this.id, this);
            bookcopies.put(this.bookIsbn, specificBook);
        } else {
            ((HashMap)bookcopies.get(this.bookIsbn)).put(this.id, this);
        }

    }

    public long getId() {
        return this.id;
    }

    public boolean isLent() {
        return this.lent;
    }

    public static void deleteBookcopy(Bookcopy bookcopy) {
        if (!bookcopy.lent) {
            ((HashMap)bookcopies.get(bookcopy.bookIsbn)).remove(bookcopy.getId());
        } else {
            System.out.println("This book copy is already lent and cannot be deleted!");
        }

    }

    public static void outputAllLentBookCopies() {
        Iterator var0 = bookcopies.entrySet().iterator();

        while(var0.hasNext()) {
            Map.Entry<Long, HashMap<Long, Bookcopy>> books = (Map.Entry)var0.next();
            HashMap<Long, Bookcopy> bookcopiesMap = (HashMap)books.getValue();
            Long number = (Long)books.getKey();
            String title = ((Book)Book.Books.get(number)).title;
            Long Isbn = ((Book)Book.Books.get(number)).isbn;
            String[] authors = ((Book)Book.Books.get(number)).authors;
            Iterator var7 = bookcopiesMap.entrySet().iterator();

            while(var7.hasNext()) {
                Map.Entry<Long, Bookcopy> bookcopy = (Map.Entry)var7.next();
                Bookcopy bookcopy1 = (Bookcopy)bookcopy.getValue();
                if (bookcopy1.isLent()) {
                    System.out.println("Title: " + title + ", Authors: " + Arrays.toString(authors) + ", Isbn: " + Isbn + "Id: " + bookcopy1.id + ", Shelf Number: " + bookcopy1.shelfLocation + "Lent: " + bookcopy1.isLent() + ", Added to library: " + bookcopy1.addedToLibrary);
                    System.out.println();
                }
            }
        }

    }

    public static void outputAllNotLentBookCopies() {
        Iterator var0 = bookcopies.entrySet().iterator();

        while(var0.hasNext()) {
            Map.Entry<Long, HashMap<Long, Bookcopy>> books = (Map.Entry)var0.next();
            HashMap<Long, Bookcopy> bookcopiesMap = (HashMap)books.getValue();
            Long number = (Long)books.getKey();
            String title = ((Book)Book.Books.get(number)).title;
            Long Isbn = ((Book)Book.Books.get(number)).isbn;
            String[] authors = ((Book)Book.Books.get(number)).authors;
            Iterator var7 = bookcopiesMap.entrySet().iterator();

            while(var7.hasNext()) {
                Map.Entry<Long, Bookcopy> bookcopy = (Map.Entry)var7.next();
                Bookcopy bookcopy1 = (Bookcopy)bookcopy.getValue();
                if (!bookcopy1.isLent()) {
                    System.out.println("Title: " + title + ", Authors: " + Arrays.toString(authors) + ", Isbn: " + Isbn + "Id: " + bookcopy1.id + ", Shelf Number: " + bookcopy1.shelfLocation + "Lent: " + bookcopy1.isLent() + ", Added to library: " + bookcopy1.addedToLibrary);
                    System.out.println();
                }
            }
        }

    }

    public static boolean searchForABookCopy(String searchedValue) {
        boolean isEmpty = true;
        Iterator var2 = bookcopies.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Long, HashMap<Long, Bookcopy>> books = (Map.Entry)var2.next();
            HashMap<Long, Bookcopy> bookcopiesMap = (HashMap)books.getValue();
            Long number = (Long)books.getKey();
            String title = ((Book)Book.Books.get(number)).title;
            String[] authors = ((Book)Book.Books.get(number)).authors;
            Long isbn = ((Book)Book.Books.get(number)).isbn;
            Iterator var9 = bookcopiesMap.entrySet().iterator();

            while(var9.hasNext()) {
                Map.Entry<Long, Bookcopy> bookcopy = (Map.Entry)var9.next();
                Bookcopy bookcopy1 = (Bookcopy)bookcopy.getValue();
                if (title.contains(searchedValue) || isbn.toString().equals(searchedValue)) {
                    System.out.println("Title: " + title + ", Authors: " + Arrays.toString(authors) + ", Isbn: " + bookcopy1.bookIsbn);
                    System.out.println("Id: " + bookcopy1.id + ", Shelf Number: " + bookcopy1.shelfLocation + "Is it lent:" + bookcopy1.isLent() + ", Added to library: " + bookcopy1.addedToLibrary);
                    System.out.println();
                    isEmpty = false;
                }

                String[] var12 = authors;
                int var13 = authors.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    String author = var12[var14];
                    if (author.contains(searchedValue)) {
                        System.out.println("Title: " + title + ", Authors: " + Arrays.toString(authors) + ", Isbn: " + bookcopy1.bookIsbn + ", Id: " + bookcopy1.id + ", Shelf Number: " + bookcopy1.shelfLocation + "Is it lent:" + bookcopy1.isLent() + ", Added to library: " + bookcopy1.addedToLibrary);
                        System.out.println();
                        isEmpty = false;
                    }
                }
            }
        }

        if (isEmpty) {
            System.out.println("No book copies found!");
            return false;
        } else {
            return true;
        }
    }

    public static void ShowAllBookcopiesByPublisher() {
        TreeMap<String, Long> publishers = new TreeMap();
        ArrayList isbn = new ArrayList();
        Iterator var2 = bookcopies.entrySet().iterator();

        Map.Entry books;
        while(var2.hasNext()) {
            books = (Map.Entry)var2.next();
            HashMap<Long, Bookcopy> bookcopiesMap = (HashMap)books.getValue();
            Iterator var5 = bookcopiesMap.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<Long, Bookcopy> bookcopy = (Map.Entry)var5.next();
                Bookcopy bookcopy1 = (Bookcopy)bookcopy.getValue();
                isbn.add(bookcopy1.bookIsbn);
            }
        }

        var2 = bookcopies.entrySet().iterator();

        while(var2.hasNext()) {
            books = (Map.Entry)var2.next();
            Long key = (Long)books.getKey();
            Long map = (long)((HashMap)books.getValue()).size();
            if (publishers.containsKey(((Book)Book.Books.get(key)).publisher)) {
                publishers.put(((Book)Book.Books.get(key)).publisher, (Long)publishers.get(((Book)Book.Books.get(key)).publisher) + map);
            } else {
                publishers.put(((Book)Book.Books.get(key)).publisher, map);
            }
        }

        double size = (double)isbn.size();
        Iterator var10 = publishers.entrySet().iterator();

        while(var10.hasNext()) {
            Map.Entry<String, Long> output = (Map.Entry)var10.next();
            double m;
            if ((Long)output.getValue() > 1L) {
                m = (double)((Long)output.getValue() * 100L) / size;
                System.out.println(" " + (String)output.getKey() + " : " + output.getValue() + "  Buchkopien   (" + m + "%)");
            } else {
                m = (double)((Long)output.getValue() * 100L) / size;
                System.out.println(" " + (String)output.getKey() + " : " + output.getValue() + " Buchkopie  (" + m + "%)");
            }
        }

    }

    public static void AddBookcopy(String csvFile) throws ParseException {
        BufferedReader br = null;
        String line = "";
        int linenumber = 0;
        String cvsSplitBy = ";";

        try {
            for(br = new BufferedReader(new FileReader(csvFile)); (line = br.readLine()) != null; ++linenumber) {
                if (linenumber > 0) {
                    String[] part = line.split(cvsSplitBy);
                    Date date5 = (new SimpleDateFormat("yyyy.MM.dd")).parse(part[5]);
                    Date date3 = (new SimpleDateFormat("yyyy.MM.dd")).parse(part[5]);
                    new Bookcopy(Long.parseLong(part[0]), Long.parseLong(part[1]), part[2], date3, Boolean.parseBoolean(part[4]), date5);
                    if (Long.parseLong(part[6]) != 0L) {
                        Long customerID = Long.parseLong(part[6]);
                        if (Customer.customers.containsKey(customerID)) {
                            if (((Customer)Customer.customers.get(Long.parseLong(part[6]))).bookslent.containsKey(Long.parseLong(part[1]))) {
                                System.out.println("The customer has already lent a book with this isbn");
                            } else if (((Customer)Customer.customers.get(Long.parseLong(part[6]))).LentBooksNumber() < 5) {
                                ((Customer)Customer.customers.get(Long.parseLong(part[6]))).bookslent.put(Long.parseLong(part[1]), Long.parseLong(part[0]));
                            } else {
                                System.out.println("The customer has borrowed five books already");
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException var19) {
            var19.printStackTrace();
        } catch (IOException var20) {
            var20.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }

    }
}
