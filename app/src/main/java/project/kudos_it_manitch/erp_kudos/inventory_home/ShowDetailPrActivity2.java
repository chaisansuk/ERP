package project.kudos_it_manitch.erp_kudos.inventory_home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.main_inventory.Qr_code_gen;


public class ShowDetailPrActivity2 extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private String projectnameStrings, projectstatusStrings;
    private TextView projectnameTextView, projectstatusTextView;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_show_detail2);

        //Bind Widget
        bindWidget();

        //show Text
        showText();
        //show Text
        showText();
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailPrActivity2.this,Qr_code_gen.class));
            }
        });
    }//onCreate

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.btn_update:
            {
                update();
                break;
            }

        }
    }

    public void update(){
// Your update algorithm
        Toast.makeText(getApplicationContext(), "update" , Toast.LENGTH_SHORT).show();
    }

    public void clickBackShowDetail(View view) {
        finish();
    }

    private void showText() {
        projectnameStrings = getIntent().getStringExtra("project_name");
        projectstatusStrings = getIntent().getStringExtra("project_status");

        projectnameTextView.setText("project_name");

        projectstatusTextView.setText("Status : " + "project_status");

    }

    private void bindWidget() {
        projectnameTextView = (TextView) findViewById(R.id.txname);
        projectnameTextView = (TextView) findViewById(R.id.txname1);
        projectstatusTextView = (TextView) findViewById(R.id.txstatus);
    }


}//Main Class
