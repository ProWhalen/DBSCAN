package DBSCAN;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<MimoCell> cells = getPoints("/Users/erikwhalen/Desktop/DBSCAN/DBScanCluster-master/chameleon.csv");
        Dbscan db = new Dbscan(6, 3, cells);
        db.Cluster();
        ArrayList<MimoCell> pnts = db.getCells();
        Visualization visual = new Visualization(750, 1250);
        visual.plotClusters(pnts, 5, 5);
        int[] clus = new int[500];
        int noise = 0;
        for (int i = 0; i < pnts.size(); i++) {
            int j = pnts.get(i).getClusterId();
            if (j > 0)
                ++clus[j];
            else
                ++noise;
        }
        for (int i = 0; i < clus.length; i++) {
            if (clus[i] != 0) {
                System.out.println("Cluster Id  [" + i + "] and No of points in cluster [" + clus[i] + "]");
            }
        }
        System.out.println("Cluster Id  [Noise ] and No of points in cluster [" + noise + "]");
        System.out.println();

    }

    private static ArrayList<MimoCell> getPoints(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        int count = 0;
        String line;
        ArrayList<MimoCell> allPoint = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            try {
                String[] array = line.split(",");
                MimoCell tdp = new MimoCell(Double.parseDouble(array[0]), Double.parseDouble(array[1]), ++count);
                allPoint.add(tdp);
            } catch (NumberFormatException e) {
                System.out.println("corrupt file ");
                br.close();
                fr.close();
                System.exit(0);
            }
        }

        br.close();
        fr.close();
        return allPoint;
    }

}
