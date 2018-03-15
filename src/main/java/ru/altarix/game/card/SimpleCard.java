package ru.altarix.game.card;

import ru.altarix.game.gamer.Gamer;

/**
 *
 * @author chuprin
 */
public class SimpleCard implements Card{
    private String name = "";
    private String type = "";    
    private boolean superCard = false;
    
    private Gamer gamer = null;
    private int wight = 0;
    private boolean discharge = false;
    
    public SimpleCard(){
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isSuperCard() {
        return superCard;
    }
    public void setSuperCard(boolean superCard) {
        this.superCard = superCard;
    }

    public int getWight() {
        return wight;
    }
    public void setWight(int wight) {
        this.wight = wight;
    }
    
    public boolean isDischarge() {
        return discharge;
    }
    public void setDischarge(boolean discharge) {
        this.discharge = discharge;
    }
    
    public Gamer getGamer() {
        return gamer;
    }
    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }
    
    @Override
    public String toString(){
        return getName() + " " + getType();
    }

}
