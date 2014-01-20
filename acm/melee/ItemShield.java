package acm.melee;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import acm.item.ACMItem;
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
	
	
	public static float correctOverFlowedAngle(float input)
	{
		if(input>180)
		{
			return input-360;
		}
		else if(input<=-180)
		{
			return input+360;
		}
		return input;
	}
	
	public void shouldBlock(LivingAttackEvent event)
	{
		EntityPlayer player = (EntityPlayer) event.entity;
		ExtendedPlayer props = ExtendedPlayer.get(player);
		Entity attacker = event.source.getSourceOfDamage();
		if(attacker != null)
		{
			boolean attackedWithWarHammer = false;
			if(attacker instanceof EntityPlayer)
			{
				EntityPlayer attackerPlayer = (EntityPlayer) attacker;
				if(attackerPlayer.inventory.getCurrentItem() != null
				&& attackerPlayer.inventory.getCurrentItem().getItem() instanceof ItemWarHammer)
				{
					attackedWithWarHammer = true;
				}
			}
			if(!attackedWithWarHammer)
			{
				float attackedAtYaw = 0;
				if (attacker != null)
                {
                    attackedAtYaw = correctOverFlowedAngle(attacker.rotationYaw-180);
                }
				final float shieldArcDegrees = 60;
				float rangeSideLeft = correctOverFlowedAngle(attackedAtYaw + shieldArcDegrees);
				float rangeSideRight = correctOverFlowedAngle(attackedAtYaw - shieldArcDegrees);
				//This code checks if the values crossed the angle loop and corrects them if
				//they have
				float playerYaw = correctOverFlowedAngle(player.rotationYaw);
				if(attacker instanceof IProjectile)
				{
					playerYaw *= -1;
				}
				boolean inRange;
				inRange = rangeSideLeft >= playerYaw && rangeSideRight <= playerYaw;
				if(rangeSideRight>rangeSideLeft)
				{
					inRange = rangeSideLeft <= playerYaw || rangeSideRight >= playerYaw;
				}
				if(inRange)
				{
					if(!(this.itemID == ACMItem.woodShield.itemID && attacker.isBurning()))
					{
						//Knockback code!!  Copied from various sections of minecraft source then tweaked some.
				    	double d0 = player.posX - attacker.posX;
				        double d1;

				        for (d1 = player.posZ - attacker.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
				        {
				            d0 = (Math.random() - Math.random()) * 0.01D;
				        }
				        attacker.isAirBorne = true;
				    	final float knockbackStrengthFactor = 5.0F/1.5F;
				        float f1 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				        float f2 = 0.8f;
				        if(!(attacker instanceof IProjectile))
				        {
				        	attacker.motionX /= 2.0D;
					        attacker.motionY /= 2.0D;
					        attacker.motionZ /= 2.0D;
					        attacker.motionX -= d0 / f1 * f2;
					        attacker.motionY += (double)f2/3;
					        attacker.motionZ -= d1 / f1 * f2;
				        }
				        if(this.itemID == ACMItem.netherShield.itemID)
				        {
				        	attacker.setFire(5);
				        }
						event.setCanceled(true);
						player.inventory.getCurrentItem().damageItem(1, player);
					}
				}
			}
		}
	}
}
