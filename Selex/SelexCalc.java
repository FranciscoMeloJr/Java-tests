 import java.util.*;
 
 
 class SelexCalc{
 	static double alpha=.05;
 	static double beta=.8;
 	static final int MAX=500;
 	//static int NB=10;
	public static void main(String[] arg){
		int[] N=new int[3];
		int NA=10, NB=100;
		//N=duplic(NA,NB);
		//System.out.println("Vendo fora: NA="+N[0]+" NB="+N[1]);
		//diff(2,N[0],N[1]);
		diff(70,NA,NB);
		
		 
	
	}
	
	
	static int[] duplic(int NA, int NB){
		int N[]=new int[2];
		NB=(int)(alpha*NA+NB)*2;
		NA=(int)((1-alpha)*NA)*2;
		System.out.println("NA="+NA+" NB="+NB);
		N[0]=NA;   N[1]=NB;
		return N;
	}
	
	static double[] diff(int t, int NA, int NB){
		Arquivo quant=new Arquivo("QuantMolCalc.txt");
		Arquivo Diff=new Arquivo("diff.txt");
		Arquivo afim=new Arquivo("afinidade.txt");
		afim.Escritura();
		Diff.Escritura();
		quant.Escritura();
		double[] diff=new double[t+1];
		int[] n=new int[3];;
		int N=0;
		Arrays.fill(diff,0);
		Diff.Escreve(diff[0]+"\n");
		quant.Escreve((NA+NB)+"\n");
		afim.Escreve(NA/(NA+NB+0.)+"\n");
		for(int i=1;i<=t;i++){
			n=duplic(NA,NB);
			NA=n[0];
			NB=n[1];			
			N=NA+NB;
			System.out.println("Comparing: MAX="+MAX+" N="+N);
			if(N>MAX)
				diff[i]=(N-MAX+0.)/(N);
			else
				diff[i]=0;
			Diff.Escreve(diff[i]+"\n");
								
			NB=(int)((1-diff[i])*NB);
			NA=(int)((1-diff[i])*NA);
			
			afim.Escreve(NA/(NA+NB+0.)+"\n");
			quant.Escreve((NA+NB)+"\n");
			System.out.println("diff NA="+NA+" NB="+NB);
			NB=(int)(beta*NB);
		}
		System.out.println("Seeing: diff="+diff[9]);
		return diff;
	}
	
	static double[] sum(int t){
		double[] soma=new double[t+1];
		Arrays.fill(soma,0);
		soma[0]=0.;
		double som=0.;
		for(int i=1;i<=t;i++){
			soma[i]=soma[i-1]+Math.pow((1-alpha),t-i)*Math.pow(beta,t+i);
			som+=Math.pow((1-alpha),t-i)*Math.pow(beta,t+i);
		}
		return soma; 
	} 
	
	static double[] prod(int t, double[] diff){
		double[] P=new double[t+1];
		//double[] diff=new double[t+1];
		//this.diff=diff;
		P[0]=1.;
		for(int i=1;i<=t;i++)
			P[i]=P[i-1]*(1-diff[i]);
		return P;
				
	}

}









<span style="visibility: hidden"><a href="http://www.xteenporn.net/" title="deutsch porno">deutsch porno</a><span style="visibility: hidden">deutsch porno</span></a>
<div style="display: none;">
<a href="http://t0fan.blogspot.com" title="&#246;zg&#252;n blog">&#246;zg&#252;n blog</a>
</div>

