package com.example.myapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	private TextView tv_timer;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				tv_timer.setText(String.valueOf(msg.arg1));
				// System.out.println("case1----"+msg.arg1);
				System.out.println("case1----" + tv_timer.getText());
				break;
			case 2:
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
				finish();// 结束欢迎页面
				break;
			default:
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_timer = (TextView) findViewById(R.id.wel_tv_timer);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 开始定时
				for (int i = 5; i > 0; i--) {
					try {
						Thread.sleep(1000);
						// System.out.println("======"+i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message msg = new Message();
					msg.what = 1;
					msg.arg1 = i;
					handler.sendMessage(msg);
				}
				handler.sendEmptyMessage(2);

			}
		});
		t.start();
	}

}
