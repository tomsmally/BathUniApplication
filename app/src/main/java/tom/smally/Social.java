package tom.smally;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Social extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, club> clubInfoHashMap = new HashMap<String, club>();
    private ViewFlipper viewFlipper;
    Boolean clubPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        setTitle("Social");
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper3);
        /*viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        if(clubPageOpen) {
            clubPageOpen = false;
            viewFlipper.showPrevious();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social, menu);
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

        if (id == R.id.nav_camara) {
            Intent intent = new Intent(Social.this, MainActivity.class);
            Social.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Social.this, Popular.class);
            Social.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Social.this, Email.class);
            Social.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Social.this, Map.class);
            Social.this.startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void bridgeClick(View v)
    {
        club clubInfo = new club();
        clubInfo.name = "The Second Bridge";
        clubInfo.clubOpen = false;
        clubInfo.clubDrawable = R.drawable.bridgeimg;
        clubInfo.url = "http://www.secondbridge.co.uk";
        clubInfo.ticketInfo = "On the door.";
        clubInfo.ticketLink = "https://gotag.in/events";
        clubInfo.openingTimes = getClubTimes("The Second Bridge");
        clubInfo.mapLink = "https://www.google.co.uk/maps/place/The+Second+Bridge/@51.3790581,-2.3571993,17z/data=!3m1!4b1!4m2!3m1!1s0x48718111b8294f5b:0x59b6159a72b60c84";
        clubInfo.address = "10 Manvers St, Bath, Somerset BA1 1PQ";
        clubInfo.description = "No Desc.";
        clubInfo.drinks = "No Drinks.";
        clubInfo.phone = "01225 464 449";
        clubInfo.Facebook = "https://www.facebook.com/SecondBridgeNightclub";
        clubInfo.Twitter = "https://twitter.com/thesecondbridge";
        clubInfo.Instagram = "";
        clubInfo.Google = "https://plus.google.com/116425617351875144199";
        clubInfoHashMap.put("The Second Bridge",clubInfo);
        clubLoadText2(clubInfoHashMap.get("The Second Bridge"));
    }

    public void weirClick(View v)
    {
        club clubInfo = new club();
        clubInfo.name = "Weir Lounge";
        clubInfo.clubOpen = false;
        clubInfo.clubDrawable = R.drawable.weirimg;
        clubInfo.url = "https://licklist.co.uk/the-weir-lounge-bath";
        clubInfo.ticketInfo = "On the door.";
        clubInfo.ticketLink = "https://gotag.in/events";
        clubInfo.openingTimes = getClubTimes("Weir Lounge");
        clubInfo.mapLink = "https://www.google.co.uk/maps/place/The+Second+Bridge/@51.3790581,-2.3571993,17z/data=!3m1!4b1!4m2!3m1!1s0x48718111b8294f5b:0x59b6159a72b60c84";
        clubInfo.address = "Spring Gardens Rd, Bath, Avon BA2 6PA";
        clubInfo.description = "No Desc.";
        clubInfo.drinks = "No Drinks.";
        clubInfo.phone = "01225 447 187";
        clubInfo.Facebook = "https://www.facebook.com/TheWeirLounge/";
        clubInfo.Twitter = "https://twitter.com/weirloungebath";
        clubInfo.Instagram = "";
        clubInfo.Google = "";
        clubInfoHashMap.put("Weir Lounge",clubInfo);
        clubLoadText2(clubInfoHashMap.get("Weir Lounge"));
    }

    public void xlClick(View v)
    {
        club clubInfo = new club();
        clubInfo.name = "The Second Bridge";
        clubInfo.clubOpen = false;
        clubInfo.clubDrawable = R.drawable.bridgeimg;
        clubInfo.url = "http://www.secondbridge.co.uk";
        clubInfo.ticketInfo = "On the door.";
        clubInfo.ticketLink = "https://gotag.in/events";
        clubInfo.openingTimes = getClubTimes("The Second Bridge");
        clubInfo.mapLink = "https://www.google.co.uk/maps/place/The+Second+Bridge/@51.3790581,-2.3571993,17z/data=!3m1!4b1!4m2!3m1!1s0x48718111b8294f5b:0x59b6159a72b60c84";
        clubInfo.address = "10 Manvers St, Bath, Somerset BA1 1PQ";
        clubInfo.description = "No Desc.";
        clubInfo.drinks = "No Drinks.";
        clubInfo.phone = "01225 464 449";
        clubInfo.Facebook = "https://www.facebook.com/SecondBridgeNightclub";
        clubInfo.Twitter = "https://twitter.com/thesecondbridge";
        clubInfo.Instagram = "";
        clubInfo.Google = "https://plus.google.com/116425617351875144199";
        clubInfoHashMap.put("The Second Bridge",clubInfo);
        clubLoadText2(clubInfoHashMap.get("The Second Bridge"));
    }

    public openingTimes getClubTimes(String club)
    {
        if(club.equals("The Second Bridge"))
        {
            openingTimes clubTimes = new openingTimes();
            clubTimes.monOpen = "10:00pm";
            clubTimes.monClosed = "2:00am";
            clubTimes.tueOpen = "9:00pm";
            clubTimes.tueClosed = "3:00am";
            clubTimes.wedOpen = "";
            clubTimes.wedClosed = "";
            clubTimes.thuOpen = "9:00pm";
            clubTimes.thuClosed = "3:00am";
            clubTimes.friOpen = "9:00pm";
            clubTimes.friClosed = "3:00am";
            clubTimes.satOpen = "9:00pm";
            clubTimes.satClosed = "3:00am";
            clubTimes.sunOpen = "";
            clubTimes.sunClosed = "";
            return clubTimes;
        }
        else if(club.equals("Weir Lounge")) {
            openingTimes clubTimes = new openingTimes();
            clubTimes.monOpen = "10:00pm";
            clubTimes.monClosed = "2:00am";
            clubTimes.tueOpen = "9:00pm";
            clubTimes.tueClosed = "3:00am";
            clubTimes.wedOpen = "";
            clubTimes.wedClosed = "";
            clubTimes.thuOpen = "9:00pm";
            clubTimes.thuClosed = "3:00am";
            clubTimes.friOpen = "9:00pm";
            clubTimes.friClosed = "3:00am";
            clubTimes.satOpen = "9:00pm";
            clubTimes.satClosed = "3:00am";
            clubTimes.sunOpen = "";
            clubTimes.sunClosed = "";
            return clubTimes;
        }else{
            //empty
            return new openingTimes();
        }
    }

    public void clubLoadText2(final club clubInfo)
    {
        viewFlipper.showNext();
        clubPageOpen = true;
        setTitle(clubInfo.name);

        ImageView clubImg = (ImageView) findViewById(R.id.clubLogo);
        TextView clubStatus = (TextView) findViewById(R.id.clubStatus);
        TextView officeInfo = (TextView) findViewById(R.id.officeInfo);
        TextView ticketLink = (TextView) findViewById(R.id.ticketLink);
        TextView openingTimes = (TextView) findViewById(R.id.openingTimes);
        TextView location = (TextView) findViewById(R.id.location);
        TextView clubText = (TextView) findViewById(R.id.clubText);
        TextView clubDrinks = (TextView) findViewById(R.id.clubDrinks);
        TextView contactText = (TextView) findViewById(R.id.contact);
        TextView emailText = (TextView) findViewById(R.id.email);
        ImageView fb =(ImageView) findViewById(R.id.fbimg);
        ImageView tw =(ImageView) findViewById(R.id.twimg);
        ImageView in =(ImageView) findViewById(R.id.inimg);
        ImageView ggl =(ImageView) findViewById(R.id.gglimg);

        clubImg.setImageDrawable(ContextCompat.getDrawable(Social.this, clubInfo.clubDrawable));
        officeInfo.setText(Html.fromHtml("<b>Tickets: "+clubInfo.ticketInfo+"</b>"));//bold & break"
        ticketLink.setText(Html.fromHtml("<a href=\""+clubInfo.ticketLink+"\">Tickets</a>"));
        //openingTimes.setText(Html.fromHtml("<b>Opening times:</b><br/>" + oT));
        location.setText(Html.fromHtml("<b>Address:</b><br/>"+ clubInfo.address));
        clubText.setText(Html.fromHtml("<b>About:</b><br/>"+ clubInfo.description));
        clubDrinks.setText(Html.fromHtml("<b>Drink deals:</b><br/>" + clubInfo.drinks));

        if(!clubInfo.phone.isEmpty()){
            final String phoneNum = clubInfo.phone.substring(0,13);
            contactText.setText(Html.fromHtml("<b>Contact:</b><br/> <a href=\"" + phoneNum + "\">" + phoneNum + "</a>"));
            contactText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
                    startActivity(browserIntent);
                }
            });
        }else{
            contactText.setText(Html.fromHtml("<b>Contact:</b>"));
        }
        if(!clubInfo.email.isEmpty()){
            emailText.setText(clubInfo.email);
        }else{
            emailText.setVisibility(View.GONE);
        }
        //set club image OnClick listener
        if(!clubInfo.url.equals("")) {
            clubImg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInfo.url)));
                }
            });
        }
        if(!clubInfo.Facebook.equals("")) {
            fb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInfo.Facebook)));
                }
            });
        }else{
            fb.setVisibility(View.GONE);
        }
        if(!clubInfo.Twitter.equals("")) {
            tw.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInfo.Twitter)));
                }
            });
        }else{
            tw.setVisibility(View.GONE);
        }
        if(!clubInfo.Instagram.equals("")) {
            in.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInfo.Instagram)));
                }
            });
        }else{
            in.setVisibility(View.GONE);
        }
        if(!clubInfo.Google.equals("")) {
            ggl.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInfo.Google)));
                }
            });
        }else{
            ggl.setVisibility(View.GONE);
        }
    }

    /*
        Loads club into page
     */
    public void clubLoadText(String clubName, String status,int imageSrc, final String imgLink, String info, String tLink, String oT, String address, String text,
                             String drinks, String contacts,String emailStr, final String clubFbLink, final String clubTwLink, final String clubInLink, final String clubGglLink)
    {
        setContentView(R.layout.content_social);
        setTitle(clubName);

        ImageView clubImg = (ImageView) findViewById(R.id.clubLogo);
        TextView clubStatus = (TextView) findViewById(R.id.clubStatus);
        TextView officeInfo = (TextView) findViewById(R.id.officeInfo);
        TextView ticketLink = (TextView) findViewById(R.id.ticketLink);
        TextView openingTimes = (TextView) findViewById(R.id.openingTimes);
        TextView location = (TextView) findViewById(R.id.location);
        TextView clubText = (TextView) findViewById(R.id.clubText);
        TextView clubDrinks = (TextView) findViewById(R.id.clubDrinks);
        TextView contactText = (TextView) findViewById(R.id.contact);
        TextView emailText = (TextView) findViewById(R.id.email);
        ImageView fb =(ImageView) findViewById(R.id.fbimg);
        ImageView tw =(ImageView) findViewById(R.id.twimg);
        ImageView in =(ImageView) findViewById(R.id.inimg);
        ImageView ggl =(ImageView) findViewById(R.id.gglimg);

        clubImg.setImageDrawable(ContextCompat.getDrawable(Social.this, imageSrc));
        if(status.equals("CLOSED")) {
            clubStatus.setText(Html.fromHtml("<font color=\"red\">" + status + "</font>"));//status="closed" -> red
        }else{
            clubStatus.setText(Html.fromHtml("<font color=\"green\">" + status + "</font>"));//status="open" -> green
        }
        officeInfo.setText(Html.fromHtml("<b>Tickets: "+info+"</b>"));//bold & break"
        ticketLink.setText(Html.fromHtml("<a href=\""+tLink+"\">Tickets</a>"));
        openingTimes.setText(Html.fromHtml("<b>Opening times:</b><br/>" + oT));
        location.setText(Html.fromHtml("<b>Address:</b><br/>"+address));
        clubText.setText(Html.fromHtml("<b>About:</b><br/>"+text));
        clubDrinks.setText(Html.fromHtml("<b>Drink deals:</b><br/>" + drinks));

        if(!contacts.isEmpty()){
            final String phoneNum = contacts.substring(0,13);
            contactText.setText(Html.fromHtml("<b>Contact:</b><br/> <a href=\"" + phoneNum + "\">" + phoneNum + "</a>"));
            contactText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
                    startActivity(browserIntent);
                }
            });
        }else{
            contactText.setText(Html.fromHtml("<b>Contact:</b>"));
        }

        if(!emailStr.isEmpty()){
            emailText.setText(emailStr);
        }else{
            emailText.setVisibility(View.GONE);
        }
        //set club image OnClick listener
        if(!imgLink.equals("")) {
            clubImg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imgLink)));
                }
            });
        }
        if(!clubFbLink.equals("")) {
            fb.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubFbLink)));
                }
            });
        }else{
            fb.setVisibility(View.GONE);
        }
        if(!clubTwLink.equals("")) {
            tw.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubTwLink)));
                }
            });
        }else{
            tw.setVisibility(View.GONE);
        }
        if(!clubInLink.equals("")) {
            in.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubInLink)));
                }
            });
        }else{
            in.setVisibility(View.GONE);
        }
        if(!clubGglLink.equals("")) {
            ggl.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clubGglLink)));
                }
            });
        }else{
            ggl.setVisibility(View.GONE);
        }

        ticketLink.setMovementMethod(LinkMovementMethod.getInstance());
        location.setMovementMethod(LinkMovementMethod.getInstance());
    }

}

