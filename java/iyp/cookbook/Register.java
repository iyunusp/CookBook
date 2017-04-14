package iyp.cookbook;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import iyp.cookbook.account.CheckUname;
import iyp.cookbook.account.SignUp;

public class Register extends Activity {
    private Button reg;
    private EditText Ename;
    private Animation shake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        //form
        reg=(Button)findViewById(R.id.regSignUp);
        Ename=(EditText)findViewById(R.id.regMailedit);
        Ename.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_DONE)
                    reg.performClick();
                return false;
            }
        });
        //anim
        shake= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
    }
    private boolean checkUname(String uname){//FIXME waiting asynctask to finish
        if(uname.equals(""))
            return false;
        CheckUname sesi=new CheckUname(Register.this);
        sesi.execute(uname);
        while(sesi.ret.equals(""));//barbar cuy
        if (sesi.ret.contains("nothing"))
            return true;
        else
            return false;
    }
    private boolean confPass(String pwd, String conf){
        if(pwd.equals(""))
            return false;
        if(pwd.equals(conf))
            return true;
        else
            return false;
    }
    private boolean checkEmail(String mail){
        if(mail.equals(""))
            return false;
        String[] temp=mail.split("@");
        if(temp.length==2 && temp[1].split("\\.").length>=2)
            return true;
        else
            return false;
    }
    public void reg(View view){
        EditText Rname, Uname, Passname,CPassname, Ename;
        String Rn,Un,Pn,Cn,En;
        boolean pass=true;
        Rname=(EditText)findViewById(R.id.regRnameedit);
        Uname=(EditText)findViewById(R.id.regUnameedit);
        Passname=(EditText)findViewById(R.id.regPwdedit);
        CPassname=(EditText)findViewById(R.id.conPwdedit);
        Ename=(EditText)findViewById(R.id.regMailedit);
        Rn=Rname.getText().toString();
        if(Rn.equals("")) {
            Rname.setText("");
            Rname.startAnimation(shake);
            pass = false;
        }
        Un=Uname.getText().toString();
        if(!checkUname(Un)) {
            Uname.setText("");
            Uname.setHint("Username is not available");
            Uname.startAnimation(shake);
            pass=false;
        }else{
            Uname.setHint("");
            Uname.setTextColor(Color.WHITE);
        }
        Pn=Passname.getText().toString();
        Cn=CPassname.getText().toString();
        if(!confPass(Pn,Cn)) {
            CPassname.setText("");
            CPassname.setHint("Password didn't match");
            Passname.startAnimation(shake);
            CPassname.startAnimation(shake);
            pass=false;
        }else{
            CPassname.setHint("");
        }
        En=Ename.getText().toString();
        if(!checkEmail(En)){
            Ename.setText("");
            Ename.setHint("Wrong Format");
            Ename.startAnimation(shake);
            pass=false;
        }else{
            Ename.setHint("");
        }
        if(pass){
            SignUp sesi= new SignUp(Register.this);
            sesi.execute(Rn,Un,Pn,En);
        }
    }
}

