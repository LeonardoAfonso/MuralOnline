package ggbtech.muralonline;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AvisosFixosFragment extends Fragment {

    public AvisosFixosFragment() {
        // Required empty public constructor
    }


    public static AvisosFixosFragment newInstance() {
        AvisosFixosFragment fragment = new AvisosFixosFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_horarios_semanais, container, false);
        TextView tv = (TextView) v.findViewById(R.id.text3);
        tv.setText(this.getTag() + " Content");
        return v;
    }

}
