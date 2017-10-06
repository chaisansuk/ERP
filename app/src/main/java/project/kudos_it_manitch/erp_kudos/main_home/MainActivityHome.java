package project.kudos_it_manitch.erp_kudos.main_home;

import android.content.DialogInterface;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        // shereoreference



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
                String res = getService.get().toString();
                JSONObject jsonObject = new JSONObject(res);

                boolean login_status = jsonObject.getBoolean("status");
                String data_obj = jsonObject.getString("data");
                JSONArray jsonArray_data = new JSONArray(data_obj);

                if(login_status == true){
                    JSONObject data_user = jsonArray_data.getJSONObject(0);
                    Toast.makeText(getApplicationContext(),"Welcome "+data_user.getString("m_name")+" !!!",Toast.LENGTH_LONG).show();
                    // intent to home page list
                    Intent intent = new Intent(getApplicationContext(),MainPageActivity.class);
                    intent.putExtra("data",data_user.toString());
                    startActivity(intent);

                    MainActivityHome.this.finish();

                }else{
                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),"รหัสผิดโว้ย ไม่เนียนไปเรียนมาใหม่ "+message,Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }

    }

//    private class AsyncLogin extends AsyncTask<String, String, String> {
//        ProgressDialog pdLoading = new ProgressDialog(MainActivityHome.this);
//        HttpURLConnection conn;
//        URL url = null;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            //this method will be running on UI thread
//            pdLoading.setMessage("\tLoading...");
//            pdLoading.setCancelable(false);
//            pdLoading.show();
//
//        }
//
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//
//                // Enter URL address where your php file resides
//                url = new URL("https://www.cloudmeka.com/app_controller/login_app");
//
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return "exception";
//            }
//            try {
//
//                // Setup HttpURLConnection class to send and receive data from php and mysql
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(READ_TIMEOUT);
//                conn.setConnectTimeout(CONNECTION_TIMEOUT);
//                conn.setRequestMethod("POST");
//
//                // setDoInput and setDoOutput method depict handling of both send and receive
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                // Append parameters to URL
//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("m_user", params[0])
//                        .appendQueryParameter("m_pass", params[1]);
//                String query = builder.build().getEncodedQuery();
//
//                // Open connection for sending data
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();
//                conn.connect();
//
//            } catch (IOException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//                return "exception";
//            }
//
//            try {
//
//                int response_code = conn.getResponseCode();
//
//                // Check if successful connection made
//                if (response_code == HttpURLConnection.HTTP_OK) {
//
//                    // Read data sent from server
//                    InputStream input = conn.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    StringBuilder result = new StringBuilder();
//                    String line;
//
//                    while ((line = reader.readLine()) != null) {
//                        result.append(line);
//                    }
//
//                    // Pass data to onPostExecute method
//                    return (result.toString());
//
//                } else {
//
//                    return ("unsuccessful");
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "exception";
//            } finally {
//                conn.disconnect();
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            //this method will be running on UI thread
//
//            pdLoading.dismiss();
//            Log.d("count",result.toString());
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//
//                boolean login_status = jsonObject.getBoolean("status");
//                String data_obj = jsonObject.getString("data");
//                JSONArray jsonArray_data = new JSONArray(data_obj);
//
//
//
//
//                if(login_status == true){
//                    JSONObject data_user = jsonArray_data.getJSONObject(0);
//                    Toast.makeText(getApplicationContext(),"Welcome "+data_user.getString("m_name")+" !!!",Toast.LENGTH_LONG).show();
//                    // intent to home page list
//                    Intent intent = new Intent(MainActivityHome.this,MainPageActivity.class);
//                    intent.putExtra("data",data_user.toString());
//                    startActivity(intent);
//
//                    MainActivityHome.this.finish();
//
//                }else{
//                    String message = jsonObject.getString("message");
//                    Toast.makeText(getApplicationContext(),"รหัสผิดโว้ย ไม่เนียนไปเรียนมาใหม่ "+message,Toast.LENGTH_LONG).show();
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("count",e.toString());
//                Toast.makeText(getApplicationContext(),"กรุณาเชื่อมต่ออินเตอร์เน็ต ",Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//    }
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
