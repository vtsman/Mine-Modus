package vtsman.mine_modus.worldGen;

import java.util.Random;

import vtsman.mine_modus.block.modBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
public class enumOreGen implements IWorldGenerator { //Implements IWorldGenerator
@Override
public void generate(Random random, int chunkX, int chunkZ, World world, //This is the base generate method
IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

generateSurface(random,chunkX*16,chunkZ*16,world); //This makes it gen overworld (the *16 is important)


}
private int[] height = {24};
private int[] ChunkNum = {4};
private int[] ReplaceID = {1};
public void generateSurface(Random random, int x, int z, World w){
	for(int i = 0;i<this.height.length;i++){ //This goes through the ore metadata
		for(int ii=0;ii<ChunkNum[i];ii++){ //This makes it gen multiple times in each chunk
		int posX = x + random.nextInt(16); //X coordinate to gen at
		int posY = random.nextInt(height[i]); //Y coordinate less than 40 to gen at
		int posZ = z + random.nextInt(16); //Z coordinate to gen at
		new genOre(modBlocks.ore.blockID,i,random.nextInt(9), ReplaceID[i]).generate(w, random, posX, posY, posZ); //The gen call
		}
		}
}
}