package com.bn.jm.ProgramaManage;

import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;

import static com.bn.core.Constant.ADD_LM;
import static com.bn.core.Constant.DEL_LM;
import static com.bn.core.Constant.bpicPath;
import static com.bn.core.Constant.dataGeted;
import static com.bn.core.Constant.subtitle;
import static com.bn.core.Constant.GET_LM;
import static com.bn.core.Constant.GET_LM_NEW;
import static com.bn.core.Constant.GET_DFB_NEW;
import static com.bn.core.Constant.XG_LM;
import static com.bn.core.Constant.XG_LMID;
import static com.bn.core.Constant.TRAN_LM;
import static com.bn.core.Constant.TRAN_XW;
import static com.bn.core.Constant.XG_FBZTID;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
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
 * ��Ŀ����
 */
@SuppressWarnings("serial")
public class ProgramaManagePanel extends JPanel
{
	private JLabel jlPrograma=new JLabel("��Ŀ�б�");
	private JLabel jlProgramaNews=new JLabel("��Ŀ��������");
	private JLabel jlNews=new JLabel("����������");
	
	
	//��Ŀ
	private JToolBar jtb1=new JToolBar();
	private String[] jtbName1={"����","����","���","ɾ��"};
	private JButton[] jtbButton1={new JButton(new ImageIcon(bpicPath+"Up.png")),
			new JButton(new ImageIcon(bpicPath+"Down.png")),
			new JButton(new ImageIcon(bpicPath+"Add.png")),
			new JButton(new ImageIcon(bpicPath+"Del.png"))};
	JTable jtPrograma = new JTable()
	{
        @Override
        protected JTableHeader createDefaultTableHeader() 
        {
            return new GroupableTableHeader(columnModel);
        }
    };
	JScrollPane jsPrograma=new JScrollPane(jtPrograma);
	
	ProgramaTableModel tmPrograma;
	
	
	//��Ŀ��������
	private JToolBar jtb2=new JToolBar();
	private String[] jtbName2={"����","����"};
	private JButton[] jtbButton2={new JButton(new ImageIcon(bpicPath+"Up.png")),
			new JButton(new ImageIcon(bpicPath+"Down.png"))};
	JTable jtProgramaNews = new JTable()
	{
        @Override
        protected JTableHeader createDefaultTableHeader() 
        {
            return new GroupableTableHeader(columnModel);
        }
    };
	JScrollPane jsProgramaNews=new JScrollPane(jtProgramaNews);
	
	ProgramaNewsTableModel tmProgramaNews;
	
	//����
	private JToolBar jtb3=new JToolBar();
	private String[] jtbName3={"����","����","����"};
	private JButton[] jtbButton3={new JButton(new ImageIcon(bpicPath+"Up.png")),
			new JButton(new ImageIcon(bpicPath+"Down.png")),
			new JButton(new ImageIcon(bpicPath+"Bin.png"))};
	JTable jtNews = new JTable()
	{
        @Override
        protected JTableHeader createDefaultTableHeader() 
        {
            return new GroupableTableHeader(columnModel);
        }
    };
	JScrollPane jsNews=new JScrollPane(jtNews);
	
	private JButton leftBtn = new JButton(new ImageIcon(bpicPath+"Left1.png"));
	private JButton rightBtn = new JButton(new ImageIcon(bpicPath+"Right1.png"));
	
	
	NewsTableModel tmNews;
	
	//��¼��Ŀ�����ϴε������
	int pressedRowInLM=-1;
	//��¼��Ԫ�������Ƿ�ı�
	boolean isDataChanged=false;
	//��¼�ոձ༭�˵���
    int lastEditRowInLM=-1;
    //��¼�����е���Ŀid
    int lmid=-1;
    //��¼��Ŀ���ű����ϴε������
	int pressedRowInLMXW=-1;
	//��¼���ű����ϴε������
	int pressedRowInXW=-1;
	
	
	private MainJFrame mf;
	
