package acm.melee;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import acm.item.ACMItem;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDagger extends Item
{
    private float weaponDamage;
    public final EnumToolMaterial toolMaterial;
    public boolean poisonous = false;

    public ItemDagger(int par1, boolean poison, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1);
        this.poisonous = poison;
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        String texturePrefix = this.getToolMaterialName().toLowerCase().equals("emerald") ? "diamond" : this.getToolMaterialName().toLowerCase();
        if(this.poisonous)
        {
        	texturePrefix = texturePrefix+"_poisonous";
        }
        this.setTextureName("acm:"+texturePrefix+"_dagger");
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setUnlocalizedName(texturePrefix+"_dagger");
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.weaponDamage = 3.0F + par2EnumToolMaterial.getDamageVsEntity();
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
    	//DON'T MOVE A SINGLE DANG BIT!
        par2EntityLivingBase.motionX = 0;
        par2EntityLivingBase.motionY = 0;
        par2EntityLivingBase.motionZ = 0;
        //Apply poison
        if(this.poisonous)
        {
        	par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 75, 1));
        }
    	//Damage item stack
        par1ItemStack.damageItem(1, par3EntityLivingBase);
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
    
    public ItemDagger getPoisoned()
    {
    	for(int i = 0; i<Item.itemsList.length;i++)
    	{
    		if(Item.itemsList[i] instanceof ItemDagger)
    		{
    			ItemDagger dagger = (ItemDagger) Item.itemsList[i];
    			EnumToolMaterial material = ReflectionHelper.getPrivateValue(ItemDagger.class, dagger, "toolMaterial");
    			if(dagger.poisonous && material.equals(this.toolMaterial))
    			{
    				return dagger;
    			}
    		}
    	}
    	return null;
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
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
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
