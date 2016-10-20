package com.silvaniastudios.advancedarmoury.skills;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillExplosivesCraft extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillExplosivesCraft(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillExplosivesCraft.staticSkillId, new SkillExplosivesCraft(player, staticSkillId));
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(skillId + "xp", xp);
		compound.setTag(skillId, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(skillId);
		xp = nbt.getFloat(skillId + "xp");
	}

	@Override public void init(Entity entity, World world) {}
	@Override public void activateSkill(EntityPlayer player, World world) {}
	@Override public boolean hasGui() { return false; }
	@Override public int iconX() { return 210; }
	@Override public int iconZ() { return 60; }
	@Override public void openGui() {}
	@Override public String shortName() { return "EXPL-C"; }
	@Override public String skillName() { return "Explosive Craft"; }
	@Override public String nameFormat() { return "\u00A7e"; }
	@Override public int xpBarColour() { return 16777045; }

	@Override
	public void addDescription() {
		description.add("Ability to craft explosives.");
		description.add("Level up to unlock new types of explosive,");
		description.add("as well as decreased cost and increased blast range.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}
}
