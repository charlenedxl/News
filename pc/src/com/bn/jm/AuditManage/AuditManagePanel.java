package com.bn.jm.AuditManage;

import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.GET_SHJL;
import static com.bn.core.Constant.GET_SHJL_FILTER;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.dataGeted;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import com.bn.util.DateChooserJButton;
import com.bn.util.SocketUtil;

/**
 * 审核管理
 *
 */
@SuppressWarnings("serial")
public class AuditManagePanel extends JPanel {
	// ======================================个人新闻列表======================================
	// 个人新闻每一列的类型
	Class[] typeArray = { Integer.class, Integer.class, String.class, String.class, String.class, String.class,
			String.class, String.class, JButton.class, JButton.class };
	// 角色表头
	String[] head = { "审核编号", "新闻编号", "新闻标题", "提交人", "提交时间", "审核人", "审核时间", "审核状态", "查看新闻", "审核" };
	// 角色表格数据
	Object[][] tableData;

	JTable jtAuditRecord = new JTable() {
		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new GroupableTableHeader(columnModel);
		}
	};

	AuditRecordTableModel tmAuditRecord;

	// 存放表格的JScrollPane
	JScrollPane jspAuditRecord = new JScrollPane(jtAuditRecord);

	JLabel jlTitle = new JLabel("审核记录列表");

	MainJFrame mf;

	// =============================================筛选框==============================================
	// 筛选
	JScrollPane jspScreen = new JScrollPane();
	JLabel jlScreen = new JLabel("请选择筛选条件：");
	JCheckBox jcbAuditStatus = new JCheckBox("审核状态");
	JLabel jlAuditStatus = new JLabel("请选择审核状态：");

	JCheckBox jcbUnaudited = new JCheckBox("提交未审核");
	JCheckBox jcbUnapproved = new JCheckBox("未通过审核");
	JCheckBox jcbApproved = new JCheckBox("已通过审核");
	JCheckBox jcbKill = new JCheckBox("封杀");

	JCheckBox jcbSubmitTime = new JCheckBox("提交时间");
	JLabel jlTimeRange = new JLabel("请选择时间范围：");
	JLabel jlStartTime = new JLabel("起始时间:");
	JLabel jlEndTime = new JLabel("截止时间:");
	// 起始时间
	DateChooserJButton startTime = new DateChooserJButton();
	// 截止时间
	DateChooserJButton endTime = new DateChooserJButton();

	// 根据新闻id筛选
	JCheckBox jcbNewsNo = new JCheckBox("新闻编号");
	JLabel jlNewsNo = new JLabel("请输入新闻编号：");
	JLabel jlNewsId = new JLabel("新闻编号：");
	JTextField jtfNewsId = new JTextField(1);

	// 检索 按钮
	JButton jbRetrieval = new JButton("检索");
	// =============================================筛选框==============================================

	public String filter = null;

	public AuditManagePanel(MainJFrame mf) {
		this.mf = mf;
		this.setLayout(null);
		// 表头设置
		jspAuditRecord.setBounds(25, 50, 1135, 470);
		jlTitle.setBounds(520, 20, 200, 20);
		jlTitle.setFont(subtitle);
		this.add(jlTitle);
		this.add(jspAuditRecord);
		initFilterPane();
		addFilterListeners();
	}

	// 初始化筛选Panel
	public void initFilterPane() {
		this.add(jspScreen);
		jspScreen.setLayout(null);
		jspScreen.setBounds(25, 540, 1135, 140);

		// "请选择筛选条件"
		jspScreen.add(jlScreen);
		jlScreen.setBounds(5, 5, 100, 20);

		// "审核状态"
		jspScreen.add(jcbAuditStatus);
		jcbAuditStatus.setBounds(130, 5, 100, 20);

		// "请选择审核状态："
		jspScreen.add(jlAuditStatus);
		jlAuditStatus.setBounds(130, 30, 100, 20);

		// "提交未审核"
		jspScreen.add(jcbUnaudited);
		jcbUnaudited.setBounds(130, 55, 100, 20);
		jspScreen.add(jcbUnapproved);
		jcbUnapproved.setBounds(260, 55, 100, 20);

		jspScreen.add(jcbApproved);
		jcbApproved.setBounds(130, 80, 100, 20);

		jspScreen.add(jcbKill);
		jcbKill.setBounds(260, 80, 100, 20);

		// "时间"
		jspScreen.add(jcbSubmitTime);
		jcbSubmitTime.setBounds(500 + 30, 5, 100, 20);

		// "请选择时间范围："
		jspScreen.add(jlTimeRange);
		jlTimeRange.setBounds(500 + 30, 30, 100, 20);

		// "起始时间:" 那一排
		jspScreen.add(jlStartTime);
		jlStartTime.setBounds(500 + 30, 55, 60, 20);
		startTime.setBounds(560 + 30, 55, 170, 20);
		jspScreen.add(startTime);

		// "截止时间:" 那一排
		jspScreen.add(jlEndTime);
		jlEndTime.setBounds(500 + 30, 80, 60, 20);
		endTime.setBounds(560 + 30, 80, 170, 20);
		jspScreen.add(endTime);

		// "新闻编号单选按钮"
		jspScreen.add(jcbNewsNo);
		jcbNewsNo.setBounds(830, 5, 100, 20);

		// "请输入新闻编号："
		jspScreen.add(jlNewsNo);
		jlNewsNo.setBounds(830, 30, 100, 20);

		// "输入新闻编号"
		jspScreen.add(jlNewsId);
		jlNewsId.setBounds(830, 55, 100, 20);
		jspScreen.add(jtfNewsId);
		jtfNewsId.setBounds(890, 55, 130, 20);

		// 默认是没有选中的状态，所有可选项不可用
		jlAuditStatus.setEnabled(false);
		jcbUnaudited.setEnabled(false);
		jcbUnapproved.setEnabled(false);
		jcbApproved.setEnabled(false);
		jcbKill.setEnabled(false);
		jlTimeRange.setEnabled(false);
		jlStartTime.setEnabled(false);
		jlEndTime.setEnabled(false);
		startTime.setEnabled(false);
		endTime.setEnabled(false);
		jlNewsNo.setEnabled(false);
		jtfNewsId.setEnabled(false);
		jlNewsId.setEnabled(false);

		// "检索"按钮
		jspScreen.add(jbRetrieval);
		jbRetrieval.setBounds(830 + 130 - 25 + 3, 110, 80, 20);

	}

	public void addFilterListeners() {
		jcbAuditStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jcbAuditStatus.isSelected()) {
					jlAuditStatus.setEnabled(true);
					jcbUnaudited.setEnabled(true);
					jcbUnapproved.setEnabled(true);
					jcbApproved.setEnabled(true);
					jcbKill.setEnabled(true);

				} else {
					jlAuditStatus.setEnabled(false);
					jcbUnaudited.setEnabled(false);
					jcbUnapproved.setEnabled(false);
					jcbApproved.setEnabled(false);
					jcbKill.setEnabled(false);
				}
			}
		});

		jcbSubmitTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jcbSubmitTime.isSelected()) {
					jlTimeRange.setEnabled(true);
					jlStartTime.setEnabled(true);
					jlEndTime.setEnabled(true);
					startTime.setEnabled(true);
					endTime.setEnabled(true);

				} else {
					jlTimeRange.setEnabled(false);
					jlStartTime.setEnabled(false);
					jlEndTime.setEnabled(false);
					startTime.setEnabled(false);
					endTime.setEnabled(false);
				}

			}
		});

		jcbNewsNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jcbNewsNo.isSelected()) {
					jlNewsNo.setEnabled(true);
					jtfNewsId.setEnabled(true);
					jlNewsId.setEnabled(true);

				} else {
					jlNewsNo.setEnabled(false);
					jtfNewsId.setEnabled(false);
					jlNewsId.setEnabled(false);
				}
			}
		});

		jbRetrieval.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final StringBuilder sb = new StringBuilder();
				sb.append(GET_SHJL_FILTER);
				// 审核状态
				if (jcbAuditStatus.isSelected()) {

					if (jcbUnaudited.isSelected()) {
						sb.append("1,");
					}
					if (jcbUnapproved.isSelected()) {
						sb.append("2,");
					}
					if (jcbApproved.isSelected()) {
						sb.append("3,");
					}
					if (jcbKill.isSelected()) {
						sb.append("4,");
					}
					if ((!jcbUnaudited.isSelected()) && (!jcbUnapproved.isSelected()) && (!jcbApproved.isSelected())
							&& (!jcbKill.isSelected())) {
						sb.append("no");
					}
					sb.append("<->");

				} else {
					sb.append("no");
					sb.append("<->");
				}
				if (jcbSubmitTime.isSelected()) {
					String qssj = startTime.getText();
					String jzsj = endTime.getText();
					sb.append(qssj);
					sb.append(",");
					sb.append(jzsj);
					sb.append("<->");
				} else {
					sb.append("no");
					sb.append("<->");
				}
				if (jcbNewsNo.isSelected()) {
					String xwbh = AuditManagePanel.this.jtfNewsId.getText().trim();
					if (xwbh.length() <= 0) {
						JOptionPane.showMessageDialog(AuditManagePanel.this, "请输入新闻编号！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (!xwbh.matches("\\d+")) {
						JOptionPane.showMessageDialog(AuditManagePanel.this, "请确保您输入的是整数！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					sb.append(xwbh);
				} else {
					sb.append("no");
				}
				sb.append(GET_SHJL_FILTER);
				filter = sb.toString();
				System.out.println(sb.toString());
				dataGeted = false;
				new Thread() {
					public void run() {
						flushDataFilter(filter);
						dataGeted = true;
					}
				}.start();
				// 监视线程
				LoginWindow.watchThread();
			}
		});
	}

	// 初始化审核记录表
	public void initTable() {
		// 设置表头绘制器
		jtAuditRecord.getTableHeader().setUI(new GroupableTableHeaderUI());
		// 设置行高
		jtAuditRecord.setRowHeight(30);
		// 设置只能单选
		jtAuditRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 获得table单元格绘制器
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// 设置表格里内容居中
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置第一列居中
		jtAuditRecord.setDefaultRenderer(Integer.class, dtcr);

		// 为修改按钮和删除按钮添加绘制器
		AuditRecordButtonRenderer jButtonRenderer = new AuditRecordButtonRenderer();
		jtAuditRecord.setDefaultRenderer(JButton.class, jButtonRenderer);

		// jtGRXW.setDefaultEditor(JButton.class, ygButtonEidtor);

		// 获得表头
		JTableHeader tableHeader = jtAuditRecord.getTableHeader();
		// 获得表头绘制器
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// 列名居中
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// 表格列不可移动
		tableHeader.setReorderingAllowed(false);

		// 获得每一列的引用
		TableColumn tc0 = jtAuditRecord.getColumnModel().getColumn(0);
		TableColumn tc1 = jtAuditRecord.getColumnModel().getColumn(1);
		TableColumn tc2 = jtAuditRecord.getColumnModel().getColumn(2);
		TableColumn tc3 = jtAuditRecord.getColumnModel().getColumn(3);
		TableColumn tc4 = jtAuditRecord.getColumnModel().getColumn(4);
		TableColumn tc5 = jtAuditRecord.getColumnModel().getColumn(5);
		TableColumn tc6 = jtAuditRecord.getColumnModel().getColumn(6);
		TableColumn tc7 = jtAuditRecord.getColumnModel().getColumn(7);
		TableColumn tc8 = jtAuditRecord.getColumnModel().getColumn(8);
		TableColumn tc9 = jtAuditRecord.getColumnModel().getColumn(9);

		// 设置每一列宽度
		tc0.setPreferredWidth(60);
		tc1.setPreferredWidth(60);
		tc2.setPreferredWidth(175);
		tc3.setPreferredWidth(100);
		tc4.setPreferredWidth(140);
		tc4.setCellRenderer(new JDateRenderer());

		tc5.setPreferredWidth(100);
		// 获得table单元格绘制器
		tc6.setPreferredWidth(140);
		tc6.setCellRenderer(new JDateRenderer());
		tc7.setPreferredWidth(120);

		tc8.setPreferredWidth(120);
		CheckButtonEditor ckButtonEidtor = new CheckButtonEditor(this, mf);
		tc8.setCellEditor(ckButtonEidtor);

		tc9.setPreferredWidth(120);
		AuditButtonEditor shButtonEidtor = new AuditButtonEditor(this, mf);
		tc9.setCellEditor(shButtonEidtor);

		// 设置每一列大小不可变
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
		tc3.setResizable(false);
		tc4.setResizable(false);
		tc5.setResizable(false);
		tc6.setResizable(false);
		tc7.setResizable(false);
		tc8.setResizable(false);
		tc9.setResizable(false);

	}

	/*
	 * 数据更新方法
	 */

	// 更新审核记录表数据的方法
	public void flushData() {
		// 发送消息获得数据
		String msg = GET_SHJL;
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
					tmAuditRecord = new AuditRecordTableModel(AuditManagePanel.this);
					// 初始化表格数据
					tableData = new Object[rowCount][colCount + 2];
					// 通过循环将数据库中数据
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							if (list.get(i)[j].equals("null")) {
								tableData[i][j] = "暂无数据";
							} else {
								tableData[i][j] = list.get(i)[j];
							}
						}
						tableData[i][colCount] = new JButton();
						tableData[i][colCount + 1] = new JButton();
					}
					// 设置表格模型
					jtAuditRecord.setModel(tmAuditRecord);
					// 更新表格外观
					initTable();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 筛选之后更新审核记录表数据的方法
	public void flushDataFilter(String sb) {
		// 发送消息获得数据
		String result = SocketUtil.sendAndGetMsg(sb);
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
					tmAuditRecord = new AuditRecordTableModel(AuditManagePanel.this);
					// 初始化表格数据
					tableData = new Object[rowCount][colCount + 2];
					// 通过循环将数据库中数据
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							if (list.get(i)[j].equals("null")) {
								tableData[i][j] = "暂无数据";
							} else {
								tableData[i][j] = list.get(i)[j];
							}

						}
						tableData[i][colCount] = new JButton();
						tableData[i][colCount + 1] = new JButton();
					}
					// 设置表格模型
					jtAuditRecord.setModel(tmAuditRecord);
					// 更新表格外观
					initTable();
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
