package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SlabMilitaryBase extends BlockSlab {
	
	String texture;
	Block parent;

	public SlabMilitaryBase(Block block, String textureName) {
		super(false, Material.rock);
		this.texture = textureName;
		this.parent = block;
		this.setHardness(1.0F);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		return null;
	}
	
	//Make "double slabs". I chose to implement this way, because having a double slab as a seperate block,
	//despite being identical to the parent block the slabs are made from,
	//is the silliest idea ever.
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
		System.out.println("onBlockActivated");
		if (!world.isRemote) {
			System.out.println("remote");
			if (player.getHeldItem() != null) {
				System.out.println("item != null");
				if (Block.getBlockFromItem(player.getHeldItem().getItem()) == this) {
					System.out.println("item == parent");
					world.setBlock(x, y, z, parent);
					if (!player.capabilities.isCreativeMode) {
						player.getHeldItem().stackSize--;
					}
				}
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + this.texture);
	}
	
	@Override
	public String getLocalizedName() {
        return StatCollector.translateToLocal("material." + texture) + " " + StatCollector.translateToLocal("blockType.slab");
    }
}
