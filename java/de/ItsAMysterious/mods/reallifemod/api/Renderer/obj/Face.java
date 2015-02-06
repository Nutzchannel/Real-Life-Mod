package de.ItsAMysterious.mods.reallifemod.api.Renderer.obj;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Face {
	public Vertex[] vertices;
	public Vertex[] vertexNormals;
	public Vertex faceNormal;
	public TextureCoordinate[] textureCoordinates;
	
	
    @SideOnly(Side.CLIENT)
    public void addFaceForRendering(Tessellator tessellator)
    {
        addFaceForRendering(tessellator, 0.0005F);
    }
    
	@SideOnly(Side.CLIENT)
	public void addFaceForRendering(Tessellator tessellator, float TexturOffset){
		if(this.faceNormal==null){
			faceNormal=this.calculateNormal();
		}
		tessellator.setNormal(faceNormal.x, faceNormal.y, faceNormal.z);
		
		float averageU=0F;
		float averageV=0F;
		
		if((textureCoordinates!=null)&&(textureCoordinates.length>0)){
			for(int i = 0;i<textureCoordinates.length; i++)
			{
				averageU+=textureCoordinates[i].u;
				averageV+=textureCoordinates[i].v;
			}
			averageU=averageU/textureCoordinates.length;
			averageV=averageV/textureCoordinates.length;
		}
		
		float offsetU, offsetV;
		
		for(int i= 0; i<vertices.length; ++i)
		{
			if((textureCoordinates!=null)&&(textureCoordinates.length>0))
			{
				offsetU=TexturOffset;
				offsetV=TexturOffset;
				
				if(textureCoordinates[i].u>averageU)
				{
					offsetU=-offsetU;
				}
				if(textureCoordinates[i].v>averageU)
				{
					offsetV=-offsetV;
				}
				tessellator.addVertexWithUV(vertices[i].x, vertices[i].y, vertices[i].z, textureCoordinates[i].u+offsetU, textureCoordinates[i].v+offsetV);
			}
			else
			tessellator.addVertex(vertices[i].x, vertices[i].y, vertices[i].z);
		}
	}

	public Vertex calculateNormal() {
		Vec3 v1 = Vec3.createVectorHelper(vertices[1].x - vertices[0].x, vertices[1].y - vertices[0].y, vertices[1].z - vertices[0].z);
        Vec3 v2 = Vec3.createVectorHelper(vertices[2].x - vertices[0].x, vertices[2].y - vertices[0].y, vertices[2].z - vertices[0].z);
        Vec3 normalVector = null;

        normalVector = v1.crossProduct(v2).normalize();

        return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
	}

}
