package project.kudos_it_manitch.erp_kudos.main_page;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.ListPrActivity;
import project.kudos_it_manitch.erp_kudos.inventory_home.ListPrActivity2;
import project.kudos_it_manitch.erp_kudos.main_home.MainActivityHome;


public class MainPageApproveActivity extends AppCompatActivity {
    private TextView text_count_po;
    private TextView text_count_pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_approve);

        Button pobtn = (Button) findViewById(R.id.pobtn);
        Button prbtn = (Button) findViewById(R.id.prbtn);
        text_count_po = (TextView) findViewById(R.id.text_count_po);
        text_count_pr = (TextView) findViewById(R.id.text_count_pr);


        findViewById(R.id.pobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageApproveActivity.this,ListPrActivity.class));
            }
        });
        findViewById(R.id.prbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageApproveActivity.this,ListPrActivity2.class));
            }
        });

    /////////testtttt

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

}
