package zakaz.zakaz.chto_pochitat.Adapter;

public class FavoritesItem {
    private String NameBook;
    private String NameAuthor;

    public FavoritesItem(String nameBook, String nameAuthor) {
        NameBook = nameBook;
        NameAuthor = nameAuthor;
    }

    public String getNameBook() {
        return NameBook;
    }

    public String getNameAuthor() {
        return NameAuthor;
    }
}
