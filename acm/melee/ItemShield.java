package acm.melee;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import acm.network.ACMPacketInfluence;
import acm.player.ExtendedPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemShield extends Item{

	private final EnumToolMaterial toolMaterial;
	
	public ItemShield(int par1, EnumToolMaterial material) {
		super(par1);
		this.maxStackSize = 1;
		this.toolMaterial = material;
        String texturePrefix = material.name().toLowerCase().equals("emerald") ? "diamond" : material.name().toLowerCase();
        this.setTextureName("acm:"+texturePrefix+"_shield");
        this.setMaxDamage(material.getMaxUses());
        this.setUnlocalizedName(texturePrefix+"_shield");
        this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public boolean isFull3D()
    {
        return true;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
		{
			ExtendedPlayer props = ExtendedPlayer.get(par3EntityPlayer);
			if(props.weaponToReturnTo != 9)
			{
    			par3EntityPlayer.inventory.currentItem = props.weaponToReturnTo;
				props.weaponToReturnTo = 9;
			}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		ExtendedPlayer props = ExtendedPlayer.get(par3EntityPlayer);
//		if(props.getStamina() >= ExtendedPlayer.blockStaminaCost)
//		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
//		}
//		else
//		{
//			ACMPacketInfluence.manuallyFlashStamina(par3EntityPlayer);
//			this.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, 0);
//		}
		return par1ItemStack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}
