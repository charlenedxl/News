package com.bn.jm.RolePermissions;

import javax.swing.table.AbstractTableModel;
/*
 * ��ɫ��ģ��
 */
@SuppressWarnings({"serial"})
public class RoleTableModel extends AbstractTableModel
{

	//ָ����Ԫ���ֵ�Ƿ�ı�
	boolean isCellChanged = false;
	
	//����༭������
	int lastEditRow ;
	RolePermissionsPanel rolePermissionsPn;
	
	public RoleTableModel(RolePermissionsPanel rolePermissionsPn)
	{
		this.rolePermissionsPn=rolePermissionsPn;
	}
	//�������
	@Override
	public int getColumnCount() {
		return 2;
	}

	//�������
	@Override
	public int getRowCount() {
		return rolePermissionsPn.tableDataJS.size();
	}

	//���ĳ��Ԫ���ֵ
	@Override
	public Object getValueAt(int r, int c) {
		if(rolePermissionsPn.tableDataJS.get(r)[c]!=null)
		return rolePermissionsPn.tableDataJS.get(r)[c];
		return "";
	}

	//���ÿһ�е�����
	@Override
	public Class<?> getColumnClass(int arg0) {
		return rolePermissionsPn.typeArrayJS[arg0];
	}

	//���ÿһ�е���ͷ
	@Override
	public String getColumnName(int arg0) {
		return rolePermissionsPn.headJS[arg0];
	}

	//�����Ƿ�ɱ༭
	@Override
	public boolean isCellEditable(int r, int c) {
		if(c==0)return false;
		return true;
	}

	//����ĳһ��Ԫ���ֵ   ����ڵ�ֵ���ʱ�����
	@Override
	public void setValueAt(Object value, int r, int c)
	{
		rolePermissionsPn.lastEditRowInRole=r;
		String[] s = new String[2];
		s[0]=rolePermissionsPn.tableDataJS.get(r)[0];
		s[1]=value.toString();
		rolePermissionsPn.tableDataJS.set(r,s);
		String newData = rolePermissionsPn.tableDataJS.get(r)[c];
		String oldData = rolePermissionsPn.origindataJS.get(r)[c];
		if(!newData.equals(oldData))
		{
			rolePermissionsPn.isDataChanged=true;
		}
	}
}
