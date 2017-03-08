package tom.smally;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Food extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, Restaurant> restaurantHashMap = new HashMap<String, Restaurant>();

    ViewFlipper viewFlipper;
    int viewNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(viewNum == 2 || viewNum == 3){
            viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper2);
            viewFlipper.showPrevious();
            viewNum --;
            setTitle("Food");
            //unregisterReceiver(timeChangedReceiver);
            //System.out.println("reciever unregistered");
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food, menu);
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

    public void onCampRestClick(View v)
    {
        setTitle("Campus Restaurants");
        restaurantLoad("campus");
    }
    public void onCityRestClick(View v)
    {
        setTitle("City Restaurants");
        restaurantLoad("city");
    }

    public void restaurantLoad(String location)
    {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper2);
        viewFlipper.showNext();
        viewNum++;

        restaurantHashMap.put("LimeTree", new Restaurant("Lime Tree", false, "limetimes"));
        restaurantHashMap.put("Fresh", new Restaurant("Fresh", false, "freshtimes"));
        restaurantHashMap.put("FourW", new Restaurant("Four West Cafe", false, "fourwtimes"));
        restaurantHashMap.put("Parade", new Restaurant("Parade Bar", false, "paradetimes"));

        //create array list Of TextViews
        //create array list of ImageViews
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        for(int x = 0; x <= 5; x++){
            ImageView i = (ImageView) findViewById(R.id.imageView1+x);
            imageViews.add(i);
        }

        ArrayList<TextView> textViews = new ArrayList<TextView>();
        for(int x = 0; x <= 5; x++){
            TextView t = (TextView) findViewById(R.id.restText1+x);
            textViews.add(t);
        }




        /**
         *
         *
         *
         *
         * NEW CODE!!!! LOADS THE RESTAURANT AND PUTS INTO THE UI!!!!!!!
         *
         *
         *
         *
         *
         */
        for (String key : restaurantHashMap.keySet()) {

            Integer restNumber = 0;

            Restaurant restaurant = restaurantHashMap.get(key);
            Integer timeToClose = getTimeToClose(restaurant.restFileName);
            restaurant.setRestTimeToClose(timeToClose);
            //restaurantHashMap.remove(key);
            String status = "<font color=\"red\">CLOSED</font>";

            if (timeToClose > 0) {
                restaurant.restOpenStatus = true;
                status = "<font color=\"#47a842\">OPEN</font>";
            }
            restaurantHashMap.put(key, restaurant);

            String closeString = convertMinsToHourMins(timeToClose);

            //This IS NOT the correct closingLabel to use for the status!
            TextView closingLabel = (TextView) findViewById(R.id.restaurantText);
            closingLabel.setText(Html.fromHtml("Lime: " + status + "<i><small><font color=\"#c5c5c5\">" + " (" + closeString + ") " + "</font></small></i>" + ", "));

            ImageView i = imageViews.get(restNumber);
            TextView t = textViews.get(restNumber);
            i.setImageResource(restaurant.restDrawable);
            t.setText(restaurant.restTitle);
            t.append(" - "+ Html.fromHtml(status + "<i><small><font color=\"#c5c5c5\">" + " (" + closeString + ") " + "</font></small></i>" + ", "));

            restNumber ++;
        }

        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */


        /**
         *
         *  DEPRECIATED CODE TO ADD RESTAURANT TO THE UI!!!!
         *
         *
         *  ^^^^ COPY THE UI SPECIFICATION INFORMATION INTO THE CODE ABOVE ^^^^
         *
         *
         *
         * //add time to close options for each restaurant
         int aTimeToClose;
         int bTimeToClose;
         int cTimeToClose;
         if(location.equals("city")) {
         aTimeToClose = getTimeToClose("freshtimes"); //must be int to check that >0
         bTimeToClose = getTimeToClose("fourwtimes");
         cTimeToClose = getTimeToClose("paradetimes");
         }else {//city restaurant load times - change Strings
         aTimeToClose = getTimeToClose("limetimes"); //must be int to check that >0
         bTimeToClose = getTimeToClose("fourwtimes");
         cTimeToClose = getTimeToClose("paradetimes");
         }

         String limeStatus = "<font color=\"red\">CLOSED</font>";
         String fourWStatus = "<font color=\"red\">CLOSED</font>";
         String paradeStatus = "<font color=\"red\">CLOSED</font>";
         //add 'Green' "Open" or 'Red' Closed if getTimeToClose <0 or >0
         if(aTimeToClose > 0){
         restaurantStatus.put("LimeTree",true);
         limeStatus= "<font color=\"#47a842\">OPEN</font>";
         }
         if(bTimeToClose > 0){
         restaurantStatus.put("FourW",true);
         fourWStatus = "<font color=\"#47a842\">OPEN</font>";
         }
         if(cTimeToClose > 0){
         restaurantStatus.put("ParadeBar",true);
         paradeStatus = "<font color=\"#47a842\">OPEN</font>";
         }

         //Lime: OPEN (3hours), Parade: CLOSED ().
         TextView closingLabel = (TextView) findViewById(R.id.restaurantText);
         if(location.equals("campus")) {
         closingLabel.setText(Html.fromHtml("Lime: " + limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         closingLabel.append(Html.fromHtml("4W Cafe: " + fourWStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + fourWCloseLine + ") " + "</font></small></i>" + ", "));
         closingLabel.append(Html.fromHtml("Parade: " + paradeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + paradeCloseLine + ") " + "</font></small></i>" + ", "));
         }else{//city
         closingLabel.setText(Html.fromHtml("Nandos: " + limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         closingLabel.append(Html.fromHtml("GBK: " + fourWStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + fourWCloseLine + ") " + "</font></small></i>" + ", "));
         closingLabel.append(Html.fromHtml("Sotto Sotto: " + paradeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + paradeCloseLine + ") " + "</font></small></i>" + ", "));
         }

         //convert minutes back to hours/mins
         String limeCloseLine = convertMinsToHourMins(aTimeToClose);
         String fourWCloseLine = convertMinsToHourMins(bTimeToClose);
         String paradeCloseLine = convertMinsToHourMins(cTimeToClose);


         if(location.equals("city")) {
         //nandos, gbk, sotto, King of Wessex (Wetherpoons), Belushis, The Cork, Peri Peri Sizzler, Same-same but different.
         TextView r = (TextView) findViewById(R.id.restaurantText);
         r.setVisibility(View.GONE);

         ImageView i = (ImageView) findViewById(R.id.imageView1);
         TextView t = (TextView) findViewById(R.id.restText1);
         i.setImageResource(R.drawable.nandos);
         t.setText("Nandos - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));

         i = (ImageView) findViewById(R.id.imageView2);
         t = (TextView) findViewById(R.id.restText2);
         i.setImageResource(R.drawable.gbk);
         t.setText("GBK - ");
         t.append(Html.fromHtml(fourWStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + fourWCloseLine + ") " + "</font></small></i>" + ", "));

         i = (ImageView) findViewById(R.id.imageView3);
         t = (TextView) findViewById(R.id.restText3);
         i.setImageResource(R.drawable.sotto);
         t.setText("Sotto Sotto - ");
         t.append(Html.fromHtml(paradeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + paradeCloseLine + ") " + "</font></small></i>" + ", "));

         i = (ImageView) findViewById(R.id.imageView4);
         t = (TextView) findViewById(R.id.restText4);
         i.setImageResource(R.drawable.sotto);
         t.setText("King of Wessex (Wetherspoons) - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));

         i = (ImageView) findViewById(R.id.imageView5);
         t = (TextView) findViewById(R.id.restText5);
         i.setImageResource(R.drawable.sotto);
         t.setText("Belushi's - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));

         i = (ImageView) findViewById(R.id.imageView6);
         t = (TextView) findViewById(R.id.restText6);
         i.setImageResource(R.drawable.sotto);
         t.setText("The Cork -");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         }
         else if(location.equals("campus"))
         {
         TextView r = (TextView) findViewById(R.id.restaurantText);
         r.setVisibility(View.GONE);

         TextView t = (TextView) findViewById(R.id.restText1);
         t.setText("Lime - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         ImageView i = (ImageView) findViewById(R.id.imageView1);
         i.setImageResource(R.drawable.limeopen);

         t = (TextView) findViewById(R.id.restText2);
         t.setText("4 West Cafe - ");
         t.append(Html.fromHtml(fourWStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + fourWCloseLine + ") " + "</font></small></i>" + ", "));
         i = (ImageView) findViewById(R.id.imageView2);
         i.setImageResource(R.drawable.fourwopen);

         t = (TextView) findViewById(R.id.restText3);
         t.setText("Parade - ");
         t.append(Html.fromHtml(paradeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + paradeCloseLine + ") " + "</font></small></i>" + ", "));
         i = (ImageView) findViewById(R.id.imageView3);
         i.setImageResource(R.drawable.paradeopen);

         t = (TextView) findViewById(R.id.restText4);
         t.setText("King of Wessex (Wetherspoons) - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         i = (ImageView) findViewById(R.id.imageView4);
         i.setImageResource(R.drawable.nandos);

         t = (TextView) findViewById(R.id.restText5);
         t.setText("Belushi's - ");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         i = (ImageView) findViewById(R.id.imageView5);
         i.setImageResource(R.drawable.nandos);

         t = (TextView) findViewById(R.id.restText6);
         t.setText("The Cork -");
         t.append(Html.fromHtml(limeStatus + "<i><small><font color=\"#c5c5c5\">" + " (" + limeCloseLine + ") " + "</font></small></i>" + ", "));
         i = (ImageView) findViewById(R.id.imageView6);
         i.setImageResource(R.drawable.nandos);
         }

         /**
         *
         *
         * ^^^^ DEPRECIATED CODE!!! ^^^^
         *
         *
         *
         */


        //end of restaurantLoad method!
    }


        public void onPlaceClick(View v)
        {
            setTitle("Fresh");
            viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper2);
            viewFlipper.showNext();
            viewNum ++;
            clubLoadText("Fresh","OPEN",R.drawable.freshimg,"","","",
                "Monday &#160;&#160;&#160; 7:30 a.m. – 10:30 p.m.<br/>" +
                        "Tuesday &#160;&#160;&#160; 7:30 a.m. – 10:30 p.m.<br/>" +
                        "Wednesday &#160;&#160;&#160; 7:30 a.m. – 10:30 p.m.<br/>" +
                        "Thursday &#160;&#160;&#160; 7:30 a.m. – 10:30 p.m.<br/>" +
                        "Friday &#160;&#160;&#160; 7:30 a.m. - 10:30 p.m.<br/>" +
                        "Saturday &#160;&#160;&#160; 8:30 a.m. – 10:30 p.m.<br/>" +
                        "Sunday &#160;&#160;&#160; 11:00 a.m. – 5:00 p.m.",
                "Shaftsbury Road, BA2","","","","","","","","");
        //Food Page: Fresh - 2 Locations Open (closes 30mins & 3hrs)
        //Club Page: tabhost to switch between the different:
        // - don't use club logo, instead just change title to Fresh - material design
        // - shop (/restaurant) locations
        // - opening times
        // - distance from location
        // - images of what they look like from the outside
    }

    public void clubLoadText(String clubName, String status,int imageSrc, final String imgLink, String info, String tLink, String oT, String address, String text,
                             String drinks, String contacts,String emailStr, final String clubFbLink, final String clubTwLink, final String clubInLink, final String clubGglLink)
    {
        //search: club1
        if(clubName.equals("Fresh")){

        }else{
            //currentPageNumber = clubNum;
        }
        ImageView clubImg = (ImageView) findViewById(R.id.clubLogo);
        clubImg.setImageDrawable(ContextCompat.getDrawable(this, imageSrc));
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
    /**
     * Fully works - could add more if statements
     */
    public String convertMinsToHourMins(int timeToClose)
    {
        if(timeToClose>= 120 || timeToClose<60 ) {
            return (timeToClose / 60) + "hrs " + (timeToClose % 60) + "mins";
        }else {
            return (timeToClose / 60) + "hr " + (timeToClose % 60) + "mins";
        }
    }
    
    @SuppressWarnings("resource")
    public int getTimeToClose(String restaurantID)
    {
        String fileName = restaurantHashMap.get(restaurantID).restFileName;

        /*String fileName = "limetimes";
        switch (restaurantID){
            case "LimeTree":
                fileName = "limetimes";
                break;
            case "FourW":
                fileName = "fourwtimes";
                break;
            case "Parade":
                fileName = "paradetimes";
                break;
            case "Fresh":
                fileName = "freshtimes";
                break;
        }*/

        String[] times = new String[6];//week,sat&sun
        try {
            InputStream in = getResources().openRawResource(
                    getResources().getIdentifier("raw/" + fileName,
                            "raw", getPackageName()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            reader.readLine();//reads "term" or "holiday" - no need to set value to string
            String timeLine = reader.readLine();
            times[0] = timeLine.replaceAll("Week ", "");
            timeLine = reader.readLine();
            times[1] = timeLine.replaceAll("Sat ", "");
            timeLine = reader.readLine();
            times[2] = timeLine.replaceAll("Sun ", "");

        } catch (IOException | NullPointerException e) {
            System.out.println("time to close failed");
        }
        int timeToClose = 0;
        //get current time/ date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        SimpleDateFormat sdfint = new SimpleDateFormat("hhmm");
        String curTime = String.valueOf(sdf.format(cal.getTime()));//correctly gets time:"1635"
        int currentTime = (Integer.parseInt(curTime.substring(0, 2)) * 60) + Integer.parseInt(curTime.substring(2, 4));
        //int currentTime = ((int) curTime.charAt(0)+(int)curTime.charAt(1))*60 + (int)(curTime.charAt(2) + curTime.charAt(3));
        int curDayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);//gets day = daynumber +1 (for some reason)

        //get close time using day of the week
        String closeTime = null;
        //x = 0 (if termtime) if (holiday) { x = 3 }
        if (curDayOfTheWeek <= 6) {
            closeTime = times[0];
        } else if (curDayOfTheWeek == 7) {
            closeTime = times[1];
        } else if (curDayOfTheWeek == 8) {
            closeTime = times[2];
        }
        int closingTimeInt = (Integer.parseInt(closeTime.substring(5, 7)) * 60) + Integer.parseInt(closeTime.substring(7, 9));
        int openingTimeInt = (Integer.parseInt(closeTime.substring(0, 2)) * 60) + Integer.parseInt(closeTime.substring(2, 4));

        //if openingTimeInt - currentTime > 0 then, shop not open, so time to close is
        timeToClose = closingTimeInt - currentTime;
        return timeToClose;
    }

}
