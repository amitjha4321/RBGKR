package com.example.rbgkr.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.rbgkr.Fragments.CollectionsFragments;
import com.example.rbgkr.Fragments.FavoriteFragment;
import com.example.rbgkr.Fragments.PhotosFragment;
import com.example.rbgkr.R;
import com.example.rbgkr.Utils.Functions;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PhotosFragment homeFragment = new PhotosFragment();
        Functions.changeMainFragment(MainActivity.this, homeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.main_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle no action bar item ......
        int id= item.getItemId();
        //......
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_photos){
            //Handle the camera setting
            PhotosFragment photosFragment=new PhotosFragment();
            Functions.changeMainFragment(MainActivity.this,photosFragment);
        }else if(id==R.id.nav_collections){
            CollectionsFragments collectionFragments=new CollectionsFragments();
            Functions.changeMainFragment(MainActivity.this,collectionFragments);
        }else if(id==R.id.nav_favorite){
            FavoriteFragment favoriteFragment=new FavoriteFragment();
            Functions.changeMainFragment(MainActivity.this,favoriteFragment);
        }
        DrawerLayout drawer= findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

}