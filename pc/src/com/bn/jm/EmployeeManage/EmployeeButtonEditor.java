package com.bn.jm.EmployeeManage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import static com.bn.core.Constant.bpicPath;

/**
 * 员工按钮编辑
 *
 */
@SuppressWarnings("serial")
public class EmployeeButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {// 员工级别JButton的编辑器

	JButton jbxg = new JButton("", null);
	EmployeeManagePanel employeeManagePn;
	// 用来存储每次点击的单元格的位置
	int row, column;

	public EmployeeButtonEditor(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
		jbxg.addActionListener(this);
	}

	// jbxg的监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		if (employeeManagePn.dataChangedForButton()) {
			employeeManagePn.xgYG(employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][0].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][6].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][7].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][8].toString());
		}

	}

	// 获取单元格编辑控件
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// 记录行 列
		this.row = row;
		this.column = column;
		// 设置显示的文字和图标
		String path = null;
		String text = null;
		text = "修改";
		path = bpicPath + "xg.png";
		jbxg.setText(text);
		jbxg.setIcon(new ImageIcon(path));
		return jbxg;
	}

	// 获得编辑器的值
	@Override
	public Object getCellEditorValue() {
		return jbxg;
	}
}
