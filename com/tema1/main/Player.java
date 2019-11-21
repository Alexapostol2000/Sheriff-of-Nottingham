package com.tema1.main;

import java.util.ArrayList;
import java.util.List;

import static com.tema1.common.Constants.MONEY;

public class Player {
    private List<Integer> cards;
    private int money;
    private boolean serif;
    public List<Integer> stand;
    private String strategy;
    public Bag bag;
    private int id;

    public Player() {
        this.serif = false;
        this.money = MONEY;
        this.stand = new ArrayList<>();

    }
    /*constructor*/
    public Player(final int id, final List<Integer> cards, final int money,
                  final boolean serif, final List<Integer> deck, final String strategy) {
        this.id = id;
        this.cards = cards;
        this.money = money;
        this.serif = serif;
        this.stand = deck;
        this.strategy = strategy;
    }
    /*return player id*/
    public int getId() {
        return id;
    }
    /*set player id*/
    public void setId(final int id) {
        this.id = id;
    }
    /*return player cards*/
    public List<Integer> getCards() {
        return cards;
    }
    /*set player cards*/
    public void setCards(final List<Integer> cards) {
        this.cards = cards;
    }
    /*return player money */
    public int getMoney() {
        return money;
    }
    /*set player money*/
    public void setMoney(final int money) {
        this.money = money;
    }
    /*return is shherif true or false*/
    public boolean isSerif() {
        return serif;
    }
    /*set sherrif status*/
    public void setSerif(final boolean serif) {
        this.serif = serif;
    }
    /*return items of the stand*/
    public List<Integer> getStand() {
        return stand;
    }
    /*set items stand*/
    public void setStand(final List<Integer> stand) {
        this.stand = stand;
    }
    /*return strategy player*/
    public String getStrategy() {
        return strategy;
    }
    /*set strategy player*/
    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }
    /*return bag player*/
    public Bag getBag() {
        return bag;
    }
    /*add money player*/
    public void addmoney(final int sum) {
        this.money += sum;
    }
    /*add penality player*/
    public void addpanality(final int sum) {
        this.money -= sum;
    }

    @Override
    /*print player*/
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
