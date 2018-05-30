package com.bn.jm.NewsRelease;

import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.GET_LM;
import static com.bn.core.Constant.GET_LM_FB_NEW;
import static com.bn.core.Constant.dataGeted;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.jltitle;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.bn.jm.LoginWindow;
import com.bn.jm.MainJFrame;
import com.bn.jm.TableHeader.GroupableTableHeader;
import com.bn.jm.TableHeader.GroupableTableHeaderUI;
import com.bn.util.SocketUtil;

/**
 * 发布新闻查看
 */
@SuppressWarnings("serial")
public class NewsReleasePanel extends JPanel {
	private JLabel jlNewsRelease = new JLabel("发布新闻查看");
	private JLabel jlPrograma = new JLabel("栏目列表");
	private JLabel jlNewsProgram = new JLabel("栏目所含新闻");

	JTable jtPrograma = new JTable() {
		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new GroupableTableHeader(columnModel);
		}
	};
	JScrollPane jsPrograma = new JScrollPane(jtPrograma);

	ProgramaTableModel tmPrograma;

	JTable jtNewsProgram = new JTable() {
		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new GroupableTableHeader(columnModel);
		}
	};
	JScrollPane jsNewsProgram = new JScrollPane(jtNewsProgram);
	//栏目新闻
	NewsProgramTableModel newsProgram;

	// 记录栏目表中上次点击的行
	int pressedRowInPrograma = -1;
	// 记录栏目新闻表中上次点击的行
	int pressedRowInNewsProgram = -1;

	int lmid;
	int xwid;

	MainJFrame mf;

	public NewsReleasePanel(MainJFrame mf) {
		this.mf = mf;
		this.setLayout(null);
		// 表头设置
		jlNewsRelease.setBounds(520, 20, 200, 20);
		jlNewsRelease.setFont(subtitle);
		this.add(jlNewsRelease);

		// 栏目表
		this.add(jlPrograma);
		jlPrograma.setBounds(25, 15 + 30 + 5, 120, 20);
		jlPrograma.setFont(jltitle);
		this.add(jsPrograma);
		jsPrograma.setBounds(25, 70 + 10, 280, 580);

		// 栏目所含新闻表
		this.add(jlNewsProgram);
		jlNewsProgram.setBounds(365 - 10, 15 + 30 + 5, 120, 20);
		jlNewsProgram.setFont(jltitle);
		this.add(jsNewsProgram);
		jsNewsProgram.setBounds(365 - 10, 70 + 10, 780 + 20, 580);
		// 280 780

		addTableListeners();

	}

	// 初始化表格
	public void initNewsRelease(JTable jtNewsRelease) {
		// 设置表头绘制器
		jtNewsRelease.getTableHeader().setUI(new GroupableTableHeaderUI());
		// 设置行高
		jtNewsRelease.setRowHeight(30);
		// 设置只能单选
		jtNewsRelease.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 获得table单元格绘制器
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// 设置表格里内容居中
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置第一列居中
		jtNewsRelease.setDefaultRenderer(Integer.class, dtcr);

		// 获得表头
		JTableHeader tableHeader = jtNewsRelease.getTableHeader();
		// 获得表头绘制器
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// 列名居中
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// 表格列不可移动
		tableHeader.setReorderingAllowed(false);

		// 获得每一列的引用
		TableColumn tc0 = jtNewsRelease.getColumnModel().getColumn(0);
		TableColumn tc1 = jtNewsRelease.getColumnModel().getColumn(1);
		TableColumn tc2 = jtNewsRelease.getColumnModel().getColumn(2);

		// 设置每一列宽度
		tc0.setPreferredWidth(50);
		tc1.setPreferredWidth(50);
		tc2.setPreferredWidth(160);

		// 设置每一列大小不可变
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
	}

	// 初始化表格
	public void initTableNewsProgram(JTable jtNewsProgram) {
		// 设置表头绘制器
		jtNewsProgram.getTableHeader().setUI(new GroupableTableHeaderUI());
		// 设置行高
		jtNewsProgram.setRowHeight(30);
		// 设置只能单选
		jtNewsProgram.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 获得table单元格绘制器
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// 设置表格里内容居中
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置第一列居中
		jtNewsProgram.setDefaultRenderer(Integer.class, dtcr);

		// 获得表头
		JTableHeader tableHeader = jtNewsProgram.getTableHeader();
		// 获得表头绘制器
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// 列名居中
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// 表格列不可移动
		tableHeader.setReorderingAllowed(false);

		// 获得每一列的引用
		TableColumn tc0 = jtNewsProgram.getColumnModel().getColumn(0);
		TableColumn tc1 = jtNewsProgram.getColumnModel().getColumn(1);
		TableColumn tc2 = jtNewsProgram.getColumnModel().getColumn(2);
		TableColumn tc3 = jtNewsProgram.getColumnModel().getColumn(3);
		TableColumn tc4 = jtNewsProgram.getColumnModel().getColumn(4);
		TableColumn tc5 = jtNewsProgram.getColumnModel().getColumn(5);

		// 设置每一列宽度
		tc0.setPreferredWidth(50);
		tc1.setPreferredWidth(50);
		tc2.setPreferredWidth(160);
		tc3.setPreferredWidth(290 + 20);
		tc4.setPreferredWidth(130);
		tc4.setCellRenderer(new JDateRenderer());
		tc5.setPreferredWidth(80);

		// 设置每一列大小不可变
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
		tc3.setResizable(false);
		tc4.setResizable(false);
		tc5.setResizable(false);
	}

	public void addTableListeners() {// 添加表格的监听器
		jtPrograma.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				final int row = jtPrograma.getSelectedRow();
				if (pressedRowInPrograma != row) {// 如果点击的不在同一行
					pressedRowInPrograma = row;
					lmid = Integer.parseInt(tmPrograma.tableData[pressedRowInPrograma][1].toString());
					dataGeted = false;
					new Thread() {
						public void run() {
							flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInPrograma][1].toString()));
							dataGeted = true;
						}
					}.start();
					// 监视线程
					LoginWindow.watchThread();
				}
			}

		});

		// 给栏目新闻添加表格监听器
		jtNewsProgram.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				final int row = jtNewsProgram.getSelectedRow();
				if (pressedRowInNewsProgram != row) {// 如果点击的不在同一行
					pressedRowInNewsProgram = row;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String xwid = newsProgram.tableData[pressedRowInNewsProgram][1].toString();
					mf.gotoXWCK(xwid, false);
				}
			}
		});

	}

	/*
	 * 数据更新方法
	 */

	// 更新栏目表数据的方法
	public void flushDataLM() {
		// 发送消息获得数据
		String msg = GET_LM;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		final List<String[]> list = SocketUtil.strToList(result);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					int colCount = 0;
					int rowCount = list.size();
					if (rowCount != 0) {
						colCount = list.get(0).length;
					}
					// 更新表格模型
					tmPrograma = new ProgramaTableModel(NewsReleasePanel.this);
					// 初始化表格数据
					tmPrograma.tableData = new Object[rowCount][colCount];
					// tmLM.OritableData = new Object[rowCount][colCount];
					// 通过循环将数据库中数据
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							tmPrograma.tableData[i][j] = list.get(i)[j];
							// tmLM.OritableData[i][j]=list.get(i)[j];
						}
					}
					// 设置表格模型
					jtPrograma.setModel(tmPrograma);
					// 更新表格外观
					initNewsRelease(jtPrograma);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新栏目所含新闻表数据的方法
	public void flushDataLMXW(int lmid) {
		// 发送消息获得数据
		String msg = GET_LM_FB_NEW;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(lmid);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		final List<String[]> list = SocketUtil.strToList(result);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					int colCount = 0;
					int rowCount = list.size();
					if (rowCount != 0) {
						colCount = list.get(0).length;
					}
					// 更新表格模型
					newsProgram = new NewsProgramTableModel(NewsReleasePanel.this);
					// 初始化表格数据
					newsProgram.tableData = new Object[rowCount][colCount];
					// 通过循环将数据库中数据
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							newsProgram.tableData[i][j] = list.get(i)[j];

						}
					}
					// 设置表格模型
					jtNewsProgram.setModel(newsProgram);
					// 更新表格外观
					initTableNewsProgram(jtNewsProgram);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// 绘制渐变 起始坐标 起始颜色
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
