package com.bn.jm.EmployeeManage;

import javax.swing.table.AbstractTableModel;

/*
 * Ա��ģ��
 */
@SuppressWarnings({ "serial" })
public class EmployeeTableModel extends AbstractTableModel {

	// ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;

	// ����༭������
	int lastEditRow;
	EmployeeManagePanel employeeManagePn;

	public EmployeeTableModel(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
	}

	// �������
	@Override
	public int getColumnCount() {
		return employeeManagePn.headEmployee.length;
	}

	// �������
	@Override
	public int getRowCount() {
		return employeeManagePn.tableDataEmployee.length;
	}

	// ���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		return employeeManagePn.tableDataEmployee[r][c];
	}

	// ���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return employeeManagePn.typeArrayEmployee[arg0];
	}

	// ���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return employeeManagePn.headEmployee[arg0];
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
		// ��¼�ո��޸����ݵ���
		employeeManagePn.lastEditRow = r;
		// System.out.println(value);
		if (value != null) {
			if (c != 9) {
				// �޸ı��ģ������
				// String data=new String(value.toString());
				if (!employeeManagePn.origindataEmployee[r][c].equals(value)) {
					employeeManagePn.tableDataEmployee[r][c] = value;
					this.fireTableCellUpdated(r, c);
				}

			} else {
				employeeManagePn.tableDataEmployee[r][c] = value;
			}
		}

	}

}