	public ProgramaManagePanel(MainJFrame mf)
	{
		this.setLayout(null);
		this.mf=mf;
		
		//��Ŀ��
		this.add(jlPrograma);
		jlPrograma.setBounds(25, 15, 120, 20);
		jlPrograma.setFont(subtitle);
		this.add(jsPrograma);
		jsPrograma.setBounds(25, 70, 280, 580);
		//������
		for(int i=0;i<jtbButton1.length;i++)
		{
			jtb1.add(jtbButton1[i]);
			jtbButton1[i].addActionListener(null);
			jtbButton1[i].setToolTipText(jtbName1[i]);
		}
		jtb1.addSeparator();
		jtb1.setFloatable(false);
		jtb1.setBounds(25, 40, 280, 30);
		this.add(jtb1);
		
		
		//��Ŀ�������ű�
		this.add(jlProgramaNews);
		jlProgramaNews.setBounds(365, 15, 120, 20);
		jlProgramaNews.setFont(subtitle);
		this.add(jsProgramaNews);
		jsProgramaNews.setBounds(365, 70, 280, 580);
		//������
		for(int i=0;i<jtbButton2.length;i++)
		{
			jtb2.add(jtbButton2[i]);
			jtbButton2[i].addActionListener(null);
			jtbButton2[i].setToolTipText(jtbName2[i]);
		}
		jtb2.addSeparator();
		jtb2.setFloatable(false);
		jtb2.setBounds(365, 40, 280, 30);
		this.add(jtb2);
		
		
		
		this.add(leftBtn);
		leftBtn.setBounds(650, 280, 50, 30);
		leftBtn.setOpaque(false);
		this.add(rightBtn);
		rightBtn.setBounds(650, 350, 50, 30);
		rightBtn.setOpaque(false);
		
		
		//���ű�
		this.add(jlNews);
		jlNews.setBounds(705, 15, 120, 20);
		jlNews.setFont(subtitle);
		this.add(jsNews);
		jsNews.setBounds(705, 70, 280, 580);
		//������
		for(int i=0;i<jtbButton3.length;i++)
		{
			jtb3.add(jtbButton3[i]);
			jtbButton3[i].addActionListener(null);
			jtbButton3[i].setToolTipText(jtbName3[i]);
		}
		jtb3.addSeparator();
		jtb3.setFloatable(false);
		jtb3.setBounds(705, 40, 280, 30);
		this.add(jtb3);
		
		
		addTableListeners();
		addToolBarListeners();
		addButtonListeners();
		
		
		
	}
	
	
	
	//��ʼ�����
	public void initTable(JTable jtLM)
	{
		//���ñ�ͷ������
		jtLM.getTableHeader().setUI(new GroupableTableHeaderUI());
		//�����и�
		jtLM.setRowHeight(30);
		//����ֻ�ܵ�ѡ
		jtLM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		//���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		//���õ�һ�о���
		jtLM.setDefaultRenderer(Integer.class, dtcr);
		

		//��ñ�ͷ
		JTableHeader tableHeader = jtLM.getTableHeader();  
		//��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader .getDefaultRenderer(); 
		//��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//����в����ƶ�    
		tableHeader.setReorderingAllowed(false);
		

		//���ÿһ�е�����
		TableColumn tc0 = jtLM.getColumnModel().getColumn(0);
		TableColumn tc1 = jtLM.getColumnModel().getColumn(1);
		TableColumn tc2 = jtLM.getColumnModel().getColumn(2);
		
		
		//����ÿһ�п��
		tc0.setPreferredWidth(50);
		tc1.setPreferredWidth(50);
		tc2.setPreferredWidth(160);
		
		
		//����ÿһ�д�С���ɱ�
		tc0.setResizable(false);
		tc1.setResizable(false);
		tc2.setResizable(false);
	}
	
	
	
