/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.manager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.match.Match;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public class CardManager { 
    private static CardManager manager = null;    
    private CardManager(){
    }    
    public static CardManager getManager(){
        if(manager == null){
            manager = new CardManager();
        }        
        return manager;
    }
    
    public List<Card> getCardsRandom(Rule rule){
        List<Card> cardInGame = new LinkedList<>();
        
        if(isWorkCardsRandom()){
            ArrayList<Card> allCards = getAllCard(rule);
            Set<Integer> maskRandom = getRandomMask(rule, allCards.size());
            cardInGame = getCardInGame(allCards, maskRandom);
        }        
        
        return cardInGame;
    }
        private Boolean isWorkCardsRandom(){                      
            return true;
        }
        
        private ArrayList<Card> getAllCard(Rule rule){
            int listSize = rule.getCardTypeList().size() + rule.getCardNameList().size();        
            ArrayList<Card> allCards = new ArrayList<>(listSize);

            for(String type: rule.getCardTypeList()){
                for(String name: rule.getCardNameList()){
                    Card card = rule.getEmptyCard();
                    card.setName(name);
                    card.setType(type);
                    card.setWight(rule.getWightCard(name));
                    
                    allCards.add(card);
                }
            }
            
            return allCards;
        }
        private Set<Integer> getRandomMask(Rule rule, int allCardsSize){
            Set<Integer> maskRandom = new LinkedHashSet<>();
            if(allCardsSize >= rule.getCountCardsInGame()){
                while(maskRandom.size() < rule.getCountCardsInGame()){
                    maskRandom.add(new Random().nextInt(rule.getCountCardsInGame()));
                }
            }
            return maskRandom;
        }        
        private List<Card> getCardInGame(ArrayList<Card> allCards, Set<Integer> maskRandom){
            List<Card> cardInGame = new LinkedList<>();
            for(Integer number: maskRandom){            
                cardInGame.add(allCards.get(number));
            }
            
            return cardInGame;
        }
        
    public void setCards(Match match){
        if(isWorkGamersCards(match)){            
            List<Card> cardInGame = getCardsRandom(match.getRule());
            
            int left = 0;
            int rigth = 0;
            for(Gamer gamer: match.getGamerList()){
                rigth = left + match.getRule().getCountCardsInHand();
                if(rigth < cardInGame.size()){
                    gamer.setCards(new LinkedList<>(cardInGame.subList(left, rigth)));
                }
                left = rigth;
            }
            
            if(rigth < cardInGame.size()){
                match.setOtherCard(cardInGame.subList(rigth, cardInGame.size()));
            }
        }
    }
        private Boolean isWorkGamersCards(Match match){
            return true;
        }
    
    public void addCardGamer(Gamer gamer, List<Card> cardList){
        gamer.getCards().addAll(cardList);
    }
    
    public void getGamerCardDischarge(Gamer gamer, Rule rule){    
        if(gamer.getCards().size() > rule.getCountCardsInHand()){
            setCardDischarge(gamer.getCards(), rule);
        }
    }        
        private void setCardDischarge(List<Card> cardList, Rule rule){
            int cardDischargeCount = cardList.size() - rule.getCountCardsInHand();
            for(int i=0;i<cardDischargeCount; i++){
                Card weakestCard = CardManager.getManager().getWeakestCard(cardList, rule);            
                weakestCard.setDischarge(true);
            }            
        }
    
    public void setSuperTypeCard(Match match){
        String typeSuper = match.getTenderResult().getCardType();
        
        for(Gamer gamer: match.getGamerList()){
            setSuperType(typeSuper, gamer.getCards());            
        }
    }
        private void setSuperType(String typeSuper, List<Card> cardList){
            for(Card card: cardList){
                if(typeSuper.equals(card.getType())){
                    card.setSuperCard(true);
                }
            }
        }

    public Card getWeakestCard(List<Card> cardList, Rule rule){
        Card weakestCard = null;
        for(Card card: cardList){
            if(card.isDischarge() == false){
                if(weakestCard == null){
                    weakestCard = card;
                }
                if(rule.isStrongerCard(weakestCard, card) > 0){
                    weakestCard = card;
                }
            }                
        }
        
        return weakestCard;
    }
    public Card getWeakestCard(List<Card> cardList, String type, Rule rule){
        Card weakestCard = null;
        for(Card card: cardList){
            if(card.isDischarge() == false){
                if(card.getType().equals(type)){
                    if(weakestCard == null){
                        weakestCard = card;
                    }
                    if(rule.isStrongerCard(weakestCard, card) > 0){
                        weakestCard = card;
                    }
                }                    
            }                
        }
        
        return weakestCard;
    }
    public Card getStrongestCard(List<Card> cardList, Rule rule, boolean all){
        Card strongestCard = null;
        for(Card card: cardList){
            if(card.isDischarge() == false || all){
                if(strongestCard == null){
                    strongestCard = card;
                }
                if(rule.isStrongerCard(strongestCard, card) < 0){
                    strongestCard = card;
                }
            }                
        }
        
        return strongestCard;
    }
    public Card getStrongestCard(List<Card> cardList, Card cardEnemy, Rule rule){
        for(Card card: cardList){
            if(card.isDischarge() == false){
                if(rule.isStrongerCard(cardEnemy, card) < 0){
                    return card;
                }
            }
        }
        
        return null;
    }
        

}

