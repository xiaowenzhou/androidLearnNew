package cn.ktc.learnandroid.pictureloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * @author zhouxw
 */
public class PictureResizer {
    private static final String TAG = "PictureResizer";


    /**
     * 压缩图片resize the picture from resource;
     *
     * @param res
     * @param viewId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap resizePictureFromResource(Resources res, int viewId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, viewId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, viewId, options);

    }


    /**
     *resize the picture from file descriptor;
     * @param fd
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public Bitmap resizePictureFromFileDescriptor(FileDescriptor fd,int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }

    /**
     *
     *  the algorithm for calculate the inSampleSize;
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqHeight == 0 || reqWidth == 0) {
            return 1;
        }
        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.e(TAG, "calculateInSampleSize:  origin,width==" + width + ",height==" + height);
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while ((halfHeight / inSampleSize >= reqHeight) && (halfWidth / inSampleSize >= reqWidth)) {
                inSampleSize *= 2;
            }

        }
        Log.e(TAG, "calculateInSampleSize: inSampleSize==" + inSampleSize);

        return inSampleSize;
    }


}
