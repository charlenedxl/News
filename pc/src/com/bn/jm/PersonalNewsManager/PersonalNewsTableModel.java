package com.bn.jm.PersonalNewsManager;

import javax.swing.table.AbstractTableModel;

/*
 * �������ű��
 */
@SuppressWarnings({ "serial" })
public class PersonalNewsTableModel extends AbstractTableModel {

	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;
	PersonalNewsManagerPanel personalNewsPn;

	public PersonalNewsTableModel(PersonalNewsManagerPanel personalNewsPn) {
		this.personalNewsPn = personalNewsPn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return personalNewsPn.head.length;
	}

	// �������
	@Override
	public int getRowCount() {
		return personalNewsPn.tableData.length;
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return personalNewsPn.tableData[r][c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return personalNewsPn.typeArray[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return personalNewsPn.head[arg0];
	}

	// �����Ƿ�ɱ༭
	@Override
	public boolean isCellEditable(int r, int c) {
		if (c > 5)
			return true;
		return false;
	}

	// ����ĳһ��Ԫ���ֵ ����ڵ�ֵ���ʱ�����
	@Override
	public void setValueAt(Object value, int r, int c) {

	}

}
