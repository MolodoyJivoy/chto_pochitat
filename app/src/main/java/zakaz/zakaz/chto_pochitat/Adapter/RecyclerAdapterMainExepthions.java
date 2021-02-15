package zakaz.zakaz.chto_pochitat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zakaz.zakaz.chto_pochitat.R;

public class RecyclerAdapterMainExepthions extends RecyclerView.Adapter<RecyclerAdapterMainExepthions.ViewHolderMain>{

    private Context context;
    private List<ExcerptionItem> modelList;

    public RecyclerAdapterMainExepthions(Context context, List<ExcerptionItem> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_excerption, parent, false);
        return new RecyclerAdapterMainExepthions.ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain holder, int position) {
        holder.NameExcerption.setText(modelList.get(position).getNameExcerption());
        holder.NameAuthor.setText(modelList.get(position).getNameAuthor());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder{

        TextView NameAuthor, NameExcerption;

        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            NameAuthor = itemView.findViewById(R.id.NameAuthorExcerption);
            NameExcerption = itemView.findViewById(R.id.NameExcerption);
        }
    }
}
