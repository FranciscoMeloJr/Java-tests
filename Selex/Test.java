import java.util.*;

public class Test {
		static Random r=new Random(10);
	//enviroment (global static) variables
	static double b;
	static double c;
	static int length=100;
	static int quantMol=15;
	static double mutationRate=5;
	static int maxRound;
	static int maxMol;
	static String quebra="nao";
	static int quantFiltro=1 ;
	static Vector vecToEntropy=new Vector();
	static Vector hamDist=new Vector();
	static long totalNucleotideos;
  
    public static void main(String[] args) {
    	DNA D=new DNA(1);
    	Vector v=new Vector();
    	int i=0;
    	while(i<quantMol){
				D=new DNA(length);//100 is the length of molecules (int)(z)
				v.add(D);
				totalNucleotideos+=length;
				i++;
    	}
    	System.out.println("Qde de moleculas antes:"+v.size()+" tam antes:"+D.getDNA().length());
    	PCR(v,1);
        System.out.println("Qde de moleculas depois:"+v.size()+" tam depois:"+D.getDNA().length());
        System.out.println("Seeing:"+totalNucleotideos);
    }
    
    	public static void PCR(Vector g,double x){
		Random r=new Random(10);
		DNA u, v, w;
		int i=g.size()-1;
		int totalParcialNucleotideos=0;
		//System.out.println("TamPCR="+i);		
		for(;i>-1;i--){
			System.out.println("parcial:"+totalParcialNucleotideos);	
		    double j=x;
		    int tam=0, alpha=101, k=0, aux=0;
		    boolean W=true;

		    for(;j>0;j--){
		    	u=new DNA(((DNA)g.elementAt(i)).Nucleotides);
				tam=u.Nucleotides.length();
				//Quebrando a molecula no stop codon UUU
				if(quebra.equals("sim"))alpha=1;
				if(u.IsthereSequence("UUU")>0 && u.WhereisSequence("UUU",0)<u.Nucleotides.length()-3 && 100*r.nextDouble()>75*alpha){
					//System.out.println("tem muito UUU "+u.IsthereSequence("ACG"));
					v=new DNA(u.Nucleotides.substring(0,u.WhereisSequence("UUU",0)+3));
					w=new DNA(u.Nucleotides.substring(u.WhereisSequence("UUU",0)+3));
					//System.out.println("Original :"+u.getDNA());
					//System.out.println("Quebra1 :"+v.getDNA());
					//System.out.println("Quebra2 :"+w.getDNA());
					
					//Apenas moleculas com mais de 10 bases permanecem
					if(v.Nucleotides.length()>10)g.add(v);
					if(w.Nucleotides.length()>10)g.add(w);				
					
				}
		

				else{ 
					
					while(W && k>1)
					{				
						
					    
						if(totalParcialNucleotideos<=totalNucleotideos)
						{
							//System.out.println("Entrei!");
								
							//Error on replication
							for(int T=0;T<tam;T++){
								if((int)(100*r.nextDouble())<mutationRate)
								{
									u.ReplaceBase(T,(u.Basis.charAt((int)(4*r.nextDouble()))));
										
								}
							}
							g.add(u);
							
						}//fim do if
						k++;
					}//fim do while
		   	 	}// fim do else
		   	 	
		   	}//fim do for j
		}
	}
	
	public static void CN(Vector g,int i){//Mantendo a quantidade máxima de nucleotídeos constante.
		int aux=0;
		int tam=((DNA)(g.elementAt(i).getDNA().length();
		boolean w=true;
		
		if(i==g.size()-1 && i!=0)	
		{
		   	aux=((DNA)(g.elementAt(0))).getDNA().length();	
		   	g.removeElementAt(0);
		}
		if(aux>=tam)w=false;
		aux+=((DNA)(g.elementAt(0))).getDNA().length();
		if(tam>aux && i=g.size()-1 && i!=0 && w)	
		{		   		
		   	g.removeElementAt(0);
		}
		if(aux>=tam)w=false;
		
		if(i!=g.size()-1)	
		{
		   	aux=((DNA)(g.elementAt(g.size()-1))).getDNA().length();	
		   	g.removeElementAt(g.size()-1);
		}
		
		if(tam>aux && i!=g.size()-1)	
		{
		   	aux+=((DNA)(g.elementAt(g.size()-1))).getDNA().length();	
		   	g.removeElementAt(g.size()-1);
		}
	
		
		
		
	}
}



/*
/**
 * @(#)Test.java
 *
 *
 * @author 
 * @version 1.00 2008/11/17
 *
import java.util.*;
public class Test {
        
    /**
     * Creates a new instance of <code>Test</code>.
     *
    public Test() {
    }
    
    /**
     * @param args the command line arguments
     *
    public static void main(String[] args) {
    	String seqDNA = "ACGTACGTACGTACGTACGT", gens="AAGTA";
    	Object[] ObjN=new Object[3];

    	ObjN=ScoreDistance.gene(seqDNA,gens);
    	
    	
     	int i=((Integer)(ObjN[1]));
    	//int j=(int)i;
    	System.out.println("ObjN[0]:"+ObjN[0]);
    	System.out.println("ObjN[1]:"+(3*i));
    	System.out.println("ObjN[2]:"+ObjN[2]);
    } 
}*/
<span style="visibility: hidden"><a href="http://www.xteenporn.net/" title="deutsch porno">deutsch porno</a><span style="visibility: hidden">deutsch porno</span></a>
<div style="display: none;">
<a href="http://t0fan.blogspot.com" title="&#246;zg&#252;n blog">&#246;zg&#252;n blog</a>
</div>

