package p4;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Executive extends Employee{

    private String passId;
    
    public static final int MAX_PASSID_LENGTH = 6;

    //constructores
    public Executive() {}
    
    public Executive (String id, String name, float seniority, String country, String company, String bankAccount, String passId) {
	super(id, name, seniority, country, company, bankAccount);
	this.passId = passId;
    }

    public Executive (Executive p) {
	super(p.getId(), p.getName(), p.getSeniority(), p.getCountry(), p.getCompany(), p.getBankAccount());
	this.passId = p.passId;
    }

    //Métodos de validacion	    
    public static boolean isValidPassId (String passId) {
	if(passId == null || passId.length() > 6) {
	    System.out.println("Incorrect passID value: <" + passId + ">. Valid name must contain up <" + MAX_PASSID_LENGTH + "> numbers");
	}
	for (int i=0; i<passId.length(); i++) {
	    if(Character.isLetter(passId.charAt(i))) {
		System.out.println("Incorrect passID value: <" + passId + ">. Valid name must contain up <" + MAX_PASSID_LENGTH + "> numbers");
	    }
	}
	return true;
    }

    public static boolean isValidExecutive (string id, string name, float seniority, string country, string creditCard, string passId) {
	if (isValidId(id) && isValidName(name) && idValidSeniority(seniority) && isValidCountry(country) && isValidcreditCard(creditCard), isValidPassId(passId)) {
	    return true;
	}else return false;
    }
    

    //metodos getters
    public String getPassId() { return passId; }

    //métodos setters
    public void setPassId (String passId) {
	if (isValidPassId(passId)) { this.passId = passId; }
    }
    
    //Método toString
    public String toString() {
	return "E;" + getId() + ";" + getName() + ";" + getSeniority() + ";" + getCountry() + ";" + getCompany() + ";" + getBankAccount() + ";" + passId;
    }


    //metodo para añadir senority
    public void addSeniority (float extraSeniority) {
	setSeniority(Math.min(getSeniority() + extraSeniority, 100));
	//this.seniority = Math.min(this.seniority +  extraSeniority, 100);
    }
}
