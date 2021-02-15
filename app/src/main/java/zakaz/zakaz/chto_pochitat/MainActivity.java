package zakaz.zakaz.chto_pochitat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;

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

public class MainActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new About());
        fragments.add(new MainMenu());
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

}
