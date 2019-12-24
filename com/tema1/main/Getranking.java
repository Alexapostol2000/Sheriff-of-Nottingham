package com.tema1.main;

import com.tema1.goods.GoodsFactory;
import com.tema1.goods.LegalGoods;

import java.util.ArrayList;
import java.util.List;

public class Getranking {
    List<Player> players = new ArrayList<>();
    int n;

    public Getranking(List<Player> players, int n) {
        this.players = players;
        this.n = n;
    }

    public void getmoneyofstand() {
        Player aux;
        GoodsFactory Factory = GoodsFactory.getInstance();

        //adaugare bonus pe carti ilegale
        for (int i = 0; i < n; i++) {
            aux = players.get(i);
            for (int j = 0; j < aux.getStand().size(); j++) {
                if (aux.getStand().get(j) > 19) {
                    if (aux.getStand().get(j) == 20) {
                        aux.getStand().add(1);
                        aux.getStand().add(1);
                        aux.getStand().add(1);
                    }
                    if (aux.getStand().get(j) == 21) {
                        aux.getStand().add(3);
                        aux.getStand().add(3);
                    }
                    if (aux.getStand().get(j) == 22) {
                        aux.getStand().add(2);
                        aux.getStand().add(2);
                    }
                    if (aux.getStand().get(j) == 23) {
                        aux.getStand().add(7);
                        aux.getStand().add(7);
                        aux.getStand().add(7);
                        aux.getStand().add(7);
                    }
                    if (aux.getStand().get(j) == 24) {
                        aux.getStand().add(4);
                        aux.getStand().add(4);
                        aux.getStand().add(6);
                        aux.getStand().add(6);
                        aux.getStand().add(6);
                        aux.getStand().add(3);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            aux = players.get(i);
            for (int j = 0; j < aux.getStand().size(); j++) {
                aux.addmoney(Factory.getGoodsById(aux.getStand().get(j)).getProfit());
            }
        }
    }

    public void getkingqueen() {
        Player aux;
        GoodsFactory Factory = GoodsFactory.getInstance();

        for (int j = 0; j < 10; j++) {
            int max = 0, idx = 0;
            for (int i = 0; i < n; i++) {
                aux = players.get(i);
                int count = 0;
                for (int k = 0; k < aux.getStand().size(); k++) {
                    if (aux.getStand().get(k) == j)
                        count++;
                }
                if (count > max) {
                    max = count;
                    idx = i;
                }
            }

            if (max != 0) {
                aux = players.get(idx);
                aux.addmoney(((LegalGoods) Factory.getGoodsById(j)).getKingBonus());
                for (int i = 0; i < aux.getStand().size(); i++) {
                    if (aux.getStand().get(i) == j) {
                        aux.getStand().remove(i);
                        i--;
                    }
                }
            }

        }
        for (int j = 0; j < 10; j++) {
            int max = 0, idx = 0;
            for (int i = 0; i < n; i++) {
                aux = players.get(i);
                int count = 0;
                for (int k = 0; k < aux.getStand().size(); k++) {
                    if (aux.getStand().get(k) == j)
                        count++;
                }
                if (count > max) {
                    max = count;
                    idx = i;
                }
            }

            if (max != 0) {
                aux = players.get(idx);
                aux.addmoney(((LegalGoods) Factory.getGoodsById(j)).getQueenBonus());
                for (int i = 0; i < aux.getStand().size(); i++) {
                    if (aux.getStand().get(i) == j) {
                        aux.getStand().remove(i);
                        i--;
                    }
                }
            }

        }

    }

    public void Printrank() {
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
            System.out.println(players.get(idx).getId() + " " + players.get(idx).getStrategy().toUpperCase() + " " + players.get(idx).getMoney());
            aux = players.get(idx);
            aux.setMoney(-2);
        }
    }


}
