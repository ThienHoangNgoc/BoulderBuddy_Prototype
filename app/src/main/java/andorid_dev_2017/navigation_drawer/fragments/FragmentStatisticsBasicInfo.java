package andorid_dev_2017.navigation_drawer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andorid_dev_2017.navigation_drawer.R;

public class FragmentStatisticsBasicInfo extends Fragment {

    View view;

    public FragmentStatisticsBasicInfo(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistics_basic_info_fragment,container,false);
        return view;
    }
}