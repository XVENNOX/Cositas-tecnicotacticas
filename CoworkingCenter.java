package p3;
import java.io.*;
import java.util.*;


public class CoworkingCenter {

    private short sizeFloors;
    private short sizeCorridors;
    private short sizeNums;
    private short upperParticularFloor;
    private Office[][][] offices;


    //metodos constructores

    public CoworkingCenter () {
    }

    public CoworkingCenter (short sizeFloors, short sizeCorridors, short SizeNums, short upperParticularFloor, Office[][][] offices) {
	this.sizeFloors = sizeFloors;
	this.sizeCorridors = sizeCorridors;
	this.sizeNums = sizeNums;
	this.upperParticularFloor = upperParticularFloor;
	this.offices = offices;
    }
	
    
    public CoworkingCenter (CoworkingCenter p) {
	this.sizeFloors = p.sizeFloors;
	this.sizeCorridors = p.sizeCorridors;
	this.sizeNums = p.sizeNums;
	this.upperParticularFloor = p.upperParticularFloor;
	this.offices = p.offices;
    }


    //constructor a partir de fichero

    public CoworkingCenter (String filename) throws FileNotFoundException {

	File ficheroOffice = new File(filename);
	Scanner inputOffice;
	String lineaFicheroOffice;
	String[] dimensionsCentro;
	String[] primeraSeparacionLinea;
	String[] segundaSeparacionLinea;

	inputOffice = new Scanner(ficheroOffice);
	
	//leer as dimensions do centro e crear o array tridimensional
	while (inputOffice.hasNext()) {
	    lineaFicheroOffice = inputOffice.nextLine().trim();
	    if (!lineaFicheroOffice.isEmpty() && !lineaFicheroOffice.startsWith("#")) {
		dimensionsCentro = lineaFicheroOffice.split(";");
		if (dimensionsCentro.length == 4) {
		    sizeFloors = Short.parseShort(dimensionsCentro[0]);
		    sizeCorridors = Short.parseShort(dimensionsCentro[1]);
		    sizeNums = Short.parseShort(dimensionsCentro[2]);
		    upperParticularFloor = Short.parseShort(dimensionsCentro[3]);
		    break;
		}
	    }
	}
	inputOffice.close();
	
	offices = new Office[sizeFloors][sizeCorridors][sizeNums];
	
	
	//inicializamos o array con objetos office
	for (int i=0; i<sizeFloors; i++) {
	    for (int j=0; j<sizeCorridors; j++) {
		for (int k=0; k<sizeNums; k++) {
		    offices[i][j][k] = new Office(new OfficeLocator((short) i,(short) j, (short) k), null);
		}
	    }
	}
	
	inputOffice = new Scanner(ficheroOffice);
	
	//leer resto do documento e asignar os ids
	while (inputOffice.hasNext()) {
	    lineaFicheroOffice = inputOffice.nextLine().trim();
	    if(!lineaFicheroOffice.isEmpty() && !lineaFicheroOffice.startsWith("#")) {
		primeraSeparacionLinea = lineaFicheroOffice.split(";");
		if(primeraSeparacionLinea.length == 2) {
		    segundaSeparacionLinea = primeraSeparacionLinea[0].split(":");
		    if(segundaSeparacionLinea.length == 3) {
			short floor = Short.parseShort(segundaSeparacionLinea[0]);
			short corridor = Short.parseShort(segundaSeparacionLinea[1]);
			short num = Short.parseShort(segundaSeparacionLinea[2]);
			String idCoworker = primeraSeparacionLinea[1];
			if(isValidSizeFloors(floor) && isValidSizeCorridors(corridor) && isValidSizeNums(num)) {
			    offices[floor][corridor][num].setIdCoworker(idCoworker);
			}
		    }
		}
	    }
	}
	inputOffice.close();
    }
			    
	
    //métodos de validación

    public static boolean isValidSizeFloors (short sizeFloor) {
	if (sizeFloor < 0) {
	    System.out.println("Invalid sizeFloor value. Valid range must not be negative");
	    return false;
	}else return true;
    }

    public static boolean isValidSizeCorridors (short sizeCorridors) {
	if (sizeCorridors < 0) {
	    System.out.println("Invalid sizeCorridors value. Valid range must not be negative");
	    return false;
	}else return true;
    }

    public static boolean isValidSizeNums (short sizeNums) {
	if (sizeNums < 0) {
	    System.out.println("Invalid sizeNums value. Valid range must not be negative");
	    return false;
	}else return true;
    }

    public static boolean isValidUpperParticularFloor (short upperParticularFloor) {
	if (upperParticularFloor < 0) {
	    System.out.println("Invalid upperParticularFloor value. Valid range must not be negative");
	    return false;
	}else return true;
    }
       
    
    //métodos getters

    public short getSizeCorridors () { return sizeCorridors; }
    public short getSizeFloors () { return sizeFloors; }
    public short getSizeNums () { return sizeNums; }
    public short getUpperParticularFloor () { return upperParticularFloor; }
    public Office[][][] getOffices () { return offices; }


    //métodos setters

