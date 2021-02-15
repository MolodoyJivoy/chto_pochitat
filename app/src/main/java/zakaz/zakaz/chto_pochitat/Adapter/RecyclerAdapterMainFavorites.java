package zakaz.zakaz.chto_pochitat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.R;

public class RecyclerAdapterMainFavorites extends RecyclerView.Adapter<RecyclerAdapterMainFavorites.ViewHolderMain> {

    private Context context;
    private List<FavoritesItem> modelList;

    public RecyclerAdapterMainFavorites(Context context, List<FavoritesItem> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_favorites, parent, false);
        return new RecyclerAdapterMainFavorites.ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain holder, int position) {
        holder.NameBookFavorites.setText(modelList.get(position).getNameBook());
        holder.NameAuthorFavorites.setText(modelList.get(position).getNameAuthor());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder{

        TextView NameBookFavorites, NameAuthorFavorites;

        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            NameAuthorFavorites = itemView.findViewById(R.id.NameAuthorFavorites);
            NameBookFavorites = itemView.findViewById(R.id.NameBookFavorites);
        }
    }
}
