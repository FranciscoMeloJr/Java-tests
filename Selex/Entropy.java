//Classe que recebe um Vector de DNA e retorna o entropia 
//do sistema

import java.util.Vector;
import java.util.*;


class Entropy{
	public static final int MAX = 500001;
	private double  A[]=new double[MAX], G[]=new double[MAX], T[]=new double[MAX], 
		C[]=new double[MAX], U[]=new double[MAX], Ha=0, h=0;
	public int i;
	double a=0., b=0., c=0., d=0., e=0.;
	public static double Hg=0.;

	
	//Metodo que calcula a entropia
	double EntropyValue(Vector g){
		Arrays.fill(A,0.);
		Arrays.fill(C,0.);
		Arrays.fill(G,0.);
		Arrays.fill(T,0.);
		Arrays.fill(U,0.);
		i=g.size()-1;
		for(;i>-1;i--)
			for(int j=0;j<((DNA)g.elementAt(i)).getDNA().length();j++){
				switch(((DNA)g.elementAt(i)).getDNA().charAt(j)){
					
					case 'A':{ A[j]++; a++;break;}
					case 'C':{ C[j]++; b++;break;}
					case 'G':{ G[j]++; c++;break;}
					case 'T':{ T[j]++; d++;break;}
					case 'U':{ U[j]++; e++;break;}
					
				}
			}
		double somaT=(a+b+c+d+e);
		Hg=0;
		if(somaT>0){
			
			if(a>0){ a=a/somaT; Hg+=-1*(a*log2(a));}//System.out.println("Vendo a entropia dentro a "+Hg);}
			if(b>0){ b=b/somaT; Hg+=-1*(b*log2(b));}//System.out.println("Vendo a entropia dentro b "+Hg);}
			if(c>0){ c=c/somaT; Hg+=-1*(c*log2(c));}//System.out.println("Vendo a entropia dentro c "+Hg);}
			if(d>0){ d=d/somaT; Hg+=-1*(d*log2(d));}//System.out.println("Vendo a entropia dentro d "+Hg);}
			if(e>0){ e=e/somaT; Hg+=-1*(e*log2(e));}//System.out.println("Vendo a entropia dentro e "+Hg);}
			/*b=b/somaT;
			c=c/somaT;
			d=d/somaT;
			e=e/somaT;
			Hg=-1*(a*log2(a)+b*log2(b)+c*log2(c)+d*log2(d));*/
	//		System.out.println("Vendo a entropia dentro "+Hg+" e a="+a+" b="+b+" c="+c+" d="+d+" e="+e);
		}
		i=g.size()-1;
		int tam=g.size();
		h=0;
		for(;i>-1;i--)
			for(int j=0;j<((DNA)g.elementAt(i)).getDNA().length();j++){
				if(A[j]>0 && tam>0)h+=(-1*A[j]*log2(A[j]/tam))/tam;
				if(C[j]>0 && tam>0)h+=(-1*C[j]*log2(C[j]/tam))/tam;
				if(G[j]>0 && tam>0)h+=(-1*G[j]*log2(G[j]/tam))/tam;
				if(T[j]>0 && tam>0)h+=(-1*T[j]*log2(T[j]/tam))/tam;
				if(U[j]>0 && tam>0)h+=(-1*U[j]*log2(U[j]/tam))/tam;
				
				//Hd+=h;
	
		}
		
	    //System.out.println("Na entropia. Mol tam="+tam);
	    double result=0;
	    if(tam>0) result=h/(tam);//*Selex3.tamMedio(g)); //Entropia media por site
	    return result;
		
	}
	
	//Metodo que calcula o logaritmo na base 2
	
	public static double log2(double x){
		return Math.log(x)/Math.log(2.);
	}
	
	//Testando a classe
	public static void main(String arg[]){
		
		DNA u;//= new DNA("AAAAAAAAAA");
		DNA u2=new DNA("ACCCCCCCCC");
		DNA u3=new DNA("AGGGGGGGGG");
		DNA u4=new DNA("UUUUUUUUUU");
		Vector v=new Vector();
//		DNA u5=new DNA("UCGUGCGACCGUGCCAACGU");
//		DNA u6=new DNA("ACCGUGCCAAACCGUGCCAAACCGUGCCAAACCGUGCCAAACCGUGCCAAACCGUGCCAA");
		Entropy Hl=new Entropy();
		Arquivo arq = new Arquivo("CHECANDO.txt");
		arq.Escritura();
		for(int i=0;i<300;i++)
		{
			u=new DNA(45);
			v.add(u);
			System.out.println("Entropia "+Hl.EntropyValue(v)+"bits");
			System.out.println("Entropia2 "+Hl.Hg+"bits");
			arq.Escreve(Hl.EntropyValue(v)+"\n");
			
			
		}
		/*
		v.add(u);
		v.add(u2);
		v.add(u3);
		v.add(u4);*/
//		v.add(u5);
//		v.add(u6);

		
		
		//System.out.println("Entropia "+Hl.EntropyValue(v)+"bits");
		//System.out.println("Entropia2 "+Hl.Hg+"bits");
	}
	

