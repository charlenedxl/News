package com.bn.xwglxt;

import java.io.File;
import android.os.Environment;

public class Constant 
{
	// ****************************�������������**************************************
	public static final int SERVER_PORT = 31418; // �������˿�
	public static final String SERVER_IP = "192.168.20.250"; // ������ip
	public static boolean dataGeted = false;// �Ƿ�������������ݣ�ͬʱ�ǵȴ��Ի���Ŀ���
	public static String path = Environment.getExternalStorageDirectory()
			.toString() + File.separatorChar + "xwglpic" + File.separator;// ͼƬ·��
	public static String PATH = Environment.getExternalStorageDirectory()
			.toString() + File.separatorChar + "xwglpic";// ͼƬ�ļ���·��
	// ****************************����������**************************************
	public static final String GET_LMA = "<#GET_LMA#>";// �����Ŀ
	public static final String GET_LM_NEWSA = "<#GET_LM_NEWSA#>";// ��Ŀ�����������б�
	public static final String GET_PICA = "<#GET_PICA#>";// ���ͼƬ
	public static final String GET_NEWA = "<#GET_NEWA#>";// �������
}
