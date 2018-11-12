import java.awt.Color;
import java.lang.invoke.LambdaConversionException;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.LabelPointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame  {
 
	   public LinePlotTest() {
	        setDefaultCloseOperation(EXIT_ON_CLOSE); 
	        setSize(600, 600);
	        Polynom poly = new Polynom("0.2x^4-1.5x^3+3.0x^2-1x-5");
	        Polynom_able poly_deriv = new Polynom();
	        poly_deriv = poly.derivative();
	        double x0 = -2;
	        double x1 = 6;
	        // Insert rest of the code here
	        DataTable data = new DataTable(Double.class, Double.class);
	        DataTable data2 = new DataTable(Double.class, Double.class);
	        DataTable data3 = new DataTable(Double.class, Double.class);
	        double x_kizon = poly_deriv.root(3, 5, 0.01);
	        System.out.println(x_kizon);
	        double y_kizon = poly.f(x_kizon);
	        data2.add(x_kizon,y_kizon);
	        for (double x = x0; x <= x1; x+=0.01) {
	            double y = poly.f(x);
	            data.add(x, y);
	        }
	        LineRenderer lines = new DefaultLineRenderer2D();
	        LineRenderer lines2 = new DefaultLineRenderer2D();
	        XYPlot plot = new XYPlot(data);
	        
	        //draw arrow above kizon
	        data3.add(x_kizon,y_kizon+1);
	        data3.add(x_kizon-0.8,y_kizon+5);
	        data3.add(x_kizon-0.4,y_kizon+5);
	        data3.add(x_kizon-0.4,y_kizon+8);
	        data3.add(x_kizon+0.4,y_kizon+8);
	        data3.add(x_kizon+0.4,y_kizon+5);
	        data3.add(x_kizon+0.8,y_kizon+5);
	        data3.add(x_kizon,y_kizon+1);
	        
	        System.out.println();
	        plot.add(data2);
	        plot.add(data3);
	        plot.setLineRenderers(data, lines);
	        plot.setLineRenderers(data3, lines2);
	        Color color = new Color(0.75f, 0.75f, 0.2f);
	        Color color2 = new Color(1.0f, 0.0f, 0.0f);
	        plot.getPointRenderers(data).get(0).setColor(color);
	        plot.getLineRenderers(data).get(0).setColor(color);
	        plot.getPointRenderers(data2).get(0).setColor(color2);
	        plot.getPointRenderers(data3).get(0).setColor(color2);
	        plot.getLineRenderers(data3).get(0).setColor(color2);
	        getContentPane().add(new InteractivePanel(plot));
	    }
	   
	   
	   
	   
	    public static void main(String[] args) {
	        LinePlotTest frame = new LinePlotTest();
	        frame.setVisible(true);

	    }
	}
