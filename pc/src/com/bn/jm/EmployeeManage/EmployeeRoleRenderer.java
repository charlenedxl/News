package com.bn.jm.EmployeeManage;

import java.awt.Component;
import java.util.Map;
import static com.bn.core.Constant.selectedBg;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Ô±¹¤½ÇÉ«Render
 *
 */
@SuppressWarnings({ "unused", "serial" })
public class EmployeeRoleRenderer extends JLabel implements TableCellRenderer {
	EmployeeManagePanel employeeManagePn;

	public EmployeeRoleRenderer(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String key = value.toString();
		String jsmc = employeeManagePn.RoleMapforRender.get(key);
		this.setText(jsmc);
		this.setBackground(selectedBg);
		if (isSelected) {
			this.setOpaque(true);
		} else {
			this.setOpaque(false);
		}
		return this;
	}

}
