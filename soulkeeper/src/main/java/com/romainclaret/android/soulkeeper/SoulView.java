package com.romainclaret.android.soulkeeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import com.badpx.particleandroid.ParticleSystem;
import com.badpx.particleandroid.widget.ParticleSystemView;


public final class SoulView extends ParticleSystemView {
    private Paint mPaint = new Paint();
    private long mTimer;
    private int mFrameCount;
    private int mFps;
    private int mPosX, mPoxY;

    public SoulView(Context context) {
        super(context);
    }

    public SoulView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(32);
        mPosX = 50;
        mPoxY = 50;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        long time = SystemClock.uptimeMillis();
        if (time - mTimer >= 1000) {
            mTimer = time;
            mFps = mFrameCount;
            mFrameCount = 0;
        }
        ++mFrameCount;
        canvas.drawText(String.format("FPS: %d", mFps), mPosX, mPoxY, mPaint);

        int particleTotal = 0;
        for (ParticleSystem particleSystem : mParticleSystems) {
            particleTotal += particleSystem.getParticleCount();
        }
        canvas.drawText(String.format("Particle Count: %d", particleTotal), mPosX, mPoxY + 50, mPaint);
    }
}
