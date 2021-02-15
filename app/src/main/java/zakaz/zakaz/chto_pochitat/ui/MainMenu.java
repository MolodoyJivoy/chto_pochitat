package zakaz.zakaz.chto_pochitat.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import zakaz.zakaz.chto_pochitat.Data.Data;
import zakaz.zakaz.chto_pochitat.MainActivity;
import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.Presenter.IViewBookPresenter;
import zakaz.zakaz.chto_pochitat.Presenter.ViewBookPresenter;
import zakaz.zakaz.chto_pochitat.R;
import zakaz.zakaz.chto_pochitat.Util.Change;
import zakaz.zakaz.chto_pochitat.View.IMainView;

/**
 * Главный экран с книгами
 * */

public class MainMenu extends Fragment implements IMainView {

    private TextView Name, Author, Excerption, Description, RatingNumber;
    private ScaleRatingBar scaleRatingBar;
    private RecyclerView CircleItems;

    private LikeButton likeButton;

    private RecyclerAdapterCircleItems recyclerAdapterCircleItems;

    private ProgressCircula progressCircula;

    private ScrollView scrollView;
//    private ProgressCircula progressCircula;

    IViewBookPresenter viewBookPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.main_menu_black_theme, container, false);
        progressCircula = viewGroup.findViewById(R.id.progressBar);
        scrollView = viewGroup.findViewById(R.id.ScrollView);
        Paper.init(getContext());
        onStartProgress();
        Name = viewGroup.findViewById(R.id.NameBook);
        Author = viewGroup.findViewById(R.id.NameAuthor);
        Excerption = viewGroup.findViewById(R.id.Excerption);
        Description = viewGroup.findViewById(R.id.Description);
        RatingNumber = viewGroup.findViewById(R.id.RatingNumber);

        likeButton = viewGroup.findViewById(R.id.like_button);

        CircleItems = viewGroup.findViewById(R.id.ItemsCircle);

        scaleRatingBar = viewGroup.findViewById(R.id.simpleRatingBar);

        viewBookPresenter = new ViewBookPresenter(this, new Data());
        viewBookPresenter.onBook();

        return viewGroup;
    }

    @Override
    public void onBookResult(final List<Book> book) {
        int size = book.size() - 1;
        final int pos = (int) (Math.random() * ++size);
        Name.setText(book.get(pos).getBook());
        Author.setText(book.get(pos).getAuthor());
        Excerption.setText(book.get(pos).getExcerption());
        Description.setText(book.get(pos).getDescription());
        scaleRatingBar.setRating(book.get(pos).getRating());
        RatingNumber.setText("Рейтинг: " + book.get(pos).getRating());
        recyclerAdapterCircleItems = new RecyclerAdapterCircleItems(getContext(), book, pos);
        CircleItems.setAdapter(recyclerAdapterCircleItems);
        List<String> keys = Paper.book("books").getAllKeys();
        for (String key : keys){
            if (key.equals(book.get(pos).getBook())){
                likeButton.setLiked(true);
            }
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
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
        Excerption.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        onStopProgress();
    }

    @Override
    public void onStartProgress() {
        progressCircula.startRotation();
        scrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStopProgress() {
        progressCircula.stopRotation();
        scrollView.setVisibility(View.VISIBLE);
        progressCircula.setVisibility(View.GONE);
    }
}
