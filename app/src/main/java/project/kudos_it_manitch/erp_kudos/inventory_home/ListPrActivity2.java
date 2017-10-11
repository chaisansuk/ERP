package project.kudos_it_manitch.erp_kudos.inventory_home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.approve_home.ShowDetailPrActivity;


public class ListPrActivity2 extends AppCompatActivity {
    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Bind Widget
        listView = (ListView) findViewById(R.id.listpr);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Test List View

//        String[] testStrings = new String[]{"test1", "test2", "test3", "test4", "test5"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, testStrings);
//        listView.setAdapter(adapter);

        //Call AsyncTask
        SynPr synPr = new SynPr(this, listView);
        synPr.execute();


    }   // Main Method



    private class SynPr extends AsyncTask<Void, Void, String> {

        //Explicit
        private Context context;
        private ListView synListView;
        private String[] projectnameStrings, projectstatusStrings;

        private static final String urlJSON = "https://www.cloudmeka.com/app_controller/get_list_approve";
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
                projectstatusStrings = new String[jsonArray.length()];



                for (int i=0;i<jsonArray.length();i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    projectnameStrings[i] = jsonObject.getString("project_name");
                    projectstatusStrings[i] = jsonObject.getString("project_status");

                }   // for

                //Create ListView
                MyAdapter2 myAdapter2 = new MyAdapter2(context, projectnameStrings, projectstatusStrings);
                synListView.setAdapter(myAdapter2);



                synListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ListPrActivity2.this, ShowDetailPrActivity.class);

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