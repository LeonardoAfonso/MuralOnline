package ggbtech.muralonline;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import ggbtech.muralonline.Classes.MySingleton;

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
        rq =  MySingleton.getInstance(myContext).getRequestQueue();
        sharedpreferences  = myContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        /*
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
        MySingleton.getInstance(myContext).addToRequestQueue(stringRequest);
        */
        final View rl = inflater.inflate(R.layout.quadro_parceiro,container,false);
        urlImagens1 = sharedpreferences.getString("url",null)+"/parceiros/anuncio0.png";
        ImageView img = (ImageView)rl.findViewById(R.id.parceiro1);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(myContext).build();
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.icon_erro)
                .showImageOnFail(R.drawable.icon_erro)
                .showImageOnLoading(R.drawable.icon_anuncio).build();
        ImageLoader.getInstance().init(config);
        ImageLoader.getInstance().displayImage(urlImagens1,img,option);
        urlImagens2 = sharedpreferences.getString("url",null)+"/parceiros/anuncio1.png";
        ImageView img2 = (ImageView)rl.findViewById(R.id.parceiro2);
        ImageLoader.getInstance().displayImage(urlImagens2,img2,option);
        ll.addView(rl);

        return rootView;
    }

}
