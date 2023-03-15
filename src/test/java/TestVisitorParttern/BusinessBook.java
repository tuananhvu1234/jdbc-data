/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestVisitorParttern;

/**
 *
 * @author DELL
 */
public class BusinessBook implements Book {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String getPublisher() {
        return "The publisher of business book";
    }
}
