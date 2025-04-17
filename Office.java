package p4;
import java.io.*;
import java.util.*;


public class Office {

    private OfficeLocator officeLocator;   //objeto da clase OfficeLocator coa localización
    private String idCoworker;             //cadena de tamaño 9 co dni do cotraballador

    private static final String ID_COWORKER_FORMAT = "DDDDDDDDL";
    
    
    //constructores

    public Office () {
    }

    public Office (OfficeLocator officeLocator, String idCoworker) {
	this.idCoworker = idCoworker;
	this.officeLocator = officeLocator;
    }

    public Office (Office p) {
	this.idCoworker = p.idCoworker;
	this.officeLocator = p.officeLocator;
    }


    //métodos de validacion

    public static boolean isValidIdCoworker (String idCoworker) {
	if (idCoworker != null) {
	    if (idCoworker.length() != 9) {
		System.out.println("Incorrect idCoworker value: <" + idCoworker + ">. Not comply with format <" + ID_COWORKER_FORMAT + ">");
		return false;
	    }
	

	    for (int i=0; i<8; i++) { 
		if (!Character.isDigit(idCoworker.charAt(i))) {
		    System.out.println("Incorrect idCoworker value: <" + idCoworker + ">. Not comply with format <" + ID_COWORKER_FORMAT + ">");
		    return false;
		}
	    }

	    char ultimoChar = idCoworker.charAt(8);
	    if (!Character.isLetter(ultimoChar)) {
		System.out.println("Incorrect idCoworker value: <" + idCoworker + ">. Not comply with format <" + ID_COWORKER_FORMAT + ">");
		return false;
	    }
	}
	
	return true;
    }

    public static boolean isValidOfficeLocator (OfficeLocator officeLocator) {
	if (OfficeLocator.isValidFloor(officeLocator.getFloor()) && OfficeLocator.isValidCorridor(officeLocator.getCorridor()) && OfficeLocator.isValidNum(officeLocator.getNum())) {
	    return true;
	}else return false;
    }


    //métodos getters

    public OfficeLocator getOfficeLocator () { return officeLocator; }
    public String getIdCoworker () { return idCoworker; }


    //métodos setters

    public void setIdCoworker (String idCoworker) {
	if (isValidIdCoworker(idCoworker)) { this.idCoworker = idCoworker; }
    }
    public void setOfficeLocator (OfficeLocator officeLocator) {
	if(isValidOfficeLocator(officeLocator)) { this.officeLocator = officeLocator; }
    }


    //método equals

    public boolean equals (Office anotherOffice) {
	if (this.officeLocator.equals(anotherOffice.officeLocator)) {
	    return true;
	}else return false;
    }

    public String toString () {
	if (idCoworker != null) {
	    return officeLocator.toString() + ";" + idCoworker;
	}else return officeLocator.toString();
    }
}
	
