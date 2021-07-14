package com.example.data_structure.structure.ZenRenLian;

public class Jingli extends Processer {

    @Override
    public void process(int i) {

        if (i > 10 && i < 20) {
            System.out.println(getName() + " process " + i);
        } else {
            if (processer != null) {
                processer.process(i);
            }
        }
    }

    @Override
    public String getName() {
        return "jingli";
    }
}
