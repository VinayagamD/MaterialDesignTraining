package com.wattabyte.materialdesigntraining;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wattabyte.materialdesigntraining.util.Information;
import com.wattabyte.materialdesigntraining.util.MaterialDesignConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment /*implements VinayAdapter.ClickListener*/{


    /**
     *  STEPS TO HANDLE THE RECYCLE CLICK
     *
     *  1 Create  a class that EXTENDS RecyclerView.OnItemTouchListener
     *
     *  2 Create an interface inside that class that supports click and long click and indicates the View that was clicked and the position where it was clicked
     *
     *  3 Create  a GestureDetetcor to detect ACTION_UP single tap and Long Press events
     *
     *  4 Return true from the singleTap to indicate your GestureDetetcor has consumed the event
     *
     *  5 Find the childView containing the coordinates specified by the motionEvent and if the childView is not null and the listener is not null either. Fire l;ong event
     *
     *  6 Use the onInterceptTouchEvent of your RecyclerView to check if the childView is not null, the listener is not null and the gesture detector consumed the ctouch event
     *
     *  7 If above condition hold true. fire the click event
     *
     */

    public static final String PREF_FILE_NAME  = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer ";

    private RecyclerView recyclerView;
    private VinayAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private View containerView;

    /*Check user is aware of the drawer*/

    private boolean mUserLearnedDrawer;

    /*Check whether fragment is created for first time*/
    private boolean mFromSavedInstanceState;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER,"false"));

        /*Check activity is coming back from rotation*/
        if (savedInstanceState != null){
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new VinayAdapter(getActivity(),getData());
        /*Methid 2*/
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),"onClick "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"onLongClick "+position, Toast.LENGTH_SHORT).show();
            }
        }));
        return layout;
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1,R.drawable.ic_number2,
                            R.drawable.ic_number3,R.drawable.ic_number4};
        String[] titles = {"Vivz","Anky","Slidenerd","YouTube"};

       /* for (int i = 0; i < titles.length&& i< icons.length; i++) {
             Information current = new Information();
            current.title = titles[i] ;
            current.itemId = icons[i];
            data.add(current);
        }*/

        for (int i= 0; i<100; i++){
            Information current = new Information();
            current.itemId = icons[i%icons.length];
            current.title = titles[i%titles.length];
            data.add(current);
        }
        return  data;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.6)
                toolbar.setAlpha(1-slideOffset);
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }



    public  static void  saveToPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName,preferenceValue);
        editor.apply();
    }

    public  static String   readFromPreferences(Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

//  Method 2
// @Override
//    public void itemClicked(View view, int position) {
//        startActivity(new Intent(getActivity(),SubActivityCustom.class));
//    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector  gestureDetector;
        private  ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            Log.d(MaterialDesignConstants.TAG,"On constructor invoked ");
            this.clickListener = clickListener;

            gestureDetector  = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d(MaterialDesignConstants.TAG, "onSingleTapUp " + e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());

                    if(child!=null && clickListener != null){
                        clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                    Log.d(MaterialDesignConstants.TAG, "onLongPress " + e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if (child != null && clickListener!=null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }
            Log.d(MaterialDesignConstants.TAG, "onInterceptTouchEvent invoked " +gestureDetector.onTouchEvent(e)+" " + e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            Log.d(MaterialDesignConstants.TAG, "onTouchEvent "+ e);

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);

    }
}
