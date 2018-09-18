package DBSCAN;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

;

@SuppressWarnings("serial")
public class Visualization extends JFrame {
	
	public Visualization(int height, int width) {
		super("Clustering Algorithms Visualization");
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
	}
	
	public void plotClusters(ArrayList<MimoCell> points, int dotWidth,
                             int dotHeight) {
		this.add(new ClustersPanel(points, dotWidth, dotHeight));
	}
}
