/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestVisitorParttern;

/**
 *
 * @author DELL
 */
public class VisitorImpl implements Visitor {

    ProgramingBook pb;

    @Override
    public void visit(BusinessBook a) {
        System.out.println(a.getPublisher());
    }

    @Override
    public void visit(DesignPatternBook w) {
        this.pb = w;
        System.out.println("design parttern : " + w.getResource());
    }

    @Override
    public void visit(JavaCoreBook g) {
        this.pb = g;
        System.out.println("java core : " + g.getResource());
    }

    @Override
    public ProgramingBook getProgramingBook() {
        return pb;
    }
}
