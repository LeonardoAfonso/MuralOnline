package ggbtech.muralonline.DB;

/**
 * Created by AEDI on 17/05/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "teste";
    private static final int VERSAO_BD = 12;

    private static BDCore mInstance = null;

    public static BDCore getInstance (Context ctx){
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new BDCore(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private BDCore(Context ctx){
        super(ctx,NOME_BD,null,VERSAO_BD);
    }

    //public BDCore(Context ctx){
     //   super(ctx, NOME_BD, null, VERSAO_BD);
    //}


/*
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table aviso(_id integer primary key autoincrement, imagem int not null ,titulo text not null, conteudo text not null, data text not null);");
    }*/

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table aviso(_id integer primary key autoincrement, imagem int not null ,titulo text not null, evento text not null, local text not null, data text not null, datafinal text null, hora text" +
                ", horafinal text , observacao text not null, contato text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table aviso;");
        onCreate(bd);
    }

}
