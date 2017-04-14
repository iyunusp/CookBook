package iyp.cookbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import iyp.cookbook.account.Account;
import iyp.cookbook.account.SignIn;

public class Login extends Activity {
    private Button signup,login;
    private EditText uname,pw;
    private TextView txt,pwd,skip;
    private Animation shake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //form
        skip=(TextView)findViewById(R.id.skip);
        uname=(EditText) findViewById(R.id.Username);
        pw=(EditText) findViewById(R.id.Password);
        txt=(TextView) findViewById(R.id.textView);
        pwd=(TextView) findViewById(R.id.textView2);
        signup= (Button) findViewById(R.id.SignUp);
        login= (Button) findViewById(R.id.Login);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), Preview_menu.class);
                Intent intent=new Intent(getApplicationContext(),MenuList.class);
                intent.putExtra("user",new Account("GUEST","GUEST","GUEST","GUEST@GUEST.COM","UNKNOWN","UNKNOWN"));
                startActivity(intent);
            }
        });
        pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE)
                    login.performClick();
                return false;
            }
        });
        //animation
        shake= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
    }
    public void login(View view){
        boolean valid=true;
        String userId=uname.getText().toString();
        String passId=pw.getText().toString();
        String[] user= new String[2];
        user[0]=userId; user[1]=passId;
        if(userId.equals("")){
            txt.setTextColor(Color.RED);
            uname.startAnimation(shake);
            valid=false;
        }else
            txt.setTextColor(Color.WHITE);

         if(passId.equals("")){
             pwd.setTextColor(Color.RED);
             pw.startAnimation(shake);
             valid=false;
        }else
            pwd.setTextColor(Color.WHITE);

        if(!valid)
            return;
        SignIn sesi=new SignIn(Login.this,view);
        sesi.execute(user);
    }
    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}
