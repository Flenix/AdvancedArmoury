package com.silvaniastudios.advancedarmoury.items_old.rounds;

import java.util.List;
import java.util.Random;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.IRound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemRound extends Item implements IRound {
	
	public ItemRound() {
		super();
		this.setCreativeTab(AdvancedArmoury.tabRounds);
	}
	
	@Override
	public void onShotBlock(ItemStack item, World world, Block blockHit, int x, int y, int z, int sideHit) {
		System.out.println("onShotBlock");
		String bullet = item.stackTagCompound.getString("bullet");
		if (!world.isRemote) {
			if (bullet.equalsIgnoreCase("exploding")) { shotExplosiveBlock(blockHit, world, x, y, z, sideHit); }
			if (bullet.equalsIgnoreCase("incendiary")) { shotIncendiaryBlock(blockHit, world, x, y, z, sideHit); }
		}
	}
	
	public void onShotEntity(ItemStack item, EntityLivingBase entityShot) {
		String bullet = item.stackTagCompound.getString("bullet");
		if (!entityShot.worldObj.isRemote) {
			if (bullet.equalsIgnoreCase("poison")) { shotPoisonEntity(entityShot); }
			if (bullet.equalsIgnoreCase("exploding")) { shotExplosiveEntity(entityShot); }
			if (bullet.equalsIgnoreCase("ArmourPiercing")) { shotArmourPiercingEntity(entityShot); }
			if (bullet.equalsIgnoreCase("Incendiary")) { shotIncendiaryEntity(entityShot); }
			if (bullet.equalsIgnoreCase("Uranium")) { shotUraniumEntity(entityShot); }
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack item) {
		if (item.stackTagCompound != null) {
			return item.stackTagCompound.getString("bullet") + " Round";
		}
		return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(item) + ".name")).trim();
    }
	
	public String bullets[] = {"ArmourPiercing", "Copper", "Exploding", "FMJ", "Incendiary", "Lead", "Poison", "Polymer", "Steel", "Tracer", "Uranium"};
	public String cases[] = {"Aluminium", "Brass", "Polymer", "Steel"};
	public int calibreId[] = {556, 762, 900, 127};
	
	@SideOnly(Side.CLIENT) public IIcon[] bulletIcons;
	@SideOnly(Side.CLIENT) public IIcon[] caseIcons;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		bulletIcons = new IIcon[bullets.length * calibreId.length]; //44 (0-43)
		caseIcons =   new IIcon[cases.length * calibreId.length]; //16 (0-15)
		
		for (int i = 0; i < calibreId.length; i++) {
			for(int j = 0; j < bullets.length; j++) {
				int k = i*bullets.length;
				bulletIcons[j+k] = icon.registerIcon(AdvancedArmoury.modid + ":" + "bullet" +  bullets[j] + calibreId[i]);
			}
		}

		for (int i = 0; i < calibreId.length; i++) {
			for (int j = 0; j < cases.length; j++) {
				int k = i*cases.length;
				caseIcons[j+k] = icon.registerIcon(AdvancedArmoury.modid + ":" + "case" + cases[j] + calibreId[i]);
			}
		}
	}
	
	public IIcon getBulletIcon(String bullet, int calibre) {
		for (int i = 0; i < calibreId.length; i++) {
			for (int j = 0; j < bullets.length; j++) {
				int k = i*bullets.length;
				if (bullet.equalsIgnoreCase(bullets[j])) {
					if (calibre == calibreId[i]) {
						return bulletIcons[j+k];
					}
				}
			}
		}

		System.out.println("OH NOES");
		return bulletIcons[0];
	}
	
	public IIcon getCaseIcon(String casing, int calibre) {
		for (int i = 0; i < calibreId.length; i++) {
			for (int j = 0; j < cases.length; j++) {
				int k = i*cases.length;
				if (casing.equalsIgnoreCase(cases[j])) {
					if (calibre == calibreId[i]) {
						return caseIcons[j+k];
					}
				}
			}
		}

		return caseIcons[0];
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		int meta = item.getItemDamage();
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
			if (entity instanceof EntityPlayer) {
				if (!world.isRemote) {
					EntityPlayer player = (EntityPlayer) entity;
					player.addChatComponentMessage(new ChatComponentText("Bad item spawning detected for a Round - switching to defaults."));
					player.addChatComponentMessage(new ChatComponentText("Next time, use the creative menu or NEI instead of cheat mods or commands."));
				}
			}
			item.stackTagCompound.setDouble("calibre", 5.56);
			item.stackTagCompound.setInteger("calibreId", 556);
			item.stackTagCompound.setString("bullet", "Lead");
			item.stackTagCompound.setString("case", "Brass");
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {		
		if (item.stackTagCompound != null) {
			list.add("Calibre: " + item.stackTagCompound.getDouble("calibre"));
			list.add("Bullet: " + item.stackTagCompound.getString("bullet"));
			list.add("Case: " + item.stackTagCompound.getString("case"));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		double[] cal = {5.56, 7.62, 9.00, 12.7};
		
		for (int i = 0; i < cal.length; i++) {
			for (int j = 0; j < bullets.length; j++) {
				for (int k = 0; k < cases.length; k++) {
					ItemStack itemRound = new ItemStack(item);
					
					itemRound.stackTagCompound = new NBTTagCompound();
					itemRound.stackTagCompound.setDouble("calibre", cal[i]);
					itemRound.stackTagCompound.setInteger("calibreId", calibreId[i]);
					itemRound.stackTagCompound.setString("bullet", bullets[j]);
					itemRound.stackTagCompound.setString("case", cases[k]);
					
					list.add(itemRound);
				}
			}
		}
	}
	
	public void shotPoisonEntity(EntityLivingBase entity) {
		Random rand = new Random();
		int chance = rand.nextInt(25);
		int effectTime = 20;
		if (chance == 5) { effectTime = 100; }
		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), effectTime, 1));
	}
	
	public void shotExplosiveEntity(EntityLivingBase entity) {
		Random rand = new Random();
		int chance = rand.nextInt(30);
		int explosionSize = 1;
		if (chance == 5) { explosionSize = 4; }
		entity.worldObj.createExplosion(entity, entity.posX, entity.posY, entity.posZ, explosionSize, false);
	}
	
	public void shotArmourPiercingEntity(EntityLivingBase entity) {}
	
	public void shotIncendiaryEntity(EntityLivingBase entity) {
		Random rand = new Random();
		int chance = rand.nextInt(5);
		if (chance == 3) {
			entity.setFire(2);
		}
	}
	
	public void shotUraniumEntity(EntityLivingBase entity) {
		entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 25, 3));
	}
	
	public void shotExplosiveBlock(Block block, World world, int x, int y, int z, int sideHit) {
		Random rand = new Random();
		int chance = rand.nextInt(30);
		int explosionSize = 1;
		if (chance == 5) { explosionSize = 4; }
		world.createExplosion(null, x, y, z, explosionSize, false);
	}
	
	public void shotIncendiaryBlock(Block block, World world, int x, int y, int z, int sideHit) {
		Random rand = new Random();
		int chance = rand.nextInt(5);
		if (chance == 3) {
			if (block.isFlammable(world, x, y, z, ForgeDirection.getOrientation(sideHit))) {
				world.setBlock(x, y, z, Blocks.fire);
			}
		}
	}
}
