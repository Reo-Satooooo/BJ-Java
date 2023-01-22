package com.example.blackjack.repository;

import com.example.blackjack.value.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerCardRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public PlayerCardRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public List<Card> readPlayerHands(){
        // sql
        String sql = "select CARD_MARK, CARD_NUMBER " + "from PLAYER_HAND ";

        // queryForListメソッドでSQLを実行し、結果MapのListで受け取る。
        List<Map<String, Object>> cards = jdbc.queryForList(sql);

        // Cardオブジェクト格納用のListを作成する。
        List<Card> cardList = new ArrayList<>();

        for (Map<String, Object> eachCard : cards){
            Card card = new Card((String) eachCard.get("card_mark"),(Integer)eachCard.get("card_number"));
            cardList.add(card);
        }
        return cardList;
    }

    public void insertPlayerHands(Card card){
        // sql
        String sql = "insert into PLAYER_HAND " + "(CARD_MARK, CARD_NUMBER) " + "values (?,?)";
        String cardMark = card.checkMark();
        Integer cardNumber = card.checkNumber();
        jdbc.update(sql, cardMark, cardNumber);
    }

    public void deletePlayerHands(){
        // sql
        String sql = "delete from PLAYER_HAND ";

        jdbc.update(sql);
    }

}
