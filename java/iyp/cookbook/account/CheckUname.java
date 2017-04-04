package iyp.cookbook.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by yunus on 03/17/2017.
 */

public class CheckUname extends AsyncTask {
    public String ret="";
    private ProgressDialog progDailog;
    private Context con;
    public CheckUname(Context con){
        this.con=con;
        progDailog = new ProgressDialog(this.con,ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected void onPreExecute() {
        progDailog.setMessage("Checking ID...");
        progDailog.setCancelable(false);
        progDailog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String uname;
        uname=(String) objects[0];
        try{
            String link="http://iyunusp.gear.host/check.php?username="+uname;
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
                ret="nothing";
            return ret;
        }catch (Exception e){
            ret+="failed";
            return ret;
        }
    }
    @Override
    protected void onPostExecute(Object o) {
        progDailog.dismiss();
    }
}
