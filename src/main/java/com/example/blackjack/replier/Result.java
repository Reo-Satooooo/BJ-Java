package com.example.blackjack.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class Result implements Replier{

    private String text;

    public Result(String text){
        this.text = text;
    }

    public Message reply(){
        return new TextMessage(text);
    }

}
