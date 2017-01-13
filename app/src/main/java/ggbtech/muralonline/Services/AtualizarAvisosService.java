package ggbtech.muralonline.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import ggbtech.muralonline.Classes.MySingleton;
import ggbtech.muralonline.JSONClasses.CustomJSONArrayRequest;


public class AtualizarAvisosService extends IntentService {
    private String url;
    private RequestQueue rq;
    private HashMap params;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedpreferences;

    public AtualizarAvisosService() {
        super("AtualizarAvisosService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sharedpreferences  = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        params = new HashMap<>();
        params.put("last_id","0");
        url= sharedpreferences.getString("urlConsulta",null);
        //url = "http://192.168.0.16/ProjetoAvisos/consultaAvisos.php";
        Log.i("Service", "Servico");
        //rq = Volley.newRequestQueue(this);
        callJsonRequest();
        //rq.cancelAll("tag");
    }

    public void callJsonRequest(){
        CustomJSONArrayRequest request = new CustomJSONArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS service: "+response);
                        try {
                            int length = response.length();
                            if(length > 0){
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                Date last_dateCreate_antigo = new Date();
                                Date date;

                                try {
                                    last_dateCreate_antigo = dateFormat.parse(sharedpreferences.getString("last_createDate",null));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                int aviso_counter = 0;
                                JSONObject j = null;
                                for (int i=0;i< response.length();i++){
                                    try{
                                        Log.i("Script","laco de contagem de avisos: "+ i);
                                        j = response.getJSONObject(i);
                                        date = dateFormat.parse(j.getString("datecreated"));
                                        if (date.after(last_dateCreate_antigo)){
                                            Log.i("Script",i + "e aviso recente");
                                            aviso_counter++;
                                        }
                                    }catch (ParseException e){
                                        e.printStackTrace();
                                    }
                                }

                                if(aviso_counter > 0){
                                    Log.i("Script","Atualizado");
                                }else{
                                    Intent it = new Intent("NOVOS_AVISOS");
                                    Log.i("Script","contador de avisos : "+aviso_counter);
                                    it.putExtra("num_avisos",aviso_counter);
                                    sendBroadcast(it);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        request.setTag("tag");
        MySingleton.getInstance(this).addToRequestQueue(request);
    }


}
