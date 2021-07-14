package com.example.data_structure.structure.ZenRenLian;

public class ZeRenLianMain {

    public static void main(String[] args) {

        int[] arr = {1, 20, 17, 35};

        ZuZhang zuZhang = new ZuZhang();
        Jingli jingLi = new Jingli();
        DongShiZhang dongShiZhang = new DongShiZhang();

        zuZhang.setProcesser(jingLi);
        jingLi.setProcesser(dongShiZhang);

        for (int i : arr) {
            zuZhang.process(i);
        }

    }

}
