package com.bn.jm.RolePermissions;

import javax.swing.table.AbstractTableModel;

/*
 * 权限表模型
 */
@SuppressWarnings({ "serial" })
public class PermissionsTableModel extends AbstractTableModel {
	// 指定单元格的值是否改变
	boolean isCellChanged = false;

	// 最近编辑过的行
	int lastEditRow;

	// 角色权限
	RolePermissionsPanel rolePermissionsPn;

	public PermissionsTableModel(RolePermissionsPanel rolePermissionsPn) {
		this.rolePermissionsPn = rolePermissionsPn;
	}

	// 获得列数
	@Override
	public int getColumnCount() {
		return 2;
	}

	// 获得行数
	@Override
	public int getRowCount() {
		return rolePermissionsPn.tableDataPermissions.size();
	}

	// 获得某单元格的值
	@Override
	public Object getValueAt(int r, int c) {
		return rolePermissionsPn.tableDataPermissions.get(r)[c];
	}

	// 获得每一列的类型
	@Override
	public Class<?> getColumnClass(int arg0) {
		return rolePermissionsPn.typeArrayPermissions[arg0];
	}

	// 获得每一列的列头
	@Override
	public String getColumnName(int arg0) {
		return rolePermissionsPn.headPermissions[arg0];
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
