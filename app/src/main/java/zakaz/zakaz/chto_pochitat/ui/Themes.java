package zakaz.zakaz.chto_pochitat.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zakaz.zakaz.chto_pochitat.Adapter.ThemesAdapter;
import zakaz.zakaz.chto_pochitat.Adapter.ThemesItem;
import zakaz.zakaz.chto_pochitat.R;

public class Themes extends Fragment{

    private ListView listView;
    private ThemesAdapter themesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.activity_themes, container, false);
        listView = viewGroup.findViewById(R.id.listThemes);
        List<ThemesItem> t = new ArrayList<>();
        t.add(new ThemesItem("1"));
        t.add(new ThemesItem("2"));
        t.add(new ThemesItem("3"));
        t.add(new ThemesItem("4"));
        t.add(new ThemesItem("5"));
        themesAdapter = new ThemesAdapter(getContext(), t);
        listView.setAdapter(themesAdapter);
        return viewGroup;
    }
}
