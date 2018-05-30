package com.bn.jm.AddNews;

import static com.bn.core.Constant.SCREEN_HEIGHT;
import static com.bn.core.Constant.SCREEN_WIDTH;
import static com.bn.core.Constant.bpicPath;
import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.dataGeted;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.jltitle;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bn.jm.LoginWindow;
import com.bn.sjb.NewPC;
import com.bn.sjb.NewPC1;
import com.bn.sjb.NewPC2;
import com.bn.sjb.NewPC3;
import com.bn.util.DateChooserJButton;
import com.bn.util.PicUtils;
import com.bn.util.SocketUtil;

/**
 * ��������
 *
 */
@SuppressWarnings("serial")
public class AddNewsPanel extends JPanel {
	private JTextField jtfLine = new JTextField();
	private JLabel jlAddNews = new JLabel("��������");
	private JLabel jlNewsTitle = new JLabel("���ű���:");
	private JLabel jlTitlePic = new JLabel("����ͼƬ:");
	private JLabel jlNewsSources = new JLabel("������Դ:");
	private JLabel jlReleaseTime = new JLabel("����ʱ��:");
	private JLabel jlNewsContent = new JLabel("��������:");
	private JLabel jlNewsSummarize = new JLabel("���Ÿ���:");

	public int BSid = 1;
	private String xwbt;
	private String xwgs;
	private String xwly;
	private String fbsj;
	private String xwnr;
	private String ygid;
	private String picTitelPath;
	private String pic1Path = bpicPath + "pic.jpg";
	private String pic1MS = "ͼƬ����";
	private String pic2Path = bpicPath + "pic.jpg";
	private String pic2MS = "ͼƬ����";

	Style1Panel sytle1 = null;
	Style2Panel sytle2 = null;
	Style3Panel sytle3 = null;

	private JTextField jtfNewsTitle = new JTextField(1);// ���ű���
	private JTextField jtfNewsSources = new JTextField(1);// ������Դ
	private DateChooserJButton ReleaseTime = new DateChooserJButton();// ����ʱ��
	private JTextArea jtaNewsSummarize = new JTextArea();
	private JScrollPane jspNewsSummarize = new JScrollPane(jtaNewsSummarize);// ���Ÿ���
	public JScrollPane jspNewsContent = new JScrollPane();
	private JButton jbSave = new JButton("����ݸ�");
	private JButton jbSubmit = new JButton("�ύ���");
	private JButton jbPic = new JButton("��ѡ��ͼƬ");
	private JFileChooser jfc = new JFileChooser();

	public AddNewsPanel(String ygid, int BSid) {
		this.ygid = ygid;
		this.BSid = BSid;

		// �������ű���
		this.setLayout(null);
		jlAddNews.setBounds(25, 15, 100, 20);
		jlAddNews.setFont(subtitle);
		this.add(jlAddNews);

		// ����ݸ尴ť
		jbSave.setBounds(900, 15, 90, 30);
		jbSave.setOpaque(false);
		this.add(jbSave);

		// �ύ��˰�ť
		jbSubmit.setBounds(1020, 15, 90, 30);
		jbSubmit.setOpaque(false);
		this.add(jbSubmit);

		// �ָ���jtf
		jtfLine.setBounds(20, 60 - 8, 1140, 4);
		jtfLine.setEnabled(false);
		this.add(jtfLine);

		// ���ű���
		jlNewsTitle.setBounds(25 + 755, 80, 100, 20);
		jtfNewsTitle.setBounds(95 + 755, 80, 310, 20);
		jlNewsTitle.setFont(jltitle);
		this.add(jlNewsTitle);
		this.add(jtfNewsTitle);

		// ���Ÿ���
		jlNewsSummarize.setBounds(780, 110, 100, 20);
		jspNewsSummarize.setBounds(850, 110, 310, 40);
		jtaNewsSummarize.setLineWrap(true);
		jlNewsSummarize.setFont(jltitle);
		this.add(jlNewsSummarize);
		this.add(jspNewsSummarize);

		// ������Դ
		jlNewsSources.setBounds(780, 110 + 50, 100, 20);
		jtfNewsSources.setBounds(850, 110 + 50, 150, 20);
		jlNewsSources.setFont(jltitle);
		this.add(jlNewsSources);
		this.add(jtfNewsSources);

		// ����ʱ��
		jlReleaseTime.setBounds(780, 110 + 30 + 50, 100, 20);
		ReleaseTime.setBounds(850, 110 + 30 + 50, 150, 20);
		ReleaseTime.setEnabled(false);
		jlReleaseTime.setFont(jltitle);
		this.add(jlReleaseTime);
		this.add(ReleaseTime);

		// ����ͼƬ
		jlTitlePic.setBounds(780, 110 + 30 + 30 + 50, 100, 20);
		jbPic.setBounds(850, 110 + 30 + 30 + 50, 150, 22);
		jbPic.setOpaque(false);
		jlTitlePic.setFont(jltitle);
		this.add(jlTitlePic);
		this.add(jbPic);

		// ��������
		jlNewsContent.setBounds(25, 60, 100, 20);
		jspNewsContent.setBounds(25, 80, 710, 600);
		jspNewsContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspNewsContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspNewsContent.setWheelScrollingEnabled(true);
		jlNewsContent.setFont(jltitle);
		this.add(jlNewsContent);
		this.add(jspNewsContent);

		// �ļ�ѡ����
		jfc.removeChoosableFileFilter(jfc.getChoosableFileFilters()[0]);
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("JPG,JEPGͼƬ�ļ�", "jpg", "jpeg"));

