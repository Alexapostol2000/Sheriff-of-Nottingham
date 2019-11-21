package com.tema1.main;

import com.tema1.goods.GoodsFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tema1.common.Constants.MAXIDLEGALL;
import static com.tema1.common.Constants.MINIDILEGALL;
import static com.tema1.common.Constants.MINBRIBE;
import static com.tema1.common.Constants.MAXBRIBE;
import static com.tema1.common.Constants.MAXCAPACITYBAG;

public class Bag {
    private List<Integer> legall;
    private List<Integer> illegal;
    private List<Integer> declared;
    private int bribe;

    /*init bag*/
    public Bag() {
        this.legall = new ArrayList<>();
        this.illegal = new ArrayList<>();
        this.declared = new ArrayList<>();
    }

    /*print bag*/
    @Override
    public String toString() {
        return "Bag{"
                + "legall=" + legall
                + ", illegal=" + illegal
                + ", declared=" + declared
                + ", bribe=" + bribe
                + '}';
    }

    /*return declared cards*/
    public List<Integer> getDeclared() {
        return declared;
    }

    /*set declared cards*/
    public void setDeclared(final List<Integer> declared) {
        this.declared = declared;
    }

    /*return legall list cards*/
    public List<Integer> getLegall() {
        return legall;
    }

    /*return ilegall list cards*/
    public List<Integer> getIllegal() {
        return illegal;
    }

    /*clear all in bag*/
    public void clearBag() {
        this.getDeclared().clear();
        this.getLegall().clear();
        this.getIllegal().clear();
    }

    /*return bribe */
    public int getBribe() {
        return bribe;
    }

    /*set bribe */
    public void setBribe(final int bribe) {
        this.bribe = bribe;
    }

    /*crate basic bag*/
    public Bag createbagBasic(final List<Integer> cards, final int money) {
        Bag bag = new Bag();
        GoodsFactory factory = GoodsFactory.getInstance();
        List<Integer> auxcards = new ArrayList<>();
        auxcards = cards;
        int max = 0, idx = -1;
        //Serch card with maximum frequency
        for (int i = 0; i < MAXIDLEGALL; i++) {
            if (auxcards.get(i) < MAXIDLEGALL) {
                if (Collections.frequency(auxcards, auxcards.get(i)) > max) {
                    max = Collections.frequency(auxcards, auxcards.get(i));
                    idx = auxcards.get(i);
                }
                if (Collections.frequency(auxcards, auxcards.get(i)) == max) {
                    if (factory.getGoodsById(idx).getProfit()
                            < factory.getGoodsById(auxcards.get(i)).getProfit()) {
                        idx = auxcards.get(i);
                    } else if (factory.getGoodsById(idx).getProfit()
                            == factory.getGoodsById(auxcards.get(i)).getProfit()) {
                        if (auxcards.get(i) > idx) {
                            idx = auxcards.get(i);
                        }
                    }
                }
            }
        }
        //has legal cards and add them to bag
        if (max != 0) {
            for (int i = 0; i < max; i++) {
                if (bag.getLegall().size() < MAXCAPACITYBAG) {
                    bag.getLegall().add(idx);
                    bag.getDeclared().add(idx);
                }
            }
            bag.setBribe(0);
        } /*has not legall item and add one illegal item*/
        else {
            for (int i = 0; i < MAXIDLEGALL; i++) {
                if (factory.getGoodsById(auxcards.get(i)).getProfit() >= max) {
                    max = factory.getGoodsById(auxcards.get(i)).getProfit();
                    idx = i;
                }
            }
            if (money >= factory.getGoodsById(auxcards.get(idx)).getPenalty()) {
                bag.illegal.add(auxcards.get(idx));
                bag.getDeclared().add(0);
                auxcards.remove(idx);
                bag.setBribe(0);
            }
        }
        return bag;
    }
    /*create bag greedy*/
    public Bag createbagGreedy(List<Integer> cards, final int r, final int money) {
        GoodsFactory factory = GoodsFactory.getInstance();
        Bag bag = new Bag();
        if (r % 2 != 0) {
            bag = createbagBasic(cards, money);
        } else {
            bag = createbagBasic(cards, money);
            if (bag.getLegall().size() < MAXCAPACITYBAG) {
                int max = 0, idx = 0;
                for (int i = 0; i < cards.size(); i++) {
                    if (cards.get(i) > MINIDILEGALL) {
                        if (factory.getGoodsById(cards.get(i)).getProfit() > max) {
                            max = factory.getGoodsById(cards.get(i)).getProfit();
                            idx = i;
                        }
                    }
                }
                if (max != 0 && money > factory.getGoodsById(cards.get(idx)).getPenalty()) {
                    bag.getIllegal().add(cards.get(idx));
                    bag.getDeclared().add(0);
                }
            }
        }
        return bag;
    }

    /*creeaza bribed bag*/
    public Bag createbagBribe(List<Integer> cards, final int money) {
        Bag bag = new Bag();
        if (money <= MINBRIBE) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        GoodsFactory factory = GoodsFactory.getInstance();
        int max = 0, idx = -1, penalty = 0, count = 0;
        for (Integer card : cards) {
            if (card > MINIDILEGALL) {
                count++;
            }
        }
        //not illegal items
        if (count == 0) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        //exist ilegall items
        while (bag.getIllegal().size() != count && bag.getIllegal().size() < MAXCAPACITYBAG) {
            max = 0;
            for (int i = 0; i < cards.size(); i++) {
                if (factory.getGoodsById(cards.get(i)).getProfit() > max && cards.get(i)
                        > MINIDILEGALL) {
                    max = factory.getGoodsById(cards.get(i)).getProfit();
                    idx = i;
                }
            }
            penalty += factory.getGoodsById(cards.get(idx)).getPenalty();
            if (bag.getIllegal().size() + 1 <= 2 && bag.getBribe() == 0) {
                if (penalty < money) {
                    bag.setBribe(MINBRIBE);
                } else {
                    break;
                }
            }
            if (bag.getIllegal().size() + 1 > 2 && bag.getBribe() == MINBRIBE) {
                if (penalty < money) {
                    bag.setBribe(MAXBRIBE);
                } else {
                    break;
                }
            }
            if (penalty + 1 >= money) {
                penalty -= factory.getGoodsById(cards.get(idx)).getPenalty();
                break;
            } else {
                bag.getIllegal().add(cards.get(idx));
                bag.getDeclared().add(0);
                cards.remove(idx);
            }
        }
        //not money
        if (bag.getIllegal().size() == 0) {
            bag = createbagBasic(cards, money);
            return bag;
        }
        //he has no more illegals or money
        while (count < MAXCAPACITYBAG && cards.size() != 0) {
            max = 0;
            idx = -1;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) < MAXIDLEGALL
                        && factory.getGoodsById(cards.get(i)).getProfit() > max) {
                    max = factory.getGoodsById(cards.get(i)).getProfit();
                    idx = i;
                }
                if (cards.get(i) < MAXIDLEGALL && factory.getGoodsById(cards.get(i)).getProfit()
                        == max && cards.get(i) > cards.get(idx)) {
                    idx = i;
                }
            }
            penalty += factory.getGoodsById(cards.get(idx)).getPenalty();
            if (penalty < money) {
                bag.getLegall().add(cards.get(idx));
                bag.getDeclared().add(0);
                cards.remove(idx);
                count++;
            } else {
                break;
            }
        }
        return bag;
    }
}
