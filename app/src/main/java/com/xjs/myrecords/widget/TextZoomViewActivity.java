package com.xjs.myrecords.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xjs.myrecords.R;

public class TextZoomViewActivity extends AppCompatActivity implements View.OnClickListener {
    ZoomViewContainer zoomViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_zoom_view);
        this.findViewById(R.id.btn_1).setOnClickListener(this);
        this.findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        initZoomView();
    }


    private void initZoomView() {
        zoomViewContainer = (ZoomViewContainer) findViewById(R.id.zoom_view_container);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                zoomViewContainer.scale2();
                break;
            case R.id.btn_2:
                zoomViewContainer.scale3();
                break;
            case R.id.btn_3:
                zoomViewContainer.reset();
                break;
        }
    }
}
