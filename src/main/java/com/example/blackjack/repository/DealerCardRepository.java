package com.example.blackjack.repository;

import com.example.blackjack.value.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DealerCardRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public DealerCardRepository(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    public List<Card> readDealerHands(){
        // sql
        String sql = "select card_mark, card_number " + "from dealer_hand ";

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

    public void insertDealerHands(Card card){
        // sql
        String sql = "insert into dealer_hand " + "(card_mark, card_number) " + "values(?,?)";
        String cardMark = card.checkMark();
        int cardNumber = card.checkNumber();
        jdbc.update(sql, cardMark, cardNumber);
    }

    public void deleteDealerHands(){
        // language = sql
        String sql = "delete from dealer_hand ";

        jdbc.update(sql);
    }

}
