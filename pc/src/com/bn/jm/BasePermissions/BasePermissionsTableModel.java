package com.bn.jm.BasePermissions;

import javax.swing.table.AbstractTableModel;

/*
 * ����Ȩ�޲鿴��ģ��
 */
@SuppressWarnings({ "serial" })
public class BasePermissionsTableModel extends AbstractTableModel {
	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;
	// JBQXCKPanel jbqxckpn;
	BasePermissionsPanel basePermissionsPn;

	public BasePermissionsTableModel(BasePermissionsPanel basePermissionsPn) {
		this.basePermissionsPn = basePermissionsPn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return 2;
	}

	// �������
	@Override
	public int getRowCount() {
		return basePermissionsPn.tableDataPermissions.size();
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return basePermissionsPn.tableDataPermissions.get(r)[c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return basePermissionsPn.typeArrayQX[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return basePermissionsPn.headPermissions[arg0];
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
