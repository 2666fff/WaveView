package com.artw.waveview;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.artw.wavetest.R;

public class ImageWaveView extends View {
	private static final float SPEED = 2.0F;
	private Bitmap bitmap;
	private float currentPosX;
	private Rect headRect;
	private MyTimerTask mTask;
	private Paint plainPaint;
	private int srcHeight;
	private Rect srcRect;
	private int srcWidth;
	private Rect tailRect;
	private Timer timer;

	private Handler updateHandler = new Handler() {
		public void handleMessage(Message paramMessage) {
			
			//前面移动的一块
			headRect.set((int) currentPosX, 50, (int) currentPosX + srcWidth,
					50 + srcHeight);
			//后面移动的一块
			tailRect.set((int) (-srcWidth + currentPosX), 50,
					(int) currentPosX, 50 + srcHeight);
			
			//移动的位置
			if (currentPosX <= srcWidth) {
				currentPosX += SPEED;
			} else {
				currentPosX = 0;
			}
			invalidate();
		}
	};

	public ImageWaveView(Context paramContext) {
		super(paramContext);
		init();
	}

	public ImageWaveView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	public ImageWaveView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		init();
	}

	private void init() {
		timer = new Timer();
		plainPaint = new Paint();
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.fb_wave);
		srcHeight = bitmap.getHeight();
		srcWidth = bitmap.getWidth();
		srcRect = new Rect(0, 0, srcWidth, srcHeight);
		headRect = new Rect(0, 0, srcWidth, srcHeight);
		tailRect = new Rect(-srcWidth, 0, 0, srcHeight);
	}

	private void start() {
		if (mTask != null) {
			mTask.cancel();
			mTask = null;
		}
		mTask = new MyTimerTask(updateHandler);
		timer.schedule(mTask, 0, 10);
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		paramCanvas.drawBitmap(bitmap, srcRect, headRect, plainPaint);
		paramCanvas.drawBitmap(bitmap, srcRect, tailRect, plainPaint);
	}

	public void onWindowFocusChanged(boolean paramBoolean) {
		super.onWindowFocusChanged(paramBoolean);
		if (paramBoolean)
			start();
	}

	class MyTimerTask extends TimerTask {
		Handler handler;

		public MyTimerTask(Handler handler) {
			this.handler = handler;
		}

		@Override
		public void run() {
			handler.sendMessage(handler.obtainMessage());
		}

	}
}