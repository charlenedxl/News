package com.bn.jm.BasePermissions;

import static com.bn.core.Constant.GET_JBQX;
import static com.bn.core.Constant.C_END;
import static com.bn.core.Constant.C_START;
import static com.bn.core.Constant.subtitle;


import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Vector;

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

import com.bn.jm.TableHeader.GroupableTableHeader;
import com.bn.jm.TableHeader.GroupableTableHeaderUI;
import com.bn.util.SocketUtil;
/*
 * ����Ȩ�޲鿴
 */
@SuppressWarnings("serial")
public class BasePermissionsPanel extends JPanel
{
	//Ȩ�ޱ��ÿһ�е�����
	@SuppressWarnings("rawtypes")
	Class[] typeArrayQX={Integer.class,String.class};
	//Ȩ�ޱ�ͷ
	String[] headPermissions={"Ȩ��ID","Ȩ������"};
	//��ɫ�������
	Vector<String[]> tableDataPermissions;
	Vector<String[]> origindataPermissions;
    //�Ӷ���ı������ģ��
	BasePermissionsTableModel tmPermissions;
	
	
	//Ȩ�ޱ�ͷ
	JLabel jlQXTableHead = new JLabel("����Ȩ�ޱ�");
	//Ȩ�ޱ��
	JTable jtPermissions = new JTable()
	{
        @Override
        protected JTableHeader createDefaultTableHeader() 
        {
            return new GroupableTableHeader(columnModel);
        }
    };
    //��ű���JScrollPane
	JScrollPane jspPermissions = new JScrollPane(jtPermissions);
	
	
	public BasePermissionsPanel()
	{
		this.setLayout(null);
		//��ͷ��ǩ
		jlQXTableHead.setBounds(228, 10, 120, 30);
		jlQXTableHead.setFont(subtitle); 
		this.add(jlQXTableHead);
		//���ñ��
		jspPermissions.setBounds(25,40,496,600);
		this.add(jspPermissions);	
	}
	
	public void initTableQX()
	{
		//�����и�
		jtPermissions.setRowHeight(30);
		//����ֻ�ܵ�ѡ
		jtPermissions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//���table��Ԫ�������
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		//���ñ�������ݾ���
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		//���õ�һ�о���
		jtPermissions.setDefaultRenderer(Integer.class, dtcr);
		
		
		//���ñ�ͷ������
		jtPermissions.getTableHeader().setUI(new GroupableTableHeaderUI());
		//��ñ�ͷ
		JTableHeader tableHeader = jtPermissions.getTableHeader();  
		//��ñ�ͷ������
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader .getDefaultRenderer(); 
		//��������
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//����в����ƶ�    
		tableHeader.setReorderingAllowed(false);
		//���ÿһ�е�����
		TableColumn tc0 = jtPermissions.getColumnModel().getColumn(0);
		TableColumn tc1 = jtPermissions.getColumnModel().getColumn(1);
		//����ÿһ�п��
		tc0.setPreferredWidth(60);
		tc1.setPreferredWidth(155);
		//����ÿһ�д�С���ɱ�
		tc0.setResizable(false);
		tc1.setResizable(false);
	}

	public void flushDataJSQX() 
	{
		//������Ϣ�������
		String msg = GET_JBQX;
		StringBuilder sb = new StringBuilder();
		sb.append(msg);
		sb.append(0);
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
			 			int rowCount = list.size();
			 			//���±��ģ��
			 			tmPermissions=new BasePermissionsTableModel(BasePermissionsPanel.this);
			 			//��ʼ���������
			 			tableDataPermissions = new Vector<String[]>();		
			 			//�����е����ݽ���һ����ʱ�ı���  
			 			//������ ������"�޸�"��ťʱ �жϵ�ǰ��������� ��  ������ݵ����� �Ƿ���ͬ  ��ͬ�Ļ� ��ʾ�����Ϣ
			 			origindataPermissions =  new Vector<String[]>();	
			 			//ͨ��ѭ�������ݿ�������
			 			for(int i=0;i<rowCount;i++)
			 			{
			 				tableDataPermissions.add(list.get(i));
			 				origindataPermissions.add(list.get(i));
			 			}
			 			//���ñ��ģ��
			 			jtPermissions.setModel(tmPermissions);	
			 			//���±�����
			 			initTableQX();		 			
			    	 }
			     }
			);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;  
        // ���ƽ���     ��ʼ����  ��ʼ��ɫ
        g2.setPaint(new GradientPaint(0, 0, C_START,0,  getHeight(), C_END));   
        g2.fillRect(0, 0, getWidth(), getHeight());  
	}
	
	
	/*
	 * �����ʾ���裺
	 * 1.��JScrollPane�����JTable����Panel�����JScrollPane��
	 * 2.ͨ��flushDataXXX()�����ӷ�����������ݣ��������ݷ���XXXTableModel�У�
	 *   ���JTable.setModel(XXXTableModel);
	 * 3.ͨ��initTableQX()�������±�����
	 * 
	 */
	
	

}
