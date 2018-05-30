package com.bn.jm.PersonalNewsManager;

import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.GET_PIC;
import static com.bn.core.Constant.SCREEN_HEIGHT;
import static com.bn.core.Constant.SCREEN_WIDTH;
import static com.bn.core.Constant.dataGeted;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.jltitle;
import static com.bn.core.Constant.GET_NEW_By_XWID;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.bn.jm.LoginWindow;
import com.bn.jm.MainJFrame;
import com.bn.jm.AddNews.Style1Panel;
import com.bn.jm.AddNews.Style2Panel;
import com.bn.jm.AddNews.Style3Panel;
import com.bn.sjb.NewPC;
import com.bn.sjb.NewPC1;
import com.bn.sjb.NewPC2;
import com.bn.sjb.NewPC3;
import com.bn.sjb.PicObject;
import com.bn.util.DateChooserJButton;
import com.bn.util.PicUtils;
import com.bn.util.SocketUtil;

/**
 * 新闻修改
 */
@SuppressWarnings("serial")
public class EditNewsPanel extends JPanel {

	private JTextField jtfLine = new JTextField();
	private JLabel jlTitle = new JLabel("修改新闻");
	private JLabel jlNewsTitle = new JLabel("新闻标题:");
	private JLabel jlChoosePic = new JLabel("标题图片:");
	private JLabel jlNewSoures = new JLabel("新闻来源:");
	private JLabel jlReleaseTime = new JLabel("发布时间:");
	private JLabel jlNewsContent = new JLabel("新闻内容:");
	private JLabel jlNewsSummarize = new JLabel("新闻概述:");

	Style1Panel sytle1 = null;
	Style2Panel sytle2 = null;
	Style3Panel sytle3 = null;

	private JTextField jtNewsTitle = new JTextField(1);// 新闻标题
	private JTextField jtNewSoures = new JTextField(1);// 新闻来源
	private DateChooserJButton ReleaseTime = new DateChooserJButton();// 发布时间
	private JTextArea jtaNewsSummarize = new JTextArea();
	private JScrollPane jspNewsSummarize = new JScrollPane(jtaNewsSummarize);// 新闻概述
	public JScrollPane jspNewsContent = new JScrollPane();
	private JButton jbPic = new JButton("查看标题图片");

	private String newsTitle;
	private String newsSummarize;
	private String newsSources;
	private String releaseTime;
	private String newsContent;
	private int bsid;

	byte[] picTitle;
	byte[] pic1;
	byte[] pic2;
	String pic1MS;
	String pic2MS;

	private String picTitelPath;
	private String pic1Path;
	private String pic2Path;

	private boolean picTitleChanged = false;

	private JButton jbBack = new JButton("返回");
	private JButton jbSave = new JButton("保存修改");
	private JButton jbSubmit = new JButton("提交审核");

	String xwid;
	String ztid;
	String[] data;
	MainJFrame mf;
	String ygid;

