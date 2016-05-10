package ggbtech.muralonline;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Leonardo Afonso on 5/2/2016.
 */
public class Tarefa extends AsyncTask<String, String, String> {
    private Context context;
    private TarefaInterface ti;
    private ProgressDialog progress;

    public Tarefa(Context context, TarefaInterface ti){
        this.context = context;
        this.ti=ti;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.context);
        progress.setMessage("atualizando");
        progress.show();


    }

    @Override
    protected String doInBackground(String... urls) {
        String data = null;
        try{
         URL url = new URL(urls[0]);
         HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
         InputStream input = conexao.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(input));
            StringBuilder total = new StringBuilder(input.available());
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            data = total.toString();
         }catch(IOException e){}
            return (data);
    }

    protected void onPostExecute(String result) {
        progress.setMessage("atualizado");
        ti.mudaTexto(result);
        progress.dismiss();

    }
}