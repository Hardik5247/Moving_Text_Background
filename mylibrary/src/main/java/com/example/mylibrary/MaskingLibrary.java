package com.example.mylibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class MaskingLibrary {

    public Bitmap MaskingProcess(Bitmap textAsBitmap, Bitmap original) {
        Bitmap Results = null, MaskBitmap;
        try{
            if (original != null){

                int iv_width = original.getWidth();
                int iv_height = original.getHeight();

                Results = Bitmap.createBitmap(iv_width,iv_height,Bitmap.Config.ARGB_8888);
                MaskBitmap = Bitmap.createScaledBitmap(textAsBitmap,iv_width,iv_height,true);

                Canvas canvas = new Canvas(Results);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

                canvas.drawBitmap(original, 0,0,null);
                canvas.drawBitmap(MaskBitmap,0,0,paint);

                paint.setXfermode(null);
                paint.setStyle(Paint.Style.STROKE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return Results;
    }

    public Bitmap textAsBitmap(String text,float textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) +0.5f); // round
        int height = (int) (baseline + paint.descent());
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

}
