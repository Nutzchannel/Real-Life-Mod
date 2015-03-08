package de.ItsAMysterious.mods.reallifemod.api.util;

import java.awt.image.ColorModel;

import de.ItsAMysterious.mods.reallifemod.api.interfaces.Buyable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class LittleFunctions{
	private static final int ScreenHeight=Minecraft.getMinecraft().displayHeight;
	private static final int ScreenWidth=Minecraft.getMinecraft().displayWidth;
	public static final int W=17;
	public static final int L=28;
	public static final int Z=29;
	public static final int A=30;
	public static final int D=32;
	public static final String numbers="1234567890";

	/**
	 *This method parses a name and surname to any Entity.class object.
	 *This name is important for the LIFE DATA or the Identification Card
	 *of that entity.The name is registered at the EntityNames.txt, which is localized
	 *at ".minecraft/config/theLIFEmod"!
	 */
	
	/**
	 * Divides the first parameter width the second.
	 */
	public static  int divideValue(int value,int divor)
	{
		return value/divor;
	}
	
	/**Gives you a detailed age which is calculated out of 
	 * the given integer of milliseconds from the entity.getAge()-method.
	 * This String can not be changed!*/
	public static final String calculateAge(int milliseconds)
	{	
		int seconds;
		int minutes;
		int hours;
		int days;
		int weeks;
		int months;
		int years;
		seconds=Math.round(milliseconds/100);
		minutes=Math.round(seconds/60);
		hours=Math.round(minutes/60);
		days= (int) Math.round(hours/24D);
		weeks=days/7;
		months=days/30;
		//if(months==1){months=days/28;}else months=days/30;
		years=Math.round(days/365);
		
		seconds=seconds-(60*minutes);
		minutes=minutes-(60*hours);
		hours=hours-(24*days);
		days=days-(7*weeks);
		weeks=weeks-(52*years);
		months=months-(12*years);
		
		if(years>0)
		return years+" Years, "+weeks+" weeks, "+days+" days, "+hours+" hours,"+minutes+" minutes,"+seconds+" seconds";
		else if(weeks>0)
			return weeks+" weeks, "+days+" days, "+hours+" hours,"+minutes+" minutes,"+seconds+" seconds";
		else if(days>0)
			return days+" days, "+hours+" hours,"+minutes+" minutes,"+seconds+" seconds";
		else 
		return hours+" hours,"+minutes+" minutes,"+seconds+" seconds";
			
	}
	
	
	public static enum WeekDay{
		MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;
	}
	
	/**
	 *Calculates the price out of any number of 
	 * "Buyable" objects. 
	 */
	static double calculateSum( Buyable price1, Buyable... prices )
	{
	  double result = price1.price();
	  for ( Buyable price : prices )
	    result += price.price();
	  return result;
	}
	
	/**
	 *Calculates the reduced price of a "Buyable"! 
	 */
	static double reduce(Buyable object){
		double result=(object.price()/100)*object.percentage();
		return result;
	}
	
	static String getAgeInYears(EntityPlayer player){
		int age=player.getAge();
		int lnge=calculateAge(age).indexOf("Years");
		String s =calculateAge(age).substring(0, lnge);
		return s;
	}

	public static double getPrice(Buyable object) {
		return object.price();
	}
	
	public static Object Color(ColorModel color){
		return color.getRGB(color);
	}
	
	/**private static void addAchievementName(String ach, String name) {
		LanguageRegistry.instance().addStringLocalization("achievement." + ach,"en_US", name);
	}

	private static void addAchievementDesc(String ach, String desc) {
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}

	public static void   addAchievementLocalizations() {
		
		addAchievementDesc("Farmer", "You've crafted an automatic Fieldmaker");
		addAchievementName("Farmer", "Farmer!");

		addAchievementDesc("AchievementOven", "Youve crafted a smelting oven!");
		addAchievementName("AchievementOven", "AchievementOven");

		addAchievementDesc("TussenKrupp", "Youve made your own krupp-steel");
		addAchievementName("TussenKrupp", "Tussen Krupp");
		
		addAchievementDesc("TheFirstStep", "Youve melted your first dirty steel ingot");
		addAchievementName("TheFirstStep", "The first step");
		
		addAchievementDesc("MCD", "Youve crafted Chips out of Potatoes");
		addAchievementName("MCD", "MC-D");
}*/
	
	public static float sq(float x){
	return x*x;
	}
	
	public static float roundDouble(final double number, final int decimalPlaces) {
	    float precision = 1.0F;
	    for (int i = 0; i < decimalPlaces; i++, precision *= 10);
	    return ((int) (number * precision + 0.5)  / precision);
	}
	
	public static float roundFloat(final float number, final int decimalPlaces) {
	    float precision = 1.0F;
	    for (int i = 0; i < decimalPlaces; i++, precision *= 10);
	    return ((int) (number * precision + 0.5)  / precision);
	}
	
	public static float getRealTimeInSeconds(float minecraftTime) {
		return minecraftTime/20; 
	}
	
	public static double realTimeToMinecraft(double e) {
		return e*1/20; 
	}
	
}
