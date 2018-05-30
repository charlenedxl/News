package com.bn.jm.AuditManage;

import javax.swing.table.AbstractTableModel;

/*
 * 审核记录模型
 */
@SuppressWarnings({ "serial" })
public class AuditRecordTableModel extends AbstractTableModel {

	// 指定单元格的值是否改变
	boolean isCellChanged = false;

	// 最近编辑过的行
	int lastEditRow;
	AuditManagePanel auditManagePn;

	public AuditRecordTableModel(AuditManagePanel auditManagePn) {
		this.auditManagePn = auditManagePn;
	}

	// 获得列数
	@Override
	public int getColumnCount() {
		return auditManagePn.head.length;
	}

	// 获得行数
	@Override
	public int getRowCount() {
		return auditManagePn.tableData.length;
	}

	// 获得某单元格的值
	@Override
	public Object getValueAt(int r, int c) {
		return auditManagePn.tableData[r][c];
	}

	// 获得每一列的类型
	@Override
	public Class<?> getColumnClass(int arg0) {
		return auditManagePn.typeArray[arg0];
	}

	// 获得每一列的列头
	@Override
	public String getColumnName(int arg0) {
		return auditManagePn.head[arg0];
	}

	// 设置是否可编辑
	@Override
	public boolean isCellEditable(int r, int c) {
		if (c > 7)
			return true;
		return false;
	}

	// 设置某一单元格的值 表格内的值变得时候调用
	@Override
	public void setValueAt(Object value, int r, int c) {

	}

}
