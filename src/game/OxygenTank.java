package game; 

import edu.monash.fit2099.engine.Item; 
 
public class OxygenTank extends Item { 
	private int oxygenPoints = 10;
	
	public OxygenTank() { 
		super("Oxygen Tank", 'T'); 
	} 
	
	public void useOxygen() {
		this.oxygenPoints  -= 1;
	}
	
	public boolean isEmpty() {
		if (this.oxygenPoints == 0) {
			return true;
		}
		return false;
	}
	 
} 
