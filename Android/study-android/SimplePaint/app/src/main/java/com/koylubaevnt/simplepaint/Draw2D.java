package com.koylubaevnt.simplepaint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by koylu on 10.07.2017.
 */

public class Draw2D extends View {

    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();
    private Bitmap mBitmap;

    public Draw2D(Context context) {
        super(context);

        Resources res = context.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.cat_bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int heigh = canvas.getHeight();


        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        //Yellow circle
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(width - 30, 30, 25, mPaint);

        //Green rectangle
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, heigh - 30, width, heigh, mPaint);

        //Text
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(32);
        canvas.drawText("Лужайка только для котов", 30, heigh - 32, mPaint);

        canvas.save();
        //Text under angle
        int x = width - 170;
        int y = 190;

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(27);;
        String str2rotate = "Лучик солнца!";

        canvas.rotate(-45, x + mRect.exactCenterX(), y + mRect.exactCenterY());
        canvas.drawText(str2rotate, x, y, mPaint);
        canvas.restore();

        canvas.drawBitmap(mBitmap, width - mBitmap.getWidth(), heigh - mBitmap.getHeight() - 10, mPaint);

    }
}
