package zakaz.zakaz.chto_pochitat.Adapter;

public class ExcerptionItem {
    private String NameAuthor;
    private String NameExcerption;

    public ExcerptionItem(String nameAuthor, String nameExcerption) {
        NameAuthor = nameAuthor;
        NameExcerption = nameExcerption;
    }

    public String getNameAuthor() {
        return NameAuthor;
    }

    public String getNameExcerption() {
        return NameExcerption;
    }
}
