package cn.edu.xjtlu.mygame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView sug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sug = findViewById(R.id.suggestions);
        sug.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/STKAITI.TTF"));
        start = findViewById(R.id.startGame);
        start.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/CHILLER.TTF"));
        start.setOnClickListener(listerner);
    }
    Button.OnClickListener listerner = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, progressBar.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };
}

