package com.example.myapp;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText edt_username;
	private EditText edt_password;
	private EditText edt_pwdagain;
	Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		intent = getIntent();
		edt_username = (EditText) findViewById(R.id.reg_edt_username);
		edt_password = (EditText) findViewById(R.id.reg_edt_password);
		edt_pwdagain = (EditText) findViewById(R.id.reg_edt_pwdagain);
		edt_username.setText(intent.getStringExtra("username").toString());
		// edt_password.setText(intent.getStringExtra("password").toString());
		// edt_pwdagain.setText(intent.getStringExtra("password").toString());
	}

	/**
	 * TODO重置文本框中内容为空串
	 * <p>
	 * “重置”按钮的onClick属性
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
	public void doReset(View view) {
		this.edt_password.setText("");
		this.edt_pwdagain.setText("");
		this.edt_username.setText("");
	}

	/**
	 * TODO进行注册，注册按钮的onClick属性
	 * <p>
	 * 从MainActivity中获取未注册的用户信息
	 * </p>
	 * <p>
	 * 1.验证填写的注册信息</br> 2.确认无误后保存信息</br> 3.利用intent将数据和返回码回传</br>
	 * </p>
	 * 
	 * @param view
	 *            <p>
	 *            </br> </br> </br>
	 *            </p>
	 */
	public void doReg(View view) {
		// 获取控件中的文本数据
		String username = edt_username.getText().toString();
		String pwd = edt_password.getText().toString();
		String pwdagain = edt_pwdagain.getText().toString();
		MainActivity.flagName = Pattern.matches(MainActivity.regularName,
				username);
		MainActivity.flagPwd = Pattern.matches(MainActivity.regularPwd, pwd);
		if (!MainActivity.flagName) {// 用户名密码等信息的验证
			// Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
			edt_username.setError("用户名格式不正确");
			edt_username.selectAll();
		} else if (!MainActivity.flagPwd) {
			edt_password.setError("密码格式不正确");
			edt_password.selectAll();
			// Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
		} else if (!pwd.equals(pwdagain)) {
			Toast.makeText(this, "两次密码不一致", Toast.LENGTH_LONG).show();
		} else {
			// 存放数据
			MainActivity.userinfor.put(username, pwd);
			Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
			intent.putExtra("regname", username);
			intent.putExtra("regpassword", pwd);
			setResult(200, intent);// 返回200的请求码和相应的数据给MainActivity
			this.finish();
		}
	}
}
