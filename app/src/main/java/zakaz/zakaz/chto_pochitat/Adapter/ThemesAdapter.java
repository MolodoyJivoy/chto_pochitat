package zakaz.zakaz.chto_pochitat.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zakaz.zakaz.chto_pochitat.R;

public class ThemesAdapter extends BaseAdapter {

    private Context context;
    private List<ThemesItem> themesItemList;

    public ThemesAdapter(Context context, List<ThemesItem> themesItemList) {
        this.context = context;
        this.themesItemList = themesItemList;
    }

    @Override
    public int getCount() {
        return themesItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return themesItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = View.inflate(context, R.layout.item_themes, null);
        TextView name = view1.findViewById(R.id.NameThemes);
        name.setText(themesItemList.get(position).getNameThemes());
        return view1;
    }
}
