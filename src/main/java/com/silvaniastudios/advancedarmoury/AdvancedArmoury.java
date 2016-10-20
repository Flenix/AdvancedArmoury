package com.silvaniastudios.advancedarmoury;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.silvaniastudios.advancedarmoury.config.AAConfig;
import com.silvaniastudios.advancedarmoury.config.ComponentGenerator;
import com.silvaniastudios.advancedarmoury.config.ComponentGeneratorConfig;
import com.silvaniastudios.advancedarmoury.items.assets.AssetFrontEnd;
import com.silvaniastudios.advancedarmoury.items.assets.AssetReceiver;
import com.silvaniastudios.advancedarmoury.items.assets.AssetStock;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentFrontEnd;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentReceiver;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentStock;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentType;
import com.silvaniastudios.advancedarmoury.items_old.AAItemModifierCores;
import com.silvaniastudios.advancedarmoury.items_old.AAItemPrebuiltGuns;
import com.silvaniastudios.advancedarmoury.network.GunBuildPacket;
import com.silvaniastudios.advancedarmoury.network.GunEventPacket;
import com.silvaniastudios.advancedarmoury.network.GunGuiPacket;
import com.silvaniastudios.advancedarmoury.network.LocateDamagePacket;
import com.silvaniastudios.advancedarmoury.skills.SkillAssaultCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillAssaultRifles;
import com.silvaniastudios.advancedarmoury.skills.SkillCombatKnives;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergyAssaultRifles;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergyLMGs;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergyPistols;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergyRifles;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergySMGs;
import com.silvaniastudios.advancedarmoury.skills.SkillEnergyShotguns;
import com.silvaniastudios.advancedarmoury.skills.SkillExplosives;
import com.silvaniastudios.advancedarmoury.skills.SkillExplosivesCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillFirearms;
import com.silvaniastudios.advancedarmoury.skills.SkillLMGCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillLMGs;
import com.silvaniastudios.advancedarmoury.skills.SkillPistolCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillPistols;
import com.silvaniastudios.advancedarmoury.skills.SkillRegistrationHandler;
import com.silvaniastudios.advancedarmoury.skills.SkillRifleCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillRifles;
import com.silvaniastudios.advancedarmoury.skills.SkillRoundCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillSMGCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillSMGs;
import com.silvaniastudios.advancedarmoury.skills.SkillShotgunCraft;
import com.silvaniastudios.advancedarmoury.skills.SkillShotguns;
import com.silvaniastudios.advancedarmoury.skills.SkillWeaponServicing;

import co.uk.silvania.rpgcore.RegisterSkill;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = AdvancedArmoury.modid, version = AdvancedArmoury.version, dependencies="after:rpgcore")
public class AdvancedArmoury
{
    public static final String modid = "advancedarmoury-be";
    public static final String version = "Alpha-0.0.2";
    
    public static DamageSource damageShot;
    public static DamageSource damageMelee;
    public static DamageSource damageEvent;
    
    @Instance(AdvancedArmoury.modid)
    public static AdvancedArmoury instance;
    public static GuiHandler guiHandler = new GuiHandler();

    @SidedProxy(clientSide="co.uk.silvania.advancedarmoury.client.ClientProxy", serverSide="co.uk.silvania.advancedarmoury.CommonProxy")
    public static CommonProxy proxy;
    
    private static int modGuiIndex = 0;
    public static final int GunInventoryGuiIndex = modGuiIndex++;
    public static File assetDir;
        
    public static SimpleNetworkWrapper network;
    
    public static boolean rpgcore = false;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    	AAConfig.init(event.getModConfigurationDirectory().getParent() + "/Advanced Armoury/");
    	ComponentGeneratorConfig.init(event.getModConfigurationDirectory().getParent() + "/Advanced Armoury/");
    	assetDir = new File(event.getModConfigurationDirectory().getParentFile(), "/Advanced Armoury/");
    	
