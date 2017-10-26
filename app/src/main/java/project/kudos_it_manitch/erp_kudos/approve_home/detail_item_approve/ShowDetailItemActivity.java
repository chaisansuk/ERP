package project.kudos_it_manitch.erp_kudos.approve_home.detail_item_approve;

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


public class ShowDetailItemActivity extends AppCompatActivity  {

    //Explicit
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private String type,doc_no,m_id, m_code ,m_user;
    private String[] id, doc_date, qty, unit, mat_name, mat_code, amount, priceunit;
    private Adapter_approve_detail adapter_approve_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_approve_detail);
        //Bind Widget
        listView = (ListView) findViewById(R.id.list_approve_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = getIntent().getStringExtra("type");
        doc_no = getIntent().getStringExtra("doc_no");

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
        String body = "[{'key':'type','value':'" + type + "'},{'key':'doc_no','value':'" + doc_no + "'}]";

        GetService getService = new GetService(getApplicationContext(), config.getItem_document(), body);
        try {
            getService.execute();
            String res_json = getService.get();
            Log.d("res_json",res_json);

            if (res_json != null) {
// array res json
                JSONArray jsonArray = new JSONArray(res_json.toString());
                id = new String[jsonArray.length()];
                doc_date = new String[jsonArray.length()];
                qty = new String[jsonArray.length()];
                unit = new String[jsonArray.length()];
                mat_name = new String[jsonArray.length()];
                mat_code = new String[jsonArray.length()];
                amount = new String[jsonArray.length()];
                priceunit = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item_obj = jsonArray.getJSONObject(i);
                    id[i] = item_obj.getString("id");
                    doc_date[i] = item_obj.getString("doc_date");
                    qty[i] = item_obj.getString("qty");
                    unit[i] = item_obj.getString("unit");
                    mat_name[i] = item_obj.getString("mat_name");
                    mat_code[i] = item_obj.getString("mat_code");
                    amount[i] = item_obj.getString("amount");
                    priceunit[i] = item_obj.getString("priceunit");

                }
               adapter_approve_detail = new Adapter_approve_detail(getApplicationContext(),id, doc_date, qty, unit, mat_name, mat_code, amount, priceunit,type,doc_no,m_id, m_code ,m_user);
                listView.setAdapter(adapter_approve_detail);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ShowDetailItemActivity.this, ListApproveActivity.class);

                        intent.putExtra("type", type);
                        startActivity(intent);
                        //Toast.makeText(ListPjNameActivity.this, project_id[i], Toast.LENGTH_SHORT).show();
                    }
                });
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


}//Main Class
