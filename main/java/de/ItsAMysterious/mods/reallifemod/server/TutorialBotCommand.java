package de.ItsAMysterious.mods.reallifemod.server;
import java.util.ArrayList;
import java.util.List;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class TutorialBotCommand implements ICommand
{ 
    private final List aliases;
  
    public TutorialBotCommand() 
    { 
        aliases = new ArrayList(); 
        aliases.add("spawnHelperbot"); 
    } 
  
    @Override 
    public int compareTo(Object o)
    { 
        return 0; 
    } 

    @Override 
    public String getCommandName() 
    { 
        return "spawnHelperbot"; 
    } 

    @Override         
    public String getCommandUsage(ICommandSender var1) 
    { 
        return "spawnHelperbot"; 
    } 

    @Override 
    public List getCommandAliases() 
    { 
        return this.aliases;
    } 

    @Override 
    public void processCommand(ICommandSender sender, String[] argString)
    { 
        EntityPlayer player;
        if(sender instanceof EntityPlayer){
            player = (EntityPlayer)sender;
            if(!player.worldObj.isRemote){
            	EntityRobot bot=new EntityRobot(player.worldObj,player.posX+1, player.posY, player.posZ);
            	player.worldObj.spawnEntityInWorld(bot);
            }
        } 
    } 

    @Override 
    public boolean canCommandSenderUseCommand(ICommandSender var1) 
    { 
        return true;
    } 

    @Override  
    public List addTabCompletionOptions(ICommandSender var1, String[] var2) 
    { 
        return null; 
    } 

    @Override 
    public boolean isUsernameIndex(String[] var1, int var2) 
    { 
        return false;
    } 
}