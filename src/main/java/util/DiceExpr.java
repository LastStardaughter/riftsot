package util;

public class DiceExpr implements Cloneable{
	public byte x,y,z;
	
	public DiceExpr(String diceExpr) {
        String[] arr=diceExpr.split("[Dd\\+\\-]");
        if(arr[0].equals("")){x=1;} else {x=Byte.parseByte(arr[0]);}
        y=Byte.parseByte(arr[1]);
        if(arr.length>2){
            if(diceExpr.contains("-")){z=Byte.parseByte(diceExpr.substring(diceExpr.indexOf("-")));}
            else {z=Byte.parseByte(arr[2]);}            
        }
    }
	
	private DiceExpr() {}
	
	@Override
	public DiceExpr clone() {
		DiceExpr copy=new DiceExpr();
		copy.x=x;
		copy.y=y;
		copy.z=z;
		return copy;
	}
    
    public int dice(){return (int) x;}
    public int sides(){return (int) y;}
    public int mod(){return (int) z;}
    
    public String expr() {
    	StringBuilder temp=new StringBuilder(9);
    	if(x>1) {temp.append((int) x);
	    	temp.append('d');
	    	temp.append((int) y);
	    	if (z>0) {
	    		temp.append('+');
	    		temp.append((int) z);
	    	} else if(z<0) {
	    		temp.append((int) z);
	    	}
    	}
    	return temp.toString();
    }
    
    public int roll() {
    	int sum=0;
    	for(byte i=0;i<x;i++) {
    		sum+=1+Rand.nextInt(y);
    	}
    	return sum+z;
    }
    
    public int rollE() {
    	int sum=0;
    	for(byte i=0;i<x;i++) {
    		int die=1+Rand.nextInt(y);
    		if(die==y) {i--;}
    		sum+=die;
    	}
    	return sum+z;
    }
    
}
