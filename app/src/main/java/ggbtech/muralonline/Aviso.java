package ggbtech.muralonline;


import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Leonardo Afonso on 4/25/2016.
 */
public class Aviso {
    private int id;
    private String titulo;
    private String data;
    private int imagem;
    private String evento;
    private String local;
    private String hora;
    private String observacao;
    private String contato;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }




    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }


    //CardView cv;
    //ImageView img;
    //RelativeLayout rl;
    //TextView tvTitulo;
    //TextView tvConteudo;
    //TextView tvData;






    /*
    public View createCard (Context myContext,String titulo,String conteudo, String data,int imagem){
        Random rand = new Random();

        cv = new CardView(myContext);
        LinearLayout.LayoutParams cvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cvParams.setMargins(15, 15, 15, 15);
        cv.setLayoutParams(cvParams);
        cv.setContentPadding(15, 15, 15, 15);
        cv.setCardElevation(9);

        rl = new RelativeLayout(myContext);
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rl.setLayoutParams(rlParams);


        img = new ImageView(myContext);
        img.setId(rand.nextInt((int) SystemClock.currentThreadTimeMillis()));
        switch (imagem){
            case 1: img.setImageResource(R.drawable.menor);break;
            case 2: img.setImageResource(R.drawable.emcri);break;
            case 3: img.setImageResource(R.drawable.familiar);break;
            case 4: img.setImageResource(R.drawable.juventude);break;
            case 5: img.setImageResource(R.drawable.catequetica);break;

        }
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imgParams.setMargins(0, 0, 16, 0);
        img.setLayoutParams(imgParams);


        tvTitulo = new TextView(myContext);
        tvTitulo.setId(rand.nextInt((int) SystemClock.currentThreadTimeMillis()));
        tvTitulo.setText(titulo);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setTextSize(20);
        RelativeLayout.LayoutParams tituloParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tituloParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        tituloParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        tvTitulo.setLayoutParams(tituloParams);


        tvConteudo = new TextView(myContext);
        tvConteudo.setId(rand.nextInt((int) SystemClock.currentThreadTimeMillis()));
        tvConteudo.setText(conteudo);
        tvConteudo.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams conteudoParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        conteudoParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        conteudoParams.addRule(RelativeLayout.BELOW, tvTitulo.getId());
        tvConteudo.setLayoutParams(conteudoParams);

        tvData = new TextView(myContext);
        tvData.setId(rand.nextInt((int) SystemClock.currentThreadTimeMillis()));
        tvData.setText(data);
        tvData.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams dataParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dataParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
        dataParams.addRule(RelativeLayout.BELOW, tvConteudo.getId());
        tvData.setLayoutParams(dataParams);

        rl.addView(img);
        rl.addView(tvTitulo);
        rl.addView(tvConteudo);
        rl.addView(tvData);

        cv.addView(rl);

        return cv;
    }*/



}
