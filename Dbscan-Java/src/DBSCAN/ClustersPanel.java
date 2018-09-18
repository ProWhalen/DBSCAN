package DBSCAN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class ClustersPanel extends JPanel {
	
	private static Color[] colors = new Color[] {
			Color.BLUE, Color.CYAN, Color.GREEN, Color.RED,
			Color.LIGHT_GRAY, Color.YELLOW, 
			Color.MAGENTA, Color.ORANGE, Color.PINK
	};
	
	ArrayList<MimoCell> points;
	ArrayList<Rectangle2D> rects;
	HashMap<Rectangle2D, String> rectIdMap;
	int width;
	int height;
	
	public ClustersPanel(ArrayList<MimoCell> points, int boxW, int boxH) {
		this.points = points;
		this.width = boxW;
		this.height = boxH;
		this.setBackground(Color.WHITE);
		rects = new ArrayList<Rectangle2D>();
		rectIdMap = new HashMap<Rectangle2D, String>();
		this.addMouseListener(new RectClicked());
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for (MimoCell p : points) {
			Color c;
			if (p.getClusterId() == Dbscan.NOISE)
				continue;
			else {
				int colorIdx = Math.abs(p.getClusterId()) % colors.length;
				c = colors[colorIdx];
			}
			g2.setColor(c);
			
			Rectangle2D pRect = new Rectangle((int) (p.getX() * 1.5), (int) (p.getY() * 1.5), width, height);
			rects.add(pRect);
			rectIdMap.put(pRect, "ID: " + p.getId() + " Clus: " + p.getClusterId() 
					+ " X = " + p.getX() + " Y = " + p.getY());
			g2.fill(pRect);
			g2.setColor(Color.BLACK);
			g2.draw(pRect);
		}
	}
	
	class RectClicked implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			String msg = "Message >>> \n";
			int i = 0;
			for (Rectangle2D r : rects) {
				i++;
				if (r.contains(x, y)) {
					msg = msg + rectIdMap.get(r) + " " + i + " \n";
				}
			}
			
			JOptionPane.showMessageDialog(null, msg);
		}

		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
}
