package com.bn.xwglxt;

import java.util.List;

import com.bn.sjb.PicObject;
import com.bn.util.DBUtil;
import com.bn.util.PicUtils;
import com.bn.util.SocketUtil;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import static com.bn.xwglxt.Constant.path;

import static com.bn.xwglxt.Constant.GET_PICA;

public class ImgAsyncTask extends AsyncTask<String, Bitmap, Bitmap> 
{
	private ImageView imgView;
	private TextView txtView;
	private boolean isTitlePic;
	private String picms;
	private int piclx;

	public ImgAsyncTask(ImageView img) 
	{
		this.imgView = img;
		this.isTitlePic=true;
	}
	
	public ImgAsyncTask(ImageView img,TextView tv) 
	{
		this.imgView = img;
		this.txtView=tv;
		this.isTitlePic=false;
	}

	@Override
	protected Bitmap doInBackground(String... params) 
	{
		//���ָ��ͼƬ��Ϣ
		/*
		 * ���ָ��ͼƬ������ͼƬ������ݱ��浽�˳�Ա������
		 */
		    Bitmap Pic;
			String xwid=params[0];
			int picLX=Integer.parseInt(params[1]);
			
			List<String[]> list=DBUtil.getPic(xwid, picLX);
			if(list!=null)
			{
				String picName=list.get(0)[0];
				picms=list.get(0)[1];
				Pic=BitmapFactory.decodeFile(path+picName);	
				if(Pic!=null)//���ݿ���ͼƬ��Ϣ������û��ͼƬ�ļ�������������
				{
					return Pic;
				}
			}
			
			//System.out.println(path+"·��"+Utils.ExistSDCard());
			// ������Ϣ�������
			String msg = GET_PICA;
			StringBuilder sb = new StringBuilder();
			sb.append(msg);
			sb.append(xwid+"<->");
			sb.append(picLX);
			sb.append(msg);
			PicObject pico=SocketUtil.sendAndGetPic(sb.toString());
			if(pico!=null)
			{
				Pic=BitmapFactory.decodeByteArray(pico.pic, 0, pico.pic.length);
				picms=pico.picMs;
				piclx=pico.picLx;
				publishProgress(Pic);
				//�������ݿ�
				String picName;
				if(this.isTitlePic)
				{
					picName=xwid+"_title.jpg";
				}else
				{
					picName=xwid+"_pic_"+piclx+".jpg";
				}
				PicUtils.saveImage(pico.pic, picName);
				DBUtil.addPic(picms,xwid,picName,piclx);
			}
			return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) 
	{
		if(result!=null)
		{
			if(!isTitlePic)
			{
				this.txtView.setText(picms);
			}
			imgView.setImageBitmap(result);		
		}

		
	}
	
	@Override
	protected void onProgressUpdate(Bitmap... values) 
	{
		if(values[0]!=null)
		{
			if(!isTitlePic)
			{
				this.txtView.setText(picms);
			}
			imgView.setImageBitmap(values[0]);	
		}
			
	}
	

}

