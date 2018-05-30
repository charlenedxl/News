package com.bn.jm.EmployeeManage;

import static com.bn.core.Constant.selectedBg;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 员工离职
 *
 */
@SuppressWarnings("serial")
public class EmployeeTurnoverRenderer extends JLabel implements TableCellRenderer {
	Map<String, String> map = new HashMap<String, String>();

	public EmployeeTurnoverRenderer() {
		map.put("0", "在职");
		map.put("1", "离职");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String lzly = map.get(value.toString());
		this.setText(lzly);
		this.setBackground(selectedBg);
		if (isSelected) {
			this.setOpaque(true);

		} else {
			this.setOpaque(false);
		}
		return this;
	}

}
