/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package testannotation;

/**
 *
 * @author DELL
 */
public enum TestType {

    /**
     *
     */
    CLASS("class", false, false),
    FIELD("field", false, true),
    FULL("full", false, false),
    ONE("one", false, true),
    TWO("two", false, true);

    private final String name;
    private final boolean commercial;
    private final boolean supprted;

    private TestType(String name, boolean commercial, boolean supprted) {
        this.name = name;
        this.commercial = commercial;
        this.supprted = supprted;
    }

    public final boolean isSupported() {
        return supprted;
    }

    public String getName() {
        return name;
    }
}
