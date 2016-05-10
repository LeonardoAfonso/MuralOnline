package ggbtech.muralonline;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.content.Context;


public class MainActivity extends AppCompatActivity implements TarefaInterface{


    private Context myContext;
    NestedScrollView mNestedScrollView;
    LinearLayout mLinearLayout;
    TextView tvConteudo;

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



        CardView cv = new CardView(myContext);
        LayoutParams cvParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        cvParams.setMargins(15, 15, 15, 15);
        cv.setLayoutParams(cvParams);
        cv.setContentPadding(15, 15, 15, 15);
        cv.setCardElevation(9);

        RelativeLayout rl = new RelativeLayout(myContext);
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        rl.setLayoutParams(rlParams);


        ImageView img = new ImageView(myContext);
        img.setId(21);
        img.setImageResource(R.drawable.logo_ufpa);
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imgParams.setMargins(0, 0, 16, 0);
        img.setLayoutParams(imgParams);


        TextView tvTitulo = new TextView(myContext);
        tvTitulo.setId(11);
        tvTitulo.setText("Aulas de Ingles");
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setTextSize(20);
        RelativeLayout.LayoutParams tituloParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        tituloParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        tituloParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        tvTitulo.setLayoutParams(tituloParams);


        tvConteudo = new TextView(myContext);
        tvConteudo.setId(12);
        tvConteudo.setText("Curso de ingles basico sera oferecido gratuitamente para interessados que morem na regiao da paroquia de N S do Perpetuo Socorro.");
        tvConteudo.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams conteudoParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        conteudoParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        conteudoParams.addRule(RelativeLayout.BELOW, tvTitulo.getId());
        tvConteudo.setLayoutParams(conteudoParams);

        TextView tvData = new TextView(myContext);
        tvData.setId(13);
        tvData.setText("25/04/2016");
        tvData.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams dataParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        dataParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        dataParams.addRule(RelativeLayout.BELOW, tvConteudo.getId());
        tvData.setLayoutParams(dataParams);

        rl.addView(img);
        rl.addView(tvTitulo);
        rl.addView(tvConteudo);
        rl.addView(tvData);

        cv.addView(rl);

        Aviso aviso = new Aviso();
        Aviso aviso2 = new Aviso();

        String texto = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure" +
                " dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur " +
                "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"";

        //mLinearLayout.addView(cv);
        mLinearLayout.addView(aviso.createCard(myContext,"TESTE",texto,"25/04/2016",1));
        mLinearLayout.addView(aviso2.createCard(myContext,"TESTE2",texto,"25/04/2016",2));
        mLinearLayout.addView(aviso.createCard(myContext,"TESTE3",texto,"25/04/2016",3));
        mLinearLayout.addView(aviso.createCard(myContext,"TESTE4",texto,"25/04/2016",4));
        mLinearLayout.addView(aviso.createCard(myContext,"TESTE5",texto,"25/04/2016",5));
        mNestedScrollView.addView(mLinearLayout);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consultarServer();
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void mudaTexto(String string) {
        tvConteudo.setText(string);

    }
}
