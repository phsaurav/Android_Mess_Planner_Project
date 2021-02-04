package com.example.don.myapplication;

public class adapter_class {

    private String name;
    private int initial;

    public adapter_class(String name, int initial){

        this.setName(name);
        this.setInitial(initial);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }


}
