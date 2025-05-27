/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

/**
 *
 * @author 64210
 * This is an abstract class for a person
 */
public abstract class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    // OOP: abstraction – subclass must implement this
    public abstract String getRole();
}