    	if (!assetDir.exists()) {
    		println("Asset Directory not found. Creating.");
    		assetDir.mkdirs();
    	} else {
    		println("Asset directory found.");
    	}

    	readAssetPacks(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("AdvancedArmoury");
    	network.registerMessage(GunEventPacket.Handler.class, GunEventPacket.class, 0, Side.SERVER);
    	network.registerMessage(GunGuiPacket.Handler.class, GunGuiPacket.class, 1, Side.SERVER);
    	network.registerMessage(GunBuildPacket.Handler.class, GunBuildPacket.class, 2, Side.SERVER);
    	network.registerMessage(LocateDamagePacket.Handler.class, LocateDamagePacket.class, 3, Side.CLIENT);
    	
    	if (Loader.isModLoaded("rpgcore")) {
    		rpgcore = true;
    		println("RPGCore detected! Initializing skills!");
    		
    		SkillFirearms skillFirearms = new SkillFirearms(null, "skillFirearms");
    		SkillPistols skillPistols = new SkillPistols(null, "skillPistols");
    		SkillSMGs skillSMGs = new SkillSMGs(null, "skillSMGs");
    		SkillShotguns skillShotguns = new SkillShotguns(null, "skillShotguns");
    		SkillAssaultRifles skillAssault = new SkillAssaultRifles(null, "skillAssaultRifles");
    		SkillLMGs skillLMGs = new SkillLMGs(null, "skillLMGs");
    		SkillRifles skillRifles = new SkillRifles(null, "skillSnipers");
    		SkillEnergyPistols skillEnergyPistols = new SkillEnergyPistols(null, "skillEnergyPistols");
    		SkillEnergySMGs skillEnergySMGs = new SkillEnergySMGs(null, "skillEnergySMGs");
    		SkillEnergyShotguns skillEnergyShotguns = new SkillEnergyShotguns(null, "skillEnergyShotguns");
    		SkillEnergyAssaultRifles skillEnergyAssaults = new SkillEnergyAssaultRifles(null, "skillEnergyAssaultRifles");
    		SkillEnergyLMGs skillEnergyLMGs = new SkillEnergyLMGs(null, "skillEnergyLMGs");
    		SkillEnergyRifles skillEnergyRifles = new SkillEnergyRifles(null, "skillEnergyRifles");
    		SkillExplosives skillExplosives = new SkillExplosives(null, "skillExplosives");
    		SkillCombatKnives skillCombatKnives = new SkillCombatKnives(null, "skillCombatKnives");
    		SkillPistolCraft skillPistolCraft = new SkillPistolCraft(null, "skillPistolCraft");
    		SkillSMGCraft skillSMGCraft = new SkillSMGCraft(null, "skillSMGCraft");
    		SkillShotgunCraft skillShotgunCraft = new SkillShotgunCraft(null, "skillShotgunCraft");
    		SkillAssaultCraft skillAssaultCraft = new SkillAssaultCraft(null, "skillAssaultCraft");
    		SkillLMGCraft skillLMGCraft = new SkillLMGCraft(null, "skillLMGCraft");
    		SkillRifleCraft skillRifleCraft = new SkillRifleCraft(null, "skillRifleCraft");
    		SkillExplosivesCraft skillExplosivesCraft = new SkillExplosivesCraft(null, "skillExplosivesCraft");
    		SkillRoundCraft skillRoundCraft = new SkillRoundCraft(null, "skillRoundCraft");
    		SkillWeaponServicing skillWeaponServicing = new SkillWeaponServicing(null, "skillWeaponServicing");
    		
    		RegisterSkill.register(skillFirearms);
    		RegisterSkill.register(skillPistols);
    		RegisterSkill.register(skillSMGs);
    		RegisterSkill.register(skillShotguns);
    		RegisterSkill.register(skillAssault);
    		RegisterSkill.register(skillLMGs);
    		RegisterSkill.register(skillRifles);
    		RegisterSkill.register(skillEnergyPistols);
    		RegisterSkill.register(skillEnergySMGs);
    		RegisterSkill.register(skillEnergyShotguns);
    		RegisterSkill.register(skillEnergyAssaults);
    		RegisterSkill.register(skillEnergyLMGs);
    		RegisterSkill.register(skillEnergyRifles);
    		RegisterSkill.register(skillExplosives);
    		RegisterSkill.register(skillCombatKnives);
    		RegisterSkill.register(skillPistolCraft);
    		RegisterSkill.register(skillSMGCraft);
    		RegisterSkill.register(skillShotgunCraft);
    		RegisterSkill.register(skillAssaultCraft);
    		RegisterSkill.register(skillLMGCraft);
    		RegisterSkill.register(skillRifleCraft);
    		RegisterSkill.register(skillExplosivesCraft);
    		RegisterSkill.register(skillRoundCraft);
    		RegisterSkill.register(skillWeaponServicing);
    		
    		MinecraftForge.EVENT_BUS.register(new SkillRegistrationHandler());
    	}
    	
    	//Forge: "Do not name your event handler "EventHandler"
    	//Me: ok.
    	MinecraftForge.EVENT_BUS.register(new HandlerOfEvents());

    	ComponentGenerator.init();
    	AAItems.init();
    	AABlocks.init();
    	
    	System.out.println("#####################################################################");
    	System.out.println("YOU ARE RUNNING AN EARLY DEV BUILD OF ADVANCED ARMOURY");
    	System.out.println("THIS WILL ABSOLUTELY, 100%, NOT WORK IN FUTURE VERSIONS. EVERYTHING YOU MAKE WILL NOT BE SAVED LATER.");
    	System.out.println("DO NOT USE IN ANY WORLD YOU CARE ABOUT, ON A SERVER, IN A MODPACK, OR BASICALLY AT ALL.");
    	System.out.println("YOU HAVE BEEN WARNED. SUFFER IN SILENCE IF IT BREAKS SOMETHING OR KILLS YOUR DOG.");
    	System.out.println("#####################################################################");
    	proxy.reflectResourcePackMethods();
    	
    	if (event.getSide() == Side.CLIENT) {
    		Minecraft.getMinecraft().refreshResources();
    		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    	}
    	
    	damageShot = new DamageSource("advancedArmoury.shot");
    	damageMelee = new DamageSource("advancedArmoury.melee");
    	damageEvent = new DamageSource("advancedArmoury.event");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.registerRenderers();
    	DungeonLootRegistry.init();
    }
    
