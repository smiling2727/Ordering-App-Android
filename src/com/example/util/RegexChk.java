package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChk {
	private boolean startCheck(String reg, String string) {
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		return tem;
	}

	/**
	 * ��������,����������������������0��������������-0��ͷ, ������������0��ͷ
	 * 
	 * */
	public boolean checkNr(String nr) {
		String reg = "^(-?)[1-9]+\\d*|0";
		return startCheck(reg, nr);
	}

	/**
	 * �ֻ�������֤,11λ����֪����ϸ���ֻ�����Σ�ֻ����֤��ͷ������1��λ��
	 * */
	public boolean checkCellPhone(String cellPhoneNr) {
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	/**
	 * �ж��ֻ����Ƿ���ȷ
	 * 
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone) {
		Pattern pattern1 = Pattern.compile("^13\\d{9}||15[8,9]\\d{8}$");
		Pattern pattern2 = Pattern.compile("^15\\d{9}||15[8,9]\\d{8}$");
		Pattern pattern3 = Pattern.compile("^18\\d{9}||15[8,9]\\d{8}$");
		Matcher matcher1 = pattern1.matcher(phone);
		Matcher matcher2 = pattern2.matcher(phone);
		Matcher matcher3 = pattern3.matcher(phone);

		if (matcher1.matches() || matcher2.matches() || matcher3.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * ����հ׷�
	 * */
	public boolean checkWhiteLine(String line) {
		String regex = "(\\s|\\t|\\r)+";

		return startCheck(regex, line);
	}

	/**
	 * ���EMAIL��ַ �û�������վ���Ʊ���>=1λ�ַ�
	 * ��ַ��β��������com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn��β
	 * */
	public boolean checkEmailWithSuffix(String email) {
		String regex = "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";

		return startCheck(regex, email);
	}

	/**
	 * ���EMAIL��ַ �û�������վ���Ʊ���>=1λ�ַ� ��ַ��β������2λ���ϣ��磺cn,test,com,info
	 * */
	public boolean checkEmail(String email) {
		String regex = "\\w+\\@\\w+\\.\\w{2,}";

		return startCheck(regex, email);
	}

	/**
	 * �����������(�й�),6λ����һλ�����Ƿ�0��ͷ������5λ����Ϊ0-9
	 * */
	public boolean checkPostcode(String postCode) {
		String regex = "^[1-9]\\d{5}";
		return startCheck(regex, postCode);
	}

	/**
	 * �����û��� ȡֵ��ΧΪa-z,A-Z,0-9,"_",���֣�������"_"��β �û�������С���Ⱥ���󳤶����ƣ������û���������4-20λ
	 * */
	public boolean checkUsername(String username, int min, int max) {
		String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * �����û��� ȡֵ��ΧΪa-z,A-Z,0-9,"_",���֣�������"_"��β ����Сλ�����Ƶ��û��������磺�û�������Ϊ4λ�ַ�
	 * */
	public boolean checkUsername(String username, int min) {
		// [\\w\u4e00-\u9fa5]{2,}?
		String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";

		return startCheck(regex, username);
	}

	/**
	 * �����û��� ȡֵ��ΧΪa-z,A-Z,0-9,"_",���� ����һλ�ַ�������ַ�λ�������ƣ�������"_"��β
	 * */
	public boolean checkUsername(String username) {
		String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
		return startCheck(regex, username);
	}

	/**
	 * �鿴IP��ַ�Ƿ�Ϸ�
	 * */
	public boolean checkIP(String ipAddress) {
		String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		return startCheck(regex, ipAddress);
	}

	/**
	 * ��֤���ڵ绰���� ��ʽ��010-67676767�����ų���3-4λ��������"0"��ͷ��������7-8λ
	 * */
	public boolean checkPhoneNr(String phoneNr) {
		String regex = "^[0]\\d{2,3}\\-\\d{7,8}";

		return startCheck(regex, phoneNr);
	}

	/**
	 * ��֤���ڵ绰���� ��ʽ��6767676, ����λ��������7-8λ,ͷһλ������"0"
	 * */
	public boolean checkPhoneNrWithoutCode(String phoneNr) {
		String reg = "^[1-9]\\d{6,7}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * ��֤���ڵ绰���� ��ʽ��0106767676����11λ����12λ��������0��ͷ
	 * */
	public boolean checkPhoneNrWithoutLine(String phoneNr) {
		String reg = "^[0]\\d{10,11}";

		return startCheck(reg, phoneNr);
	}

	/**
	 * ��֤�������֤���룺15��18λ����������ɣ�������0��ͷ
	 * */
	public boolean checkIdCard(String idNr) {
		String reg = "^[1-9](\\d{14}|\\d{17})";

		return startCheck(reg, idNr);
	}

	/**
	 * ��ַ��֤<br>
	 * �������ͣ�<br>
	 * http://www.test.com<br>
	 * http://163.com
	 * */
	public boolean checkWebSite(String url) {
		// http://www.163.com
		String reg = "^(http)\\://(\\w+\\.\\w+\\.\\w+|\\w+\\.\\w+)";

		return startCheck(reg, url);
	}
}
