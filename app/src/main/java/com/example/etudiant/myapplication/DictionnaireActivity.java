package com.example.etudiant.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

public class DictionnaireActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    class Expression{
        
        public Expression(String qc, String fr) {
            this.qc = qc;
            this.fr = fr;
        }

        public String getQc() {
            return qc;
        }

        public void setQc(String qc) {
            this.qc = qc;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        private String qc;
        private String fr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Expression[] expressions= new Expression[50];
        for (int i =0;i<10;i+=1){
            expressions[i*5]=new Expression("Yup","Oui");
            expressions[i*5+1]=new Expression("Ostie de tarbarnak","Zut!");
            expressions[i*5+2]=new Expression("asdadasdasdasdasdasdasdasdasdadadwadwasdwasdwasdwasdwasdwasdwasdLoL","MDR");
            expressions[i*5+3]=new Expression("Salut","Bonjour");
            expressions[i*5+4]=new Expression("Nope","Non");
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionnaire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DictionnaireAdapter da = new DictionnaireAdapter(this,expressions);
        ((ListView) findViewById(R.id.dicoListView)).setAdapter(da);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dictionnaire, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DictionnaireAdapter extends ArrayAdapter<Expression>{
        private final Context context;
        private final Expression[] values;

        public DictionnaireAdapter(Context context, Expression[] values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("yo",String.valueOf(position));
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item_dictionnaire, parent, false);
            TextView qcTextView = (TextView) rowView.findViewById(R.id.qcTextView);
            TextView frTextView = (TextView) rowView.findViewById(R.id.frTextView);
            qcTextView.setText(values[position].getQc());
            frTextView.setText(values[position].getFr());
            return rowView;
        }
    }
}