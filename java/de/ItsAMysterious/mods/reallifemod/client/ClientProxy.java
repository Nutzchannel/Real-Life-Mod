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
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.TLM;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.ChristmasPyramidTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.ChristmasTreeTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.doorwreathTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.pillarTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.rooftileTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.toiletTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.washbasinTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.chairTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.computerTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.cupboardTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.drinksmachineTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.freezerTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.lanternTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.neonlampTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.blastfurnaceTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.pissoirTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.TileEntityShield_Renderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.TileEntityTV_Renderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.TileEntityTrafficLight_Renderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.atmTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.bankTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.crashbarrierTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.fireplaceTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.heatingTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.radioTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.safeTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.toasterTER;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockBlastFurnaceRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockChairRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockCupboardRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockFreezerRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockRenderLantern;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockRenderTrafficLight;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockRendererComputer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockRendererNeonLamp;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.BlockRenderer_Pissoir;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.ChristmasPyramid_BR;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.ChristmasTreeBR;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.DoorWreathBR;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.DrinksMachineBlockRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.blockRendererBasin;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.blockTVRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.BlockRenderer.rooftileBR;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.ItemRenderer.AK47Renderer;
import de.ItsAMysterious.mods.reallifemod.api.Renderer.ItemRenderer.uziRenderer;
import de.ItsAMysterious.mods.reallifemod.api.Util.RLMResourceListener;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.tableTER;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderAdvancedFlame;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderBullet;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderLanz;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderTGX;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderNPC;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.RenderTrailer;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.entity.renderjeep;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityJeep;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityTrailer;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityTruck;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.reallifemodNPC;
import de.ItsAMysterious.mods.reallifemod.core.entitys.Particles.EntityAdvancedFlameFX;
import de.ItsAMysterious.mods.reallifemod.core.entitys.weapons.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemCoke;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.ChristmasTreeTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.doorwreathTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.blastfurnaceTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.chairTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.christmaspyramidTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.computerTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.cupboardTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.drinksmachineTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.freezerTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.lanteernTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.neonlampTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.pissoirTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.bilboardTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.TileEntityTV;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.rooftileTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.toiletTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.trafficlightTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.washbasinTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.toasterTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.atmTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.bankTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.crashbarrierTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.fireplaceTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.heatingTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.pillarTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.radioTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.safeTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.tableTE;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;

/**
 * 
 * @author MO
 *
 */
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
	 christmaspyramid, christmastreeID, doorwreathid, pillarID,rooftileID;
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
	}
	
	@Override
	public void registerRenderThings(){
		super.registerRenderThings();
        ClientRegistry.bindTileEntitySpecialRenderer(freezerTE.class,new freezerTER());
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
        RenderingRegistry.registerEntityRenderingHandler(EntityLanz.class, new RenderLanz());
    	RenderingRegistry.registerEntityRenderingHandler(EntityTruck.class,new RenderTGX());
    	RenderingRegistry.registerEntityRenderingHandler(EntityTrailer.class, new RenderTrailer());
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(reallifemodNPC.class, new RenderNPC(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityAdvancedFlameFX.class, new RenderAdvancedFlame());
		RenderingRegistry.registerEntityRenderingHandler(EntityJeep.class, new renderjeep());
	}
	

	@Override
	public void registerBlockHandlers() {
		super.registerBlockHandlers();
		RenderingRegistry.registerBlockHandler(new BlockCupboardRenderer(cupboardRenderID));
		RenderingRegistry.registerBlockHandler(new BlockBlastFurnaceRenderer(furnaceRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRendererComputer(computerRenderID));
		RenderingRegistry.registerBlockHandler(new BlockFreezerRenderer(freezerRenderID));
		RenderingRegistry.registerBlockHandler(new BlockChairRenderer(chairRenderID));
		RenderingRegistry.registerBlockHandler(new blockRendererBasin(basinrenderID));
		RenderingRegistry.registerBlockHandler(new blockTVRenderer(televisionRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderer_Pissoir(pissoirID));
		RenderingRegistry.registerBlockHandler(new BlockRenderLantern(LanternRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRenderTrafficLight(TrafficLightRenderID));
		RenderingRegistry.registerBlockHandler(new DrinksMachineBlockRenderer(DrinksMachineRenderID));
		RenderingRegistry.registerBlockHandler(new BlockRendererNeonLamp(NeonLampRenderID));
		RenderingRegistry.registerBlockHandler(new ChristmasPyramid_BR(christmaspyramid));
		RenderingRegistry.registerBlockHandler(new ChristmasTreeBR(christmastreeID));
		RenderingRegistry.registerBlockHandler(new DoorWreathBR(doorwreathid));
		RenderingRegistry.registerBlockHandler(new rooftileBR(rooftileID));
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
		else return -1;
	}
	
	@Override
	public void registerEntitys(){
		EntityRegistry.registerModEntity(EntityLanz.class, "LANZ",514, TLM.instance, 80, 1, true);
		EntityRegistry.registerModEntity(EntityTruck.class, "TGX",RenderingRegistry.getNextAvailableRenderId(),TLM.instance,80,1,true);
		EntityRegistry.registerModEntity(EntityBullet.class, "BULLET",515,TLM.instance,80,1,true);
		EntityRegistry.registerModEntity(reallifemodNPC.class, "REALLIFEMODNPC",516,TLM.instance,80,1,true);
		EntityRegistry.registerModEntity(EntityJeep.class, "JEEP",517,TLM.instance,80,1,true);
	}
	
	@Override
	public void registerTileEntities()
	{
	    GameRegistry.registerTileEntity(cupboardTE.class, "cupboard");
	    GameRegistry.registerTileEntity(chairTE.class, "blockChair");
	    GameRegistry.registerTileEntity(freezerTE.class, "blockFreezer" );
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
	}
	@Override
	public void createFolders()
    {
        File file = new File(TLM.Dir, "assets/reallifemod");
        if(!file.exists())
        	file.mkdirs();
        File check=new File(file,"sounds");
        if(!check.exists())
        	check.mkdir();
        File names=new File(TLM.Dir,"sound.json");

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

       ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new RLMResourceListener());

    }
	
	public static void doNameUpdate(){
        File file = new File(TLM.Dir, "assets/reallifemod");
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
        File file = new File(TLM.Dir, "assets");
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
     		financialProps props=financialProps.get(p);
     		Money.put(p.getDisplayName(), props.Cash);
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
	
	public static void doSaving(){
		EntityPlayer player=FMLClientHandler.instance().getClient().thePlayer;
        File file = new File(TLM.Dir, "assets");
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