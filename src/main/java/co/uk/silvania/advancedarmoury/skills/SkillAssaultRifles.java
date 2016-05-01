package co.uk.silvania.advancedarmoury.skills;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillAssaultRifles extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillAssaultRifles(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}
	
	@Override
	public void addXP(float xpAdd, EntityPlayer player) {
		SkillLevelBase firearms = (SkillLevelBase) SkillFirearms.get(player, SkillFirearms.staticSkillId);
		firearms.forceAddXP(xpAdd/2, player);
		super.addXP(xpAdd, player);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillAssaultRifles.staticSkillId, new SkillAssaultRifles(player, staticSkillId));
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
	@Override public int iconX() { return 120; }
	@Override public int iconZ() { return 0; }
	@Override public void openGui() {}
	@Override public String shortName() { return "ASLT"; }
	@Override public String skillName() { return "Assault Rifles"; }
	@Override public String nameFormat() { return "\u00A74"; }
	@Override public int xpBarColour() { return 11141120; }

	@Override
	public void addDescription() {
		description.add("Proficiency with Assault Rifles.");
		description.add("Level up to unlock higher power weapons,");
		description.add("as well as a general Accuracy boost.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}
}
