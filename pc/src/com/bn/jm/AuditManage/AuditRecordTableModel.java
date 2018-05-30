package com.bn.jm.AuditManage;

import javax.swing.table.AbstractTableModel;

/*
 * ��˼�¼ģ��
 */
@SuppressWarnings({ "serial" })
public class AuditRecordTableModel extends AbstractTableModel {

	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;
	AuditManagePanel auditManagePn;

	public AuditRecordTableModel(AuditManagePanel auditManagePn) {
		this.auditManagePn = auditManagePn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return auditManagePn.head.length;
	}

	// �������
	@Override
	public int getRowCount() {
		return auditManagePn.tableData.length;
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return auditManagePn.tableData[r][c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return auditManagePn.typeArray[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return auditManagePn.head[arg0];
	}

	// �����Ƿ�ɱ༭
	@Override
	public boolean isCellEditable(int r, int c) {
		if (c > 7)
			return true;
		return false;
	}

	// ����ĳһ��Ԫ���ֵ ����ڵ�ֵ���ʱ�����
	@Override
	public void setValueAt(Object value, int r, int c) {

	}

}