    public static void println(String str) {
    	System.out.println("[Advanced Armoury] " + str);
    }
    
    public void readAssetPacks(FMLPreInitializationEvent event) {    	
    	List<File> assetPacks = proxy.getAssetPackList();
    	
    	for (File assetPack : assetPacks) {
    		try {
    			if (isZipFile(assetPack)) {
    				ZipFile zip = new ZipFile(assetPack);
    				ZipInputStream zipStream = new ZipInputStream(new FileInputStream(assetPack));
    				BufferedReader read = new BufferedReader(new InputStreamReader(zipStream));
    				ZipEntry zipEntry = zipStream.getNextEntry();
    				
    				do {
    					zipEntry = zipStream.getNextEntry();
    					if (zipEntry == null) {
    						continue;
    					}
    					TypeFile typeFile = null;
    					for(EnumComponentName type : EnumComponentName.values()) {
    						if(zipEntry.getName().contains(type.folderName) && zipEntry.getName().split(type.folderName + "/").length > 1 && zipEntry.getName().split(type.folderName + "/")[1].length() > 0) {
    							String[] splitName = zipEntry.getName().split("/");
    							typeFile = new TypeFile(zip.getName(), type, splitName[splitName.length - 1].split("\\.")[0]);
    						}
    					}
    					if (typeFile == null) {
    						continue;
    					}
    					for (;;) {
    						String line = null;
    						try {
    							line = read.readLine();
    						} catch (Exception e) {
    							break;
    						}
    						if (line == null) {
    							break;
    						}
    						typeFile.lines.add(line);
    					}
    				}
    				while(zipEntry != null);
    				read.close();
    				zip.close();
    				zipStream.close();
    			}
			} catch (IOException e) {
				e.printStackTrace();
			}

    	}
    	
    	for(EnumComponentName type : EnumComponentName.values()) {
    		Class <? extends ComponentType> typeClass = type.getTypeClass();
            println("Type: " + type);
    		for(TypeFile typeFile : TypeFile.files.get(type)) {
    			try {
    				ComponentType componentType = (typeClass.getConstructor(TypeFile.class).newInstance(typeFile));
    				componentType.read(typeFile);
    				println("Type: " + type.folderName);
    				//Switch EnumTypes to get the correct item.
    				switch(type) {
    					case receiver:
    						new AssetReceiver((ComponentReceiver) componentType).setUnlocalizedName(typeFile.name);
    						break;
    					case frontend:
    						new AssetFrontEnd((ComponentFrontEnd)componentType).setUnlocalizedName(typeFile.name);
    						break;
    					case stock:
    						new AssetStock((ComponentStock)componentType).setUnlocalizedName(typeFile.name);
    						break;
    				}
    			}
    			catch (Exception ex) {
    				println("Adding of " + type.name() + " with type of  " + typeFile.name + "has failed.");
    				println("Please report this to Flenix and include a zip of all logs, your assets and your mod jar.");
    				ex.printStackTrace();
    			}
    		}
    	}
    }
    
