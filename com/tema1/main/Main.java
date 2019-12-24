package com.tema1.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) throws IOException {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        List<Player> players = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Integer> cards = new ArrayList<>();
        name = gameInput.getPlayerNames();
        cards = gameInput.getAssetIds();
        int i;
        for (i = 0; i < name.size(); i++) {
            Player aux;
            aux = new Player();
            aux.setStrategy(name.get(i));
            aux.setId(i);
            players.add(i, aux);
        }
        Player aux, sheriff;
        int n = i;
        Getranking getrank = new Getranking(players, n);
        for (int j = 0; j < gameInput.getRounds(); j++) {
            for (int k = 0; k < n; k++) {
                aux = players.get(k);
                aux.setSerif(true);
                sheriff = aux;
                for (i = 0; i < n; i++) {
                    aux = players.get(i);
                    if (!aux.isSerif()) {
                        List<Integer> auxcards = new ArrayList<>();
                        for (int h = 0; h < 10; h++) {
                            auxcards.add(cards.get(0));
                            cards.remove(0);
                        }
                        aux.setCards(auxcards);
                        aux.bag = new Bag();
                        if (aux.getStrategy().equals("basic"))
                            aux.bag = aux.bag.createbagBasic(aux.getCards(), aux.getMoney());
                        if (aux.getStrategy().equals("greedy"))
                            aux.bag = aux.bag.createbagGreedy(aux.getCards(), j, aux.getMoney());
                        if (aux.getStrategy().equals("bribed"))
                            aux.bag = aux.bag.createbagBribe(aux.getCards(), aux.getMoney());
                    }
                }

                ////To do aici se intampla actiunea////
                for (i = 0; i < n; i++) {
                    aux = players.get(i);
                    if (!aux.isSerif()) {
                        CheckSheriff check = new CheckSheriff(sheriff, aux, cards);
                        if (sheriff.getStrategy().equals("basic"))
                            check.verifyBasic();
                        if (sheriff.getStrategy().equals("greedy"))
                            check.verifyGreedy();
                        if (sheriff.getStrategy().equals("bribed")) {
                            check.verifybribe(i, k, n);
                        }
                    }
                }

                //////////////////////////////////////
                for (i = 0; i < n; i++) {
                    aux = players.get(i);
                    List<Integer> auxcards = new ArrayList<>();
                    auxcards = null;
                    if (aux.getCards() != null)
                        aux.setCards(auxcards);
                }
                aux = players.get(k);
                aux.setSerif(false);
            }
        }
        getrank.getmoneyofstand();
        getrank.getkingqueen();
        getrank.Printrank();
        //TODO implement homework logsic
        FileWriter fw = new FileWriter("output.txt");
        fw.write("mumu");
        fw.close();
    }
}

