package cn.edu.xjtlu.mygame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Loginin extends AppCompatActivity {
    private Button jump;
    private Button reg;
    private Button login;
    private EditText account;
    private EditText pwd;
    private TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin);

        reg = findViewById(R.id.reg);
        login = findViewById(R.id.login);
        account = findViewById(R.id.account);
        pwd = findViewById(R.id.pwd);
        state = findViewById(R.id.state);
        jump=(Button)findViewById(R.id.jump);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = account.getText().toString().trim();
                String pass = pwd.getText().toString().trim();

                User user = new User();
                user.setUsername(name);
                user.setUserpwd(pass);

                int result = SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1) {
                    state.setText("注册成功！");
                }else if(result==-1) {
                    state.setText("用户名已存在！");
                }
                else {
                    state.setText("!");
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = account.getText().toString().trim();
                String pass = pwd.getText().toString().trim();
                userList = SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result = SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1) {
                    state.setText("登录成功！");
                }else if (result==0) {
                    state.setText("用户名不存在！");
                }else if (result==-1) {
                    state.setText("密码错误！");
                }
            }
        });
        //jump.setOnClickListener(listner);
    }
    //Button.OnClickListener listner=new Button.OnClickListener(){
        //@Override
        //public void onClick(View view) {
            //Intent intent= new Intent(Loginin.this, progressBar.class);
            //startActivity(intent);
            //Loginin.this.finish();
        //}
    //};
}
