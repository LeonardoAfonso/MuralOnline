package ggbtech.muralonline;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TabParceirosFragment extends Fragment {
    private RequestQueue rq;
    private Context myContext;
    private String url;
    private String urlImagens1;
    private String urlImagens2;
    private int qtdParceiros;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedpreferences;

    public TabParceirosFragment() {
        // Required empty public constructor
    }

    public static TabParceirosFragment newInstance() {
        TabParceirosFragment fragment = new TabParceirosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_parceiros, container, false);
        LinearLayout ll = (LinearLayout)rootView.findViewById(R.id.ll3);
        myContext = getContext();
        rq = Volley.newRequestQueue(myContext);

        sharedpreferences  = myContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        url = sharedpreferences.getString("url",null)+"parceiros.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("str", response);
                qtdParceiros = Integer.parseInt(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("str",String.valueOf(error));
            }
        });
        stringRequest.setTag("imagem");
        rq.add(stringRequest);

       for (int i=0;i<=qtdParceiros;i+=2){

           final View rl = inflater.inflate(R.layout.quadro_parceiro,container,false);

           urlImagens1 = sharedpreferences.getString("url",null)+"/parceiros/anuncio"+i+".png";
           ImageRequest request = new ImageRequest(
                   urlImagens1,
                   new Response.Listener<Bitmap>() {
                       @Override
                       public void onResponse(Bitmap response) {
                           Log.i("url",urlImagens1);
                           ImageView img = (ImageView)rl.findViewById(R.id.parceiro1);
                           img.setImageBitmap(response);
                       }
                   },0 , 0, null, Bitmap.Config.RGB_565,
                   new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Log.i("img","erro baixando imagem 1");
                       }
                   });
           request.setTag("imagem");
           rq.add(request);

           urlImagens2 = sharedpreferences.getString("url",null)+"/parceiros/anuncio"+(i+1)+".png";
           ImageRequest request2 = new ImageRequest(
                   urlImagens2,
                   new Response.Listener<Bitmap>() {
                       @Override
                       public void onResponse(Bitmap response) {
                           Log.i("url",urlImagens2);
                           ImageView img2 = (ImageView) rl.findViewById(R.id.parceiro2);
                           img2.setImageBitmap(response);
                       }
                   },0 , 0, null, Bitmap.Config.RGB_565,
                   new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Log.i("img","erro baixando imagem 2");
                       }
                   });
           request2.setTag("imagem");
           rq.add(request2);
           ll.addView(rl);
       }


        return rootView;
    }

    @Override
    public void onStop(){
        super.onStop();
        rq.cancelAll("imagem");
    }
}
