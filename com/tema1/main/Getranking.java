package com.tema1.main;

import com.tema1.goods.GoodsFactory;
import com.tema1.goods.LegalGoods;

import java.util.ArrayList;
import java.util.List;

import static com.tema1.common.Constants.BARREL;
import static com.tema1.common.Constants.BEER;
import static com.tema1.common.Constants.BREAD;
import static com.tema1.common.Constants.CHEESE;
import static com.tema1.common.Constants.CHICKEN;
import static com.tema1.common.Constants.MAXIDLEGALL;
import static com.tema1.common.Constants.MINIDILEGALL;
import static com.tema1.common.Constants.MINIMONEY;
import static com.tema1.common.Constants.PEPPER;
import static com.tema1.common.Constants.POTATO;
import static com.tema1.common.Constants.SEAFOOD;
import static com.tema1.common.Constants.SILK;
import static com.tema1.common.Constants.TOMATO;
import static com.tema1.common.Constants.WINE;

public class Getranking {
    private List<Player> players = new ArrayList<>();
    private int n;
    /*constructor*/
    public Getranking(final List<Player> players, final int n) {
        this.players = players;
        this.n = n;
    }
    /*get mony of items*/
    public void getmoneyofstand() {
        Player aux;
        GoodsFactory factory = GoodsFactory.getInstance();
        for (int i = 0; i < n; i++) {
            aux = players.get(i);
            for (int j = 0; j < aux.getStand().size(); j++) {
                if (aux.getStand().get(j) > MINIDILEGALL) {
                    if (aux.getStand().get(j) == SILK) {
                        aux.getStand().add(CHEESE);
                        aux.getStand().add(CHEESE);
                        aux.getStand().add(CHEESE);
                    }
                    if (aux.getStand().get(j) == PEPPER) {
                        aux.getStand().add(CHICKEN);
                        aux.getStand().add(CHICKEN);
                    }
                    if (aux.getStand().get(j) == BARREL) {
                        aux.getStand().add(BREAD);
                        aux.getStand().add(BREAD);
                    }
                    if (aux.getStand().get(j) == BEER) {
                        aux.getStand().add(WINE);
                        aux.getStand().add(WINE);
                        aux.getStand().add(WINE);
                        aux.getStand().add(WINE);
                    }
                    if (aux.getStand().get(j) == SEAFOOD) {
                        aux.getStand().add(TOMATO);
                        aux.getStand().add(TOMATO);
                        aux.getStand().add(POTATO);
                        aux.getStand().add(POTATO);
                        aux.getStand().add(POTATO);
                        aux.getStand().add(CHICKEN);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            aux = players.get(i);
            for (int j = 0; j < aux.getStand().size(); j++) {
                aux.addmoney(factory.getGoodsById(aux.getStand().get(j)).getProfit());
            }
        }
    }
    /*get king and queen bonus*/
    public void getkingqueen() {
        Player aux;
        GoodsFactory factory = GoodsFactory.getInstance();

        for (int j = 0; j < MAXIDLEGALL; j++) {
            int max = 0, idx = 0;
            for (int i = 0; i < n; i++) {
                aux = players.get(i);
                int count = 0;
                for (int k = 0; k < aux.getStand().size(); k++) {
                    if (aux.getStand().get(k) == j) {
                        count++;
                    }
                }
                if (count > max) {
                    max = count;
                    idx = i;
                }
            }

            if (max != 0) {
                aux = players.get(idx);
                aux.addmoney(((LegalGoods) factory.getGoodsById(j)).getKingBonus());
                for (int i = 0; i < aux.getStand().size(); i++) {
                    if (aux.getStand().get(i) == j) {
                        aux.getStand().remove(i);
                        i--;
                    }
                }
            }

        }
        for (int j = 0; j < MAXIDLEGALL; j++) {
            int max = 0, idx = 0;
            for (int i = 0; i < n; i++) {
                aux = players.get(i);
                int count = 0;
                for (int k = 0; k < aux.getStand().size(); k++) {
                    if (aux.getStand().get(k) == j) {
                        count++;
                }
                }
                if (count > max) {
                    max = count;
                    idx = i;
                }
            }

            if (max != 0) {
                aux = players.get(idx);
                aux.addmoney(((LegalGoods) factory.getGoodsById(j)).getQueenBonus());
                for (int i = 0; i < aux.getStand().size(); i++) {
                    if (aux.getStand().get(i) == j) {
                        aux.getStand().remove(i);
                        i--;
                    }
                }
            }

        }

    }
    /*print ranking*/
    public void printrank() {
        Player aux;
        for (int i = 0; i < n; i++) {
            int max = -1, idx = 0;
            for (int j = 0; j < n; j++) {
                aux = players.get(j);
                if (aux.getMoney() > max) {
                    max = aux.getMoney();
                    idx = j;
                }
            }
            System.out.println(players.get(idx).getId() + " "
                    + players.get(idx).getStrategy().toUpperCase()
                    + " " + players.get(idx).getMoney());
            aux = players.get(idx);
            aux.setMoney(MINIMONEY);
        }
    }


}
