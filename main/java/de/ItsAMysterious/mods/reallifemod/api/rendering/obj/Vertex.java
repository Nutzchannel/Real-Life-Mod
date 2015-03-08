package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

public class Vertex {
	float x,y,z;
	
	public Vertex(float xf, float yf,float zf){
		this.x=xf;
		this.y=yf;
		this.z=zf;
	}
	
	public Vertex(float xf, float yf){
		this(xf,yf,0.0F);
	}
}