  public static boolean isZipFile(File file) throws IOException {
      if(file.isDirectory()) {
          return false;
      }
      if(!file.canRead()) {
          throw new IOException("Cannot read file " + file.getAbsolutePath());
      }
      if(file.length() < 4) {
          return false;
      }
      DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
      int test = in.readInt();
      in.close();
      return test == 0x504b0304;
  }
  
    public static CreativeTabs tabComponentsGeneric = new CreativeTabs("tabComponentsGeneric") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		return getTabIcon(tabComponentsGeneric);
    	}
    };
    
    public static CreativeTabs tabComponentsAssault = new CreativeTabs("tabComponentsAssault") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		return getTabIcon(tabComponentsAssault);
    	}
    };
    
    public static CreativeTabs tabComponentsCalibre = new CreativeTabs("tabComponentsCalibre") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		return getTabIcon(tabComponentsCalibre);
    	}
    };
    
    public static CreativeTabs tabRounds = new CreativeTabs("tabRounds") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		ItemStack stack = new ItemStack(AAItems.itemRound);
    		return stack.getItem();
    	}
    };
    
    public static CreativeTabs tabMachines = new CreativeTabs("tabMachines") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		ItemStack stack = new ItemStack(AABlocks.assaultRifleAssemblyTable);
    		return stack.getItem();
    	}
    };
    
    public static CreativeTabs tabModifiers = new CreativeTabs("tabModifiers") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		ItemStack stack = new ItemStack(AAItemModifierCores.coreSimpleChamberNet);
    		return stack.getItem();
    	}
    };
    
    public static CreativeTabs tabGuns = new CreativeTabs("tabGuns") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		ItemStack item = new ItemStack(AAItemPrebuiltGuns.m4CarbineBling);
    		return item.getItem();
    	}
    };
    
    public static CreativeTabs tabGeneric = new CreativeTabs("tabGeneric") {
    	@Override @SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		ItemStack item = new ItemStack(AAItemPrebuiltGuns.m4CarbineBling);
    		return item.getItem();
    	}
    };
    
	public static Item getTabIcon(CreativeTabs tab) {
		Iterator iterator = Item.itemRegistry.iterator();
	
	    while (iterator.hasNext()) {
	    	Item item = (Item)iterator.next();
	
	        if (item == null) { continue; }
	
	        for (CreativeTabs itemTab : item.getCreativeTabs()) {
	            if (itemTab == tab) {
	            	return item;
	            }
	        }
	    }
		return Items.bone;
	}
}
