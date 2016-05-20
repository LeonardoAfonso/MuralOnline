package ggbtech.muralonline;

/**
 * Created by AEDI on 17/05/16.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context context){
        BDCore auxBd = new BDCore(context);
        bd = auxBd.getWritableDatabase();
    }


    public void inserir(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put("imagem",aviso.getImagem());
        valores.put("titulo", aviso.getTitulo());
        valores.put("conteudo", aviso.getConteudo());
        valores.put("data", aviso.getData());

        bd.insert("aviso", null, valores);
    }


    public void atualizar(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put("titulo", aviso.getTitulo());
        valores.put("conteudo", aviso.getConteudo());

        bd.update("aviso", valores, "_id = ?", new String[]{""+aviso.getId()});
    }


    public void deletar(Aviso aviso){
        bd.delete("aviso", "_id ="+aviso.getId(), null);
    }


    public List<Aviso> buscar(){
        List<Aviso> list = new ArrayList<Aviso>();
        String[] colunas = new String[]{"_id", "imagem", "titulo", "conteudo", "data"};

        Cursor cursor = bd.query("aviso", colunas, null, null, null, null, "titulo ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Aviso a = new Aviso();
                a.setId(cursor.getLong(0));
                a.setImagem(cursor.getInt(1));
                a.setTitulo(cursor.getString(2));
                a.setConteudo(cursor.getString(3));
                a.setData(cursor.getString(4));
                list.add(a);

            }while(cursor.moveToNext());
        }

        return(list);
    }
}
