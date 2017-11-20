import java.text.DecimalFormat;

public class CopyAccount implements ICopyAcct {

    private String userID;
    private String name;
    private String address;
    private double credits;
    private int monoCopies;
    private int colourCopies;

    private static double costMono = 1.5;
    private static double costColour = 3.5;
    private static double creditCost = 0.10;
    private static double discountThreshold = 20;
    private static int discountRate = 10;

    public CopyAccount(String ID, String name, String address, double credits) {
        this.userID = ID;
        this.name = name;
        this.address = address;
        this.credits = credits;
    }

    public CopyAccount(String ID, String name, String address) {
        this.userID = ID;
        this.name = name;
        this.address = address;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public double getCredits() {
        return credits;
    }

    @Override
    public int getMonoCopies() {
        return monoCopies;
    }

    @Override
    public int getColourCopies() {
        return colourCopies;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String userInfo() {
        return "user ID: " + userID + "" +
                ", name: " + name + "" +
                ", credits available (2dp): " + new DecimalFormat("#.00").format(credits) + "" +
                ", current number of monocopies: " + monoCopies + "" +
                ", current number of colourcopies: " + colourCopies;
    }

    @Override
    public String makeCopy(boolean isMonoCopy, int numPages) {
        String copyMode = isMonoCopy ? "Mono copy" : "Colour copy";
        double fullCost = 0;
        double actualPageCost = isMonoCopy ? costMono : costColour;
        if (numPages > discountThreshold && !isMonoCopy) {
            fullCost = actualPageCost * numPages * (1 - (discountRate * 100));
        } else {
            fullCost = actualPageCost * numPages;
        }
        int printedPages = 0;
        while (credits > actualPageCost && printedPages < numPages) {
            credits -= actualPageCost;
            printedPages += 1;
        }
        if (isMonoCopy) {
            monoCopies+= printedPages;
        } else {
            colourCopies+= printedPages;
        }
        String result = "Copy request has been successfully created." +
                    "\nCopy mode: " + copyMode + "" +
                    ", number of pages: " + numPages + "" +
                    ", cost: " + fullCost;
        if (printedPages < numPages) {
            result+=", please top up your copy credits.";
        }
        return result;
    }

    @Override
    public double topUp(double amount) {
        int countOfCredits = (int) (amount / creditCost);
        return amount / creditCost - countOfCredits;
    }

    @Override
    public int copiesMade() {
        return colourCopies + monoCopies;
    }

    @Override
    public double amountCredited() {
        return (credits + monoCopies * costMono + colourCopies * costColour) * creditCost ;
    }

    public static double getCostMono() {
        return costMono;
    }

    public static void setCostMono(double costMono) {
        CopyAccount.costMono = costMono;
    }

    public static double getCostColour() {
        return costColour;
    }

    public static void setCostColour(double costColour) {
        CopyAccount.costColour = costColour;
    }

    public static double getCreditCost() {
        return creditCost;
    }

    public static void setCreditCost(double creditCost) {
        CopyAccount.creditCost = creditCost;
    }

    public static double getDiscountThreshold() {
        return discountThreshold;
    }

    public static void setDiscountThreshold(double discountThreshold) {
        CopyAccount.discountThreshold = discountThreshold;
    }

    public static int getDiscountRate() {
        return discountRate;
    }

    public static void setDiscountRate(int discountRate) {
        CopyAccount.discountRate = discountRate;
    }
}
