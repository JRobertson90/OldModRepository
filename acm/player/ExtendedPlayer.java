package acm.player;

import java.util.UUID;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import acm.ACM;
import acm.item.ACMItem;
import acm.network.ACMPacketInfluence;

public class ExtendedPlayer implements IExtendedEntityProperties
{
public final static String EXT_PROP_NAME = "XJ_ACM";

private final EntityPlayer player;

public int weaponToReturnTo = 9;
public int netherArmorCount = 0;
public boolean wasInitialized = false;

	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
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
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}
	
	public void tickInitialize()
	{
		this.onInventoryChanged();
		this.wasInitialized = true;
	}
	
	public void onInventoryChanged()
	{
		//
		//Begin stealth leggings code
		//
		final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
		//Get attribute information from player and get our modifier ready
		BaseAttributeMap attributes = player.getAttributeMap();
		AttributeModifier modifier;
		//Create our Attribute modifier, and select the value by which to increase the speed based on if they are wearing camo leggings or not.
        modifier = new AttributeModifier(movementSpeedUID, "Camo leggings speed change", ACM.playerIsWearingItem(player, ACMItem.camoLegs) ? 0.07d : 0d, 0);
        //Add modifier to Multimap list
		Multimap modifiersToAdd = ArrayListMultimap.create();
        modifiersToAdd.put("generic.movementSpeed", modifier);
        attributes.applyAttributeModifiers(modifiersToAdd);
        //
        //End stealth leggings code
        //
        //Begin Nether armor counting code
        //
        int armorCount = 0;
        armorCount += ACM.playerIsWearingItem(player, ACMItem.netherHelm) ? 1 : 0;
        armorCount += ACM.playerIsWearingItem(player, ACMItem.netherPlate) ? 1 : 0;
		armorCount += ACM.playerIsWearingItem(player, ACMItem.netherLegs) ? 1 : 0;
		armorCount += ACM.playerIsWearingItem(player, ACMItem.netherBoots) ? 1 : 0;
        this.netherArmorCount = armorCount;
        //
        //End Nether armor counting code
        //
	}
}
