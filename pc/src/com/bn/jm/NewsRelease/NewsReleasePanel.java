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
 * �������Ų鿴
 */
@SuppressWarnings("serial")
public class NewsReleasePanel extends JPanel {
	private JLabel jlNewsRelease = new JLabel("�������Ų鿴");
	private JLabel jlPrograma = new JLabel("��Ŀ�б�");
	private JLabel jlNewsProgram = new JLabel("��Ŀ��������");

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
	//��Ŀ����
	NewsProgramTableModel newsProgram;

	// ��¼��Ŀ�����ϴε������
	int pressedRowInPrograma = -1;
	// ��¼��Ŀ���ű����ϴε������
	int pressedRowInNewsProgram = -1;

	int lmid;
	int xwid;

	MainJFrame mf;

	public NewsReleasePanel(MainJFrame mf) {
		this.mf = mf;
		this.setLayout(null);
		// ��ͷ����
		jlNewsRelease.setBounds(520, 20, 200, 20);
		jlNewsRelease.setFont(subtitle);
		this.add(jlNewsRelease);

		// ��Ŀ��
		this.add(jlPrograma);
		jlPrograma.setBounds(25, 15 + 30 + 5, 120, 20);
		jlPrograma.setFont(jltitle);
		this.add(jsPrograma);
		jsPrograma.setBounds(25, 70 + 10, 280, 580);

		// ��Ŀ�������ű�
		this.add(jlNewsProgram);
		jlNewsProgram.setBounds(365 - 10, 15 + 30 + 5, 120, 20);
		jlNewsProgram.setFont(jltitle);
		this.add(jsNewsProgram);
		jsNewsProgram.setBounds(365 - 10, 70 + 10, 780 + 20, 580);
		// 280 780

		addTableListeners();

	}

	// ��ʼ�����
	public void initNewsRelease(JTable jtNewsRelease) {
		// ���ñ�ͷ������
		jtNewsRelease.getTableHeader().setUI(new GroupableTableHeaderUI());
		// �����и�
		jtNewsRelease.setRowHeight(30);
		// ����ֻ�ܵ�ѡ
		jtNewsRelease.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// ���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// ���õ�һ�о���
		jtNewsRelease.setDefaultRenderer(Integer.class, dtcr);

		// ��ñ�ͷ
		JTableHeader tableHeader = jtNewsRelease.getTableHeader();
		// ��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// ��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// ����в����ƶ�
		tableHeader.setReorderingAllowed(false);

		// ���ÿһ�е�����
		TableColumn tc0 = jtNewsRelease.getColumnModel().getColumn(0);
		TableColumn tc1 = jtNewsRelease.getColumnModel().getColumn(1);
		TableColumn tc2 = jtNewsRelease.getColumnModel().getColumn(2);

		// ����ÿһ�п��
		tc0.setPreferredWidth(50);
		tc1.setPreferredWidth(50);
		tc2.setPreferredWidth(160);

		// ����ÿһ�д�С���ɱ�
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
	}

	// ��ʼ�����
	public void initTableNewsProgram(JTable jtNewsProgram) {
		// ���ñ�ͷ������
		jtNewsProgram.getTableHeader().setUI(new GroupableTableHeaderUI());
		// �����и�
		jtNewsProgram.setRowHeight(30);
		// ����ֻ�ܵ�ѡ
		jtNewsProgram.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// ���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// ���õ�һ�о���
		jtNewsProgram.setDefaultRenderer(Integer.class, dtcr);

		// ��ñ�ͷ
		JTableHeader tableHeader = jtNewsProgram.getTableHeader();
		// ��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// ��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// ����в����ƶ�
		tableHeader.setReorderingAllowed(false);

		// ���ÿһ�е�����
		TableColumn tc0 = jtNewsProgram.getColumnModel().getColumn(0);
		TableColumn tc1 = jtNewsProgram.getColumnModel().getColumn(1);
		TableColumn tc2 = jtNewsProgram.getColumnModel().getColumn(2);
		TableColumn tc3 = jtNewsProgram.getColumnModel().getColumn(3);
		TableColumn tc4 = jtNewsProgram.getColumnModel().getColumn(4);
		TableColumn tc5 = jtNewsProgram.getColumnModel().getColumn(5);

		// ����ÿһ�п��
		tc0.setPreferredWidth(50);
		tc1.setPreferredWidth(50);
		tc2.setPreferredWidth(160);
		tc3.setPreferredWidth(290 + 20);
		tc4.setPreferredWidth(130);
		tc4.setCellRenderer(new JDateRenderer());
		tc5.setPreferredWidth(80);

		// ����ÿһ�д�С���ɱ�
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
		tc3.setResizable(false);
		tc4.setResizable(false);
		tc5.setResizable(false);
	}

	public void addTableListeners() {// ��ӱ��ļ�����
		jtPrograma.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				final int row = jtPrograma.getSelectedRow();
				if (pressedRowInPrograma != row) {// �������Ĳ���ͬһ��
					pressedRowInPrograma = row;
					lmid = Integer.parseInt(tmPrograma.tableData[pressedRowInPrograma][1].toString());
					dataGeted = false;
					new Thread() {
						public void run() {
							flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInPrograma][1].toString()));
							dataGeted = true;
						}
					}.start();
					// �����߳�
					LoginWindow.watchThread();
				}
			}

		});

		// ����Ŀ������ӱ�������
		jtNewsProgram.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				final int row = jtNewsProgram.getSelectedRow();
				if (pressedRowInNewsProgram != row) {// �������Ĳ���ͬһ��
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
	 * ���ݸ��·���
	 */

	// ������Ŀ�����ݵķ���
	public void flushDataLM() {
		// ������Ϣ�������
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
					// ���±��ģ��
					tmPrograma = new ProgramaTableModel(NewsReleasePanel.this);
					// ��ʼ���������
					tmPrograma.tableData = new Object[rowCount][colCount];
					// tmLM.OritableData = new Object[rowCount][colCount];
					// ͨ��ѭ�������ݿ�������
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							tmPrograma.tableData[i][j] = list.get(i)[j];
							// tmLM.OritableData[i][j]=list.get(i)[j];
						}
					}
					// ���ñ��ģ��
					jtPrograma.setModel(tmPrograma);
					// ���±�����
					initNewsRelease(jtPrograma);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ������Ŀ�������ű����ݵķ���
	public void flushDataLMXW(int lmid) {
		// ������Ϣ�������
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
					// ���±��ģ��
					newsProgram = new NewsProgramTableModel(NewsReleasePanel.this);
					// ��ʼ���������
					newsProgram.tableData = new Object[rowCount][colCount];
					// ͨ��ѭ�������ݿ�������
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							newsProgram.tableData[i][j] = list.get(i)[j];

						}
					}
					// ���ñ��ģ��
					jtNewsProgram.setModel(newsProgram);
					// ���±�����
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
		// ���ƽ��� ��ʼ���� ��ʼ��ɫ
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
