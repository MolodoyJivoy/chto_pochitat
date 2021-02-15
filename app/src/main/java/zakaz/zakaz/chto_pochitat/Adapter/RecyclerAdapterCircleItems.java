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
public class RecyclerAdapterCircleItems extends RecyclerView.Adapter<RecyclerAdapterCircleItems.ViewHolderMain> {

    private Context context;
    private List<Book> modelList;
    private int pos;

    public RecyclerAdapterCircleItems(Context context, List<Book> modelList, int pos) {
        this.context = context;
        this.modelList = modelList;
        this.pos = pos;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cirlce_items, parent, false);
        return new RecyclerAdapterCircleItems.ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain holder, final int position) {
        holder.nameElem.setText(modelList.get(pos).getGenre().get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.get(pos).getGenre().size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder{

        TextView nameElem;

        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            nameElem = itemView.findViewById(R.id.NameElem);
        }
    }
}

