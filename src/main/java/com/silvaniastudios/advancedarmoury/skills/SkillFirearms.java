package com.silvaniastudios.advancedarmoury.skills;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillFirearms extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillFirearms(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
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
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillFirearms.staticSkillId, new SkillFirearms(player, staticSkillId));
	}

	@Override public void init(Entity entity, World world) {}
	@Override public void activateSkill(EntityPlayer player, World world) {}
	@Override public boolean hasGui() { return false; }
	@Override public int iconX() { return 0; }
	@Override public int iconZ() { return 0; }
	@Override public void openGui() {}
	@Override public String shortName() { return "GUNS"; }
	@Override public String skillName() { return "Firearms"; }
	@Override public String nameFormat() { return "\u00A7c"; }
	@Override public int xpBarColour() { return 16733525; }
	
	@Override
	public void addDescription() {
		description.add("Overall firearms proficiency.");
		description.add("Any firearms-based skill will increase this.");
		description.add("Does not need to be equipped!");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}
}
