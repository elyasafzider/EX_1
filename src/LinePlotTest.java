import java.awt.Color;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame  {
	
	   public LinePlotTest() {
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(800, 600);
	        Polynom poly = new Polynom("1x^3");
	        // Insert rest of the code here
	        DataTable data = new DataTable(Double.class, Double.class);
	        for (double x = -5.0; x <= 5.0; x+=0.25) {
	            double y = poly.f(x);
	            data.add(x, y);
	        }
	        LineRenderer lines = new DefaultLineRenderer2D();
	        XYPlot plot = new XYPlot(data);
	        plot.setLineRenderers(data, lines);
	        Color color = new Color(0.0f, 0.3f, 1.0f);
	        plot.getPointRenderers(data).get(0).setColor(color);
	        plot.getLineRenderers(data).get(0).setColor(color);
	        getContentPane().add(new InteractivePanel(plot));
	        
	    }
	   
	   
	   
	   
	    public static void main(String[] args) {
	        LinePlotTest frame = new LinePlotTest();
	        frame.setVisible(true);

	    }
	}
