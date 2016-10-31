package ggbtech.muralonline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TabSobreFragment extends Fragment {
    public TabSobreFragment() {
        // Required empty public constructor
    }


    public static TabSobreFragment newInstance() {
        TabSobreFragment fragment = new TabSobreFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_tab_sobre, container, false);
        TextView txt = (TextView) rootview.findViewById(R.id.texto_acesse);
        txt.setMovementMethod(LinkMovementMethod.getInstance());

        return rootview;
    }
}
