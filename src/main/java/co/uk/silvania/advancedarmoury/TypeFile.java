package co.uk.silvania.advancedarmoury;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeFile {
	
	public EnumComponentName type;
	public String name, assetPack;
	public ArrayList<String> lines;
	public static HashMap<EnumComponentName, ArrayList<TypeFile>> files;
	private int readerPosition = 0;
	
	static {
		files = new HashMap<EnumComponentName, ArrayList<TypeFile>>();
		for (EnumComponentName type : EnumComponentName.values()) {
			files.put(type, new ArrayList<TypeFile>());
		}
	}
	
	public TypeFile(String contentPack, EnumComponentName t, String s) {
		this(contentPack, t, s, true);
	}
	
	public TypeFile(String contentPack, EnumComponentName t, String s, boolean addToTypeFileList) {
		type = t;
		name = s;
		this.assetPack = contentPack;
		lines = new ArrayList<String>();
		if(addToTypeFileList)
			files.get(type).add(this);
	}

	public String readLine() {
		if(readerPosition == lines.size())
			return null;
		return lines.get(readerPosition++);
	}

}