	public EditNewsPanel(MainJFrame mf, String userid) {
		this.mf = mf;
		this.ygid = userid;
		this.setLayout(null);
		jlTitle.setBounds(25, 15, 100, 20);
		jlTitle.setFont(subtitle);
		this.add(jlTitle);

		// 返回按钮
		jbBack.setBounds(780, 15, 90, 30);
		jbBack.setOpaque(false);
		this.add(jbBack);

		// 保存草稿按钮
		jbSave.setBounds(900, 15, 90, 30);
		jbSave.setOpaque(false);
		this.add(jbSave);

		// 提交审核按钮
		jbSubmit.setBounds(1020, 15, 90, 30);
		jbSubmit.setOpaque(false);
		this.add(jbSubmit);

		// 分割线jtf
		jtfLine.setBounds(20, 60 - 8, 1140, 4);
		jtfLine.setEnabled(false);
		this.add(jtfLine);

		// 新闻标题
		jlNewsTitle.setBounds(25 + 755, 80, 100, 20);
		jtNewsTitle.setBounds(95 + 755, 80, 310, 20);
		jlNewsTitle.setFont(jltitle);
		this.add(jlNewsTitle);
		this.add(jtNewsTitle);

		// 新闻概述
		jlNewsSummarize.setBounds(780, 110, 100, 20);
		jspNewsSummarize.setBounds(850, 110, 310, 40);
		jtaNewsSummarize.setLineWrap(true);
		jlNewsSummarize.setFont(jltitle);
		this.add(jlNewsSummarize);
		this.add(jspNewsSummarize);

		// 新闻来源
		jlNewSoures.setBounds(780, 110 + 50, 100, 20);
		jtNewSoures.setBounds(850, 110 + 50, 150, 20);
		jlNewSoures.setFont(jltitle);
		this.add(jlNewSoures);
		this.add(jtNewSoures);

		// 发布时间
		jlReleaseTime.setBounds(780, 110 + 30 + 50, 100, 20);
		ReleaseTime.setBounds(850, 110 + 30 + 50, 150, 20);
		ReleaseTime.setEnabled(false);
		jlReleaseTime.setFont(jltitle);
		this.add(jlReleaseTime);
		this.add(ReleaseTime);

		// 设置图片
		jlChoosePic.setBounds(780, 110 + 30 + 30 + 50, 100, 20);
		jbPic.setBounds(850, 110 + 30 + 30 + 50, 150, 22);
		jbPic.setOpaque(false);
		jlChoosePic.setFont(jltitle);
		this.add(jlChoosePic);
		this.add(jbPic);

		// 新闻内容
		jlNewsContent.setBounds(25, 60, 100, 20);
		jspNewsContent.setBounds(25, 80, 710, 600);
		jspNewsContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspNewsContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspNewsContent.setWheelScrollingEnabled(true);
		jlNewsContent.setFont(jltitle);
		this.add(jlNewsContent);
		this.add(jspNewsContent);

		addButtonListener();
	}

