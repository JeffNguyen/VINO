package com.yoloswag.vino;

public class Wine 
{
    protected int vintage;
	protected double alcoholContent;
	protected String name;
	protected String region;
	protected Category category;
	protected Varietal varietal;
	protected SweetOrDry sweetOrDry;
	protected ServingTemp servingTemp;
	
	protected static Wine a = new Wine("Castello Banfi", "Italy", 2008, 12.9, "red", 
	                                   "Chianti", "dry", "room temperature" );
	protected static Wine b = new Wine("Barefoot", "California", 2009, 7.0, "white",
	                                   "Moscato", "sweet", "chilled");
	protected static Wine c = new Wine("Montana", "New Zealand", 2011, 13.4, "white",
	                                   "Sauvignon Blanc", "dry", "chilled");
	protected static Wine d = new Wine("Yellow Tail", "Australia", 2011, 14.8, "red",
                                       "Cabernet Sauvignon", "dry", "room temperature");
	protected static Wine e = new Wine("Chateau Ste. Michelle", "Florida", 2012, 11.2,
                                       "white", "Riesling", "sweet", "chilled");
	

	/**  Default constructor
	 */
	public Wine() 
	{
	}
	
	/**  All-member constructor for fake database
	 */
	public Wine(String name, String region, int vintage, double alcoholContent,
	            String category, String varietal, String sweetOrDry,
		        String servingTemp) 
	{
		this.name           = name;
		this.region         = region;
		this.vintage        = vintage;
		this.alcoholContent = alcoholContent;
		this.category       = new Category(category);
		this.varietal       = new Varietal(varietal);
		this.sweetOrDry     = new SweetOrDry(sweetOrDry);
		this.servingTemp    = new ServingTemp(servingTemp);
	}
	
	public static Wine get(Wine wine) {
		return wine;
	}
	
	/**  Returns all wines with their corresponding data
	 */
	public static Wine[] getAll() 
	{	
		return new Wine[] {a, b, c, d, e};
	}
	
	public Producer getProducer() 
	{
		// TODO actual implementation that works
		return new Producer();
	}

	public ServingTemp getServingTemp() {
		return new ServingTemp("CHILLED");
	}

	public SweetOrDry getSweetOrDry() {
		return new SweetOrDry("SWEET");
	}

	public Varietal getVarietal() {
		// would usually load from database
		return new Varietal("CAYETANA");
	}

	public Category getCategory() {
		return new Category("RED");
	}
}
