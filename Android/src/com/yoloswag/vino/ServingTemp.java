package com.yoloswag.vino;

public class ServingTemp {
	
	private String servingTemp;
	

	public ServingTemp(String serving) {
		this.servingTemp = serving;
	}

	//returns a ServingTemperature array containing arbitrary data
	//so we can begin implementing our other methods
	public static ServingTemp[] getAll() {
		return  new ServingTemp[] { new ServingTemp("ROOMTEMP"), 
				                    new ServingTemp("CHILLED")
		                          };
	}
}