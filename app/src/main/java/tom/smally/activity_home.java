package tom.smally;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class activity_home extends AppCompatActivity {

    String GlobalEmail = "ts690@bath.ac.uk";
    String GlobalUsername = "ts690";
    String GlobalPassword = "Snowleopard2412";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Bath University App");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int fragment_sectionNumber = 0;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            fragment.fragment_sectionNumber = sectionNumber;
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;

            switch (fragment_sectionNumber){
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_activity_home, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_home_bath_uni, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_home_extras, container, false);
                    break;
            }
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Bath Uni";
                case 2:
                    return "Extras";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onTimetableClick(View v){
        Intent intent = new Intent(activity_home.this, Popular.class);
        intent.putExtra("EMAIL",GlobalEmail);
        intent.putExtra("USERNAME",GlobalUsername);
        intent.putExtra("PASSWORD",GlobalPassword);
        activity_home.this.startActivity(intent);
    }
    public void onEmailClick(View v){
        Intent intent = new Intent(activity_home.this, Email.class);
        intent.putExtra("EMAIL",GlobalEmail);
        intent.putExtra("USERNAME",GlobalUsername);
        intent.putExtra("PASSWORD",GlobalPassword);
        activity_home.this.startActivity(intent);
    }
    public void onMapClick(View v){
        Intent intent = new Intent(activity_home.this, Map.class);
        activity_home.this.startActivity(intent);
    }
    public void onLibraryClick(View v){
        Intent intent = new Intent(activity_home.this, Library.class);
        intent.putExtra("EMAIL",GlobalEmail);
        intent.putExtra("USERNAME",GlobalUsername);
        intent.putExtra("PASSWORD",GlobalPassword);
        activity_home.this.startActivity(intent);
    }
    public void onTravelClick(View v){
        Intent intent = new Intent(activity_home.this, Travel.class);
        activity_home.this.startActivity(intent);
    }
    public void onFoodClick(View v){
        Intent intent = new Intent(activity_home.this, Food.class);
        activity_home.this.startActivity(intent);
    }
    public void onSocialClick(View v){
        Intent intent = new Intent(activity_home.this, Social.class);
        activity_home.this.startActivity(intent);
    }

}
