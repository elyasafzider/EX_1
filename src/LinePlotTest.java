/**
 * This Class is for Drawing the Polynom.
 * This Class Uses Gral to print.
 * https://github.com/eseifert/gral
 * 
 * in order to make this work, you need to download and install the Jar file from github.
 * this is the link:
 * https://repo1.maven.org/maven2/de/erichseifert/gral/gral-core/0.11/gral-core-0.11.jar
 * this JAR is also attached to this github project, but if it has a problem download from the website.
 * 
 * it marks the Kizun Points in red
 */

import java.awt.Color;
import java.lang.invoke.LambdaConversionException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.graphics.Orientation;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.legends.ValueLegend;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.LabelPointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame  {

	public LinePlotTest(Polynom poly,double x0, double x1, double eps) {
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setSize(600, 600);
		Polynom_able poly_deriv = new Polynom();
		poly_deriv = poly.derivative();
		// Insert rest of the code here
		DataTable data = new DataTable(Double.class, Double.class); 
		DataTable data2 = new DataTable(Double.class, Double.class);
		DataTable data3 = new DataTable(Double.class, Double.class);


		for (double x = x0; x <= x1; x+=eps) {
			double y = poly.f(x);
			data.add(x, y);
		}
		
		ArrayList<Long> arr = new ArrayList<>();
		for (double x = x0; x <= x1; x+=eps) {
			if(poly_deriv.f(x-eps)>0 && poly_deriv.f(x+eps)<0)
			{
				if(!arr.contains(Math.round(x)))
				{
					arr.add(Math.round(x));
					System.out.println("x is:"+x+" and y' is:"+poly_deriv.f(x));
					data2.add(x,poly.f(x));
				}
			}
			if(poly_deriv.f(x-eps)<0 && poly_deriv.f(x+eps)>0)
			{
				if(!arr.contains(Math.round(x)))
				{
					arr.add(Math.round(x));
					System.out.println("x is:"+x+" and y' is:"+poly_deriv.f(x));
					data2.add(x,poly.f(x));
				}

			}
		}


		LineRenderer lines = new DefaultLineRenderer2D();
		LineRenderer lines2 = new DefaultLineRenderer2D();
		XYPlot plot = new XYPlot(data);

		System.out.println();
		plot.add(data2);
		plot.add(data3);
		plot.setLineRenderers(data, lines);
		plot.setLineRenderers(data3, lines2);
		Color color = new Color(0.1f, 0.75f, 0.5f);
		Color color2 = new Color(1.0f, 0.0f, 0.0f);
		plot.getPointRenderers(data).get(0).setColor(color);
		plot.getLineRenderers(data).get(0).setColor(color);
		plot.getPointRenderers(data2).get(0).setColor(color2);
		plot.getPointRenderers(data3).get(0).setColor(color2);
		plot.getLineRenderers(data3).get(0).setColor(color2);
		getContentPane().add(new InteractivePanel(plot));
	}


	public static void main(String[] args) {
		Polynom poly = new Polynom("0.2x^4-1.5x^3+3.0x^2-1x-5");
		double x0=-2,x1=6,eps=0.01;
		LinePlotTest frame = new LinePlotTest(poly,x0,x1,eps);
		frame.setVisible(true);
		System.out.println(poly.upArea(x0, x1, eps));
		

	}
}
