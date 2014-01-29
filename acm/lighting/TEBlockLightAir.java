//------------------------------------------------------
//
//   Greg's Lighting - Floodlight Beam Tile Entity
//
//------------------------------------------------------

package acm.lighting;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.common.*;

public class TEBlockLightAir extends BaseTileEntity {

	public int numOfSources = 0;
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		numOfSources = nbt.getInteger("numOfSources");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("numOfSources", numOfSources);
	}

}
