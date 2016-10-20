package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.TypeFile;

public class ComponentReceiver extends ComponentType {
	
	public int magId;
	public boolean topRail;
	public boolean stock;
	public float frontEndX;
	public float frontEndY;
	public float frontEndZ;
	public float stockX;
	public float stockY;
	public float stockZ;
	public float magX;
	public float magY;
	public float magZ;
	public float attachmentX;
	public float attachmentY;
	public float attachmentZ;
	
	public ComponentReceiver(TypeFile file) {
		super(file);
	}
	
	public void read(String[] split, TypeFile file) {
		super.read(split, file);
		try {
			unlocalizedName = file.name;
			if (split[0].replace(" ", "").equalsIgnoreCase("displayname")) { 	displayName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("componentName")) {	componentName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("model")) { 			modelName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("modelTexture")) { 	modelTexture = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("gunType")) { 		gunType = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("xSize")) { 			xSize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("ySize")) { 			ySize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("zSize")) { 			zSize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("frontEndX")) {		frontEndX = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("frontEndY")) {		frontEndY = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("frontEndZ")) {		frontEndZ = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("stockX")) {			stockX = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("stockY")) {			stockY = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("stockZ")) {			stockZ = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("magX")) {			magX = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("magY")) {			magY = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("magZ")) {			magZ = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("attachmentX")) {	attachmentX = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("attachmentY")) {	attachmentY = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("attachmentZ")) {	attachmentZ = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("magId")) {			magId = AAUtils.parseInt(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("topRail")) {		topRail = AAUtils.parseBoolean(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("stock")) {			stock = AAUtils.parseBoolean(split[1].trim()); }
			
			
			if (modelName.equalsIgnoreCase("m4receiver")) {
				magId = 0;
				topRail = true;
				xSize = 0.155F; //X/red in blender
				ySize = 0.677F; //Z/blue in blender
				zSize = 0.501F; //Y/green in blender
				
				frontEndX = 0.0F;
				frontEndY = -0.37551F;
				frontEndZ = 0.02F;
				
				stockX = 0.0F;
				
				magX = 0.0F;
				
				attachmentX = 0.0F;
			}
			
		} catch (Exception ex) {
			AdvancedArmoury.println("Reading of asset file " + file.name + " from pack " + file.assetPack + " has failed.");
		}
	}
}
