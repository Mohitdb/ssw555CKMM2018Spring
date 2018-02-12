//package gedDistributor;

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

		public String getIndividualID() {
			return individualID;
		}

		public void setIndividualID(String individualID) {
			this.individualID = individualID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public boolean isAlive() {
			return alive;
		}

		public void setAlive(boolean alive) {
			this.alive = alive;
		}

		public String getDeath() {
			return death;
		}

		public void setDeath(String death) {
			this.death = death;
		}

		public ArrayList<String> getChild() {
			return child;
		}

		public void setChild(ArrayList<String> child) {
			this.child = child;
		}

		public ArrayList<String> getSpouse() {
			return spouse;
		}

		public void setSpouse(ArrayList<String> spouse) {
			this.spouse = spouse;
		}
		
}
