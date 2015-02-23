package de.ItsAMysterious.mods.reallifemod.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender.BlockRenderer_Christmastree;
import de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender.BlockRenderer_Desk;
import de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender.BlockRenderer_Doorwreath;
import de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender.BlockRenderer_Rooftile;
import de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender.BlockRenderer_Toilet;
import de.ItsAMysterious.mods.reallifemod.api.rendering.items.AK47Renderer;
import de.ItsAMysterious.mods.reallifemod.api.rendering.items.uziRenderer;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.ReallifemodNPC;
import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityAdvancedFlameFX;
import de.ItsAMysterious.mods.reallifemod.core.entitys.weapons.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemCoke;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderAdvancedFlame;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderBullet;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderLanz;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderNPC;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderRobot;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderTGX;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.RenderTrailer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.entitys.renderjeep;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ChristmasPyramidTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.ChristmasTreeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TileEntityShield_Renderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TileEntityTV_Renderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.TileEntityTrafficLight_Renderer;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.atmTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.bankTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.blastfurnaceTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.cabinetTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.chairTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.computerTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.crashbarrierTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.cupboardTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.deskTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.dishwasherTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.doorwreathTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.drinksmachineTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.espressoTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.fireplaceTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.fishtankTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.freezerTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.growpotTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.heatingTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.lanternTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.neonlampTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.pillarTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.pissoirTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.radioTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.rooftileTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.safeTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.shelfTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.sinkTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.tableTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.toasterTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.toiletTER;
import de.ItsAMysterious.mods.reallifemod.core.rendering.tiles.washbasinTER;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityCherokee;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTrailer;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTruck;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmasTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.bankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.bilboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.blastfurnaceTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.cabinetTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.chairTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.christmaspyramidTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.computerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.crashbarrierTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.cupboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.deskTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.dishwasherTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.doorwreathTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.drinksmachineTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.espressoTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.fireplaceTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.fishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.freezerTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.growpotTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.heatingTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.lanteernTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.neonlampTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.pillarTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.pissoirTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.radioTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.rooftileTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.safeTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.shelfTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.sinkTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toasterTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toiletTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.trafficlightTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.washbasinTE;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;

public class ClientProxy extends ServerProxy{
	public static Map<String,String> EntityNames= new HashMap<String, String>();
	public static Map<String,String> EntitySurnames= new HashMap<String, String>();
	public static Map<String,String> BirthDates= new HashMap<String, String>();
	public static Map<String,String> Genders= new HashMap<String, String>();
	public static Map<String, Float> Money= new HashMap<String, Float>();
	public static Map<Block, Float> Prices= new HashMap<Block, Float>();


	static int cupboardRenderID, furnaceRenderID, computerRenderID, freezerRenderID, chairRenderID,
	 basinrenderID, televisionRenderID, LanzRenderID, LowryRenderID, LanternRenderID, TrafficLightRenderID,
	 ItemLowryRenderID, DrinksMachineRenderID, NeonLampRenderID, pissoirID, TrailerID, bottleID, deskLampID,
	 christmaspyramid, christmastreeID, doorwreathid, pillarID,rooftileID,toiletID, deskID;
	public static int bedTime=0;
	
