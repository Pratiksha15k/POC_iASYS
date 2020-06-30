package com.customannotation.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*@Target(ElementType.TYPE)*///annotation can be applied to class, interface or enumeration
/*@Target(ElementType.FIELD)*///annotation can be applied to field of class
/*@Target(ElementType.CONSTRUCTOR)*///annotation can be applied to constructor of class
/*@Target(ElementType.METHOD)*///annotation can be applied to method of class
/*@Target(ElementType.PARAMETER)*///annotation can be applied to parameters of method or constructor of class
/*@Inherited*///annotation can be applied to subclasses also.
@Target({ElementType.PACKAGE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation{
	String name() /*default("Pratiksha")*/;
	int id() /*default(123)*/;
	String message();
}

/*Java Reflection makes it possible to inspect classes, interfaces, fields 
and methods at runtime, without knowing the names of the classes, methods etc. at compile time. */