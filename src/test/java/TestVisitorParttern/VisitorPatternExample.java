/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestVisitorParttern;

/**
 *
 * @author DELL
 */
public class VisitorPatternExample {

    public static void main(String[] args) throws Exception {
        Book book1 = new BusinessBook();
        Book book2 = new JavaCoreBook();
        Book book3 = new DesignPatternBook();

        Visitor v = new VisitorImpl();
        book1.accept(v);
        book2.accept(v);
        book3.accept(v);
        System.out.println(v.getProgramingBook().getResource());
        
    }
}
