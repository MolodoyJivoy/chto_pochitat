package zakaz.zakaz.chto_pochitat.Data;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import zakaz.zakaz.chto_pochitat.Model.Book;
import zakaz.zakaz.chto_pochitat.View.IMainView;

public class Data implements IDataClass {

    private List<Book> books = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public List<Book> getData() {

        List<String> arr = new ArrayList<>();
        arr.add("#роман");
        arr.add("#ужасы");
        arr.add("#фантастика");

        books.add(new Book("Три товарища", "Э. М. Ремарк", "Бабушка правда умеет уговаривать. У меня в детстве часто был насморк, и бабушка убеждала всех, что сопли - признак гениальности. И ей верили!", arr, (float)4.86, "Запомни одну вещь, мальчик: никогда, никогда и еще раз никогда ты не окажешься смешным в глазах женщины, если сделаешь что-то ради нее"));
        books.add(new Book("Портрет Дориана Грея", "Оскар Уальд", "Бабушка правда умеет уговаривать. У меня в детстве часто был насморк, и бабушка убеждала всех, что сопли - признак гениальности. И ей верили!", arr, (float)4.32, "Запомни одну вещь, мальчик: никогда, никогда и еще раз никогда ты не окажешься смешным в глазах женщины, если сделаешь что-то ради нее"));
        books.add(new Book("Тринадцатая сказка", "Диана Сеттерфилд", "Бабушка правда умеет уговаривать. У меня в детстве часто был насморк, и бабушка убеждала всех, что сопли - признак гениальности. И ей верили!", arr, (float)4.00, "Запомни одну вещь, мальчик: никогда, никогда и еще раз никогда ты не окажешься смешным в глазах женщины, если сделаешь что-то ради нее"));
        return books;
    }

    @Override
    public void getItemData(final IMainView iMainView) {
//        DocumentReference documentReference = db.collection("book").document();
//        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                List<Book> books = new ArrayList<>();
//                while (documentSnapshot.exists()) {
//                    books.add(documentSnapshot.toObject(Book.class));
//                }
//                iMainView.onBookResult(books);
//            }
//        });
        db.collection("book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> map = document.getData();
                    Book m_books = document.toObject(Book.class);
                    books.add(m_books);
                }
                iMainView.onBookResult(books);
            }
        });
    }
}
