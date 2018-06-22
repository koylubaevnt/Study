package com.brysekkel.typeinfo.io;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesDemo {

	public static void main(String[] args) throws BackingStoreException {
		Preferences prefs = Preferences.userNodeForPackage(Preferences.class);
		prefs.put("Location", "Oz");
		prefs.put("Footwear", "Ruby Slippers");
		prefs.putInt("Companions", 4);
		prefs.putBoolean("Are there witches?", true);
		int usageCount = prefs.getInt("UsageCount", 0);
		usageCount++;
		prefs.putInt("UsageCount", usageCount);
		for(String key : prefs.keys())
			System.out.println(key + ": " + prefs.get(key, null));
		System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
	}

}
