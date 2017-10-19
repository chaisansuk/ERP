package project.kudos_it_manitch.erp_kudos.approve_home.approve_pr_pjname;

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
import project.kudos_it_manitch.erp_kudos.approve_home.ListApproveActivity;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;


public class ListPjNameActivity extends AppCompatActivity {
    //Explicit
    private ListView listView;
    private String type;
    private SharedPreferences sharedPreferences;
    private String m_id;
    private String[] project_name, project_code, project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);

        //Bind Widget
        listView = (ListView) findViewById(R.id.listpj);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        type = getIntent().getStringExtra("type");
        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        m_id = sharedPreferences.getString("m_id", "false");

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

        String body = "[{'key':'m_id','value':'" + m_id + "'},{'key':'type','value':'" + type + "'}]";
        GetService getService = new GetService(getApplicationContext(), config.getListproject(), body);
        try {
            getService.execute();

            String res_json = getService.get();
            if (res_json != null) {


                JSONArray json_result = new JSONArray(res_json.toString());


                Log.d("count_array ", String.valueOf(json_result.length()));
                project_name = new String[json_result.length()];
                project_code = new String[json_result.length()];
                project_id = new String[json_result.length()];

                for (int i = 0; i < json_result.length(); i++) {
                    JSONObject jsonObject = json_result.getJSONObject(i);


                    project_name[i] = jsonObject.getString("project_name");
                    project_code[i] = jsonObject.getString("project_code");
                    project_id[i] = jsonObject.getString("project_id");

                }

                Adapter_PjName adapterPjName = new Adapter_PjName(getApplicationContext(), project_name,project_code,project_id);

                listView.setAdapter(adapterPjName);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ListPjNameActivity.this, ListApproveActivity.class);
                        intent.putExtra("project_id", project_id[i]);
                        intent.putExtra("type", type);
                        startActivity(intent);
                        //Toast.makeText(ListPjNameActivity.this, project_id[i], Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                Toast.makeText(this, "ไม่สามารถ เชื่อมต่อได้", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "error :" + e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listview, menu);
        return super.onCreateOptionsMenu(menu);
    }


}   // Main Class