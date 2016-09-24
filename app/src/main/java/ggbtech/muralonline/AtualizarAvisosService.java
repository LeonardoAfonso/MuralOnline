package ggbtech.muralonline;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AtualizarAvisosService extends IntentService {
    private String url;
    private RequestQueue rq;
    private HashMap params;
    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public AtualizarAvisosService() {
        super("AtualizarAvisosService");
    }

    public Boolean exists (){
        if (sharedpreferences.contains("last_id")){
            return false;
        }else return true;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (exists()){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("last_id","0");
            editor.commit();
            Log.i("Shared Preferences","SP iniciado, Last Id:"+ sharedpreferences.getString("last_id",null));
        }else{
            Log.i("Shared Preferences","Ja existe");
            Log.i("Shared Preferences"," Last Id:"+ sharedpreferences.getString("last_id",null));}

        params = new HashMap<String, String>();
        params.put("last_id", sharedpreferences.getString("last_id",null));

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        url = "http://192.168.0.16/ProjetoAvisos/ProjetoAvisos/public/consultaAvisos.php";

        rq = Volley.newRequestQueue(this);
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
                        Log.i("Script", "SUCCESS: "+response);
                        JSONObject json;
                        try {
                            json = response.getJSONObject(0);
                            if(json.has("sit")){
                                Log.i("Script", "SUCCESS: "+response);
                                try {
                                    String sit = json.getString("sit");
                                    Log.i("Script", "Sit = "+sit);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Intent it = new Intent("NOVOS_AVISOS");
                                sendBroadcast(it);
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
        rq.add(request);
    }


}
