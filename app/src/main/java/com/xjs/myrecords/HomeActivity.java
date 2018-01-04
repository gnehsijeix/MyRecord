package com.xjs.myrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xjs.myrecords.view.CircleProgressView;

import java.util.Timer;
import java.util.TimerTask;


@Route(path="com.xjs.code.home")
public class HomeActivity extends AppCompatActivity {
    private CircleProgressView mCircleProgressView;
    @Autowired(name = "progress",required = true)
    int progress = 0;
    @Autowired(name = "timer",required = false)
    Timer timer;

    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCircleProgressView = (CircleProgressView) findViewById(R.id.main_circle_progress_view);
        testProgress();
    }
    private void testProgress() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress++;
                        mCircleProgressView.updateProgress(progress);
                        if (progress>=100) {
                            timer.cancel();
                        }
                    }
                });
            }
        },0,100);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
