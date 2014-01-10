package acm.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import acm.network.ACMPacketInfluence;

public class ExtendedPlayer implements IExtendedEntityProperties
{
public final static String EXT_PROP_NAME = "XJ_ACM";

private final EntityPlayer player;

//public static final int stamina_watcher = 20;
//public static final int stamina_cooldown_watcher = 21;

public int weaponToReturnTo = 9;
//public int flashingTicksRemaining = 0;
//public static int flashingTicksMax = 40;
//public static int maxStamina = 200;
//public static int staminaRegen = 6;
//public static int maxStaminaRegenCooldown = 20;
//public static int blockStaminaCost = 30;
//public final String nbtName = "stamina";
//public final String nbtCooldownName = "staminaCooldown";
//public final String nbtFlashingName = "staminaFlashing";

	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
//		this.player.getDataWatcher().addObject(stamina_watcher, this.maxStamina);
//		this.player.getDataWatcher().addObject(stamina_cooldown_watcher, 0);
	}

/**
* Used to register these extended properties for the player during EntityConstructing event
* This method is for convenience only; it will make your code look nicer
*/
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}

/**
* Returns ExtendedPlayer properties for player
* This method is for convenience only; it will make your code look nicer
*/
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}


	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();
		
//		properties.setInteger(nbtName, this.player.getDataWatcher().getWatchableObjectInt(stamina_watcher));
//		properties.setInteger(nbtCooldownName, this.player.getDataWatcher().getWatchableObjectInt(stamina_cooldown_watcher));
		/*
		Now add our custom tag to the player's tag with a unique name (our property's name). This will allow you to save multiple types of properties and distinguish between them. If you only have one type, it isn't as important, but it will still avoid conflicts between your tag names and vanilla tag names. For instance, if you add some "Items" tag, that will conflict with vanilla. Not good. So just use a unique tag name.
		*/
		compound.setTag(EXT_PROP_NAME, properties);
	}

	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		// Get our data from the custom tag compound
//		this.player.getDataWatcher().updateObject(stamina_watcher, properties.getInteger(nbtName));
//		this.player.getDataWatcher().updateObject(stamina_cooldown_watcher, properties.getInteger(nbtCooldownName));
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}
	
//	public final int getStamina()
//	{
//		return this.player.getDataWatcher().getWatchableObjectInt(stamina_watcher);
//	}
	
//	public final int getFlashingTicksRemaining()
//	{
//		return flashingTicksRemaining;
//	}
//	public final void reduceFlashingTicksRemaining()
//	{
//		flashingTicksRemaining--;
//	}
//	
//	public boolean checkAndUseStamina(int staminaToUse)
//	{
//		int stamina = this.player.getDataWatcher().getWatchableObjectInt(stamina_watcher);
//		int staminaRegenCooldownRemaining = this.player.getDataWatcher().getWatchableObjectInt(stamina_cooldown_watcher);
//		if(player.capabilities.isCreativeMode)
//		{
//			return true;
//		}
//		if(stamina>=staminaToUse)
//		{
//			stamina -= staminaToUse;
//			staminaRegenCooldownRemaining = maxStaminaRegenCooldown;
//			this.player.getDataWatcher().updateObject(stamina_watcher, stamina);
//			this.player.getDataWatcher().updateObject(stamina_cooldown_watcher, staminaRegenCooldownRemaining);
//			return true;	
//		}
//		ACMPacketInfluence.manuallyFlashStamina(player);
//		return false;
//	}
//	
//	public void regenerateStamina()
//	{
//		int stamina = this.player.getDataWatcher().getWatchableObjectInt(stamina_watcher);
//		int staminaRegenCooldownRemaining = this.player.getDataWatcher().getWatchableObjectInt(stamina_cooldown_watcher);
//        if(stamina<maxStamina)
//        {
//        	if(staminaRegenCooldownRemaining <= 0)
//        	{
//        		//This code prevents overflow of the stamina bar, if the bar is about to overflow just set it equal to max stamina.
//        		if(maxStamina-stamina<staminaRegen)
//            	{
//        			stamina = maxStamina;
//            	}
//            	else
//            	{
//            		stamina += staminaRegen;
//            	}
//        	}
//        	else
//        	{
//        		if(staminaRegenCooldownRemaining>0)
//        		{
//        			staminaRegenCooldownRemaining -=1;
//        		}
//        	}
//        	int preStamina = this.player.getDataWatcher().getWatchableObjectInt(stamina_watcher);
//    		int preStaminaRegenCooldownRemaining = this.player.getDataWatcher().getWatchableObjectInt(stamina_cooldown_watcher);
//    		if(preStamina != stamina)
//    		{
//    			this.player.getDataWatcher().updateObject(stamina_watcher, stamina);
//    		}
//        	if(preStaminaRegenCooldownRemaining != staminaRegenCooldownRemaining)
//        	{
//        		this.player.getDataWatcher().updateObject(stamina_cooldown_watcher, staminaRegenCooldownRemaining);
//        	}
//        }
//	}
}
