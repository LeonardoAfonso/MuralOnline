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
        BDCore auxBd = BDCore.getInstance(context);
        bd = auxBd.getWritableDatabase();
    }


    public void inserir(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put("imagem",aviso.getImagem());
        valores.put("titulo", aviso.getTitulo());
        valores.put("evento", aviso.getEvento());
        valores.put("data", aviso.getData());
        valores.put("hora", aviso.getHora());
        valores.put("observacao", aviso.getObservacao());
        valores.put("contato", aviso.getContato());

        bd.insert("aviso", null, valores);
    }


    public void atualizar(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put("titulo", aviso.getTitulo());
        valores.put("evento", aviso.getEvento());

        bd.update("aviso", valores, "_id = ?", new String[]{""+aviso.getId()});
    }


    public void deletar(Aviso aviso){
        bd.delete("aviso", "_id ="+aviso.getId(), null);
    }


    public List<Aviso> buscar(){
        List<Aviso> list = new ArrayList<Aviso>();
        String[] colunas = new String[]{"_id", "imagem", "titulo", "evento", "data", "hora", "observacao", "contato"};

        Cursor cursor = bd.query("aviso", colunas, null, null, null, null, "_id ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Aviso a = new Aviso();
                a.setId(cursor.getInt(0));
                a.setImagem(cursor.getInt(1));
                a.setTitulo(cursor.getString(2));
                a.setEvento(cursor.getString(3));
                a.setData(cursor.getString(4));
                a.setHora(cursor.getString(5));
                a.setObservacao(cursor.getString(6));
                a.setContato(cursor.getString(7));
                list.add(a);

            }while(cursor.moveToNext());
        }

        return(list);
    }
}
