
import java.util.ArrayList;

public class Customer implements Comparable<Customer>  {

    private final String id;
    private String mobileNum;
    private String name;
    private String address;
    private String plan;

    private ArrayList<String> interestedMediaList = new ArrayList<>();
    private ArrayList<String> rentedMediaList = new ArrayList<>();


    public Customer(String id,String name,String mobileNum, String address, String plan) {
        this.id = id;
        this.name = name;
        this.mobileNum = mobileNum;
        this.address = address;
        setPlan(plan);
    }

    public String getId() {
        return id;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) throws RuntimeException {

        if (plan.equalsIgnoreCase("LIMITED")||plan.equalsIgnoreCase("UNLIMITED"))
            this.plan = plan.toUpperCase();
        else {
            throw new RuntimeException("ERROR , Plan must be ( LIMITED or UNLIMITED )");
        }
    }

    public ArrayList<String> getInterestedMediaList() {
        return interestedMediaList;
    }

    public void setInterestedMediaList(ArrayList<String> interestedMediaList) {
        this.interestedMediaList = interestedMediaList;
    }

    public ArrayList<String> getRentedMediaList() {
        return rentedMediaList;
    }

    public void setRentedMediaList(ArrayList<String> rentedMediaList) {
        this.rentedMediaList = rentedMediaList;
    }


    @Override
    public int compareTo(Customer o) {
        return Integer.compare(name.compareTo(o.name), 0);
    }

    @Override
    public String toString() {
        return  "#Customer Id : " + id +"\n  - name : " + name + "\n  - address : " + address + "\n  - plan : " + plan +  "\n  - interestedMediaList : " + interestedMediaList +" (By Code)"+ "\n  - rentedMediaList : " + rentedMediaList+ " (By Code)" ;
    }
}
