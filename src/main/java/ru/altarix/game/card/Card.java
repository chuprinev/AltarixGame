/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.card;

import ru.altarix.game.gamer.Gamer;

/**
 *
 * @author chuprin
 */
public interface Card {
    public String getName();
    public void setName(String name);
    
    public String getType();
    public void setType(String type);
    
    public boolean isSuperCard();
    public void setSuperCard(boolean superCard);
    
    public int getWight();
    public void setWight(int wight);
    
    public boolean isDischarge();
    public void setDischarge(boolean discharge);
    
    public Gamer getGamer();
    public void setGamer(Gamer gamer);
    
    public String toString();
}
