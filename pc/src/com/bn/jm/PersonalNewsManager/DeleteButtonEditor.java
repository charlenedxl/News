package com.bn.jm.PersonalNewsManager;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.bn.jm.LoginWindow;
import com.bn.util.SocketUtil;

import static com.bn.core.Constant.DEL_NEW;
import static com.bn.core.Constant.bpicPath;
import static com.bn.core.Constant.dataGeted;

/**
 * ����ɾ���ı༭��
 */
@SuppressWarnings("serial")
public class DeleteButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {// ����ɾ����JButton�ı༭��

	JButton jbSC = new JButton("", null);
	PersonalNewsManagerPanel personalNewsPn;
	String ztmc, xwid;
	// �����洢ÿ�ε���ĵ�Ԫ���λ��
	int row, column;

	public DeleteButtonEditor(PersonalNewsManagerPanel personalNewsPn) {
		this.personalNewsPn = personalNewsPn;
		jbSC.addActionListener(this);
	}

	// jbxg�ļ�����
	@Override
	public void actionPerformed(ActionEvent e) {
		ztmc = (personalNewsPn.tableData[row][column - 3]).toString();
		xwid = (personalNewsPn.tableData[row][0]).toString();
		if (ztmc.equals("δ�ύ���") || ztmc.equals("δͨ�����")) {
			int i = JOptionPane.showConfirmDialog(personalNewsPn, "ȷ��Ҫɾ������������?", "��ʾ", JOptionPane.CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (i == 0)// �����"��"
			{
				delNEW(xwid);
			}
		} else {
			JOptionPane.showMessageDialog(personalNewsPn, "ֻ����ɾ��δ�ύ���,δͨ����˵����ţ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// ��ȡ��Ԫ��༭�ؼ�
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// ������ʾ�����ֺ�ͼ��
		String text = "ɾ��";
		String path = bpicPath + "sc.png";
		// ��¼�� ��
		this.row = row;
		this.column = column;
		jbSC.setText(text);
		jbSC.setIcon(new ImageIcon(path));
		return jbSC;
	}

	// ��ñ༭����ֵ
	@Override
	public Object getCellEditorValue() {
		return jbSC;
	}

	// ɾ������
	public void delNEW(final String xwid)// ������ ��ɫID
	{
		// �����߳�
		dataGeted = false;
		new Thread() {
			public void run() {
				// ������Ϣ�������
				String msg = DEL_NEW;// �ܷ�ɾ���ý�ɫ
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(xwid);
				sb.append(msg);
				String result = SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted = true;
				if (result.equals("ok")) {
					JOptionPane.showMessageDialog(personalNewsPn, "����ɾ���ɹ���", "��ʾ", JOptionPane.NO_OPTION);
					dataGeted = false;
					LoginWindow.watchThread();
					personalNewsPn.flushData();
					dataGeted = true;
				} else {
					JOptionPane.showMessageDialog(personalNewsPn, "����ɾ��ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}.start();
		// �����߳�
		LoginWindow.watchThread();
	}
}
