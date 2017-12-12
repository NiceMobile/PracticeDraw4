package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float center1X = bitmap.getWidth() / 2 + point1.x;
        float center1Y = bitmap.getHeight() / 2 + point1.y;
        float center2X = bitmap.getWidth() / 2 + point2.x;
        float center2Y = bitmap.getHeight() / 2 + point2.y;

        canvas.save();
        camera.save();
        camera.rotateX(30);
        //这里需要特别注意:canvas的几何变换shunx是反的，所以要先把移到中心的代码写在下面，把从中心移动回来的代码写在上面
        canvas.translate(center1X, center1Y);//将画布还原到原来位置
        camera.applyToCanvas(canvas);//把旋转投影到canvas上面
        canvas.translate(-center1X,-center1Y);//将画布拖动到轴心
        camera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        canvas.save();
        camera.save();
        camera.rotateY(30);
        canvas.translate(center2X, center2Y);
        camera.applyToCanvas(canvas);
        canvas.translate(-center2X,-center2Y);
        camera.restore();
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
