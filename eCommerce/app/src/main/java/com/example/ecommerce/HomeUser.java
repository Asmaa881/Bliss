package com.example.ecommerce;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.paperdb.Paper;

public class HomeUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        Toolbar toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(HomeUser.this);
        showHomeFragment();
    }


    void showHomeFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.userHomecontainer, new HomeUserFragment()).addToBackStack("");
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_home:
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.userHomecontainer, new HomeUserFragment()).addToBackStack("");
                ft.commit();
                break;
            case R.id.nav_favorites:

                break;
            case R.id.nav_orders:

                break;
            case R.id.nav_share_app:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String shareBody = "https//play.google.com/store/apps/details ? id=package com.example.userpart";
                String shareSub = "Share App";
                share.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                share.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(share, "Share"));
                break;
            case R.id.nav_send_notes:
                String txtEmail = "Hello Developers\n My Opinion is ";
                Intent sendNotes = new Intent(Intent.ACTION_SEND);
                sendNotes.setData(Uri.parse("mailto: "));
                sendNotes.setType("message/rfc822");
                sendNotes.putExtra(Intent.EXTRA_EMAIL,"Aya.h.elrayess@gmail.com");
                sendNotes.putExtra(Intent.EXTRA_SUBJECT, "Bliss");
                sendNotes.putExtra(Intent.EXTRA_TEXT, txtEmail);
                startActivity(sendNotes);
                break;
            case R.id.nav_rate_us:

                break;
            case R.id.nav_about_us:
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.userHomecontainer, new AboutUsFragment()).addToBackStack("");
                ft1.commit();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeUser.this);
                builder.setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 Paper.book().destroy();
                                 Intent logout = new Intent(HomeUser.this,MainActivity.class);
                                 startActivity(logout);
                                 finish();
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            default:
                //  fragment = GalleryFragment.class;
        }
        return false;
    }

}