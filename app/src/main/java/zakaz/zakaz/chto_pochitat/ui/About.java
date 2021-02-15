package zakaz.zakaz.chto_pochitat.ui;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import zakaz.zakaz.chto_pochitat.R;

public class About extends Fragment {

    private Button wishes;
    private TextView link;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.activity_about, container, false);

        wishes = viewGroup.findViewById(R.id.Wishes);
        link = viewGroup.findViewById(R.id.linkAbout);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        wishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetsDialogTheme);
                View bottomView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheets_about, (LinearLayout)getActivity().findViewById(R.id.bottomContainer));
                Button sendButton = bottomView.findViewById(R.id.sendButton);
                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.show();
            }
        });

        return viewGroup;
    }
}
