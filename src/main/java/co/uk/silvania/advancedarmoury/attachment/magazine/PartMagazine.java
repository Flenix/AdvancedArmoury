package co.uk.silvania.advancedarmoury.attachment.magazine;

import co.uk.silvania.advancedarmoury.AAUtils;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.TypeFile;
import co.uk.silvania.advancedarmoury.items.components.ComponentType;

public class PartMagazine extends ComponentType {
	
	public int size;
	public double calibre;
	public int magwellType; //0 assault, 1 pistol, 2 sniper, 3 shotgun
	public boolean proprietary;
	public String propId;
	public String displayName;
	public String modelName;
	
	public PartMagazine(TypeFile type) {
		super(type);
	}
	
	public void read(String[] split, TypeFile file) {
		super.read(split, file);
		try {
			unlocalizedName = file.name;
			if (split[0].replace(" ", "").equalsIgnoreCase("size")) {
				size = AAUtils.parseInt(split[1].trim());
			}
			if (split[0].replace(" ", "").equalsIgnoreCase("calibre") || split[0].replace(" ", "").equalsIgnoreCase("caliber")) {
				calibre = AAUtils.parseInt(split[1].trim());
			}
			if (split[0].replace(" ", "").equalsIgnoreCase("displayname")) {
				displayName = split[1].trim();
			}
			if (split[0].replace(" ", "").equalsIgnoreCase("model")) {
				modelName = split[1].trim();
			}
		} catch (Exception ex) {
			AdvancedArmoury.println("Reading of asset file " + file.name + " from pack " + file.assetPack + " has failed.");
		}
	}

}
