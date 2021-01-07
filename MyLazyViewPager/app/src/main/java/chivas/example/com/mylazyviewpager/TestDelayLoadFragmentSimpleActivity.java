package chivas.example.com.mylazyviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chivas.example.com.mylazyviewpager.simple.SimpleFragmentPagerAdapter;
import chivas.example.com.mylazyviewpager.simple.SimpleLazyFragment;
import chivas.example.com.mylazyviewpager.simple.TestViewPagerPopulateLazyFragment;

public class TestDelayLoadFragmentSimpleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_load_fragment);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        List<Fragment> fragmentList = new ArrayList<>();
        TestViewPagerPopulateLazyFragment fragment = TestViewPagerPopulateLazyFragment.newInstance(1);
        Toast.makeText(this, "fragment.isHidden(): " + fragment.isHidden(), Toast.LENGTH_SHORT).show();
        fragmentList.add(fragment);
        fragmentList.add(SimpleLazyFragment.newInstance(2));
        fragmentList.add(SimpleLazyFragment.newInstance(3));
        fragmentList.add(SimpleLazyFragment.newInstance(4));
        fragmentList.add(SimpleLazyFragment.newInstance(5));
        
        PagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setOnPageChangeListener(viewpagerChangeListener);
    }

    ViewPager.OnPageChangeListener viewpagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            Log.d("FragmentLifeCyclex", "onPageSelected -> " + i);
            int itemId = R.id.fragment_1;
            switch (i) {
                case 0:
                    itemId = R.id.fragment_1;
                    break;
                case 1:
                    itemId = R.id.fragment_2;
                    break;
                case 2:
                    itemId = R.id.fragment_3;
                    break;
                case 3:
                    itemId = R.id.fragment_4;
                    break;
                case 4:
                    itemId = R.id.fragment_5;
                    break;
            }
            bottomNavigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d("FragmentLifeCyclex", "onNavigationItemSelected -> " + item.getItemId());
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.fragment_2:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.fragment_3:
                    viewPager.setCurrentItem(2, true);
                    return true;
                case R.id.fragment_4:
                    viewPager.setCurrentItem(3, true);
                    return true;
                case R.id.fragment_5:
                    viewPager.setCurrentItem(4, true);
                    return true;
            }
            return false;
        }

    };
}
