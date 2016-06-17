package co.uk.silvania.advancedarmoury.blocks.decorative;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockAA extends Block {

	String texture;
	
	public BlockAA(String textureName) {
		super(Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
		this.useNeighborBrightness = true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + this.texture);
	}
	
    public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
    
    @Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
    
    @SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.aaBlockRenderID;
	}
    
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}
    
    public void onBlockPlacedBy(World block, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int rot = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = block.getBlockMetadata(x, y, z);
    	if (meta == 1 || meta == 2) {
    		return;
    	}
        if (meta >= 4) {
	        if (rot == 0) {
	        	block.setBlockMetadataWithNotify(x, y, z, 2 | meta, 2); 
	        }
	
	        if (rot == 1) {
	        	block.setBlockMetadataWithNotify(x, y, z, 1 | meta, 2);
	        }
	
	        if (rot == 2) {
	        	block.setBlockMetadataWithNotify(x, y, z, 3 | meta, 2);
	        }
	
	        if (rot == 3) {
	        	block.setBlockMetadataWithNotify(x, y, z, 0 | meta, 2);
	        }
        } else {
        	block.setBlockMetadataWithNotify(x, y, z, item.getItemDamage(), 2);
        }
        System.out.println("Final after placing: " + block.getBlockMetadata(x, y, z));
    }
	
    public int onBlockPlaced(World world, int x, int y, int z, int side, float xHit, float yHit, float zHit, int meta) {
		if (side != 0 && (side == 1 || (double)yHit <= 0.5D)) {
			return 1;
		} else {
			return 2;
		}
        //return side != 0 && (side == 1 || (double)yHit <= 0.5D) && (meta == 1 || meta == 2) ? 2 : 1;
    }
}
