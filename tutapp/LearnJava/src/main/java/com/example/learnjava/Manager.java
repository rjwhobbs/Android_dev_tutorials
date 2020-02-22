package com.example.learnjava;

public class Manager extends Employee {

    @Override
    public double getAnnualSalary() {
        return super.getAnnualSalary() + 10000;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id + 222);
    }


    @Override
    public String toString() {
        return this.getFirstName() + ", "
                + this.getId() + ", "
                + this.getAnnualSalary();
    }
}
