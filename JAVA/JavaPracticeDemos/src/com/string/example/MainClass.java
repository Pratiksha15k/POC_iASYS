package com.string.example;

/*class A
{
    String s = "Class A";
}
 
class B extends A
{
    String s = "Class B";
 
    {
        System.out.println(super.s);
    }
}
 
class C extends B
{
    String s = "Class C";
 
    {
        System.out.println(super.s);
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        C c = new C();
 
        System.out.println(c.s);
    }
}*/


class A
{
    static
    {
        System.out.println("THIRD");
    }
}
 
class B extends A
{
    static
    {
        System.out.println("SECOND");
    }
}
 
class C extends B
{
    static
    {
        System.out.println("FIRST");
    }
}
 
public class MainClass
{
    public static void main(String[] args)
    {
        C c = new C();
    }
}