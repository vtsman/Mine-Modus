package vtsman.mine_modus.worldGen.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class craig extends BiomeGenBase{

	public craig(int par1) {
		super(par1);
		this.waterColorMultiplier = 50;
		this.topBlock = (byte)Block.gravel.blockID;
		this.biomeName = "Craig";
		this.maxHeight = 100;
		// TODO Auto-generated constructor stub
	}

}
