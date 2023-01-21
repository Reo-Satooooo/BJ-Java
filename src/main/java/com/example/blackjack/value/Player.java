package com.example.blackjack.value;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> cards;
    private int sum;

    public Player(String name){
        this.name = name;

        Deck deck = new Deck();

        // とりあえずsumに数字を入れていく仕様
        Card card1 = deck.drawCard();
        cards.add(card1);

        Card card2 = deck.drawCard();
        cards.add(card2);

        SumHandCard();

        // ToDo:cardsの中身をDBに記録する
    }

    private void SumHandCard(){
        sum = 0;
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (Card card : cardArrayList){
            int cardNum = card.checkNumber();
            if(cardNum >= 11){
                cardNum = 10;
                sum += cardNum;
            }
            else {
                sum += cardNum;
            }
        }
    }

}
