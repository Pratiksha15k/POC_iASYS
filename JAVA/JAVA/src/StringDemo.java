
public class StringDemo {
	public static void main(String args[ ]){
		String str1 = new String("abc");
		String str2 = new String("abc");
		String str3 = "abc";
		if(str1 == str3)
			System.out.println("true");
		else
			System.out.println("false");
		
		if(str1.equals(str3))
			System.out.println("trueee");
		else
			System.out.println("falseee");
	}
}
