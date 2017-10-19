package project.kudos_it_manitch.erp_kudos.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.ListApproveActivity;
import project.kudos_it_manitch.erp_kudos.approve_home.approve_pr_pjname.ListPjNameActivity;


public class MainPageICActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_ic);

        Button receivebtn = (Button) findViewById(R.id.receivebtn);
        Button issuebtn = (Button) findViewById(R.id.issuebtn);



        findViewById(R.id.receivebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageICActivity.this,ListApproveActivity.class));
            }
        });
        findViewById(R.id.issuebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPageICActivity.this,ListPjNameActivity.class));
            }
        });



    }

}
