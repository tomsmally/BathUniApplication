package tom.smally;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

/*import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
//import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;
//import android.support.multidex.MultiDexApplication;
import android.util.Log;

//import com.amazonaws.mobile.AWSMobileClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MobileServiceClient mClient; // Mobile Service Client reference
    //private MobileServiceTable<ToDoItem> mToDoTable; // Mobile Service Table used to access data
    private MobileServiceTable<OpeningTimesItem> mOpenTimesTable; // Mobile Service Table used to access data

    // Offline Sync:
    // private MobileServiceSyncTable<ToDoItem> mToDoTable; //Mobile Service Table used to access and Sync data
    //private ToDoItemAdapter mAdapter; // Adapter to sync the items list with the view
    private EditText mTextNewToDo; //EditText containing the "New To Do" text
    private ProgressBar mProgressBar; //Progress spinner to use for table operations

    public String GlobalEmail = "ts690@bath.ac.uk";
    public String GlobalUsername = "ts690";
    public String GlobalPassword = "Snowleopard2412";

    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //MUST OPEN LOGIN PAGE FIRST:
            //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //MainActivity.this.startActivity(intent);

        // Initialize the AWS Mobile Client
        //AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        //BUT FOR NOW:
        Intent intent = new Intent(MainActivity.this, activity_home.class);
        //MainActivity.this.startActivity(intent);
        startActivity(intent);
        onBackPressed();
    }

    public void setCredentials(String email, String username, String password){
        GlobalEmail = email;
        GlobalUsername = username;
        GlobalPassword = password;
    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * Select an option from the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_refresh) {
           //refreshItemsFromTable();
        }else if (id == R.id.action_settings) {
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
            //do nothing, as already home
        } else if (id == R.id.nav_gallery) {
            //Load popular page
            Intent intent = new Intent(MainActivity.this, Popular.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, Email.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, Map.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, Travel.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(MainActivity.this, Food.class);
            MainActivity.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onTimetableClick(View v){
        Intent intent = new Intent(MainActivity.this, Popular.class);
        MainActivity.this.startActivity(intent);
    }
    public void onEmailClick(View v){
        Intent intent = new Intent(MainActivity.this, Email.class);
        MainActivity.this.startActivity(intent);
    }
    public void onMapClick(View v){
        Intent intent = new Intent(MainActivity.this, Map.class);
        MainActivity.this.startActivity(intent);
    }
    public void onLibraryClick(View v){
        Intent intent = new Intent(MainActivity.this, Library.class);
        MainActivity.this.startActivity(intent);
    }
    public void onTravelClick(View v){
        Intent intent = new Intent(MainActivity.this, Travel.class);
        MainActivity.this.startActivity(intent);
    }
    public void onFoodClick(View v){
        Intent intent = new Intent(MainActivity.this, Food.class);
        MainActivity.this.startActivity(intent);
    }
    public void onSocialClick(View v){
        Intent intent = new Intent(MainActivity.this, Social.class);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Mark an item as completed
     * @param item  - The item to mark
     *
    public void checkItem(final OpeningTimesItem item)
    {
        if (mClient == null) {
            return;
        }
        item.setComplete(true); // Set the item as completed and update it in the table

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    checkItemInTable(item);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (item.isComplete()) {
                                mAdapter.remove(item);
                                //removeFromOpeningTimes(item);
                            }
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error, removeItem");
                    System.out.println("RemoveItem");
                    e.printStackTrace();
                }
                return null;
            }
        };
        runAsyncTask(task);
    }*/

    /**
     * Mark an item as completed in the Mobile Service Table
     * @param item - The item to mark
     *
    public void checkItemInTable(OpeningTimesItem item) throws ExecutionException, InterruptedException {
        //mToDoTable.update(item).get();
        mOpenTimesTable.update(item).get();
    }

    /**
     * Add a new item
     * @param view - The view that originated the call
     *
    public void addItem(View view) {
        if (mClient == null) {
            return;
        }

        // Create a new item
        ///final ToDoItem item = new ToDoItem();
        //item.setText(mTextNewToDo.getText().toString());
        //item.setComplete(false);

        final OpeningTimesItem item = new OpeningTimesItem();
        //item.setOpenTimes(getOpeningTimes());
        item.setText(mTextNewToDo.getText().toString());
        item.setComplete(false);

        // Insert the new item
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //final ToDoItem entity = addItemInTable(item);
                    final OpeningTimesItem entity = addItemInTable(item);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!entity.isComplete()){
                                mAdapter.add(entity);
                                //LoadIntoOpeningTimesView(entity);
                            }
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error, addItem");
                    System.out.println("addItem");
                    e.printStackTrace();
                }
                return null;
            }
        };
        runAsyncTask(task);
        mTextNewToDo.setText("");
    }*/

    /**
     * Add an item to the Mobile Service Table
     * @param item - The item to Add
     *
    public OpeningTimesItem addItemInTable(OpeningTimesItem item) throws ExecutionException, InterruptedException {
        //return mToDoTable.insert(item).get();
        return mOpenTimesTable.insert(item).get();
    }

    /**
     * Refresh the list with the items in the Table
     *
    private void refreshItemsFromTable()
    {
        // Get items not marked as completed and add them in the adapter
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTable();
                    final List<OpeningTimesItem> results = refreshItemsFromMobileServiceTable();
                    //Offline Sync:
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.clear();
                            //for (ToDoItem item : results) {
                            for (OpeningTimesItem item : results) {
                                mAdapter.add(item);
                                //LoadIntoOpeningTimes(item);
                                System.out.println("OpeningTimesItem:results: "+item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error, refreshItemFromTable");
                    System.out.println("RefreshItemFromTable");
                    e.printStackTrace();
                }
                return null;
            }
        };
        runAsyncTask(task);
    }*/

    /**
     * Refresh the list with the items in the Mobile Service Table
     *
    private MobileServiceList<OpeningTimesItem> refreshItemsFromMobileServiceTable() throws ExecutionException, InterruptedException, MobileServiceException {
        //return mToDoTable.where().field("complete").eq(val(false)).execute().get();
        System.out.println("refreshItemsFromMobileServiceTable");
        return mOpenTimesTable.where().field("complete").eq(val(false)).execute().get();
    }*/

    //Offline Sync
    /**
     * Refresh the list with the items in the Mobile Service Sync Table
     */
    /*private List<ToDoItem> refreshItemsFromMobileServiceTableSyncTable() throws ExecutionException, InterruptedException {
        //sync the data
        sync().get();
        Query query = QueryOperations.field("complete").
                eq(val(false));
        return mToDoTable.read(query).get();
    }*/

    /**
     * Initialize local storage
     * @return
     * @throws MobileServiceLocalStoreException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException
    {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    MobileServiceSyncContext syncContext = mClient.getSyncContext();
                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);
                    /*java.util.Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("text", ColumnDataType.String);
                    tableDefinition.put("foriegnkey", ColumnDataType.Integer);
                    tableDefinition.put("complete", ColumnDataType.Boolean);*/

                    java.util.Map<String, ColumnDataType> openTableDefinition = new HashMap<String, ColumnDataType>();
                    openTableDefinition.put("id", ColumnDataType.String);
                    openTableDefinition.put("complete", ColumnDataType.Boolean);
                    openTableDefinition.put("text", ColumnDataType.String);
                    openTableDefinition.put("MonOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("MonClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("TueOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("TueClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("WedOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("WedClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("ThuOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("ThuClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("FriOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("FriClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("SatOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("SatClosed", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("SunOpen", ColumnDataType.DateTimeOffset);
                    openTableDefinition.put("SunClosed", ColumnDataType.DateTimeOffset);

                    //localStore.defineTable("ToDoItem", tableDefinition);
                    localStore.defineTable("OpeningTimeItem", openTableDefinition);

                    //SimpleSyncHandler handler = new SimpleSyncHandler();
                    //syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error, AsyncTask");
                    System.out.println("AsyncTask");
                    e.printStackTrace();
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }

    //Offline Sync
    /**
     * Sync the current context and the Mobile Service Sync Table
     * @return
     */
    /*
    private AsyncTask<Void, Void, Void> sync() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    MobileServiceSyncContext syncContext = mClient.getSyncContext();
                    syncContext.push().get();
                    mToDoTable.pull(null).get();
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }
    */

    /**
     * Creates a dialog and shows it
     * @param exception - The exception to show in the dialog
     * @param title - The dialog title
     */
    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error, CreateShowDialog");
                System.out.println("CreateShowDialog");
                exception.printStackTrace();
            }
        });
    }
    /**
     * Creates a dialog and shows it
     * @param exception - The exception to show in the dialog
     * @param title - The dialog title
     */
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }
    /**
     * Creates a dialog and shows it
     * @param message - The dialog message
     * @param title - The dialog title
     */
    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
    /*
    private class ProgressFilter implements ServiceFilter
    {
        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);
            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                    System.out.println("Futures - onFailure, CHECK WiFi!!!");
                }
                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                        }
                    });

                    resultFuture.set(response);
                }
            });
            return resultFuture;
        }
    }
    */
}
