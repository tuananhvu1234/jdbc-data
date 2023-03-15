/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noproject.jdbc;

/**
 *
 * @author DELL
 */
@TestAnnotationType(TestType.CLASS)
public class TestClass {

    @TestAnnotationType(TestType.FIELD)
    public int testField;

    TestType type;

    public TestClass(TestType type) {
        this.type = type;
    }

    public int getTestField() {
        return testField;
    }

    @TestAnnotationType({TestType.FULL})
    public void hello() throws SecurityException, NoSuchMethodException {
        TestAnnotationType t = getClass().getMethod("hello").getAnnotation(TestAnnotationType.class);
        System.out.println(t.value()[0].isSupported());

//        if (TestType.TWO == t.value()[0]) {
//            System.out.println("hello");
//        } else {
//            System.out.println("bye");
//        }
//        System.out.println(TestType.values()[1].isSupported());
//        System.out.println(TestType.values()[2]);
    }
}
