package com.example.blackjack.replier;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.lang.reflect.Member;

public class Rule implements Replier{

    private MessageEvent<TextMessageContent> event;

    public Rule(MessageEvent<TextMessageContent> event){
        this.event = event;
    }

    @Override
    public Message reply(){

        return new TextMessage("【ブラックジャック】\n"
                +"開始：ゲームを始める\n"
                +"ヒット：カードを一枚引く\n"
                +"スタンド：結果を表示する");
    }

}
