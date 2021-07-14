package com.example.data_structure.structure.ZenRenLian;

public class ZuZhang extends Processer {

    @Override
    public void process(int i) {
        if (i > 0 && i < 10) {
            System.out.println(getName() + " process " + i);
        } else {
            if (processer != null) {
                processer.process(i);
            }
        }
    }

    @Override
    public String getName() {
        return "Zuzhang";
    }
}
