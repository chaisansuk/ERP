package project.kudos_it_manitch.erp_kudos.main_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.ListPrActivity;
import project.kudos_it_manitch.erp_kudos.inventory_home.ListPrActivity2;
import project.kudos_it_manitch.erp_kudos.main_home.MainActivityHome;


public class MainPageActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private TextView nameUser;
    private TextView text_count_approve;
    private TextView text_count_ic;
    private CircleImageView circleImageUser;
    private Button approvebtn;
    private Button inventorybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        nameUser = (TextView) findViewById(R.id.nameUser);
        text_count_approve = (TextView) findViewById(R.id.text_count_approve);
        text_count_ic = (TextView) findViewById(R.id.text_count_ic);
        circleImageUser = (CircleImageView) findViewById(R.id.imageUser);

        approvebtn = (Button) findViewById(R.id.approvebtn);
        inventorybtn = (Button) findViewById(R.id.inventorybtn);
        Button logout = null;

        // shereoreference
        sharedPreferences = getApplicationContext().getSharedPreferences("session_member", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();




        findViewById(R.id.approvebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageActivity.this,MainPageApproveActivity.class));
            }
        });
        findViewById(R.id.inventorybtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageActivity.this,MainPageICActivity.class));
            }
        });
        findViewById(R.id.fablist_approve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageActivity.this,ListPrActivity.class));
            }
        });
        findViewById(R.id.fablist_ic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageActivity.this,ListPrActivity2.class));
            }
        });

        get_content();

        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStart() {
        super.onStart();
        get_content();

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

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        dialog.show();
    }

    public void get_content(){
        //name and picture User
        String data = getIntent().getStringExtra("data");
        try {
            JSONObject data_json = new JSONObject(data);
            nameUser.setText(data_json.getString("m_name"));
            String path_img = data_json.getString("uimg");
            Picasso.with(MainPageActivity.this).load(path_img).into(circleImageUser);
            JSONObject permissionobj = new JSONObject(data_json.getString("permission"));


            boolean approve_ic;

            JSONObject obj_approve = permissionobj.getJSONObject("approve");
            approve_ic = permissionobj.getBoolean("ic");


            //Toast.makeText(getApplicationContext(), obj_approve.toString(), Toast.LENGTH_SHORT).show();

            if(obj_approve.getBoolean("pr") == true || obj_approve.getBoolean("po") == true){
                approvebtn.setEnabled(true);
            }else{
                approvebtn.setBackgroundColor(Color.DKGRAY);
                approvebtn.setEnabled(false);
            }

            if(approve_ic==true){
                inventorybtn.setEnabled(true);
            }else{
                inventorybtn.setEnabled(false);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}