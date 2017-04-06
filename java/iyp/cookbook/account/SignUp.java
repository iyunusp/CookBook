package iyp.cookbook.account;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.net.URL;

/**
 * Created by yunus on 03/17/2017.
 */

public class SignUp extends AsyncTask {
    public String ret="";
    private ProgressDialog progDailog;
    private Context con;
    private Activity act;
    public SignUp(Context con){
        this.con=con;
        this.act=(Activity)con;
        progDailog = new ProgressDialog(this.con,ProgressDialog.STYLE_SPINNER);
    }
    @Override
    protected void onPreExecute() {
        progDailog.setMessage("Signing Up...");
        progDailog.setCancelable(false);
        progDailog.show();
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        String Rname,Uname,Pname,Mname;
        Rname=(String) objects[0];
        Uname=(String) objects[1];
        Pname=(String) objects[2];
        Mname=(String) objects[3];
        try{
            String link="http://iyunusp.gear.host/register.php?username="+Uname+"&password="+Pname+"&realname="+Rname+"&mail="+Mname;
            URL url=new URL(link);
            HttpClient client=new DefaultHttpClient();
            HttpGet request=new HttpGet();
            request.setURI(new URI(link));
            client.execute(request);
            ret="success";
        }catch (Exception e){
            ret="Connection Failed";
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (ret.contains("success")) {
            Toast.makeText(con,"SignUp Success",Toast.LENGTH_LONG).show();
            progDailog.dismiss();
            this.act.finish();
        }else {
            Toast.makeText(con,ret,Toast.LENGTH_SHORT).show();
            progDailog.dismiss();
        }
    }
}
