package aiti.m1403l.group1.movies;

import aiti.m1403l.group1.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFilmsFragment extends Fragment {
	
	public MyFilmsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_myfilms, container, false);
         
        return rootView;
    }
}
