package com.bn.jm.AuditManage;

import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.GET_SH_By_SHID;
import static com.bn.core.Constant.GET_PIC;
import static com.bn.core.Constant.SCREEN_HEIGHT;
import static com.bn.core.Constant.SCREEN_WIDTH;
import static com.bn.core.Constant.bpicPath;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.jltitle;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.bn.jm.MainJFrame;
import com.bn.jm.AddNews.Style1Panel;
import com.bn.jm.AddNews.Style2Panel;
import com.bn.jm.AddNews.Style3Panel;
import com.bn.sjb.PicObject;
import com.bn.util.DateChooserJButton;
import com.bn.util.PicUtils;
import com.bn.util.SocketUtil;

/**
 * 查看
 *
 */
@SuppressWarnings("serial")
public class CheckPanel extends JPanel {

	private JTextField jtfLine = new JTextField();
	private JLabel jlTitle = new JLabel("查看新闻");
	private JLabel jlNewsTitle = new JLabel("新闻标题:");
	private JLabel jlTitlePic = new JLabel("标题图片:");
	private JLabel jlNewsSources = new JLabel("新闻来源:");
	private JLabel jlReleaseTime = new JLabel("发布时间:");
	private JLabel jlNewsContent = new JLabel("新闻内容:");
	private JLabel jlNewsSummarize = new JLabel("新闻概述:");
	private JLabel jlAuditOpinion = new JLabel("审核意见:");

	Style1Panel sytle1 = null;
	Style2Panel sytle2 = null;
	Style3Panel sytle3 = null;

	private JTextField jtfNewsTitle = new JTextField(1);// 新闻标题
	private JTextField jtfNewsSources = new JTextField(1);// 新闻来源
	private DateChooserJButton ReleaseTime = new DateChooserJButton();// 发布时间
	private JTextArea jtaNewsSummarize = new JTextArea();
	private JScrollPane jspNewsSummarize = new JScrollPane(jtaNewsSummarize);// 新闻概述
	public JScrollPane jspNewsContent = new JScrollPane();
	private JButton jbTitlePic = new JButton("查看标题图片");

	private JTextArea jtaAuditOpinion = new JTextArea();// 审核意见
	JScrollPane jspAuditOpinion = new JScrollPane(jtaAuditOpinion);

	private JButton jbBack = new JButton("返回");

	String shid;
	int bsid;
	String xwnr;
	String xwid;
	String[] data;
	MainJFrame mf;

	byte[] picTitle;
	byte[] pic1;
	byte[] pic2;
	String pic1MS;
	String pic2MS;

