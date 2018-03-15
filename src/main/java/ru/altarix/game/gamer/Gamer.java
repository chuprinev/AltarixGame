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
public interface Gamer {
    
    public List<Card> getCards();
    public void setCards(List<Card> cards);
    
    public String getName();
    public void setName(String name);
    
    public float getAgressive();
    public void setAgressive(float agressive);
    
    public boolean isInGame();
    public void setInGame(boolean inGame);
    
    public boolean isPrimary();
    public void setPrimary(boolean primary);
    
    public boolean isStartStep();
    public void setStartStep(boolean startStep);
    
    public int getScoreRound();
    public void addScoreRound();
    public void setScoreRound(int scoreRound);
    
    public int getScoreGame();
    public void addScoreGame(int scoreGame);
    public void setScoreGame(int scoreGame);
    
    public Forecast getForecast();
    public void setForecast(Forecast forecast);
    
    public boolean equals(Object object);
    public String toString();
    
}
