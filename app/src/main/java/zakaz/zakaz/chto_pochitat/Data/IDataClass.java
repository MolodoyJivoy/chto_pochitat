package zakaz.zakaz.chto_pochitat.Data;

import java.util.List;

import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.View.IMainView;

public interface IDataClass {
    List<Book> getData();
    void getItemData(IMainView iMainView);
}
