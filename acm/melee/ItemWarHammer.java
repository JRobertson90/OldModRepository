package acm.melee;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWarHammer extends Item
{
    private float weaponDamage;
    private final EnumToolMaterial toolMaterial;

    public ItemWarHammer(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1);
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        String texturePrefix = this.getToolMaterialName().toLowerCase().equals("emerald") ? "diamond" : this.getToolMaterialName().toLowerCase();
        this.setTextureName("acm:"+texturePrefix+"_warhammer");
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setUnlocalizedName(texturePrefix+"_warHammer");
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.weaponDamage = 5.0F + par2EnumToolMaterial.getDamageVsEntity();
    }

    public float func_82803_g()
    {
        return this.toolMaterial.getDamageVsEntity();
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    @Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if (par2Block.blockID == Block.web.blockID)
        {
            return 15.0F;
        }
		Material material = par2Block.blockMaterial;
		return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin ? 1.0F : 1.5F;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
    	
    	if( ! (par3EntityLivingBase instanceof EntityPlayer)) {
    		return false;
    	}
    	
    	EntityPlayer attacker = (EntityPlayer) par3EntityLivingBase;
    	
    	if( ! (attacker.inventory.getCurrentItem().getItem() instanceof ItemWarHammer)) {
    		return false;
    	}
    	
    	//Knockback code!!  Copied from various sections of minecraft source then tweaked some.
    	double d0 = attacker.posX - par2EntityLivingBase.posX;
        double d1;

        for (d1 = attacker.posZ - par2EntityLivingBase.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
        {
            d0 = (Math.random() - Math.random()) * 0.01D;
        }
    	par2EntityLivingBase.isAirBorne = true;
    	final float knockbackStrengthFactor = 5.0F/1.5F;
        float f1 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
        float f2 = (this.weaponDamage/knockbackStrengthFactor);
        par2EntityLivingBase.motionX /= 2.0D;
        par2EntityLivingBase.motionY /= 2.0D;
        par2EntityLivingBase.motionZ /= 2.0D;
        par2EntityLivingBase.motionX -= d0 / f1 * f2;
        par2EntityLivingBase.motionY += (double)f2/3;
        par2EntityLivingBase.motionZ -= d1 / f1 * f2;
        attacker.worldObj.playSoundAtEntity(attacker, "acm:boom.boom", this.weaponDamage/10, 1.0F);
    	//Damage item stack
        par1ItemStack.damageItem(1, attacker);
        return true;
    }

    @Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(2, par7EntityLivingBase);
        }

        return true;
    }

    @Override
	@SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block.blockID == Block.web.blockID;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    @Override
	public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponDamage, 0));
        return multimap;
    }
}