	private void addButtonListener() {
		// 保存修改
		jbSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputCheck()) {
					updata_new(Integer.parseInt(ztid));// 保持原有状态

				}
			}

		});

		// 提交审核
		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputCheck()) {
					updata_new(1);// 1提交未审核
					// add_shjl(1);

				}

			}

		});

		// 返回按钮
		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				picTitleChanged = false;
				pic1Path = null;
				pic2Path = null;
				mf.gotoBackGRXWGL(false);
			}

		});

		// 设置图片按钮
		jbPic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (picTitleChanged) {
					new JdialogShowPic(picTitelPath);
				} else {
					new JdialogShowPic(PicUtils.bytesToImage(picTitle));
					// System.out.println("image******************");
				}
			}

		});

	}

	// 输入验证
	private boolean inputCheck() {

		if (jtNewsTitle.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻标题不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			jtNewsTitle.requestFocus();
			return false;
		}
		newsTitle = jtNewsTitle.getText().trim();

		if (jtaNewsSummarize.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻作者不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			jtaNewsSummarize.requestFocus();
			return false;
		}
		newsSummarize = jtaNewsSummarize.getText().trim();

		if (jtNewSoures.getText().trim().length() <= 0) {
			JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻来源不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
			jtNewSoures.requestFocus();
			return false;
		}
		newsSources = jtNewSoures.getText().trim();

		// 发布时间
		releaseTime = this.ReleaseTime.getText();

		if (bsid == 1) {
			if (sytle1.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻内容不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			newsContent = sytle1.getContent();
			sytle1.clear();

		} else if (bsid == 2) {
			// pic1
			pic1Path = sytle2.getPic1();
			if (sytle2.getPic1Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻插图描述不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic1MS = sytle2.getPic1Decrition();
			// content
			if (sytle2.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻内容不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			newsContent = sytle2.getContent();
			sytle2.clear();

		} else if (bsid == 3) {
			// pic1
			pic1Path = sytle3.getPic1();
			if (sytle3.getPic1Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻插图描述不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic1MS = sytle3.getPic1Decrition();
			// pic2
			pic2Path = sytle3.getPic2();
			if (sytle3.getPic2Decrition().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻插图描述不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			pic2MS = sytle3.getPic2Decrition();
			// content
			if (sytle3.getContent().trim().length() <= 0) {
				JOptionPane.showMessageDialog(EditNewsPanel.this, "新闻内容不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			newsContent = sytle3.getContent();
			sytle3.clear();
		}

		return true;
	}

	// 更新新闻
	private void updata_new(final int ztid) {
		// 任务线程
		dataGeted = false;
		new Thread() {
			public void run() {
				NewPC newpc = null;
				if (bsid == 1) {
					// System.out.println("picpaht========"+picTitelPath);
					newpc = new NewPC1(newsTitle, newsSummarize, newsSources, releaseTime, newsContent, ygid, ztid, bsid,
							picTitleChanged ? PicUtils.getBytePic(picTitelPath) : picTitle);
				} else if (bsid == 2) {
					newpc = new NewPC2(newsTitle, newsSummarize, newsSources, releaseTime, newsContent, ygid, ztid, bsid,
							picTitleChanged ? PicUtils.getBytePic(picTitelPath) : picTitle,
							pic1Path != null ? PicUtils.getBytePic(pic1Path) : pic1, pic1MS);
				} else if (bsid == 3) {
					newpc = new NewPC3(newsTitle, newsSummarize, newsSources, releaseTime, newsContent, ygid, ztid, bsid,
							picTitleChanged ? PicUtils.getBytePic(picTitelPath) : picTitle,
							pic1Path != null ? PicUtils.getBytePic(pic1Path) : pic1, pic1MS,
							pic2Path != null ? PicUtils.getBytePic(pic2Path) : pic2, pic2MS);
				}
				System.out.println("**********xwnr" + newsContent);
				String msg = SocketUtil.sendNewObject(newpc, false, xwid);
				dataGeted = true;
				if (msg.equals("ok")) {
					if (ztid == 2 || ztid == 0) {
						JOptionPane.showMessageDialog(EditNewsPanel.this, "恭喜，保存修改成功！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(EditNewsPanel.this, "恭喜，新闻提交成功,请耐心等待审核！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				jtNewsTitle.setText(null);
				jtNewSoures.setText(null);
				jtaNewsSummarize.setText(null);
				jbPic.setText("查看标题图片");
				picTitelPath = null;
				picTitleChanged = false;
				pic1Path = null;
				pic2Path = null;
				if (ztid == 2 || ztid == 0) {
					mf.gotoBackGRXWGL(false);
				} else {
					mf.gotoBackGRXWGL(true);
				}
			}
		}.start();
		// 监视线程
		LoginWindow.watchThread();
	}

	// 获得新闻
	public void getNewById(String xwid) {
		// 发送消息获得数据
		String msg = GET_NEW_By_XWID;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(xwid);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		final List<String[]> list = SocketUtil.strToList(result);
		data = list.get(0);
		// xw.xwbt, xw.xwgs, xw.xwly, xw.fbsj, xw.xwnr, xw.ztid ,xw.bsid
	}

	// 初始化新闻修改界面
	public void flushData(String xwid) {
		this.xwid = xwid;
		getNewById(xwid);
		jtNewsTitle.setText(data[0]);
		jtaNewsSummarize.setText(data[1]);
		jtNewSoures.setText(data[2]);
		this.ReleaseTime.setText(data[3]);
		newsContent = data[4];
		ztid = data[5];
		bsid = Integer.parseInt(data[6]);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					if (bsid == 1) {
						if (sytle1 == null) {
							sytle1 = new Style1Panel();
						}
						sytle1.flushContent(newsContent);
						jspNewsContent.setViewportView(sytle1);
					} else if (bsid == 2) {
						if (sytle2 == null) {
							sytle2 = new Style2Panel();
						}
						sytle2.flushContent(newsContent);
						jspNewsContent.setViewportView(sytle2);
					} else if (bsid == 3) {
						if (sytle3 == null) {
							sytle3 = new Style3Panel();
							// System.out.println("************"+xwnr);
						}
						sytle3.flushContent(newsContent);
						jspNewsContent.setViewportView(sytle3);

					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得图片的相关方法
	/*
	 * 根据板式信息启动不同个数的线程开始获得图片数据，并且更新显示
	 */
	public void flushPics() {
		new Thread() {
			public void run() {
				flushDataPic(0);
			}
		}.start();

		if (bsid == 2) {
			new Thread() {
				public void run() {
					flushDataPic(1);
				}
			}.start();

		} else if (bsid == 3) {
			new Thread() {
				public void run() {
					flushDataPic(1);
				}
			}.start();
			new Thread() {
				public void run() {
					flushDataPic(2);
				}
			}.start();
		}
	}

	/*
	 * 获得指定图片，并更新此图片的显示（通用方法） 1.要判断图片类型 2.要判断板式
	 */
	public void flushDataPic(final int picLX) {
		getPic(xwid, picLX);
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					if (picLX == 1) {
						if (bsid == 2) {
							sytle2.flushPic1(PicUtils.bytesToImage(pic1), pic1MS);
						} else if (bsid == 3) {
							sytle3.flushPic1(PicUtils.bytesToImage(pic1), pic1MS);
						}
					} else if (picLX == 2) {
						sytle3.flushPic2(PicUtils.bytesToImage(pic2), pic2MS);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得指定图片信息
	/*
	 * 获得指定图片，并把图片相关数据保存到了成员变量中
	 */
	public void getPic(String xwid, int picLX) {
		// 发送消息获得数据
		String msg = GET_PIC;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(xwid + "<->");
		sb.append(picLX);
		sb.append(msg);
		PicObject pico = SocketUtil.sendAndGetPic(sb.toString());
		if (picLX == 0) {
			this.picTitle = pico.pic;
		} else if (picLX == 1) {
			this.pic1 = pico.pic;
			this.pic1MS = pico.picMs;

		} else if (picLX == 2) {
			this.pic2 = pico.pic;
			this.pic2MS = pico.picMs;
		}
	}

	class JdialogShowPic extends JDialog implements ActionListener {
		private JFileChooser jfc = new JFileChooser();
		JLabel jlabelTip = new JLabel("提示：请选择宽高比接近5:4的图片！");
		Image img = null;
		JPanel jpPic = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 400, 320, this);
			};
		};
		JButton jbuttonOK = new JButton("确定");
		JButton jbuttonChange = new JButton("修改");

		public JdialogShowPic(String path) {
			img = this.getToolkit().getImage(path);
			// 文件选择器
			jfc.removeChoosableFileFilter(jfc.getChoosableFileFilters()[0]);
			jfc.addChoosableFileFilter(new FileNameExtensionFilter("JPG,JEPG图片文件", "jpg", "jpeg"));
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
			this.setTitle("标题图片预览");
			this.setModal(true);

			jbuttonOK.addActionListener(this);
			jbuttonChange.addActionListener(this);

			this.setVisible(true);
		}

		public JdialogShowPic(Image img) {
			// 文件选择器
			this.img = img;
			jfc.removeChoosableFileFilter(jfc.getChoosableFileFilters()[0]);
			jfc.addChoosableFileFilter(new FileNameExtensionFilter("JPG,JEPG图片文件", "jpg", "jpeg"));
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
			this.setTitle("标题图片预览");
			this.setModal(true);

			jbuttonOK.addActionListener(this);
			jbuttonChange.addActionListener(this);

			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbuttonOK) {
				picTitleChanged = true;
				this.dispose();
			} else {
				int result = jfc.showOpenDialog(EditNewsPanel.this);
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

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// 绘制渐变 起始坐标 起始颜色
		g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}

}
