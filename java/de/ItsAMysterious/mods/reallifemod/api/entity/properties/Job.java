package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import net.minecraft.entity.player.EntityPlayer;

public class Job {
	public static enum names
	{
		 MINOR,POLICEMEN,FIREMEN, ;
	}
	
	public static String name;
	private EntityPlayer jobber;
	
	public Job(String Jobname)
	{
		if(Jobname.equalsIgnoreCase(names.valueOf(Jobname).toString()))
			Job.name=Jobname;
	}
	
	public static String name(){
		return name;
	}
	
	public void setName(String name){
		Job.name=name;
	}
	
}
