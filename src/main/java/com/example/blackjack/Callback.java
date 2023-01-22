package com.example.blackjack;

import com.example.blackjack.replier.*;
import com.example.blackjack.service.GameManager;
import com.example.blackjack.value.Dealer;
import com.example.blackjack.value.Deck;
import com.example.blackjack.value.Player;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private GameManager gameManager;

    @Autowired
    public Callback(GameManager gameManager){
        this.gameManager = gameManager;
    }

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event){
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        Intent intent =  Intent.whichIntent(text);
        switch (intent){
            // わざわざEnum必要なさそう
            case GAME_START:
                GameStart gameStart = gameManager.gameStart();
                return gameStart.reply();

            case HIT:
                try{
                    AddCard addCard = gameManager.addCard();
                    return addCard.reply();
                }
                catch (RuntimeException e){
                    return new TextMessage("ゲームが開始されていません");
                }

            case STAND:
                try{
                    Result result = gameManager.getResult();
                    return result.reply();
                }
                catch (RuntimeException e){
                    return new TextMessage("ゲームが開始されていません");
                }

            case UNKNOWN:
            default:
                Rule rule = new Rule(event);
                return rule.reply();
        }
    }

}
