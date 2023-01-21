package com.example.blackjack.value;

public class Card {

    private String mark;
    private int number;

    public Card(String mark, int number){
        this.mark = mark;
        this.number = number;
    }

    public void print(){
        System.out.println(this.mark + "の" + this.number);
    }

    public int checkNumber(){
        return number;
    }

    public String  checkMark(){
        return mark;
    }

}
