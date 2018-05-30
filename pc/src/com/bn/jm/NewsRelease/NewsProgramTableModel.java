package com.bn.jm.NewsRelease;

import javax.swing.table.AbstractTableModel;

/*
 * 栏目所含新闻
 */
@SuppressWarnings({ "serial" })
public class NewsProgramTableModel extends AbstractTableModel {
	// 个人新闻每一列的类型
	Class[] typeArray = { Integer.class, Integer.class, String.class, String.class, String.class, String.class };
	// 角色表头
	String[] head = { "顺序ID", "新闻ID", "新闻名称", "新闻概述", "发布时间", "提交人" };
	// 角色表格数据
	Object[][] tableData;

	// 发布新闻查看
	NewsReleasePanel newsReleasePn;

	// 指定单元格的值是否改变
	boolean isCellChanged = false;

	// 最近编辑过的行
	int lastEditRow;

	public NewsProgramTableModel(NewsReleasePanel newsReleasePn) {
		this.newsReleasePn = newsReleasePn;
	}

	// 获得列数
	@Override
	public int getColumnCount() {
		return head.length;
	}

	// 获得行数
	@Override
	public int getRowCount() {
		return tableData.length;
	}

	// 获得某单元格的值
	@Override
	public Object getValueAt(int r, int c) {
		return tableData[r][c];
	}

	// 获得每一列的类型
	@Override
	public Class<?> getColumnClass(int arg0) {
		return typeArray[arg0];
	}

	// 获得每一列的列头
	@Override
	public String getColumnName(int arg0) {
		return head[arg0];
	}

	// 设置是否可编辑
	@Override
	public boolean isCellEditable(int r, int c) {
		return false;
	}

	// 设置某一单元格的值 表格内的值变得时候调用
	@Override
	public void setValueAt(Object value, int r, int c) {

	}
}
