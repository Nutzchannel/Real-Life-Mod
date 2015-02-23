package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.gui.container.ContainerCBO;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.TileEntityCBO;

public class CustomBlockGui extends GuiContainer
{
	private TileEntityCBO tileCBO;
	public static Block currentBlock;
	public static int blockX;
	public static int blockY;
	public static int blockZ;
	int bx=0;
	int by=0;
	int bz=0;
	private GuiTextField EditX;
	private GuiTextField EditY;
	private GuiTextField EditZ;
	public static Block theItem;
	public static String xs;
	public static String ys;
	public static String zs;
	public static int blockID;
	
	public CustomBlockGui(int xi,int yi,int zi,InventoryPlayer inventory,TileEntityCBO tile )
	{	super(new ContainerCBO(inventory,tile));
		this.tileCBO = tile;
		blockX=xi;
		blockY=yi;
		blockZ=zi;
		CustomBlockGui.currentBlock=(Block)Block.blockRegistry.getObjectById(0);
        CustomBlockGui.blockID=Block.blockRegistry.getIDForObject(currentBlock);

	}
	
	@Override
	public void initGui(){
		Keyboard.enableRepeatEvents(true);
    	this.buttonList.add(new GuiButton(0, 20, 20, 200, 20, "Block setzen"));    	
		this.buttonList.add(new GuiButton(1, 20, 50, 200, 20, "Block löschen"));
		this.buttonList.add(new GuiButton(2, 20, 80, 200, 20, "Block umwandeln"));
		this.buttonList.add(new GuiButton(3, 20, 110, 200, 20, "Blocks auffüllen"));
		this.buttonList.add(new GuiButton(4,width-50,height-30,40,20, "Abbrechen"));
		this.buttonList.add(new GuiButton(5,width-100,height-100, 70, 20, "Hilfe"));    	
    	this.buttonList.add(new GuiButton(6,width-110,height-30,40,20,"OK"));
    	this.buttonList.add(new GuiButton(7,220,200,20,10,"+"));
    	this.buttonList.add(new GuiButton(8,220,210,20,10,"-"));
        this.EditX = new GuiTextField(this.fontRendererObj, this.width-270, 60, 250, 20);
        this.EditX.setFocused(true);
        this.EditX.setMaxStringLength(100);
        this.EditX.setText("X");
        this.EditY = new GuiTextField(this.fontRendererObj, this.width-270, 100, 250, 20);
        this.EditY.setFocused(false);
        this.EditY.setMaxStringLength(100);
        this.EditY.setText("Y");        
        this.EditZ = new GuiTextField(this.fontRendererObj, this.width- 270, 140, 250, 20);
        this.EditZ.setFocused(false);
        this.EditZ.setMaxStringLength(100);
        this.EditZ.setText("Z");
        this.EditX.setCanLoseFocus(true);
        this.EditY.setCanLoseFocus(true);
        this.EditZ.setCanLoseFocus(true);

        

	}
	
	@Override
    public void drawScreen(int par1, int par2, float par3) 
    {
		GL11.glColor4f(0.9F, 0.9F, 1.0F, 1.0F);
        super.drawScreen(par1, par2, par3);
        this.EditX.drawTextBox();
        this.EditY.drawTextBox();
        this.EditZ.drawTextBox();
        this.EditX.setEnableBackgroundDrawing(true);
        this.EditY.setEnableBackgroundDrawing(true);
        this.EditZ.setEnableBackgroundDrawing(true);

    }
	
    @Override
	public void updateScreen()
    {	this.EditX.updateCursorCounter();
        this.EditY.updateCursorCounter();
        this.EditZ.updateCursorCounter();
		}
	
	
    @Override
    public void actionPerformed(GuiButton button){
   	if (button.id==4)
   	{
   		mc.setIngameFocus();
   		mc.displayGuiScreen(null);   		
   	}
   	if(button.id==0)
   	{	
   		/**bx=Integer.valueOf(EditX.getText());
   		by=Integer.valueOf(EditY.getText());
   		bz=Integer.valueOf(EditZ.getText());
   		World world = mc.theWorld;
		for(int i=0;i<bx;i++)
		{
			world.setBlock(blockX+i, blockY, blockZ, this.currentBlock);
			for(int i2=0;i2<by;i2++){
				world.setBlock(blockX+i, blockY+i2, blockZ, this.currentBlock);
				for(int i3=0;i3<bz;i3++){
					world.setBlock(blockX+i, blockY+i2, blockZ+i3, this.currentBlock);
					}
				}
   			}*/
   		}  			
   		
   	if(button.id==1){
   		{
   		/**bx=Integer.parseInt(EditX.getText());
   		by=Integer.parseInt(EditY.getText());
   		bz=Integer.parseInt(EditZ.getText());
   		
   	   		World world = mc.theWorld;
   	 			
   	   			for(int i=0;i<bx;i++)
   	   			{
   	   				world.setBlock(blockX+i, blockY, blockZ,  Blocks.air);
   	   				for(int i2=0;i2<by;i2++){
   	   					world.setBlock(blockX+i, blockY+i2, blockZ,  Blocks.air);
   	   					for(int i3=0;i3<bz;i3++){
   	   						world.setBlock(blockX+i, blockY+i2, blockZ+i3, Blocks.air);
   	   						}
   	   					}
   	   			}*/ }	
   	}
   	if(button.id==7){
   		/*if (blockID>=1){
   		blockID++;
   		this.currentBlock=(Block) Block.blockRegistry.getObjectById(blockID);
   		}
   		else blockID=1;*/
   	}
   	if(button.id==8){
   		/*if (blockID>=1){
   		blockID--;
   		this.currentBlock=(Block) Block.blockRegistry.getObjectById(blockID);
   		}
   		else blockID=1;*/
   	}
   	
   	}
    
    @Override
	protected void keyTyped(char zeichen, int id)
    {
    	if(EditX.textboxKeyTyped(zeichen, id)){};
    	if(EditY.textboxKeyTyped(zeichen, id)){};
    	if(EditZ.textboxKeyTyped(zeichen, id)){};
    	super.keyTyped(zeichen, id);
    }
    
    @Override
	protected void mouseClicked(int x, int y, int button){
    	EditX.mouseClicked(x, y,button);
    	EditY.mouseClicked(x, y,button);
    	EditZ.mouseClicked(x, y,button);
    	super.mouseClicked(x, y, button);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2,
                    int par3) {
    		ResourceLocation texture1= new ResourceLocation("tbme:textures/gui/PlaylistBackground.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(texture1);
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
    }

}
