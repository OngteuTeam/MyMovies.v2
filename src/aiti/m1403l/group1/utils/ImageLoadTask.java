package aiti.m1403l.group1.utils;
import aiti.m1403l.group1.data.model.*;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class ImageLoadTask extends AsyncTask<String, String, Bitmap> {
	 @Override
     protected void onPreExecute() {
         Log.i("ImageLoadTask", "Loading image...");
     }

     // PARAM[0] IS IMG URL
     protected Bitmap doInBackground(String... param) {
         Log.i("ImageLoadTask", "Attempting to load image URL: " + param[0]);
         try {
             Bitmap b = ImageService.getBitmapFromURL(param[0]);
             return b;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }

     protected void onProgressUpdate(String... progress) {
         // NO OP
     }

     protected void onPostExecute(Bitmap ret) {
    	 Category cates;
         if (ret != null) {
        	 
             Log.i("ImageLoadTask", "Successfully loaded " + cates.getName() + " image");
             
             cates.setImgBM(ret);	
             if (cates.getAdapter() != null) {
                 // WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
                 cates.getAdapter().notifyDataSetChanged();
             }
         } else {
             Log.e("ImageLoadTask", "Failed to load " + cates.getName() + " image");
         }
     }
    }
