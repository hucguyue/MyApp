package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserInforActivity extends Activity {
	private TextView tv_userInfor;
	private TextView tv_uititle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_infor);
		Intent intent = getIntent();
		tv_userInfor = (TextView) findViewById(R.id.uinfor_tv_data);
		tv_uititle = (TextView) findViewById(R.id.uinfor_tv_title);
		tv_userInfor.setText("用户名："+intent.getStringExtra("username")+"\n密码："
				+ intent.getStringExtra("password"));
	}

}
