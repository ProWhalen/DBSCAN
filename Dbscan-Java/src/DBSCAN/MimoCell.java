package DBSCAN;

import java.util.ArrayList;

public class MimoCell {
    private double x, y;
    private int clusterId = 0;
    private int id;
    private ArrayList<MimoCell> neighbors;

    public MimoCell(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.clusterId = Dbscan.NOT_VISITED;
        this.neighbors = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public int getClusterId() {
        return clusterId;
    }

    public ArrayList<MimoCell> getNeighbors() {
        return neighbors;
    }

    public void addNeighbors(MimoCell cell) {
        this.neighbors.add(cell);
    }


    public static double EuclideanDistance(MimoCell X1, MimoCell X2) {
        double dx = X1.getX() - X2.getX();
        double dy = X1.getY() - X2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
