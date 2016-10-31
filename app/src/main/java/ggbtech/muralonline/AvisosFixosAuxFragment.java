package ggbtech.muralonline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ggbtech.muralonline.Classes.Aviso;
import ggbtech.muralonline.Classes.AvisoAdapter;


public class AvisosFixosAuxFragment extends Fragment {
    LinearLayout mLinearLayout;

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
        View v = inflater.inflate(R.layout.fragment_tab_horarios_semanais, container, false);
        String fragTag = this.getTag();

        switch(fragTag){
            case "TabDom": tabDom(v);break;
            case "TabSeg":tabSeg(v);break;
            case "TabTer":tabTer(v);break;
            case "TabQua": tabQua(v);break;
            case "TabQui":tabQui(v);break;
            case "TabSex":tabSex(v);break;
            case "TabSab":tabSab(v);break;
        }
        return v;
    }

    public void tabDom(View v){
        //View card = inflater.inflate(R.layout.aviso_cv,container,false);
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("07h - 8:30h - 17:30h - 19h");
        a.setImagem(51);
        avisosFixos.add(a);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }
    }

    public void tabSeg(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 18h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabTer(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("05h - 17h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 18h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        Aviso a2 = new Aviso();
        a2.setTitulo("Novena");
        a2.setEvento("06h - 07h - 08h - 09h - 10h - 11h - 12h - 13h - 14h - 15h - 16h - 18h - 19h - 20h - 21h");
        a2.setImagem(53);
        avisosFixos.add(a2);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabQua(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 18h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabQui(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 18h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        Aviso a2 = new Aviso();
        a2.setTitulo("Adoração");
        a2.setEvento("20h");
        a2.setImagem(52);
        avisosFixos.add(a2);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabSex(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 18h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabSab(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("8h até 12h");
        a1.setImagem(50);
        a1.setLocal("91 3233 1797 / 91 3264 9061");
        avisosFixos.add(a1);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.delete).setVisibility(View.GONE);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

}
