package compile;
/** *Created By lushichen on 2018年4月25日 下午11:32:26
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
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class test4 {

    private static final StringBuffer strToken = new StringBuffer();    //存放构成单词符号的字符串

    //数字
    private static final String NUMBERS = "0123456789";

    //空格、回车、换行、制表符
    private static final String SPLITS = " \r\n\t";

    //界符
    private static final Set<String> BOUNDARYSIGNS = new HashSet<>(Arrays.asList("(", ")", "[", "]", "{", "}", "<", ">", "#", ",", ";", ":",  "·"));

    //保留字
    private static final Set<String> RETAINWORDS = new HashSet<>(Arrays.asList("auto", "break", "case", "char", "const", "continue", "default", "do",
            "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short",
            "signed", "sizeof", "static", "struct", "switch", "typeof", "union", "unsigned", "void", "volatile", "while"
    ));

    //运算符
    private static final Set<String> OPERATIONSIGNS = new HashSet<>(Arrays.asList("+=", "++", "--", "*=", "-=", "/=", "==",
            "&", "&&", "|", "||", "+", "-", "*","=", "/"));

    //判断是字符串否是数字
    private static boolean strIsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (NUMBERS.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    //判断是否是空格、回车、换行
    private static boolean isSplit(int ch) {
        return SPLITS.contains(String.valueOf((char) ch));//ch == 32 || ch == 10 || ch == 13
    }

    //判断字符串
    private static void judge() {

        if (strToken.length() == 0) return;

        String str = strToken.toString();

        if (BOUNDARYSIGNS.contains(str)) {
            System.out.println("界定符：" + str);
            return;
        }

        if (OPERATIONSIGNS.contains(str)) {
            System.out.println("运算符: " + str);
            return;
        }

        if (RETAINWORDS.contains(str)) {
            System.out.println("保留字: " + str);
            return;
        }

        if (strIsDigit(str)) {
            System.out.println("数字: " + str);
            return;
        }

        System.out.println("普通字符串: " + str);

    }

    //读取文件
    public static void scanner(String file) {
        BufferedReader br;
        int ch;
        char c;

        try {
            br = new BufferedReader(new FileReader(file));
            while ((ch = br.read()) != -1) {
                c = (char) ch;
                if (!isSplit(c))        //判断空格、回车、换行
                {
                    strToken.append(c);
                } else {
                    judge();
                    strToken.delete(0, strToken.length());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static void main(String[] args) {
        scanner("/Users/lushichen/test.txt");
    }
}

