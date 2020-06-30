package com.customannotation.example;
import java.lang.reflect.Method;

public class UseAnnotation {
	public static void main(String args[]){
		MyAnnotation myAnnotation = new MyAnnotation();
		//Method methods[] = MyAnnotation.class.getMethods();
		Method method[] = MyAnnotation.class.getDeclaredMethods();
		/*Method methods [] = myAnnotation.getClass().getDeclaredMethods(); another way to get declared methods*/
		try{
			for(Method methods : method){
				if(methods.getParameterCount() == 1){
					String message = methods.getAnnotation(CustomAnnotation.class).message();
					methods.invoke(myAnnotation, message);
				}else if(methods.getParameterCount() == 2){
					int id = methods.getAnnotation(CustomAnnotation.class).id();
					String name = methods.getAnnotation(CustomAnnotation.class).name();
					methods.invoke(myAnnotation, name, id);
				}
				//System.out.println(methods.getParameterCount());
			}
		}catch(Exception e){
			System.out.println("Exception"+e.getMessage());
		}
		/*for(Method method : methods){
			if(method.getAnnotation(CustomAnnotation.class) != null){
				int id = method.getAnnotation(CustomAnnotation.class).id();
				String name = method.getAnnotation(CustomAnnotation.class).name();
				String message = method.getAnnotation(CustomAnnotation.class).message();
				try {
					method.invoke(myAnnotation, message);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Method Name :"+method.getName());
				System.out.println("Annotations value :"+name+" "+id+" "+message);
			}
		}*/
	}
}
