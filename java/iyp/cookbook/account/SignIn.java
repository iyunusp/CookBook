package iyp.cookbook.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import iyp.cookbook.MenuList;

/**
 * Created by yunus on 03/17/2017.
 */

public class SignIn extends AsyncTask {

    public String ret="";
    private String uname,pass;
    private ProgressDialog progDailog;
    private Context con;
    private View v;
    public SignIn(Context con ,View v){
        this.v=v;
        this.con=con;
        progDailog = new ProgressDialog(this.con,ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected void onPreExecute() {
        progDailog.setMessage("Signing In...");
        progDailog.setCancelable(false);
        progDailog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        uname=(String) objects[0];
        pass=(String) objects[1];
        try{
            String link="http://iyunusp.gear.host/loginApp.php?username="+uname+"&password="+pass;
            URL url=new URL(link);
            HttpClient client=new DefaultHttpClient();
            HttpGet request=new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response=client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb= new StringBuffer("");
            String line="";
            while((line = in.readLine())!=null){
                sb.append(line);
                break;
            }
            in.close();
            ret=sb.toString();
            if(ret.equals(""))
                ret="failed";
            return ret;
        }catch (Exception e){
            ret="error";
            return ret;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (ret.equals("failed")) {
            Snackbar.make(v, "Username or Password is Incorrect", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            progDailog.dismiss();
            return;
        } else if(ret.equals("error")){
            Snackbar.make(v, "Connection Failed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            progDailog.dismiss();
            return;
        }
        String[] dataUser=ret.split("#");
        if(dataUser.length<4){
            Snackbar.make(v, "Database Error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            progDailog.dismiss();
            return;
        }
        Intent intent = new Intent(con, MenuList.class);
        String phone="unknown",address="unknown";
        if(dataUser.length==4)
            phone=dataUser[3];
        if(dataUser.length==5)
            address=dataUser[4];
        //try parcelable
        Account acount= new Account(uname,pass,dataUser[1],dataUser[2],address,phone);
        intent.putExtra("user",acount);
        //end
        progDailog.dismiss();
        con.startActivity(intent);
    }
}
