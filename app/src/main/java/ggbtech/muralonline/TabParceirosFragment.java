package ggbtech.muralonline;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class TabParceirosFragment extends Fragment {
    private ImageView mImageView;
    private RequestQueue rq;
    private Context myContext;
    private TextView mTextView;

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
        //LinearLayout ll = (LinearLayout)rootView.findViewById(R.id.ll3);


        myContext = getContext();

        //for (int i= 0;i<1;i++){
           // mImageView = (ImageView)  inflater.inflate(R.layout.quadro_parceiro,container,false);
            String url= "https://a2ua.com/imagem/imagem-006.jpg";
            //String url= "http://10.0.2.2/ProjetoAvisos/alarm"+i+".png";
            mImageView = (ImageView)rootView.findViewById(R.id.imgview);
            mTextView = (TextView)rootView.findViewById(R.id.txtParceiros);

            ImageRequest request = new ImageRequest(
                    url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            mTextView.setText("Imagem Baixada");
                            mImageView.setImageBitmap(response);

                        }
                    },0 , 0, null, Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("Erro ao baixar imagem");
                        }
                    });

        rq = Volley.newRequestQueue(myContext);
            rq.add(request);
            //ll.addView(mImageView);

        return rootView;
    }
}
