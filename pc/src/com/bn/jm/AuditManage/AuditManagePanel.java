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
 * ��˹���
 *
 */
@SuppressWarnings("serial")
public class AuditManagePanel extends JPanel {
	// ======================================���������б�======================================
	// ��������ÿһ�е�����
	Class[] typeArray = { Integer.class, Integer.class, String.class, String.class, String.class, String.class,
			String.class, String.class, JButton.class, JButton.class };
	// ��ɫ��ͷ
	String[] head = { "��˱��", "���ű��", "���ű���", "�ύ��", "�ύʱ��", "�����", "���ʱ��", "���״̬", "�鿴����", "���" };
	// ��ɫ�������
	Object[][] tableData;

	JTable jtAuditRecord = new JTable() {
		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new GroupableTableHeader(columnModel);
		}
	};

	AuditRecordTableModel tmAuditRecord;

	// ��ű���JScrollPane
	JScrollPane jspAuditRecord = new JScrollPane(jtAuditRecord);

	JLabel jlTitle = new JLabel("��˼�¼�б�");

	MainJFrame mf;

	// =============================================ɸѡ��==============================================
	// ɸѡ
	JScrollPane jspScreen = new JScrollPane();
	JLabel jlScreen = new JLabel("��ѡ��ɸѡ������");
	JCheckBox jcbAuditStatus = new JCheckBox("���״̬");
	JLabel jlAuditStatus = new JLabel("��ѡ�����״̬��");

	JCheckBox jcbUnaudited = new JCheckBox("�ύδ���");
	JCheckBox jcbUnapproved = new JCheckBox("δͨ�����");
	JCheckBox jcbApproved = new JCheckBox("��ͨ�����");
	JCheckBox jcbKill = new JCheckBox("��ɱ");

	JCheckBox jcbSubmitTime = new JCheckBox("�ύʱ��");
	JLabel jlTimeRange = new JLabel("��ѡ��ʱ�䷶Χ��");
	JLabel jlStartTime = new JLabel("��ʼʱ��:");
	JLabel jlEndTime = new JLabel("��ֹʱ��:");
	// ��ʼʱ��
	DateChooserJButton startTime = new DateChooserJButton();
	// ��ֹʱ��
	DateChooserJButton endTime = new DateChooserJButton();

	// ��������idɸѡ
	JCheckBox jcbNewsNo = new JCheckBox("���ű��");
	JLabel jlNewsNo = new JLabel("���������ű�ţ�");
	JLabel jlNewsId = new JLabel("���ű�ţ�");
	JTextField jtfNewsId = new JTextField(1);

	// ���� ��ť
	JButton jbRetrieval = new JButton("����");
	// =============================================ɸѡ��==============================================

	public String filter = null;

	public AuditManagePanel(MainJFrame mf) {
		this.mf = mf;
		this.setLayout(null);
		// ��ͷ����
		jspAuditRecord.setBounds(25, 50, 1135, 470);
		jlTitle.setBounds(520, 20, 200, 20);
		jlTitle.setFont(subtitle);
		this.add(jlTitle);
		this.add(jspAuditRecord);
		initFilterPane();
		addFilterListeners();
	}

	// ��ʼ��ɸѡPanel
	public void initFilterPane() {
		this.add(jspScreen);
		jspScreen.setLayout(null);
		jspScreen.setBounds(25, 540, 1135, 140);

		// "��ѡ��ɸѡ����"
		jspScreen.add(jlScreen);
		jlScreen.setBounds(5, 5, 100, 20);

		// "���״̬"
		jspScreen.add(jcbAuditStatus);
		jcbAuditStatus.setBounds(130, 5, 100, 20);

		// "��ѡ�����״̬��"
		jspScreen.add(jlAuditStatus);
		jlAuditStatus.setBounds(130, 30, 100, 20);

		// "�ύδ���"
		jspScreen.add(jcbUnaudited);
		jcbUnaudited.setBounds(130, 55, 100, 20);
		jspScreen.add(jcbUnapproved);
		jcbUnapproved.setBounds(260, 55, 100, 20);

		jspScreen.add(jcbApproved);
		jcbApproved.setBounds(130, 80, 100, 20);

		jspScreen.add(jcbKill);
		jcbKill.setBounds(260, 80, 100, 20);

		// "ʱ��"
		jspScreen.add(jcbSubmitTime);
		jcbSubmitTime.setBounds(500 + 30, 5, 100, 20);

		// "��ѡ��ʱ�䷶Χ��"
		jspScreen.add(jlTimeRange);
		jlTimeRange.setBounds(500 + 30, 30, 100, 20);

		// "��ʼʱ��:" ��һ��
		jspScreen.add(jlStartTime);
		jlStartTime.setBounds(500 + 30, 55, 60, 20);
		startTime.setBounds(560 + 30, 55, 170, 20);
		jspScreen.add(startTime);

		// "��ֹʱ��:" ��һ��
		jspScreen.add(jlEndTime);
		jlEndTime.setBounds(500 + 30, 80, 60, 20);
		endTime.setBounds(560 + 30, 80, 170, 20);
		jspScreen.add(endTime);

		// "���ű�ŵ�ѡ��ť"
		jspScreen.add(jcbNewsNo);
		jcbNewsNo.setBounds(830, 5, 100, 20);

		// "���������ű�ţ�"
		jspScreen.add(jlNewsNo);
		jlNewsNo.setBounds(830, 30, 100, 20);

		// "�������ű��"
		jspScreen.add(jlNewsId);
		jlNewsId.setBounds(830, 55, 100, 20);
		jspScreen.add(jtfNewsId);
		jtfNewsId.setBounds(890, 55, 130, 20);

		// Ĭ����û��ѡ�е�״̬�����п�ѡ�����
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

		// "����"��ť
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
				// ���״̬
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
						JOptionPane.showMessageDialog(AuditManagePanel.this, "���������ű�ţ�", "��ʾ",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (!xwbh.matches("\\d+")) {
						JOptionPane.showMessageDialog(AuditManagePanel.this, "��ȷ�����������������", "��ʾ",
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
				// �����߳�
				LoginWindow.watchThread();
			}
		});
	}

	// ��ʼ����˼�¼��
	public void initTable() {
		// ���ñ�ͷ������
		jtAuditRecord.getTableHeader().setUI(new GroupableTableHeaderUI());
		// �����и�
		jtAuditRecord.setRowHeight(30);
		// ����ֻ�ܵ�ѡ
		jtAuditRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// ���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// ���õ�һ�о���
		jtAuditRecord.setDefaultRenderer(Integer.class, dtcr);

		// Ϊ�޸İ�ť��ɾ����ť��ӻ�����
		AuditRecordButtonRenderer jButtonRenderer = new AuditRecordButtonRenderer();
		jtAuditRecord.setDefaultRenderer(JButton.class, jButtonRenderer);

		// jtGRXW.setDefaultEditor(JButton.class, ygButtonEidtor);

		// ��ñ�ͷ
		JTableHeader tableHeader = jtAuditRecord.getTableHeader();
		// ��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// ��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// ����в����ƶ�
		tableHeader.setReorderingAllowed(false);

		// ���ÿһ�е�����
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

		// ����ÿһ�п��
		tc0.setPreferredWidth(60);
		tc1.setPreferredWidth(60);
		tc2.setPreferredWidth(175);
		tc3.setPreferredWidth(100);
		tc4.setPreferredWidth(140);
		tc4.setCellRenderer(new JDateRenderer());

		tc5.setPreferredWidth(100);
		// ���table��Ԫ�������
		tc6.setPreferredWidth(140);
		tc6.setCellRenderer(new JDateRenderer());
		tc7.setPreferredWidth(120);

		tc8.setPreferredWidth(120);
		CheckButtonEditor ckButtonEidtor = new CheckButtonEditor(this, mf);
		tc8.setCellEditor(ckButtonEidtor);

		tc9.setPreferredWidth(120);
		AuditButtonEditor shButtonEidtor = new AuditButtonEditor(this, mf);
		tc9.setCellEditor(shButtonEidtor);

		// ����ÿһ�д�С���ɱ�
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
	 * ���ݸ��·���
	 */

	// ������˼�¼�����ݵķ���
	public void flushData() {
		// ������Ϣ�������
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
					// ���±��ģ��
					tmAuditRecord = new AuditRecordTableModel(AuditManagePanel.this);
					// ��ʼ���������
					tableData = new Object[rowCount][colCount + 2];
					// ͨ��ѭ�������ݿ�������
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							if (list.get(i)[j].equals("null")) {
								tableData[i][j] = "��������";
							} else {
								tableData[i][j] = list.get(i)[j];
							}
						}
						tableData[i][colCount] = new JButton();
						tableData[i][colCount + 1] = new JButton();
					}
					// ���ñ��ģ��
					jtAuditRecord.setModel(tmAuditRecord);
					// ���±�����
					initTable();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɸѡ֮�������˼�¼�����ݵķ���
	public void flushDataFilter(String sb) {
		// ������Ϣ�������
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
					// ���±��ģ��
					tmAuditRecord = new AuditRecordTableModel(AuditManagePanel.this);
					// ��ʼ���������
					tableData = new Object[rowCount][colCount + 2];
					// ͨ��ѭ�������ݿ�������
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							if (list.get(i)[j].equals("null")) {
								tableData[i][j] = "��������";
							} else {
								tableData[i][j] = list.get(i)[j];
							}

						}
						tableData[i][colCount] = new JButton();
						tableData[i][colCount + 1] = new JButton();
					}
					// ���ñ��ģ��
					jtAuditRecord.setModel(tmAuditRecord);
					// ���±�����
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
		// ���ƽ��� ��ʼ���� ��ʼ��ɫ
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
