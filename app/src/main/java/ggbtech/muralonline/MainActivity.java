package ggbtech.muralonline;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity{
    private String url;
    private int adapterCount;
    private Map<String, String> params;
    private RequestQueue rq;
    private Context myContext;
    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;
    Button btn;
    Aviso aviso = new Aviso();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myContext = getApplicationContext();

        mNestedScrollView = (NestedScrollView)findViewById(R.id.nestedLayout);
        mLinearLayout = new LinearLayout(myContext);
        btn = new Button(myContext);
        mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setPadding(10,10,10,10);
        mLinearLayout.setClipToPadding(false);

        BD bd = new BD(this);
        List<Aviso> list = bd.buscar();
        final AvisoAdapter avisoAdapter = new AvisoAdapter(this,list);

        adapterCount = avisoAdapter.getCount();

        for (int i = adapterCount-1; i >=0 ; i--) {
            View item = avisoAdapter.getView(i, null, null);
            mLinearLayout.addView(item);
        }

        mNestedScrollView.addView(mLinearLayout);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callByJsonArrayRequest(null);
                }
        });

        url = "http://10.0.2.2:8888/ProjetoAvisos/public/consultaAvisos.php";
        //url = "http://localhost:8888/ProjetoAvisos/public/consultaAvisos.php";
        rq = Volley.newRequestQueue(MainActivity.this);

        callByJsonArrayRequest(null);

    }

    /*
    public void callByJSONObjectRequest(View view){
        Log.i("Script", "ENTREI: callByJsonObjectRequest()");

        params = new HashMap<>();
        params.put("last_id", sharedpreferences.getString("last_id",null));
        //params.put("last_id","teste");
        //params.put("password","teste");
        //params.put("method", "web-data-jor");

        CustomJSONObjectResquest request = new CustomJSONObjectResquest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Script", "SUCCESS: "+response);
                        try {
                            String sit = (String) response.get("sit");
                            Log.i("Script", "Sit = "+sit);
                            Snackbar.make(mNestedScrollView, "Atualizado", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MainActivity.this, "SUCCESS :"+response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);

    }*/

    public Boolean exists (){
        if (sharedpreferences.contains("last_id")){
            return false;
        }else return true;
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
                        BD bd2 = new BD(getApplicationContext());
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
                                        Log.i("ID :", String.valueOf(json.getInt("aviso_id")));
                                        aviso.setId(json.getInt("aviso_id"));
                                        aviso.setImagem(json.getInt("grupo_id"));
                                        aviso.setTitulo(json.getString("titulo"));
                                        aviso.setConteudo(json.getString("conteudo"));
                                        aviso.setData(json.getString("data"));
                                        bd2.inserir(aviso);
                                    }
                                    editor.putString("last_id",String.valueOf(json.getInt("aviso_id")));
                                    editor.commit();
                                    Log.i("Atualizando SP","Last_id :"+sharedpreferences.getString("last_id",null));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        mLinearLayout.removeAllViews();
                        List<Aviso> list = bd2.buscar();
                        final AvisoAdapter avisoAdapter = new AvisoAdapter(MainActivity.this,list);

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
                        Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
                        /*
                        NetworkResponse resp = error.networkResponse;
                        if (resp != null && resp.data !=null){
                            switch(resp.statusCode){
                                case 400:Toast.makeText(MainActivity.this, "Bad Request : ERROR 400",Toast.LENGTH_LONG).show();break;
                                case 404:Toast.makeText(MainActivity.this, "NOT FOUND : ERROR 404",Toast.LENGTH_LONG).show();break;
                                case 401:Toast.makeText(MainActivity.this, "UNAUTHORIZED : ERROR 401",Toast.LENGTH_LONG).show();break;
                                case 403:Toast.makeText(MainActivity.this, "FORBIDDEN : ERROR 403",Toast.LENGTH_LONG).show();break;
                                case 408:Toast.makeText(MainActivity.this, "REQUEST TIMEDOUT : ERROR 408",Toast.LENGTH_LONG).show();break;

                            }
                        }*/

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddAvisoActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_list) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
