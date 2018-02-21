package GedcomParse;
//note
//import static gedcomparse.GedcomParse.famHash;
import java.util.ArrayList;

public class Family 
{
	String famID;
	String married;
	String divorced;
	String husbID;
	String husbName;
	String wifeID;
	String wifeName;
	ArrayList<String> children;
	
	public Family()
	{
		famID = "";
		married = "";
		divorced = "";
		husbID = "";
		husbName = "";
		wifeID = "";
		wifeName = "";
		children = new ArrayList<String>();
                //System.out.println("In family.java");
	}

	public String getFamID() {
		return famID;
	}

	public void setFamID(String famID) {
		this.famID = famID;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String isDivorced() {
		return divorced;
	}

	public void setDivorced(String divorced) {
		this.divorced = divorced;
	}

	public String getHusbID() {
		return husbID;
	}

	public void setHusbID(String husbID) {
		this.husbID = husbID;
	}

	public String getHusbName() {
		return husbName;
	}

	public void setHusbName(String husbName) {
		this.husbName = husbName;
	}

	public String getWifeID() {
		return wifeID;
	}

	public void setWifeID(String wifeID) {
		this.wifeID = wifeID;
	}

	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}

	public ArrayList<String> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}
}