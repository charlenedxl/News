package com.bn.sjb;

public class NewPC1 extends NewPC implements java.io.Serializable {
	private static final long serialVersionUID = -6627190243762427107L;
	public String newsTitle; // ���ű���
	public String newsSummary; // ���Ÿ���
	public String newsSources; // ������Դ
	public String releaseTime; // ����ʱ��
	public String newsContent; // ��������
	public String employeeId; // Ա��id
	public int newsStatus; // ����״̬
	public int formatId; // ��ʽid
	public byte[] picTitle;// ����ͼƬ

	public NewPC1() {
	}

	// ���в�ͼ
	public NewPC1(String newsTitle, String newsSummary, String newsSources, String releaseTime, String newsContent,
			String employeeId, int newsStatus, int formatId, byte[] picTitle) {
		this.newsTitle = newsTitle;
		this.newsSummary = newsSummary;
		this.newsSources = newsSources;
		this.releaseTime = releaseTime;
		this.newsContent = newsContent;
		this.employeeId = employeeId;
		this.newsStatus = newsStatus;
		this.picTitle = picTitle;
		this.formatId = formatId;
	}

	// ���в�ͼ
	/*
	 * public NewPC1(String xwbt,String xwgs,String xwly,String fbsj,String xwnr,
	 * String ygid,int ztid,int bsid,String picTitle) { this.xwbt=xwbt;
	 * this.xwgs=xwgs; this.xwly=xwly; this.fbsj=fbsj; this.xwnr=xwnr;
	 * this.ygid=ygid; this.ztid=ztid; this.picTitle=PicUtils.getBytePic(picTitle);
	 * this.bsid=bsid; }
	 */

}
