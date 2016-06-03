package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.items_old.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.network.LocateDamagePacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;

public class HandlerOfEvents {
	
	public double moveX;
	public double moveY;
	public double moveZ;
	
	//Handles all the events except mob drops. See MobDropHandler for that.
	
	//Cancels left-click and middle-click when holding a gun.
	//Needed to prevent block breaking and block picking when firing/melee.
    @SubscribeEvent
    public void mouseClickEvent(MouseEvent event) {
    	ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
    	if (item != null && item.getItem() instanceof GunFrame) {
	    	if (event.button == 2) {
	    		event.setCanceled(true);
	    	}
    	}
    }
    
    //increaseHealth() Increases player's maximum health drastically, to give gun damages more room to play with.
    //@SubscribeEvent public void onRespawn(PlayerRespawnEvent event) { increaseHealth(event.player); }
    
    //@SubscribeEvent public void onLivingUpdate(LivingUpdateEvent event) {
    	//99% for existing worlds, but good to have to be safe. If entity's health hasn't been increased, we increase it.
    	//TODO increaseHealth(event.entity);
    	//Applies the weight of whatever the entity has, restricting their movement.
    	//TODO applyWeight(event.entity);
    //}
        
    //TODO add support for mods which also increase health. Current known mods:
    //Tinkers Construct, DifficultLife
    //Mods need to be open source (Or at least have a deobfuscated jar available) for me to be able to support them.
    /*public void increaseHealth(Entity eventEntity) {
    	if (eventEntity instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) eventEntity;
			
			//Makes the modification unique so that other mods can still modify the modified modifier and your mod won't be confused by the other mods mods.
			//Generated using https://www.uuidgenerator.net/
			final UUID healthModUuid = UUID.fromString("a56f642f-d7bf-44a5-8897-c416a340c412");
			AttributeModifier healthBump = (new AttributeModifier(healthModUuid, "healthBump", 9, 2));
			IAttributeInstance health = entity.getEntityAttribute(SharedMonsterAttributes.maxHealth);
			if (health.getModifier(healthModUuid) == null) {
				health.applyModifier(healthBump);
				entity.setHealth(entity.getMaxHealth());
			}
    	}
    }
    
    public void applyWeight(Entity eventEntity) {
    	if (eventEntity instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) eventEntity;
    		int totalWeight = 0;
    		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
    			ItemStack item = player.inventory.getStackInSlot(i);
    			
    			if (item != null && item.stackTagCompound != null) {
    				totalWeight += item.stackTagCompound.getInteger("weight");
    			}
    		}
    		
    		//We add the held item twice (It was caught in the loop above) because the held item throws balance more.
    		//When you're running with a weapon in hand, it's harder than running with one slung over your shoulder
    		//Therefore weilding a gun impacts you more than just having it in your inventory.
    		ItemStack heldItem = player.getHeldItem();
    		if (heldItem != null && heldItem.stackTagCompound != null) {
    			totalWeight += heldItem.stackTagCompound.getInteger("weight");
    		}
    		
    		player.moveEntityWithHeading(player.moveStrafing, player.moveForward * 1.2F);
    		//player.capabilities.setPlayerWalkSpeed(0.5F);
    		//System.out.println("Total weight: " + totalWeight);
    	}
    }*/
    
    //Also increase the overall damage, unless the damagesource is from AA.
    //By doing this, we have no impact on the health increase as far as any other mod is concerned.
    //Damage balance tables are totally unaffected, unless another mod directly supports it.
    @SubscribeEvent
    public void onHurtEvent(LivingHurtEvent event) {
    	/*if (event.source instanceof DamageSourceShot || event.source instanceof DamageSourceMelee) {
    		//System.out.println("Shot/melee. No modification.");
    		return;
    	}
    	double rand = new Random().nextDouble();
    	event.ammount = (float) (event.ammount * (8 + (rand*2)));*/
    	
    	//While we're here, fire off a packet saying where abouts the attacker is in relation to the player.
    	//We use this for displaying located damage on-screen, which has to be done client-side.
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (event.source.getEntity() instanceof EntityLivingBase) {
				EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
				
				float angle1 = attacker.rotationYaw + 180F;
				float angle2 = player.rotationYaw + 180F;

				float angle = angle1 - angle2;
				
				while (angle > 360) { angle = angle-360; }
				while (angle < 0) {  angle = angle+360; }
				AdvancedArmoury.network.sendTo(new LocateDamagePacket(angle), (EntityPlayerMP) player);
			} else {
				AdvancedArmoury.network.sendTo(new LocateDamagePacket(-1), (EntityPlayerMP) player);
			}
    	}
    	
    	//System.out.println("Entity: " + event.entity.getCommandSenderName() + ", Amount: " + event.ammount + ", Health: " + event.entityLiving.getHealth() + "/" + event.entityLiving.getMaxHealth() + ", Source: " + event.source);
    }
    
    /*
    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent.Specials event) {
    	if (event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof GunFrame) {
    		RenderPlayerCustom renderPlayer = new RenderPlayerCustom();
    		event.
    		
    		if (!(event.renderer instanceof RenderPlayerCustom)) {
    			event.setCanceled(true);
    			System.out.println("Player: " + event.entityLiving);
        		renderPlayer.doRender((EntityLivingBase) event.entityLiving, 0, 0, 0, 1.0F, event.partialRenderTick);
    		}
    	}
    }
    */
    
    /*@SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	double doubleX = mc.thePlayer.posX - 0.5;
    	double doubleY = mc.thePlayer.posY + 0.1;
    	double doubleZ = mc.thePlayer.posZ - 0.5;
    	
    	Vec3 vec3 = mc.thePlayer.getLookVec();//mc.thePlayer.rayTrace(200, 1.0f).hitVec;
    	
    	GL11.glPushMatrix();
    	GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
    	GL11.glColor3ub((byte)255, (byte)255, (byte)255);
    	float mx = 9;
    	float my = 9;
    	float mz = 9;
    	GL11.glBegin(GL11.GL_LINES);
    	GL11.glVertex3d(doubleX, doubleY, doubleZ);
    	GL11.glVertex3d(vec3.xCoord + doubleX, vec3.yCoord + doubleY, vec3.zCoord + doubleZ);
    	//GL11.glVertex3f(mx+0.4f, my, mz-0.4f);
    	//GL11.glVertex3f(mx-0.4f, my, mz+0.4f);
    	GL11.glEnd();
    	GL11.glPopMatrix();
    }*/

}
