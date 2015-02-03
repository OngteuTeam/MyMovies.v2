package aiti.m1403l.group1.data.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import aiti.m1403l.group1.R;
import aiti.m1403l.group1.movies.adapter.CategoryAdapter;
public class Category {
	
	
	private int id;
	private String name;
	private String image;
	private Bitmap imgBM;
	private CategoryAdapter catesadapter;
	
	Bitmap defImg = BitmapFactory.decodeFile("/drawable/ic_unavai.png");
	
	
	public Category() {
	}

	
	public Category(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;		
		this.imgBM = defImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	public Bitmap getImgBM() {
		return imgBM;
	}


	public void setImgBM(Bitmap imgBM) {
		this.imgBM = imgBM;
	}


	@Override
	public String toString() {
		return this.getName();
	}
	
	public CategoryAdapter getAdapter() {
        return catesadapter;
    }
 
    public void setAdapter(CategoryAdapter sta) {
        this.catesadapter = sta;
    }
	
}
