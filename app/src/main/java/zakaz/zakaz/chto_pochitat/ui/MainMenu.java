package zakaz.zakaz.chto_pochitat.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.hmomeni.progresscircula.ProgressCircula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zakaz.zakaz.chto_pochitat.Adapter.ViewPagerMainMenu;
import zakaz.zakaz.chto_pochitat.Data.Data;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.Presenter.IViewBookPresenter;
import zakaz.zakaz.chto_pochitat.Presenter.ViewBookPresenter;
import zakaz.zakaz.chto_pochitat.R;
import zakaz.zakaz.chto_pochitat.View.IMainView;
import zakaz.zakaz.chto_pochitat.View.ViewPagerMain;

/**
 * Главный экран с книгами
 * */

public class MainMenu extends Fragment implements IMainView, ViewPagerMain{
    private ProgressCircula progressCircula;
    private ScrollView scrollView;
    ViewPager2 mainPager;
    IViewBookPresenter viewBookPresenter;
    ViewGroup viewGroup;

    private BottomSheetDialog bottomSheetDialog;
    private View bottomView;

    HashMap<String, List<String>> genre = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.view_pager_main, container, false);
        bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetsDialogTheme);
        bottomView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheets_about, (LinearLayout)viewGroup.findViewById(R.id.bottomContainer));
        bottomSheetDialog.setContentView(bottomView);
        setGenreList();
        mainPager = viewGroup.findViewById(R.id.view_pagerMainMenu);
        mainPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        progressCircula = viewGroup.findViewById(R.id.progressBar);
        scrollView = viewGroup.findViewById(R.id.ScrollView);
        onStartProgress();
        viewBookPresenter = new ViewBookPresenter(this, new Data());
        viewBookPresenter.onBook();

        return viewGroup;
    }

    private void setGenreList() {
        List<String> listRoman = new ArrayList<>();
        listRoman.add("Бизнес-книги");
        listRoman.add("Классическая литература");
        listRoman.add("Короткие любовные романы");
        listRoman.add("Остросюжетные любовные романы");
        listRoman.add("Любовное фэнтези, любовно-фантастические романы");
        genre.put("Roman", listRoman);
        ChipGroup chipGroup = bottomView.findViewById(R.id.chipGroupRoman);

        for (int i = 0 ; i < listRoman.size(); i++){
            final Chip chip = new Chip(getContext());
            chip.setText(listRoman.get(i));
            chip.setClickable(true);
            chip.setTextStartPadding(26);
            chip.setTextEndPadding(26);
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.chip_bg_states);
            chip.setTextColor(Color.parseColor("#7985CB"));
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        chip.setTextColor(Color.parseColor("#616CAA"));
                        chip.setChipIconTintResource(R.color.chipIconNotSelect);
                        chip.setTextColor(Color.WHITE);
                        chip.setChipBackgroundColorResource(R.color.chip_bg_states);
                    }else {
                        chip.setChipBackgroundColorResource(R.color.chip_bg_states);
                        chip.setTextColor(Color.parseColor("#7985CB"));
                    }
                }
            });
            chipGroup.addView(chip);
        }

        chipGroup.setSingleSelection(true);
    }


    @Override
    public void onBookResult(final List<Book> book) {
        ViewPagerMainMenu viewPagerMainMenu = new ViewPagerMainMenu(getContext(), book, this);
        mainPager.setAdapter(viewPagerMainMenu);
        mainPager.setClipToPadding(false);
        mainPager.setClipChildren(false);
        mainPager.setOffscreenPageLimit(3);
        mainPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.90f + r * 0.10f);
            }
        });

        mainPager.setPageTransformer(compositePageTransformer);
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

    public void showBottomMenu(){
        Button sendButton = bottomView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
}
