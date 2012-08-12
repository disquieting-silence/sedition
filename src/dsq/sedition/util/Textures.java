package dsq.sedition.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import javax.microedition.khronos.opengles.GL10;

public class Textures {

    private Textures() {}

    public static void load(GL10 g, Context context, int id, int drawable) {
        final Bitmap bitmap = loadBitmap(context, drawable);

        g.glBindTexture(GL10.GL_TEXTURE_2D, id);
        g.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        g.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
    }

    private static Bitmap loadBitmap(final Context context, final int drawable) {
        final Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, drawable);
    }
}
