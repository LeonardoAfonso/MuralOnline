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
        a.setTitulo("Missas");
        a.setEvento("07h - 8:30h - 17:30h - 19h");
        a.setImagem(51);
        avisosFixos.add(a);


        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }
    }

    public void tabSeg(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 12h / 13:30h até 18h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a2 = new Aviso();
        a2.setTitulo("Terço dos Homens");
        a2.setEvento("20h");
        a2.setImagem(54);
        avisosFixos.add(a2);

        Aviso a3 = new Aviso();
        a3.setTitulo("Pastoral Social");
        a3.setEvento("8:30h até 12h / 14:30h até 18h");
        a3.setImagem(10);
        avisosFixos.add(a3);


        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabTer(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 12h / 13:30h até 18h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        Aviso a = new Aviso();
        a.setTitulo("Missas");
        a.setEvento("05:30h - 6:30h - 17:30h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a2 = new Aviso();
        a2.setTitulo("Novenas");
        a2.setEvento("06h (25 minutos) - 07h - 08h - 09h - 10h - 11h - 12h - 13h - 14h - 15h - 16h - 18h - 19h - 20h - 21h");
        a2.setImagem(53);
        avisosFixos.add(a2);

        Aviso a3 = new Aviso();
        a3.setTitulo("Pastoral Social");
        a3.setEvento("8:30h até 12h / 14:30h até 18h");
        a3.setImagem(10);
        avisosFixos.add(a3);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabQua(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 12h / 13:30h até 18h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        Aviso a3 = new Aviso();
        a3.setTitulo("Pastoral Social");
        a3.setEvento("8:30h até 12h / 14:30h até 18h");
        a3.setImagem(10);
        avisosFixos.add(a3);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabQui(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 12h / 13:30h até 18h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a2 = new Aviso();
        a2.setTitulo("Adoração");
        a2.setEvento("20h");
        a2.setImagem(52);
        avisosFixos.add(a2);

        Aviso a3 = new Aviso();
        a3.setTitulo("Pastoral Social");
        a3.setEvento("8:30h até 12h / 14:30h até 18h");
        a3.setImagem(10);
        avisosFixos.add(a3);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabSex(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 12h / 13:30h até 18h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        Aviso a3 = new Aviso();
        a3.setTitulo("Pastoral Social");
        a3.setEvento("8:30h até 12h / 14:30h até 18h");
        a3.setImagem(10);
        avisosFixos.add(a3);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

    public void tabSab(View v){
        ArrayList<Aviso> avisosFixos = new ArrayList<>();
        Aviso a1 = new Aviso();
        a1.setTitulo("Secretaria");
        a1.setEvento("7:30h até 11:30h");
        a1.setImagem(50);
        a1.setLocal("(91)  3233-1797 / (91) 98874-3591 (WhatsApp)");
        avisosFixos.add(a1);

        /*Aviso a2 = new Aviso();
        a2.setTitulo("Pároco Informa");
        a2.setEvento("Todo o 1º sábado de mês, reunião com todos os coordenadores de comunidades");
        a2.setLocal("15h");
        a2.setImagem(50);
        avisosFixos.add(a2);*/

        Aviso a = new Aviso();
        a.setTitulo("Missa");
        a.setEvento("19h");
        a.setImagem(51);
        avisosFixos.add(a);

        final AvisoAdapter avisoAdapter = new AvisoAdapter(getContext(),avisosFixos);

        int adapterCount = avisoAdapter.getCount();
        mLinearLayout = (LinearLayout)v.findViewById(R.id.ll2);

        for (int j = 0; j <adapterCount ; j++) {
            View item = avisoAdapter.getView(j, null, null);
            item.findViewById(R.id.hora).setVisibility(View.GONE);
            mLinearLayout.addView(item);
        }

    }

}
