package vtsman.mine_modus.tileentity;

import org.bouncycastle.util.encoders.Hex;

public class nodetype {
	private String type;
	private int radius;
	private int capacity;
	private int color;
	private double dodecagon_radius = 0;
	public nodetype(String type, int radius, int capacity, int color, double modelRadius){
		this.type = type;
		this.radius = radius;
		this.capacity = capacity;
		this.color = color;
		this.dodecagon_radius = modelRadius;
	}
	public int getColor(){
		return color;
	}
	public String getType(){
		return type;
	}
	public int getRange(){
		return radius;
	}
	public int getCapacity(){
		return capacity;
	}
	public double getRadius(){
		return this.dodecagon_radius;
	}
}