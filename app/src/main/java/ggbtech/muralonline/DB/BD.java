package ggbtech.muralonline.DB;

/**
 * Created by AEDI on 17/05/16.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ggbtech.muralonline.Classes.Aviso;

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
        valores.put("local", aviso.getLocal());
        valores.put("data", aviso.getData());
        valores.put("datafinal", aviso.getDatafinal());
        valores.put("hora", aviso.getHora());
        valores.put("horafinal", aviso.getHorafinal());
        valores.put("observacao", aviso.getObservacao());
        valores.put("contato", aviso.getContato());
        bd.insert("aviso", null, valores);
    }


    /*public void atualizar(Aviso aviso){
        ContentValues valores = new ContentValues();
        valores.put("titulo", aviso.getTitulo());
        valores.put("evento", aviso.getEvento());

        bd.update("aviso", valores, "_id = ?", new String[]{""+aviso.getId()});
    }*/


    /*public void deletar(Aviso aviso){
        bd.delete("aviso", "_id ="+aviso.getId(), null);
    }*/

    public void esvaziarBanco(){
        bd.delete("aviso",null,null);
    }

    public List<Aviso> buscar(){
        List<Aviso> list = new ArrayList<Aviso>();
        String[] colunas = new String[]{"_id", "imagem", "titulo", "evento", "local", "data", "datafinal", "hora", "horafinal", "observacao", "contato"};

        Cursor cursor = bd.query("aviso", colunas, null, null, null, null, "date(data) DESC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{

                Aviso a = new Aviso();
                a.setId(cursor.getInt(0));
                a.setImagem(cursor.getInt(1));
                a.setTitulo(cursor.getString(2));
                a.setEvento(cursor.getString(3));
                a.setLocal(cursor.getString(4));
                SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat formatto = new SimpleDateFormat("HH:mm");
                try{
                    String dt = myFormat.format(fromUser.parse(cursor.getString(5)));
                    a.setData(dt);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(cursor.getString(6) != null && !cursor.getString(6).equals("null") && !cursor.getString(6).isEmpty()){
                    try{
                        String dtfinal = myFormat.format(fromUser.parse(cursor.getString(6)));
                        a.setDatafinal(dtfinal);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                try{
                    String time = formatto.format(timeformat.parse(cursor.getString(7)));
                    a.setHora(time);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(cursor.getString(8) != null && !cursor.getString(8).equals("null") && !cursor.getString(8).isEmpty()){
                    try{
                        String timefinal = formatto.format(timeformat.parse(cursor.getString(8)));
                        a.setHorafinal(timefinal);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                a.setObservacao(cursor.getString(9));
                a.setContato(cursor.getString(10));
                list.add(a);

            }while(cursor.moveToNext());
        }

        return(list);
    }
}
