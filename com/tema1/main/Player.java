package com.tema1.main;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    public List<Integer> cards;
    public int money;
    public boolean serif;
    public List<Integer> stand;
    public String strategy;
    public Bag bag;

    public Player() {
        this.serif = false;
        this.money = 80;
        this.stand = new ArrayList<>();

    }

    public Player(int id, List<Integer> cards, int money, boolean serif, List<Integer> deck, String strategy) {
        this.id = id;
        this.cards = cards;
        this.money = money;
        this.serif = serif;
        this.stand = deck;
        this.strategy = strategy;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public void setSerif(boolean serif) {
        this.serif = serif;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public int getMoney() {
        return money;
    }

    public boolean isSerif() {
        return serif;
    }

    public List<Integer> getStand() {
        return stand;
    }

    public String getStrategy() {
        return strategy;
    }

    public Bag getBag() {
        return bag;
    }

    public void setStand(List<Integer> stand) {
        this.stand = stand;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addmoney(int sum) {
        this.money += sum;
    }

    public void addpanality(int sum) {
        this.money -= sum;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Player{"
                + "id=" + id
                + ", cards=" + cards
                + ", money=" + money
                + ", serif=" + serif
                + ", stand=" + stand
                + ", strategy='"
                + strategy + '\''
                + ", bag=" + bag
                + '}';
    }
}
