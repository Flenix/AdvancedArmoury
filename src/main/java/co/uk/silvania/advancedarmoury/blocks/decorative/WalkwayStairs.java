package co.uk.silvania.advancedarmoury.blocks.decorative;

import java.util.List;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WalkwayStairs extends DecorativeBlockAA {

	public WalkwayStairs(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayStairsRenderID;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
	    int direction = MathHelper.floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
	    world.setBlockMetadataWithNotify(x, y, z, direction, 0);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {
		boolean hasCollision = false;
		int meta = world.getBlockMetadata(x, y, z);
		if (!(world.getBlock(x, y-1, z) instanceof WalkwayStairs)) {
			hasCollision = true;
			
			if (meta == 0) { //South-facing
				setBlockBounds(0.0F, 0.0F,  0.0F,  1.0F, 0.0625F, 0.25F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.3125F, 0.50F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.50F, 0.50F, 1.0F, 0.5625F, 0.75F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.75F, 0.75F, 1.0F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 1) { //West-facing
				setBlockBounds(0.75F, 0.0F, 0.0F,  1.00F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.50F, 0.25F, 0.0F, 0.75F, 0.3125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.25F, 0.50F, 0.0F, 0.50F, 0.5625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F,  0.75F, 0.0F, 0.25F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 2) { //North-facing
				setBlockBounds(0.0F, 0.0F, 0.75F,  1.0F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.25F, 0.50F, 1.0F, 0.3125F, 0.75F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.50F, 0.25F, 1.0F, 0.5625F, 0.50F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.0F, 0.75F, 0.0F,  1.0F, 0.8125F, 0.25F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			} else if (meta == 3) { //East-facing
				setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 0.0625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.25F, 0.25F, 0.0F, 0.50F, 0.3125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.50F, 0.50F, 0.0F, 0.75F, 0.5625F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
				setBlockBounds(0.75F, 0.75F, 0.0F, 1.00F, 0.8125F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
		}
		
		//Check it is a stairs, and not OPEN stairs.
		if (world.getBlock(x, y, z).getClass().equals(this)) { 
			boolean connectNorth = checkConnections(world, x, y, z-1, - 1); //z-1
			boolean connectEast  = checkConnections(world, x+1, y, z, - 1);  //x+1
			boolean connectSouth = checkConnections(world, x, y, z+1, - 1); //z+1
			boolean connectWest  = checkConnections(world, x-1, y, z, - 1);  //x-1
			
			boolean hitboxNorth = false;
			boolean hitboxEast  = false;
			boolean hitboxSouth = false;
			boolean hitboxWest  = false;
			
			if (meta == 0 || meta == 2) {
				if (!connectEast) { hitboxEast = true; }
				if (!connectWest) { hitboxWest = true; }
			} else if (meta == 1 || meta == 3) {
				if (!connectNorth) { hitboxNorth = true; }
				if (!connectSouth) { hitboxSouth = true; }
			}
			
			if (hitboxNorth) {
				hasCollision = true;
				setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.25F, 0.0625F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
			
			if (hitboxSouth) {
				hasCollision = true;
				setBlockBounds(0.0F, 0.0F, 0.9375F, 1.0F, 2.25F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
			
			if (hitboxEast) {
				hasCollision = true;
				setBlockBounds(0.9375F, 0.0F, 0.0625F, 1.0F, 2.25F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
			
			if (hitboxWest) {
				hasCollision = true;
				setBlockBounds(0.0F, 0.0F, 0.0F, 0.0625F, 2.25F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
	
			if (!hasCollision) {
				setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, bb, list, entity);
			}
			
			setBlockBoundsForItemRender();
		}
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public boolean checkConnections(World world, int x, int y, int z, int targetMeta) {
		if (world.getBlock(x, y, z).isNormalCube(world, x, y, z)) {
			return true;
		}
		if (world.getBlock(x, y, z) instanceof WalkwayStairs) {
			return true;
		}
		
		if (targetMeta >= 0) {
			if (world.getBlock(x, y - 1, z) instanceof WalkwayStairs) {
				int meta = world.getBlockMetadata(x, y - 1, z);
				if (meta == targetMeta || meta == (targetMeta + 2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
