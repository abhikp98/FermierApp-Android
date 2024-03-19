package com.app.fermierapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.app.fermierapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sh;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        setSupportActionBar(binding.appBarHome.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View headerview = navigationView.getHeaderView(0);
        LinearLayout ln = headerview.findViewById(R.id.header);
        TextView name = ln.findViewById(R.id.name);
        TextView email = ln.findViewById(R.id.textView);
        TextView phone = ln.findViewById(R.id.textView122);
        name.setText(sh.getString("name", ""));
        email.setText(sh.getString("email", ""));
        phone.setText(sh.getString("phone", ""));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_all_list){
                    startActivity(new Intent(getApplicationContext(), ViewCpfActivity.class));
                }
                if (id == R.id.nav_all_off){
                    startActivity(new Intent(getApplicationContext(), NearestOfficesActivity.class));
                }
                if (id == R.id.nav_view_request_status){
                    startActivity(new Intent(getApplicationContext(), ViewRequestStatus.class));

                }
                if(id == R.id.nav_view_complaints){
                    startActivity(new Intent(getApplicationContext(), ViewComplaints.class));
                }
                if(id == R.id.nav_send_feedback){
                    startActivity(new Intent(getApplicationContext(), SendFeedback.class));

                }


                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart){
            startActivity(new Intent(getApplicationContext(), ViewCartActivity.class));
        }
        if (id == R.id.action_notifications){
            startActivity(new Intent(getApplicationContext(), ViewNotificationActivity.class));
        }
        if (id == R.id.action_logout){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
//        super.onBackPressed();
    }
}