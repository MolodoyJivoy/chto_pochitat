package zakaz.zakaz.chto_pochitat.Model;

import java.util.List;

public class Book implements IBook {
    private String book, author, excerption, description;
    private List<String> genre;
    private float rating;

    Book(){

    }

    public Book(String nameBook, String author, String excerption, List<String> genre, float rating, String description) {
        this.book = nameBook;
        this.author = author;
        this.excerption = excerption;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
    }

    @Override
    public String getBook() {
        return book;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getExcerption() {
        return excerption;
    }

    @Override
    public List<String> getGenre() {
        return genre;
    }

    @Override
    public Float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
