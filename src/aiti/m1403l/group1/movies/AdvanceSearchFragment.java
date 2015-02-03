package aiti.m1403l.group1.movies;

import aiti.m1403l.group1.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdvanceSearchFragment extends Fragment {
	
	public AdvanceSearchFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
         
        return rootView;
    }
}