    public void setSizeFloors (short sizeFloors){
	if (isValidSizeFloors(sizeFloors)) { this.sizeFloors = sizeFloors; }
    }
    public void setSizeCorridors (short sizeCorridors) {
	if (isValidSizeCorridors(sizeCorridors)) { this.sizeCorridors = sizeCorridors; }
    }
    public void setSizeNums (short sizeNums) {
	if (isValidSizeNums(sizeNums)) { this.sizeNums = sizeNums; }
    }
    public void setUpperParticularFloor (short upperparticularFloor) {
	if (isValidUpperParticularFloor(upperParticularFloor)) { this.upperParticularFloor = upperParticularFloor; }
    }


    //metodo para almacenar el fichero

    public void saveCoworkingCenterToFile (String filename) throws FileNotFoundException{

	File ficheroCoworkingCenter = new File(filename);
	PrintWriter pw = new PrintWriter(ficheroCoworkingCenter);

	pw.println(sizeFloors + ";" + sizeCorridors + ";" + sizeNums + ";" + upperParticularFloor);
	
	for(int i=sizeFloors -1 ; i>=0; i--) {
	    for (int j=0; j<sizeCorridors; j++) {
		for (int k=0; k<sizeNums; k++) {
		    if(offices[i][j][k].getIdCoworker() != null) {
			System.out.println("Escribiendo oficina: " + offices[i][j][k].toString());
			pw.println(offices[i][j][k].toString());

		    }
		}
	    }
	}  
	pw.flush();
	pw.close();
    }
    
    //metodo de entrada de coworkers

    public void coworkerIn (String id, char coworkerType) {

	int upperFloor, bottomFloor;
	
	if (coworkerType == 'P') {
	    upperFloor = upperParticularFloor;
	    bottomFloor = 0;
	}else if (coworkerType == 'E') {
	    upperFloor = sizeFloors - 2;
	    bottomFloor = 0;
	}else {
	    upperFloor = sizeFloors - 1;
	    bottomFloor = 0;
	}


	//Buscar unha oficina disponible
	for (int i= upperFloor; i>= bottomFloor; i--) {
	    for (int j=0; j<sizeCorridors; j++) {
		for (int k=0; k<sizeNums; k++) {
		    if (offices[i][j][k].getIdCoworker() == null) {
			offices[i][j][k].setIdCoworker(id);
			System.out.println(id + "Asignado a oficina" + i + j + k );
			return;
		    }
		}
	    }
	}
    }


    //metodo de salida de coworkers

    public void coworkerOut (String idCoworker) {

	for (int i=0; i<sizeFloors; i++) {
	    for (int j=0; j<sizeCorridors; j++) {
		for (int k=0; k<sizeNums; k++) {
		    if (idCoworker.equals(offices[i][j][k].getIdCoworker())) {
			System.out.println("Eliminando a: " + offices[i][j][k].getIdCoworker() + " de la oficina " + i + j + k );
			offices[i][j][k].setIdCoworker(null);
			return;
		    }
		}
	    }
	}
    }

    //método para conseguir o numero de oficinas ocupadas

    public int getNumCoworkers () {

	int contadorOficinasOcupadas = 0;

	for (int i=0; i<sizeFloors; i++) {
	    for (int j=0; j<sizeCorridors; j++) {
		for (int k=0; k<sizeNums; k++) {
		    if (offices[i][j][k].getIdCoworker() != null) {
			contadorOficinasOcupadas++;
		    }
		}
	    }
	}
	return  contadorOficinasOcupadas;
    }


    //método para crear mapa con caracter de separacion |
    public String toMap () {

	String map = "";

	String lineSeparator = "-".repeat(4 + (18 * sizeNums)) + "\n";

	for (int i=sizeFloors -1 ; i>=0; i++) {
	    map += lineSeparator;
	    map += "FLOOR" + i + "\n";

		for (int j=0; j<sizeCorridors; j++) {
		    String floorTag;

		    if(i == sizeFloors - 1) {
			floorTag = "X  ";
		    }else if (j > upperParticularFloor) {
			floorTag = "XE ";
		    }else {
			floorTag = "XEP";
		    }

		    String line = floorTag + "|";

		    for(int k=0; k<sizeNums; k++) {
			Office office = offices[i][j][k];
			String officeLocator = office.getOfficeLocator().toString();
			String id;
			if (office.getIdCoworker() != null) {
			    id = office.getIdCoworker();
			}else {
			    id = "         ";
			}

			line += " " + officeLocator + " " + id + " " + "|";
		    }
		}
	    map += lineSeparator;
	}
	return map;
    }

    //crear mapa con caracter de separacion elegido
    public String toMap (char caracter) {

	String map = "";

	String lineSeparator = "-".repeat(4 + (18 * sizeNums)) + "\n";

	for (int i=sizeFloors -1 ; i>=0; i++) {
	    map += lineSeparator;
	    map += "FLOOR" + i + "\n";

		for (int j=0; j<sizeCorridors; j++) {
		    String floorTag;

		    if(i == sizeFloors - 1) {
			floorTag = "X  ";
		    }else if (j > upperParticularFloor) {
			floorTag = "XE ";
		    }else {
			floorTag = "XEP";
		    }

		    String line = floorTag + caracter;

		    for(int k=0; k<sizeNums; k++) {
			Office office = offices[i][j][k];
			String officeLocator = office.getOfficeLocator().toString();
			String id;
			if (office.getIdCoworker() != null) {
			    id = office.getIdCoworker();
			}else {
			    id = "         ";
			}

			line += " " + officeLocator + " " + id + " " + caracter;
		    }
		}
	    map += lineSeparator;
	}
	return map;
    }
}
