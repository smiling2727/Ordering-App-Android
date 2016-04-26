package com.example.pay;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.util.TinaApplication;

import android.app.Activity;

public class DoPay {
public DoPay(Activity act){
	//final String bgUrl ="MainActivity";//接收返回值的地址
	//final String signType = "01";//签名类型
	//String sign = "签名";//签名（这个很麻烦...晚一些时候再研究）
	// String sign = sign(orderInfo);
	//final String orderId = TinaApplication1.get_dingdanstring();//商户订单号(就是订单号)
	//final Double orderAmount = Double.parseDouble(EditText1.getText().toString());//订单金额,数组型
	//final String orderCur = "CNY";//币种
	//final String orderTime = "nowTime";//订单提交时间
	//final String productName = "";//商品名称 N/A
	//final long productNum = 0;//商品数量 N/A
	//final String productId = "";//商品代码 N/A
	//final long productPrice = 0;//单价 N/A
	//final String productDesc = "";//商品描述 N/A
	//final String payType ="";//00 代表网银支付 01 代表快捷支付 02 代表余额支付
	//final long payId =123;//商户在支付平台的账户ID 说明文档里没有
	
	  try {
		   URL url = new URL("https://ip:8443/uibepay/canclePay.action");
		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		   conn.setConnectTimeout(5000);
		   conn.setRequestMethod("GET");
		   conn.setDoInput(true);
		   int code = conn.getResponseCode();
		   ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   byte[] buffer = new byte[1024];
		   int len = 0;
		   System.out.println("返回码kaishi  code = "+code);
		   if(code == 200){
		    System.out.println("返回码code = "+code);
		    InputStream is = conn.getInputStream();
		    while ((len = is.read(buffer)) != -1) {
		     baos.write(buffer, 0, len);
		    }
		    String str = new String(baos.toByteArray());
		    System.out.println("打印user对象**********  "+str);
		   }
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
};

public String runPay(){
	
	
	return "输出结果串";
	
}
}
