package co.uk.silvania.advancedarmoury.client.renderers.assault;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.IMagazine;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBase;
import co.uk.silvania.advancedarmoury.gun.EntityItemGun;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.assets.ComponentType;
import co.uk.silvania.advancedarmoury.items.generic.Barrel;
import co.uk.silvania.advancedarmoury.items.generic.FrontEnd;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverCasing;
import co.uk.silvania.advancedarmoury.items.generic.Stock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderAssaultRifle implements IItemRenderer {
	
	PartRenderBase renderer;
	ComponentType component;
	ModelRenderer upperArmModel;
	ModelRenderer foreArmModel;
	private float aimRotate;
	private float aimX;
	private float aimY;
	private float aimZ;
	private float speed;
	
	public RenderAssaultRifle() {
		this.upperArmModel = new ModelRenderer(new ModelBiped(), 40, 16);
		this.foreArmModel = new ModelRenderer(new ModelBiped(), 40, 20);
		this.aimRotate = 227F;
		this.speed = 1.0F;
		aimX = -0.25F;
		aimY = -0.15F;
		aimZ = -0.05F;	
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		ItemIInventory inventory = new AssaultIInventory(item);
		if (inventory != null) {
			if (inventory.getStackInSlot(4) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		ItemIInventory inventory = new AssaultIInventory(item);
		if (inventory != null) {
			ItemStack magazine = inventory.getStackInSlot(0);
			ItemStack barrel = inventory.getStackInSlot(2);
			ItemStack frontEnd = inventory.getStackInSlot(3);
			ItemStack receiver = inventory.getStackInSlot(4);
			ItemStack stock = inventory.getStackInSlot(5);
			
			ItemStack barrelAttachment = inventory.getStackInSlot(7);
			
			if (receiver != null) {
				if (receiver.getItem() instanceof ReceiverCasing) {
					ReceiverCasing compReceiver = (ReceiverCasing) receiver.getItem();
					
					float frontEndX = compReceiver.barrelX;
					float frontEndY = compReceiver.barrelY;
					float frontEndZ = compReceiver.barrelZ;
					
					float magX = compReceiver.magX;
					float magY = compReceiver.magY;
					float magZ = compReceiver.magZ;
					
					float stockX = compReceiver.stockX;
					float stockY = compReceiver.stockY;
					float stockZ = compReceiver.stockZ;
					
					//new AssaultReceiverRenderer(compReceiver.modelName, compReceiver.modelTexture, "Polymer", "Polymer", "Polymer");
					render(receiver, 0, 0, 0, type, item);
			
					if (frontEnd != null) {
						if (frontEnd.getItem() instanceof FrontEnd) {
							FrontEnd componentFrontEnd = (FrontEnd) frontEnd.getItem();
							render(frontEnd, frontEndX, frontEndY, frontEndZ, type, item);
							renderArm(type, data);
						}
					}
					
					if (barrel != null) {
						if (barrel.getItem() instanceof Barrel) {
							render(barrel, frontEndX, frontEndY, frontEndZ, type, item);
							
							if (barrelAttachment != null) {
								int barrelLength = barrel.stackTagCompound.getInteger("length");
								render(barrelAttachment, frontEndX - ((barrelLength - 1) * 0.05F), frontEndY, frontEndZ, type, item);
							}
						}
					}
					
					if (magazine != null) {
						if (magazine.getItem() instanceof IMagazine) {
							render(magazine, magX, magY, magZ, type, item);
						}
					}
					
					if (stock != null) {
						if (stock.getItem() instanceof Stock) {
							render(stock, stockX, stockY, stockZ, type, item);
						}
					}
				}
			}
		}
	}
	
	public void renderArm(ItemRenderType type, Object... data) {
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			if (data[1] instanceof EntityPlayer) {
				AbstractClientPlayer player = (AbstractClientPlayer) data[1];
				Minecraft.getMinecraft().renderEngine.bindTexture(player.getLocationSkin());
				this.upperArmModel.mirror = true;
				this.foreArmModel.mirror = true;
		        GL11.glPushMatrix();
		        float move = (aimRotate-227F);
		        GL11.glRotatef(-(move*2), 0F, 1F, 0F);
		        GL11.glTranslatef(2.75F+(move/2), -3.8F, -4.0F); //left-/right+ up+/down- near+/far-
		        this.upperArmModel.addBox(-1.95F, -5.9F, -2.2F, 4, 4, 4);
		        this.upperArmModel.setRotationPoint(0F, 6F, 0F);
		        this.upperArmModel.rotateAngleX = -0.625F; //Left-Right
				//this.upperArmModel.rotateAngleY = 0.5F; //Pivot
				this.upperArmModel.rotateAngleZ = -1.5F;
				this.upperArmModel.render(0.4F);
		        
		        //				  //up-/down+     left-/right+

				this.foreArmModel.addBox(-1.75F, -2F, -2F, 4, 8, 4); 	//THIS IS GOOD
				this.foreArmModel.setRotationPoint(0F, 6F, 0F);			//LEAVE IT ALONE
				this.foreArmModel.rotateAngleX = -0.25F; //Left-Right	//DON'T TOUCH IT
				this.foreArmModel.rotateAngleZ = -1.25F;				//BE LIKE A CHRISTIAN BOY AT NIGHT
				this.foreArmModel.render(0.4F);
				GL11.glPopMatrix();
			}
		}
	}
	
	public void render(ItemStack stack, float moveX, float moveY, float moveZ, ItemRenderType type, ItemStack gun) {
		float scale = 1.0F;
		float xOffset = 0;
		float yOffset = 0;
		float zOffset = 0;
		float rotX = 0;
		float rotY = 0;
		
		if (type == ItemRenderType.ENTITY) {
			scale = 2F; //TODO
			xOffset = 0;
			yOffset = 0;
			zOffset = 0;
			rotX = 0;
			rotY = 0;
		}
		if (type == ItemRenderType.EQUIPPED) {
			scale = 2F;
			xOffset = 0.35F;
			yOffset = -0.5F;
			zOffset = 0;
			rotX = 310;
			rotY = 75;
		}
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			if (aimRotate > 227F) { aimRotate = 227;}
			if (aimRotate < 225.5F) { aimRotate = 225.5F;}
			
			if (aimX < -0.25F) { aimX = -0.25F; }
			if (aimY < -0.15F) { aimY = -0.15F; }
			if (aimZ < -0.05F) { aimZ = -0.05F; }
			
			if (aimX > -0.15F) { aimX = -0.15F; }
			if (aimY > -0.1F)  { aimY = -0.1F; }
			if (aimZ > 0.08F)  { aimZ = 0.08F; }
			
			if (gun.stackTagCompound.getBoolean("aiming")) {
				if (aimRotate > 225.5F) { aimRotate -= 0.1F; }			
				if (aimX < -0.15F) { aimX += 0.05F; }
				if (aimY < -0.1F)  { aimY += 0.015F; }
				if (aimZ < 0.08F)  { aimZ += 0.05F; }
			} else {
				if (aimRotate < 227F) { aimRotate += 0.1F; }	
				if (aimX > -0.25F) { aimX -= 0.05F; }
				if (aimY > -0.15F) { aimY -= 0.015F; }
				if (aimZ > -0.05F) { aimZ -= 0.05F; }
			}
			scale = 8;
			xOffset = aimX; //Near+/far-
			yOffset = aimY;
			zOffset = aimZ; //Left+/Right-
			rotX = aimRotate;
			rotY = 0;
		}
		if (type == ItemRenderType.INVENTORY) {
			scale = 1;
			xOffset = 0;
			yOffset = 0;
			zOffset = 0;
			rotX = 0;
			rotY = 0;
		}
		
		EntityItem entityItem = new EntityItemGun(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
		entityItem.setEntityItemStack(stack);
		GL11.glPushMatrix();
		//Intitial rotations to get it back to where it should be before we add our own rotations.
		
		GL11.glRotatef(rotX, 0F, 1F, 0F); //X AXIS (We don't touch the Z axis/tilt)
		GL11.glRotatef(rotY, 0F, 0F, 1F); //Y AXIS (Coz we ain't gangsta.)
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(moveX + xOffset, moveY + yOffset, moveZ + zOffset);
		entityItem.hoverStart = -0.05F;
		RenderItem.renderInFrame = true;
		RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
		GL11.glPopMatrix();		
	}
}
