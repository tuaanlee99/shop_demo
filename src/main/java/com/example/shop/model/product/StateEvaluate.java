package com.example.shop.model.product;

public enum StateEvaluate {
    EVALUATE_1(1),
    EVALUATE_2(2),
    EVALUATE_3(3),
    EVALUATE_4(4),
    EVALUATE_5(5);
    public final int stateEvaluate;
    private StateEvaluate(int i) {
        this.stateEvaluate = i;
    }
}
