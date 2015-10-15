package com.wattabyte.materialdesigntraining;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TabUsingLibraryActivity extends AppCompatActivity implements MaterialTabListener {

    public static final String POSITON = "Positon";
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_using_library);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0 ; i <myPagerAdapter.getCount();i++){
            tabHost.addTab(tabHost.newTab()
                    .setIcon(myPagerAdapter.getIcon(i))
                    .setTabListener(this));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

   private   class MyPagerAdapter  extends FragmentPagerAdapter {

       int[] icons = {
               R.drawable.ic_action_home,R.drawable.ic_action_articles,R.drawable.ic_action_personal,
               R.drawable.ic_action_home,R.drawable.ic_action_articles,R.drawable.ic_action_personal,
               R.drawable.ic_action_home,R.drawable.ic_action_articles,R.drawable.ic_action_personal
       };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return MyFragment.getInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return getResources().getStringArray(R.array.tabs)[position];
        }

       private Drawable getIcon(int position){
           return ContextCompat.getDrawable(TabUsingLibraryActivity.this,icons[position]);
       }

        @Override
        public int getCount() {
            return 9;
        }
    }


}
