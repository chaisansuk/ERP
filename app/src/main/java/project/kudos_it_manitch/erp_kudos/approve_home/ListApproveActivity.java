package project.kudos_it_manitch.erp_kudos.approve_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;


public class ListApproveActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;
    private String type;
    private SharedPreferences sharedPreferences;
    private String m_id, m_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //Bind Widget
        listView = (ListView) findViewById(R.id.listpr);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        type = getIntent().getStringExtra("type");
        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        m_id = sharedPreferences.getString("m_id","false");
        m_code = sharedPreferences.getString("m_code", "false");

        Toast.makeText(this, type+" "+m_id+" "+m_code, Toast.LENGTH_SHORT).show();

        //Call AsyncTask
//        SynPr synPr = new SynPr(this, listView);
//        synPr.execute();

    }   // Main Method

    @Override
    protected void onStart() {
        super.onStart();
        
    }

    private class SynPr extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private ListView synListView;
        private String[] projectnameStrings, projectdetailStrings;

        private static final String urlJSON = "https://www.cloudmeka.com/getdata_projectname.php";
        public SynPr(Context context,
                     ListView synListView) {
            this.context = context;
            this.synListView = synListView;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("22JulyV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                projectnameStrings = new String[jsonArray.length()];
                projectdetailStrings = new String[jsonArray.length()];



                for (int i=0;i<jsonArray.length();i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    projectnameStrings[i] = jsonObject.getString("project_name");
                    projectdetailStrings[i] = jsonObject.getString("project_detail");

                }   // for

                //Create ListView
                MyAdapter myAdapter = new MyAdapter(context, projectnameStrings, projectdetailStrings);
                synListView.setAdapter(myAdapter);


                synListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ListApproveActivity.this, ShowDetailPrActivity.class);

                        startActivity(intent);

                    }// onItemClick
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost

    }   // SynShopCenter Class

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listview, menu);
        return super.onCreateOptionsMenu(menu);
    }


}   // Main Class