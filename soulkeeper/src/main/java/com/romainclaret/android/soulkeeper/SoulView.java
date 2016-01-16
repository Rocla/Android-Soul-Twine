package com.romainclaret.android.soulkeeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;

import com.badpx.particleandroid.widget.ParticleSystemView;


public final class SoulView extends ParticleSystemView {

    public boolean displayFPS = false;
    private Paint paint = new Paint();
    private int posX;
    private int posY;
    private long timer;
    private int frameCount;
    private int fps;

    public SoulView(Context context) {
        super(context);
    }

    public SoulView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showFPS() {
        displayFPS = true;
    }

    public void hideFPS() {
        displayFPS = false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (displayFPS) {
            final int viewMaxX = getResources().getDisplayMetrics().widthPixels;
            final int viewMaxY = getResources().getDisplayMetrics().heightPixels;
            long time = SystemClock.uptimeMillis();

            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            posX = viewMaxX - 280;
            posY = viewMaxY - 600;

            if (time - timer >= 1000) {
                timer = time;
                fps = frameCount;
                frameCount = 0;
            }
            ++frameCount;
            canvas.drawText(String.format("FPS: %d", fps), posX, posY, paint);
        }
    }
}
