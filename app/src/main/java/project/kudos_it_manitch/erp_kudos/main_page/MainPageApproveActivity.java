package project.kudos_it_manitch.erp_kudos.main_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.ListApproveActivity;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;
import project.kudos_it_manitch.erp_kudos.main_home.MainActivityHome;


public class MainPageApproveActivity extends AppCompatActivity {
    private TextView text_count_po;
    private TextView text_count_pr;
    private SharedPreferences sharedPreferences;
    private String m_id,m_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_approve);

        Button pobtn = (Button) findViewById(R.id.pobtn);
        Button prbtn = (Button) findViewById(R.id.prbtn);
        text_count_po = (TextView) findViewById(R.id.text_count_po);
        text_count_pr = (TextView) findViewById(R.id.text_count_pr);


        findViewById(R.id.prbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListApproveActivity.class);
                intent.putExtra("type","pr");
                startActivity(intent);
                //MainPageApproveActivity.this.finish();
            }
        });
        findViewById(R.id.pobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ListApproveActivity.class);
                intent.putExtra("type","po");
                startActivity(intent);
                //MainPageApproveActivity.this.finish();
            }
        });


        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        m_id = sharedPreferences.getString("m_id","false");
        m_code = sharedPreferences.getString("m_code", "false");

        get_noti_approve();

    }


    public  void logout(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Logout");
        dialog.setIcon(R.drawable.splash1);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to logout?");
        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), MainActivityHome.class);
                startActivity(i);
                finish();
            }
        });

        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        get_noti_approve();

    }

    public void get_noti_approve(){
        String body = "[{'key':'m_id','value':'"+m_id+"'},{'key':'m_code','value':'"+m_code+"'}]";

        Config config = new Config();

        GetService getService = new GetService(getApplicationContext(), config.getNoti(), body);
        getService.execute();

        try{
            String res = getService.get();

            if(res != null){
                JSONObject approve = new JSONObject(res.toString());
                JSONObject obj_approve = approve.getJSONObject("approve");
               
                text_count_pr.setText(obj_approve.getString("pr"));
                text_count_po.setText(obj_approve.getString("po"));

            }else{
                Toast.makeText(getApplicationContext(), "ไม่สามารถ เชื่อต่อได้", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }

       // Toast.makeText(getApplicationContext(),body, Toast.LENGTH_SHORT).show();
    }
}//end class
