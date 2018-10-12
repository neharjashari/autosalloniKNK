package Knk_Package;

import java.util.prefs.Preferences;

public class UserPreferences {

	private Preferences prefs;
	private final String LANG="lang";
	
	
	public void setLang(String lang) {
		prefs.put(LANG, lang);
		
		
		
	}
	
	public String getLang() {
		
		return prefs.get(LANG, "en");
	}
	
	public UserPreferences Preference() {
		prefs=Preferences.userRoot().node(this.getClass().getName());
		return this;
		
	}
	
	
	
}