package com.bn.jm.AuditManage;

import static com.bn.core.Constant.*;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * ��˼�¼��ť
 *
 */
@SuppressWarnings("serial")
public class AuditRecordButtonRenderer extends JButton implements TableCellRenderer {
	public AuditRecordButtonRenderer() {
		// �Ƿ�����Ϊ��͸��
		this.setOpaque(false);
		// ����ˮƽ����
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	// ��õ�Ԫ�������
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String path = null;
		String text = null;
		if (column == 8) {
			text = "�鿴����";
			path = bpicPath + "ckxw.png";
		} else {
			text = "���";
			path = bpicPath + "sh.png";
		}
		// ���û��Ƶ�JButton�����ֺ�ͼ��
		this.setText(text);
		this.setIcon(new ImageIcon(path));
		return this;
	}
}
