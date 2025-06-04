/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2.model;

import pdc_a2.model.Person;

/**
 *
 * @author 64210
 */
public class Customer extends Person {
    
    public Customer(String name) {
        super(name);
    }

    // OOP: polymorphism – overrides abstract method
    @Override
    public String getRole() {
        return "Guest";
    }
    
}
