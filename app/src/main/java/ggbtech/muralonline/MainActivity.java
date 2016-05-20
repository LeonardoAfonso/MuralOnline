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
import com.android.volley.toolbox.Volley;
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

        mLinearLayout.addView(btn);
        mNestedScrollView.addView(mLinearLayout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLinearLayout.removeAllViews();

                for (int i = adapterCount-1; i >=0 ; i--) {
                    View item = avisoAdapter.getView(i, null, null);
                    mLinearLayout.addView(item);
                }

                Snackbar.make(view, "Atualizado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        url = "http://www.aedi.ufpa.br/~leonardo/teste.php";
        rq = Volley.newRequestQueue(MainActivity.this);


        btn.setText("OK");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callByJSONObjectRequest(null);
            }
        });

    }


    public void callByJSONObjectRequest(View view){
        Log.i("Script", "ENTREI: callByJsonObjectRequest()");

        params = new HashMap<>();
        params.put("email","teste");
        params.put("password","teste");
        params.put("method", "web-data-jor");

        CustomJSONObjectResquest request = new CustomJSONObjectResquest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Script", "SUCCESS: "+response);
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

}
