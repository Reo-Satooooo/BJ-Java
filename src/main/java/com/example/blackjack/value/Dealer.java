package com.example.blackjack.value;

import java.util.ArrayList;

public class Dealer {

    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private int sum;

    public Dealer(Deck deck){
        this.name = "ディーラー";

        // とりあえずsumに数字を入れていく仕様
        Card card1 = deck.drawCard();
        cards.add(card1);

        Card card2 = deck.drawCard();
        cards.add(card2);

        SumHandCard();

        // ディーラーは自身の手札が17を超えるようにカードを弾き続ける
        while (sum < 17){
            Card additionalCard = deck.drawCard();
            cards.add(additionalCard);
            SumHandCard();
        }

    }

    private void SumHandCard(){
        sum = 0;
        for (Card card : cards){
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

    public String getName(){
        return name;
    }

    public ArrayList<Card> showHandCard(){
        return cards;
    }

    public int getSum(){
        return sum;
    }

}
