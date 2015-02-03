package aiti.m1403l.group1.movies;

import aiti.m1403l.group1.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class CategoriesFragment1 extends Fragment {
	
	
	public CategoriesFragment1(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_cate_layout, container, false);
//        cate1 = (Button)rootView.findViewById(R.id.btn_action); 
//        cate1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),CategoryDetailsActivity.class);
//				intent.putExtra("cateId",R.id. );
//				startActivity(intent);
//			}
//		});
        return rootView;
    }
}
