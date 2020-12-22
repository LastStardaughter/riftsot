package character;

import java.util.Arrays;

import util.*;

public class Character {
	private static final DiceExpr DEFAULT_WILD_DIE=new DiceExpr("d6");
	private byte[] traits=new byte[Traits.traits.size()];
	private DiceExpr pace;
	private boolean wildCard;
	
	
	public String displayTrait(String traitName) {
		switch(traits[Traits.index(traitName)]){
			case 0: return "-";
			case 1: return "d4";
			case 2: return "d6";
			case 3: return "d8";
			case 4: return "d10";
			case 5: return "d12";
			default:return "d12+"+(traits[Traits.index(traitName)]-5);
		}
	}
	
	public int rollTrait(String traitName, int mod, DiceExpr wildDie) {
		int wildDieRoll=0;
		String traitDie = displayTrait(traitName);
		if(traitDie.equals("-")) {traitDie="d4-2";}
		DiceExpr traitDieExpr=new DiceExpr(traitDie);
		mod+=traitDieExpr.z;
		traitDieExpr.z=0;
		
		if(wildCard) {wildDieRoll=wildDie.rollE();}
		
		return Math.max(traitDieExpr.rollE(), wildDieRoll)+mod;		
	}
	
	public int rollTrait(String traitName) {
		return rollTrait(traitName, 0, DEFAULT_WILD_DIE);
	}
	
	public int rollTrait(String traitName, int mod) {
		return rollTrait(traitName, mod, DEFAULT_WILD_DIE);
	}
	
	public int[] rollAuto(String traitName, int mod, DiceExpr wildDie, int num) {
		int[] rolls=new int[num+1];
		String traitDie = displayTrait(traitName);
		if(traitDie.equals("-")) {traitDie="d4-2";}
		DiceExpr traitDieExpr=new DiceExpr(traitDie);
		mod+=traitDieExpr.z;
		traitDieExpr.z=0;
				
		for(int i=0;i<num;i++) {rolls[i]=traitDieExpr.rollE()+mod;}
		if(wildCard) {rolls[num]=wildDie.rollE()+mod;}
		
		Arrays.sort(rolls);
		return Arrays.copyOfRange(rolls, 1, num);		
	}
}
