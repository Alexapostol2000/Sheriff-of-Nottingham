package com.tema1.main;

import com.tema1.goods.GoodsFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private List<Integer> legall;
    private List<Integer> illegal;
    private List<Integer> declared;
    private int bribe;


    @Override
    public String toString() {
        return "Bag{" +
                "legall=" + legall +
                ", illegal=" + illegal +
                ", declared=" + declared +
                ", bribe=" + bribe +
                '}';
    }

    public Bag() {
        this.legall = new ArrayList<>();
        this.illegal = new ArrayList<>();
        this.declared = new ArrayList<>();
    }

    public void setBribe(int bribe) {
        this.bribe = bribe;
    }

    public void setDeclared(List<Integer> declared) {
        this.declared = declared;
    }

    public List<Integer> getDeclared() {
        return declared;
    }

    public List<Integer> getLegall() {
        return legall;
    }

    public List<Integer> getIllegal() {
        return illegal;
    }

    public void clearBag() {
        this.getDeclared().clear();
        this.getLegall().clear();
        this.getIllegal().clear();
    }

    public int getBribe() {
        return bribe;
    }

    public Bag createbagBasic(List<Integer> cards, int money) {
        Bag bag = new Bag();
        GoodsFactory Factory = GoodsFactory.getInstance();
        int max = 0, idx = -1;
        for (int i = 0; i < 10; i++) {
            if (cards.get(i) < 10) {
                if (Collections.frequency(cards, cards.get(i)) > max) {
                    max = Collections.frequency(cards, cards.get(i));
                    idx = cards.get(i);
                }
                if (Collections.frequency(cards, cards.get(i)) == max)
                    if (Factory.getGoodsById(idx).getProfit() < Factory.getGoodsById(cards.get(i)).getProfit()) {
                        idx = cards.get(i);
                    } else if (Factory.getGoodsById(idx).getProfit() == Factory.getGoodsById(cards.get(i)).getProfit()) {
                        if (cards.get(i) > idx)
                            idx = cards.get(i);
                    }
            }
        }
        if (max != 0) {
            for (int i = 0; i < max; i++) {
                if (bag.getLegall().size() < 8)
                    bag.getLegall().add(idx);
                bag.getDeclared().add(idx);
            }
            bag.setBribe(0);
        } else {
            for (int i = 0; i <= 9; i++) {
                if (Factory.getGoodsById(cards.get(i)).getProfit() >= max) {
                    max = Factory.getGoodsById(cards.get(i)).getProfit();
                    idx = i;
                }
            }

            if (money >= Factory.getGoodsById(cards.get(idx)).getPenalty()) {
                bag.illegal.add(cards.get(idx));
                bag.getDeclared().add(0);
                cards.remove(idx);
                bag.setBribe(0);
            }
        }
        return bag;
    }

    public Bag createbagGreedy(List<Integer> cards, int r, int money) {
        GoodsFactory Factory = GoodsFactory.getInstance();
        Bag bag = new Bag();
        r++;
        if (r % 2 != 0) {
            bag = createbagBasic(cards, money);
        } else {
            bag = createbagBasic(cards, money);
            if (bag.getLegall().size() < 8) {
                int max = 0, idx = 0;
                for (int i = 0; i < cards.size(); i++)
                    if (cards.get(i) > 19)
                        if (Factory.getGoodsById(cards.get(i)).getProfit() > max) {
                            max = Factory.getGoodsById(cards.get(i)).getProfit();
                            idx = i;
                        }
                if (max != 0 && money > Factory.getGoodsById(cards.get(idx)).getPenalty()) {
                    bag.getIllegal().add(cards.get(idx));
                    bag.getDeclared().add(0);
                }
            }
        }
        return bag;
    }

    public Bag createbagBribe(List<Integer> cards, int money) {
        Bag bag = new Bag();
        if (money < 5) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        GoodsFactory Factory = GoodsFactory.getInstance();
        int max = 0, idx = -1, penalty = 0, count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) > 19)
                count++;
        }
        //daca nu are ilegale
        if (count == 0) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        //are ilegale
        while (bag.getIllegal().size() != count && bag.getIllegal().size() < 8) {
            max = 0;
            for (int i = 0; i < cards.size(); i++) {
                if (Factory.getGoodsById(cards.get(i)).getProfit() > max && cards.get(i) > 19) {
                    max = Factory.getGoodsById(cards.get(i)).getProfit();
                    idx = i;
                }
            }
            penalty += Factory.getGoodsById(cards.get(idx)).getPenalty();
            if (bag.getIllegal().size() + 1 <= 2 && bag.getBribe() == 0) {
                if (penalty < money) {
                    bag.setBribe(5);
                } else {
                    break;
                }
            }
            if (bag.getIllegal().size() + 1 > 2 && bag.getBribe() == 5) {
                if (penalty < money) {
                    bag.setBribe(10);
                } else {
                    break;
                }
            }
            if (penalty >= money) {
                break;
            } else {
                bag.getIllegal().add(cards.get(idx));
                bag.getDeclared().add(0);
                cards.remove(idx);
            }
        }
        //aici nu are bani sa puna nimic ilegal  in sac dar are ce
        if (bag.getIllegal().size() == 0) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        //aici sau terminat ilegalele sau nu mai are bani sa puna ilegale
        while (count < 8 && cards.size() != 0) {
            max = 0;
            idx = -1;
            for (int i = 0; i < cards.size(); i++) {
                if (Factory.getGoodsById(cards.get(i)).getProfit() > max) {
                    max = Factory.getGoodsById(cards.get(i)).getProfit();
                    idx = i;
                }
                if (Factory.getGoodsById(cards.get(i)).getProfit() == max && cards.get(i) > cards.get(idx)) {
                    idx = i;
                }

            }
            penalty += Factory.getGoodsById(cards.get(idx)).getPenalty();
            if (penalty < money) {
                bag.getLegall().add(cards.get(idx));
                bag.getDeclared().add(0);
                cards.remove(idx);
                count++;
            } else break;
        }
        return bag;
    }
}