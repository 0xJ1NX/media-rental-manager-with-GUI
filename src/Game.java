
public class Game extends Media {

    private double weight;

    public Game(String id,String title, int copiesAvailable, double weight) {
        super(id,title, copiesAvailable);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    @Override
    public int compareTo(Media o) {
        return Integer.compare(title.compareTo(o.title), 0);
    }

    @Override
    public String toString() {
        return "--- TYPE : "+"Game"+" ---\n"+super.toString()+"\n  - weight : " + weight;
    }
}


//Author: Omar Qalalweh