	public ClientProxy(){
		createFolders();
		doNameUpdate();
	}
	@Override
	public void setIDs()
	{
		cupboardRenderID=RenderingRegistry.getNextAvailableRenderId();
		furnaceRenderID=RenderingRegistry.getNextAvailableRenderId();
		computerRenderID=RenderingRegistry.getNextAvailableRenderId();
		freezerRenderID=RenderingRegistry.getNextAvailableRenderId();
		chairRenderID=RenderingRegistry.getNextAvailableRenderId();
		basinrenderID=RenderingRegistry.getNextAvailableRenderId();
		televisionRenderID=RenderingRegistry.getNextAvailableRenderId();
		LanternRenderID=RenderingRegistry.getNextAvailableRenderId();
		TrafficLightRenderID=RenderingRegistry.getNextAvailableRenderId();
		DrinksMachineRenderID=RenderingRegistry.getNextAvailableRenderId();
		ItemLowryRenderID=RenderingRegistry.getNextAvailableRenderId();
		NeonLampRenderID=RenderingRegistry.getNextAvailableRenderId();
		pissoirID=RenderingRegistry.getNextAvailableRenderId();
		deskLampID = RenderingRegistry.getNextAvailableRenderId(); 
		christmaspyramid=RenderingRegistry.getNextAvailableRenderId();
		christmastreeID=RenderingRegistry.getNextAvailableRenderId();
		doorwreathid=RenderingRegistry.getNextAvailableRenderId();
		pillarID=RenderingRegistry.getNextAvailableRenderId();
		rooftileID=RenderingRegistry.getNextAvailableRenderId();
		toiletID=RenderingRegistry.getNextAvailableRenderId();
		deskID=RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderThings(){
		TileEntitySpecialRenderer renderfr = new freezerTER();
       ClientRegistry.bindTileEntitySpecialRenderer(freezerTE.class,renderfr);
       ClientRegistry.bindTileEntitySpecialRenderer(bilboardTE.class,new TileEntityShield_Renderer());
        ClientRegistry.bindTileEntitySpecialRenderer(computerTE.class,new computerTER());
        ClientRegistry.bindTileEntitySpecialRenderer(blastfurnaceTE.class,new blastfurnaceTER());
        ClientRegistry.bindTileEntitySpecialRenderer(cupboardTE.class, new cupboardTER());
        ClientRegistry.bindTileEntitySpecialRenderer(chairTE.class, new chairTER());
        ClientRegistry.bindTileEntitySpecialRenderer(washbasinTE.class, new washbasinTER());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTV.class, new TileEntityTV_Renderer());
        ClientRegistry.bindTileEntitySpecialRenderer(lanteernTE.class, new lanternTER());
        ClientRegistry.bindTileEntitySpecialRenderer(trafficlightTE.class, new TileEntityTrafficLight_Renderer());
        ClientRegistry.bindTileEntitySpecialRenderer(drinksmachineTE.class, new drinksmachineTER());
        ClientRegistry.bindTileEntitySpecialRenderer(neonlampTE.class, new neonlampTER());
        ClientRegistry.bindTileEntitySpecialRenderer(pissoirTE.class, new pissoirTER());
        ClientRegistry.bindTileEntitySpecialRenderer(christmaspyramidTE.class, new ChristmasPyramidTER());
        ClientRegistry.bindTileEntitySpecialRenderer(ChristmasTreeTE.class, new ChristmasTreeTER());
        ClientRegistry.bindTileEntitySpecialRenderer(doorwreathTE.class, new doorwreathTER());
        ClientRegistry.bindTileEntitySpecialRenderer(crashbarrierTE.class, new crashbarrierTER());
        ClientRegistry.bindTileEntitySpecialRenderer(pillarTE.class, new pillarTER());
        ClientRegistry.bindTileEntitySpecialRenderer(safeTE.class, new safeTER());
        ClientRegistry.bindTileEntitySpecialRenderer(bankTE.class, new bankTER());
        ClientRegistry.bindTileEntitySpecialRenderer(tableTE.class, new tableTER());
        ClientRegistry.bindTileEntitySpecialRenderer(radioTE.class, new radioTER());
        ClientRegistry.bindTileEntitySpecialRenderer(fireplaceTE.class, new fireplaceTER());
        ClientRegistry.bindTileEntitySpecialRenderer(toasterTE.class, new toasterTER());
        ClientRegistry.bindTileEntitySpecialRenderer(atmTE.class, new atmTER());
        ClientRegistry.bindTileEntitySpecialRenderer(heatingTE.class, new heatingTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(rooftileTE.class, new rooftileTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(toiletTE.class, new toiletTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(fishtankTE.class, new fishtankTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(cabinetTE.class, new cabinetTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(shelfTE.class, new shelfTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(dishwasherTE.class, new dishwasherTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(sinkTE.class, new sinkTER());
		TileEntitySpecialRenderer renderfp = new growpotTER();
		ClientRegistry.bindTileEntitySpecialRenderer(growpotTE.class, renderfp);
    	ClientRegistry.bindTileEntitySpecialRenderer(espressoTE.class, new espressoTER());
    	ClientRegistry.bindTileEntitySpecialRenderer(deskTE.class, new deskTER());
    	
    	//Tileentitys
    	RenderingRegistry.registerEntityRenderingHandler(EntityLanz.class, new RenderLanz());
    	RenderingRegistry.registerEntityRenderingHandler(EntityTruck.class,new RenderTGX());
    	RenderingRegistry.registerEntityRenderingHandler(EntityTrailer.class, new RenderTrailer());
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(ReallifemodNPC.class, new RenderNPC(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRobot.class, new RenderRobot(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedFlameFX.class, new RenderAdvancedFlame());
		RenderingRegistry.registerEntityRenderingHandler(EntityCherokee.class, new renderjeep());
	}
	

	@Override
	public void registerBlockHandlers() {
		super.registerBlockHandlers();
		/**RenderingRegistry.registerBlockHandler(new BlockRenderer_Cupboard(cupboardRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Blastfurnace(furnaceRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Computer(computerRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Freezer(freezerRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Chair(chairRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Washbasin(basinrenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_TV(televisionRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Pissoir(pissoirID));
		RenderingRegistry.registerBlockHandler(new BlockRender_Lantern(LanternRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRender_Trafficlight(TrafficLightRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Drinksmachine(DrinksMachineRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Neonlamp(NeonLampRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Christmaspyramid(christmaspyramid));*/
		//RenderingRegistry.registerBlockHandler(new BlockRenderer_Doorwreath(doorwreathid));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Rooftile(rooftileID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Christmastree(christmastreeID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Desk(deskID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Toilet(toiletID));
		MinecraftForgeClient.registerItemRenderer(TLMItems.ak, new AK47Renderer());
		MinecraftForgeClient.registerItemRenderer(TLMItems.uzi, new uziRenderer());
		}
	
	public static int getRenderID(Block blockID){
		if(blockID==TLMBlocks.cupboard)
			return cupboardRenderID;
		if(blockID==TLMBlocks.blastFurnace)
			return furnaceRenderID;
		if(blockID==TLMBlocks.PC)
			return computerRenderID;
		if(blockID==TLMBlocks.freezer)
			return freezerRenderID;
		if(blockID==TLMBlocks.chair)
			return chairRenderID;
		if(blockID==TLMBlocks.basin)
			return basinrenderID;
		if(blockID==TLMBlocks.televison)
			return televisionRenderID;
		if(blockID==TLMBlocks.laterne)
			return LanternRenderID;
		if(blockID== TLMBlocks.TrafficLight)
			return TrafficLightRenderID;
		if(blockID==TLMBlocks.DrinksMachine)
			return DrinksMachineRenderID;
		if(blockID==TLMBlocks.NeonLamp)
			return NeonLampRenderID;
		if(blockID==TLMBlocks.pissoir)
			return pissoirID;
		if(blockID==TLMBlocks.deskLamp)
			return deskLampID;
		if(blockID==TLMBlocks.christmaspyramid)
			return christmaspyramid;
		if(blockID==TLMBlocks.christmastree)
			return christmastreeID;
		if(blockID==TLMBlocks.BlockDoorwreath)
			return doorwreathid;
		if(blockID==TLMBlocks.rooftile)
			return rooftileID;
		if(blockID==TLMBlocks.toilet)
			return toiletID;
		if(blockID==TLMBlocks.desk)
			return deskID;
		else return -1;
	}
	
	@Override
	public void registerEntitys(){
		EntityRegistry.registerModEntity(EntityLanz.class, "LANZ",1000, RealLifeMod.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityTruck.class, "TGX",1001,RealLifeMod.instance,80,1,true);
		EntityRegistry.registerModEntity(EntityBullet.class, "BULLET",1002,RealLifeMod.instance,80,1,true);
		EntityRegistry.registerModEntity(ReallifemodNPC.class, "worker",1003,RealLifeMod.instance,80,1,true);
		EntityRegistry.registerModEntity(EntityCherokee.class, "JEEP",1004,RealLifeMod.instance,80,1,true);
		EntityRegistry.registerModEntity(EntityRobot.class, "Tutorialbot",1005,RealLifeMod.instance,80,1,true);
	}
	
	@Override
	public void registerTileEntities()
	{
	    GameRegistry.registerTileEntity(freezerTE.class, "freezerTE" );
	    GameRegistry.registerTileEntity(cupboardTE.class, "cupboard");
	    GameRegistry.registerTileEntity(chairTE.class, "blockChair");
	    GameRegistry.registerTileEntity(TileEntityTV.class,"blockTelevision");
	    GameRegistry.registerTileEntity(blastfurnaceTE.class, "blockBlastFurnace");
	    GameRegistry.registerTileEntity(computerTE.class, "blockComputer");
	    GameRegistry.registerTileEntity(washbasinTE.class, "blockBasin");
	    GameRegistry.registerTileEntity(pissoirTE.class, "blockPissoir");
	    GameRegistry.registerTileEntity(trafficlightTE.class, "trafficlight");
	    GameRegistry.registerTileEntity(lanteernTE.class, "lantern");
	    GameRegistry.registerTileEntity(drinksmachineTE.class, "drinksmachine");
	    GameRegistry.registerTileEntity(neonlampTE.class, "NeonLamp");
	    GameRegistry.registerTileEntity(christmaspyramidTE.class, "christmaspyramid");
	    GameRegistry.registerTileEntity(ChristmasTreeTE.class, "christmastree");
	    GameRegistry.registerTileEntity(doorwreathTE.class, "doorwreathte");
	    GameRegistry.registerTileEntity(crashbarrierTE.class, "crashbarrierRS");
	    GameRegistry.registerTileEntity(pillarTE.class, "pillar");
	    GameRegistry.registerTileEntity(safeTE.class, "safe");
	    GameRegistry.registerTileEntity(bankTE.class, "bankeree");
	    GameRegistry.registerTileEntity(tableTE.class, "tableTE");
	    GameRegistry.registerTileEntity(radioTE.class, "radioTE");
	    GameRegistry.registerTileEntity(toasterTE.class, "toaster");
	    GameRegistry.registerTileEntity(atmTE.class, "atm");
	    GameRegistry.registerTileEntity(heatingTE.class, "heatingTE");
	    GameRegistry.registerTileEntity(rooftileTE.class, "rooftileTE");
	    GameRegistry.registerTileEntity(toiletTE.class, "toiletTE");
	    GameRegistry.registerTileEntity(cabinetTE.class, "cabinet");
	    GameRegistry.registerTileEntity(shelfTE.class, "shelfTE");
	    GameRegistry.registerTileEntity(dishwasherTE.class, "dishwasherTE");
	    GameRegistry.registerTileEntity(fishtankTE.class, "fishtankTE");
	    GameRegistry.registerTileEntity(growpotTE.class, "growpotTE");
	}
	@Override
	public void createFolders()
    {
        File file = new File(RealLifeMod.Dir, "assets/reallifemod");
        if(!file.exists())
        	file.mkdirs();
        File check=new File(file,"sounds");
        if(!check.exists())
        	check.mkdir();
        File names=new File(RealLifeMod.Dir,"sound.json");

        File json=new File(file,"sound.json");
        if(!json.exists())
        	try {
        		BufferedWriter writer=new BufferedWriter(new FileWriter(json));
				json.createNewFile();
				writer.write("{");
				if(check.listFiles()!=null){
					for(File f:check.listFiles()){
						String g=String.valueOf('"');
						writer.write(g+f.getName().substring(0,f.getName().length()-4)+g+":"+"{"+g+"category"+g+":"+g+"ambient"+g+","+
						g+"sounds"+g+":"+"[{"+g+"name"+g+":"+f.getName().substring(0,f.getName().length()-4)+g+","+g+"stream"+g+":"+"true}]},");
						writer.write("\n");
					}
				}
				writer.write("}");
	        	writer.close();
			} catch (IOException e) {
			}
     //  ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new RLMResourceListener());
    }
	
	public static void doNameUpdate(){
        File file = new File(RealLifeMod.Dir, "assets/reallifemod");
        File Names=new File(file,"playernames.txt");

    	try{
     		BufferedReader reader=new BufferedReader(new FileReader(Names));
     		String line;
     		while((line = reader.readLine()) != null )
     		{
     			if (line.contains(":")&&line.split(":")[1]!=""&&line.contains(" ")&&line.contains("born:")&& line.contains("gender:")&& line.contains("Money:")){
     				EntityNames.put(Minecraft.getMinecraft().thePlayer.getDisplayName(), line.split(":")[1].split(" ")[0]);
     				EntitySurnames.put(Minecraft.getMinecraft().thePlayer.getDisplayName(), line.split(" ")[1]);
     				BirthDates.put(Minecraft.getMinecraft().thePlayer.getDisplayName(),line.split("born:")[1]);
     				Genders.put(Minecraft.getMinecraft().thePlayer.getDisplayName(),line.split("gender:")[1]);
     				Money.put(Minecraft.getMinecraft().thePlayer.getDisplayName(),Float.valueOf(line.split("Money:")[1]));
     			}
     			else
     			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Error! Please delete the file 'playernames.txt' in '.minecraft/reallifemod/assets'! "));
     		}
     		reader.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
	
	public static void setName(EntityPlayer p, String Name, String Surname,String date, String gender, float money){
        File file = new File(RealLifeMod.Dir, "assets");
        File Names=new File(file,"playernames.txt");
    	try{
        	if(!Names.exists())
        		Names.createNewFile();
     		BufferedWriter Writer=new BufferedWriter(new FileWriter(Names));
         		Writer.write(p.getDisplayName()+":"+Name+" "+Surname+" born:"+date+" gender:"+gender+" Money:"+money);
     		Writer.close();
     		EntityNames.put(p.getDisplayName(), Name);
     		EntitySurnames.put(p.getDisplayName(), Surname);
     		BirthDates.put(p.getDisplayName(), date);
     		Genders.put(p.getDisplayName(), gender);
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
	
	public static void doSaving(){
		EntityPlayer player=FMLClientHandler.instance().getClient().thePlayer;
        File file = new File(RealLifeMod.Dir, "assets");
        File Names=new File(file,"playernames.txt");
    	try{
        	if(!Names.exists())
        		Names.createNewFile();
     		BufferedWriter Writer=new BufferedWriter(new FileWriter(Names));
         		Writer.write(player.getDisplayName()+":"+EntityNames.get(player.getDisplayName())+" "+EntitySurnames.get(player.getDisplayName())+" born:"+BirthDates.get(player.getDisplayName())+" gender:"+Genders.get(player.getDisplayName())+" Money:"+Money.get(player.getDisplayName()));
     		Writer.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
    
	@Override
	public void registerThings() {
		GameRegistry.registerFuelHandler(new ItemCoke());
	}
	
}