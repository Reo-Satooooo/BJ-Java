package com.example.blackjack.service;

import com.example.blackjack.replier.AddCard;
import com.example.blackjack.replier.GameStart;
import com.example.blackjack.replier.Result;
import com.example.blackjack.value.Card;
import com.example.blackjack.value.Dealer;
import com.example.blackjack.value.Deck;
import com.example.blackjack.value.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameManager {

    private Deck deck;
    private Player player;
    private Dealer dealer;

    private GameManager(){
        // リポジトリの初期化
    }

    public GameStart gameStart(){

        Deck deck = new Deck();
        this.deck = deck;

        Player player = new Player("あなた",deck);
        Dealer dealer = new Dealer(deck);
        this.player = player;
        this.dealer = dealer;

        // 返答メッセージ作成
        String replyText;

        // プレイヤーの手札表示
        String playerName = "【" + player.getName() + "】";
        replyText = playerName;
        ArrayList<Card> playerCards = player.showHandCard();
        for (Card card : playerCards){
            String mark = card.checkMark();
            int number = card.checkNumber();
            String cardName = mark + "の" + number;
            replyText = String.join("\n", replyText, cardName);
        }

        // ディーラーの1枚目を表示
        String dealerName = "【" + dealer.getName() + "】";
        ArrayList<Card> dealerCards = dealer.showHandCard();
        String mark = dealerCards.get(0).checkMark();
        int number = dealerCards.get(0).checkNumber();
        String cardName = mark + "の" + number;

        replyText = String.join("\n", replyText, dealerName ,cardName);

        return new GameStart(replyText);
    }

    public AddCard addCard(){
        if(player == null){
            throw new RuntimeException();
        }
        Card additionalCard = player.DrawAddCard(deck);
        String mark = additionalCard.checkMark();
        int number = additionalCard.checkNumber();
        String cardName = mark + "の" + number;
        String replyText = "引いたカード\n" + cardName;
        return new AddCard(replyText);
    }

    public Result getResult(){
        if(player == null){
            throw new RuntimeException();
        }

        // 返答メッセージ作成
        String replyText;

        // プレイヤーの手札表示
        String playerName = "【" + player.getName() + "】";
        replyText = playerName;
        ArrayList<Card> playerCards = player.showHandCard();
        for (Card card : playerCards){
            String mark = card.checkMark();
            int number = card.checkNumber();
            String cardName = mark + "の" + number;
            replyText = String.join("\n", replyText, cardName);
        }

        // ディーラーの手札表示
        String dealerName = "【" + dealer.getName() + "】";
        replyText = String.join("\n", replyText, dealerName);
        ArrayList<Card> dealerCards = dealer.showHandCard();
        for (Card card : dealerCards){
            String mark = card.checkMark();
            int number = card.checkNumber();
            String cardName = mark + "の" + number;
            replyText = String.join("\n", replyText, cardName);
        }

        // 勝敗判定
        Jugement jugement = new Jugement();
        String resultText = jugement.jugement(player,dealer);

        // インスタンスを無効化
        this.deck = null;
        this.player = null;
        this.dealer = null;

        replyText = String.join("\n", replyText, "", resultText);
        return new Result(replyText);
    }

}
