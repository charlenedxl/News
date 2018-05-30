package com.bn.jm.PersonalInformation;

import static com.bn.core.Constant.UPDATE_YG;
import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.dataGeted;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.jltitle;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bn.jm.LoginWindow;
import com.bn.util.SocketUtil;

/**
 * ������Ϣ����
 */
@SuppressWarnings("serial")
public class PersonalInformationPanel extends JPanel {
	String employeeId = null;// Ա��id
	String originalPassword = null;// ԭʼ����
	String newPassword = null;// ������
	String confirmPassword = null;// ȷ������
	String employeeName = null;// Ա������
	String contact = null;// ��ϵ��ʽ
	String sex = null;// �Ա�

	private JLabel jlTitle = new JLabel("������Ϣ�޸�");
	private JLabel[] jlArry = { new JLabel("Ա�� I D:"), new JLabel("ԭʼ����:"), new JLabel("�� �� ��:"), new JLabel("ȷ������:"),
			new JLabel("��ʵ����:"), new JLabel("Ա���Ա�:"), new JLabel("��ϵ��ʽ:") };

	private JTextField jtfEmployeeId = new JTextField(1);
	private JPasswordField jtfOriginalPassword = new JPasswordField(1);
	private JPasswordField jtfNewPassword = new JPasswordField(1);
	private JPasswordField jtfConfirmPassword = new JPasswordField(1);
	private JTextField jtfEmployeeName = new JTextField(1);
	String[] str = { "��", "Ů" };
	private JComboBox jcbSex = new JComboBox(str);
	private JTextField jtfContact = new JTextField(1);

	private JButton jbEdit = new JButton("�޸�");

	public PersonalInformationPanel(String employeeId) {
		this.employeeId = employeeId;
		this.setLayout(null);
		jlTitle.setBounds(25, 15, 100, 20);
		this.add(jlTitle);
		jlTitle.setFont(subtitle);
		for (int i = 0; i < jlArry.length; i++) {
			jlArry[i].setBounds(25, 50 + i * 30, 70, 20);
			jlArry[i].setFont(jltitle);
			this.add(jlArry[i]);
		}
		jtfEmployeeId.setBounds(95, 50 + 0 * 30, 150, 20);
		jtfOriginalPassword.setBounds(95, 50 + 1 * 30, 150, 20);
		jtfNewPassword.setBounds(95, 50 + 2 * 30, 150, 20);
		jtfConfirmPassword.setBounds(95, 50 + 3 * 30, 150, 20);
		jtfEmployeeName.setBounds(95, 50 + 4 * 30, 150, 20);
		jcbSex.setBounds(95, 50 + 5 * 30, 150, 20);
		jtfContact.setBounds(95, 50 + 6 * 30, 150, 20);
		jbEdit.setBounds(25, 265, 80, 25);
		jbEdit.setOpaque(false);
		jtfEmployeeId.setText(employeeId);
		jtfEmployeeId.setEditable(false);
		this.add(jtfEmployeeId);
		this.add(jtfOriginalPassword);
		this.add(jtfNewPassword);
		this.add(jtfConfirmPassword);
		this.add(jtfEmployeeName);
		this.add(jcbSex);
		this.add(jtfContact);
		this.add(jbEdit);
		addFocusListener();
		initButton();
	}

	// ��ӻس�����
	private void addFocusListener() {

		jtfOriginalPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNewPassword.requestFocus();
			}
		});
		jtfNewPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfConfirmPassword.requestFocus();
			}
		});
		jtfConfirmPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfEmployeeName.requestFocus();
			}
		});
		jtfEmployeeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcbSex.requestFocus();
			}
		});
		jtfContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbEdit.requestFocus();
			}
		});
	};

	// Ϊ�޸İ�ť�Ӽ���
	private void initButton() {
		jbEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (new String(jtfOriginalPassword.getPassword()).length() <= 0) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "������ԭʼ���룡", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				originalPassword = new String(jtfOriginalPassword.getPassword()).trim();
				if (new String(jtfNewPassword.getPassword()).length() <= 0) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "�����������룡", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				newPassword = new String(jtfNewPassword.getPassword()).trim();
				if (new String(jtfConfirmPassword.getPassword()).length() <= 0) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "������ȷ�ϵ����룡", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				confirmPassword = new String(jtfConfirmPassword.getPassword()).trim();
				if (!newPassword.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "������������벻��ͬ��", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (jtfEmployeeName.getText().trim().length() <= 0) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "������������", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				employeeName = jtfEmployeeName.getText().trim();
				if (jtfContact.getText().trim().length() <= 0) {
					JOptionPane.showMessageDialog(PersonalInformationPanel.this, "��������ϵ��ʽ��", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				contact = jtfContact.getText().trim();
				sex = jcbSex.getSelectedItem().toString();

				// �����߳�
				dataGeted = false;
				new Thread() {
					public void run() {
						StringBuilder sb = new StringBuilder();
						sb.append(UPDATE_YG);
						sb.append(employeeId + "<->");
						sb.append(originalPassword + "<->");
						sb.append(newPassword + "<->");
						sb.append(employeeName + "<->");
						sb.append(sex + "<->");
						sb.append(contact);
						sb.append(UPDATE_YG);
						String msg = SocketUtil.sendAndGetMsg(sb.toString());
						dataGeted = true;
						if (msg.equals("mmcw")) {
							JOptionPane.showMessageDialog(PersonalInformationPanel.this, "ԭʼ���벻��ȷ�����������룡", "��ʾ",
									JOptionPane.WARNING_MESSAGE);
							jtfOriginalPassword.setText(null);
							jtfOriginalPassword.requestFocus();
							return;

						} else {
							JOptionPane.showMessageDialog(PersonalInformationPanel.this, "��ϲ��������Ϣ�޸ĳɹ���", "��ʾ",
									JOptionPane.NO_OPTION);
							jtfOriginalPassword.setText(null);
							jtfNewPassword.setText(null);
							jtfConfirmPassword.setText(null);
							jtfEmployeeName.setText(null);
							jcbSex.setSelectedIndex(0);
							jtfContact.setText(null);
						}

					}
				}.start();
				// �����߳�
				LoginWindow.watchThread();
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// ���ƽ��� ��ʼ���� ��ʼ��ɫ
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
