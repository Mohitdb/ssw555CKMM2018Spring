package gedDistributor;

import java.util.ArrayList;

public class Family 
{
	String famID;
	String married;
	boolean divorced;
	String husbID;
	String husbName;
	String wifeID;
	String wifeName;
	ArrayList<String> children;
	
	public Family()
	{
		famID = "";
		married = "";
		divorced = false;
		husbID = "";
		husbName = "";
		wifeID = "";
		wifeName = "";
		children = new ArrayList<String>();
	}

}
