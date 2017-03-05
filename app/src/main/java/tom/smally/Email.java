package tom.smally;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

public class Email extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Email");

        final WebView web1 = (WebView) findViewById(R.id.webView);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.getSettings().setBuiltInZoomControls(true);
        web1.getSettings().setSupportZoom(true);
        web1.getSettings().setDisplayZoomControls(false);//removes zoom control-buttons - allows pinch-zoom
        web1.getSettings().setUseWideViewPort(true);
        web1.loadUrl("https://mail.bath.ac.uk");
        web1.setInitialScale(1);

        web1.setWebViewClient(new WebViewClient() {
            Boolean pageSubmitted = false;
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Email.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                if (!pageSubmitted) {
                        if (url.equals("https://mail.bath.ac.uk/owa/")) {
                            //already logged in, so leave it
                            pageSubmitted = true;
                        } else {
                            if (url.contains("https://mail.bath.ac.uk/owa/#path=/mail")) {
                                pageSubmitted = true;
                            } else {
                                web1.loadUrl("javascript: {" +
                                        "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                        "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                        "document.getElementsByClassName('signinbutton')[0].click();" + //login-button - for TABLET login
                                        "};"); //submit*/
                                pageSubmitted = true;
                            }
                        }
                } else {
                    //these conditions are used to distinguish if the user has OWA or Webmail account and goes back to homepage
                    if (!(url.contains("https://mail.bath.ac.uk/owa/"))) {
                        Toast.makeText(Email.this, "Email not migrated, opening Bath Webmail", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Email.this, MainActivity.class);
                        Email.this.startActivity(intent);
                    }
                    if (url.equals("https://mail.bath.ac.uk/owa/")) {
                        pageSubmitted = true;
                    }else if (url.contains("https://mail.bath.ac.uk/owa/")) {
                        web1.loadUrl("javascript: {" +
                                "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                "document.getElementsByClassName('login-button')[0].click();" + // - for TABLET login?
                                "};"); //submit*/
                        pageSubmitted = true;
                    } else if (!(url.contains("https://webmail.bath.ac.uk/imp/mailbox"))) {

                    }else{
                        web1.loadUrl("https://mail.bath.ac.uk/owa/");
                    }
                }
            }
        });

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
        getMenuInflater().inflate(R.menu.email, menu);
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
            Intent intent = new Intent(Email.this, MainActivity.class);
            Email.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Email.this, Popular.class);
            Email.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Email.this, Map.class);
            Email.this.startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
