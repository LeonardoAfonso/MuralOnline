package ggbtech.muralonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddAvisoActivity extends AppCompatActivity {

    private Aviso aviso = new Aviso();
    private EditText etImagem;
    private EditText etTitulo;
    private EditText etConteudo;
    private EditText etData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aviso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etImagem = (EditText)findViewById(R.id.etCodigo);
        etConteudo = (EditText)findViewById(R.id.etConteudo);
        etTitulo = (EditText)findViewById(R.id.etTitulo);
        etData = (EditText)findViewById(R.id.etData);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aviso.setImagem(Integer.parseInt(etImagem.getText().toString()));
                aviso.setTitulo(etTitulo.getText().toString());
                aviso.setConteudo(etConteudo.getText().toString());
                aviso.setData(etData.getText().toString());

                BD bd = new BD(getApplicationContext());
                bd.inserir(aviso);

                Snackbar.make(view, "Usuario inserido com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
