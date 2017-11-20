
public interface ICopyAcct {
    // getters
    public String getName();
    public String getAddress();
    public String getUserID();
    public double getCredits();
    public int getMonoCopies();
    public int getColourCopies();

    // setters
    public void setName(String name);
    public void setAddress(String address);

    // object methods
    public String userInfo();
    public String makeCopy(boolean isMonoCopy, int numPages);
    public double topUp(double amount);
    public int copiesMade();
    public double amountCredited();

}
