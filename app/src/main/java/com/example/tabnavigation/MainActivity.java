package com.example.tabnavigation;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tabnavigation.fragments.FirstFragment;
import com.example.tabnavigation.fragments.HomeFragment;

import com.example.tabnavigation.fragments.SecondFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private int CENTER_ICON_OFFSET=80 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurvedBottomNavigationView customBottomNavigationView =  findViewById(R.id.customBottomBar);
        customBottomNavigationView.getMenu().clear(); //clear old inflated items.
        customBottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);
       customBottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new FirstFragment());
        customBottomNavigationView.setClipChildren(false);
        customBottomNavigationView.setClipToPadding(false);
        customBottomNavigationView.setClipToOutline(false);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) customBottomNavigationView.getChildAt(0);
            final View iconView = menuView.getChildAt(1).findViewById(com.google.android.material.R.id.icon);
            menuView.setClipChildren(false);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)iconView.getLayoutParams();
            marginLayoutParams.setMargins(0,0 , 0, 46);
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62, displayMetrics);
            //For shadow around center button
            //iconView.setElevation(240 * displayMetrics.density);
            iconView.setLayoutParams(layoutParams);
            iconView.setX(0);
            iconView.setY(customBottomNavigationView.getMeasuredHeight()-CENTER_ICON_OFFSET);

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    CurvedBottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Fragment firstFragment= new FirstFragment();
                            openFragment(firstFragment);
                            return true;
                        case R.id.navigation_sms:
                            Fragment homeFragment= new HomeFragment();
                            openFragment(homeFragment);
                            return true;
                        case R.id.action_music:
                            Fragment secondFragment=new SecondFragment();
                            openFragment(secondFragment);
                            return true;
                    }
                    return false;
                }
            };
}