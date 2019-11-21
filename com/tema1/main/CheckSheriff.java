package com.tema1.main;

import com.tema1.goods.GoodsFactory;

import java.util.List;

import static com.tema1.common.Constants.MINMONEY;

public class CheckSheriff {
    private Player sherrif, player;
    private List<Integer> cards;
    /*construct*/
    public CheckSheriff(final Player sherrif, final Player player, final List<Integer> cards) {
        this.sherrif = sherrif;
        this.player = player;
        this.cards = cards;
    }

    /* verify sherrif basic*/
    public void verifyBasic() {
        GoodsFactory factory = GoodsFactory.getInstance();
        if (sherrif.getMoney() >= MINMONEY) {
            int size = player.getBag().getIllegal().size();
            if (size != 0) {
                if (player.getBag().getBribe() > 0) {
                    player.addpanality(player.getBag().getBribe());
                }
                for (int i = 0; i < size; i++) {
                    sherrif.addmoney(
                            factory.getGoodsById(
                                    player.getBag().getIllegal().get(0)).getPenalty());
                    player.addpanality(
                            factory.getGoodsById(
                                    player.getBag().getIllegal().get(0)).getPenalty());
                    cards.add(player.getBag().getIllegal().get(0));
                    player.getBag().getIllegal().remove(0);
                }
                int n = player.getBag().getLegall().size();
                for (int j = 0; j < n; j++) {
                    int poz = -1, m = player.getBag().getDeclared().size(), pen;
                    pen = factory.getGoodsById(
                            player.getBag().getLegall().get(0)).getPenalty();
                    for (int k = 0; k < m; k++) {
                        if (player.getBag().getDeclared().get(k).equals(
                                player.getBag().getLegall().get(0))) {
                            poz = k;
                        }
                    }
                    if (poz != -1) {
                        player.stand.add(player.getBag().getLegall().get(0));
                        player.getBag().getLegall().remove(0);
                        player.getBag().getDeclared().remove(poz);
                    } else {
                        player.getBag().getLegall().remove(0);
                        player.addpanality(pen);
                        sherrif.addmoney(pen);
                    }
                }
                if (player.getBag().getBribe() != 0) {
                    player.addmoney(player.getBag().getBribe());
                    player.getBag().setBribe(0);
                }
            } else {
                int n = player.getBag().getLegall().size();
                for (int j = 0; j < n; j++) {
                    int poz = -1, m = player.getBag().getDeclared().size();
                    for (int k = 0; k < m; k++) {
                        if (player.getBag().getDeclared().get(k)
                                == player.getBag().getLegall().get(0)) {
                            poz = k;
                        }
                    }
                    if (poz != -1) {
                        player.stand.add(player.getBag().getLegall().get(0));
                        player.addmoney(
                                factory.getGoodsById(
                                        player.getBag().getLegall().get(0)).getPenalty());
                        sherrif.addpanality(
                                factory.getGoodsById(
                                        player.getBag().getLegall().get(0)).getPenalty());
                        player.getBag().getLegall().remove(0);
                        player.getBag().getDeclared().remove(poz);
                    } else {
                        player.addpanality(
                                factory.getGoodsById(
                                        player.getBag().getLegall().get(0)).getPenalty());
                        cards.add(player.getBag().getLegall().get(0));
                        player.getBag().getLegall().remove(0);
                    }


                }
            }
        } else {
            int n = player.getBag().getLegall().size();
            for (int j = 0; j < n; j++) {
                player.stand.add(player.getBag().getLegall().get(0));
                player.getBag().getLegall().remove(0);
            }
            n = player.getBag().getIllegal().size();
            for (int j = 0; j < n; j++) {
                player.stand.add(player.getBag().getIllegal().get(0));
                player.getBag().getIllegal().remove(0);
            }
            if (player.getBag().getBribe() != 0) {
                player.getBag().setBribe(0);
            }
        }
        player.getBag().getDeclared().clear();
    }

    /*verify sherrif greedy*/
    public void verifyGreedy() {
        GoodsFactory factory = GoodsFactory.getInstance();
        if (player.getBag().getBribe() != 0) {
            sherrif.addmoney(player.getBag().getBribe());
            player.addpanality(player.getBag().getBribe());
            player.getBag().setBribe(0);
            int n = player.getBag().getLegall().size();
            for (int j = 0; j < n; j++) {
                player.stand.add(player.getBag().getLegall().get(0));
                player.getBag().getLegall().remove(0);
            }
            n = player.getBag().getIllegal().size();
            for (int j = 0; j < n; j++) {
                player.stand.add(player.getBag().getIllegal().get(0));
                player.getBag().getIllegal().remove(0);
            }
        } else {
            verifyBasic();
        }
    }

    /*verify sherrif bribed*/
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public void verifybribe(final int idx, final int r, final int n) {
        GoodsFactory factory = GoodsFactory.getInstance();
        if (idx == r - 1) {
            verifyBasic();
        }
        if (r == 0) {
            if (idx == n) {
                verifyBasic();
        }
        }
        if (r == n) {
            if (idx == 0) {
                verifyBasic();
            }
        }
        if (idx == r + 1) {
            verifyBasic();
        }
        if (player.getBag().getBribe() != 0) {
            sherrif.addmoney(player.getBag().getBribe());
            player.addpanality(player.getBag().getBribe());
            player.getBag().setBribe(0);
        }
        int m = player.getBag().getLegall().size();
        for (int j = 0; j < m; j++) {
            player.stand.add(player.getBag().getLegall().get(0));
            player.addmoney(
                    factory.getGoodsById(player.getBag().getLegall().get(0)).getPenalty());
            sherrif.addpanality(
                    factory.getGoodsById(player.getBag().getLegall().get(0)).getPenalty());
            player.getBag().getLegall().remove(0);
        }

    }
}
