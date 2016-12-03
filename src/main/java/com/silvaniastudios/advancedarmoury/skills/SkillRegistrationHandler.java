package com.silvaniastudios.advancedarmoury.skills;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

public class SkillRegistrationHandler {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {			
			event.entity.registerExtendedProperties(SkillPistols.staticSkillId, new SkillPistols((EntityPlayer)event.entity, SkillPistols.staticSkillId));
			event.entity.registerExtendedProperties(SkillSMGs.staticSkillId, new SkillSMGs((EntityPlayer)event.entity, SkillSMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillShotguns.staticSkillId, new SkillShotguns((EntityPlayer)event.entity, SkillShotguns.staticSkillId));
			event.entity.registerExtendedProperties(SkillAssaultRifles.staticSkillId, new SkillAssaultRifles((EntityPlayer)event.entity, SkillAssaultRifles.staticSkillId));
			event.entity.registerExtendedProperties(SkillLMGs.staticSkillId, new SkillLMGs((EntityPlayer)event.entity, SkillLMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillRifles.staticSkillId, new SkillRifles((EntityPlayer)event.entity, SkillRifles.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillExplosives.staticSkillId, new SkillExplosives((EntityPlayer)event.entity, SkillExplosives.staticSkillId));
			event.entity.registerExtendedProperties(SkillCombatKnives.staticSkillId, new SkillCombatKnives((EntityPlayer)event.entity, SkillCombatKnives.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillFirearmCrafting.staticSkillId, new SkillFirearmCrafting((EntityPlayer)event.entity, SkillFirearmCrafting.staticSkillId));
		}
	}
}
