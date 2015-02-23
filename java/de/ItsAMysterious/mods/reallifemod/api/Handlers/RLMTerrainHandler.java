package de.ItsAMysterious.mods.reallifemod.api.handlers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;

public class RLMTerrainHandler {

	@SubscribeEvent
	public void onTreeGrow(SaplingGrowTreeEvent event){
		int posX=event.x;
		int posY=event.y;
		int posZ=event.z;
		System.out.println("Grown tree!");
	}
	
	@SubscribeEvent(priority=EventPriority.HIGH, receiveCanceled=false)
	public void onChunkLoad(PopulateChunkEvent.Pre event)
	{
	    Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX, event.chunkZ);
	    BlockAirExtended toBlock = new BlockAirExtended(); // change this to suit your need
	    Block FromBlock = Blocks.air; // change this to suit your need

	    for (ExtendedBlockStorage storage : chunk.getBlockStorageArray()) 
	    {
	        if (storage != null) 
	        {
	            for (int x = 0; x < 16; ++x) 
	            {
	                for (int y = 0; y < 16; ++y) 
	                {
	                    for (int z = 0; z < 16; ++z) 
	                    {
	                        if (storage.getBlockByExtId(x, y, z) == FromBlock) 
	                        {
	                            storage.func_150818_a(x, y, z, toBlock);
	                        }
	                    }
	                }
	            }
	        }
	    }  
	    chunk.isModified = true; // this is important as it marks it to be saved
	}
	

}
