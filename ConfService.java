package p4;

public class ConfService {

    private boolean activated;

    public ConfService() {
	activated = false;
    }

    public static boolean isValidActivated(boolean activated) {
	if (activated == true || activated == false) {
	    return true;
	}else return false;
    }	

    public boolean getActivated() { return activated; }
    
    public void setActivated(boolean activated) {
	if (isValidActivated(activated)) { this.activated = activated; }
    }

    public void activate() {
	setActivated(true);
    }

    public void deactivate() {
	setActivated(false);
    }
}
    
