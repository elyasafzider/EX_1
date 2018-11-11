

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boazsd
 *
 */
public class Polynom implements Polynom_able{
	ArrayList <Monom> mList = new ArrayList<>();



	/**
	 * this constructor creator new polynom
	 */
	public Polynom()
	{
		Monom m1 = new Monom(0,0);			//creat new monom "0x^0"
		add(m1);							//add the monom to this polynom
	}

	/**
	 * this constructor creator new polynom of string
	 * @param str string
	 */
	public Polynom(String str)
	{
		try {												
			double co=0;
			int power =0;
			String strArr[] = str.split("\\+");				//separator between monom whit "+"
			for (int i = 0; i < strArr.length; i++) {
				if (!strArr[i].contains("x"))				//chek case "a"
				{
					power=0;
					co=Double.parseDouble(strArr[i]);
					Monom m1 = new Monom(co,power);
					add(m1);
					continue;
				}
				String strArr2[]=strArr[i].split("x");		//case "ax" -separator between x to a

				if (strArr2[0].equals(""))
					co = 1;
				else if (strArr2[0].equals("-"))			//chak if a is negative
					co=-1;
				else
					if(strArr2[0].contains("*"))
							{
						strArr2[0]=strArr2[0].split("\\*")[0];
							}
							
					co=Double.parseDouble(strArr2[0]);		//case "x^b"
				if (strArr[i].contains("^"))
				{
					String strArr3[]=strArr2[1].split(Pattern.quote("^"));
					power=Integer.parseInt(strArr3[1]);
				}
				else {
					power=1;
				}


				Monom m1 = new Monom(co,power);
				add(m1);
			}
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			System.out.println("failed at constructor with string");		//if not polynom
		}
	}

	/**
	 * 	this function Calculates polynom  whit x
	 * @param x double
	 *  @return the y of polynom
	 */
	@Override	 
	public double f(double x) {
		double sum=0;
		for(int i=0;i<mList.size();i++)
		{
			sum+= mList.get(i).f(x);
		}

		return sum;
	}

	/**
	 * this function add Polynom_able p1 to this polynom 
	 * @param p1 Polynom_able
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator <Monom> itr = p1.iteretor();
		while (itr.hasNext())
		{
			add(itr.next());					//user add function of Monom
		}

		zeroRemove();
		Monom_Comperator c= new Monom_Comperator();
		mList.sort(c);
	}

	@Override

	/**
	 * this function add monom m1 to this polynom 
	 * @param m1 monom
	 */
	public void add(Monom m1) {

		boolean ifPow =false;
		for (int i = 0; i < mList.size(); i++) {
			mList.get(i).add(m1);
			if(mList.get(i).get_power()==m1.get_power())		//chak if have equl powar at polynom
				ifPow =true;
		}
		if(!ifPow)
			mList.add(m1);

		Monom_Comperator c= new Monom_Comperator();
		mList.sort(c);
	}

	/**
	 * this function do subtract whit monom m and htis polynom
	 * @param m Monom
	 */
	public void substract(Monom m1) {

		boolean ifPow =false;
		for (int i = 0; i < mList.size(); i++) {
			mList.get(i).subtract(m1);
			if(mList.get(i).get_power()==m1.get_power())		//chak equl between powar
				ifPow =true;
		}
		if(!ifPow)												//case no equl
		{
			Monom zero= new Monom(0, m1.get_power());			//creat monom zero
			zero.subtract(m1);
			mList.add(zero);
		}
		zeroRemove();
		Monom_Comperator c= new Monom_Comperator();
		mList.sort(c);
	}


	@Override
	/**
	 * this function do subtract whit Polynom_able p1 and htis polynom
	 * @param Polynom_able p1
	 */
	public void substract(Polynom_able p1) {
		Iterator <Monom> itr = p1.iteretor();
		while (itr.hasNext())
		{
			Monom m = itr.next();
			substract(m);
		}	
		zeroRemove();
		Monom_Comperator c= new Monom_Comperator();
		mList.sort(c);
	}


