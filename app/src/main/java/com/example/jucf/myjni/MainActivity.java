package com.example.jucf.myjni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ImageView imageIV;

    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        imageIV = (ImageView) findViewById(R.id.imageIV);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.lena);
        imageIV.setImageBitmap(bmp);
    }

    public void gray(View view){
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        bmp.getPixels(pixels, 0, w, 0, 0, w, h);
        int[] resPixels = grayProc(pixels,w,h);
        Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(resPixels, 0, w, 0, 0, w, h);
        imageIV.setImageBitmap(resultImg);
    }

    /**
     * 图像灰度处理
     *
     * @param pixels 图像数据
     * @param w 图像宽度
     * @param h 图像高度
     * @return
     */
    public native int[] grayProc(int[] pixels,int w,int h);

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
