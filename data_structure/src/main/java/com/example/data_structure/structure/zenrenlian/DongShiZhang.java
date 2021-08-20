package com.example.data_structure.structure.zenrenlian;

public class DongShiZhang extends Processer {

    @Override
    public void process(int i) {

        if (i > 20 && i < 30) {
            System.out.println(getName() + " process " + i);
        } else {
            throw new RuntimeException("Invalid request");
        }
    }

    @Override
    public String getName() {
        return "DongShiZhang";
    }
}
