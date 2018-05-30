package com.bn.jm.EmployeeManage;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Ô±¹¤½ÇÉ«
 *
 */
@SuppressWarnings("serial")
public class EmployeeRoleComboBoxEidtor extends AbstractCellEditor implements TableCellEditor, ItemListener {
	JComboBox jcbRole = new JComboBox();
	EmployeeManagePanel employeeManagePn;
	int row, col;
	String data;
	Map<String, String> RoleMapforEditor;
	Map<String, String> RoleMapforRender;

	public EmployeeRoleComboBoxEidtor(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
		Set<String> keyset = this.employeeManagePn.RoleMapforEditor.keySet();
		for (String item : keyset) {
			jcbRole.addItem(item);
		}
		jcbRole.addItemListener(this);
	}

	@Override
	public Object getCellEditorValue() {
		return data;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			String val = jcbRole.getSelectedItem().toString();
			data = employeeManagePn.RoleMapforEditor.get(val);
			employeeManagePn.tableDataEmployee[row][col] = data;
			employeeManagePn.tmEmployee.fireTableCellUpdated(row, col);
		}

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.row = row;
		this.col = column;
		String select = employeeManagePn.RoleMapforRender.get(value.toString());
		jcbRole.setSelectedItem(select);
		return jcbRole;
	}

}
