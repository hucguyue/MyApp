package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class UserInforActivity extends Activity {
	private TextView tv_userInfor;
	private TextView tv_uititle;
	private ListView lv_data;
	private List<HashMap<String, String>> userinfor;// 存放用于和lv_data绑定的数据
	private SimpleAdapter adapter;// 由于数据格式复杂，所以用SimpleAdapter。
	private Intent intent;
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_infor);
		initView();
		intent = getIntent();
		username = intent.getStringExtra("username");
		password = intent.getStringExtra("password");
		tv_userInfor.setText("用户名：" + username);
		List<HashMap<String, String>> d = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> item = new HashMap<String, String>();
		item.put("image", R.drawable.gittx + "");
		item.put("username", username);
		item.put("password", password);
		for (int i = 0; i < 5; i++) {
			d.add(item);
		}
		userinfor.clear();
		userinfor.addAll(d);
		adapter.notifyDataSetChanged();
	}

	/**
	 * TODO
	 * <p>
	 * 初始化各个控件
	 * </p>
	 * <p>
	 * tv_userInfor:显示用户信息的文本框</br> tv_uititle:用户信息标题</br>
	 * lv_data:显示用户信息的列表控件</br>
	 * </p>
	 * <p>
	 * </br> </br> </br>
	 * </p>
	 */
	private void initView() {
		// TODO Auto-generated method stub

		tv_userInfor = (TextView) findViewById(R.id.uinfor_tv_data);
		tv_uititle = (TextView) findViewById(R.id.uinfor_tv_title);
		lv_data = (ListView) findViewById(R.id.uinfor_lsv_data);
		userinfor = new ArrayList<HashMap<String, String>>();
		this.adapter = new SimpleAdapter(this, userinfor,
				R.layout.list_item_userinfor, new String[] { "image",
						"username", "password" }, new int[] {
						R.id.userinfor_tx, R.id.userinfor_tv_name,
						R.id.userinfor_tv_password });
		this.lv_data.setAdapter(adapter);
	}
}
