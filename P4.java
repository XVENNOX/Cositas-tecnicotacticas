package p4;
import java.io.*;
import java.util.*;

public class P4 {

    static Particular[] particulars = new Particular[25];
    static Employee[] employees = new Employee[25];
    static Executive[] executives = new Executive[25];

    public static void main (String[] args) throws FileNotFoundException{

	if (args.length != 4) {
	    System.out.println("Uso: java P3 <fichero centro coworking> <fichero de entradas y salidas> <fichero en el que guardar el centro tras actualizarlo> <fichero de cotrabajadores>");
	}


	readCoworkersFile(args[3]);

	CoworkingCenter miCoworkingCenter = new CoworkingCenter(args[0]);

	int numCoworkers = miCoworkingCenter.getNumCoworkers();
	//System.out.println("Número de coworkers: " + numCoworkers);

	processIO (miCoworkingCenter, numCoworkers, args[1]);

	System.out.println("Valor actual de la variable numCoworkers = <" + numCoworkers + ">. Número actual de coworkers = <" + miCoworkingCenter.getNumCoworkers() + ">");

	miCoworkingCenter.saveCoworkingCenterToFile(args[2]);
	
       	
    }



    //método proccesIO
    public static void processIO (CoworkingCenter unCoworkingCenter, int numCoworkers, String filename) throws FileNotFoundException {

	File ficheroEntradasSalidas = new File(filename);
	Scanner inputFichero = new Scanner(ficheroEntradasSalidas);
	String lineaFichero;
	String[] partesLinea;
	char tipoEntradaSalida;
	char tipoCoworker;
	String id;
	int contadorBucle = 0;
	    
	while(inputFichero.hasNextLine()) {
	    contadorBucle++;
	    if(contadorBucle > 1000) { break; }

	    lineaFichero =  inputFichero.nextLine().trim();
	    if(!lineaFichero.isEmpty() && !lineaFichero.startsWith("#")) {
		partesLinea = lineaFichero.split(";");

		if(partesLinea.length == 2) {
		    tipoEntradaSalida = partesLinea[0].charAt(0);
		    id = partesLinea[1];
		    //System.out.println(id);

		    if (tipoEntradaSalida == 'I') {
			tipoCoworker = getCoworkerTypeFromId(id);
			unCoworkingCenter.coworkerIn(id, tipoCoworker);
			numCoworkers++;
			
		    }else if (tipoEntradaSalida == 'O') {
			unCoworkingCenter.coworkerOut(id);
			numCoworkers--;
		    }
		}
	    }
	}
	inputFichero.close();
    }
			    

	
    //este método lee o archivo e crea os objetos que despois almacenanse no array
    public static void readCoworkersFile(String filename) throws FileNotFoundException {

	String cadenaDatosCoworker;
	String[] datosCoworker;
	File ficheroCoworkers = new File(filename);
	Scanner inputFicheroCoworkers;
	int contadorCoworkers = 0;
	int contadorParticulars = 0, contadorEmployees = 0, contadorExecutives = 0;
	    

	inputFicheroCoworkers = new Scanner(ficheroCoworkers);
	
	while (inputFicheroCoworkers.hasNext()) {
	    cadenaDatosCoworker = inputFicheroCoworkers.nextLine().trim();
	    datosCoworker = cadenaDatosCoworker.split(";");

	    if(P3.checkParticulars(datosCoworker)) {
		if(contadorParticulars < 25) {
		    Particular particular = new Particular(datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5]);
		    particulars[contadorParticulars] = particular;
		    contadorParticulars++;
		}
	    }

	    if(P3.checkEmployees(datosCoworker)) {
		if(contadorEmployees < 25) {
		    Employee  employee = new Employee (datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5], datosCoworker[6]);
		    employees[contadorEmployees] = employee;
		    contadorEmployees++;
		}
	    }

	    if(P3.checkExecutives(datosCoworker)) {
		if(contadorExecutives < 25) {
		    Executive  executive = new Executive (datosCoworker[1], datosCoworker[2], Float.parseFloat(datosCoworker[3].replace(",",".")), datosCoworker[4], datosCoworker[5], datosCoworker[6], datosCoworker[7]);
		    executives[contadorExecutives] = executive;
		    contadorExecutives++;
		}
	    }
	}
	inputFicheroCoworkers.close();
    }


        //métodos para checkear se os datos estan ben
    public static boolean checkParticulars(String[] datosCoworker) {

	if (datosCoworker[0].equals("P") && Particular.isValidId(datosCoworker[1]) && Particular.isValidName(datosCoworker[2]) && Particular.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",","."))) && Particular.isValidCountry(datosCoworker[4]) && Particular.isValidCreditCard(datosCoworker[5])){
	    return true;
	}else return false;
    }

    public static boolean checkEmployees (String[] datosCoworker) {

	if (datosCoworker[0].equals("E") && Employee.isValidId(datosCoworker[1]) && Employee.isValidName(datosCoworker[2]) && Employee.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",","."))) && Employee.isValidCountry(datosCoworker[4]) && Employee.isValidCompany(datosCoworker[5]) && Employee.isValidBankAccount(datosCoworker[6])){
	    return true;
	}else return false;
    }

    public static boolean checkExecutives (String[] datosCoworker) {

	if (datosCoworker[0].equals("X") && Executive.isValidId(datosCoworker[1]) && Executive.isValidName(datosCoworker[2]) && Executive.isValidSeniority(Float.parseFloat(datosCoworker[3].replace(",", "."))) && Executive.isValidCountry(datosCoworker[4]) && Executive.isValidCompany(datosCoworker[5]) && Executive.isValidBankAccount(datosCoworker[6]) && Executive.isValidPassId(datosCoworker[7])){
	    return true;
	}else return false;
    }


    //metodo para obtener el tipo a partir del Id

    public static char getCoworkerTypeFromId (String id) {

	for (int i=0; i<particulars.length; i++) {
	    if(particulars[i] != null && particulars[i].getId().equals(id)) {
		return 'P';
	    }
	}

	for (int i=0; i<employees.length; i++) {
	    if(employees[i] != null && employees[i].getId().equals(id)) {
		return 'E';
	    }
	}

	for (int i=0; i<executives.length; i++) {
	    if(executives[i] != null && executives[i].getId().equals(id)) {
		return 'X';
	    }
	}
	return '?';
    }


    //método para gardar o mapa
    public void saveCoworkingCenterMap (String filename, CoworkingCenter elCoworkingCenter) throws FileNotFoundException {

	File ficheroMapa = new File(filename);
	PrintWriter pw = new PrintWriter(ficheroMapa);

	pw.print(elCoworkingCenter.toMap());

	pw.close();
    }
}
