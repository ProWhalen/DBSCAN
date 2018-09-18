package DBSCAN;

import java.util.ArrayList;


public class Dbscan {
    public static final int NOT_VISITED = -1;
    public static final int NOISE = -2;

    private double maxClusterRadius;
    private int minClusterNum;
    private ArrayList<MimoCell> cells;

    private int clusterNum = 0;

    public Dbscan(double maxClusterRadius, int minClusterNum, ArrayList<MimoCell> cells) {
        this.maxClusterRadius = maxClusterRadius;
        this.minClusterNum = minClusterNum;
        this.cells = cells;
    }

    private void findNeighbors() {
        for(int i = 0; i < this.cells.size(); i++) {
            MimoCell cell = this.cells.get(i);
            for(int j = 0; j < this.cells.size(); j++) {
                if(i == j) {continue;}
                MimoCell candidate = this.cells.get(j);
                double dist = MimoCell.EuclideanDistance(cell, candidate);
                if(dist <= this.maxClusterRadius) {
                    cell.addNeighbors(candidate);
                }
            }
        }
    }

    public void Cluster() {
        findNeighbors();
        int counter = 0;
        for (int i = 0; i < this.cells.size(); i++) {
            MimoCell curCell = this.cells.get(i);
            if (curCell.getClusterId() == NOT_VISITED) {
                ArrayList<MimoCell> neighbors = curCell.getNeighbors();
                if (neighbors.size() < this.minClusterNum) {
                    curCell.setClusterId(NOISE);
                } else {
                    counter++;
                    curCell.setClusterId(counter);
                    expandCluster(neighbors, counter);
                    this.clusterNum = counter;
                }
            }
        }

    }

    private void expandCluster(ArrayList<MimoCell> neighbors, int clusterID) {
        while (!neighbors.isEmpty()) {
            MimoCell curCell = neighbors.remove(0);

            if (curCell.getClusterId() == NOT_VISITED) {
                ArrayList<MimoCell> subNeighbors = curCell.getNeighbors();
                if (subNeighbors.size() >= this.minClusterNum) {
                    neighbors.addAll(subNeighbors);
                }
                curCell.setClusterId(clusterID);
            }

            if (curCell.getClusterId() == NOISE) {
                curCell.setClusterId(clusterID);
            }
        }
    }

    public ArrayList<MimoCell> getCells() {
        return this.cells;
    }
}
