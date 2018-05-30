package com.bn.jm.AuditManage;

import static com.bn.core.Constant.bpicPath;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.bn.jm.MainJFrame;

/**
 * ��˰�ť
 *
 */
@SuppressWarnings("serial")
public class AuditButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	JButton jbAudit = new JButton("", null);
	AuditManagePanel auditManagePn;
	String ztmc, shid;
	MainJFrame mf;
	// �����洢ÿ�ε���ĵ�Ԫ���λ��
	int row, column;

	public AuditButtonEditor(AuditManagePanel auditManagePn, MainJFrame mf) {
		this.auditManagePn = auditManagePn;
		jbAudit.addActionListener(this);
		this.mf = mf;
	}

	// jbxg�ļ�����
	@Override
	public void actionPerformed(ActionEvent e) {
		ztmc = (auditManagePn.tableData[row][column - 2]).toString();
		shid = (auditManagePn.tableData[row][0]).toString();
		// System.out.println(ztmc);
		if (ztmc.equals("�ύδ���")) {
			mf.gotoSH(shid);
		} else {
			JOptionPane.showMessageDialog(auditManagePn, "�������Ѿ���˹��ˣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// ��ȡ��Ԫ��༭�ؼ�
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// ������ʾ�����ֺ�ͼ��
		String text = "���";
		String path = bpicPath + "sh.png";
		// ��¼�� ��
		this.row = row;
		this.column = column;
		jbAudit.setText(text);
		jbAudit.setIcon(new ImageIcon(path));
		return jbAudit;
	}

	// ��ñ༭����ֵ
	@Override
	public Object getCellEditorValue() {
		return jbAudit;
	}

}
