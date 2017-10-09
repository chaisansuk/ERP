package project.kudos_it_manitch.erp_kudos.main_home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.config.Config;
import project.kudos_it_manitch.erp_kudos.getservice_okhttp.GetService;
import project.kudos_it_manitch.erp_kudos.main_page.MainPageActivity;

public class MainActivityHome extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        // shereoreference
       sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);


    }

    public void checkLogin(View arg0) {
        final String m_user = txtUsername.getText().toString();
        final String m_pass = txtPassword.getText().toString();
        if (m_user.equals("")||m_pass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"ไอควายกรอกดีๆสิ",Toast.LENGTH_LONG).show();
        }else {

            Config config = new Config();

            String body = "[{'key':'m_user','value':'"+m_user+"'},{'key':'m_pass','value':'"+m_pass+"'}]";

            GetService getService = new GetService(getApplicationContext(),config.getSerlogin(),body);
            getService.execute();

            try {
                String res = getService.get();

                if(res!=null){
                    JSONObject jsonObject = new JSONObject(res.toString());

                    boolean login_status = jsonObject.getBoolean("status");
                    String data_obj = jsonObject.getString("data");
                    JSONArray jsonArray_data = new JSONArray(data_obj);

                    if(login_status == true){
                        JSONObject data_user = jsonArray_data.getJSONObject(0);
                        Toast.makeText(getApplicationContext(),"Welcome "+data_user.getString("m_name")+" !!!",Toast.LENGTH_LONG).show();

                        //crete sharedprefer
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("status_login",true);
                        editor.putString("m_code",data_user.getString("m_code"));
                        editor.putString("m_id",data_user.getString("m_id"));
                        editor.putString("m_user",m_user);
                        editor.putString("m_pass",m_pass);
                        editor.commit();


                        // intent to home page list
                        Intent intent = new Intent(getApplicationContext(),MainPageActivity.class);
                        intent.putExtra("data",data_user.toString());
                        startActivity(intent);


                        MainActivityHome.this.finish();

                    }else{
                        String message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(),"รหัสผิดโว้ย ไม่เนียนไปเรียนมาใหม่ "+message,Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(getApplicationContext(),"ไม่สามารถเชื่อต่อได้",Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }

    }

    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setIcon(R.drawable.splash1);
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.show();
    }
}
