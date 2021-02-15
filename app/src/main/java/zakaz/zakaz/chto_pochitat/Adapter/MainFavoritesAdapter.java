package zakaz.zakaz.chto_pochitat.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zakaz.zakaz.chto_pochitat.R;

public class MainFavoritesAdapter extends BaseAdapter {

    private Context context;
    private List<FavoritesItem> favoritesItemList;

    public MainFavoritesAdapter(Context context, List<FavoritesItem> favoritesItemList) {
        this.context = context;
        this.favoritesItemList = favoritesItemList;
    }

    @Override
    public int getCount() {
        return favoritesItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return favoritesItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = View.inflate(context, R.layout.item_favorites, null);
        TextView nameBook = view1.findViewById(R.id.NameBookFavorites);
        TextView nameAuthor = view1.findViewById(R.id.NameAuthorFavorites);
        nameBook.setText(favoritesItemList.get(position).getNameBook());
        nameAuthor.setText(favoritesItemList.get(position).getNameAuthor());
        return view1;
    }
}
