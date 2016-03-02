package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	private TextView tv_timer;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				tv_timer.setText(msg.obj.toString());
				break;
			case 2:
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("====concreate start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		System.out.println("====concreate end");
	}
	@Override
	protected void onStart() {
		System.out.println("====constart start");
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("====start e");
	}
	private void initView() {
		// TODO Auto-generated method stub
		tv_timer=(TextView) findViewById(R.id.wel_tv_timer);
	}

	@Override
	protected void onResume() {
		System.out.println("====resume start");
		// TODO Auto-generated method stub
		super.onResume();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 开始定时
				for (int i = 4; i > 0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message msg = new Message();
					msg.what = 1;
					msg.obj = i;
					handler.sendMessage(msg);
				}
				handler.sendEmptyMessage(2);

			}
		});
		t.start();
	}

}
