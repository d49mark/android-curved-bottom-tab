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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurvedBottomNavigationView customBottomNavigationView = (CurvedBottomNavigationView) findViewById(R.id.customBottomBar);
        //customBottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);
       customBottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
       openFragment(new FirstFragment());
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) customBottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(1).findViewById(com.google.android.material.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 52, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
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