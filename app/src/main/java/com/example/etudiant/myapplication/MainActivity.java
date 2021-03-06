package com.example.etudiant.myapplication;

import android.content.ContentValues;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static boolean detail=false;

        //Permission pour accéder au GPS
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 123 ;
    public static String bddName="bdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //getActionBar().setTitle("Calculateur de tip");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Your Guide");
        requestPermission();


        if(savedInstanceState==null||!savedInstanceState.getBoolean("ever_started")) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ArticleActivity fragment = new ArticleActivity();
            fragmentTransaction.add(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
            createDatabase();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean("ever_started", true);
    }

    private void createDatabase(){
        SQLiteOpenHelper helper=new DatabaseHandler(getApplicationContext(), bddName, null, 3);
        helper.onUpgrade(helper.getWritableDatabase(), 3, 3);
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.ARTICLE_CONTENU_HTML, ArticleActivity.article1);
        value.put(DatabaseHandler.ARTICLE_CONTENU_STRING, ArticleActivity.article1sanshtml);
        helper.getWritableDatabase().insert(DatabaseHandler.ARTICLE_TABLE_NAME, null, value);
        ContentValues value2 = new ContentValues();
        value2.put(DatabaseHandler.ARTICLE_CONTENU_HTML, ArticleActivity.article2);
        value2.put(DatabaseHandler.ARTICLE_CONTENU_STRING, ArticleActivity.article2sanshtml);
        helper.getWritableDatabase().insert(DatabaseHandler.ARTICLE_TABLE_NAME, null, value2);
    }

    @Override
    public void onBackPressed() {

/*
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isAcceptingText()){

            imm.hideSoftInputFromWindow((IBinder) getWindow(),0);

        }
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(detail){

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            ArticleActivity newFrag = new ArticleActivity();
            transaction.replace(R.id.frameLayout, newFrag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

            detail=false;


        }
        else {
            super.onBackPressed();
        }


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (id == R.id.articles) {
            ArticleActivity newFrag = new ArticleActivity();
            transaction.replace(R.id.frameLayout, newFrag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        } else if (id == R.id.convertor) {
            /*Intent intent =  new Intent(this, ConvertorDevise.class);
            startActivity(intent);*/

            ConvertorDevise newFrag = new ConvertorDevise();
            transaction.replace(R.id.frameLayout, newFrag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();


        } else if (id == R.id.calculator_tips) {
            /*Intent intent =  new Intent(this, activity_tip_calculator.class);
            startActivity(intent);*/
            activity_tip_calculator newFrag = new activity_tip_calculator();
            transaction.replace(R.id.frameLayout, newFrag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            transaction.commit();

        } else if (id == R.id.point_interet) {
            Intent intent =  new Intent(this, InteretActivity.class);
            startActivity(intent);



        } else if (id == R.id.dico_expression) {
            /*Intent intent = new Intent(this,DictionnaireActivity.class);
            startActivity(intent);*/
            DictionnaireActivity newFrag = new DictionnaireActivity();
            transaction.replace(R.id.frameLayout, newFrag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            transaction.commit();
        } /*else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
    }
}
