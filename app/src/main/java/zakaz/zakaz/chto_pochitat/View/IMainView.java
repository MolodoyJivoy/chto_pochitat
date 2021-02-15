package zakaz.zakaz.chto_pochitat.View;

import java.util.List;

import zakaz.zakaz.chto_pochitat.Model.Book;

public interface IMainView {
    void onBookResult(List<Book> book);
    void onStartProgress();
    void onStopProgress();
}
