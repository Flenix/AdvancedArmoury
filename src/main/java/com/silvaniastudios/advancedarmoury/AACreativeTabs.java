package com.silvaniastudios.advancedarmoury;

import java.util.Iterator;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AACreativeTabs {
	
	
	public Item getTabIcon(CreativeTabs tab) {
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
		return null;
	}

}
