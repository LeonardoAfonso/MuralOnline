package ggbtech.muralonline;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ggbtech.muralonline.Classes.Aviso;
import ggbtech.muralonline.Classes.AvisoAdapter;
import ggbtech.muralonline.Classes.MySingleton;
import ggbtech.muralonline.DB.BD;
import ggbtech.muralonline.JSONClasses.CustomJSONArrayRequest;


public class AvisosFixosAuxFragment extends Fragment {
    private LinearLayout mLinearLayout;
    private HashMap<String,String> params;
    private String url;
    private Context myContext;
    private View v;
    private ProgressDialog progressDialog;

    public AvisosFixosAuxFragment() {
        // Required empty public constructor
    }

    public static AvisosFixosAuxFragment newInstance() {
        AvisosFixosAuxFragment fragment = new AvisosFixosAuxFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myContext = getContext();
        url ="http://ec2-52-67-73-128.sa-east-1.compute.amazonaws.com/muralonline/consultaAvisosFixos.php";
        v = inflater.inflate(R.layout.fragment_tab_horarios_semanais, container, false);
        String fragTag = this.getTag();
        switch(fragTag){
            case "TabDom": getAvisosFixos(0);break;
            case "TabSeg":getAvisosFixos(1);break;
            case "TabTer":getAvisosFixos(2);break;
            case "TabQua": getAvisosFixos(3);break;
            case "TabQui":getAvisosFixos(4);break;
            case "TabSex":getAvisosFixos(5);break;
            case "TabSab":getAvisosFixos(6);break;
        }
        return v;
    }

    public void getAvisosFixos(int codigo_dia){
        progressDialog = ProgressDialog.show(myContext,"Atualizando","Aguarde enquanto atualizamos os avisos", false, true);
        //dialog.setIcon(R.drawable.ic_launcher);
        progressDialog.setCancelable(false);
        params = new HashMap<>();
        params.put("codigo_dia", String.valueOf(codigo_dia));
        Log.i("Script codigo_dia", String.valueOf(codigo_dia));
        CustomJSONArrayRequest request = new CustomJSONArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS: "+response);
                        JSONObject json = null;
                        if (response.length()>0){
                            try {
                                mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);
                                mLinearLayout.removeAllViews();
                                ArrayList<Aviso> avisosFixos = new ArrayList<>();
                                for (int i=0;i<response.length();i++){
                                    Aviso aviso = new Aviso();
                                    json = response.getJSONObject(i);
                                    Log.i("ID :", String.valueOf(json.getInt("id")));
                                    aviso.setId(json.getInt("id"));
                                    aviso.setImagem(json.getInt("grupo_id"));
                                    aviso.setTitulo(json.getString("titulo"));
                                    aviso.setEvento(json.getString("evento"));
                                    aviso.setLocal(json.getString("local"));
                                    aviso.setData(json.getString("data"));
                                    aviso.setDatafinal(json.getString("datafinal"));
                                    aviso.setHora(json.getString("hora"));
                                    aviso.setHorafinal(json.getString("horafinal"));
                                    aviso.setObservacao(json.getString("observacao"));
                                    aviso.setContato(json.getString("contato"));
                                    avisosFixos.add(aviso);
                                }
                                final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);
                                int adapterCount = avisoAdapter.getCount();
                                for (int j = 0; j <adapterCount ; j++) {
                                    View item = avisoAdapter.getView(j, null, null);
                                    item.findViewById(R.id.hora).setVisibility(View.GONE);
                                    mLinearLayout.addView(item);
                                }
                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        if (error instanceof AuthFailureError) {
                            Toast.makeText(myContext,"AuthFailureError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(myContext,"ServerError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(myContext,"NetworkError" ,Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(myContext,"ParseError" ,Toast.LENGTH_LONG).show();
                        }
                    }
                });

        request.setTag("tag");
        MySingleton.getInstance(myContext).addToRequestQueue(request);
    }

}
