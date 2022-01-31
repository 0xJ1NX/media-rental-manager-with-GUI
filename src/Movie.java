

public class Movie extends Media {

    private String rating;

    public Movie(String id ,String title, int copiesAvailable, String rating) {
        super(id,title,copiesAvailable);
        setRating(rating);
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) throws RuntimeException {
        if((rating.equalsIgnoreCase("DR") || rating.equalsIgnoreCase("HR") || rating.equalsIgnoreCase("AC"))) {
            this.rating = rating.toUpperCase();
        }else {
            throw new RuntimeException("ERROR , Rating Should Be (HR , DR or AC)");
        }
    }

    @Override
    public int compareTo(Media o) {
        return Integer.compare(title.compareTo(o.title), 0);
    }

    @Override
    public String toString() {
        return "--- TYPE : "+"Movie"+" ---\n"+super.toString()+ "\n  - Rating : " + rating;
    }
}


//Author: Omar Qalalweh
