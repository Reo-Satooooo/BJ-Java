package com.example.blackjack.value;

import com.example.blackjack.repository.PlayerCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private int sum;

    public Player(String name, Deck deck){
        this.name = name;

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

    public Card DrawAddCard(Deck deck){
        Card additionalCard = deck.drawCard();
        cards.add(additionalCard);
        SumHandCard();
        return additionalCard;
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
