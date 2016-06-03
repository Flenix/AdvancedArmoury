package co.uk.silvania.advancedarmoury;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.config.AAConfig;
import co.uk.silvania.advancedarmoury.items_old.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.network.LocateDamagePacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ClientEventHandler extends Gui {
	
    private float lineOffset;
	
    //Just for adjusting the crosshair when player takes damage.
    @SubscribeEvent
    public void onHurtEvent(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			lineOffset = 3;
		}
    }
	
	//Cancels vanilla health bar rendering, then renders a custom one.
    //Custom one depletes in a more sensible fashion for our increased health.
    //We'll also render a rounded health number for accurate health grabbing at a glance.
    //TODO future: Hide health outright if map is hardcore/mod config is hardcore
    
    //Black border/red heart = normal
    //White border/red heart = under attack
	/*@SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Pre event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int w = scaledresolution.getScaledWidth();
        int h = scaledresolution.getScaledHeight();
    	
    	if (event.type.equals(ElementType.HEALTH)) {
    		event.setCanceled(true);
    	}
    	
    	if (!mc.thePlayer.capabilities.isCreativeMode && !AAConfig.hardMode) {
            boolean highlight = mc.thePlayer.hurtResistantTime / 3 % 2 == 1;
            if (mc.thePlayer.hurtResistantTime < 10) { highlight = false; }
    	
	    	float health = mc.thePlayer.getHealth();
	    	float maxHealth = mc.thePlayer.getMaxHealth();
	    	int healthLast = MathHelper.ceiling_float_int(mc.thePlayer.prevHealth);
	    	float absorb = mc.thePlayer.getAbsorptionAmount();
	    	float absorbRemaining = absorb;
	    	
	    	int left_height = 39;
	    	int healthRows = MathHelper.ceiling_float_int((maxHealth + absorb) / 2.0F / 10.0F);
	    	int rowHeight = Math.max(10 - (healthRows - 2), 3);
	    	left_height += (healthRows * rowHeight);
	        if (rowHeight != 10) left_height += 10 - rowHeight;
	        
	    	int left = w / 2 - 91;
	        int top = h - 39;
	        
	        int row = MathHelper.ceiling_float_int((float)(1) / 10.0F) - 1;
            int x = left + 0 % 10 * 8;
            int y = top - row * rowHeight;
	    	
            int heartMargin = 0;
            boolean poisoned = false;
            boolean withered = false;
            if (mc.thePlayer.isPotionActive(Potion.poison))      { heartMargin += 36; poisoned = true; }
            else if (mc.thePlayer.isPotionActive(Potion.wither)) { heartMargin += 72; withered = true; }
            int backgroundHeart = 0;
            int backgroundBar = 0;
            
            if (highlight) { backgroundHeart = 9; backgroundBar = 81; }
            
            final int TOP =  9 * (mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? 5 : 0);
            
	    	GL11.glPushMatrix();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
	    	mc.renderEngine.bindTexture(new ResourceLocation(AdvancedArmoury.modid, "textures/gui/hpicons.png"));
            drawTexturedModalRect(x, y, backgroundBar, 18, 81, 9);
            
            int val = (int) Math.round((health / maxHealth) * 79);
            if (val > 79) { val = 79; }
            
            if (highlight) { drawTexturedModalRect(x+1, y+1, 158, 27, val, 7); }
            if (absorbRemaining > 0.0F) { drawTexturedModalRect(x+1, y+1, 79, 34, val, 7); } else { drawTexturedModalRect(x+1, y+1, 0, 27, val, 7); }
            if (poisoned) { drawTexturedModalRect(x+1, y+1,  0, 34, val, 7); }
            if (withered) { drawTexturedModalRect(x+1, y+1, 79, 27, val, 7); }
            
            drawTexturedModalRect(x, y, backgroundHeart, TOP, 9, 9);

            if (highlight) {
                if (1 < healthLast)
                    drawTexturedModalRect(x, y, heartMargin + 54, TOP, 9, 9);
                else if (1 == healthLast)
                	drawTexturedModalRect(x, y, heartMargin + 63, TOP, 9, 9);
            }

            if (absorbRemaining > 0.0F) {
                if (absorbRemaining == absorb && absorb % 2.0F == 1.0F)
                    drawTexturedModalRect(x, y, heartMargin + 153, TOP, 9, 9);
                else
                    drawTexturedModalRect(x, y, heartMargin + 144, TOP, 9, 9);
                absorbRemaining -= 2.0F;
            } else {
                if (1 < health)
                    drawTexturedModalRect(x, y, heartMargin + 36, TOP, 9, 9);
                else if (1 == health)
                    drawTexturedModalRect(x, y, heartMargin + 45, TOP, 9, 9);
            }
            String str = (int)Math.ceil(health) + "/" + (int)Math.ceil(maxHealth);
        	//GL11.glScalef(0.75F, 0.75F, 0.75F);
        	mc.fontRenderer.drawString(str, x+79-(str.length()*6), y+1, 16777215);
        	//GL11.glScalef(1.25F, 1.25F, 1.25F);
            
	    	GL11.glPopMatrix();
	    	

            
            //if (AAConfig.renderHealthText) {

            mc.renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
    	}
    }*/
	
	//This is used for locational damage display on-screen. It renders an indicator to show
    //which direction damage came from - a staple of most FPS or even many combat games.
    //Pulls info from LocateDamagePacket, which is fired every time the player takes damage.
    //Resets timeHit each time, meaning damage indicators last longer if you're attacked more than once.
	boolean flag = false;
	public static long timeHit = 0;
	float fade = 0.2F;
	
    @SubscribeEvent
    public void renderHurtOverlay(RenderGameOverlayEvent.Post event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int w = scaledresolution.getScaledWidth();
        int h = scaledresolution.getScaledHeight();
    	
    	boolean renderHit = LocateDamagePacket.Handler.renderHit;
    	double angle = LocateDamagePacket.Handler.angle / 60.0;
    	if (renderHit) {
    		if (!flag) {
    			timeHit = mc.theWorld.getTotalWorldTime();
    			flag = true;
    		}
    		if (mc.theWorld.getTotalWorldTime() < (timeHit + 30)) {
    			long tick = mc.theWorld.getTotalWorldTime() - timeHit;
    	        boolean tl = false, t = false, tr = false, r = false, br = false, b = false, bl = false, l = false;
    	        GL11.glEnable(GL11.GL_BLEND);
    	        GL11.glDisable(GL11.GL_DEPTH_TEST);
    	        GL11.glDepthMask(false);
    	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    	        
    	        if (tick >= 10) {
    	        	int i = Math.abs((int) tick - 30);
    	        	fade = (i/100.0F) - 0.01F;
    	        }
    	        
    	        GL11.glColor4f(1.0F, 1.0F, 1.0F, fade);
    	        GL11.glDisable(GL11.GL_ALPHA_TEST);
    	        mc.renderEngine.bindTexture(new ResourceLocation(AdvancedArmoury.modid, "textures/gui/attackblur.png"));
    	        if (angle < 0) { tl = t = tr = r = br = b = bl = l = true;  }
    	        else if (angle < 0.1875|| angle > 5.8125) { b = true; }
    	        else if (angle < 0.5625)   { b = true; bl = true; }
    	        else if (angle < 0.9375)   { bl = true; }
    	        else if (angle < 1.3125)   { bl = true; l = true; }
    	        else if (angle < 1.6875)   { l = true;  }
    	        else if (angle < 2.0625)   { l = true; tl = true; }
    	        else if (angle < 2.4375)   { tl = true; }
    	        else if (angle < 2.8125)   { tl = true; t = true; }
    	        else if (angle < 3.1875)   { t = true;  }
    	        else if (angle < 3.5625)   { t = true; tr = true; }
    	        else if (angle < 3.9375)   { tr = true; }
    	        else if (angle < 4.3125)   { tr = true; r = true; }
    	        else if (angle < 4.6875)   { r = true;  }
    	        else if (angle < 5.0625)   { r = true; br = true; }
    	        else if (angle < 5.4375)   { br = true; }
    	        else if (angle < 5.8125)   { br = true; b = true; }
    	        else { tl = t = tr = r = br = b = bl = l = true;  }
    	        
    	        if (tl) { this.drawTexturedModalRect(w/2 - 128, h/2 - 128,  20,  20, 69, 69); } //Top-left
    	        if (t)  { this.drawTexturedModalRect(w/2 - 34,  h/2 - 128,  94,  20, 69, 10); } //Top
    	        if (tr) { this.drawTexturedModalRect(w/2 + 60,  h/2 - 128, 167,  20, 69, 69); } //Top-right
    	        if (r)  { this.drawTexturedModalRect(w/2 + 119, h/2 - 34,  226,  94, 10, 69); } //Right
    	        if (br) { this.drawTexturedModalRect(w/2 + 60,  h/2 + 60,  167, 167, 69, 69); } //Bottom-right
    	        if (b)  { this.drawTexturedModalRect(w/2 - 34,  h/2 + 119,  94, 226, 69, 10); } //Bottom
    	        if (bl) { this.drawTexturedModalRect(w/2 - 128, h/2 + 60,   20, 167, 69, 69); } //Bottom-left
    	        if (l)  { this.drawTexturedModalRect(w/2 - 128, h/2 - 34,   20,  94, 10, 69); } //Left
    	        GL11.glDepthMask(true);
    	        GL11.glEnable(GL11.GL_DEPTH_TEST);
    	        GL11.glEnable(GL11.GL_ALPHA_TEST);
    	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    		} else {
    			flag = false;
    			LocateDamagePacket.Handler.renderHit = false;
    			fade = 0.2F;
    		}
    	}
    }
    
    private Entity pointedEntity;
    
    @SubscribeEvent
    public void renderCrosshair(RenderGameOverlayEvent.Pre event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int w = scaledresolution.getScaledWidth();
        int h = scaledresolution.getScaledHeight();
        
    	ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
    	
    	if (item != null && item.getItem() instanceof GunFrame && item.stackTagCompound != null) {
	    	if (event.type.equals(ElementType.CROSSHAIRS)) {
	    		event.setCanceled(true);
	    		
	    		int x = w;
	    		int y = h;
	    		
	    		int target = 0;
	    		
	    		int range = 200;
	    		
	    		EntityPlayer player = mc.thePlayer;
	    		World world = mc.theWorld;
	    		
	    		Vec3 vec3 = player.getPosition(1.0F);
	    		Vec3 lookVec = player.getLookVec();
	    		
	    		Vec3 addedVector = vec3.addVector(lookVec.xCoord * range, lookVec.yCoord * range, lookVec.zCoord * range);
	    		Vec3 blockVector;
	    		double blockDistance = -1;
	    		
	    		MovingObjectPosition mop = mc.renderViewEntity.rayTrace(range, 1.0F);
	    		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
	    			blockVector = Vec3.createVectorHelper(mop.blockX, mop.blockY, mop.blockZ);
	    			blockDistance = blockVector.distanceTo(player.getPosition(1.0F));
	    		}
				
		        this.pointedEntity = null;
		        Vec3 vec33 = null;
		        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(lookVec.xCoord * 200, lookVec.yCoord * 200, lookVec.zCoord * 200).expand(1.0D, 1.0D, 1.0D));
		        double d2 = 200 + 1;
		        
		        for (int i = 0; i < list.size(); ++i) {
		            Entity entity = (Entity)list.get(i);
		            if (entity.canBeCollidedWith()) {
		                float f2 = entity.getCollisionBorderSize();
		                AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
		                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, addedVector);
		                if (movingobjectposition != null) {
		                    double d3 = vec3.distanceTo(movingobjectposition.hitVec);
		                    if (d3 < d2 || d2 == 0.0D) {
		                        if (!(entity == player.ridingEntity && !entity.canRiderInteract())) {
		                            this.pointedEntity = entity;
		                            vec33 = movingobjectposition.hitVec;
		                            d2 = d3;
		                            
		                            double entityDistance = entity.getDistanceToEntity(player);
		                            
		                            if (entityDistance <= blockDistance) {
			                            if (entity instanceof EntityMob || entity instanceof IMob) {
			                            	target = 96;
			                            } else if (entity instanceof EntityAnimal || entity instanceof IAnimals) {
			                            	target = 32;
			                            } else {
			                            	target = 64;
			                            }
		                            }
		                        }
		                    }
		                }
		            }
		        }
		        //System.out.println("Player Motion, X: " + player.motionX + ", Y: " + player.motionY + ", Z: " + player.motionZ);
		        int burst = item.stackTagCompound.getInteger("burstCount");
		        
	        	if (burst >= 1) {
	        		if (burst > 3) {
	        			burst = 3;
	        		}
        			lineOffset = 7+(burst*3);
		        } else if (player.motionX > 0 || player.motionY > 0 || player.motionZ > 0 || player.motionX < 0 || player.motionZ < 0) {
		        	//Don't check if Y is less than zero. It always is - I assume as a form of "gravity", keeping the player down.
		        	if (lineOffset > 8) {
		        		lineOffset -= 0.25F;
		        	} else {
		        		lineOffset = 8;
		        	}
		        } else if (!player.isSneaking() && !Mouse.isButtonDown(1)) {
		        	if (lineOffset > 3) {
		        		lineOffset -= 0.25F;
		        	} else {
		        		lineOffset = 3;
		        	}
		        } else if (!player.isSneaking() || !Mouse.isButtonDown(1)) {
		        	if (lineOffset > 1) {
		        		lineOffset -= 0.25F;
		        	} else {
		        		lineOffset = 1;
		        	}
		        } else {
		        	if (lineOffset > 0) {
		        		lineOffset -= 0.25F;
		        	} else if (lineOffset < 0) {
		        		lineOffset = 0;
		        	}
		        }
	    		
	    		GL11.glPushMatrix();
	    		
	    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
		    	mc.renderEngine.bindTexture(new ResourceLocation(AdvancedArmoury.modid, "textures/gui/crosshair.png"));
		    	
		    	int o = Math.round(lineOffset);
		    	
		    	drawTexturedModalRect(x-1,    y-14-o, 15 + target,  1,  2, 12);//Top
		    	drawTexturedModalRect(x-14-o, y,       1 + target, 15, 12,  2);//Left
		    	drawTexturedModalRect(x+3+o,  y,      19 + target, 15, 12,  2);//Right
		    	drawTexturedModalRect(x-1,    y+3+o,  15 + target, 19,  2, 12);//Bottom
		    	
		    	GL11.glPopMatrix();
		    	mc.renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
	    	}
    	}
    }
}
