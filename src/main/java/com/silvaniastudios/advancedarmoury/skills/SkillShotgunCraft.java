package com.silvaniastudios.advancedarmoury.skills;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillShotgunCraft extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillShotgunCraft(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillShotgunCraft.staticSkillId, new SkillShotgunCraft(player, staticSkillId));
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
	@Override public int iconX() { return 90; }
	@Override public int iconZ() { return 60; }
	@Override public void openGui() {}
	@Override public String shortName() { return "STGN-C"; }
	@Override public String skillName() { return "Shotgun Crafting"; }
	@Override public String nameFormat() { return "\u00A71"; }
	@Override public int xpBarColour() { return 170; }

	@Override
	public void addDescription() {
		description.add("Ability to create Shotguns");
		description.add("Level up to craft improved Shotguns,");
		description.add("as well as use better mateirals.");
		description.add("Eventually earn the ability to create Energy weapons.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}
}
