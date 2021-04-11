package zakaz.zakaz.chto_pochitat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hmomeni.progresscircula.ProgressCircula;

import java.util.ArrayList;
import java.util.List;

import zakaz.zakaz.chto_pochitat.Adapter.SliderPagerAdapter;
import zakaz.zakaz.chto_pochitat.ui.About;
import zakaz.zakaz.chto_pochitat.ui.Favorites;
import zakaz.zakaz.chto_pochitat.ui.MainMenu;
import zakaz.zakaz.chto_pochitat.ui.Themes;

/**
 * ViewPager трех меню
 * */

// implements GestureDetector.OnGestureListener
public class MainActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private ViewPager2 viewPager2;
    private PagerAdapter pagerAdapter;

    private MainMenu mainMenu;
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainMenu = new MainMenu();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new About());
        fragments.add(mainMenu);
        fragments.add(new Favorites());

        viewPager = findViewById(R.id.viewPagerAll);
        pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    Favorites fragment = (Favorites) pagerAdapter.instantiateItem(viewPager, position);
                    if (fragment != null) {
                        fragment.onRefresh();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                float valueY = y2 - y1;

                DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
                int dp =  Math.round(28 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

                float value = displayMetrics.heightPixels - dp;

                if (y1 > value){
                    if (Math.abs(valueY) > MIN_DISTANCE){
                        if (y2>y1){
                            //Toast.makeText(this, "Нижний свайп право", Toast.LENGTH_SHORT).show();
                        }else {
                            mainMenu.showBottomMenu();
                            //Toast.makeText(this, "Верхний свайп право", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        }

        return super.dispatchTouchEvent(event);
    }
}
