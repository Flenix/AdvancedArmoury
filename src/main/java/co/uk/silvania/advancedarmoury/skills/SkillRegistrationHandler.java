package co.uk.silvania.advancedarmoury.skills;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

public class SkillRegistrationHandler {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			event.entity.registerExtendedProperties(SkillFirearms.staticSkillId, new SkillFirearms((EntityPlayer)event.entity, SkillFirearms.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillPistols.staticSkillId, new SkillPistols((EntityPlayer)event.entity, SkillPistols.staticSkillId));
			event.entity.registerExtendedProperties(SkillSMGs.staticSkillId, new SkillSMGs((EntityPlayer)event.entity, SkillSMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillShotguns.staticSkillId, new SkillShotguns((EntityPlayer)event.entity, SkillShotguns.staticSkillId));
			event.entity.registerExtendedProperties(SkillAssaultRifles.staticSkillId, new SkillAssaultRifles((EntityPlayer)event.entity, SkillAssaultRifles.staticSkillId));
			event.entity.registerExtendedProperties(SkillLMGs.staticSkillId, new SkillLMGs((EntityPlayer)event.entity, SkillLMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillRifles.staticSkillId, new SkillRifles((EntityPlayer)event.entity, SkillRifles.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillEnergyPistols.staticSkillId, new SkillEnergyPistols((EntityPlayer)event.entity, SkillEnergyPistols.staticSkillId));
			event.entity.registerExtendedProperties(SkillEnergySMGs.staticSkillId, new SkillEnergySMGs((EntityPlayer)event.entity, SkillEnergySMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillEnergyShotguns.staticSkillId, new SkillEnergyShotguns((EntityPlayer)event.entity, SkillEnergyShotguns.staticSkillId));
			event.entity.registerExtendedProperties(SkillEnergyAssaultRifles.staticSkillId, new SkillEnergyAssaultRifles((EntityPlayer)event.entity, SkillEnergyAssaultRifles.staticSkillId));
			event.entity.registerExtendedProperties(SkillEnergyLMGs.staticSkillId, new SkillEnergyLMGs((EntityPlayer)event.entity, SkillEnergyLMGs.staticSkillId));
			event.entity.registerExtendedProperties(SkillEnergyRifles.staticSkillId, new SkillEnergyRifles((EntityPlayer)event.entity, SkillEnergyRifles.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillExplosives.staticSkillId, new SkillExplosives((EntityPlayer)event.entity, SkillExplosives.staticSkillId));
			event.entity.registerExtendedProperties(SkillCombatKnives.staticSkillId, new SkillCombatKnives((EntityPlayer)event.entity, SkillCombatKnives.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillPistolCraft.staticSkillId, new SkillPistolCraft((EntityPlayer)event.entity, SkillPistolCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillSMGCraft.staticSkillId, new SkillSMGCraft((EntityPlayer)event.entity, SkillSMGCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillShotgunCraft.staticSkillId, new SkillShotgunCraft((EntityPlayer)event.entity, SkillShotgunCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillAssaultCraft.staticSkillId, new SkillAssaultCraft((EntityPlayer)event.entity, SkillAssaultCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillLMGCraft.staticSkillId, new SkillLMGCraft((EntityPlayer)event.entity, SkillLMGCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillRifleCraft.staticSkillId, new SkillRifleCraft((EntityPlayer)event.entity, SkillRifleCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillExplosivesCraft.staticSkillId, new SkillExplosivesCraft((EntityPlayer)event.entity, SkillExplosivesCraft.staticSkillId));
			event.entity.registerExtendedProperties(SkillRoundCraft.staticSkillId, new SkillRoundCraft((EntityPlayer)event.entity, SkillRoundCraft.staticSkillId));
			
			event.entity.registerExtendedProperties(SkillWeaponServicing.staticSkillId, new SkillWeaponServicing((EntityPlayer)event.entity, SkillWeaponServicing.staticSkillId));
		}
	}
}
