package ggbtech.muralonline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class TabAvisosFixosFragment extends Fragment {

    private FragmentTabHost tabHost;
    private ViewPager viewPager;

    public TabAvisosFixosFragment() {
        // Required empty public constructor
    }

    public static TabAvisosFixosFragment newInstance() {
        TabAvisosFixosFragment f = new TabAvisosFixosFragment();
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
               AvisosFixosAuxFragment.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt("Arg for Frag2", 2);
        tabHost.addTab(tabHost.newTabSpec("TabSeg").setIndicator("S"),
                AvisosFixosAuxFragment.class, arg2);

        Bundle arg3 = new Bundle();
        arg3.putInt("Arg for Frag3", 3);
        tabHost.addTab(tabHost.newTabSpec("TabTer").setIndicator("T"),
               AvisosFixosAuxFragment.class, arg3);

        Bundle arg4 = new Bundle();
        arg4.putInt("Arg for Frag4", 4);
        tabHost.addTab(tabHost.newTabSpec("TabQua").setIndicator("Q"),
               AvisosFixosAuxFragment.class, arg4);

        Bundle arg5 = new Bundle();
        arg5.putInt("Arg for Frag5", 5);
        tabHost.addTab(tabHost.newTabSpec("TabQui").setIndicator("Q"),
               AvisosFixosAuxFragment.class, arg5);

        Bundle arg6 = new Bundle();
        arg6.putInt("Arg for Frag6", 6);
        tabHost.addTab(tabHost.newTabSpec("TabSex").setIndicator("S"),
               AvisosFixosAuxFragment.class, arg6);

        Bundle arg7 = new Bundle();
        arg7.putInt("Arg for Frag7", 7);
        tabHost.addTab(tabHost.newTabSpec("TabSab").setIndicator("S"),
               AvisosFixosAuxFragment.class, arg7);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY: tabHost.setCurrentTabByTag("TabDom");break;
            case Calendar.MONDAY: tabHost.setCurrentTabByTag("TabSeg");break;
            case Calendar.TUESDAY:tabHost.setCurrentTabByTag("TabTer");break;
            case Calendar.WEDNESDAY: tabHost.setCurrentTabByTag("TabQua");break;
            case Calendar.THURSDAY: tabHost.setCurrentTabByTag("TabQui");break;
            case Calendar.FRIDAY: tabHost.setCurrentTabByTag("TabSex");break;
            case Calendar.SATURDAY: tabHost.setCurrentTabByTag("TabSab");break; }
        return tabHost;
    }
}
