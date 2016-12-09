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

    public Boolean exists (SharedPreferences sp){
        if (sp.contains("last_id")){
            return false;
        }else return true;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sharedpreferences  = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (exists(sharedpreferences)){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("last_id","1");
            editor.commit();
            Log.i("Shared Preferences","SP iniciado, Last Id:"+ sharedpreferences.getString("last_id",null));
        }else{
            Log.i("Shared Preferences","Ja existe");
            Log.i("Shared Preferences"," Last Id:"+ sharedpreferences.getString("last_id",null));}

        params = new HashMap<String, String>();
        params.put("last_id","0");//sharedpreferences.getString("last_id",null));

        url= sharedpreferences.getString("url",null)+"consultaAvisosTeste.php";
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
                        JSONObject json;
                        try {
                            int length = response.length();
                            if(length>0){
                                json = response.getJSONObject(length-1);
                                int lastId_antigo = Integer.parseInt(sharedpreferences.getString("last_id",null));
                                int lastId_novo = json.getInt("avisos_id");
                                if((lastId_novo-lastId_antigo) == 0){
                                    Log.i("Script","Atualizado");
                                }else{
                                    Intent it = new Intent("NOVOS_AVISOS");
                                    it.putExtra("num_avisos",lastId_novo-lastId_antigo);
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
