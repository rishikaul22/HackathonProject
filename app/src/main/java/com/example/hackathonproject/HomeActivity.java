package com.example.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    public static Fragment fragment;
    Class fragmentClass;

    private Button logout_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sNavigationDrawer=findViewById(R.id.Navigation_drawer);
        List<MenuItem> menuItems=new ArrayList<>();

        menuItems.add(new MenuItem("Home",R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Contact Us",R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Sign Out",R.mipmap.ic_launcher));
        menuItems.add(new MenuItem("Help",R.mipmap.ic_launcher));

        sNavigationDrawer.setMenuItemList(menuItems);

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {


            @Override
            public void onMenuItemClicked(int position) {
                switch (position){

                    case 0:{
                        fragmentClass=HomeFragment1.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = ContactFragment.class;
                        break;
                    }
                    case 2:{
                        fragmentClass = Fragment_SignOut.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = HelpFragment.class;
                        break;
                    }

                }

                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                    @Override
                    public void onDrawerOpening() {

                    }
                    @Override
                    public void onDrawerClosing() {

                        try {
                            fragment=(Fragment)fragmentClass.newInstance();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        if (fragment!=null){
                            FragmentManager fragmentManager=getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                                    .replace(R.id.frameLayout,fragment).commit();
                        }

                    }

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                });
            }
        });

        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
                                                @Override
                                                public void onDrawerOpening() {

                                                }

                                                @Override
                                                public void onDrawerClosing() {

                                                    try {
                                                        fragment=(Fragment)fragmentClass.newInstance();
                                                    } catch (IllegalAccessException e) {
                                                        e.printStackTrace();
                                                    } catch (InstantiationException e) {
                                                        e.printStackTrace();
                                                    }

                                                    if (fragment!=null){
                                                        FragmentManager fragmentManager=getSupportFragmentManager();
                                                        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                                                                .replace(R.id.frameLayout,fragment).commit();
                                                    }

                                                }

                                                @Override
                                                public void onDrawerOpened() {

                                                }

                                                @Override
                                                public void onDrawerClosed() {

                                                }

                                                @Override
                                                public void onDrawerStateChanged(int newState) {

                                                }
                                            }
        );
    }
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
