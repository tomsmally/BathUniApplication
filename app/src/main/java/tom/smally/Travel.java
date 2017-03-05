package tom.smally;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Travel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String GlobalEmail;
    String GlobalUsername;
    String GlobalPassword;

    Timer timer;
    TimerTask task;
    ViewFlipper viewFlipper;
    int viewNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Intent intent = getIntent();
        GlobalEmail = intent.getStringExtra("EMAIL");
        GlobalUsername = intent.getStringExtra("USERNAME");
        GlobalPassword = intent.getStringExtra("PASSWORD");

        setTitle("Travel");

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
        } else if(viewNum == 2){
            viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
            viewFlipper.showPrevious();
            viewNum = 1;
            unregisterReceiver(timeChangedReceiver);
            System.out.println("reciever unregistered");
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.travel, menu);
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
            // Handle the home action
            Intent intent = new Intent(Travel.this, MainActivity.class);
            Travel.this.startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            //Load popular page
            Intent intent = new Intent(Travel.this, Popular.class);
            intent.putExtra("EMAIL",GlobalEmail);
            intent.putExtra("USERNAME",GlobalUsername);
            intent.putExtra("PASSWORD",GlobalPassword);
            Travel.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Travel.this, Email.class);
            intent.putExtra("EMAIL",GlobalEmail);
            intent.putExtra("USERNAME",GlobalUsername);
            intent.putExtra("PASSWORD",GlobalPassword);
            Travel.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Travel.this, Map.class);
            Travel.this.startActivity(intent);
        } else if (id == R.id.nav_share) {
            //Intent intent = new Intent(Travel.this, Travel.class);
            //Travel.this.startActivity(intent);
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onU1Click(View v){
        //set XML file to load timetable page with U1
        firstLoad("u1");
    }
    public void onWessexClick(View v){
        //set XML file to load timetable page with 18

    }
    public void onOldU1Click(View v){
        //set XML file to load timetable page with U1
    }
    public void onOldWessexClick(View v){
        //set XML file to load timetable page with U18 old
    }
    public int getTimeNow(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        return Integer.parseInt(dateFormat.format(calendar.getTime()));
    }

    public void firstLoad(final String route)
    {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.showNext();//load bus times XML (content_travel2)
        viewNum = 2;

        TextView busEstimates = (TextView) findViewById(R.id.busEstimates);
        int timeNow = getTimeNow();
        //must use to/from locations, because we cant have one estimate for the whole journey.
        if (timeNow > 745 && timeNow < 915) {
            busEstimates.setText("Journey estimate: 40mins");
        } else if (timeNow >= 915 && timeNow < 945) {
            busEstimates.setText("Journey estimate: 30mins");
        } else if (timeNow >= 945 && timeNow < 1015) {
            busEstimates.setText("Journey estimate: 30mins");
        } else if (timeNow >= 1015 && timeNow < 1045) {
            busEstimates.setText("Journey estimate: 22mins");
        } else if (timeNow >= 1045 && timeNow < 1115) {
            busEstimates.setText("Journey estimate: 25mins");
        } else if (timeNow >= 1115 && timeNow < 1615) {
            busEstimates.setText("Journey estimate: 30mins");
        } else if (timeNow >= 1615 && timeNow < 1815) {
            busEstimates.setText("Journey estimate: 45mins");
        } else {
            busEstimates.setText("Journey estimate: 25mins");
        }
        //WebView imaging method:
        final WebView web1 = (WebView) findViewById(R.id.busWebView);
        web1.getSettings().setJavaScriptEnabled(true);
        web1.getSettings().setBuiltInZoomControls(true);
        web1.getSettings().setSupportZoom(true);
        web1.getSettings().setDisplayZoomControls(false);//removes zoom control-buttons - allows pinch-zoom
        web1.getSettings().setUseWideViewPort(true);
        web1.setInitialScale(1);
        if (route.equals("18")) {
            setTitle("18 - First");
            web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='first18termb.png' />", "text/html", "utf-8", null);
        } else {
            setTitle("U1 - First");
            web1.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='u1term.png' />", "text/html", "utf-8", null);
        }

        List<String> managementArray = new ArrayList<String>();
        if (route != "u1") {
            managementArray.add("Oldfield Park, Lower Oldfield Park");
            managementArray.add("Bus Station, Dorchester St Bn");
            managementArray.add("University of Bath, Bus Station dep");
        } else {
            managementArray.add("Oldfield Park, Junction Road");
            managementArray.add("Bath City Centre, Dorchester Street Bn");
            managementArray.add("University of Bath, University of Bath dep");
            managementArray.add("Bath City Centre, Dorchester Street Bh dep");
        }

        Spinner locations = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                Travel.this, android.R.layout.simple_spinner_item, managementArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locations.setAdapter(adapter1);
        locations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean selectAvailable = false;

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("new location selected");

                updateBusText(route);
                registerReceiver(timeChangedReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
                System.out.println("reciever registered");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
        /*final TextView closingLabel = (TextView) findViewById(R.id.departures);
        //frequent time updates and calculations (5 secs) for greater accuracy of bus times
        timer = new Timer();
        task = new TimerTask() {
            int lastTime = 0;
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                final int newTime = Integer.parseInt(sdf2.format(cal.getTime()));
                if (newTime > lastTime)
                {
                    runOnUiThread(new Runnable() {
                        //Runs on UI updater thread, so that closingLabel can be edited after the thread has finished loading the UI:
                        @Override
                        public void run() {
                            //needs to have a catch statement or something to stop this calling once the view has been changed
                            //(use back click or change of contentView to handle cancellation of this timer task)
                            // cancellation also helps with RAM speeds (only calling timer when view is visible-need
                            // timer for after app has opened)
                            //This is probs cancelled when the change of Class occurs
                            try {
                                if(route.equals("18")) {//remove this if, replace by just passing route instead
                                    closingLabel.setText(latestBusTime("first18times"));//change text filename here
                                }else{
                                    closingLabel.setText(latestBusTime("u1times2"));//change text filename here
                                }
                            }catch (Exception e){
                                System.out.println("No longer 18/U1");
                            }

                        }
                    });
                    lastTime = newTime;
                }
            }
        };
        timer.schedule(task, 0, 10000);*/


    public String latestBusTime(String busName)
    {
        final HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        InputStream in = getResources().openRawResource(
                getResources().getIdentifier("raw/" + busName, //first18times
                        "raw", getPackageName()));
        BufferedReader br = null;
        int size = 0;
        String line = "";
        try {
            br = new BufferedReader(new InputStreamReader(in));

            size = br.read();
            line = br.readLine();//remove first line (name of text file)

            //this can be done better - only add new lists to "map" when a bus stop is added...
            //add new array lists as number of bus stops increases ie. 11 instead of just 6
            ArrayList<String> one = new ArrayList<String>();
            ArrayList<String> two = new ArrayList<String>();
            ArrayList<String> three = new ArrayList<String>();
            ArrayList<String> four = new ArrayList<String>();
            ArrayList<String> five = new ArrayList<String>();
            ArrayList<String> six = new ArrayList<String>();
            ArrayList<String> seven = new ArrayList<String>();
            ArrayList<String> eight = new ArrayList<String>();
            ArrayList<String> nine = new ArrayList<String>();
            ArrayList<String> ten = new ArrayList<String>();

            if(busName.equals("first18times"))
            {
                /*Oldfield Park, Lower Oldfield Park
                        Bus Station, Dorchester St Bn
                        University of Bath, Bus Station arr
                        University of Bath, Bus Station dep
                        Bus Station, Dorchester St Bh
                        Oldfield Park, Lower Oldfield Park1*/
                while (!(line = br.readLine()).equals("End.")) {
                    if (line.equals("Notes:")) {
                        line = br.readLine();
                        one.add((line.replaceAll("Oldfield Park, Lower Oldfield Park", "")));
                        line = br.readLine();
                        two.add(line.replaceAll("Bus Station, Dorchester St Bn", ""));
                        line = br.readLine();
                        three.add(line.replaceAll("University of Bath, Bus Station arr", ""));
                        line = br.readLine();
                        four.add(line.replaceAll("University of Bath, Bus Station dep", ""));
                        line = br.readLine();
                        five.add((line.replaceAll("Bus Station, Dorchester St Bh", "")));
                        line = br.readLine();
                        six.add(line.replaceAll("Oldfield Park, Lower Oldfield Park1", ""));
                    }
                }
                /*
                map.put("Oldfield Park, Lower Oldfield Park", oldList);
                map.put("Bus Station, Dorchester St Bn", dorchList);
                map.put("University of Bath, Bus Station arr", bathListarr);
                map.put("University of Bath, Bus Station dep", bathListdep);
                map.put("Bus Station, Dorchester St Bh", dorchList1);
                map.put("Oldfield Park, Lower Oldfield Park1", oldList1);
                */
                map.put("Oldfield Park, Lower Oldfield Park", one);
                map.put("Bus Station, Dorchester St Bn", two);
                map.put("University of Bath, Bus Station arr", three);
                map.put("University of Bath, Bus Station dep", four);
                map.put("Bus Station, Dorchester St Bh", five);
                map.put("Oldfield Park, Lower Oldfield Park1", six);

            }
            else if(busName.equals("u1times2"))
            {
                /*
                Oldfield Park, Junction Road
                Bath City Centre, Dorchester Street Bn
                University of Bath, University of Bath dep
                Bath City Centre, Dorchester Street Bh dep
                 */
                while (!(line = br.readLine()).equals("End.")) {
                    if (line.equals("Notes:")) {
                        line = br.readLine();
                        one.add((line.replaceAll("Oldfield Park, Junction Road", "")));
                        line = br.readLine();
                        two.add(line.replaceAll("Bath City Centre, Dorchester Street Bn", ""));
                        line = br.readLine();
                        three.add(line.replaceAll("University of Bath, University of Bath dep", ""));
                        line = br.readLine();
                        four.add(line.replaceAll("Bath City Centre, Dorchester Street Bh dep", ""));
                    }
                }
                map.put("Oldfield Park, Junction Road", one);
                map.put("Bath City Centre, Dorchester Street Bn", two);
                map.put("University of Bath, University of Bath dep", three);
                map.put("Bath City Centre, Dorchester Street Bh dep", four);
            }
            else if(busName.equals("wu18time"))
            {
                while (!(line = br.readLine()).equals("End.")) {
                    if (line.equals("Notes:")) {
                        line = br.readLine();
                        one.add((line.replaceAll("University of Bath ", "")));
                        line = br.readLine();
                        two.add(line.replaceAll("Bathwick Hill ", ""));
                        line = br.readLine();
                        three.add(line.replaceAll("City Centre, North Parade", ""));
                        line = br.readLine();
                        four.add(line.replaceAll("Lower Bristol Road", ""));
                        line = br.readLine();
                        five.add((line.replaceAll("Lower Oldfield Park, dep", "")));
                        line = br.readLine();
                        six.add(line.replaceAll("City Centre, High Street", ""));
                        line = br.readLine();
                        seven.add(line.replaceAll("Bathwick Hill2", ""));
                        line = br.readLine();
                        eight.add(line.replaceAll("University of Bath2", ""));
                    }
                }
                map.put("University of Bath", one);
                map.put("Bathwick Hill", two);
                map.put("City Centre, North Parade", three);
                map.put("Lower Bristol Road", four);
                map.put("Lower Oldfield Park, dep", five);
                map.put("City Centre, High Street", six);
                map.put("Bathwick Hill2", seven);
                map.put("University of Bath2", eight);
            }else if(busName.equals("wu18time2"))
            {
                while (!(line = br.readLine()).equals("End.")) {
                    if (line.equals("Notes:")) {
                        line = br.readLine();
                        one.add((line.replaceAll("University of Bath", "")));
                        line = br.readLine();
                        two.add(line.replaceAll("Bathwick Hill, Youth Hostel", ""));
                        line = br.readLine();
                        three.add(line.replaceAll("City Centre, North Parade", ""));
                        line = br.readLine();
                        four.add(line.replaceAll("Manvers Street Bg", ""));
                        line = br.readLine();
                        five.add(line.replaceAll("Lower Bristol Road", ""));
                        line = br.readLine();
                        six.add((line.replaceAll("Lower Oldfield Park, dep", "")));
                        line = br.readLine();
                        seven.add(line.replaceAll("City Centre, Corn Street", ""));
                        line = br.readLine();
                        eight.add(line.replaceAll("City Centre, High Street", ""));
                        line = br.readLine();
                        nine.add(line.replaceAll("Bathwick Hill, Youth Hostel2", ""));
                        line = br.readLine();
                        ten.add(line.replaceAll("University of Bath2", ""));
                    }
                }
                map.put("University of Bath", one);
                map.put("Bathwick Hill, Youth Hostel", two);
                map.put("City Centre, North Parade", three);
                map.put("Manvers Street Bg", four);
                map.put("Lower Bristol Road", five);
                map.put("Lower Oldfield Park, dep", six);
                map.put("City Centre, Corn Street", seven);
                map.put("City Centre, High Street", eight);
                map.put("Bathwick Hill, Youth Hostel2", nine);
                map.put("University of Bath2", ten);
            }
        }catch (IOException e) {
            System.out.println("error reading file: 'latestBusTimes'");
        }

        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf2 =  new SimpleDateFormat("HHmm");
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        String placeName = spin.getSelectedItem().toString();
        System.out.println("spin: " + placeName);
        int curTime = Integer.parseInt(sdf2.format(cal.getTime()));

        if(busName!="u1times2"){//u1 does not need a morning time change since it is 24 hours
            if (curTime > 2300) {//need to check this works correctly
                if (placeName.equals("Oldfield Park, Lower Oldfield Park")) {
                    placeName = "Oldfield Park, Lower Oldfield Park1";
                } else if (placeName.equals("Bus Station, Dorchester St Bn")) {
                    placeName = "Bus Station, Dorchester St Bh";
                } else if (placeName.equals("University of Bath")) {
                    placeName = "University of Bath2";
                } else if (placeName.equals("Bathwick Hill")) {
                    placeName = "Bathwick Hill2";
                } else if (placeName.equals("Bathwick Hill, Youth Hostel")) {
                    placeName = "Bathwick Hill, Youth Hostel2";
                }
            }
        }
        int[] nextTimes = calculateNextTime(placeName, map);

        String timeLine = "";
        for(int x = 1; x <= nextTimes.length; x++){
            int nextTime = nextTimes[x-1];
            if(nextTime == 0){
                timeLine += x + ": now    ";
            }else if(nextTime == 1){
                timeLine += x + ": 1min    ";
            }else if(nextTime == -1){
                timeLine += x + ": none   ";
            }else if(nextTime == 60){
                timeLine += x + ": 60mins";
            }else if((nextTime > 60)&&(nextTime<120)){
                timeLine += x + ": 1hr, " + (nextTime - 60) + "mins   ";
            }else if((nextTime < 60)){
                timeLine += x + ": "+nextTime+"mins   ";
            }else{
                timeLine += x + ": "+(nextTime/60)+"hrs "+(nextTime%60)+"mins    ";
            }
        }
        System.out.println("timeLine"+timeLine);
        return timeLine;
    }

    public int[] calculateNextTime(String placeName, HashMap<String, ArrayList<String>> map) {
        int y = 0;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HHmm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        System.out.println(placeName);
        //if time > 2300, "park1" and "er St Bh" are used - fixed in latestBusTimes

        ArrayList<String> timeList = map.get(placeName);
        int[] nextTimes = new int[3];

        for (String listTimes : timeList) {
            String[] timeArray = listTimes.split(" ");//time array is row of times for each place
            for (int x = 0; x < timeArray.length; x++) {
                String busTime = timeArray[x];
                if ((!busTime.isEmpty())) {
                    if (!busTime.contains("-")) {
                        if (y < 3) {
                            String currentTime = sdf2.format(cal.getTime());
                            String[] currentTimeArray = currentTime.split(":");
                            String[] busTimeArray = new String[2];
                            busTimeArray[0] = String.valueOf(busTime.charAt(0)) + String.valueOf(busTime.charAt(1));//get hour values from string
                            busTimeArray[1] = String.valueOf(busTime.charAt(2)) + String.valueOf(busTime.charAt(3));//get minute values from string

                            int busTimeMinutes = (Integer.parseInt(busTimeArray[1]) + ((Integer.parseInt(busTimeArray[0])) * 60));
                            int currentTimeMinutes = (Integer.parseInt(currentTimeArray[1]) + ((Integer.parseInt(currentTimeArray[0])) * 60));
                            if (currentTimeMinutes <= busTimeMinutes) {
                                //try {
                                int timeDiff = busTimeMinutes - currentTimeMinutes;
                                nextTimes[y] = timeDiff;
                                y++;
                                //this is a terrible way of checking when an array is out of bounds - use a loop!!!!!!!
                                /*} catch (ArrayIndexOutOfBoundsException e1) {
                                    System.out.println("Calculate next times, array index out of bounds");
                                    break;
                                }*/
                            }
                        }
                    } else {
                        /*must remove times that are before the current time.
                        String curTime = sdf1.format(cal.getTime());
                        for(int z = 0; z < 4; z++){
                            nextTimes[z] = Integer.parseInt(curTime + 3*z);
                        }
                        */
                    }
                }
            }
        }
        return nextTimes;
    }

    public final BroadcastReceiver timeChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            updateBusText("u1");
        }
    };

    public void updateBusText(final String route){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // updated UI here:
                int lastTime = 0;
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                    int curTime = Integer.parseInt(sdf2.format(cal.getTime()));
                    if (lastTime < curTime || lastTime == 0) {//if next minute, or not updated yet
                        lastTime = curTime;
                        final TextView busText = (TextView) findViewById(R.id.departures);
                        if (route.equals("18")) {//remove this if, replace by just passing route instead
                            busText.setText(latestBusTime("first18times"));//change text filename here
                        } else {
                            busText.setText(latestBusTime("u1times2"));//change text filename here
                        }
                    }
                } catch (Exception e) {
                    System.out.println("No longer 18/U1");
                }
            }
        });
    }

}


