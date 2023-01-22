package com.example.blackjack.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class AddCard implements Replier{

    private String text;

    public AddCard(String text){
        this.text = text;
    }

    public Message reply(){
        return new TextMessage(text);
    }

}
