package com.sandeep.blackscholes;

public class BlackScholes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		double	K=7200.0 ;  // Strike Price  
		double	S=7500.0 ;// Spot Price
		double	T=0.123287671; // E*piray days  in year   = ( option e*piration date - today's date) /365
		double	R=0.08 ; // Risk Free Rate ,constant value
		double	V=0.172479149045707; // Stock volatility
		double  dR=0.0; //Dividend rate   ( its needs to be set at the start of the month )
		

		double  D1 ,D2 ,C ,DeltaC,P,DeltaP;
		
			
			
		if (dR==0)
		{
			//Case1: if we dont have dividend rate(dR)
			// =Log (S/K) + [( R + 0.5 (V^2) )T ]
		    D1=(Math.log(S/K) + ( R + 0.5*Math.pow(V,2))*T)/(V*Math.sqrt(T));
		    //D2=D1-0.5*V*T;
		    D2=D1-V*Math.sqrt(T);
		   
		    //Case1:  if we dont have dividend rate (dR1)
		    // =  S * NORMSDIST (D1) -[ K e^(-RT) * NORMSDIST (D1)]
		    C=S*Distribution.NORMSDIST(D1)-(K*Math.exp( -R*T)*Distribution.NORMSDIST(D1));
		    
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

}