	/*
	 * ��Ӽ���������
	 */
	public void addToolBarListeners()
	{
		//��Ŀ������******************����
		jtbButton1[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInLM==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ����Ŀ��", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLM<=0)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "����Ŀ���ö���", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLM>0)
				{
					tranLM(tmPrograma.tableData[pressedRowInLM][0].toString(), tmPrograma.tableData[pressedRowInLM][1].toString(), 
							tmPrograma.tableData[pressedRowInLM-1][0].toString(), tmPrograma.tableData[pressedRowInLM-1][1].toString());
					
				}	
			}
			
		});
		//����
		jtbButton1[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInLM==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ����Ŀ��", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLM>=(tmPrograma.tableData.length-1))
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "����Ŀ���õף�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLM<(tmPrograma.tableData.length-1))
				{
					tranLM(tmPrograma.tableData[pressedRowInLM][0].toString(), tmPrograma.tableData[pressedRowInLM][1].toString(), 
							tmPrograma.tableData[pressedRowInLM+1][0].toString(), tmPrograma.tableData[pressedRowInLM+1][1].toString());
				}	
				
				
				
			}
			
		});
		//�����Ŀ
		jtbButton1[2].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				      addLM();
				      dataGeted=false;
						new Thread()
						{
							public void run()
							{
								flushDataLM();
								dataGeted=true;
							}
						}.start();
						//�����߳�
						LoginWindow.watchThread();     
			}
			
		});
		//ɾ����Ŀ
		jtbButton1[3].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				  delLM(lmid);
			      dataGeted=false;
					new Thread()
					{
						public void run()
						{
							flushDataLM();
							dataGeted=true;
						}
					}.start();
					//�����߳�
					LoginWindow.watchThread();    
				
			}
			
		});
		
		//��Ŀ�������Ź�����******************
		jtbButton2[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInLMXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLMXW<=0)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "���������ö���", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLMXW>0)
				{
					tranXW(tmProgramaNews.tableData[pressedRowInLMXW][0].toString(), tmProgramaNews.tableData[pressedRowInLMXW][1].toString(), 
							tmProgramaNews.tableData[pressedRowInLMXW-1][0].toString(), tmProgramaNews.tableData[pressedRowInLMXW-1][1].toString());
					
				}	
			}
			
		});
		//����
		jtbButton2[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInLMXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLMXW>=(tmProgramaNews.tableData.length-1))
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "���������õף�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInLMXW<(tmPrograma.tableData.length-1))
				{
					tranXW(tmProgramaNews.tableData[pressedRowInLMXW][0].toString(), tmProgramaNews.tableData[pressedRowInLMXW][1].toString(), 
							tmProgramaNews.tableData[pressedRowInLMXW+1][0].toString(), tmProgramaNews.tableData[pressedRowInLMXW+1][1].toString());
				}	
				
				
				
			}
			
		});
		
		
		//���������Ź�����******************
		jtbButton3[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInXW<=0)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "���������ö���", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInXW>0)
				{
					tranDFBXW(tmNews.tableData[pressedRowInXW][0].toString(), tmNews.tableData[pressedRowInXW][1].toString(), 
							tmNews.tableData[pressedRowInXW-1][0].toString(), tmNews.tableData[pressedRowInXW-1][1].toString());
					
				}	
			}
			
		});
		//����
		jtbButton3[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInXW>=(tmNews.tableData.length-1))
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "���������õף�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInXW<(tmNews.tableData.length-1))
				{
					tranDFBXW(tmNews.tableData[pressedRowInXW][0].toString(), tmNews.tableData[pressedRowInXW][1].toString(), 
							tmNews.tableData[pressedRowInXW+1][0].toString(), tmNews.tableData[pressedRowInXW+1][1].toString());
				}	
				
				
				
			}
			
		});
		
		//���Ź���
		jtbButton3[2].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pressedRowInXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				int i = JOptionPane.showConfirmDialog(ProgramaManagePanel.this, "ȷ��Ҫ������������Ϊ������?", "��ʾ",JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(i==0)//�����"��"
				{
					xgFBZTID(tmNews.tableData[pressedRowInXW][1].toString(), "2");
				}				
			}
			
		});	
	}
	
	public void addTableListeners()
	{//��ӱ��ļ�����
		jtPrograma.addMouseListener
		(
			new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent arg0)
				{
					final int row = jtPrograma.getSelectedRow();
					if(pressedRowInLM!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInLM = row;
						lmid=Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString());
						if(isDataChanged)//������ݸı���  ���Ƿ񱣴�����
						{
							isDataChanged=false;
							
							if(tmPrograma.tableData[lastEditRowInLM][2].equals(""))
							{
								JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��Ŀ���Ʋ���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
								tmPrograma.tableData[lastEditRowInLM][2]=tmPrograma.OritableData[lastEditRowInLM][2];
								tmPrograma.fireTableCellUpdated(lastEditRowInLM, 2);
								return;
							}
							int i = JOptionPane.showConfirmDialog(ProgramaManagePanel.this, "�Ƿ񱣴����Ŀ����޸�?", "��ʾ",JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(i==0)//�����"��"
							{
								xgLM(tmPrograma.tableData[lastEditRowInLM][1].toString(),tmPrograma.tableData[lastEditRowInLM][2].toString());
							}else
							{//�����"��"����"��"
								tmPrograma.tableData[lastEditRowInLM][2]=tmPrograma.OritableData[lastEditRowInLM][2];
								tmPrograma.fireTableCellUpdated(lastEditRowInLM, 2);
							}
						}
						dataGeted=false;
						new Thread()
						{
							public void run()
							{
								flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString()));
								dataGeted=true;
							}
						}.start();
						//�����߳�
						LoginWindow.watchThread();
					}
				}
				
			}
		);
		
		jtPrograma.addKeyListener
		(
			new KeyAdapter() 
			{

				@Override
				public void keyReleased(KeyEvent e) 
				{
					//rowʵʱ��¼��������
					final int row = jtPrograma.getSelectedRow();
					if(pressedRowInLM!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInLM = row;
						lmid=Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString());
						if(isDataChanged)//������ݸı���  ���Ƿ񱣴�����
						{
							isDataChanged=false;
							
							if(tmPrograma.tableData[lastEditRowInLM][2].equals(""))
							{
								JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��Ŀ���Ʋ���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
								tmPrograma.tableData[lastEditRowInLM][2]=tmPrograma.OritableData[lastEditRowInLM][2];
								tmPrograma.fireTableCellUpdated(lastEditRowInLM, 2);
								return;
							}
							int i = JOptionPane.showConfirmDialog(ProgramaManagePanel.this, "�Ƿ񱣴����Ŀ����޸�?", "��ʾ",JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(i==0)//�����"��"
							{
								xgLM(tmPrograma.tableData[lastEditRowInLM][1].toString(),tmPrograma.tableData[lastEditRowInLM][2].toString());
							}else
							{//�����"��"����"��"
								tmPrograma.tableData[lastEditRowInLM][2]=tmPrograma.OritableData[lastEditRowInLM][2];
								tmPrograma.fireTableCellUpdated(lastEditRowInLM, 2);
							}
						}
						dataGeted=false;
						new Thread()
						{
							public void run()
							{
								flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString()));
								dataGeted=true;
							}
						}.start();
						//�����߳�
						LoginWindow.watchThread();
					}
				}
			}
		);
		
		//����Ŀ������ӱ�������
		jtProgramaNews.addMouseListener
		(
			new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent arg0)
				{
					final int row = jtProgramaNews.getSelectedRow();
					if(pressedRowInLMXW!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInLMXW = row;
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					if(e.getClickCount()==2)
					{
						String xwid=tmProgramaNews.tableData[pressedRowInLMXW][1].toString();
						mf.gotoXWCK(xwid, true);	
					}			
				}
			}
		);
		
		jtProgramaNews.addKeyListener
		(
			new KeyAdapter() 
			{

				@Override
				public void keyReleased(KeyEvent e) 
				{
					//rowʵʱ��¼��������
					final int row = jtProgramaNews.getSelectedRow();
					if(pressedRowInLMXW!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInLMXW = row;
					}
				}
			}
		);
		
		
		//�����������ű���ӱ�������
		jtNews.addMouseListener
		(
			new MouseAdapter() 
			{
				@Override
				public void mousePressed(MouseEvent arg0)
				{
					final int row = jtNews.getSelectedRow();
					if(pressedRowInXW!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInXW = row;
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					if(e.getClickCount()==2)
					{
						String xwid=tmNews.tableData[pressedRowInXW][1].toString();
						mf.gotoXWCK(xwid, true);	
					}			
				}
			}
		);
		
		jtNews.addKeyListener
		(
			new KeyAdapter() 
			{

				@Override
				public void keyReleased(KeyEvent e) 
				{
					//rowʵʱ��¼��������
					final int row = jtNews.getSelectedRow();
					if(pressedRowInXW!=row)
					{//�������Ĳ���ͬһ��
						pressedRowInXW = row;
					}
				}
			}
		);
		
		
		
		
		
		
		
		
	}
	
	public void addButtonListeners()
	{//��Ӱ�ť�ļ�����
		//�������Ű�ť��left
		leftBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(pressedRowInLM==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ����Ŀ��", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				if(pressedRowInXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				xgLMID(tmNews.tableData[pressedRowInXW][1].toString(), tmPrograma.tableData[pressedRowInLM][1].toString());
			}
			
		});
		
		
		//�������Ű�ť��right
		rightBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(pressedRowInLMXW==-1)
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ�����ţ�", "��ʾ", JOptionPane.NO_OPTION);
					return;
				}
				xgLMID(tmProgramaNews.tableData[pressedRowInLMXW][1].toString(), "0");
			}
			
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * ���ݸ��·���
	 */
	
	//������Ŀ�����ݵķ���
	public void flushDataLM() 
	{
			//������Ϣ�������
			String msg = GET_LM;
			StringBuilder sb = new StringBuilder();
			sb.append(msg);
			sb.append(msg);
			String result =SocketUtil.sendAndGetMsg(sb.toString());
			final List<String[]> list = SocketUtil.strToList(result);
			try {
				SwingUtilities.invokeAndWait
				(
				     new Runnable()
				     {
				    	 public void run()
				    	 {
				    		 int colCount=0;
				 			 int rowCount = list.size();
				    		 if(rowCount!=0)
				    		 {
				    			colCount = list.get(0).length; 
				    		 }
				 			//���±��ģ��
				    		 tmPrograma=new ProgramaTableModel(ProgramaManagePanel.this);
				 			//��ʼ���������
				    		 tmPrograma.tableData = new Object[rowCount][colCount];
				    		 tmPrograma.OritableData = new Object[rowCount][colCount];
				 			//ͨ��ѭ�������ݿ�������
				 			for(int i=0;i<rowCount;i++)
				 			{
				 				for(int j=0;j<colCount;j++)
				 				{
				 					tmPrograma.tableData[i][j]=list.get(i)[j];
				 					tmPrograma.OritableData[i][j]=list.get(i)[j];
				 				}
				 			}
				 			//���ñ��ģ��
				 			jtPrograma.setModel(tmPrograma);	
				 			//���±�����
				 			initTable(jtPrograma);
				    	 }
				     }
				);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
	
	//������Ŀ�������ű����ݵķ���
	public void flushDataLMXW(int lmid) 
	{
			//������Ϣ�������
			String msg = GET_LM_NEW;
			StringBuilder sb = new StringBuilder();
			sb.append(msg);
			sb.append(lmid);
			sb.append(msg);
			String result =SocketUtil.sendAndGetMsg(sb.toString());
			final List<String[]> list = SocketUtil.strToList(result);
			try {
				SwingUtilities.invokeAndWait
				(
				     new Runnable()
				     {
				    	 public void run()
				    	 {
				    		 int colCount=0;
				 			 int rowCount = list.size();
				    		 if(rowCount!=0)
				    		 {
				    			colCount = list.get(0).length; 
				    		 }
				 			//���±��ģ��
				    		 tmProgramaNews=new ProgramaNewsTableModel(ProgramaManagePanel.this);
				 			//��ʼ���������
				    		 tmProgramaNews.tableData = new Object[rowCount][colCount];
				 			//ͨ��ѭ�������ݿ�������
				 			for(int i=0;i<rowCount;i++)
				 			{
				 				for(int j=0;j<colCount;j++)
				 				{
				 					tmProgramaNews.tableData[i][j]=list.get(i)[j];
				 					
				 				}
				 			}
				 			//���ñ��ģ��
				 			jtProgramaNews.setModel(tmProgramaNews);	
				 			//���±�����
				 			initTable(jtProgramaNews);
				    	 }
				     }
				);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
	
	//������Ŀ�����ݵķ���
	public void flushDataXW() 
	{
			//������Ϣ�������
			String msg = GET_DFB_NEW;
			StringBuilder sb = new StringBuilder();
			sb.append(msg);
			sb.append(msg);
			String result =SocketUtil.sendAndGetMsg(sb.toString());
			final List<String[]> list = SocketUtil.strToList(result);
			try {
				SwingUtilities.invokeAndWait
				(
				     new Runnable()
				     {
				    	 public void run()
				    	 {
				    		 int colCount=0;
				 			 int rowCount = list.size();
				    		 if(rowCount!=0)
				    		 {
				    			colCount = list.get(0).length; 
				    		 }
				 			//���±��ģ��
				    		 tmNews=new NewsTableModel(ProgramaManagePanel.this);
				 			//��ʼ���������
				    		 tmNews.tableData = new Object[rowCount][colCount];
				 			//ͨ��ѭ�������ݿ�������
				 			for(int i=0;i<rowCount;i++)
				 			{
				 				for(int j=0;j<colCount;j++)
				 				{
				 					tmNews.tableData[i][j]=list.get(i)[j];
				 				}
				 			}
				 			//���ñ��ģ��
				 			jtNews.setModel(tmNews);	
				 			//���±�����
				 			initTable(jtNews);
				    	 }
				     }
				);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
	
	//�޸���Ŀ����
	public void xgLM(final String lmid,final String lmmc)//��ɫID  ��ɫ����
	{
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = XG_LM;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(lmid);
				sb.append("<->");
				sb.append(lmmc);
				sb.append(msg);
				String result =SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				if(result.endsWith("ok"))
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "�޸ĳɹ���", "��ʾ", JOptionPane.NO_OPTION);
					dataGeted=false;
					LoginWindow.watchThread();
					flushDataLM();
					dataGeted=true;
				}else
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "������ϣ��޸�ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	//�����Ŀ
	private void addLM()
	{
	  StringBuilder sb=new StringBuilder();
	  sb.append(ADD_LM);
	  sb.append(ADD_LM);
	  SocketUtil.sendAndGetMsg(sb.toString());	
	}
	
	
	//ɾ����Ŀ
	public void delLM(final int lmid)//������ ��ɫID
	{
		if(lmid==-1)
		{
			JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��ѡ����Ŀ��", "��ʾ", JOptionPane.NO_OPTION);
			return;
		}
		//������Ϣ�������
		String msg = DEL_LM;//�ܷ�ɾ���ý�ɫ
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(lmid);
		sb.append(msg);
		String result =SocketUtil.sendAndGetMsg(sb.toString());
		if(result.equals("ok"))
		{
			JOptionPane.showMessageDialog(ProgramaManagePanel.this, "��Ŀɾ���ɹ���", "��ʾ", JOptionPane.NO_OPTION);
		}else if(result.equals("hasNew"))
		{
			JOptionPane.showMessageDialog(ProgramaManagePanel.this, "����Ŀ�º������ţ�ɾ����Ŀʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
		}
				
	}
	
	//������Ŀ˳��
	public void tranLM(final String sxid1,final String lmid1,final String sxid2,final String lmid2)//��ɫID  ��ɫ����
	{
		//sxid1 lmid2
		//sxid2 lmid1
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = TRAN_LM;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(sxid1+"<->");
				sb.append(lmid2+"<->");
				sb.append(sxid2+"<->");
				sb.append(lmid1);
				sb.append(msg);
				SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				//������Ŀ��Ϣ
				dataGeted=false;
				LoginWindow.watchThread();
				flushDataLM();
				dataGeted=true;
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	//������Ŀ˳��
	public void tranXW(final String sxid1,final String xwid1,final String sxid2,final String xwid2)//��ɫID  ��ɫ����
	{
		//sxid1 xwid2
		//sxid2 xwid1
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = TRAN_XW;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(sxid1+"<->");
				sb.append(xwid2+"<->");
				sb.append(sxid2+"<->");
				sb.append(xwid1);
				sb.append(msg);
				SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				//������Ŀ��Ϣ
				dataGeted=false;
				LoginWindow.watchThread();
				flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString()));
				dataGeted=true;
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	//��������������˳��
	public void tranDFBXW(final String sxid1,final String xwid1,final String sxid2,final String xwid2)//��ɫID  ��ɫ����
	{
		//sxid1 xwid2
		//sxid2 xwid1
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = TRAN_XW;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(sxid1+"<->");
				sb.append(xwid2+"<->");
				sb.append(sxid2+"<->");
				sb.append(xwid1);
				sb.append(msg);
				SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				//������Ŀ��Ϣ
				dataGeted=false;
				LoginWindow.watchThread();
				flushDataXW();
				dataGeted=true;
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	
	//�޸���Ŀid
	public void xgLMID(final String xwid,final String lmid)//����ID  ��Ŀid
	{
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = XG_LMID;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(xwid);
				sb.append("<->");
				sb.append(lmid);
				sb.append(msg);
				String result =SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				if(result.endsWith("ok"))
				{
					dataGeted=false;
					LoginWindow.watchThread();
					flushDataLMXW(Integer.parseInt(tmPrograma.tableData[pressedRowInLM][1].toString()));
					dataGeted=true;
					//������Ŀ����
					dataGeted=false;
					LoginWindow.watchThread();
					flushDataXW();
					dataGeted=true;
					
				}else
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "������ϣ��޸�ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	//�޸����ŷ���״̬id
	public void xgFBZTID(final String xwid,final String fbztid)//��ɫID  ��ɫ����
	{
		//�����߳�
		dataGeted=false;
		new Thread()
		{
			public void run()
			{
				//������Ϣ�������
				String msg = XG_FBZTID;//�޸Ľ�ɫ����
				StringBuilder sb = new StringBuilder();
				sb.append(msg);
				sb.append(xwid);
				sb.append("<->");
				sb.append(fbztid);
				sb.append(msg);
				String result =SocketUtil.sendAndGetMsg(sb.toString());
				dataGeted=true;
				if(result.endsWith("ok"))
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "�������ù��ڳɹ���", "��ʾ", JOptionPane.NO_OPTION);
					dataGeted=false;
					LoginWindow.watchThread();
					flushDataXW();
					dataGeted=true;
				}else
				{
					JOptionPane.showMessageDialog(ProgramaManagePanel.this, "������ϣ��޸�ʧ�ܣ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}.start();
		//�����߳�
		LoginWindow.watchThread();
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;  
        // ���ƽ���     ��ʼ����  ��ʼ��ɫ
        g2.setPaint(new GradientPaint(0, 0, C_START,0,  getHeight(), C_END));   
        g2.fillRect(0, 0, getWidth(), getHeight());  
	}
	

	
	
	
}
