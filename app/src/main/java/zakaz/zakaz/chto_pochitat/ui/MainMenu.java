package zakaz.zakaz.chto_pochitat.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hmomeni.progresscircula.ProgressCircula;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;
import java.util.Random;

import io.paperdb.Paper;
import zakaz.zakaz.chto_pochitat.Adapter.RecyclerAdapterCircleItems;
import zakaz.zakaz.chto_pochitat.Adapter.ViewPagerMainMenu;
import zakaz.zakaz.chto_pochitat.Data.Data;
import zakaz.zakaz.chto_pochitat.MainActivity;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.Presenter.IViewBookPresenter;
import zakaz.zakaz.chto_pochitat.Presenter.ViewBookPresenter;
import zakaz.zakaz.chto_pochitat.R;
import zakaz.zakaz.chto_pochitat.Util.Change;
import zakaz.zakaz.chto_pochitat.View.IMainView;
import zakaz.zakaz.chto_pochitat.View.ViewPagerMain;

/**
 * Главный экран с книгами
 * */

public class MainMenu extends Fragment implements IMainView, ViewPagerMain {

//    private TextView Name, Author, Excerption, Description, RatingNumber;
//    private ScaleRatingBar scaleRatingBar;
//    private RecyclerView CircleItems;
//
//    private LikeButton likeButton;
//
//    private RecyclerAdapterCircleItems recyclerAdapterCircleItems;
//
    private ProgressCircula progressCircula;
//
    private ScrollView scrollView;
//    private ProgressCircula progressCircula;

    ViewPager2 mainPager;

    IViewBookPresenter viewBookPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.view_pager_main, container, false);
        mainPager = viewGroup.findViewById(R.id.view_pagerMainMenu);
        progressCircula = viewGroup.findViewById(R.id.progressBar);
        scrollView = viewGroup.findViewById(R.id.ScrollView);
        onStartProgress();
        viewBookPresenter = new ViewBookPresenter(this, new Data());
        viewBookPresenter.onBook();
        return viewGroup;
    }


    @Override
    public void onBookResult(final List<Book> book) {
        ViewPagerMainMenu viewPagerMainMenu = new ViewPagerMainMenu(getContext(), book, this);
        mainPager.setAdapter(viewPagerMainMenu);
        onStopProgress();
    }

    @Override
    public void onStartProgress() {
        progressCircula.startRotation();
    }

    @Override
    public void onStopProgress() {
        progressCircula.stopRotation();
        progressCircula.setVisibility(View.GONE);
    }

    @Override
    public void startSwipe() {
        mainPager.setUserInputEnabled(true);
    }

    @Override
    public void stopSwipe() {
        mainPager.setUserInputEnabled(false);
    }
}
