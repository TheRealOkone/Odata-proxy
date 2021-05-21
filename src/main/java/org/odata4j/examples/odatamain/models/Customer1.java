package org.odata4j.examples.odatamain.models;

public class Customer1 implements Customer {

    private String column1;

    public Customer1(String column1) {
        this.column1 = column1;
    }

    public Customer1() {
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }
}
