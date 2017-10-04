package project.kudos_it_manitch.erp_kudos.main_splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import project.kudos_it_manitch.erp_kudos.R;
import project.kudos_it_manitch.erp_kudos.main_home.MainActivityHome;

public class SplashScreen extends Activity {
//    Handler handler;
//    Runnable runnable;
//    long delay_time;
//    long time = 2000L;
    private Timer timer;
    private ProgressBar progressBar;
    private int i=0;
    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("");

        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }else{
                    //closing the timer
                    timer.cancel();
                    Intent intent =new Intent(SplashScreen.this,MainActivityHome.class);
                    startActivity(intent);
                    // close this activity
                    finish();
                }
            }
        }, 0, period);
    }

//    public void onResume() {
//        super.onResume();
//        delay_time = time;
//        handler.postDelayed(runnable, delay_time);
//        time = System.currentTimeMillis();
//
//    }
//
//    public void onPause() {
//        super.onPause();
//        handler.removeCallbacks(runnable);
//        time = delay_time - (System.currentTimeMillis() - time);
//    }
}