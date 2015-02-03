package aiti.m1403l.group1.movies;

import aiti.m1403l.group1.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CategoryFragment extends Fragment {
	
public CategoryFragment(){}
	
	Button cate1;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_cate_layout, container, false);
       
        return rootView;
    }
}
