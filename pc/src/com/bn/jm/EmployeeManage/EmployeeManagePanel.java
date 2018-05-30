package com.bn.jm.EmployeeManage;

import static com.bn.core.Constant.SCREEN_HEIGHT;
import static com.bn.core.Constant.SCREEN_WIDTH;
import static com.bn.core.Constant.winIcon;
import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.GET_BM;
import static com.bn.core.Constant.GET_YG;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.XG_YG;
import static com.bn.core.Constant.GET_JS;
import static com.bn.core.Constant.dataGeted;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/*
 * Ա����Ϣ����
 */
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeModel;

import com.bn.jm.LoginWindow;
import com.bn.jm.TableHeader.GroupableTableHeader;
import com.bn.jm.TableHeader.GroupableTableHeaderUI;
import com.bn.util.SocketUtil;
import com.bn.util.TreeModelUitl;

/**
 * Ա����Ϣ����
 *
 */
@SuppressWarnings("serial")
public class EmployeeManagePanel extends JPanel {
	// ======================================Ա����Ϣ��======================================
	// ��ɫ���ÿһ�е�����
	Class[] typeArrayEmployee = { Integer.class, String.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, JButton.class };
	// ��ɫ��ͷ
	String[] headEmployee = { "Ա��ID", "��½�˺�", "��½����", "��ʵ����", "�Ա�", "��ϵ��ʽ", "���ڲ���", "��ɫ", "��ְ���", "�޸�" };
	// ��ɫ�������
	Object[][] tableDataEmployee;
	Object[][] origindataEmployee;

	EmployeeTableModel tmEmployee;

	JTable jtEmployee = new JTable() {
		@Override
		protected JTableHeader createDefaultTableHeader() {
			return new GroupableTableHeader(columnModel);
		}
	};

	// ��¼�ϴε�����У����ڴ������������У��޸ķ����������¼���
	int pressedRow = -1;
	// ��¼�ոձ༭�˵��У����ڵõ�����ģ���еı༭�е����ݣ�
	int lastEditRow = -1;
	// ��¼��ʾ����Ա����������ְԱ������ְԱ����int
	public int lzyf = 2;// -1��ʾ���У�0ֻ��ʾ��ְԱ����1ֻ��ʾ��ְԱ��

	// ���ڼ�¼�ӷ�������ȡ�Ľ�ɫ��Ϣ����ɫid,��ɫ���ƣ�
	Map<String, String> RoleMapforRender = new HashMap<String, String>();
	// ���ڼ�¼�ӷ�������ȡ�Ľ�ɫ��Ϣ����ɫ����,��ɫid��
	Map<String, String> RoleMapforEditor = new HashMap<String, String>();

	// ��ű���JScrollPane
	JScrollPane jspEmployee = new JScrollPane(jtEmployee);

	JLabel jlTitle = new JLabel("Ա����Ϣ�б�");
	String[] str = { "ֻ��ʾ��ְԱ��", "ֻ��ʾ��ְԱ��", "��ʾ����Ա��" };
	JComboBox jcbLzyf = new JComboBox(str);

	JButton jbAddEmployee = new JButton("���Ա��");

	// ���ڲ���ѡ������һЩ����
	DefaultTreeModel dtm;// ����ѡ���������б�����ģ��
	List<String[]> listDepartment;// ����������Ϣ��List

	public EmployeeManagePanel() {
		this.setLayout(null);
		// ��ͷ����
		jspEmployee.setBounds(25, 50, 1135, 595);
		jlTitle.setBounds(520, 20, 200, 20);
		jlTitle.setFont(subtitle);

		jcbLzyf.setBounds(25, 20, 120, 20);
		jcbLzyf.setOpaque(false);
		jbAddEmployee.setBounds(25, 660, 80, 30);
		jbAddEmployee.setOpaque(false);

		this.addButtonListener();
		this.addTableListener();
		this.add(jcbLzyf);
		this.add(jbAddEmployee);
		this.add(jlTitle);
		this.add(jspEmployee);
		initJSMap();
	}

