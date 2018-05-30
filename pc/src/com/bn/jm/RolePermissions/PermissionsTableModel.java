package com.bn.jm.RolePermissions;

import javax.swing.table.AbstractTableModel;

/*
 * Ȩ�ޱ�ģ��
 */
@SuppressWarnings({ "serial" })
public class PermissionsTableModel extends AbstractTableModel {
	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;

	// ��ɫȨ��
	RolePermissionsPanel rolePermissionsPn;

	public PermissionsTableModel(RolePermissionsPanel rolePermissionsPn) {
		this.rolePermissionsPn = rolePermissionsPn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return 2;
	}

	// �������
	@Override
	public int getRowCount() {
		return rolePermissionsPn.tableDataPermissions.size();
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return rolePermissionsPn.tableDataPermissions.get(r)[c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return rolePermissionsPn.typeArrayPermissions[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return rolePermissionsPn.headPermissions[arg0];
	}

	// �����Ƿ�ɱ༭
	@Override
	public boolean isCellEditable(int r, int c) {
		return false;
	}

	// ����ĳһ��Ԫ���ֵ ����ڵ�ֵ���ʱ�����
	@Override
	public void setValueAt(Object value, int r, int c) {

	}
}
