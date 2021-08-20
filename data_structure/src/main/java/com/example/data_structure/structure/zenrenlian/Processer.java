package com.example.data_structure.structure.zenrenlian;

public abstract class Processer {

    public Processer processer;

    public abstract void process(int i);


    public abstract String getName();


    public void setProcesser(Processer processer) {
        this.processer = processer;
    }

}
