package tom.smally;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Library extends MainActivity {

    HashMap<String, String> pcFinderLinks = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        setTitle("Library");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pcFinderLinks.put("Library","https://isecure.bath.ac.uk/computingavailability/Location.aspx?id=1");
        pcFinderLinks.put("Library+Level5","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+5+PCs&parentid=1");
        pcFinderLinks.put("Library+Level4","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+4+PCs&parentid=1");
        pcFinderLinks.put("Library+Level3","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+3+PCs&parentid=1");
        pcFinderLinks.put("Library+Level2","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+2+PCs&parentid=1");
        pcFinderLinks.put("Library+Level+2QA","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+2+QA+PCs&parentid=1");
        pcFinderLinks.put("Library+Level+2Cat","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+2+LibCat+PCs&parentid=1");
        pcFinderLinks.put("Library+Level1","https://isecure.bath.ac.uk/computingavailability/Room.aspx?id=Level+1+PCs&parentid=1");

        pcFinderLinks.put("Teaching","https://isecure.bath.ac.uk/computingavailability/Location.aspx?id=4");

    }

    @Override
    public void onBackPressed()
    {
        this.finish();
    }

    public void pinClick(View v)
    {
        setContentView(R.layout.activity_library2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView) findViewById(R.id.webView2);
        webView.loadUrl("http://bath-ac-primo.hosted.exlibrisgroup.com/primo_library/libweb/action/login.do?loginFn=signin&vid=44BAT_VU1&authProfileName=44BAT_ALMA_SAML_Prod&targetURL=http%3a%2f%2fbath-ac-primo.hosted.exlibrisgroup.com%2fprimo_library%2flibweb%2faction%2fmyAccountMenu.do%3fvid%3d44BAT_VU1%26institution%3d44BAT");
        libraryViewer(webView,"pin");
    }

    public void borrowClick(View v)
    {
        setContentView(R.layout.activity_library2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView) findViewById(R.id.webView2);
        webView.loadUrl("http://bath-ac-primo.hosted.exlibrisgroup.com/primo_library/libweb/action/login.do?loginFn=signin&vid=44BAT_VU1&authProfileName=44BAT_ALMA_SAML_Prod&targetURL=http%3a%2f%2fbath-ac-primo.hosted.exlibrisgroup.com%2fprimo_library%2flibweb%2faction%2fmyAccountMenu.do%3fvid%3d44BAT_VU1%26institution%3d44BAT");
        libraryViewer(webView,"borrow");
    }

    public void pcFinderClick(View v)
    {
        setContentView(R.layout.activity_library2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView) findViewById(R.id.webView2);
        webView.loadUrl("https://isecure.bath.ac.uk/computingavailability/");
        libraryViewer(webView,"pc");
    }

    /*
    * Search for: Library1
     */
    public  void libraryViewer(final WebView webView, final String buttonClick)
    {
        final Activity activity = this;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);//removes zoom control-buttons - allows pinch-zoom
        webView.getSettings().setUseWideViewPort(true);

        if(buttonClick.equals("queue")
                ||buttonClick.equals("radio")||buttonClick.contains("email")||buttonClick.contains("pc")) {
            webView.setInitialScale(1);
        }

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        webView.setWebViewClient(new WebViewClient() {
            Boolean pageSubmitted = false;

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                if (!pageSubmitted) {
                    if (buttonClick.equals("table")) {
                        if (url.equals("https://mytimetable.bath.ac.uk/m/#loggedin")) {
                            webView.loadUrl("javascript: {" +
                                    "document.getElementsById('goto-menu').click();" + //login-button - only for PC login
                                    "};");
                            webView.loadUrl("javascript: {" +
                                    "document.getElementsById('goto-logout').click();" + //login-button - only for PC login
                                    "};");
                        }
                    }else if (buttonClick.equals("borrow") || buttonClick.equals("pin"))
                    {
                        System.out.println("loading bath-ac-primo.hosted.exlibrisgroup.com");
                        webView.loadUrl("javascript: {" +
                            "document.getElementsByClassName('ng-scope').click();" +
                            "};");
                        System.out.println("loaded: bath-ac-primo");
                        webView.loadUrl("javascript: {" +
                                "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                "document.getElementsByClassName('btn-submit')[0].click();" + //login-button - only for PC login
                                "};"); //submit*/
                        pageSubmitted = true;
                    } else if (buttonClick.equals("login")) {
                        if (webView.getUrl().contains("http://library.bath.ac.uk/uhtbin/bath/UNIV-BATH/login/pin?ticket=ST")) {
                            //no need to log in
                            webView.reload();
                            pageSubmitted = true;
                        } else {
                            //need to re-login
                            webView.loadUrl("javascript: {" +
                                    "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                    "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                    "document.getElementsByClassName('btn-submit')[0].click();" + //login-button - only for PC login
                                    "};"); //submit*/
                            pageSubmitted = true;
                        }
                    } else if (buttonClick.equals("atm")) {
                        webView.loadUrl("javascript: {" +
                                "document.getElementById('mapSearchTextbox').value = '" + "bath" + "';" +
                                "document.getElementsById('searchButton').click();" + //login-button - only for PC login
                                "};"); //submit*/
                        pageSubmitted = true;
                        //webView.loadUrl("javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions('btn-submit', '', true, '', '', false, true))");
                    } else if (buttonClick.contains("email")) {
                        if (buttonClick.equals("emailOld")) {
                            webView.loadUrl("javascript: {" +
                                    "document.getElementById('horde_user').value = '" + GlobalUsername + "';" +
                                    "document.getElementById('horde_pass').value = '" + GlobalPassword + "';" +
                                    //"document.getElementById('login-button').click();
                                    //  "document.getElementById('horde-login-button').click();" +//login-button - only for PC login
                                    "document.getElementsByClassName('ui-btn-hidden')[0].click();" +
                                    "};");
                            pageSubmitted = true;
                            pd.dismiss();
                        } else {
                            if (url.equals("https://mail.bath.ac.uk/owa/")) {
                                //webView.loadUrl("https://mail.bath.ac.uk/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fmail.bath.ac.uk%2fowa");//outlook
                                //already logged in, so leave it
                                pd.dismiss();
                                pageSubmitted = true;
                            } else {
                                if (url.contains("https://mail.bath.ac.uk/owa/#path=/mail")) {
                                    pd.dismiss();
                                    pageSubmitted = true;
                                } else {
                                    //Desktop:
                                    //String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
                                    webView.loadUrl("javascript: {" +
                                            "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                            "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                            "document.getElementsByClassName('signinbutton')[0].click();" + //login-button - for TABLET login
                                            "};"); //submit*/
                                    pageSubmitted = true;
                                    pd.dismiss();
                                }
                            }
                        }
                    } else if (buttonClick.equals("time")) {
                        pd.dismiss();
                        pageSubmitted = false;
                    } else if (buttonClick.equals("catalog")) {
                        if (url.contains("http://www.bath.ac.uk/library")) {
                            pd.dismiss();
                        }
                        pageSubmitted = false;
                   } else if (buttonClick.equals("queue") || buttonClick.equals("sport") || buttonClick.equals("radio")
                            || buttonClick.equals("news") || url.equals("https://mytimetable.bath.ac.uk/")) {
                        //if you need to log in
                        pd.dismiss();
                        pageSubmitted = true;
                    } else {
                        //mytable (unless saved in cache ^)
                        webView.loadUrl("javascript: {" +
                                "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                "document.getElementById('btn-submit').click();" +//login-button - only for PC login
                                "};"); //submit
                        pageSubmitted = true;
                        pd.dismiss();
                    }
                    //}
                } else if(pageSubmitted) {
                    if (buttonClick.equals("login")) {
                        if (url.contains("http://library.bath.ac.uk/uhtbin/bath/UNIV-BATH/login/pin?ticket=")) {
                            //validated username, save credentials & switch to homepage
                            //saveToSD(userUsername, userPassword, "verified");
                            //setContentView(R.layout.homepage);
                            //currentPageNumber = homeNum;
                            setTitle("Home");

                            System.out.println("saved Username & Password");
                            /*LOADING CREDENTIALS
                            String[] credentials = loadFromSD().split(" ");//will save credentials to savedUsername/Password
                            if(credentials.length>=2) {
                                savedUsername = credentials[0];
                                savedPassword = credentials[1];
                            }else{
                                Toast.makeText(MainActivity.this, "No saved credentials", Toast.LENGTH_SHORT).show();
                            }*/
                            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                    webView.getWindowToken(), 0);

                            //get PIN number
                        } else {
                            //not validated, so stay on home page
                            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                    webView.getWindowToken(), 0);
                            Toast.makeText(Library.this, "Incorrect Username and/or Password", Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();
                    } else if (buttonClick.equals("pin")) {
                        pd.dismiss();
                        System.out.println("pin: page submitted, pd.dismiss()");

                        TextView t = (TextView) findViewById(R.id.libraryPin);
                        URL url1 = null;
                        HttpURLConnection connection;
                        /*
                        try {
                            //IMPLEMENT TO GET PIN FROM WEBSITE
                            url1 = new URL("http://library.bath.ac.uk/uhtbin/bath/UNIV-BATH/login/pin?ticket");
                            connection = (HttpURLConnection)url1.openConnection();
                            connection.setRequestProperty("User-Agent", "");
                            connection.setRequestMethod("GET");
                            connection.setDoInput(true);
                            connection.connect()
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/

                        /*StringBuilder str = new StringBuilder();
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(webs, "iso-8859-1"));
                            String line;
                            str.append("PIN:");
                            while((line = reader.readLine()) != null) {
                                //if(line.contains("PIN is")){
                                str.append(line);
                                //}
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        t.setText(str);*/
                        pageSubmitted = false;
                    } else if (buttonClick.contains("email")) {
                        //these conditions are used to distinguish if the user has OWA or Webmail account and goes back to homepage
                        if (url.contains("https://webmail.bath.ac.uk/imp/mailbox")) {
                            pd.dismiss();
                        } else if (!(url.contains("https://mail.bath.ac.uk/owa/")) && (buttonClick.equals("emailNew"))) {
                            pd.dismiss();
                            Toast.makeText(Library.this, "Email not migrated, opening Bath Webmail", Toast.LENGTH_SHORT).show();
                            webView.loadUrl("https://webmail.bath.ac.uk/imp/mailbox");
                            libraryViewer(webView, "emailOld");
                        }
                        if (url.equals("https://mail.bath.ac.uk/owa/")) {
                            pd.dismiss();
                            pageSubmitted = true;
                        }else if (url.contains("https://mail.bath.ac.uk/owa/")) {
                            webView.loadUrl("javascript: {" +
                                    "document.getElementById('username').value = '" + GlobalUsername + "';" +
                                    "document.getElementById('password').value = '" + GlobalPassword + "';" +
                                    "document.getElementsByClassName('login-button')[0].click();" + // - for TABLET login?
                                    "};"); //submit*/
                            pageSubmitted = true;
                            pd.dismiss();
                        } else if (!(url.contains("https://webmail.bath.ac.uk/imp/mailbox")) && (buttonClick.equals("emailOld"))) {
                            Toast.makeText(Library.this, "Email migrated, use Outlook Web App", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }else{
                            webView.loadUrl("https://mail.bath.ac.uk/owa/");
                        }
                    } else if (buttonClick.equals("borrow") || buttonClick.equals("mytable") || buttonClick.equals("atm")) {
                        pd.dismiss();
                        pageSubmitted = false;
                    }
                }
                if (url.contains("http://library.bath.ac.uk/uhtbin")) {
                    pd.dismiss();
                }else if(url.contains("http://bath-ac-primo.hosted.exlibrisgroup.com/primo_library/libweb/action/myAccountMenu.do?vid=44BAT_VU1&institution=44BAT&samlLogin=true&dscnt=0&dstmp=1464611485652&fromLogin=true")){
                    pd.dismiss();
                }
            }
        });
    }

}
