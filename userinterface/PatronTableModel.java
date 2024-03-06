package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class PatronTableModel
{
    private final SimpleStringProperty patronId;
	private final SimpleStringProperty name;
	private final SimpleStringProperty address;
	private final SimpleStringProperty city;
	private final SimpleStringProperty stateCode;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty email;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty status;

	//----------------------------------------------------------------------------
	public PatronTableModel(Vector<String> patronData)
	{
		patronId =  new SimpleStringProperty(patronData.elementAt(0));
		name =  new SimpleStringProperty(patronData.elementAt(1));
		address =  new SimpleStringProperty(patronData.elementAt(2));
        city =  new SimpleStringProperty(patronData.elementAt(3));
		stateCode =  new SimpleStringProperty(patronData.elementAt(4));
        zip =  new SimpleStringProperty(patronData.elementAt(5));
        email =  new SimpleStringProperty(patronData.elementAt(6));
        dateOfBirth =  new SimpleStringProperty(patronData.elementAt(7));
        status =  new SimpleStringProperty(patronData.elementAt(8));
    }

	//----------------------------------------------------------------------------
	public String getPatronId() {
        return patronId.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronId(String id) {
        patronId.set(id);
    }

    //----------------------------------------------------------------------------
    public String getName() {
        return name.get();
    }

    //----------------------------------------------------------------------------
    public void setName(String nme) {
        name.set(nme);
    }

    //----------------------------------------------------------------------------
    public String getAddress() {
        return address.get();
    }

    //----------------------------------------------------------------------------
    public void setAddress(String addr) {
        address.set(addr);
    }

    //----------------------------------------------------------------------------
    public String getCity() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setCity(String cty) {
        city.set(cty);
    }

    //----------------------------------------------------------------------------
    public String getStateCode() {
        return stateCode.get();
    }

    //----------------------------------------------------------------------------
    public void setStateCode(String code) {
        stateCode.set(code);
    }

    //----------------------------------------------------------------------------
    public String getzip() {
        return zip.get();
    }

    //----------------------------------------------------------------------------
    public void setZip(String zp) {
        zip.set(zp);
    }

    //----------------------------------------------------------------------------
    public String getEmail() {
        return email.get();
    }

    //----------------------------------------------------------------------------
    public void setEmail(String eml) {
        email.set(eml);
    }
    
    //----------------------------------------------------------------------------
    public String getStatus() {
        return status.get();
    }

    //----------------------------------------------------------------------------
    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    //----------------------------------------------------------------------------
    public void setDateOfBirth(String dob) {
        dateOfBirth.set(dob);
    }

    //----------------------------------------------------------------------------
    public void setStatus(String sts)
    {
    	status.set(sts);
    }
}
