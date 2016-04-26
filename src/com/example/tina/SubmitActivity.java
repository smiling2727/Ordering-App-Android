package com.example.tina;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.model.submit;
import com.example.pay.DoPay;
import com.example.util.SubmitDBManager;
import com.example.util.TinaApplication;
import com.example.util.dingnumDBManager;
import com.zdp.aseo.content.AseoZdpAseo;

public class SubmitActivity extends Activity {
	// 配置初始化
	public TinaApplication TinaApplication1;
	public SubmitDBManager SubmitDBManager1;
	public dingnumDBManager dingnumDBManager1;
	public submit submit1;
	// 控件初始化
	public EditText EditText1;
	public EditText EditText2;
	public EditText EditText3;
	public Button Button1;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		// 标题栏添加按钮和背景
		getActionBar().setBackgroundDrawable(
				this.getResources().getDrawable(R.drawable.up));
		// 全局变量
		TinaApplication1 = (TinaApplication) getApplication();
		TinaApplication1.getInstance().addActivity(this);
		dingnumDBManager1 = new dingnumDBManager(this);
		SubmitDBManager1 = new SubmitDBManager(this);
		SubmitDBManager1.createding(TinaApplication1.get_dingdanstring()); // 创建订单
		EditText1 = (EditText) findViewById(R.id.activity_submit_editText1);// 总金额
		EditText2 = (EditText) findViewById(R.id.activity_submit_editText2);// 用餐人数
		EditText3 = (EditText) findViewById(R.id.activity_submit_editText3);// 备注
		Button1 = (Button) findViewById(R.id.activity_submit_button1);// 提交

		EditText1.setText(dingnumDBManager1.gettotalmoney("postdep", "5588"));
		AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE);;

		Button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(SubmitActivity.this)
						.setTitle("提示")
						.setMessage("确认提交吗?")
						.setPositiveButton("取消", null)
						.setNegativeButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										if ("".equals(TinaApplication1
												.getusername())) {
											Intent Intent1 = new Intent();
											Intent1.setClass(
													SubmitActivity.this,
													LoginActivity.class);
											startActivity(Intent1);
											Toast.makeText(SubmitActivity.this,
													"请先登录!", Toast.LENGTH_SHORT)
													.show();
										} else {
							
											// 保存本地数据库
											submit1 = new submit(
													SubmitActivity.this);
											submit1.set_submitnum(TinaApplication1
													.get_dingdanstring());
											submit1.set_username(TinaApplication1
													.getusername());
											submit1.set_totalmoney(Double
													.parseDouble(EditText1
															.getText()
															.toString()));
											if ("".equals(EditText2.getText()
													.toString())) {
												submit1.set_renshu(2);
											} else {
												submit1.set_renshu(Integer
														.parseInt(EditText2
																.getText()
																.toString()));
											}
											if ("".equals(EditText2.getText()
													.toString())) {
												submit1.set_contract("无附加条件");
											} else {
												submit1.set_contract(EditText3
														.getText().toString());
											}
											submit1.set_fukuan(false);
											submit1.set_queding(true);
											submit1.set_submitnum(TinaApplication1
													.get_dingdanstring());
											SubmitDBManager1
													.updateding(submit1);
											dingnumDBManager1.update(
													TinaApplication1
															.getusername(),
													TinaApplication1
															.get_dingdanstring());
											Toast.makeText(SubmitActivity.this,
													"下单成功!", Toast.LENGTH_SHORT)
													.show();
											TinaApplication1.set_dingdansring();
											setResult(0x719);
											finish();
											
											//在这个地方调用API接口
											SimpleDateFormat format=new SimpleDateFormat("yyyyMMddhhmmss");
											String nowTime=format.format(new Date());
											 //System.out.println("nowTime="+nowTime);
											Map map = new HashMap();											final String inputCharset = "01";//字符集
											map.put("bgUrl", "MainActivity");//接收返回值的地址
											map.put("signType", "01");//签名类型
											map.put("sign", "签名");//签名（这个很麻烦...晚一些时候再研究）
											// String sign = sign(orderInfo);
											map.put("orderId", TinaApplication1.get_dingdanstring());//商户订单号(就是订单号)
											map.put("orderAmount", Double.parseDouble(EditText1.getText().toString()));//订单金额,数组型
											map.put("orderCur","CNY");//币种
											map.put("orderTime",nowTime);//订单提交时间
											map.put("productName","商品名称");//商品名称 N/A
											map.put("productNum",new Integer(1));//商品数量 N/A
											map.put("productId","商品代码");//商品代码 N/A
											map.put("productPrice",new Integer(1));//单价 N/A
											map.put("productId","商品描述");//商品描述 N/A
											map.put("payTyple","02");//00 代表网银支付 01 代表快捷支付 02 代表余额支付
											map.put("payId",new Integer(123));//商户在支付平台的账户ID 说明文档里没有
											//final JSONObject orderInfo = JSONObject.fromObject(map);
											Runnable payRunnable = new Runnable() {
											    @Override
											    public void run() {
												//	DoPay dopay1 = new DoPay(SubmitActivity.this);
											    //    String result = dopay1.runPay();
											     //   Message msg = new Message();
											       // msg.what = SDK_PAY_FLAG;
											     //   msg.obj = result;
											       // mHandler.sendMessage(msg);
											    }
											};
											// 必须异步调用
											//Thread payThread = new Thread(payRunnable);
											//payThread.start();
											
											//HttpClient client = new DefaultHttpClient();
											//HttpUriRequest request = new HttpGet("https://ip:8443/uibepay/canclePay.action");
											//HttpResponse response;
										    //response = client.execute(request);
											//InputStream ins = response.getEntity().getContent();
										
										
										}//else结束
									}

								}).show();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& KeyEvent.KEYCODE_BACK == keyCode) {
			SubmitDBManager1.deleteding(TinaApplication1.get_dingdanstring());
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
}
