package com.bn.jm.EmployeeManage;

import static com.bn.core.Constant.selectedBg;
import static com.bn.core.Constant.tpicPath;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 员工部门Render
 *
 */
@SuppressWarnings("serial")
public class EmployeeDepartmentRenderer extends JLabel implements TableCellRenderer {
	EmployeeManagePanel employeeManagePn;

	public EmployeeDepartmentRenderer(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String bmid = value.toString();
		for (int i = 0; i < employeeManagePn.listDepartment.size(); i++) {
			String[] str = employeeManagePn.listDepartment.get(i);
			if (bmid.equals(str[0])) {
				this.setText(str[2]);
				this.setIcon(new ImageIcon(tpicPath + "bm.png"));
			}
		}
		this.setBackground(selectedBg);
		if (isSelected) {
			this.setOpaque(true);
		} else {
			this.setOpaque(false);
		}
		return this;
	}

}
