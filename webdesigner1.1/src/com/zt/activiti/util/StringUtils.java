package com.zt.activiti.util;

public class StringUtils {

	public static boolean isNotEmpty(String s){
		boolean flag=false;
		if(s!=null && !s.equals("")){
			flag=true;
		}
		return flag;
	}
	
	public static boolean isEmpty(String s){
		boolean flag=false;
		if(s==null || s.equals("")){
			flag=true;
		}
		return flag;
	}
}
