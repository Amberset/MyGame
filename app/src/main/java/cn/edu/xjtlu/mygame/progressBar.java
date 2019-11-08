package cn.edu.xjtlu.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class progressBar extends AppCompatActivity {
    private TextView textView;
    private TextView tittle;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent= new Intent(this, Loginin.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        tittle = findViewById(R.id.tittle);
        tittle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/COLONNA.TTF"));
        String s = "plagiarist";
        try {
            TextEffect te = new TextEffect(tittle,s,300);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView)findViewById(R.id.barText);

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        while (progressStatus < 100) {
                            progressStatus += 5;
                            // Update the progress bar and display the
                            //current value in the text view
                            handler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    textView.setText(progressStatus+"/"+progressBar.getMax());
                                }
                            });
                            try {
                                // Sleep for 200 milliseconds.
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }
}
