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
 * 新闻修改编辑器
 */
@SuppressWarnings("serial")
public class UpdateButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	JButton jbXG = new JButton("", null);
	PersonalNewsManagerPanel personalNewsPn;
	String ztmc, xwid;
	MainJFrame mf;
	// 用来存储每次点击的单元格的位置
	int row, column;

	public UpdateButtonEditor(PersonalNewsManagerPanel personalNewsPn, MainJFrame mf) {
		this.personalNewsPn = personalNewsPn;
		jbXG.addActionListener(this);
		this.mf = mf;
	}

	// jbxg的监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		// xwid=grxwglpn.tableData[row][0].toString();
		ztmc = (personalNewsPn.tableData[row][column - 2]).toString();
		xwid = (personalNewsPn.tableData[row][0]).toString();
		if (ztmc.equals("未提交审核") || ztmc.equals("未通过审核")) {
			mf.gotoXWXG(xwid);
		} else {
			JOptionPane.showMessageDialog(personalNewsPn, "只允许修改未提交审核,未通过审核的新闻！", "提示",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// 获取单元格编辑控件
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// 设置显示的文字和图标
		String text = "编辑";
		String path = bpicPath + "xg.png";
		// 记录行 列
		this.row = row;
		this.column = column;
		jbXG.setText(text);
		jbXG.setIcon(new ImageIcon(path));
		return jbXG;
	}

	// 获得编辑器的值
	@Override
	public Object getCellEditorValue() {
		return jbXG;
	}

}
