package ImageUtils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by dr on 2015/11/2.
 */
public class ImageDecoder {

    public static Bitmap sampleBitmapFromResource(Resources res, int resId, int viewWidth, int viewHeight){

        //Initialize a new decode option
        final BitmapFactory.Options options = new BitmapFactory.Options();

        //Only decode the size, don't allocate memory for decoding now
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = getSampledSize(options,viewWidth,viewHeight);

        //After setting the sample size, we allocate memory for the decoding process
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    public static int getSampledSize(BitmapFactory.Options options, int viewWidth, int viewHeight){

        //Raw width and height of the image
        final int rawW = options.outWidth;
        final int rawH = options.outHeight;
        int sampleSize = 1;

        if(rawH > viewHeight || rawW > viewWidth){

            final int halfHeight = rawH / 2;
            final int halfWidth = rawW / 2;

            //Calculate appropriate sample size
            while((halfHeight / sampleSize) > viewHeight && (halfWidth / sampleSize) > viewWidth){
                sampleSize *= 2;
            }
        }

        return sampleSize;
    }
}
