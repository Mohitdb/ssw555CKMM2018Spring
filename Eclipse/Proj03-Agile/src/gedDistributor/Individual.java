package gedDistributor;

import java.util.ArrayList;

public class Individual 
{
		String individualID;
		String name;
		String gender;
		String birth;
		int age;
		boolean alive;
		String death;
		ArrayList<String> child;
		ArrayList<String> spouse;
		
		public Individual()
		{
			individualID = "";
			name = "";
			gender = "";
			birth = "";
			age = 0;
			alive = true;
			death = "";
			child = new ArrayList<String>();
			spouse = new ArrayList<String>();
		}
		
}
