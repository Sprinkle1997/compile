package compile;
/** *Created By lushichen on 2018年4月11日 上午8:13:44
	*E-mail:lu_shi_chen@163.com 
	*Copyright 2018 lushichen
	*All right reserved.

 *                #####################################################  
 *                #                                                   #  
 *                #                       _oo0oo_                     #  
 *                #                      o8888888o                    #  
 *                #                      88" . "88                    #  
 *                #                      (| -_- |)                    #  
 *                #                      0\  =  /0                    #  
 *                #                    ___/`---'\___                  #  
 *                #                  .' \\|     |# '.                 #  
 *                #                 / \\|||  :  |||# \                #  
 *                #                / _||||| -:- |||||- \              #  
 *                #               |   | \\\  -  #/ |   |              #  
 *                #               | \_|  ''\---/''  |_/ |             #  
 *                #               \  .-\__  '-'  ___/-. /             #  
 *                #             ___'. .'  /--.--\  `. .'___           #  
 *                #          ."" '<  `.___\_<|>_/___.' >' "".         #  
 *                #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #  
 *                #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #  
 *                #     =====`-.____`.___ \_____/___.-`___.-'=====    #  
 *                #                       `=---='                     #  
 *                #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #  
 *                #                                                   #  
 *                #               佛祖保佑         永无BUG              #  
 *                #                                                   #  
 *                #####################################################  
 */
/**
 * @author lushichen
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class cifafenxi {
	public int ch;
	public int code,code2;		//保留字状态
	public StringBuffer strToken =new StringBuffer();	//存放构成单词符号的字符串
	public String [] retainWord=new String[] {"auto","break","case","char","const","continue","default","do",
			"double","else","enum","extern","float","for","goto","if","int","long","register","return","short",
			"signed","sizeof","static","struct","switch","typeof","union","unsigned","void","volatile","while"
			};//保留字
	public String [] operationWord=new String[] {"+","-","*","/","+=","++","--","*=",
													"-=","/=","==","&","&&","|","||"};
	//判断是否是字母
	public boolean IsLetter() {
		if((ch>=65&&ch<=90)||(ch>=97&&ch<=122))
		{
			return true;
		}
		return false;
	}
	
	//判断是否是数字
	public boolean IsDigit() {
		if(ch>=48 && ch<=57)
		{
			return true;
		}
		return false;
	}
	
	//判断是否是空格
	public boolean IsBC(int ch) {
		if(ch==32)
		{
			return true;
		}
		return false;
	}
	
	//连接字符
	public void Concat(char ch) {
		strToken.append(ch);
	}
	
	//判断是否是保留字
	public int Reserve() {
		for(int i=0;i<retainWord.length;i++) {
			if(strToken.toString().equals(retainWord[i])) 
			{
				return 1;
			}
		}
		if(strToken.length() !=0)
		{
			if(strToken.charAt(0)>='0' && strToken.charAt(0)<='9')
			{
				return 3;
			}
		}
		return 2;
	}
	
	public int  Operation() {
		for(int i=0;i<operationWord.length;i++)
		{
			if(strToken.toString().equals(operationWord[i]))
			{
				return 9;
			}
		}
		return 90;
	}
	
	//
	public void Retract() {
		code =Reserve();
		code2 =Operation();
		if(code ==1)
		{
			System.out.println("保留字   "+strToken);
		}else if(code ==2)
		{
			System.out.println("字母   "+strToken);
		}else if(code ==3)
		{
			System.out.println("数字   "+strToken);
		}else if(code2 ==9)
		{
			System.out.println("   "+strToken);
		}
		strToken.delete(0,strToken.length());
	}
	
	//读取文件
	public void scanner() {
		BufferedReader br;
		try {
			br=new BufferedReader(new FileReader("/Users/lushichen/test.txt"));
			while((ch=br.read()) !=-1) {
			//System.out.println("======"+(char)ch);
				if(IsBC(ch)==false)
				{
					if(IsLetter())
					{
						if(IsLetter()==true || IsDigit()==true)
						{
							Concat((char)ch);
						}
					}else if(IsDigit()==true)
					{
						Concat((char)ch);
					
					}
					else if (ch==61) {
						if((strToken.length() !=0) && (strToken.charAt(0)=='='))
						{
							strToken.append((char)ch);
							System.out.println("运算符   "+strToken);
							strToken.delete(0, strToken.length());
						}
						else
						{
							strToken.append((char)ch);
						}
					}else if (ch==43) {
						Retract();
						System.out.println("运算符   "+(char)ch);
					}else if (ch==45) {
						Retract();
						System.out.println("运算符   "+(char)ch);
					}else if (ch==42) {
						Retract();
						System.out.println("运算符   "+(char)ch);
					}else if (ch==47) {
						Retract();
						System.out.println("运算符   "+(char)ch);
					}else if (ch==';') {
						Retract();
						System.out.println("界符  "+(char)ch);
					}else if (ch=='(') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch==')') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch=='{') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch=='}') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch==',') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch=='#') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch=='"') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch=='[') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}else if (ch==']') {
						Retract();
						System.out.println("界符   "+(char)ch);
					}
				}
				else
				{
					Retract();
				}	
			}
		}catch (FileNotFoundException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}
	public static void main(String[]args) {
		cifafenxi cffx=new cifafenxi();
		cffx.scanner();
	}
}
