package com.example.tina;
import com.example.model.goods;
import com.example.util.TinaApplication;
import com.example.util.dingDBManager;
import com.example.util.dingnumDBManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {
	// 页面配置
		final int code = 0x719;
		private ProgressDialog ProgressDialog1; // 加载对话框
		private Thread Thread1;
		private TinaApplication TinaApplication1; // 全局应用层
		

		// 控件
		private EditText EditText1;
		private EditText EditText2;
		private EditText EditText3;
		private Button Button1;
		
		//添加的信息
		private String itemname;
		private String description;
		private String price;
		private goods addgoods;
		private dingDBManager addDBManager;

		@SuppressLint("NewApi")
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_additem);//加载addItem这个xml样式
			TinaApplication1 = (TinaApplication) getApplication();
			TinaApplication1.getInstance().addActivity(this);// 这个是为了退出时清除所有打开的activity
			// 标题栏运行出现按钮
			getActionBar().setDisplayHomeAsUpEnabled(true);
			// 标题栏添加按钮和背景
			//AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE); //这句是启动广告的
			getActionBar().setBackgroundDrawable(
					this.getResources().getDrawable(R.drawable.up));
			//threadstart();
			
			
			EditText1 = (EditText) findViewById(R.id.activity_additem_editText1);//商品名称
			EditText2 = (EditText) findViewById(R.id.activity_additem_editText2);//商品描述
			EditText3 = (EditText) findViewById(R.id.activity_additem_editText4);//价格
			Button1 = (Button) findViewById(R.id.activity_menu_items_button3);//确认添加
			// 搜索操作
			Button1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//strwhere = " _title like '%" + EditText1.getText().toString()+ "%'";
					itemname = EditText1.getText().toString().trim();
					description = EditText2.getText().toString().trim();
					price =EditText3.getText().toString().trim();
					addData();
					Intent Intent2 = new Intent();
					Intent2.setClass(AddItemActivity.this,MainActivity.class);
					startActivity(Intent2);
					
				}});
		}
		
		public void addData(){
			addDBManager = new dingDBManager(this);
			addgoods = new goods();
			addgoods.set_category_id(0);
			addgoods.set_title(itemname);
			addgoods.set_sell_price(price);
			addgoods.set_img_url(String.valueOf("1"));
			addgoods.set_content(description);
			addgoods.set_buynumber(0);
			addgoods.set_user("postdep");
			addDBManager.add(addgoods);
		}
		
		
}