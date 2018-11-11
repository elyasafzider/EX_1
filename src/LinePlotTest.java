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
	        Polynom poly = new Polynom("-2x^2+2");
	        // Insert rest of the code here
	        DataTable data = new DataTable(Double.class, Double.class);
	        DataTable data2 = new DataTable(Double.class, Double.class);
	        Polynom poly_nigzeret = new Polynom("-4x^1");
	        double kizon = poly_nigzeret.root(-0.5, 0.5, 0.25);
	        data2.add(kizon,poly.f(kizon));
	        for (double x = -5.0; x <= 5.0; x+=0.25) {
	            double y = poly.f(x);
	            data.add(x, y);
	        }
	        LineRenderer lines = new DefaultLineRenderer2D();
	        XYPlot plot = new XYPlot(data);
	        plot.add(data2);
	        plot.setLineRenderers(data, lines);
	        plot.setLineRenderers(data2, lines);
	        Color color = new Color(0.75f, 0.75f, 0.2f);
	        Color color2 = new Color(1.0f, 0.0f, 0.0f);
	        plot.getPointRenderers(data).get(0).setColor(color);
	        plot.getLineRenderers(data).get(0).setColor(color);
	        plot.getPointRenderers(data2).get(0).setColor(color2);
	        getContentPane().add(new InteractivePanel(plot));
	        
	    }
	   
	   
	   
	   
	    public static void main(String[] args) {
	        LinePlotTest frame = new LinePlotTest();
	        frame.setVisible(true);

	    }
	}
