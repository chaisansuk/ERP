package project.kudos_it_manitch.erp_kudos.approve_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;


public class ListApproveActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;
    private String type;
    private SharedPreferences sharedPreferences;
    private String m_id, m_code;
    private String[] run_number, projects, project_name, create_user, create_date, types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Bind Widget
        listView = (ListView) findViewById(R.id.listpr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        type = getIntent().getStringExtra("type");
        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        m_id = sharedPreferences.getString("m_id", "false");
        m_code = sharedPreferences.getString("m_code", "false");


        //Call AsyncTask
//        SynPr synPr = new SynPr(this, listView);
//        synPr.execute();

    }   // Main Method

    @Override
    protected void onStart() {
        super.onStart();
        show_list_view();
    }

    public void show_list_view() {

        Config config = new Config();

        String body = "[{'key':'m_id','value':'" + m_id + "'},{'key':'m_code','value':'" + m_code + "'},{'key':'type','value':'" + type + "'}]";
        GetService getService = new GetService(getApplicationContext(), config.getListapprove(), body);
        try {
            getService.execute();

            String res_json = getService.get();
            if (res_json != null) {

                JSONObject json_result = new JSONObject(res_json.toString());
                Boolean status_result = json_result.getBoolean("status");

                if (status_result == true) {


                    JSONArray array_list_approve = json_result.getJSONArray("data");
                    Log.d("count_array ", String.valueOf(array_list_approve.length()));

                    run_number = new String[array_list_approve.length()];
                    projects = new String[array_list_approve.length()];
                    project_name = new String[array_list_approve.length()];
                    create_user = new String[array_list_approve.length()];
                    create_date = new String[array_list_approve.length()];
                    types = new String[array_list_approve.length()];

                    for (int i = 0; i < array_list_approve.length(); i++) {

                        JSONObject item_obj = array_list_approve.getJSONObject(i);

                        run_number[i] = item_obj.getString("app_pr");
                        projects[i] = item_obj.getString("app_project");
                        project_name[i] = item_obj.getString("project_name");
                        create_user[i] = item_obj.getString("creatuser");
                        create_date[i] = item_obj.getString("creatudate");
                        types[i] = item_obj.getString("type");
                    }

//                    Log.d("count", String.valueOf(create_date.length));
                    Adapter_approve adapter_approve = new Adapter_approve(getApplicationContext(), run_number, projects, project_name, create_user, create_date, types);
//
                    listView.setAdapter(adapter_approve);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListApproveActivity.this, ShowDetailPrActivity.class);
                        startActivity(intent);
                    }// onItemClick
                });

                } else {
                    Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "ไม่สามารถ เชื่อมต่อได้", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "error :"+e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

//    private class SynPr extends AsyncTask<Void, Void, String> {
//
//        //Explicit
//        private Context context;
//        private ListView synListView;
//        private String[] projectnameStrings, projectdetailStrings;
//
//        private static final String urlJSON = "https://www.cloudmeka.com/app_controller/get_list_approve";
//
//        public SynPr(Context context,
//                     ListView synListView) {
//            this.context = context;
//            this.synListView = synListView;
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//
//            try {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request.Builder builder = new Request.Builder();
//                Request request = builder.url(urlJSON).build();
//                Response response = okHttpClient.newCall(request).execute();
//                return response.body().string();
//
//            } catch (Exception e) {
//                return null;
//            }
//
//        }   // doInBack
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Log.d("22JulyV1", "JSON ==> " + s);
//
//            try {
//
//                JSONArray jsonArray = new JSONArray(s);
//
//                projectnameStrings = new String[jsonArray.length()];
//                projectdetailStrings = new String[jsonArray.length()];
//
//
//                for (int i = 0; i < jsonArray.length(); i += 1) {
//
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    projectnameStrings[i] = jsonObject.getString("project_name");
//                    projectdetailStrings[i] = jsonObject.getString("project_detail");
//
//                }   // for
//
//                //Create ListView
//                MyAdapter myAdapter = new MyAdapter(context, projectnameStrings, projectdetailStrings);
//                synListView.setAdapter(myAdapter);
//
//
//                synListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        Intent intent = new Intent(ListApproveActivity.this, ShowDetailPrActivity.class);
//
//                        startActivity(intent);
//
//                    }// onItemClick
//                });
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }   // onPost
//
//    }   // SynShopCenter Class

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listview, menu);
        return super.onCreateOptionsMenu(menu);
    }


}   // Main Class