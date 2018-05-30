package com.bn.jm.PersonalNewsManager;

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
 * �����޸ı༭��
 */
@SuppressWarnings("serial")
public class UpdateButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	JButton jbXG = new JButton("", null);
	PersonalNewsManagerPanel personalNewsPn;
	String ztmc, xwid;
	MainJFrame mf;
	// �����洢ÿ�ε���ĵ�Ԫ���λ��
	int row, column;

	public UpdateButtonEditor(PersonalNewsManagerPanel personalNewsPn, MainJFrame mf) {
		this.personalNewsPn = personalNewsPn;
		jbXG.addActionListener(this);
		this.mf = mf;
	}

	// jbxg�ļ�����
	@Override
	public void actionPerformed(ActionEvent e) {
		// xwid=grxwglpn.tableData[row][0].toString();
		ztmc = (personalNewsPn.tableData[row][column - 2]).toString();
		xwid = (personalNewsPn.tableData[row][0]).toString();
		if (ztmc.equals("δ�ύ���") || ztmc.equals("δͨ�����")) {
			mf.gotoXWXG(xwid);
		} else {
			JOptionPane.showMessageDialog(personalNewsPn, "ֻ�����޸�δ�ύ���,δͨ����˵����ţ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// ��ȡ��Ԫ��༭�ؼ�
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// ������ʾ�����ֺ�ͼ��
		String text = "�༭";
		String path = bpicPath + "xg.png";
		// ��¼�� ��
		this.row = row;
		this.column = column;
		jbXG.setText(text);
		jbXG.setIcon(new ImageIcon(path));
		return jbXG;
	}

	// ��ñ༭����ֵ
	@Override
	public Object getCellEditorValue() {
		return jbXG;
	}

}