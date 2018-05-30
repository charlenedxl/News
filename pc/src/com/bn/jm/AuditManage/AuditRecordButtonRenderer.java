package com.bn.jm.AuditManage;

import static com.bn.core.Constant.*;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 审核记录按钮
 *
 */
@SuppressWarnings("serial")
public class AuditRecordButtonRenderer extends JButton implements TableCellRenderer {
	public AuditRecordButtonRenderer() {
		// 是否设置为不透明
		this.setOpaque(false);
		// 设置水平居中
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	// 获得单元格绘制器
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String path = null;
		String text = null;
		if (column == 8) {
			text = "查看新闻";
			path = bpicPath + "ckxw.png";
		} else {
			text = "审核";
			path = bpicPath + "sh.png";
		}
		// 设置绘制的JButton的文字和图标
		this.setText(text);
		this.setIcon(new ImageIcon(path));
		return this;
	}
}
