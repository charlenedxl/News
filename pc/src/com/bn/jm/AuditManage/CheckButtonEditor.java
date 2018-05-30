package com.bn.jm.AuditManage;

import static com.bn.core.Constant.bpicPath;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.bn.jm.MainJFrame;

/**
 * �鿴��ť
 *
 */
@SuppressWarnings("serial")
public class CheckButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	JButton jbCheck = new JButton("", null);
	AuditManagePanel auditManagePn;
	String shid;
	MainJFrame mf;
	// �����洢ÿ�ε���ĵ�Ԫ���λ��
	int row, column;

	public CheckButtonEditor(AuditManagePanel auditManagePn, MainJFrame mf) {
		this.auditManagePn = auditManagePn;
		jbCheck.addActionListener(this);
		this.mf = mf;
	}

	// jbxg�ļ�����
	@Override
	public void actionPerformed(ActionEvent e) {
		shid = (auditManagePn.tableData[row][0]).toString();
		mf.gotoCK(shid);
	}

	// ��ȡ��Ԫ��༭�ؼ�
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// ������ʾ�����ֺ�ͼ��
		String text = "�鿴����";
		String path = bpicPath + "ckxw.png";
		// ��¼�� ��
		this.row = row;
		this.column = column;
		jbCheck.setText(text);
		jbCheck.setIcon(new ImageIcon(path));
		return jbCheck;
	}

	// ��ñ༭����ֵ
	@Override
	public Object getCellEditorValue() {
		return jbCheck;
	}

}
