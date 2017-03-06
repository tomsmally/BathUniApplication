package tom.smally;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Popular extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    String GlobalEmail;
    String GlobalUsername;
    String GlobalPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        Intent intent = getIntent();
        GlobalEmail = intent.getStringExtra("EMAIL");
        GlobalUsername = intent.getStringExtra("USERNAME");
        GlobalPassword = intent.getStringExtra("PASSWORD");

        setTitle("Timetable");

        /*try {
            HttpURLConnection conn = new Task();
            System.out.println(Task.getResponseMessage());
            int responseCode = connection.getResponseCode(); // getting the response code<br />
            final StringBuilder output = new StringBuilder("Request URL " + url1.toString());
                //output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
                output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            System.out.println("output===============" + br);
            while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
            }
            br.close();
            output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());
            System.out.println(output);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AsyncTask Task = new AsyncTask() {

            @Override
            protected HttpURLConnection doInBackground(Object[] objects) {

                try {
                    final URL url1 = new URL("https://mytimetable.bath.ac.uk/");
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestProperty("User-Agent", "");
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    return connection;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };*/

        final WebView web1 = (WebView) findViewById(R.id.webView);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.getSettings().setBuiltInZoomControls(true);
        web1.getSettings().setSupportZoom(true);
        web1.getSettings().setDisplayZoomControls(false);//removes zoom control-buttons - allows pinch-zoom
        web1.getSettings().setUseWideViewPort(true);
        web1.loadUrl("https://mytimetable.bath.ac.uk/");
        web1.setWebViewClient(new WebViewClient() {
            Boolean pageSubmitted = false;

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Popular.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!pageSubmitted) {
                    web1.loadUrl("javascript: {" +
                            "document.getElementById('username').value = '" + GlobalUsername + "';" +
                            "document.getElementById('password').value = '" + GlobalPassword + "';" +
                            "document.getElementsByClassName('btn-submit')[0].click();" + //login-button - only for PC login
                            "};"); //submit*/
                    /*web1.loadUrl("javascript: {" +
                            "document.getElementById('username').value = '" + "ts690" + "';" +
                            "document.getElementById('password').value = '" + "Snowleopard2412" + "';" +
                            "document.getElementById('btn-submit').click();" +//login-button - only for PC login
                            "};"); //submit*/
                    pageSubmitted = true;
                } else {
                    /*web1.loadUrl("javascript: {" +
                            "document.getElementById('username').value = '" + "ts690" + "';" +
                            "document.getElementById('password').value = '" + "Snowleopard2412" + "';" +
                            "document.getElementById('btn-submit').click();" +//login-button - only for PC login
                            "};"); //submit
                    pageSubmitted = true;*/
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        getMenuInflater().inflate(R.menu.popular, menu);
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
            Intent intent = new Intent(Popular.this, MainActivity.class);
            Popular.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            //do nothing
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Popular.this, Email.class);
            intent.putExtra("EMAIL",GlobalEmail);
            intent.putExtra("USERNAME",GlobalUsername);
            intent.putExtra("PASSWORD",GlobalPassword);
            Popular.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Popular.this, Map.class);
            Popular.this.startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
