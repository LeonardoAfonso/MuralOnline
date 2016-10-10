package ggbtech.muralonline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabHorariosSemanaisFragment extends Fragment {
    public TabHorariosSemanaisFragment() {
        // Required empty public constructor
    }

    public static TabHorariosSemanaisFragment newInstance() {
        TabHorariosSemanaisFragment f = new TabHorariosSemanaisFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_horarios_semanais, container, false);
    }
}
