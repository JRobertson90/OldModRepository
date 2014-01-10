package acm.ranged;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import acm.entity.ArrowFar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGunpowderBow extends Item
{
    private Icon iconActive;
    private Icon iconStandby;
    public int messageCooldown = 0;

    public ItemGunpowderBow(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setUnlocalizedName("gunpowderbow");
        this.setTextureName("acm:gunpowderbow");
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
    	//setIconStandby();
        int j = 72000;

        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || (par3EntityPlayer.inventory.hasItem(Item.arrow.itemID) && par3EntityPlayer.inventory.hasItem(Item.gunpowder.itemID)))
        {
            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if (f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            ArrowFar entityarrow = new ArrowFar(par2World, par3EntityPlayer, f * 2.0F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                entityarrow.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
                par3EntityPlayer.inventory.consumeInventoryItem(Item.gunpowder.itemID);
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(entityarrow);
            }
        }
    }
    
    @Override
	public boolean isFull3D()
    {
        return true;
    }

    @Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 100;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
    	/*if(flag || (par3EntityPlayer.inventory.hasItem(Item.arrow.itemID) && par3EntityPlayer.inventory.hasItem(Item.gunpowder.itemID)))
    	{
    		setIconActive();
    	}*/
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (flag || (par3EntityPlayer.inventory.hasItem(Item.arrow.itemID) && par3EntityPlayer.inventory.hasItem(Item.gunpowder.itemID)))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }
        else
        {
        	if(messageCooldown==0)
        	{
        		par3EntityPlayer.addChatMessage("You need an arrow and gunpowder to shoot this.");
            	messageCooldown = 10;
        	}
        	else
        	{
        		messageCooldown--;
        	}
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    @Override
	public int getItemEnchantability()
    {
        return 1;
    }
    
    public void setIconActive()
    {
    	this.itemIcon = this.iconActive;
    }
    
    public void setIconStandby()
    {
    	this.itemIcon = this.iconStandby;
    }

    @Override
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString() + "_standby");
        this.iconActive = par1IconRegister.registerIcon(this.getIconString() + "_active");
        this.iconStandby = this.itemIcon;
    }

    @SideOnly(Side.CLIENT)

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    public Icon getItemIconForUseDuration(int par1)
    {
        return this.iconActive;
    }
}
