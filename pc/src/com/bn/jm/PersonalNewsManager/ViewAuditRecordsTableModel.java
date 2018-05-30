package com.bn.jm.PersonalNewsManager;

import javax.swing.table.AbstractTableModel;

/*
 * �鿴��˼�¼Table
 */
@SuppressWarnings({ "serial" })
public class ViewAuditRecordsTableModel extends AbstractTableModel {

	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;
	ViewAuditRecordsPanel viewRecordsPn;

	public ViewAuditRecordsTableModel(ViewAuditRecordsPanel viewRecordsPn) {
		this.viewRecordsPn = viewRecordsPn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return viewRecordsPn.head.length;
	}

	// �������
	@Override
	public int getRowCount() {
		return viewRecordsPn.tableData.length;
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return viewRecordsPn.tableData[r][c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return viewRecordsPn.typeArray[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return viewRecordsPn.head[arg0];
	}

	// �����Ƿ�ɱ༭
	@Override
	public boolean isCellEditable(int r, int c) {
		if (c == 3)
			return true;
		return false;
	}

	// ����ĳһ��Ԫ���ֵ ����ڵ�ֵ���ʱ�����
	@Override
	public void setValueAt(Object value, int r, int c) {

	}

}
