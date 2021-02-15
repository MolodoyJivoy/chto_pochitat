package zakaz.zakaz.chto_pochitat.Presenter;

import java.util.List;

import zakaz.zakaz.chto_pochitat.Data.IDataClass;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.View.IFavorites;
import zakaz.zakaz.chto_pochitat.View.IMainView;

public class ViewBookPresenter implements IViewBookPresenter {

    IMainView mainView;
    IDataClass iDataClass;

    public ViewBookPresenter(IMainView IMainView, IDataClass iDataClass) {
        this.mainView = IMainView;
        this.iDataClass = iDataClass;
    }

    @Override
    public void onBook() {
        iDataClass.getItemData(mainView);
        mainView.onStartProgress();
    }
}
