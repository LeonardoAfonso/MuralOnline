package ggbtech.muralonline;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import ggbtech.muralonline.Classes.MySingleton;

public class CheckVersionActivity extends AppCompatActivity {
    private int verCode;
    private PackageInfo pInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_version);
        pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        verCode = pInfo.versionCode;
        if(isConnected()){
            checkVersion(this);
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Sem Conexão à Internet")
                    .setMessage("Ops! Parece que você não possui nenhuma conexão a Internet. Fique conectado à Internet para se manter informado sobre os avisos da Paróquia Mão do Perpátuo Socorro.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    public void checkVersion(final Context ctx){
       StringRequest str = new StringRequest(Request.Method.GET,"http://ec2-52-67-73-128.sa-east-1.compute.amazonaws.com/muralonline/version.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("version response",response);
                        if(verCode == 0){
                            new AlertDialog.Builder(ctx)
                                    .setTitle("Manutenção")
                                    .setMessage("Opa! Parece que estamos realizando uma manutenção em nossos servidores, por favor volte após algumas horas!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            System.exit(0);
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .show();
                        }else{
                            if(verCode < Integer.parseInt(response)){
                                new AlertDialog.Builder(ctx)
                                        .setTitle("Atualização disponivel")
                                        .setMessage("Há uma nova versão do aplicativo na sua loja, atualize agora e não deixe de saber os eventos e reunião da Paróquia Mãe do Perpétuo Socorro")
                                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                                try {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                                } catch (android.content.ActivityNotFoundException anfe) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                                }
                                            }
                                        })
                                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                                System.exit(0);
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .show();

                            }else{
                                Intent it = new Intent(ctx,TabbedMainActivity.class);
                                startActivity(it);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("version response","erro");
                new AlertDialog.Builder(ctx)
                        .setTitle("Erro Verificando Aplicativo")
                        .setMessage("Ops! Tivemos um probleminha técnico! Verifique novamente sua conexão de rede e tente novamente. Caso o problema persista, tente mais tarde.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(str);
    }

    public Boolean isConnected(){
        ConnectivityManager cm =(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return connected;
    }

}
