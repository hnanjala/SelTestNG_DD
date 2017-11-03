package tests;

import java.util.regex.Pattern;

public class SampleCase3 {
	
	public static void main(String args[]){
		String orderNum="ORDER (88011634) HAS BEEN UPDATED SUCCESSFULLY";
		String[] orderNumarr=orderNum.split(Pattern.quote("("));
System.out.println("Value:"+orderNumarr[1].substring(0, 8));
	}

}
