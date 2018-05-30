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
 * 查看按钮
 *
 */
@SuppressWarnings("serial")
public class CheckButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	JButton jbCheck = new JButton("", null);
	AuditManagePanel auditManagePn;
	String shid;
	MainJFrame mf;
	// 用来存储每次点击的单元格的位置
	int row, column;

	public CheckButtonEditor(AuditManagePanel auditManagePn, MainJFrame mf) {
		this.auditManagePn = auditManagePn;
		jbCheck.addActionListener(this);
		this.mf = mf;
	}

	// jbxg的监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		shid = (auditManagePn.tableData[row][0]).toString();
		mf.gotoCK(shid);
	}

	// 获取单元格编辑控件
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// 设置显示的文字和图标
		String text = "查看新闻";
		String path = bpicPath + "ckxw.png";
		// 记录行 列
		this.row = row;
		this.column = column;
		jbCheck.setText(text);
		jbCheck.setIcon(new ImageIcon(path));
		return jbCheck;
	}

	// 获得编辑器的值
	@Override
	public Object getCellEditorValue() {
		return jbCheck;
	}

}
