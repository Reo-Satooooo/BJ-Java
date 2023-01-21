package com.example.blackjack.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cardArrayList;

    public Deck(){
        String[] marks = new String[]{"ダイヤ","スペード","ハート","クローバー"};
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (int i = 1; i <= marks.length; i++){
            String mark = marks[i];
            for (int j = 1; j <= 13; j++){
                Card card = new Card(mark,j);
                cardArrayList.add(card);
            }
        }

        Collections.shuffle(cardArrayList);

        this.cardArrayList = cardArrayList;
    }

    public void printCardList(){
        for (int i = 0; i < cardArrayList.size(); i++){
            Card card = cardArrayList.get(i);
            card.print();
        }
    }

    public Card drawCard(){
        int remainCard = checkCardRemain();
        Random rand = new Random(remainCard);
        int num = rand.nextInt();
        Card card = cardArrayList.get(num);
        cardArrayList.remove(num);
        return card;
    }

    public int checkCardRemain(){
        int remainCardNum = cardArrayList.size();
        return remainCardNum;
    }

}