	// ��ʼ��Ա����
	public void initTableYG() {

		// ���ñ�ͷ������
		jtEmployee.getTableHeader().setUI(new GroupableTableHeaderUI());
		// �����и�
		jtEmployee.setRowHeight(30);
		// ����ֻ�ܵ�ѡ
		jtEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		// ���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		// ���õ�һ�о���
		jtEmployee.setDefaultRenderer(Integer.class, dtcr);
		// �޸İ�ť������
		EmployeeButtonRenderer jButtonRenderer = new EmployeeButtonRenderer();
		jtEmployee.setDefaultRenderer(JButton.class, jButtonRenderer);
		// �޸İ�ť�༭��
		EmployeeButtonEditor ygButtonEidtor = new EmployeeButtonEditor(this);
		jtEmployee.setDefaultEditor(JButton.class, ygButtonEidtor);

		// ��ñ�ͷ
		JTableHeader tableHeader = jtEmployee.getTableHeader();
		// ��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
		// ��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// ����в����ƶ�
		tableHeader.setReorderingAllowed(false);

		// ���ÿһ�е�����
		TableColumn tc0 = jtEmployee.getColumnModel().getColumn(0);
		TableColumn tc1 = jtEmployee.getColumnModel().getColumn(1);
		TableColumn tc2 = jtEmployee.getColumnModel().getColumn(2);
		TableColumn tc3 = jtEmployee.getColumnModel().getColumn(3);
		TableColumn tc4 = jtEmployee.getColumnModel().getColumn(4);
		TableColumn tc5 = jtEmployee.getColumnModel().getColumn(5);
		TableColumn tc6 = jtEmployee.getColumnModel().getColumn(6);
		TableColumn tc7 = jtEmployee.getColumnModel().getColumn(7);
		TableColumn tc8 = jtEmployee.getColumnModel().getColumn(8);
		TableColumn tc9 = jtEmployee.getColumnModel().getColumn(9);

		// ����ÿһ�п��
		tc0.setPreferredWidth(60);
		tc1.setPreferredWidth(100);
		tc2.setPreferredWidth(100);
		tc3.setPreferredWidth(100);
		tc4.setPreferredWidth(100);
		tc5.setPreferredWidth(220);
		tc6.setPreferredWidth(120);
		tc7.setPreferredWidth(120);
		tc8.setPreferredWidth(100);
		tc9.setPreferredWidth(115);

		// Ϊ��������ӱ༭���ͻ�����
		tc6.setCellEditor(new EmployeeDepartmentTreeComboBoxEidtor(this));
		tc6.setCellRenderer(new EmployeeDepartmentRenderer(EmployeeManagePanel.this));

		// Ϊ��ְ�������ӱ༭���ͻ�����
		tc8.setCellEditor(new EmployeeTurnoverComboBoxEditor(this));
		tc8.setCellRenderer(new EmployeeTurnoverRenderer());

		// Ϊ��ɫ����ӱ༭���ͻ�����
		tc7.setCellEditor(new EmployeeRoleComboBoxEidtor(this));
		tc7.setCellRenderer(new EmployeeRoleRenderer(this));

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

	// ��ʼ��������ɫ�����б����ݵ�map�ķ���
	public void initJSMap() {
		// ������Ϣ�������
		String msg = GET_JS;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());// "<#GET_JS#>""<#GET_JS#>"
		List<String[]> listJS = SocketUtil.strToList(result);
		RoleMapforRender.clear();
		for (String[] str : listJS) {
			RoleMapforRender.put(str[0], str[1]);
			RoleMapforEditor.put(str[1], str[0]);
		}
	}

