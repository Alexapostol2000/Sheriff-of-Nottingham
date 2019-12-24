package com.tema1.main;

import java.util.ArrayList;
import java.util.List;

public class Getcards {
    List<Integer> cards;

    public Getcards(List<Integer> cards) {
        this.cards = cards;
    }

    public List<Integer> sharecards() {
        List<Integer> auxcards = new ArrayList<>();
        for (int h = 0; h < 10; h++) {

            auxcards.add(cards.get(h));

        }
        return auxcards;
    }


}
