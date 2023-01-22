package com.example.blackjack.service;

import com.example.blackjack.value.Dealer;
import com.example.blackjack.value.Player;

public class Jugement {

    public String jugement(Player player, Dealer dealer){
        int playerSum = player.getSum();
        int dealerSum = dealer.getSum();

        // 両者21超でディーラー勝利
        if (playerSum > 21 && dealerSum > 21){
            return dealer.getName() + "の勝利";
        }
        // プレイヤーの21超でディーラーの勝利
        else if(playerSum > 21){
            return dealer.getName() + "の勝利";
        }
        // ディーラーの21超でプレイヤーの勝利
        else if(dealerSum > 21){
            return player.getName() + "の勝利";
        }
        // 両者21以下で比較し、ディーラーの方が21に近い場合、ディーラーの勝利
        else if(playerSum < dealerSum){
            return dealer.getName() + "の勝利";
        }
        // 両者21以下で比較し、プレイヤーの方が21に近い場合、プレイヤーの勝利
        else if(playerSum > dealerSum){
            return player.getName() + "の勝利";
        }
        // 両者21以下で比較し、引き分けの場合、ディーラーの勝利
        else {
            return dealer.getName() + "の勝利";
        }
    }

}
