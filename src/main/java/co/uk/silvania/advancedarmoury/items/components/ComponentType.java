package co.uk.silvania.advancedarmoury.items.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.TypeFile;
import net.minecraft.item.Item;

public class ComponentType {
	
	public static List<ComponentType> componentTypes = new ArrayList<ComponentType>();
	public static HashMap<String, ComponentType> items = new HashMap<String, ComponentType>();
	
	public String displayName;
	public String modelName;
	public String modelTexture;
	public String iconTexture;
	public String gunType;
	public String material;
	
	public float xSize;
	public float ySize;
	public float zSize;
	
	public String assetPack;
	public String unlocalizedName;
	public Item item;
	
	public ComponentType(TypeFile file) {
		assetPack = file.assetPack;
		componentTypes.add(this);
	}
	
	public void read(TypeFile file) {
		for(;;) {
			String line = null;
			line = file.readLine();
			if (line == null)
				break;
			if (line.startsWith("//") || line.startsWith("#") || line.startsWith(";"))
				continue; //Skip lines considered as comments
			String[] split = line.split("=");
			if(split.length < 2)
				continue;
			
			if (split[0].equalsIgnoreCase("localname")) {
				unlocalizedName = split[1];
			}
			
			items.put(unlocalizedName, this);
			
			read(split, file);
		}
	}
	
	protected void read(String[] split, TypeFile file) {
		AdvancedArmoury.println("Reading file information: [" + file.name + "] " + split[0] + ": " + split[1]);
	}
}
