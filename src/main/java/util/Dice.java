package util;

public class Dice {
	
    public static int roll(String diceExpr) {
    	return new DiceExpr(diceExpr).roll();
    }
    
    public static int rollE(String diceExpr) {
    	return new DiceExpr(diceExpr).rollE();
    }

}
