package co.uk.silvania.advancedarmoury.skills;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillWeaponServicing extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;

	public SkillWeaponServicing(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillSMGCraft.staticSkillId, new SkillSMGCraft(player, staticSkillId));
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
	@Override public int iconX() { return 60; }
	@Override public int iconZ() { return 60; }
	@Override public void openGui() {}
	@Override public String shortName() { return "SRV"; }
	@Override public String skillName() { return "Weapon Servicing"; }
	@Override public String nameFormat() { return "\u00A78"; }
	@Override public int xpBarColour() { return 5592405; }

	@Override
	public void addDescription() {
		description.add("Repair and service weapons");
		description.add("Level up to repair weapons quicker");
		description.add("and more effectively.");
		description.add("Eventually add temporary buffs to weapons.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}

}
