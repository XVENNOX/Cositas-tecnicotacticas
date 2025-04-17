package p4;

public class Employee extends Coworker{

    private String company;
    private String bankAccount;

    public static final int MAX_COMPANY_LENGTH = 30;
    public static final String BANK_ACCOUNT_FORMAT = "LLDDDDDDDDDDDDDDDDDDDDDD"; //D=0...9, L=A...Z

    //constructores
    public Employee() {}
    
    public Employee (String id, String name, float seniority, String country, String company, String bankAccount) {
	super(id, name, seniority, country);
	this.company = company;
	this.bankAccount = bankAccount;
    }

    public Employee (Employee p) {
	super(p.getId(), p.getName(), p.getSeniority(), p.getCountry());
	this.company = p.company;
	this.bankAccount = p.bankAccount;
    }

    //Métodos de validacion
    public static boolean isValidCompany (String company) {
	if (company.length() < 0 || company.length() > 30 || company == null) {
	    System.out.println("Incorrect company value: <" + company + ">. Valid range is <0>-<" + MAX_COMPANY_LENGTH + ">");
	    return false;
	}

	return true;
    }

    public static boolean isValidBankAccount (String bankAccount) {
	if (bankAccount == null || bankAccount.length() != 24) {
	    return false;
	}
	if (!Character.isLetter(bankAccount.charAt(0)) || !Character.isLetter(bankAccount.charAt(1))) {
	    System.out.println("Incorrect bank account value: <" + bankAccount + ">. Not comply with format <" + BANK_ACCOUNT_FORMAT + ">");
	    return false;
	}
	    
	for (int i=2; i<24; i++) {
	    if (!Character.isDigit(bankAccount.charAt(i))) {
		System.out.println("Incorrect bank account value: <" + bankAccount + ">. Not comply with format <" + BANK_ACCOUNT_FORMAT + ">");
		return false;
	    }
	}
	return true;	    
    }

    public static boolean isValidEmployee (string id, string name, float seniority, string country, string company, string bankAccount) {
	if (isValidId(id) && isValidName(name) && idValidSeniority(seniority) && isValidCountry(country) && isValidCompany(company) && isValidBankAccount(bankAccount)) {
	    return true;
	}else return false;
    }
    
	

    //metodos getters
    public String getCompany() { return company; }
    public String getBankAccount() { return bankAccount; }

    //métodos setters
    public void setCompany (String company) {
	if (isValidCompany(company)) { this.company = company; }
    }
    public void setBankAccount (String bankAccount) {
	if (isValidBankAccount(bankAccount)) { this.bankAccount = bankAccount; }
    }

    //Método toString
    public String toString() {
	return "E;" + getId() + ";" + getName() + ";" + getSeniority() + ";" + getCountry() + ";" + company + ";" + bankAccount;
    }
}
