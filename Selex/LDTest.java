import java.util.*;
import java.lang.*;



public class LDTest{
	

		
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String a="123", b="312";

		Distance dl=new Distance();
		
		System.out.println("DL("+a+","+b+")="+dl.LD(a,b));
		System.out.println("DH("+a+","+b+")="+dl.HD(a,b));
	}

