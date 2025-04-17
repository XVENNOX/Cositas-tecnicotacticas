package p4;

public abstract class Coworker {

    private String id;
    private String name;
    private float seniority;
    private String country;

    public static String ID_FORMAT = "DDDDDDDDL"; //D=0...9, L=A...Z
    public static int MAX_SENIORITY = 30;
    public static int MAX_COUNTRY_LENGTH = 20;

    //constructor sin argumentos
    public Coworker(){}

    //constructor con argumentos
    public Coworker (String id, String name, float seniority, String country) {
	this.id = id;
	this.name = name;
	this.seniority = seniority;
	this.country = country;
    }

    //constructor de copia
    public Coworker (Coworker p) {
	this.id = p.id;
	this.name = p.name;
	this.seniority = p.seniority;
	this.country = p.country;
    }


    //Métodos de validacion
    public static boolean isValidId(String id) {
	if (id == null || id.length() != 9) {
	    System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
	    return false;
	}

	for (int i=0; i<8; i++) { 
	    if (!Character.isDigit(id.charAt(i))) {
		System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
		return false;
	    }
	}

	char ultimoChar = id.charAt(8);
	if (!Character.isLetter(ultimoChar)) {
	    System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
	    return false;
	}

	return true;
    }

    public static boolean isValidName(String name) {
	if (name == null || name.isEmpty()) {
	    System.out.println("Name is empty");
	    return false;
	}

	String nombreSinEspacios = name.replace(" ", "");
	for (int i=0; i<nombreSinEspacios.length(); i++) {
	    if (!Character.isLetter(nombreSinEspacios.charAt(i))) {
		System.out.println("Incorrect name value: <" + name + ">. Valid name must contain only letters");
		return false;
	    }
	}

	return true;
    }

    public static boolean isValidSeniority (float seniority) {
	if (seniority < 0 || seniority > 30) {
	    System.out.println("Incorrect seniority value: <" + seniority + ">. Valid range is <0>-<" + MAX_SENIORITY + ">");
	    return false;
	}

	return true;
    }

    public static boolean isValidCountry (String country) {
	if (country.isEmpty()) {
	    System.out.println("Incorrect country value, its empty");
	    return false;
	}
	if (country == null || country.length() > MAX_COUNTRY_LENGTH) {
	    System.out.println("Incorrect country value: <" + country + ">. Valid name must contain up <" + MAX_COUNTRY_LENGTH + "> letters");
	    return false;
	}
	for (int i=0; i<country.length(); i++) {
	    if (Character.isDigit(country.charAt(i))) {
		System.out.println("Country name must not contain numbers");
		return false;
	    }
	}

	return true;
    }

    //Métodos getters
    public String getId() { return id; }
    public String getName() { return name; }
    public float getSeniority() { return seniority; }
    public String getCountry() { return country; }

    //Métodos setters
    public void setId (String id) {
	if (isValidId(id)) { this.id = id; }
    }
    public void setName (String name) {
	if (isValidName(name)) { this.name = name; }
    }
    public void setSeniority (float seniority) {
	if (isValidSeniority(seniority)) { this.seniority = seniority; }
    }
    public void setCountry (String country) {
	if (isValidCountry(country)) { this.country = country; }
    }	

    //Método toString
    public String toString() {
	return  id + ";" + name + ";" + seniority + ";" + country;
    }
}