	public CheckPanel(MainJFrame mf) {
		this.mf = mf;

		// 新增新闻标题
		this.setLayout(null);
		jlTitle.setBounds(25, 15, 100, 20);
		jlTitle.setFont(subtitle);
		this.add(jlTitle);

		// 返回按钮
		jbBack.setBounds(1020, 15, 90, 30);
		jbBack.setOpaque(false);
		this.add(jbBack);

		// 分割线jtf
		jtfLine.setBounds(20, 60 - 8, 1140, 4);
		jtfLine.setEnabled(false);
		this.add(jtfLine);

		// 新闻标题
		jlNewsTitle.setBounds(25 + 755, 80, 100, 20);
		jtfNewsTitle.setBounds(95 + 755, 80, 310, 20);
		jlNewsTitle.setFont(jltitle);
		this.add(jlNewsTitle);
		this.add(jtfNewsTitle);

		// 新闻概述
		jlNewsSummarize.setBounds(780, 110, 100, 20);
		jspNewsSummarize.setBounds(850, 110, 310, 40);
		jtaNewsSummarize.setLineWrap(true);
		jlNewsSummarize.setFont(jltitle);
		this.add(jlNewsSummarize);
		this.add(jspNewsSummarize);

		// 新闻来源
		jlNewsSources.setBounds(780, 110 + 50, 100, 20);
		jtfNewsSources.setBounds(850, 110 + 50, 150, 20);
		jlNewsSources.setFont(jltitle);
		this.add(jlNewsSources);
		this.add(jtfNewsSources);

		// 发布时间
		jlReleaseTime.setBounds(780, 110 + 30 + 50, 100, 20);
		ReleaseTime.setBounds(850, 110 + 30 + 50, 150, 20);
		ReleaseTime.setEnabled(false);
		jlReleaseTime.setFont(jltitle);
		this.add(jlReleaseTime);
		this.add(ReleaseTime);

		// 设置图片
		jlTitlePic.setBounds(780, 110 + 30 + 30 + 50, 100, 20);
		jbTitlePic.setBounds(850, 110 + 30 + 30 + 50, 150, 22);
		jbTitlePic.setOpaque(false);
		jlTitlePic.setFont(jltitle);
		this.add(jlTitlePic);
		this.add(jbTitlePic);

		// 审核意见
		jlAuditOpinion.setBounds(780, 110 + 30 + 30 + 30 + 50, 100, 20);
		jspAuditOpinion.setBounds(850, 110 + 30 + 30 + 30 + 50, 310, 150);
		jlAuditOpinion.setFont(jltitle);
		this.add(jlAuditOpinion);
		this.add(jspAuditOpinion);

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

		// 提交审核
		jbBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mf.gotoBackSHGL(false);
			}

		});

		// 设置图片按钮
		jbTitlePic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JdialogShowPic(PicUtils.bytesToImage(picTitle));
			}

		});
	}

	// 初始化新闻查看界面
	public void flushData(String shid) {
		this.shid = shid;
		getSHById(shid);

		jtfNewsTitle.setText(data[0]);
		jtaNewsSummarize.setText(data[1]);
		jtfNewsSources.setText(data[2]);
		String time = data[3];
		// 设置JDatePicker当前选中值 ===============start
		ReleaseTime.setText(time);
		// 设置JDatePicker当前选中值 ===============over
		xwnr = data[4];
		bsid = Integer.parseInt(data[5]);
		jtaAuditOpinion.setText(data[6]);
		xwid = data[7];
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					if (bsid == 1) {
						if (sytle1 == null) {
							sytle1 = new Style1Panel();
						}
						sytle1.flushContent(xwnr);
						jspNewsContent.setViewportView(sytle1);
					} else if (bsid == 2) {
						if (sytle2 == null) {
							sytle2 = new Style2Panel();
							sytle2.isListened(false);
						}
						sytle2.flushContent(xwnr);
						jspNewsContent.setViewportView(sytle2);
					} else if (bsid == 3) {
						if (sytle3 == null) {
							sytle3 = new Style3Panel();
							sytle3.isListened(false);
						}
						sytle3.flushContent(xwnr);
						jspNewsContent.setViewportView(sytle3);

					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得审核记录里的新闻先关信息
	public void getSHById(String shid) {
		// 发送消息获得数据
		String msg = GET_SH_By_SHID;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(shid);
		sb.append(msg);
		String result = SocketUtil.sendAndGetMsg(sb.toString());
		final List<String[]> list = SocketUtil.strToList(result);
		data = list.get(0);
		// xw.xwbt, xw.xwgs, xw.xwly, shjl.tjsj, xw.xwnr, xw.bsid ,shjl.shyj,shjl.xwid
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

	// 显示标题图片的JDialog（每次new新的出来）
	class JdialogShowPic extends JDialog implements ActionListener {
		JLabel jlabelTip = new JLabel("提示：请选择宽高比接近5:4的图片！");
		Image img = new ImageIcon(bpicPath + "pic.jpg").getImage();
		JPanel jpPic = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 400, 320, this);
			};
		};
		JButton jbuttonOK = new JButton("确定");

		public JdialogShowPic(Image img) {
			if (img != null) {
				this.img = img;
			}
			jpPic.setLayout(null);
			this.setLayout(null);
			jlabelTip.setBounds(20, 10, 360, 20);
			this.add(jlabelTip);
			jpPic.setBounds(20, 40, 400, 320);
			this.add(jpPic);
			jbuttonOK.setBounds(20, 370 + 5, 80, 20);
			this.add(jbuttonOK);
			this.setSize(445, 440);
			this.setLocation((int) (SCREEN_WIDTH - 230) / 2, (int) (SCREEN_HEIGHT - 400) / 2);
			this.setResizable(false);
			this.setTitle("标题图片预览");
			this.setModal(true);
			jbuttonOK.addActionListener(this);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.dispose();
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
