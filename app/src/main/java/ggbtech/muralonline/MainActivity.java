package ggbtech.muralonline;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.content.Context;

import java.util.List;


public class MainActivity extends AppCompatActivity implements TarefaInterface{


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
