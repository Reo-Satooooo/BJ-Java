package com.example.blackjack.replier;

import java.util.EnumSet;
import java.util.regex.Pattern;

public enum Intent {

    // メッセージのパターンに対応するやりとり状態の定義
    GAME_START("開始"),
    HIT("ヒット"),
    STAND("スタンド"),
    UNKNOWN(".+");

    private final String regexp;

    private Intent(String regexp){
        this.regexp = regexp;
    }

    // メッセージからやりとり状態を判断
    public static Intent whichIntent(String text){
        // 全てのIntent(REMINDER, UNKNOWN)を取得
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        // 引数textが、REMINDER, UNKNOWNのパターンに当てはまるかチェックする
        // 当てはまった方を戻り値とする
        for (Intent intent : set){
            if(Pattern.matches(intent.regexp, text)){
                return intent;
            }
        }
        return UNKNOWN;
    }

}
