package com.bn.jm.EmployeeManage;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * 员工离职按钮编辑
 *
 */
@SuppressWarnings("serial")
public class EmployeeTurnoverComboBoxEditor extends AbstractCellEditor implements TableCellEditor, ItemListener {
	String[] strIsTurnover = { "在职", "离职" };
	JComboBox jcb = new JComboBox(strIsTurnover);
	int row, col;
	String data;
	EmployeeManagePanel employeeManagePn;

	public EmployeeTurnoverComboBoxEditor(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
		jcb.addItemListener(this);
	}

	@Override
	public Object getCellEditorValue() {

		return data;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			if (jcb != null) {
				String data = new String(jcb.getSelectedIndex() + "");
				employeeManagePn.tableDataEmployee[row][col] = data;
				employeeManagePn.tmEmployee.fireTableCellUpdated(row, col);
			}
		}

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.row = row;
		this.col = column;
		int lzid = Integer.parseInt(value.toString());
		jcb.setSelectedIndex(lzid);
		return jcb;
	}

}
