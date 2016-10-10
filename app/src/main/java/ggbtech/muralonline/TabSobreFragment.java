package ggbtech.muralonline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



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
        return inflater.inflate(R.layout.fragment_tab_sobre, container, false);
    }
}
