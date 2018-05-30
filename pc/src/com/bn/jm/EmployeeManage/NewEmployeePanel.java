package com.bn.jm.EmployeeManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.bn.jm.LoginWindow;
import com.bn.util.BKJTreeCellRenderer;
import com.bn.util.BKJTreeNode;
import com.bn.util.JCOMDataObj;
import com.bn.util.SocketUtil;
import com.bn.util.TreeModelUitl;
import com.sunking.swing.JTreeComboBox;

import static com.bn.core.Constant.ADD_YG;

import static com.bn.core.Constant.GET_BM;
import static com.bn.core.Constant.GET_JS;
import static com.bn.core.Constant.dataGeted;

/**
 * ��Ա��
 *
 */
@SuppressWarnings("serial")
public class NewEmployeePanel extends JPanel {

	private JLabel[] jlEmployeeInformation = // Ա����ϢJLabel
			{ new JLabel("��½�˺�"), new JLabel("��½����"), new JLabel("��ʵ����"), new JLabel("��ϵ��ʽ"), new JLabel("Ա���Ա�"),
					new JLabel("���ڲ���"), new JLabel("Ա����ɫ") };

	String account = "ZH";// �˺�
	String password = "MM";// ����
	String name = "XM";// ����
	String contact = "LXFS";// ��ϵ��ʽ

	JTextField jtEmployeeInformation[] = // Ա����ϢJTextField
			{ new JTextField(1), new JTextField(1), new JTextField(1), new JTextField(1) };

	private String strSex[] = { "��", "Ů" };
	private JComboBox sex = new JComboBox(strSex);// Ա���Ա�
	private String Sex = "XB";

	private JTree jtChooseDepartment = new JTree();// ����ѡ����
	private JTreeComboBox jtcom = new JTreeComboBox(jtChooseDepartment);// ����ѡ����
	private String DepartmentId = "BMID";

	private JComboBox jcomEmployeeRole = new JComboBox();// ��ɫ
	private String Role = "JS";

	private JButton submit = new JButton("���");

	public NewEmployeePanel() {
		this.setLayout(null);
		// ���JLabel
		for (int i = 0; i < jlEmployeeInformation.length; i++) {
			this.add(jlEmployeeInformation[i]);
			jlEmployeeInformation[i].setBounds(25, 40 + 30 * i - 25, 60, 20);

		}
		// ���JTextFiled
		for (int i = 0; i < jtEmployeeInformation.length; i++) {
			this.add(jtEmployeeInformation[i]);
			jtEmployeeInformation[i].setBounds(78, 40 + 30 * i - 25, 120, 20);
			jtEmployeeInformation[i].setText(null);
		}

		// ��ӻس�����
		jtEmployeeInformation[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtEmployeeInformation[1].requestFocus();
			}
		});
		jtEmployeeInformation[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtEmployeeInformation[2].requestFocus();
			}
		});
		jtEmployeeInformation[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtEmployeeInformation[3].requestFocus();
			}
		});
		jtEmployeeInformation[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sex.requestFocus();
			}
		});

		// ��Ӱ�ť
		this.add(submit);
		submit.setBounds(120, 40 + 30 * 7 - 17, 80, 25);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ������֤
				for (int i = 0; i < jtEmployeeInformation.length; i++) {
					if (jtEmployeeInformation[i].getText().trim().length() <= 0) {
						JOptionPane.showMessageDialog(NewEmployeePanel.this,
								"������" + jlEmployeeInformation[i].getText() + "��", "��ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (i == 0) {
						String text = jtEmployeeInformation[i].getText().trim();
						if (!text.matches("\\w+")) {
							JOptionPane.showMessageDialog(NewEmployeePanel.this, "��½�˺�ֻ���԰�������,��ĸ,�»��ߣ�", "��ʾ",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
				if (jtcom.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(NewEmployeePanel.this, "ѡ��Ա�����ڲ��ţ�", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				account = jtEmployeeInformation[0].getText().trim();
				password = jtEmployeeInformation[1].getText().trim();
				name = jtEmployeeInformation[2].getText().trim();
				contact = jtEmployeeInformation[3].getText().trim();

				// ��֤ͨ������û��ѡ��������б���ֵ
				if (Sex.equals("XB")) {
					Sex = "��";
				}
				if (Role.equals("JS")) {
					Role = "0";
				}

				// System.out.println("�˺ţ�"+ZH+"���룺"+MM+"��ϵ��ʽ��"+LXFS+"�Ա�"+XB+"����id��"+BMID+"��ɫid"+JS);
				// �����߳�
				dataGeted = false;
				new Thread() {
					public void run() {
						StringBuilder sb = new StringBuilder();
						sb.append(ADD_YG);
						sb.append(account + "<->");
						sb.append(password + "<->");
						sb.append(name + "<->");
						sb.append(contact + "<->");
						sb.append(Sex + "<->");
						sb.append(DepartmentId + "<->");// ����id��
						sb.append(Role);// ��ɫ
						sb.append(ADD_YG);
						String msg = SocketUtil.sendAndGetMsg(sb.toString());
						dataGeted = true;
						JOptionPane.showMessageDialog(NewEmployeePanel.this, "��ϲ��Ա����ӳɹ���", "��ʾ", JOptionPane.NO_OPTION);
						for (int i = 0; i < jtEmployeeInformation.length; i++) {
							jtEmployeeInformation[i].setText(null);
							sex.setSelectedIndex(0);
							jtcom.setSelectedItem(null);
							jcomEmployeeRole.setSelectedIndex(0);
						}
					}
				}.start();
				// �����߳�
				LoginWindow.watchThread();

			}
		});

		initXB();
		initJS();
		initBM();

	}

	// ��ʼ���Ա������б�
	private void initXB() {
		sex.setBounds(78, 40 + 30 * 4 - 25, 120, 20);
		this.add(sex);
		sex.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					Sex = sex.getSelectedItem().toString();
				}
			}
		});
	}

	// ��ʼ����ɫ�����б�
	private void initJS() {
		jcomEmployeeRole.setBounds(78, 40 + 30 * 6 - 25, 120, 20);
		this.add(jcomEmployeeRole);
		String msg1 = SocketUtil.sendAndGetMsg(GET_JS + GET_JS);
		jcomEmployeeRole.removeAllItems();
		List<String[]> list = SocketUtil.strToList(msg1);
		for (int i = 0; i < list.size(); i++) {
			jcomEmployeeRole.addItem(new JCOMDataObj(list.get(i)[0], list.get(i)[1]));
		}

		jcomEmployeeRole.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					JCOMDataObj sel;
					sel = (JCOMDataObj) e.getItem();
					Role = sel.getId();
				}
			}
		});
	}

	// ��ʼ�����������б�
	private void initBM() {
		jtcom.setBounds(78, 40 + 30 * 5 - 25, 120, 20);
		jtcom.setSelectedItem(null);
		this.add(jtcom);
		// ������Ϣ�������
		String msg = GET_BM;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		DefaultTreeModel dtm = TreeModelUitl.getTreeModel(SocketUtil.strToList(result));
		jtChooseDepartment.setModel(dtm);
		jtChooseDepartment.setCellRenderer(new BKJTreeCellRenderer());
		jtcom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					TreePath tp = (TreePath) e.getItem();
					BKJTreeNode node = (BKJTreeNode) tp.getLastPathComponent();
					DepartmentId = node.getId() + "";
				}

			}
		});

	}

}
