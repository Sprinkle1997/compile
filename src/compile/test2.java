package compile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//词法分析、符号表

public class test2 {
	public int ch;
	public int retainWordN;		//保留字状态	1
	public int operationSignN;	//运算符状态	2
	public int boundarySignN;	//界符状态	3
								//数字		4
	public StringBuffer strToken =new StringBuffer();	//存放构成单词符号的字符串
	public String [] retainWord=new String[] {"auto","break","case","char","const","continue","default","do",
			"double","else","enum","extern","float","for","goto","if","int","long","register","return","short",
			"signed","sizeof","static","struct","switch","typeof","union","unsigned","void","volatile","while"
			};//保留字
	
	//运算符
	public String [] operationSign=new String[] {"+=","++","--","*=","-=","/=","==","=",
												"&","&&","|","||","+","-","*","/"};
	
	//界符
	public String [] boundarySign=new  String[] {"(",")","[","]","{","}","<",">","#",",",";",	":·"};
	
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
	
	//判断是否是空格、回车、换行
	public boolean IsBC(int ch) {
		if(ch==32||ch==10||ch==13)
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
				return 4;
			}
		}
		return 9;
	}
	
	public int  Operation() {
		for(int i=0;i<operationSign.length;i++)
		{
			if(strToken.toString().equals(operationSign[i]))
			{
				return 2;
			}
		}
		return 9;
	}
	
	public int Boundary() {
		for(int i=0;i<boundarySign.length;i++)
		{
			if(strToken.toString().equals(boundarySign[i]))
			{
				return 3;
			}
		}
		return 9;
	}
	
	//
	public void Retract() {
		retainWordN =Reserve();
		operationSignN =Operation();
		boundarySignN=Boundary();
		if(retainWordN ==1)
		{
			System.out.println("保留字   "+strToken);
		}else if(operationSignN ==2)
		{
			System.out.println("运算符   "+strToken);
		}else if(boundarySignN ==3)
		{
			System.out.println("界符   "+strToken);
		}else if(retainWordN ==4)
		{
			System.out.println("数字   "+strToken);
		}else {
//			System.out.println("字母   "+strToken);
		}
		strToken.delete(0,strToken.length());
	}
	
	//读取文件
	public void scanner() {
		BufferedReader br;
		try {
			br=new BufferedReader(new FileReader("/Users/lushichen/test.txt"));
			while((ch=br.read()) !=-1) {
//			System.out.println("======"+(char)ch);
				if(IsBC(ch)==false)		//判断空格、回车、换行
				{
					if(IsLetter())		//判断是否字母
					{
						if(IsLetter()==true || IsDigit()==true)
						{
							
							Concat((char)ch);
						}
//						Retract();
					}else if(IsDigit()==true)
					{
						Concat((char)ch);
					}else {
						Retract();
					}	
				}
				else if(ch==10||ch==13||ch==32) 
				{
					continue;
				}
				else {
//					Retract();

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
		test t=new test();
		t.scanner();
	}
}

