package com.wattabyte.materialdesigntraining;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wattabyte.tab.SlidingTabLayout;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    public static final String POSITON = "Positon";
    private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Basic setup for navigation drawer*/
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);


        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
       mTabs.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));
        mTabs.setViewPager(mPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case  R.id.action_settings :
            break;
            case R.id.navigate :
                startActivity(new Intent(this,SubActivityCustom.class));
                break;
            case  R.id.tabsUsingLibrary:
                startActivity(new Intent(this,TabUsingLibraryActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter  extends FragmentPagerAdapter{

        int[] icons = {R.drawable.ic_action_home,R.drawable.ic_action_articles,R.drawable.ic_action_personal};
        String [] tabs;


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = MyFragment.getInstance(position);
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = ContextCompat.getDrawable(MainActivity.this,icons[position]);
            drawable.setBounds(0,0,72,72);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public static class MyFragment extends Fragment{

        private TextView textView;

        public static MyFragment getInstance(int position){
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt(POSITON,position);
            myFragment.setArguments(args);
             return myFragment;
        }


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_my,container,false);
            textView = (TextView) layout.findViewById(R.id.pagerText);
            Bundle bundle = getArguments();
            if (bundle!= null)
                textView.setText(String.format("The page currently selected is %d", bundle.getInt(POSITON)));
            return layout;
        }
    }
}
