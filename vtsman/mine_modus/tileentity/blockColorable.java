package vtsman.mine_modus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class blockColorable extends TileEntity{
	int color = 0x000000;
	@Override
	 public void readFromNBT(NBTTagCompound data)
	    {
	        super.readFromNBT(data);
	        color = data.getInteger("color");
	    }

	    /**
	     * Writes a tile entity to NBT.
	     */
	 @Override
		public void writeToNBT(NBTTagCompound data) {
			super.writeToNBT(data);
			data.setInteger("Color", color);
		}
}
