package project.kudos_it_manitch.erp_kudos.approve_home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.approve_pr.Adapter_approve_pr;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;


public class ShowDetailPrActivity extends AppCompatActivity  {

    //Explicit
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private String m_id, m_code ,type,run_number,projects,m_user;
    private String[] lock, user, approve_sequence,approve,disapprove,reject,message,id_item;
    private Button approvebtn;
    private Adapter_approve_pr adapter_approve_pr;
    // Refresh menu item
    private MenuItem refreshMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approve_pr);

        //Bind Widget
        listView = (ListView) findViewById(R.id.list_approve_pr);
        approvebtn = (Button) findViewById(R.id.approvebtn);
        Button disapprovebtn = (Button) findViewById(R.id.disapprovebtn);
        Button rejectbtn = (Button) findViewById(R.id.rejectbtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        type = getIntent().getStringExtra("type");
        run_number = getIntent().getStringExtra("doc_no");
        projects = getIntent().getStringExtra("pj_code");

        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        m_id = sharedPreferences.getString("m_id", "false");
        m_code = sharedPreferences.getString("m_code", "false");
        m_user = sharedPreferences.getString("m_user", "false");




    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();
        show_list_view();
    }

    public void show_list_view() {

        Config config = new Config();
        String body = "[{'key':'pj_code','value':'" + projects + "'},{'key':'m_id','value':'" + m_id + "'},{'key':'doc_no','value':'" + run_number + "'},{'key':'username','value':'" + m_user + "'},{'key':'type','value':'" + type + "'}]";

        GetService getService = new GetService(getApplicationContext(), config.getDetailSequenc(), body);
        try {
            getService.execute();
            String res_json = getService.get();
            Log.d("res_json",res_json);

            if (res_json != null) {
// array res json
                JSONArray jsonArray = new JSONArray(res_json.toString());
                lock = new String[jsonArray.length()];
                user = new String[jsonArray.length()];
                approve_sequence = new String[jsonArray.length()];
                approve = new String[jsonArray.length()];
                disapprove = new String[jsonArray.length()];
                reject = new String[jsonArray.length()];
                message = new String[jsonArray.length()];
                id_item = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item_obj = jsonArray.getJSONObject(i);
                    lock[i] = item_obj.getString("lock");
                    user[i] = item_obj.getString("user");
                    approve_sequence[i] = item_obj.getString("approve_sequence");
                    approve[i] = item_obj.getString("approve");
                    disapprove[i] = item_obj.getString("disapprove");
                    reject[i] = item_obj.getString("reject");
                    message[i] = item_obj.getString("message");
                    id_item[i] = item_obj.getString("id_item");
                }
               adapter_approve_pr = new Adapter_approve_pr(getApplicationContext(),lock, user, approve_sequence,approve,disapprove,reject,message,id_item,run_number,projects,m_user,type);
                listView.setAdapter(adapter_approve_pr);
//
            } else {
                Toast.makeText(this, "ไม่สามารถ เชื่อมต่อได้", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "error :"+e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listview, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // refresh
                refreshMenuItem = item;
                // load the data from server
                new SyncData().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Async task to load the data from server
     * **/
    private class SyncData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            // set the progress bar view
            refreshMenuItem.setActionView(R.layout.action_progressbar);
            refreshMenuItem.expandActionView();
        }

        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            refreshMenuItem.collapseActionView();
            // remove the progress bar view
            refreshMenuItem.setActionView(null);
            show_list_view();
        }

    }

}//Main Class
