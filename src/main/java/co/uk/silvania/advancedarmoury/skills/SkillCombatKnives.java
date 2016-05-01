package co.uk.silvania.advancedarmoury.skills;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillCombatKnives extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillCombatKnives(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillCombatKnives.staticSkillId, new SkillCombatKnives(player, staticSkillId));
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
	@Override public int iconX() { return 0; }
	@Override public int iconZ() { return 30; }
	@Override public void openGui() {}
	@Override public String shortName() { return "CMBT"; }
	@Override public String skillName() { return "Combat Knives"; }
	@Override public String nameFormat() { return "\u00A78"; }
	@Override public int xpBarColour() { return 11184810; }

	@Override
	public void addDescription() {
		description.add("Proficiency with Combat Knives.");
		description.add("Level up to unlock different knives,");
		description.add("as well as a general Damage & Speed boost.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}
}
