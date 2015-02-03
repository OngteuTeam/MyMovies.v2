package aiti.m1403l.group1.movies.adapter;

import java.util.ArrayList;
import java.util.List;

import aiti.m1403l.group1.R;
import aiti.m1403l.group1.data.model.Category;
import aiti.m1403l.group1.data.orm.CategoryORM;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
   private Context mContext;
   ArrayList<Category> cateList;
   private LayoutInflater mInflater;
   
   // Constructor
   public CategoryAdapter(Context context) {
	   this.mInflater = LayoutInflater.from(context);
       this.cateList = CategoryORM.getListCategory(context);
   }

   public int getCount() {
      return cateList.size();
   }

   public Object getItem(int position) {
      return null;
   }

   public long getItemId(int position) {
      return 0;
   }

   // create a new ImageView for each item referenced by the Adapter
   public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView;
      TextView textView;
      ViewHolder holder;
      Category cate = cateList.get(position);
      if (convertView == null) {
    	  convertView = mInflater.inflate(R.layout.grid_cates_item, null);
      imageView = new ImageView(mContext);
      holder = new ViewHolder();
      holder.name = (TextView)convertView.findViewById(R.id.tvCateName);
      holder.image = (ImageView)convertView.findViewById(R.id.IvCates);
      convertView.setTag(holder);
      
      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
      textView = new TextView(mContext);
      textView.setText(cate.getName());
      
      } else {
    	  holder = (ViewHolder) convertView.getTag();
      }
      holder.name.setText(cate.getName());
      if (cate.getImage() != null) {
          holder.image.setImageBitmap(cate.getImgBM());
      } else {
              // MY DEFAULT IMAGE
          holder.image.setImageResource(R.drawable.ic_unavai);
      }
      
      return convertView;
   }

   static class ViewHolder {
       TextView name;
       ImageView image;
   }
  
}