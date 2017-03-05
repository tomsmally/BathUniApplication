package tom.smally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Map extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final WebView web1 = (WebView) findViewById(R.id.webView);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.getSettings().setBuiltInZoomControls(true);
        web1.getSettings().setSupportZoom(true);
        web1.getSettings().setDisplayZoomControls(false);//removes zoom control-buttons - allows pinch-zoom
        web1.getSettings().setUseWideViewPort(true);

        String [] mapArray = new String[4];
        mapArray[0] = "Blocks";
        mapArray[1] = "Amenities";
        mapArray[2] = "3D with key";
        mapArray[3] = "City";
        Spinner spin1 = (Spinner) findViewById(R.id.mapSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                Map.this, android.R.layout.simple_spinner_item, mapArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                web1.setInitialScale(1);
                if (position == 0)
                    web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='campusmap1.jpg' />", "text/html", "utf-8", null);
                if (position == 1)
                    web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='campusmap2.png' />", "text/html", "utf-8", null);
                if (position == 2)
                    web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='campusmap4.jpg' />", "text/html", "utf-8", null);
                if (position == 3)
                    web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='citymap1.png' />", "text/html", "utf-8", null);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.map, menu);
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
        }else if(id == R.id.action_signout) {
            //onSignout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            Intent intent = new Intent(Map.this, MainActivity.class);
            Map.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Map.this, Popular.class);
            Map.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Map.this, Email.class);
            Map.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            //do nothing
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
