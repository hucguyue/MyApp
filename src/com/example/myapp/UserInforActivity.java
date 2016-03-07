package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author James
 * @2016年3月3日上午10:49:28
 */
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
		initClickEvent();
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
		adapter.notifyDataSetChanged();// 唤醒适配器的数据交换
	}

	private void initClickEvent() {
		// TODO Auto-generated method stub
		this.lv_data
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					/**
					 * (non-Javadoc) id: 被单击的项的行标识
					 * 
					 * @TODO点击每一项时出现对话框显示详细的信息。 </br>
					 *                          <p>
					 *                          1.自定义对话框</br>
					 *                          >>创建布局解析器inflater</br>
					 *                          >>解析某个布局文件
					 *                          ，形成视图。inflate()的第二个参数表示生成视图的父视图
					 *                          。</br> 2.往对话框中传递数据</br>
					 * 
					 *                          </p>
					 * 
					 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
					 *      android.view.View, int, long)
					 */
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						LayoutInflater inflater = LayoutInflater
								.from(UserInforActivity.this);// 从当前的活动中创建一个布局解析器
						View v = inflater.inflate(R.layout.alert_userinfor_all,
								null);//

						TextView tv_name = (TextView) findViewById(R.id.dialoguserinfor_tv_name);// 对item中的各个控件进行绑定
						TextView tv_pwd = (TextView) findViewById(R.id.dialoguserinfor_tv_password);
						TextView tv_pwdagain = (TextView) findViewById(R.id.dialoguserinfor_tv_pwdagain);
						AlertDialog.Builder alert = new Builder(
								UserInforActivity.this);// 创建一个对话框的对象
						alert.setView(v);// 绑定该对话框的视图
						alert.setPositiveButton("确定", null);
						alert.setTitle("详细信息");
						alert.setIcon(android.R.drawable.ic_menu_info_details);
					/*	System.out.println("------->"
								+ intent.getStringExtra("password"));
						tv_name.setText(intent.getStringExtra("username"));
						tv_pwd.setText(intent.getStringExtra("password"));
						tv_pwdagain.setText(intent.getStringExtra("password"));*/
						alert.show();
					}
				});
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
	 * public SimpleAdapter(Context context, List<? extends Map<String, ?>>
	 * data, int resource, String[] from, int[] to)
	 * 
	 * @param context
	 *            :Context类型，当前活动的上下文</br> data:List<? extends
	 *            Map<String,?>>类型，一个内容为任意继承Map类类型的List</br>
	 *            resource：int类型，数据源，或者说存放数据的方式或容器</br>
	 *            from:String[]类型，从哪里映射数据。</br> to:int[]类型，将数据映射到哪里。</br>
	 *            </p>
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
