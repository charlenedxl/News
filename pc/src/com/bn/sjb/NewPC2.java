package com.bn.sjb;

public class NewPC2 extends NewPC implements java.io.Serializable {
	private static final long serialVersionUID = -6627190243762427108L;
	public String newsTitle; // ���ű���
	public String newsSummary; // ���Ÿ���
	public String newsSources; // ������Դ
	public String releaseTime; // ����ʱ��
	public String newsContent; // ��������
	public String employeeId; // Ա��id
	public int newsStatus; // ����״̬
	public int formatId; // ��ʽid
	public byte[] picTitle;// ����ͼƬ
	public String pic1MS;
	public byte[] pic1;// ��ͼ1

	public NewPC2() {
	}

	// ���в�ͼ
	public NewPC2(String newsTitle, String newsSummary, String newsSources, String releaseTime, String newsContent,
			String employeeId, int newsStatus, int formatId, byte[] picTitle, byte[] pic1, String pic1MS) {
		this.newsTitle = newsTitle;
		this.newsSummary = newsSummary;
		this.newsSources = newsSources;
		this.releaseTime = releaseTime;
		this.newsContent = newsContent;
		this.employeeId = employeeId;
		this.newsStatus = newsStatus;
		this.picTitle = picTitle;
		this.pic1 = pic1;
		this.formatId = formatId;
		this.pic1MS = pic1MS;
	}

	// ���в�ͼ
	/*
	 * public NewPC2(String xwbt, String xwgs, String xwly, String fbsj, String
	 * xwnr, String ygid, int ztid, int bsid, String picTitle, String pic1,String
	 * pic1MS) { this.xwbt = xwbt; this.xwgs = xwgs; this.xwly = xwly; this.fbsj =
	 * fbsj; this.xwnr = xwnr; this.ygid = ygid; this.ztid = ztid; this.picTitle =
	 * PicUtils.getBytePic(picTitle); this.pic1 = PicUtils.getBytePic(pic1);
	 * this.bsid = bsid; this.pic1MS=pic1MS; }
	 */

}