	// Ϊ��ť�������б���Ӽ���
	public void addButtonListener() {
		jcbLzyf.setSelectedIndex(lzyf);
		jcbLzyf.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					lzyf = jcbLzyf.getSelectedIndex();
					// jcbLzyf.setSelectedIndex(lzyf);
					new Thread() {
						public void run() {
							flushData(lzyf);
						};

					}.start();
				}

			}

		});

		jbAddEmployee.addActionListener(new ActionListener() {
			final JFrame newjf = new JFrame("�����Ա��");
			final NewEmployeePanel newygpanel = new NewEmployeePanel();

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginWindow.mf.setEnabled(false);// MainJFrame�Ժ�Ҫ�޸�
				newjf.setAlwaysOnTop(true);
				newjf.setLayout(null);
				newjf.setLocation(550, 110);
				newjf.setResizable(false);
				newjf.setIconImage(winIcon);
				newjf.add(newygpanel);
				newygpanel.setBounds(10, 10, 350, 300);
				newjf.setSize(250, 310);
				newjf.setLocation((int) (SCREEN_WIDTH - 230) / 2, (int) (SCREEN_HEIGHT - 400) / 2);
				newjf.setVisible(true);
				newjf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {

						LoginWindow.mf.setEnabled(true);// MainJFrame�Ժ�Ҫ�޸�
						newjf.dispose();
						newjf.setAlwaysOnTop(false);
					}
				});

			}

		});

	}

	// Ϊ���������ͼ��̼���
	public void addTableListener() {
		jtEmployee.addMouseListener(new MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				int row = jtEmployee.getSelectedRow();
				if (pressedRow != row) {
					pressedRow = row;
					if (dataChanged()) {
						int i = JOptionPane.showConfirmDialog(EmployeeManagePanel.this, "�Ƿ񱣴��Ա����Ϣ����޸ģ�", "��ʾ",
								JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (i == 0) {
							xgYG(tableDataEmployee[lastEditRow][0].toString(),
									tableDataEmployee[lastEditRow][6].toString(),
									tableDataEmployee[lastEditRow][7].toString(),
									tableDataEmployee[lastEditRow][8].toString());

						} else {
							tableDataEmployee[lastEditRow][6] = origindataEmployee[lastEditRow][6];
							tableDataEmployee[lastEditRow][7] = origindataEmployee[lastEditRow][7];
							tableDataEmployee[lastEditRow][8] = origindataEmployee[lastEditRow][8];
						}
					}
				}

			};

		});

		jtEmployee.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int row = jtEmployee.getSelectedRow();
				if (pressedRow != row) {
					pressedRow = row;
					if (dataChanged()) {
						int i = JOptionPane.showConfirmDialog(EmployeeManagePanel.this, "�Ƿ񱣴��Ա����Ϣ����޸ģ�", "��ʾ",
								JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (i == 0) {
							xgYG(tableDataEmployee[lastEditRow][0].toString(),
									tableDataEmployee[lastEditRow][6].toString(),
									tableDataEmployee[lastEditRow][7].toString(),
									tableDataEmployee[lastEditRow][8].toString());

						} else {
							tableDataEmployee[lastEditRow][6] = origindataEmployee[lastEditRow][6];
							tableDataEmployee[lastEditRow][7] = origindataEmployee[lastEditRow][7];
							tableDataEmployee[lastEditRow][8] = origindataEmployee[lastEditRow][8];
						}
					}
				}

			}
		});
	}

	/*
	 * ���ݸ��·���
	 */

	// ����Ա�������ݵķ���
	public void flushData(int lzid) {
		// ������Ϣ�������
		String msg = GET_YG;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(lzid);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());// "<#GET_YG#>""<#GET_YG#>"
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
					tmEmployee = new EmployeeTableModel(EmployeeManagePanel.this);
					// ��ʼ���������
					tableDataEmployee = new Object[rowCount][colCount + 1];
					// �����е����ݽ���һ����ʱ�ı���
					// ������ ������"�޸�"��ťʱ �жϵ�ǰ��������� �� ������ݵ����� �Ƿ���ͬ ��ͬ�Ļ� ��ʾ�����Ϣ
					origindataEmployee = new Object[rowCount][colCount + 1];
					// ͨ��ѭ�������ݿ�������
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							tableDataEmployee[i][j] = list.get(i)[j];
							origindataEmployee[i][j] = list.get(i)[j];
							// System.out.println(tableDataYG[i][j]);
						}
						tableDataEmployee[i][colCount] = new JButton();
						origindataEmployee[i][colCount] = new JButton();
					}
					// ���ñ��ģ��
					jtEmployee.setModel(tmEmployee);
					// ���±�����
					initTableYG();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����Ա�������ݵķ���
	public void flushDataBM() {
		// ������Ϣ�������
		String msg = GET_BM;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		listDepartment = SocketUtil.strToList(result);
		dtm = TreeModelUitl.getTreeModel(listDepartment);

	};

	/*
	 * �����������ݷ���
	 */

	// �ж�һ�е������Ƿ��޸���
	public boolean dataChangedForButton() {
		if (lastEditRow == -1) {
			JOptionPane.showMessageDialog(this, "��Ա�����޸�֮��,�����޸İ�ť��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (tableDataEmployee[lastEditRow][6].equals(origindataEmployee[lastEditRow][6])
				&& tableDataEmployee[lastEditRow][7].equals(origindataEmployee[lastEditRow][7])
				&& tableDataEmployee[lastEditRow][8].equals(origindataEmployee[lastEditRow][8])) {
			JOptionPane.showMessageDialog(this, "����û���޸�,���޸��˱������֮��,�����޸İ�ť����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	// �ж�һ�е������Ƿ��޸���
	public boolean dataChanged() {
		if (lastEditRow == -1) {
			return false;
		}
		if (tableDataEmployee[lastEditRow][6].equals(origindataEmployee[lastEditRow][6])
				&& tableDataEmployee[lastEditRow][7].equals(origindataEmployee[lastEditRow][7])
				&& tableDataEmployee[lastEditRow][8].equals(origindataEmployee[lastEditRow][8])) {
			return false;
		}
		return true;
	}

	// �޸�Ա����Ϣ��Ա��id�����ţ���ɫ����ְ��
	public void xgYG(final String ygid, final String bmid, final String jsid, final String lzid) {
		dataGeted = false;
		new Thread() {
			@Override
			public void run() {
				String msg = XG_YG;
				StringBuffer sb = new StringBuffer();
				sb.append(msg);
				sb.append(ygid);
				sb.append("<->");
				sb.append(bmid);
				sb.append("<->");
				sb.append(jsid);
				sb.append("<->");
				sb.append(lzid);
				sb.append(msg);
				String result = SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted = true;
				if (result.endsWith("ok")) {
					JOptionPane.showMessageDialog(EmployeeManagePanel.this, "Ա����Ϣ�޸ĳɹ���", "��ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					dataGeted = false;
					flushData(lzyf);
					LoginWindow.watchThread();
					dataGeted = true;
				} else {
					JOptionPane.showMessageDialog(EmployeeManagePanel.this, "Ա����Ϣ�޸�ʧ�ܣ�", "��ʾ",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// ���ƽ��� ��ʼ���� ��ʼ��ɫ
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
