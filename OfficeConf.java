package p4;

public class OfficeConf extends Oficce {

    private ConfService confService;

    public OfficeConf(OfficeLocator officeLocator, string idCoworker, ConfService confService) {
	super(officeLocator, idCoworker);
	this.confService = confService;
    }

    // public static boolena isValidConfService(ConfService confService){
    //if (isValidActivated)

    public ConfService getConfService () { return confService; }
}
