/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.forecast;

import ru.altarix.game.gamer.Gamer;

/**
 *
 * @author chuprin
 */
public class Forecast{
    private Gamer gamer = null;
    private String cardType = "";
    private int cardWin = 0;
    private int superCardInGame = 0;
    private int answer = 0;

    public Forecast(){
    }

    public Forecast(String cardType, int cardWin, int superCardInGame){
        this.cardType = cardType;
        this.cardWin = cardWin;
        this.superCardInGame = superCardInGame;
    }
    
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardWin() {
        return cardWin;
    }
    public void setCardWin(int cardWin) {
        this.cardWin = cardWin;
    }

    public int getSuperCardInGame() {
        return superCardInGame;
    }
    public void setSuperCardInGame(int superCardInGame) {
        this.superCardInGame = superCardInGame;
    }
    
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    
    public Gamer getGamer() {
        return gamer;
    }
    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    @Override
    public String toString(){
        return "answer " + getAnswer() + " " + getCardType() + " " + getCardWin() + " " + getSuperCardInGame();
    
    }
    
}
