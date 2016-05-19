package ggbtech.muralonline;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements TarefaInterface{
    private String url;
    private Map<String, String> params;
    private RequestQueue rq;
    private Context myContext;
    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;

    public void consultarServer(){
        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute("http://192.168.100.13:8888/android_teste/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myContext = getApplicationContext();

        mNestedScrollView = (NestedScrollView)findViewById(R.id.nestedLayout);
        mLinearLayout = new LinearLayout(myContext);
        mLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setPadding(10,10,10,10);
        mLinearLayout.setClipToPadding(false);

        BD bd = new BD(this);
        List<Aviso> list = bd.buscar();
        AvisoAdapter avisoAdapter = new AvisoAdapter(this,list);

        final int adapterCount = avisoAdapter.getCount();

        for (int i = adapterCount-1; i >=0 ; i--) {
            View item = avisoAdapter.getView(i, null, null);
            mLinearLayout.addView(item);
        }

        mNestedScrollView.addView(mLinearLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());

                Snackbar.make(view, "Atualizado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        url = "http://localhost:8888/PhpProject1/index.php";
        rq = Volley.newRequestQueue(MainActivity.this);

        Button btn = new Button(myContext);
        btn.setText("OK");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callByJSONObjectRequest(v);
            }
        });

        mLinearLayout.addView(btn);





    }


    public void callByJSONObjectRequest(View view){
        params = new HashMap<>();
        params.put("email","teste");
        params.put("senha","teste");
        CustomJSONObjectResquest cjor = new CustomJSONObjectResquest(Request.Method.POST, url, params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Script", "SUCESS: " + response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"ERRO: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        cjor.setTag("tag");
        rq.add(cjor);

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
    public void mudaTexto(String string) {

    }
}
