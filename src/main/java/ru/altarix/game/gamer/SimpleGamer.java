/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.gamer;

import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.forecast.Forecast;

/**
 *
 * @author chuprin
 */
public class SimpleGamer implements Gamer{
    private String name = "";
    private List<Card> cards;
    private Forecast forecast = new Forecast();
    private float agressive = 1.3f;
    private int scoreRound = 0;
    private int scoreGame = 0;
    
    private boolean inGame = true;
    private boolean primary = false;
    private boolean startStep = false;
    

    public SimpleGamer(String name){
        this.name = name;
    }
    
    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public float getAgressive() {
        return agressive;
    }
    public void setAgressive(float agressive) {
        this.agressive = agressive;
    }
    
    public boolean isInGame() {
        return inGame;
    }
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    
    public boolean isStartStep() {
        return startStep;
    }
    public void setStartStep(boolean startStep) {
        this.startStep = startStep;
    }
    
    public int getScoreRound() {
        return scoreRound;
    }
    public void addScoreRound() {
        this.scoreRound++;
    }
    public void setScoreRound(int scoreRound) {
        this.scoreRound = scoreRound;
    }

    public int getScoreGame() {
        return scoreGame;
    }
    public void addScoreGame(int scoreGame) {
        this.scoreGame += scoreGame;
    }
    public void setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
    }
    
    public Forecast getForecast(){
        return forecast;
    }
    public void setForecast(Forecast forecast) {
        Double rate = Math.ceil(forecast.getCardWin() * this.agressive);
        forecast.setAnswer(rate.intValue());        
        
        this.forecast = forecast;
    }
    
    @Override
    public boolean equals(Object object){        
        if(!(object instanceof SimpleGamer)){
            return false;
        }
        
        SimpleGamer other = (SimpleGamer) object;
        return this.getName().equals(other.getName());
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
