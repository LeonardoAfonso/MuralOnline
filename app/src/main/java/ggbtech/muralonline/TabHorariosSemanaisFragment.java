package ggbtech.muralonline;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabHorariosSemanaisFragment extends Fragment {

    private FragmentTabHost tabHost;
    private ViewPager viewPager;

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
        //return inflater.inflate(R.layout.fragment_tab_horarios_semanais, container, false);


        tabHost = new FragmentTabHost(getActivity());
        viewPager = new ViewPager(getContext());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_tab_horarios_semanais);

        Bundle arg1 = new Bundle();
        arg1.putInt("Arg for Frag1", 1);
        tabHost.addTab(tabHost.newTabSpec("TabDom").setIndicator("D"),
               AvisosFixosFragment.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt("Arg for Frag2", 2);
        tabHost.addTab(tabHost.newTabSpec("TabSeg").setIndicator("S"),
                AvisosFixosFragment.class, arg2);

        Bundle arg3 = new Bundle();
        arg3.putInt("Arg for Frag3", 3);
        tabHost.addTab(tabHost.newTabSpec("TabTer").setIndicator("T"),
               AvisosFixosFragment.class, arg3);

        Bundle arg4 = new Bundle();
        arg2.putInt("Arg for Frag4", 4);
        tabHost.addTab(tabHost.newTabSpec("TabQua").setIndicator("Q"),
               AvisosFixosFragment.class, arg4);

        Bundle arg5 = new Bundle();
        arg1.putInt("Arg for Frag5", 5);
        tabHost.addTab(tabHost.newTabSpec("TabQui").setIndicator("Q"),
               AvisosFixosFragment.class, arg5);

        Bundle arg6 = new Bundle();
        arg2.putInt("Arg for Frag6", 6);
        tabHost.addTab(tabHost.newTabSpec("TabSex").setIndicator("S"),
               AvisosFixosFragment.class, arg6);

        Bundle arg7 = new Bundle();
        arg1.putInt("Arg for Frag7", 7);
        tabHost.addTab(tabHost.newTabSpec("TabSab").setIndicator("S"),
               AvisosFixosFragment.class, arg7);

        return tabHost;
    }

}
