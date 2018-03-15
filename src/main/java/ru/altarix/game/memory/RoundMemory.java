/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.memory;

import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;

/**
 *
 * @author chuprin
 */
public class RoundMemory {
    private List<Gamer> gamerList;
    private List<Card> cardList;
    private Card cardWin;
    private Gamer gamerWin;
    private int round;

    public List<Gamer> getGamerList() {
        return gamerList;
    }
    public void setGamerList(List<Gamer> gamerList) {
        this.gamerList = gamerList;
    }

    public List<Card> getCardList() {
        return cardList;
    }
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Card getCardWin() {
        return cardWin;
    }
    public void setCardWin(Card cardWin) {
        this.cardWin = cardWin;
    }

    public Gamer getGamerWin() {
        return gamerWin;
    }
    public void setGamerWin(Gamer gamerWin) {
        this.gamerWin = gamerWin;
    }

    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    
    @Override
    public String toString(){
        return  round  + " " + gamerWin + " " + cardWin;
    }
    
}
