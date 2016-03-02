package com.example.myapp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author James
 * @2016年2月27日下午5:40:26
 */

public class MainActivity extends Activity {
	/**
	 * 登陆的主界面
	 */
	// 声明控件
	public static Map<String, String> userinfor = new HashMap();// 存放用户的信息
	public final static String regularName = "[a-z_A-Z]{1}[a-z_A-Z0-9]{0,6}";
	public final static String regularPwd = "[a-z_A-Z0-9]{1,7}";
	public static boolean flagName = false;
	public static boolean flagPwd = false;
	private final static int regcode = 100;
	private final static int logincode = 300;
	private TextView tv_newuser;
	private EditText edt_name;
	private EditText edt_pwd;
	private Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_newuser = (TextView) findViewById(R.id.main_edt_newuser);
		userinfor.put("zhangsan", "123");
		edt_name = (EditText) findViewById(R.id.main_edt_name);
		edt_pwd = (EditText) findViewById(R.id.main_edt_pwd);
		btn_login = (Button) findViewById(R.id.main_btn_login);
	}

	/**
	 * TODO从其他活动重新回到本活动后进行数据处理
	 * <p>
	 * </p>
	 * <p>
	 * </br> </br> </br>
	 * </p>
	 * 
	 * @param view
	 *            <p>
	 *            </br> </br> </br>
	 *            </p>
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 200) {
			switch (requestCode) {// 根据返回码判断是从哪个活动传回来数据
			case regcode:// 从注册窗体传来信息
				this.edt_name.setText(data.getStringExtra("regname"));
				this.edt_pwd.setText(data.getStringExtra("regpassword"));
				break;
			}
		}
	}

	/**
	 * TODO
	 * <p>
	 * 登陆按钮点击事件
	 * </p>
	 */

	public void doLogin(View view) {
		String username = edt_name.getText().toString();
		String password = edt_pwd.getText().toString();
		// System.out.println("====dologin"+username);
		if ("".equals(username) || "".equals(password)) {
			Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
		} else {
			if (!userinfor.containsKey(username)) {
				Toast.makeText(this, "该用户尚未注册，请先注册！", Toast.LENGTH_LONG).show();
				// System.out.println("===未注册");
			} else {
				// 登陆到主界面，显示用户的信息
				System.out.println("====" + userinfor.get(username));
				if (password.equals(userinfor.get(username))) {
					Intent intent = new Intent();
					intent.setClass(this, UserInforActivity.class);
					intent.putExtra("username", username);
					intent.putExtra("password", password);
					startActivityForResult(intent, logincode);
				} else {
					Toast.makeText(this, "密码错误，请重新输入！", Toast.LENGTH_LONG)
							.show();
				}
			}
		}
	}

	/**
	 * TODO
	 * <p>
	 * 新用户注册的label
	 * </p>
	 * <p>
	 * 将用户填好的用户名和密码传递到注册页面
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @param view
	 */
	public void goReg(View view) {
		String username = edt_name.getText().toString();
		String password = edt_pwd.getText().toString();
		Intent intent = new Intent();
		if (!userinfor.containsKey(username)) {
			intent.putExtra("username", username);
			intent.putExtra("password", edt_pwd.getText());
			intent.setClass(this, RegisterActivity.class);
			startActivityForResult(intent, regcode);
		}
	}

}
