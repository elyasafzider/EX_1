




/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */



   
public class Monom implements function{

	private double _coefficient; // 
	private int _power; 

	/**
	 * this constructor creator new monom "ax^b"
	 * @param a int
	 * @param b double
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);			//a=coefficient
		this.set_power(b);					//b=power
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}


	public int get_power() {
		return _power;
	}
	
	/**
	 * this function add monom m to this monom 
	 * @param m Monom
	 */
	public void add(Monom m)
	{
		if(this._power==m._power && m._power>=0)   // chek equal between the powar
			_coefficient += m._coefficient;		   // if true do the add
	}
	/**
	 * 	this function multiply monom m whit this monom
	 * @param m Monom
	 */
	public void multiply(Monom m)
	{
		if(m._power>=0)
		{
			_coefficient *= m._coefficient;
			_power+=m._power;
		}

	}
	/**
	 * 	this function do derivative to this monom
	 * @return the derivative of this Monom
	 */
	public Monom derivative()
	{
		int _p1 = _power;
		double _co1=_coefficient;
		if(_p1==0)						//chek the powar to this case ax
			_co1= 0;
		else
		{
			_co1 *=_power;				// powar*coefficient
			_p1=_p1- 1;					//powar-1
		}
		Monom m = new Monom(_co1,_p1);
		return m;
	}

	public double get_coefficient() {
		// TODO Auto-generated method stub
		return _coefficient;
	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	
	
	
	/**
	 * 	this function Calculates monom  whit x
	 * @param x double
	 *  @return the y of monom
	 */
	@Override
	public double f(double x) {
		return Math.pow(x,_power)*_coefficient;
	}
	/**
	 * this function do subtract whit monom m and htis monom
	 * @param m Monom
	 */
	public void subtract(Monom m)
	{
		if(this._power==m._power && m._power>=0) 		//chak if the powar equl 
			_coefficient -= m._coefficient;	
	}
	/**
	 * this function equl between Monom m1 and this Monom
	 * @param m1 Monom
	 * @return true or false
	 */
	public boolean equals(Monom m1) {

		return (m1._power==_power&&m1._coefficient==_coefficient);		//chak if the power and coefficient equl 
	}
	/**
	 * this function print Monom
	 * @return string 
	 */
	@Override
	public String toString()
	{
		return _coefficient+"x^"+_power;
	}
	
	public Monom(String s){
		s = s.toLowerCase();
		String temp = "";
		int index=0;
		double a=0;
		int b=0;

		if((index = s.indexOf('x'))!=-1)
		{
			if(index+2<s.length() && index!=0)
			{
				if(s.charAt(index+1)=='^')
				{
					try
					{
						if(s.charAt(0) == '-' && index==1)
						{
							a = -1;
						}
						else
						{
							a = Double.parseDouble(s.substring(0,index));
						}
						b = Integer.parseInt(s.substring(index+2,s.length()));
					}
					catch (Exception e) {
						throw new RuntimeException("Syntax is not allow for Init!!");
					}

				}
				else
				{
					throw new RuntimeException("Syntax is not allow for Init!!");
				}
			}
			else if(index+1==s.length())
			{
				b=1;
				if(s.length()==1)
				{
					a=1;
				}
				else if(s.charAt(0)=='-')
				{
					if(index==1)
					{
						a=-1;
					}
					else
					{
						a = Double.parseDouble(s.substring(0,index));
					}

				}
				else
				{
					try
					{
						temp = s.substring(0, index);
						a = Double.parseDouble(temp);
					}
					catch (Exception e) {
						throw new RuntimeException("Syntax is not allow for Init!!");
					}
				}
			}
			else if(index==0)
			{
				a=1;
				try
				{
					b=Integer.parseInt(s.substring(index+2,s.length()));
				}
				catch (Exception e) {
					throw new RuntimeException("Syntax is not allow for Init!!");
				}

			}
			else
			{
				throw new RuntimeException("Syntax is not allow for Init!!");
			}

		}
		else
		{
			try
			{
				a = Double.parseDouble(s);
			}
			catch (Exception e) {
				throw new RuntimeException("Syntax is not allow for Init!!");
			}
		}

		this.set_coefficient(a);
		this.set_power(b);

	}

}
