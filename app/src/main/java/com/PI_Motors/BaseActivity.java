package com.PI_Motors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class BaseActivity extends AppCompatActivity {
    NavController navCtrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        NavHostFragment nav_host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.base_navhost);
        navCtrl = nav_host.getNavController();
        Intent fromProfileToBase = getIntent();
        String userUID = fromProfileToBase.getStringExtra("userUID");
        NavigationUI.setupActionBarWithNavController(this,navCtrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.base_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!super.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    navCtrl.navigateUp();
                    return true;
                case R.id.all_posts:
                    navCtrl.navigate(R.id.carListFragment);
                    return true;
                case R.id.my_posts:
                    navCtrl.navigate(R.id.editCarFragment);
                    return true;
                case R.id.log_out:

                    return true;
                default:
                    return NavigationUI.onNavDestinationSelected(item, navCtrl);
            }
        }
        return true;
    }
}