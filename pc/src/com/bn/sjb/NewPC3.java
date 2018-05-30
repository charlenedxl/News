package com.bn.sjb;

public class NewPC3 extends NewPC implements java.io.Serializable {
	private static final long serialVersionUID = -6627190243762427109L;
	public String newsTitle; // ���ű���
	public String newsSummary; // ���Ÿ���
	public String newsSources; // ������Դ
	public String releaseTime; // ����ʱ��
	public String newsContent; // ��������
	public String employeeId; // Ա��id
	public int newsStatus; // ����״̬
	public int formatId; // ��ʽid
	public byte[] picTitle;// ����ͼƬ
	public byte[] pic1;// ��ͼ1
	public String pic1MS;// ��ͼ1����
	public byte[] pic2;// ��ͼ2
	public String pic2MS;// ��ͼ2����

	public NewPC3() {
	}

	public NewPC3(String newsTitle, String newsSummary, String newsSources, String releaseTime, String newsContent,
			String employeeId, int newsStatus, int formatId, byte[] picTitle, byte[] pic1, String pic1MS, byte[] pic2,
			String pic2MS) {
		this.newsTitle = newsTitle;
		this.newsSummary = newsSummary;
		this.newsSources = newsSources;
		this.releaseTime = releaseTime;
		this.newsContent = newsContent;
		this.employeeId = employeeId;
		this.newsStatus = newsStatus;
		this.picTitle = picTitle;
		this.pic1 = pic1;
		this.pic1MS = pic1MS;
		this.pic2 = pic2;
		this.pic2MS = pic2MS;
		this.formatId = formatId;
	}

	// ���в�ͼ
	/*
	 * public NewPC3(String xwbt,String xwgs,String xwly,String fbsj,String xwnr,
	 * String ygid,int ztid,int bsid,String picTitle,String pic1,String pic1MS
	 * ,String pic2,String pic2MS) { this.xwbt=xwbt; this.xwgs=xwgs; this.xwly=xwly;
	 * this.fbsj=fbsj; this.xwnr=xwnr; this.ygid=ygid; this.ztid=ztid;
	 * this.picTitle=PicUtils.getBytePic(picTitle);
	 * this.pic1=PicUtils.getBytePic(pic1); this.pic1MS=pic1MS;
	 * this.pic2=PicUtils.getBytePic(pic2); this.pic2MS=pic2MS; this.bsid=bsid; }
	 */

}
