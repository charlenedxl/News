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
 * Ա����ť�༭
 *
 */
@SuppressWarnings("serial")
public class EmployeeButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {// Ա������JButton�ı༭��

	JButton jbxg = new JButton("", null);
	EmployeeManagePanel employeeManagePn;
	// �����洢ÿ�ε���ĵ�Ԫ���λ��
	int row, column;

	public EmployeeButtonEditor(EmployeeManagePanel employeeManagePn) {
		this.employeeManagePn = employeeManagePn;
		jbxg.addActionListener(this);
	}

	// jbxg�ļ�����
	@Override
	public void actionPerformed(ActionEvent e) {
		if (employeeManagePn.dataChangedForButton()) {
			employeeManagePn.xgYG(employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][0].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][6].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][7].toString(),
					employeeManagePn.tableDataEmployee[employeeManagePn.lastEditRow][8].toString());
		}

	}

	// ��ȡ��Ԫ��༭�ؼ�
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// ��¼�� ��
		this.row = row;
		this.column = column;
		// ������ʾ�����ֺ�ͼ��
		String path = null;
		String text = null;
		text = "�޸�";
		path = bpicPath + "xg.png";
		jbxg.setText(text);
		jbxg.setIcon(new ImageIcon(path));
		return jbxg;
	}

	// ��ñ༭����ֵ
	@Override
	public Object getCellEditorValue() {
		return jbxg;
	}
}
