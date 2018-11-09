/**
 *
 *
 * Each object of this class is a row of best scores table.
 */
public class Score {

    private String name;
    private int points;

    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }
    // TODO feito

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString() {
        return "Score(" + name + "," + points + ")";
    }


}