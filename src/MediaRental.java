
import java.text.SimpleDateFormat;
import java.util.*;


public class MediaRental implements MediaRentalInt {

    private final ArrayList <Customer> customers = new ArrayList<>() ;
    private final ArrayList <Media> mediaList = new ArrayList<>() ;
    private int limitedPlanLimit = 2;

    public MediaRental(){             //default constructor (empty and plan limit = 2)
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public ArrayList<Media> getMediaList() {
        return mediaList;
    }



    @Override
    public void addCustomer(String id , String name,String mobileNum, String address, String plan) throws RuntimeException {      //methode for adding a customer
        boolean customerE = (linearSearchCustomersById(id) != -1);     //if customer exist in the database(arraylist)
        if(!name.isEmpty()&&!address.isEmpty()&&!plan.isEmpty()&&!id.isEmpty()&&!mobileNum.isEmpty()) {
            if (!customerE){
                Customer customer = new Customer(id.trim(),name.trim(),mobileNum.trim(),address.trim(),plan.trim());     //creating a new customer object
                customers.add(customer);    //adding the customer to the array list
            }else {
                throw new IllegalArgumentException("The Customer already Exist.");   //some exceptions  ;)
            }
        }else{
            throw new RuntimeException("Fields can't be empty.");
        }
    }       //done
    @Override
    public void addMovie(String code ,String title, int copiesAvailable, String rating) throws RuntimeException {    // methode for adding a movie

        boolean mediaExist = (linearSearchMediaByCode(code) != -1);    // if the media title in the database (expecting that THERE IS NO MEDIA WITH SAME TITLE )
        if (!title.isEmpty() && !rating.isEmpty() && !code.isEmpty()) {
            if(!mediaExist) {
                Movie movie = new Movie(code.trim(),title.trim(), copiesAvailable, rating.trim());     // creating Movie object and add it to the array list
                mediaList.add(movie);
            }else {
                throw new IllegalArgumentException(" Media with the same code Exist.");   //some exceptions  ;)
            }

        } else{
            throw new RuntimeException("Fields can't be empty. ");
        }

    }  //done
    @Override
    public void addGame(String code ,String title, int copiesAvailable, double weight) throws RuntimeException{   // methode for adding a game media
        if (!title.isEmpty()) {
            boolean mediaExist = (linearSearchMediaByCode(code) != -1);
            if(!mediaExist) {
                Game game = new Game(code.trim() ,title.trim(), copiesAvailable, weight);  //creating the game object and adding it to the array list
                mediaList.add(game);
            }else {
                throw new IllegalArgumentException("Media with the same code Exist.");  //you know , exceptions also
            }
        } else{
            throw new RuntimeException("Fields can't be empty. ");
        }
    }    //done
    @Override
    public void addAlbum(String code ,String title, int copiesAvailable, String artist, String songs) throws RuntimeException {
        boolean mediaExist = (linearSearchMediaByCode(code) != -1);     // bla , bla same thing here (test if the media exist)
        if (!title.isEmpty() && !artist.isEmpty() && !songs.isEmpty()) {
            if(!mediaExist) {

                Album album = new Album(code.trim() ,title.trim(), copiesAvailable, artist, new ArrayList<>(Arrays.asList(Arrays.stream(songs.trim().split(",")).map(String::trim).toArray(String[]::new))));
                mediaList.add(album);       // creating the album object and adding it to the array list

            }else {
                throw new IllegalArgumentException("Media with the same code Exist.");   // :)
            }
        } else{
            throw new RuntimeException("Fields can't be empty. ");
        }


    }   //done


    @Override
    public void setLimitedPlanLimit(int value) {    //methode for setting the limited plan limit
        this.limitedPlanLimit = value;
    }     //done

    @Override
    public String getAllCustomersInfo() {    // return all the customers as string ( calling the toString)
        Collections.sort(customers);
        StringBuilder str = new StringBuilder();
        for (Customer customer : customers) {
            str.append(customer).append("\n");   // appending every customer to a string builder
        }
        return str.toString();
    }    //done


    @Override
    public String getAllMediaInfo() { // return all the media info as string ( calling the toString)

        Collections.sort(mediaList);
        StringBuilder str = new StringBuilder();
        for (Media media : mediaList) {
            str.append(media).append("\n\n");  // appending every media to a string builder
        }
        return str.toString();
    }       //done


    @Override
    public boolean addToCart(String id, String code)  {   // adding the media to the cart of customer (given the customer id and media code)

        boolean success = false;
        int indexOfCus = linearSearchCustomersById(id);    //getting the customer index that satisfy the name

        if(indexOfCus >= 0 && ( customers.get(indexOfCus).getInterestedMediaList().isEmpty() || !customers.get(indexOfCus).getInterestedMediaList().contains(code)  )){
            if (linearSearchMediaByCode(code) >= 0) {   //test if the customer exist and if the media exist and if it's not added to the cart already
                customers.get(indexOfCus).getInterestedMediaList().add(code.trim());  // adding the media to the customer cart
                success = true;
            }
        }
        return success;   //returning if added or no

    }       //done


    @Override
    public boolean removeFromCart(String id, String code) {   // removing the media from the customer cart (given customer name and media title )
        int indexOfCus = linearSearchCustomersById(id);
        if (indexOfCus >= 0) {
            return customers.get(indexOfCus).getInterestedMediaList().remove(code);
        }
        return false;
    }   //done


    @Override
    public String processRequests(String id) throws RuntimeException {// processing the cart of every customer in alphabetic order

        StringBuilder logs = new StringBuilder();
        int indexOfCus = linearSearchCustomersById(id);
        Customer c = customers.get(indexOfCus);
        ArrayList<String> cart = c.getInterestedMediaList();     //interested list as the cart

        if(c.getPlan().equalsIgnoreCase("UNLIMITED")){   //if the customer is unlimited add every thing in the cart if it's available
            if(cart.size() > 0) {
                sendToRented(logs, c, cart);        //method to add the media and decrement the copies available
            }
        }
        else if (c.getPlan().equalsIgnoreCase("LIMITED")){    //limited plan customers
            int limitAllowed = limitedPlanLimit - c.getRentedMediaList().size();   // creating a limit for the cart (interested list) to add media from the cart depending on the limit
            if(cart.size() > 0 && limitAllowed >= cart.size()) {
                sendToRented(logs, c, cart);    //method to add the media and decrement the copies available
            }else {
                throw new RuntimeException("The Cart Has More Than Allowed");
            }
        }
        return logs.toString();    //the method sendToRented will add logs (sending {Media} to {Customer}) to the string builder , so will return it here.
    }


    @Override
    public boolean returnMedia(String id, String code) throws RuntimeException {   //return the media and increment the database number of copies
        int indexOfCus = linearSearchCustomersById(id);  // linear search for the customer index
        if (indexOfCus == -1) {   //if customer doesn't exist
            throw new RuntimeException("Customer does Not Exist");
        }
        int indexOfMedia = linearSearchMediaByCode(code);
        if (indexOfMedia == -1){
            throw new RuntimeException("Media does Not Exist");
        }
        if(customers.get(indexOfCus).getRentedMediaList().remove(code)){   // if the media removed successfully increment the storage(copies available)
            mediaList.get(indexOfMedia).setNumOfCopies(mediaList.get(indexOfMedia).numOfCopies + 1);
            return true;
        }
        return false;
    }   //done


    @Override
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {   //searching media depend on the title or any properties of them (like filters) also none of them(will return everything)
        ArrayList<String> result = new ArrayList<>();   //creating
        String[] songList = songs.split(","); // for splitting the songs after getting it seperated by comma

        for (Media m : mediaList) {      //moving on every media
            boolean titleMatch = false, ratingMatch = false, artistMatch = false, songsMatch = false;   //boolean if the search result exists
            if (title.isEmpty()|| title.equals(m.title)) {
                titleMatch = true;     //if the title parameter is empty or title exist
            }
            if (m instanceof Movie) {  // if it is a movie
                if (rating.isEmpty() || rating.equalsIgnoreCase(((Movie) m).getRating())) {
                    ratingMatch = true;      //if the rating match after the tittle
                }
                if(artist.isEmpty() && songs.isEmpty()) {
                    artistMatch = true;   // movie has a rating only ,so if the other parameter is empty, so you will get the search result
                    songsMatch = true;
                }
            }
            if (m instanceof Album) {  // if it is an album
                if (artist.isEmpty() || artist.equalsIgnoreCase(((Album) m).getArtist())) {
                    artistMatch = true;     // if  the artist or song empty or matches then the result exist
                }
                if(songs.isEmpty()){
                    songsMatch = true;
                }else {
                    for (String s : songList) {   //to see if the songs array list match the searching parameters
                        if (!((Album) m).getSongs().contains(s)) {
                            songsMatch = false;
                            break;
                        } else {
                            songsMatch = true;
                        }
                    }
                }
                if(rating.isEmpty()) {
                    ratingMatch = true;    //albums don't have a rating, so you should keep it empty
                }
            }
            if (m instanceof Game){
                if (artist.isEmpty() && songs.isEmpty() && rating.isEmpty()) {
                    ratingMatch = true;
                    songsMatch = true;
                    artistMatch = true;      // if you are searching for a game it hase the title parameter only so keep the other fields empty
                }                           // if it iss not empty you won't get a result...
            }
            if (titleMatch && artistMatch && songsMatch && ratingMatch) {
                result.add(m.title);    // if the media matches all searching parameters add it to result arraylist
            }
        }
        Collections.sort(result);
        return result;
    }   //done


    private void sendToRented(StringBuilder logs, Customer c, ArrayList<String> cart) {

        Iterator<String> iterator = cart.iterator();
        while (iterator.hasNext()){
            String s = iterator.next().trim();
            int indexOfMedia = linearSearchMediaByCode(s);
            if (indexOfMedia >= 0 && mediaList.get(indexOfMedia).numOfCopies > 0) {    // to see if the media exist and it is available
                c.getRentedMediaList().add(s.trim());
                mediaList.get(indexOfMedia).setNumOfCopies(mediaList.get(indexOfMedia).numOfCopies - 1);
                iterator.remove();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                logs.append("SENDING ").append(mediaList.get(indexOfMedia).title).append(" TO ").append(c.getName()).append(" - Date - ").append(formatter.format(date)).append("\n");   //adding the logs
            }
        }


    }

    private int linearSearchCustomersById(String id){   //linear search for customer by name (returning index)
        for (int i = 0; i < customers.size(); i++) {
            if (id.trim().equalsIgnoreCase(customers.get(i).getId()))
                return i;
        }
        return -1;
    }    //linear search customers by name

    private int linearSearchMediaByCode(String code){   // linear search media by code  (returning index)
        for (int i = 0; i < mediaList.size(); i++) {
            if (mediaList.get(i).code.equalsIgnoreCase(code.trim())){
                return i;
            }
        }
        return -1;
    }

    public int getLimitedPlanLimit() {
        return limitedPlanLimit;
    }
}


//Author: Omar Qalalweh
