package zakaz.zakaz.chto_pochitat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.hmomeni.progresscircula.ProgressCircula;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import io.paperdb.Paper;
import zakaz.zakaz.chto_pochitat.Data.Data;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.Presenter.IViewBookPresenter;
import zakaz.zakaz.chto_pochitat.Presenter.ViewBookPresenter;
import zakaz.zakaz.chto_pochitat.R;
import zakaz.zakaz.chto_pochitat.Util.Change;
import zakaz.zakaz.chto_pochitat.View.IMainView;
import zakaz.zakaz.chto_pochitat.View.ViewPagerMain;

/**
 * ViewPager главного меню
 * */
public class ViewPagerMainMenu  extends RecyclerView.Adapter<ViewPagerMainMenu.ViewHolder> {

    private Context context;
    ViewHolder holder;
    List<Book> book;
    ViewPagerMain viewPagerMain;
    int posSwipe = 0;

    public ViewPagerMainMenu(Context context, List<Book>book, ViewPagerMain viewPagerMain) {
        this.context = context;
        this.book = book;
        this.viewPagerMain = viewPagerMain;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.main_menu_black_theme, parent, false);
        return new ViewPagerMainMenu.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        this.holder = holder;
        Paper.init(context);

//        holder.scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (!holder.scrollView.canScrollVertically(1)) {
//                    Toast.makeText(context, "Низ", Toast.LENGTH_SHORT).show();
//                    viewPagerMain.startSwipe();
//                }
//                if (!holder.scrollView.canScrollVertically(-1)) {
//                    Toast.makeText(context, "Вверх", Toast.LENGTH_SHORT).show();
//                    viewPagerMain.stopSwipe();
//                }
//            }
//        });

        int size = book.size() - 1;
        final int pos = (int) (Math.random() * ++size);
        holder.Name.setText(book.get(pos).getBook());
        holder.Author.setText(book.get(pos).getAuthor());
        holder.Excerption.setText(book.get(pos).getExcerption());
        holder.Description.setText(book.get(pos).getDescription());
        holder.scaleRatingBar.setRating(book.get(pos).getRating());
        holder.RatingNumber.setText("Рейтинг: " + book.get(pos).getRating());
        holder.recyclerAdapterCircleItems = new RecyclerAdapterCircleItems(context, book, pos);
        holder.CircleItems.setAdapter(holder.recyclerAdapterCircleItems);
        List<String> keys = Paper.book("books").getAllKeys();
        for (String key : keys){
            if (key.equals(book.get(pos).getBook())){
                holder.likeButton.setLiked(true);
            }
        }
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Change.setChanges(true);
                Paper.book("books").write(book.get(pos).getBook(), book.get(pos));

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Change.setChanges(true);
                Paper.book("books").delete(book.get(pos).getBook());
            }
        });
        holder.Excerption.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Name, Author, Excerption, Description, RatingNumber;
        private ScaleRatingBar scaleRatingBar;
        private RecyclerView CircleItems;

        private LikeButton likeButton;

        private RecyclerAdapterCircleItems recyclerAdapterCircleItems;

        private ScrollView scrollView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scrollView = itemView.findViewById(R.id.ScrollView);
            Name = itemView.findViewById(R.id.NameBook);
            Author = itemView.findViewById(R.id.NameAuthor);
            Excerption = itemView.findViewById(R.id.Excerption);
            Description = itemView.findViewById(R.id.Description);
            RatingNumber = itemView.findViewById(R.id.RatingNumber);

            likeButton = itemView.findViewById(R.id.like_button);

            CircleItems = itemView.findViewById(R.id.ItemsCircle);

            scaleRatingBar = itemView.findViewById(R.id.simpleRatingBar);
        }
    }
}
