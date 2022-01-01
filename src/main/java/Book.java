//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Book {
    long isbn;
    String title;
    int year;
    String[] authors;
    String city;
    String publisher;
    int edition;
    public static HashMap<Long, Book> Books = new HashMap();

    public Book(long isbn, String title, String[] authors, int year, String city, String publisher, int edition) {
        this.title = title;
        this.year = year;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.city = city;
        this.edition = edition;
        Books.put(this.isbn, this);
    }

    public long getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public static void deleteBook(Book book) {
        long i = (long)((HashMap)Bookcopy.bookcopies.get(book.getIsbn())).size();
        long b = 0L;

        for(long a = 0L; a < i; ++a) {
            if (!((Bookcopy)((HashMap)Bookcopy.bookcopies.get(book.getIsbn())).get(i)).isLent()) {
                ++b;
            }
        }

        if (b == i) {
            Books.remove(book.getIsbn());
            Bookcopy.bookcopies.remove(book.getIsbn());
        }

    }

    public static void outputAllBooks() {
        Iterator var0 = Books.entrySet().iterator();

        while(var0.hasNext()) {
            Entry<Long, Book> book = (Entry)var0.next();
            Book book1 = (Book)book.getValue();
            System.out.println("Title: " + book1.getTitle() + ", Authors: " + Arrays.toString(book1.authors) + ", Year: " + book1.year + ", Isbn: " + book1.isbn);
            System.out.println();
        }

    }

    public static void AddBook(String csvFile) {
        BufferedReader br = null;
        String line = "";
        int linenumber = 0;
        String cvsSplitBy = ";";
        String authorss = ",";

        try {
            for(br = new BufferedReader(new FileReader(csvFile)); (line = br.readLine()) != null; ++linenumber) {
                if (linenumber > 0) {
                    String[] part = line.split(cvsSplitBy);
                    String[] authors = part[2].split(authorss);
                    new Book(Long.parseLong(part[0]), part[1], authors, Integer.parseInt(part[3]), part[4], part[5], Integer.parseInt(part[6]));
                }
            }
        } catch (FileNotFoundException var18) {
            var18.printStackTrace();
        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }

        }

    }
}
