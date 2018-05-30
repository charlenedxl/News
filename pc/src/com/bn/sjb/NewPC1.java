package com.bn.sjb;

public class NewPC1 extends NewPC implements java.io.Serializable {
	private static final long serialVersionUID = -6627190243762427107L;
	public String newsTitle; // 新闻标题
	public String newsSummary; // 新闻概述
	public String newsSources; // 新闻来源
	public String releaseTime; // 发布时间
	public String newsContent; // 新闻内容
	public String employeeId; // 员工id
	public int newsStatus; // 新闻状态
	public int formatId; // 板式id
	public byte[] picTitle;// 标题图片

	public NewPC1() {
	}

	// 含有插图
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

	// 含有插图
	/*
	 * public NewPC1(String xwbt,String xwgs,String xwly,String fbsj,String xwnr,
	 * String ygid,int ztid,int bsid,String picTitle) { this.xwbt=xwbt;
	 * this.xwgs=xwgs; this.xwly=xwly; this.fbsj=fbsj; this.xwnr=xwnr;
	 * this.ygid=ygid; this.ztid=ztid; this.picTitle=PicUtils.getBytePic(picTitle);
	 * this.bsid=bsid; }
	 */

}
