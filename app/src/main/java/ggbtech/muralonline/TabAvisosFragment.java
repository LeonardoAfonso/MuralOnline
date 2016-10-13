package ggbtech.muralonline;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ggbtech.muralonline.Classes.Aviso;
import ggbtech.muralonline.Classes.AvisoAdapter;
import ggbtech.muralonline.DB.BD;
import ggbtech.muralonline.JSONClasses.CustomJSONArrayRequest;


public class TabAvisosFragment extends Fragment{
    private String url;
    private int adapterCount;
    private Map<String, String> params;
    private RequestQueue rq;
    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;
    private Context myContext;
    Button btn;
    Aviso aviso = new Aviso();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    
    public TabAvisosFragment() {
        // Required empty public constructor
    }

    public static TabAvisosFragment newInstance() {
        TabAvisosFragment fragment = new TabAvisosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed_main, container, false);

        //View mainView = inflater.inflate(R.layout.activity_tabbed_main,container,false);

        //FloatingActionButton fab = (FloatingActionButton)mainView.findViewById(R.id.fab);

        myContext = getContext();

        boolean alarmeAtivo = (PendingIntent.getBroadcast(myContext, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){
            Log.i("Script", "Novo alarme");
            Intent intent = new Intent("ALARME_DISPARADO");
            PendingIntent p = PendingIntent.getBroadcast(myContext, 0, intent, 0);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 30000, p);
        }
        else{
            Log.i("Script", "Alarme ja ativo");
        }

        mNestedScrollView = (NestedScrollView)rootView.findViewById(R.id.nestedLayout);
        mLinearLayout = new LinearLayout(myContext);
        btn = new Button(myContext);
        mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setPadding(10,10,10,10);

        BD bd = new BD(myContext);
        List<Aviso> list = bd.buscar();
        final AvisoAdapter avisoAdapter = new AvisoAdapter(myContext,list);

        adapterCount = avisoAdapter.getCount();
        for (int i = adapterCount-1; i >=0 ; i--) {
            View item = avisoAdapter.getView(i, null, null);
            mLinearLayout.addView(item);
        }

        mNestedScrollView.addView(mLinearLayout);

        sharedpreferences = myContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //url = "http://10.0.2.2:8888/ProjetoAvisos/public/consultaAvisos.php";
        if(!(sharedpreferences.contains("url"))){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("url","http://192.168.43.160/ProjetoAvisos/consultaAvisos.php");
            editor.commit();
        }

        url = sharedpreferences.getString("url",null)  ;//"http://192.168.100.23/ProjetoAvisos/consultaAvisos.php";
        rq = Volley.newRequestQueue(myContext);

        if(isConnected()){
            callByJsonArrayRequest(null);
        }else{
            Toast.makeText(myContext,"Sem conexão à Internet",Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }


    public Boolean exists (){
        if (sharedpreferences.contains("last_id")){
            return false;
        }else return true;
    }

    public String novosAvisos(int length){
        if (length == 1)
            return "Você tem "+ length +" novo aviso";
        else
            return "Você tem "+length+" novos avisos";
    }

    public Boolean isConnected(){
        ConnectivityManager cm =(ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return connected;
    }

    public void callByJsonArrayRequest(View view){
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
        Log.i("Script", "last_id: "+sharedpreferences.getString("last_id",null));

        CustomJSONArrayRequest request = new CustomJSONArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        Log.i("Script", "SUCCESS: "+response);
                        JSONObject json;
                        BD bd2 = new BD(myContext);
                        try {
                            json = response.getJSONObject(0);
                            if(json.has("sit")){
                                Log.i("Script", "SUCCESS: "+response);
                                try {
                                    String sit = (String) json.getString("sit");
                                    Log.i("Script", "Sit = "+sit);
                                    Snackbar.make(mNestedScrollView, "Atualizado", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                for (int i=0;i<response.length();i++){
                                    json = response.getJSONObject(i);
                                    Log.i("ID :", String.valueOf(json.getInt("avisos_id")));
                                    aviso.setId(json.getInt("avisos_id"));
                                    aviso.setImagem(json.getInt("grupo_id"));
                                    aviso.setTitulo(json.getString("titulo"));
                                    aviso.setEvento(json.getString("evento"));
                                    aviso.setLocal(json.getString("local"));
                                    aviso.setData(json.getString("data"));
                                    aviso.setHora(json.getString("hora"));
                                    aviso.setObservacao(json.getString("observacao"));
                                    aviso.setContato(json.getString("contato"));
                                    bd2.inserir(aviso);
                                }
                                editor.putString("last_id",String.valueOf(json.getInt("avisos_id")));
                                editor.commit();
                                Log.i("Atualizando SP","Last_id :"+sharedpreferences.getString("last_id",null));
                                Snackbar.make(mNestedScrollView, novosAvisos(response.length()), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mLinearLayout.removeAllViews();
                        List<Aviso> list = bd2.buscar();
                        final AvisoAdapter avisoAdapter = new AvisoAdapter(myContext,list);
                        adapterCount = avisoAdapter.getCount();

                        for (int j = adapterCount-1; j >=0 ; j--) {
                            View item = avisoAdapter.getView(j, null, null);
                            mLinearLayout.addView(item);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof AuthFailureError) {
                            Toast.makeText(myContext,"AuthFailureError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(myContext,"ServerError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(myContext,"NetworkError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(myContext,"ParseError" ,Toast.LENGTH_LONG).show();
                        }
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }

    @Override
    public void onStop(){
        super.onStop();
        rq.cancelAll("tag");
    }
}
