package p4;
import java.io.*;
import java.lang.*;

public class CoworkerBD {

    static Coworker[] coworkers = new Coworker[100];
    
    //este método lee o archivo e crea os objetos que despois almacenanse no array
    public static void readCoworkersFile(String filename) throws FileNotFoundException {

	String cadenaDatosCoworker;
	String[] datosCoworker;
	File ficheroCoworkers = new File(filename);
	Scanner inputFicheroCoworkers;
	int contadorCoworkers = 0;

	
	inputFicheroCoworkers = new Scanner(ficheroCoworkers);
	
	while (inputFicheroCoworkers.hasNext()) {
	    cadenaDatosCoworker = inputFicheroCoworkers.nextLine().trim();
	    datosCoworker = cadenaDatosCoworker.split(";");
	    

	    if(checkCoworker(datosCoworker)){
		if(contadorCoworkers < 100) {
		    switch (datosCoworker[0]) {
		    case 'P':
			Particular particular = new Particular(datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5]);
			coworkers[contadorCoworkers] = particular;
			contadorCoworkers++;
			break;
			
		    case 'E':
			Employee employee = new Employee (datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5], datosCoworker[6]);
			coworkers[contadorcoworkers] = employee;
			contadorCoworkers++;
			break;
			
		    case 'X':
			Executive executive = new Executive (datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5], datosCoworker[6], datosCoworker[7]);
			coworkers[contadorCoworkers] = executive;
			contadorCoworkers++;
			break;
		    }
		}
	    }
	}inputFicheroCoworker.close();   
    }		
		    
    
    //método para checkear coworker
    public static boolean checkCoworker(String[] datosCoworker) {

	switch(datosCoworker[0]){
	case 'P':
	    if (Particular.isValidId(datosCoworker[1]) && Particular.isValidName(datosCoworker[2]) && Particular.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",","."))) && Particular.isValidCountry(datosCoworker[4]) && Particular.isValidCreditCard(datosCoworker[5])){
		return true;
	    }else return false;
	    break;
	    
	case 'E':
	    if (Employee.isValidId(datosCoworker[1]) && Employee.isValidName(datosCoworker[2]) && Employee.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",","."))) && Employee.isValidCountry(datosCoworker[4]) && Employee.isValidCompany(datosCoworker[5]) && Employee.isValidBankAccount(datosCoworker[6])){
		return true;
	    }else return false;
	    break;

	case 'X':
	    if (Executive.isValidId(datosCoworker[1]) && Executive.isValidName(datosCoworker[2]) && Executive.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",", "."))) && Executive.isValidCountry(datosCoworker[4]) && Executive.isValidCompany(datosCoworker[5]) && Executive.isValidBankAccount(datosCoworker[6]) && Executive.isValidPassId(datosCoworker[7])){
		return true;
	    }else return false;
	    break;

	deafualt:
	    //println("Not a valid coworker type");
	    return false;
	}
    }
}

