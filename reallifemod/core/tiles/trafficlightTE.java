package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class trafficlightTE extends TileEntityDirectional{
	public int delta;
	public static enum State{
		GREEN,ORANGE, RED, ORANGE_FLASH, INACTIVE;
	}
	boolean fromRed;
	public State state = State.RED;
	
    public void updateEntity() {
    	if(this.state!=State.INACTIVE){
    		this.delta++;
    		if(delta==600){
    	    	if(this.state==State.GREEN){
    	    		this.fromRed=false;
    	    		this.state=State.ORANGE;
    	    		delta=0;
    	    	}
    	    	if(this.state==State.RED){
    	    			this.fromRed=true;
        	    		this.state=State.ORANGE;
        	    		delta=0;
    	    	}
    		}
    		if(delta==5){
    	    	if(this.state==State.ORANGE&&this.fromRed){
    	    		this.fromRed=false;
    	    		this.state=State.GREEN;
    	    		delta=0;
    	    	}
    	    	if(this.state==State.ORANGE&&this.fromRed==false){
    	    			this.fromRed=true;
        	    		this.state=State.RED;
        	    		delta=0;
    	    	}
    		}
    		} 
    	this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
	@Override
    public int getBlockMetadata()
    {
		if(this.worldObj!=null){
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }
        return this.blockMetadata;
    }else
    	return 1;
    }

}
