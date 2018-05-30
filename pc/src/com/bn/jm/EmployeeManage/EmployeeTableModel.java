package com.bn.jm.EmployeeManage;

import javax.swing.table.AbstractTableModel;

/*
 * 员工模型
 */
@SuppressWarnings({ "serial" })
public class EmployeeTableModel extends AbstractTableModel {

	// 指定单元格的值是否改变
	boolean isCellChanged = false;

	// 最近编辑过的行
	int lastEditRow;
	EmployeeManagePanel employeeManagePn;

	public EmployeeTableModel(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
	}

	// 获得列数
	@Override
	public int getColumnCount() {
		return employeeManagePn.headEmployee.length;
	}

	// 获得行数
	@Override
	public int getRowCount() {
		return employeeManagePn.tableDataEmployee.length;
	}

	// 获得某单元格的值
	@Override
	public Object getValueAt(int r, int c) {
		return employeeManagePn.tableDataEmployee[r][c];
	}

	// 获得每一列的类型
	@Override
	public Class<?> getColumnClass(int arg0) {
		return employeeManagePn.typeArrayEmployee[arg0];
	}

	// 获得每一列的列头
	@Override
	public String getColumnName(int arg0) {
		return employeeManagePn.headEmployee[arg0];
	}

	// 设置是否可编辑
	@Override
	public boolean isCellEditable(int r, int c) {
		if (c > 5)
			return true;
		return false;
	}

	// 设置某一单元格的值 表格内的值变得时候调用
	@Override
	public void setValueAt(Object value, int r, int c) {
		// 记录刚刚修改数据的行
		employeeManagePn.lastEditRow = r;
		// System.out.println(value);
		if (value != null) {
			if (c != 9) {
				// 修改表格模型数据
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
