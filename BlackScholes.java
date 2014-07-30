package com.sandeep.blackscholes;

public class BlackScholes extends Bisection {

	public BlackScholes() {
		
		//super(0.0000000001,50); // Calls Bisection Method Constructor 
		
	}
	
	
	private double	K;  // Strike Price  
	private double	S;// Spot Price
	private double	T; // E*piray days  in year   = ( option e*piration date - today's date) /365
	private double	R; // Risk Free Rate ,constant value
	private double	V; // Stock volatility
	private double  dR; //Dividend rate   ( its needs to be set at the start of the month )
	private double  C;
	private double  DeltaC;
	private double  P;
	private double  DeltaP;
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlackScholes b=new BlackScholes();
        b.setK(7750.0 );
        b.setS(7748.7);
		b.setT(0.084931507);
		b.setR(0.08);
		b.setV(0.148505224);
		b.setdR(0.09);
		
		
		b.setC(0.0);
		b.setP(0.0);
		b.setDeltaC(0.0);
		b.setDeltaP(0.0);
		
				
        b.optionPrice();
        
        b.setC(150.0);
        
        double y=b.doBisectionAlgorithm(-0.9,0.9);
        System.out.println("y:"+y);
        
       System.out.println("Volatility:"+(y/Math.sqrt(b.T)));
 /*
		double A = C/S,double  B = K/S, double R = rT  double y; = V SQRT(T) 

		N(( -log(B) + R + (1/2)y2 ) / y ) - B e-R N((( -log(B) + R + (1/2)y2 ) / y ) -y) - A
	*/
		
		

		

  
     

	}

	
	public void optionPrice()
	{
		
		double  D1 ,D2;
		if (dR==0)
		{
			//Case1: if we dont have dividend rate(dR)
			// =Log (S/K) + [( R + 0.5 (V^2) )T ]
		    D1=(Math.log(S/K) + ( R + 0.5*V*V)*T)/(V*Math.sqrt(T));
		    //D2=D1-0.5*V*T;
		    D2=D1-V*Math.sqrt(T);

		    //Case1:  if we dont have dividend rate (dR1)
		    // =  S * NORMSDIST (D1) -[ K e^(-RT) * NORMSDIST (D2)]
		    C=S*Distribution.NORMSDIST(D1)-(K*Math.exp( -R*T)*Distribution.NORMSDIST(D2));

		    //Delta Call :=  e^(-dRT)*NORMSDIST (D1)
		    DeltaC=Math.exp(-dR*T)*Distribution.NORMSDIST(D1);

		    //Case1:  if we dont have dividend rate (dR1)
		    //=K e^(-RT)(1-NORMSDIST (D2)) - [S* (1-NORMSDIST (D1))]
		    P=K*Math.exp(-R*T)*(1-Distribution.NORMSDIST(D2))-(S*(1-Distribution.NORMSDIST(D1)));

		    //Delta Put :=e^(-dRT)*(NORMSDIST(D1)-1)
		    DeltaP=Math.exp(-dR*T)*(Distribution.NORMSDIST(D1)-1);

		}
		else{
			//Case2: if we have dividend rate 
			//Log (S/K) + [( R -dR + 0.5(V^2) )T]		
			D1=(Math.log(S/K)+ ( R -dR + 0.5*Math.pow(V,2))*T)/(V*Math.sqrt(T));
			D2=D1-V*Math.sqrt(T);

			//Case2: if we have dividend rate (dR1)
		    //= S *  NORMSDIST (D1) * e^(-dRT) - [ K e^(-RT)  * NORMSDIST (D2)]
			C=S*Distribution.NORMSDIST(D1)*Math.exp(-dR*T)-(K*Math.exp(-R*T)*Distribution.NORMSDIST(D2));
			//Delta Call :=  e^(-dRT)*NORMSDIST (D1)
			DeltaC=Math.exp(-dR*T)*Distribution.NORMSDIST(D1);

			//Case2: if we have dividend rate (dR1)

			//=EXP(-R*T)*K*NORMSDIST(-D2)-EXP(-dR*T)*S*NORMSDIST(-D1))

		    P=K*Math.exp(-R*T)*Distribution.NORMSDIST(-D2)-(S*Distribution.NORMSDIST(-D1)*Math.exp(-dR*T));

			//Delta Put :=e^(-dRT)*(NORMSDIST(D1)-1)
		    DeltaP=Math.exp(-dR*T)*(Distribution.NORMSDIST(D1)-1);
		}


		System.out.println("K:"+K);
		System.out.println("S:"+S);
		System.out.println("T:"+T);
		System.out.println("R:"+R);
		System.out.println("V:"+V);
		System.out.println("dR:"+dR);

		System.out.println("---------------------------------");

		System.out.println("D1:"+D1);
		System.out.println("D2:"+D2);
		System.out.println("ND1:"+Distribution.NORMSDIST(D1));
		System.out.println("ND2:"+Distribution.NORMSDIST(D2));


		System.out.println("C:"+C);
		System.out.println("DeltaC:"+DeltaC);

		System.out.println("P:"+P);
		System.out.println("DeltaP:"+DeltaP);

		
	}
	
	


	@Override
	public double doFunction(double y) {
		// TODO Auto-generated method stub
		double B = 0,Rr = 0,A = 0,D1,D2,J=0;
		
		B=K/S;
		Rr=R*T;
		A=C/S;
		J=dR*T;
				 
		D1 = ( -Math.log(B) + Rr -J+ 0.5*y*y) / y ;
		D2=D1-y;
				
		return Distribution.NORMSDIST(D1)*Math.exp(-J) - B*Math.exp(-Rr)* Distribution.NORMSDIST(D2) - A;
	
		
	}



	public double getC() {
		return C;
	}


	public void setC(double c) {
		C = c;
	}


	public double getDeltaC() {
		return DeltaC;
	}


	public void setDeltaC(double deltaC) {
		DeltaC = deltaC;
	}


	public double getP() {
		return P;
	}


	public void setP(double p) {
		P = p;
	}


	public double getDeltaP() {
		return DeltaP;
	}


	public void setDeltaP(double deltaP) {
		DeltaP = deltaP;
	}


	public double getK() {
		return K;
	}



	public void setK(double k) {
		K = k;
	}



	public double getS() {
		return S;
	}



	public void setS(double s) {
		S = s;
	}



	public double getT() {
		return T;
	}



	public void setT(double t) {
		T = t;
	}



	public double getR() {
		return R;
	}



	public void setR(double r) {
		R = r;
	}



	public double getV() {
		return V;
	}



	public void setV(double v) {
		V = v;
	}



	public double getdR() {
		return dR;
	}



	public void setdR(double dR) {
		this.dR = dR;
	}

	

	
	
}
