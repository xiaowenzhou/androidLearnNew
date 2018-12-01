package cn.ktc.learnandroid.pictureloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.ktc.learnandroid.R;

/**
 * @author zhouxw
 */
public class PictureLoader {
    private static final String TAG = "PictureLoader";
    //the message is to notify the mainThread to update UI;
    private static final int MESSAGE_POST_RESULT =0x0012;
    //get system's CPU count;
    private static final int CPU_COUNT =Runtime.getRuntime().availableProcessors();
    //set ThreadPool;s core thread count;
    private static final  int CORE_POOL_SIZE =CPU_COUNT + 1;
    //set ThreadPool's max thread count;
    private static  final int MAX_POOL_SIZE= CORE_POOL_SIZE *2 +1;
    //set every thread's alive time;
    private static final long KEEP_ALIVE = 10L;

    //get KEY_URI from imageView;
    private static final int TAG_KEY_URI=R.id.pictureload_uri;
    private static final long DISK_CACHE_SIZE =1024*1024*50;
    private static final long IO_BUFFER_SIZE =1024*8;
    private static final long DISK_CACHE_INDEX =0;
    private boolean isDiskLruCacheCreated = false;

     private  class LoaderResult{
         String    uri;
         ImageView imageView;
         Bitmap bitmap;

         LoaderResult(String uri,ImageView imageView,Bitmap bitmap){
             this.uri = uri;
             this.imageView = imageView;
             this.bitmap = bitmap;
         }


     }

     //create a ThreadFactory to ThreadPool;
    private static final ThreadFactory pThreadFactory = new ThreadFactory() {
         private final AtomicInteger tIndex= new AtomicInteger(0);
         @Override
         public Thread newThread(@NonNull Runnable r) {
             return new Thread(r,"PictureLoader#"+tIndex.getAndIncrement());
         }
     };


    /**
     * create the ThreadPool;
     */
    private static final Executor loaderThreadPoolExecutor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,MAX_POOL_SIZE,KEEP_ALIVE,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(),
            pThreadFactory);

    private Handler mainHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result =(LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)){
                imageView.setImageBitmap(result.bitmap);
            }else {
                Log.e(TAG, "handleMessage: image url has changed, so ignored" );
            }
        }
    };

    private Context mContext;
    private PictureResizer mPictureResizer =new PictureResizer();
    private LruCache<String,Bitmap> memoryCache;
    private DiskLruCache mDiskLruCache;



}