		addButtonListener();

	}

	// ���ð�ʽ
	public void setSytle() {
		if (BSid == 1) {
			if (sytle1 == null) {
				sytle1 = new Style1Panel();
			}
			this.jspNewsContent.setViewportView(sytle1);
		} else if (BSid == 2) {
			if (sytle2 == null) {
				sytle2 = new Style2Panel();
			}
			this.jspNewsContent.setViewportView(sytle2);
		} else if (BSid == 3) {
			if (sytle3 == null) {
				sytle3 = new Style3Panel();
			}
			this.jspNewsContent.setViewportView(sytle3);
		}
	}

	private void addButtonListener() {
		// ����Ϊ�ݸ�
		jbSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputCheck()) {
					add_new(0, BSid);// 0δ�ύ���
				}
			}
		});

		// �ύ���
		jbSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputCheck()) {
					add_new(1, BSid);// 1�ύδ���
				}

			}

		});

		// ����ͼƬ��ť
		jbPic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (picTitelPath == null) {
					int result = jfc.showOpenDialog(AddNewsPanel.this);
					File pic = jfc.getSelectedFile();
					if (pic != null && result == JFileChooser.APPROVE_OPTION) {
						jbPic.setText(pic.getName());
						picTitelPath = pic.getPath();
						new JdialogShowPic(picTitelPath);
						System.out.println(pic.getPath());
					}
				} else {
					new JdialogShowPic(picTitelPath);
				}
			}

		});

	}

	class JdialogShowPic extends JDialog implements ActionListener {
		JLabel jlabelTip = new JLabel("��ʾ����ѡ���߱Ƚӽ�5:4��ͼƬ��");
		Image img = null;
		JPanel jpPic = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 400, 320, this);
			};
		};
		JButton jbuttonOK = new JButton("ȷ��");
		JButton jbuttonChange = new JButton("�޸�");

		public JdialogShowPic(String path) {
			img = this.getToolkit().getImage(path);

			jpPic.setLayout(null);
			this.setLayout(null);
			jlabelTip.setBounds(20, 10, 360, 20);
			this.add(jlabelTip);
			jpPic.setBounds(20, 40, 400, 320);
			this.add(jpPic);
			jbuttonOK.setBounds(20, 370 + 5, 80, 20);
			jbuttonChange.setBounds(120, 370 + 5, 80, 20);
			this.add(jbuttonOK);
			this.add(jbuttonChange);
			this.setSize(445, 440);
			this.setLocation((int) (SCREEN_WIDTH - 230) / 2, (int) (SCREEN_HEIGHT - 400) / 2);
			this.setResizable(false);
			this.setTitle("����ͼƬԤ��");
			this.setModal(true);

			jbuttonOK.addActionListener(this);
			jbuttonChange.addActionListener(this);

			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbuttonOK) {
				this.dispose();
			} else {
				int result = jfc.showOpenDialog(AddNewsPanel.this);
				File pic = jfc.getSelectedFile();
				this.dispose();
				if (pic != null && result == JFileChooser.APPROVE_OPTION) {
					jbPic.setText(pic.getName());
					picTitelPath = pic.getPath();
					new JdialogShowPic(picTitelPath);
				}
			}
		}
	}

	// ������֤
	private boolean inputCheck() {

		if (jtfNewsTitle.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(AddNewsPanel.this, "���ű��ⲻ��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			jtfNewsTitle.requestFocus();
			return false;
		}
		xwbt = jtfNewsTitle.getText().trim();

		if (jtaNewsSummarize.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ÿ�������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			jtaNewsSummarize.requestFocus();
			return false;
		}
		xwgs = jtaNewsSummarize.getText().trim();

		if (jtfNewsSources.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(AddNewsPanel.this, "������Դ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			jtfNewsSources.requestFocus();
			return false;
		}
		xwly = jtfNewsSources.getText().trim();

		fbsj = this.ReleaseTime.getText();

		// ����ͼƬ·��
		if (picTitelPath == null) {
			JOptionPane.showMessageDialog(AddNewsPanel.this, "ͼƬ·������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			jbPic.requestFocus();
			return false;
		}

		if (BSid == 1) {
			if (sytle1.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "�������ݲ���Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			xwnr = sytle1.getContent();
			sytle1.clear();

		} else if (BSid == 2) {
			// pic1
			if (sytle2.getPic1() != null) {
				pic1Path = sytle2.getPic1();
			} else {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if (sytle2.getPic1Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ��������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic1MS = sytle2.getPic1Decrition();
			// content
			if (sytle2.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "�������ݲ���Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			xwnr = sytle2.getContent();
			sytle2.clear();

		} else if (BSid == 3) {
			// pic1
			if (sytle3.getPic1() != null) {
				pic1Path = sytle3.getPic1();
			} else {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if (sytle3.getPic1Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ��������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic1MS = sytle3.getPic1Decrition();
			// pic2
			if (sytle3.getPic2() != null) {
				pic2Path = sytle3.getPic2();
			} else {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ����Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if (sytle3.getPic2Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "���Ų�ͼ��������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic2MS = sytle3.getPic2Decrition();
			// content
			if (sytle3.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(AddNewsPanel.this, "�������ݲ���Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			xwnr = sytle3.getContent();
			sytle3.clear();
		}
		return true;
	}

	// �������,���ύ��ˣ��ڷ������˰�fbztid=0��
	private void add_new(final int ztid, final int bsid) {
		// �����߳�
		dataGeted = false;
		new Thread() {
			public void run() {
				NewPC newpc = null;
				if (bsid == 1) {
					// System.out.println("picpaht========"+picTitelPath);
					newpc = new NewPC1(xwbt, xwgs, xwly, fbsj, xwnr, ygid, ztid, bsid,
							PicUtils.getBytePic(picTitelPath));
				} else if (bsid == 2) {
					newpc = new NewPC2(xwbt, xwgs, xwly, fbsj, xwnr, ygid, ztid, bsid,
							PicUtils.getBytePic(picTitelPath), PicUtils.getBytePic(pic1Path), pic1MS);
				} else if (bsid == 3) {
					newpc = new NewPC3(xwbt, xwgs, xwly, fbsj, xwnr, ygid, ztid, bsid,
							PicUtils.getBytePic(picTitelPath), PicUtils.getBytePic(pic1Path), pic1MS,
							PicUtils.getBytePic(pic2Path), pic2MS);
				}
				String msg = SocketUtil.sendNewObject(newpc, true, null);
				dataGeted = true;
				if (msg.equals("ok")) {
					if (ztid == 0) {
						JOptionPane.showMessageDialog(AddNewsPanel.this, "��ϲ������ݸ�ɹ�,�����ٶԲݸ���б༭,"
								+ "�뵽�������Ź�����棡", "��ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(AddNewsPanel.this, "��ϲ��������ӳɹ�,�����ĵȴ���ˣ�", "��ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					}
					setDefault();
				}
			}
		}.start();
		// �����߳�
		LoginWindow.watchThread();

	}

	// �ָ���Ĭ��״̬
	public void setDefault() {
		jtfNewsTitle.setText(null);
		jtfNewsSources.setText(null);
		jfc.setSelectedFile(null);
		jtaNewsSummarize.setText(null);
		jbPic.setText("��ѡ��ͼƬ");
		picTitelPath = null;
		ReleaseTime.setDate(new Date());
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// ���ƽ��� ��ʼ���� ��ʼ��ɫ
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
