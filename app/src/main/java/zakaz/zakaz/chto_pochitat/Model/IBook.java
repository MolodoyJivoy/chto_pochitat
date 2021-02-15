package zakaz.zakaz.chto_pochitat.Model;

import java.util.List;

public interface IBook {
    String getBook();
    String getAuthor();
    String getExcerption();
    String getDescription();
    List<String> getGenre();
    Float getRating();
}
