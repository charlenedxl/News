package com.bn.sjb;

public class NewPC3 extends NewPC implements java.io.Serializable {
	private static final long serialVersionUID = -6627190243762427109L;
	public String newsTitle; // 新闻标题
	public String newsSummary; // 新闻概述
	public String newsSources; // 新闻来源
	public String releaseTime; // 发布时间
	public String newsContent; // 新闻内容
	public String employeeId; // 员工id
	public int newsStatus; // 新闻状态
	public int formatId; // 板式id
	public byte[] picTitle;// 标题图片
	public byte[] pic1;// 插图1
	public String pic1MS;// 插图1描述
	public byte[] pic2;// 插图2
	public String pic2MS;// 插图2描述

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

	// 含有插图
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
