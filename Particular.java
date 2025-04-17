package p4;

public class Particular extends Coworker{

    private String creditCard;
    
    public static final String CREDIT_CARD = "DDDD-DDDD-DDDD-DDDD";

    //constructor sin argumentos
    public Particular(){
    }

    //constructor con argumentos
    public Particular (String id, String name, float seniority, String country, String creditCard) {
	super(id, name, seniority, country);
	this.creditCard = creditCard;
    }

    //constructor de copia
    public Particular (Particular p) {
	super(p.getId(), p.getName(), p.getSeniority(), p.getCountry());
	this.creditCard = p.creditCard;
    }

    //Métodos de validación
    public static boolean isValidCreditCard (String creditCard) {
	if (creditCard == null || creditCard.length() > 19 || !creditCard.contains("-")){
	    System.out.println("Incorrect credit card value: <" + creditCard + ">. Not comply with format <" + CREDIT_CARD + ">");
	    return false;
	}

	return true;
    }

    public static boolean isValidParticular (string id, string name, float seniority, string country, string creditCard) {
	if(isValidId(id) && isValidName(name) && idValidSeniority(seniority) && isValidCountry(country) && isValidcreditCard(creditCard)) {
	    return true;
	}else return false;
    }


    //Métodos getters
    public String getCreditCard() { return creditCard; }

    
    //Métodos setters
    public void setCreditCard (String creditCard) {
	if (isValidCreditCard(creditCard)) { this.creditCard = creditCard; }
    }

    //Método toString
    public String toString() {
	return "P;" + getId() + ";" + getName() + ";" + getSeniority() + ";" + getCountry() + ";" + creditCard;
    }
}	
