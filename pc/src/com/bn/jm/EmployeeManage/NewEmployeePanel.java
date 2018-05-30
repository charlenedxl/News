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
 * 新员工
 *
 */
@SuppressWarnings("serial")
public class NewEmployeePanel extends JPanel {

	private JLabel[] jlEmployeeInformation = // 员工信息JLabel
			{ new JLabel("登陆账号"), new JLabel("登陆密码"), new JLabel("真实姓名"), new JLabel("联系方式"), new JLabel("员工性别"),
					new JLabel("所在部门"), new JLabel("员工角色") };

	String account = "ZH";// 账号
	String password = "MM";// 密码
	String name = "XM";// 姓名
	String contact = "LXFS";// 联系方式

	JTextField jtEmployeeInformation[] = // 员工信息JTextField
			{ new JTextField(1), new JTextField(1), new JTextField(1), new JTextField(1) };

	private String strSex[] = { "男", "女" };
	private JComboBox sex = new JComboBox(strSex);// 员工性别
	private String Sex = "XB";

	private JTree jtChooseDepartment = new JTree();// 部门选择树
	private JTreeComboBox jtcom = new JTreeComboBox(jtChooseDepartment);// 部门选择树
	private String DepartmentId = "BMID";

	private JComboBox jcomEmployeeRole = new JComboBox();// 角色
	private String Role = "JS";

	private JButton submit = new JButton("添加");

	public NewEmployeePanel() {
		this.setLayout(null);
		// 添加JLabel
		for (int i = 0; i < jlEmployeeInformation.length; i++) {
			this.add(jlEmployeeInformation[i]);
			jlEmployeeInformation[i].setBounds(25, 40 + 30 * i - 25, 60, 20);

		}
		// 添加JTextFiled
		for (int i = 0; i < jtEmployeeInformation.length; i++) {
			this.add(jtEmployeeInformation[i]);
			jtEmployeeInformation[i].setBounds(78, 40 + 30 * i - 25, 120, 20);
			jtEmployeeInformation[i].setText(null);
		}

		// 添加回车监听
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

		// 添加按钮
		this.add(submit);
		submit.setBounds(120, 40 + 30 * 7 - 17, 80, 25);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 输入验证
				for (int i = 0; i < jtEmployeeInformation.length; i++) {
					if (jtEmployeeInformation[i].getText().trim().length() <= 0) {
						JOptionPane.showMessageDialog(NewEmployeePanel.this,
								"请输入" + jlEmployeeInformation[i].getText() + "！", "提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (i == 0) {
						String text = jtEmployeeInformation[i].getText().trim();
						if (!text.matches("\\w+")) {
							JOptionPane.showMessageDialog(NewEmployeePanel.this, "登陆账号只可以包含数字,字母,下划线！", "提示",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
				if (jtcom.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(NewEmployeePanel.this, "选择员工所在部门！", "提示",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				account = jtEmployeeInformation[0].getText().trim();
				password = jtEmployeeInformation[1].getText().trim();
				name = jtEmployeeInformation[2].getText().trim();
				contact = jtEmployeeInformation[3].getText().trim();

				// 验证通过，给没有选择的下拉列表赋初值
				if (Sex.equals("XB")) {
					Sex = "男";
				}
				if (Role.equals("JS")) {
					Role = "0";
				}

				// System.out.println("账号："+ZH+"密码："+MM+"联系方式："+LXFS+"性别："+XB+"部门id："+BMID+"角色id"+JS);
				// 任务线程
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
						sb.append(DepartmentId + "<->");// 部门id、
						sb.append(Role);// 角色
						sb.append(ADD_YG);
						String msg = SocketUtil.sendAndGetMsg(sb.toString());
						dataGeted = true;
						JOptionPane.showMessageDialog(NewEmployeePanel.this, "恭喜，员工添加成功！", "提示", JOptionPane.NO_OPTION);
						for (int i = 0; i < jtEmployeeInformation.length; i++) {
							jtEmployeeInformation[i].setText(null);
							sex.setSelectedIndex(0);
							jtcom.setSelectedItem(null);
							jcomEmployeeRole.setSelectedIndex(0);
						}
					}
				}.start();
				// 监视线程
				LoginWindow.watchThread();

			}
		});

		initXB();
		initJS();
		initBM();

	}

	// 初始化性别下拉列表
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

	// 初始化角色下拉列表
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

	// 初始化部门下拉列表
	private void initBM() {
		jtcom.setBounds(78, 40 + 30 * 5 - 25, 120, 20);
		jtcom.setSelectedItem(null);
		this.add(jtcom);
		// 发送消息获得数据
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
