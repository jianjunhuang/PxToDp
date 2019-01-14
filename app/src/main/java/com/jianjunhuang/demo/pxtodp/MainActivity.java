package com.jianjunhuang.demo.pxtodp;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  private boolean isPxToDp = true;
  private TextInputEditText leftEdt;
  private TextInputEditText rightEdt;
  private TextInputLayout leftLayout;
  private TextInputLayout rightLayout;

  private final String DP = "dp";
  private final String PX = "px";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    leftEdt = findViewById(R.id.edt_left);
    rightEdt = findViewById(R.id.edt_right);

    leftLayout = findViewById(R.id.left);
    rightLayout = findViewById(R.id.right);

    Button convertBtn = findViewById(R.id.btn_convert);
    convertBtn.setOnClickListener(view -> {
      float value = 0;
      if (isPxToDp) {
        value = pxToDp(Float.valueOf(leftEdt.getText().toString()));
      } else {
        value = dpToPx(Float.valueOf(leftEdt.getText().toString()));
      }
      rightEdt.setText(String.valueOf(value));
    });
    ImageView alignmentImageView = findViewById(R.id.iv_alignment);
    alignmentImageView.setOnClickListener(view -> {
      view.animate().rotation(isPxToDp ? 180 : -180);
      alignment();
    });
  }

  private void alignment() {
    isPxToDp = !isPxToDp;
    leftLayout.setHint(isPxToDp ? PX : DP);
    rightLayout.setHint(isPxToDp ? DP : PX);
  }

  private float dpToPx(float dp) {
    return dp * getScreenDensity();
  }

  private float pxToDp(float px) {
    return px / getScreenDensity();
  }

  public DisplayMetrics getDisplayMetrics() {
    return getResources().getDisplayMetrics();
  }

  public float getScreenDensity() {
    return getDisplayMetrics().density;
  }
}