	@Override
	/**
	 * this function Multiply whit Polynom_able p1 and htis polynom
	 * @param p1 multiply this Polynom with p1
	 */
	public void multiply(Polynom_able p1) {

		Iterator<Monom> itermon =p1.iteretor();

		Polynom ans=new Polynom();
		while(itermon.hasNext())
		{
			Polynom newpoly = new Polynom(toString());
			Monom mon=itermon.next();
			newpoly.multiply(mon);
			ans.add(newpoly);

		}
		zeroRemove();
		mList=ans.mList;
		Monom_Comperator comper=new Monom_Comperator();
		mList.sort(comper);

	}
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true iff this pulynom represents the same function ans p1
	 */
	//@Override
	public boolean equals(Polynom_able p1) {
		boolean flag;
		Iterator <Monom> itr = p1.iteretor();
		while (itr.hasNext())
		{
			flag=false;
			Monom m  = itr.next();
			Iterator <Monom> itr2 = iteretor();
			while (itr2.hasNext())
			{
				Monom m2 = itr2.next();
				if (m.get_coefficient()==m2.get_coefficient() && m.get_power()==m2.get_power())
					flag = true;
			}
			if (flag == false) return false;
		}

		Monom_Comperator c= new Monom_Comperator();
		mList.sort(c);
		return true;
	}
	/**
	 * Test if this is the Zero Polynom
	 * @return true if this is the zero the polynom
	 */
	@Override
	public boolean isZero() {

		for (Monom monom : mList) {
			if (monom.get_coefficient()!=0) return false;
		}
		return true;
	}
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return
	 */
	@Override
	public double root(double x0, double x1, double eps) {

		if (f(x0)*f(x1)>0)
			try {
				throw new Exception();
			} catch (Exception e) {

				e.printStackTrace();
			}
		double newX = (x1+x0)/2;
		double newY = f(newX);

		if (newY<eps) return newX;
		if (f(x0)>0)
		{
			if (newY>0)
			{
				return root(newX,x1,eps);
			}
			else
			{
				return root(x0,newX,eps);
			}
		}
		else
		{
			if (newY>0)
			{
				return root(x0,newX,eps);
			}
			else
			{
				return root(newX,x1,eps);
			}
		}


	}
	/**
	 * this fuction create a deep copy of this Polynum
	 * @return  new polynom
	 */
	@Override
	public Polynom_able copy() {

		Polynom newPoly = new Polynom();
		Iterator <Monom> itr = iteretor();
		while (itr.hasNext())
		{
			Monom m = itr.next();
			Monom newMonom = new Monom(m);
			newPoly.add(newMonom);			
		}
		return newPoly;
	}
	/**
	 * 	this function do derivative to this polynom
	 * @return the derivative of this polynom
	 */
	@Override
	public Polynom_able derivative() {
		Polynom p1 = new Polynom();
		Iterator <Monom> itr = iteretor();
		while (itr.hasNext())
		{
			Monom m1= itr.next();
			Monom m2 = new Monom(m1.derivative());
			p1.add(m2);
		}
		p1.zeroRemove();
		return p1;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) { 
		double area =0;
		int numOfRec =(int)((x1-x0)/eps);					//numofrec equl (x1-x0)/eps
		for (int i = 1; i < numOfRec; i++) 
		{
			double x2 = x0+i*eps;							//Computers width at a point
			double y2 = f(x2);								//Computers  height at a point		
			area= area+ y2*eps;
		}

		return area;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator <Monom> iter = mList.iterator(); 
		return iter;
	}
	/**
	 * this function print polynom
	 * @return string 
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < mList.size(); i++) {
			Monom m = mList.get(i);
			str+=m.toString();
			if (i<mList.size()-1)
				str+="+";
		}
		return str;
	}
	/**
	 * 	this function multiply monom m whit polynom
	 * @param m Monom
	 */
	@Override
	public void multiply(Monom m1) {
		Iterator <Monom> iter = iteretor();
		while (iter.hasNext())
			iter.next().multiply(m1);
		zeroRemove();
	}
	
	public boolean zeroRemove()
	{
		for ( int i=0;i<mList.size();i++)
		{
			if(mList.get(i).get_coefficient()==0)
			{
				mList.remove(i);
				return true;
			}
			
		}
		return false;
		
	}

}
