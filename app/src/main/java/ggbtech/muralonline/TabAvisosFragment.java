package ggbtech.muralonline;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ggbtech.muralonline.Classes.Aviso;
import ggbtech.muralonline.Classes.AvisoAdapter;
import ggbtech.muralonline.Classes.AtualizarEvent;
import ggbtech.muralonline.Classes.MySingleton;
import ggbtech.muralonline.DB.BD;
import ggbtech.muralonline.JSONClasses.CustomJSONArrayRequest;


public class TabAvisosFragment extends Fragment{
    private String url;
    private int adapterCount;
    private Map<String, String> params;
    private NestedScrollView mNestedScrollView;
    private  LinearLayout mLinearLayout;
    private Context myContext;
    public Aviso aviso = new Aviso();
    private  SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private int lastId_novo;
    private int lastId_antigo;

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
        myContext = getContext();

        mNestedScrollView = (NestedScrollView) rootView.findViewById(R.id.nestedLayout);
        mLinearLayout = new LinearLayout(myContext);
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

        if(!(sharedpreferences.contains("not"))){
            SharedPreferences.Editor edt = sharedpreferences.edit();
            edt.putString("not","0");
            edt.commit();
        }else{
            if(sharedpreferences.getString("not",null) != "0"){
                SharedPreferences.Editor edt = sharedpreferences.edit();
                edt.putString("not","0");
                Log.i("not", "setando pra 0");
                edt.commit();
            }
        }



        if(!(sharedpreferences.contains("url"))){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("url","http://ec2-52-67-73-128.sa-east-1.compute.amazonaws.com/muralonline/");
            editor.commit();
        }

        url = sharedpreferences.getString("url",null)+"consultaAvisosTeste.php";

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
        Log.i("Script","Tamanho "+length);
        if (length == 0){
            return "Atualizado";
        }else {
            if (length == 1)
                return "Você tem " + length + " novo aviso";
            else
                return "Você tem " + length + " novos avisos";
        }
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
            Log.i("Shared Preferences"," Last Id:"+ sharedpreferences.getString("last_id",null));
        }

        params = new HashMap<>();
        params.put("last_id","0" );
        Log.i("Script","last_id 0");
        CustomJSONArrayRequest request = new CustomJSONArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        Log.i("Script", "SUCCESS: "+response);
                        JSONObject json = null;
                        BD bd2 = new BD(myContext);
                        if (response.length()>0){
                            try {
                                bd2.esvaziarBanco();
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
                                    Log.i("Script","atualizando Last_id :"+sharedpreferences.getString("last_id",null));
                                    lastId_antigo = Integer.parseInt(sharedpreferences.getString("last_id",null));
                                if (sharedpreferences.getBoolean("firstrun", true)) {
                                    lastId_novo =response.length();
                                    sharedpreferences.edit().putBoolean("firstrun", false).commit();
                                }else{
                                    lastId_novo = json.getInt("avisos_id");
                                }

                                    Log.i("Script", "antigo :"+lastId_antigo+" novo: "+lastId_novo);
                                    Snackbar.make(mNestedScrollView, novosAvisos(lastId_novo-lastId_antigo), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    editor.putString("last_id",String.valueOf(json.getInt("avisos_id")));
                                    editor.commit();

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
                        }else{
                            bd2.esvaziarBanco();
                            mLinearLayout.removeAllViews();
                            Snackbar.make(mNestedScrollView, novosAvisos(0), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
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
        MySingleton.getInstance(myContext).addToRequestQueue(request);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(AtualizarEvent event){
        if(isConnected()){
            callByJsonArrayRequest(null);
        }else{
            Toast.makeText(myContext,"Sem conexão à Internet",Toast.LENGTH_SHORT).show();
        }
    }

}
