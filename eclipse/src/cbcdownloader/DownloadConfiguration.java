package cbcdownloader;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

//in the future, this should have some sort of singleton type structure,
//to prevent redeclaration of the requirementsList
public abstract class DownloadConfiguration {
	
	//An TreeMap will preserve the order
	private TreeMap<String, String[]> requirementsList =
		new TreeMap<String, String[]>();
	//<nameOfRequirement, {description, default, answer}>
	//a value of null for the default means that there is no default, and the
	//user will be forced to give that information
	
	//returning the entire map is a bad idea because it forces an API change if
	//we ever decide to change the internal map-based structure
	public Set<String> getRequirements() {
		return requirementsList.keySet();
	}
	
	public String getDescriptionFor(String name) {
		return requirementsList.get(name)[0];
	}
	
	public boolean hasDefaultFor(String name) {
		return getDefaultFor(name) != null;
	}
	
	public String getDefaultFor(String name) {
		return requirementsList.get(name)[1];
	}
	
	public String getValueFor(String name) {
		String actualValue = requirementsList.get(name)[2];
		return actualValue == null ? getDefaultFor(name) : actualValue;
	}
	
	public void setValueFor(String name, String value) {
		if(!requirementsList.containsKey(name)) {
			throw new java.lang.IllegalArgumentException("\"" + name + "\" is "+
			"not a valid configuration option. Please note that configuration "+
			"options are case-sensitive");
		}
		String[] oldValues = requirementsList.get(name);
		oldValues[2] = value;
		requirementsList.put(name, oldValues);
	}
	
	protected void addRequirement(String name, String description) {
		addRequirement(name, description, null);
	}
	
	protected void addRequirement(String name, String description,
			String defaultVal) {
		String[] vals = new String[3];
		vals[0] = description; vals[1] = defaultVal; vals[2] = null;
		requirementsList.put(name, vals);
	}
	
	/* I'm leaving this in here for right now, but it's probably a Bad Idea(TM)
	   because it could lead to some unclean API usage, and is unclear on usage
	   use the other addRequirement methods instead.
	protected void addRequirements(Map<String, String[]> requirements) {
		requirementsList.putAll(requirements);
	}*/
}
