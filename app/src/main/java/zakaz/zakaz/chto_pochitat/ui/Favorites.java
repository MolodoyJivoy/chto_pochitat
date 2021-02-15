package zakaz.zakaz.chto_pochitat.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import zakaz.zakaz.chto_pochitat.Adapter.ExcerptionFavoritesAdapter;
import zakaz.zakaz.chto_pochitat.Adapter.ExcerptionItem;
import zakaz.zakaz.chto_pochitat.Adapter.FavoritesItem;
import zakaz.zakaz.chto_pochitat.Adapter.MainFavoritesAdapter;
import zakaz.zakaz.chto_pochitat.Adapter.RecyclerAdapterMainExepthions;
import zakaz.zakaz.chto_pochitat.Adapter.RecyclerAdapterMainFavorites;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.R;
import zakaz.zakaz.chto_pochitat.Util.Change;
import zakaz.zakaz.chto_pochitat.View.IFavorites;

public class Favorites extends Fragment implements IFavorites {

    private ListView MainFavorite, ExcerptionFavorite;
    private List<FavoritesItem> maintList;
    private List<ExcerptionItem> excerptionList;

    private TextView chekOnNull, Books, Exepthion;

    List<Book> list;

    MainFavoritesAdapter mainFavoritesAdapter;
    ExcerptionFavoritesAdapter excerptionFavoritesAdapter;

    private RecyclerView recyclerViewMain, recyclerViewExcerption;
    private RecyclerAdapterMainFavorites recyclerAdapterMainFavorites;
    private RecyclerAdapterMainExepthions recyclerAdapterMainExepthions;

    private static int counter = 0;

    List<String> keys;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.activity_favorites, container, false);
        MainFavorite = viewGroup.findViewById(R.id.MainFavorite);
        ExcerptionFavorite = viewGroup.findViewById(R.id.ExcerptionFavorite);
        chekOnNull = viewGroup.findViewById(R.id.checkFavorites);
        Books = viewGroup.findViewById(R.id.m_book);;
        Exepthion = viewGroup.findViewById(R.id.m_exepthion);
        Paper.init(getContext());
        maintList = new ArrayList<>();
        excerptionList = new ArrayList<>();

        getData();

        mainFavoritesAdapter = new MainFavoritesAdapter(getContext(), maintList);
        excerptionFavoritesAdapter = new ExcerptionFavoritesAdapter(getContext(), excerptionList);
        ExcerptionFavorite.setAdapter(excerptionFavoritesAdapter);
        MainFavorite.setAdapter(mainFavoritesAdapter);


        recyclerViewMain = viewGroup.findViewById(R.id.MainFavoriteRecycler);
        recyclerAdapterMainFavorites = new RecyclerAdapterMainFavorites(getContext(), maintList);
        recyclerViewMain.setAdapter(recyclerAdapterMainFavorites);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewExcerption = viewGroup.findViewById(R.id.ExcerptionFavoriteRecycler);
        recyclerAdapterMainExepthions = new RecyclerAdapterMainExepthions(getContext(), excerptionList);
        recyclerViewExcerption.setAdapter(recyclerAdapterMainExepthions);
        recyclerViewExcerption.setLayoutManager(new LinearLayoutManager(getContext()));

        //Удаление
//        MainFavorite.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Paper.book("books").delete(keys.get(i));
//                getData();
//                mainFavoritesAdapter.notifyDataSetChanged();
//                return false;
//            }
//        });

        return viewGroup;
    }

    private void checkOnNull() {
        if (maintList.isEmpty()){
            chekOnNull.setVisibility(View.VISIBLE);
            Books.setVisibility(View.INVISIBLE);
            Exepthion.setVisibility(View.INVISIBLE);
        }else {
            chekOnNull.setVisibility(View.INVISIBLE);
            Books.setVisibility(View.VISIBLE);
            Exepthion.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        if (Change.isChanges()){
            getData();
            recyclerAdapterMainExepthions.notifyDataSetChanged();
            recyclerAdapterMainFavorites.notifyDataSetChanged();
            excerptionFavoritesAdapter.notifyDataSetChanged();
            mainFavoritesAdapter.notifyDataSetChanged();
            Change.setChanges(false);
        }
    }

    void getData(){
        maintList.clear();
        excerptionList.clear();
        keys = Paper.book("books").getAllKeys();
        if (!keys.isEmpty()){
            for (int i = 0 ; i < keys.size(); i++){
                Book list = Paper.book("books").read(keys.get(i));
                maintList.add(new FavoritesItem(list.getBook(), list.getAuthor()));
                excerptionList.add(new ExcerptionItem(list.getAuthor(), list.getExcerption()));
            }
        }
        checkOnNull();
    }
}
