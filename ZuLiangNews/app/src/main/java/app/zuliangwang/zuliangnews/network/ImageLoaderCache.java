package app.zuliangwang.zuliangnews.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zuliangwang on 15/7/24.
 */
public class ImageLoaderCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> mCache;

    @Override
    public void putBitmap(String s, final Bitmap bitmap) {
        mCache.put(s,bitmap);
    }

    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    public ImageLoaderCache(){
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }
}
