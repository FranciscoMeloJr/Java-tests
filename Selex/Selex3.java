import java.util.*;
import javax.swing.JOptionPane;

public class Selex3{
	static Random r=new Random(10);
	//enviroment (global static) variables
	static double b;
	static double c;
	static int length;
	static int quantMol;
	static double mutationRate;
	static int maxRound;
	static String quebra="nao";
	static int quantFiltro=1 ;
//	static double z5;
//	static double z6;5


	public static void main(String []arg){		
		DNA D;
		//TextoPL savePl = new TextoPL();
		Entropy Hl=new Entropy();
		//Opening or creating files
		Arquivo arq=new Arquivo("afinidade.txt");
		//newQdeNaoAfimQueFica_sementeDNA_%deErro_molinicial.dat
		arq.Escritura();
		Arquivo G=new Arquivo("Qde_per_round.txt");
		G.Escritura();
		Arquivo arqTosort=new Arquivo("hist.txt");	
		arqTosort.Escritura();
		Arquivo tamMedio=new Arquivo("tamMedio.txt");
		tamMedio.Escritura();
		Arquivo entropia=new Arquivo ("entropia.txt");
		entropia.Escritura();
		Arquivo shannon=new Arquivo ("shannon.txt");
		shannon.Escritura();
		Arquivo last=new Arquivo("lastConfig.txt");
		last.Escritura();
		Arquivo text = new Arquivo("savePL.txt");
       	text.Escritura();
		
		Scanner sc=new Scanner(System.in);
		//Creating variables
		double A=0., B, a, maxEntropy=0.;
		int k=0, n=0, P1=0, P2=0, P3=0, I=0, P=0;
		int[] H=new int[5];
		double[] moment=new double[5];
		String j=new String("ACGT"), texto;
		Vector v=new Vector();
		Vector v1=new Vector(), v2=new Vector(), v3=new Vector();
		Object ALL[]={v};
//para acessar um elemento: 
//((DNA)(((Vector)ALL[# do vetor]).elementAt(# do elemento dentro do vetor))).Nucleotides
		
		//External values 
		System.out.print("Digite: 1) A eficiencia do filtro 2) Tamanho de cada molecula");
		System.out.println(" 3)Qde inicial de moleculas 4)Taxa de mutacao (%) ");
		System.out.print(" 5)O numero maximo de iteracoes (round ou ciclos)");
		System.out.print( "6)Com quebra? (sim ou nao)");
		System.out.println(" 7)Quantos filtros? (maximo 5)");
		System.out.println(" 8)Qual sera o titulo dos graficos?");
		c=sc.nextDouble();//% of mol. that stayed on the target
		length=sc.nextInt();//length of each molecules
		quantMol=sc.nextInt();//amount of initial molecules
		mutationRate=sc.nextDouble();// mutation rate in % 	
		maxRound=sc.nextInt();//numero maximo de iteracoes 
		quebra=sc.next();
		quantFiltro=sc.nextInt();//quantidade de filtros
		texto=sc.next()+" ";//Titulo dos graficos
		texto=texto+sc.next()+" ";
		texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		texto=texto+sc.next();
		last.Escreve("Digite: 1) A eficiencia do filtro 2) Tamanho de cada molecula 3)");
		last.Escreve(" Qde inicial de moleculas 4) Taxa de mutacao (%) 5) O numero maximo de iteracoes (round ou ciclos)");
		last.Escreve(" 6)Com quebra? 7)A qde de filtros 8)Qual sera o titulo dos graficos?");
		last.Escreve("\n");
		last.Escreve(c+"\t"+length+"\t"+quantMol+"\t"+mutationRate+"\t"+maxRound+"\t"+quebra+"\t"+quantFiltro+"\t"+texto);
		text.Escreve(TextoPL.incluiTitle(texto));
	//	z5=0;//Double.valueOf(arg[5]).doubleValue();//% q nao entra no filtro A
	//	z6=0;//Double.valueOf(arg[6]).doubleValue();//% q nao entra no filtro B

      	b=100.;
		a=1;
		//Creating DNA molecules
		for(int i=0;i<quantMol;i++){//z2 is the amount of inicial molecules
			if(i<quantMol	){
				D=new DNA(length);//100 is the length of molecules (int)(z)
				v.add(D);
			}
			else {
				D=new DNA("AAAAAAAAAAAAAAAAAAAAAAGGGGGAAAAAAAAAACGUUAAAAAAAAAAAAAAAAA");
				v.add(D);
			}
		}
		//Testing conditions		
		
		/*D=new DNA("ACAGUACAUTGACGTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		v.add(D);
		D=new DNA("AAUCGACTACCGAGGTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		v.add(D);
		D=new DNA("AUCGAAGUACTTTGGTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		v.add(D);
		D=new DNA("TTTAGTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		v.add(D);*/
		arq.Escreve(""+k+"\t"+A);
		arq.Escreve("\n");
		n=(((Vector)ALL[0]).size());
		G.Escreve(""+n+"\t"+P1+"\n"/*+P2+"\t"+P3*/);
		System.out.println("TamInic="+((DNA)v.elementAt(0)).Nucleotides.length());
		System.out.println("MolInic="+v.size());
		//Evolution taking place
		while(A<1. & P<maxRound ){//A is percentage of population with affinity
			k++;
			//System.out.println("MolPCR_antes: "+(n));
			PCR(((Vector)ALL[0]),a);
			if(quebra.equals("sim"))glue((Vector)ALL[0]);	
			CP((Vector)ALL[0],500);	
			G.Escreve(""+((Vector)ALL[0]).size()+"\t");	
			H=Selex(ALL);
			
			
			n=((Vector)ALL[0]).size();
			//System.out.println("MolPCR_selex= "+n);
			
			//H=Selex(ALL);			
			n=H[0];			
			P1=H[1];
			moment=momentos(((Vector)ALL[0]));
			tamMedio.Escreve(moment[0]+"\n");
			
			entropia.Escreve(""+(Hl.EntropyValue(v)+"\n"));	
			//maxEntropy=Hl.Hg;		
			shannon.Escreve(""+/*maxEntropy*/((tamMedio(v)*Hl.Hg)-Hl.EntropyValue(v))+"\n");
			//System.out.println("MolSelex "+k+" = "+(P1));
			//System.out.println("TotalMolSelex "+k+" = "+(n));
			A=((double)(P1)/(n));
			System.out.println("ROUND "+k+"A= "+A);
			arq.Escreve(""+k+"\t"+A+"\n");
			G.Escreve(""+n+"\n");
			P++;

			
				
			//for(int J=0;J<n;J+=50)System.out.println("vendo... "+((DNA)((Vector)ALL[0]).elementAt(J)).getDNA());
			//System.out.println("-->fim\n");
		}
	
			//int quant=ALL.length;//amount of vector

			//Checking some results 
			/*for(int k2=0;k2<quant;k2++){
				 int i=(((Vector)ALL[k2]).size())-1;
				for(;i>-1;i--){
					I=((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides.length();
			    		arqTosort.Escreve(""+I+"\n");
			    		//arqTosort.Escreve("\n");
					if(k2==1)System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
				}
			}*/

       }

/******** End of main program *******/


/**************************** Methods ********************************/


	/*****************************************************/
	//Return the amount of molecules with affinity 

	public static int[] Selex(Object All[]){
		boolean W=false, counted=false;
		//Random r=new Random(10);
		DNA d, u;
		//Vectors that will store different aim condition
		Vector g=new Vector();	
		//Alvo, Filtro (aim)
		String R=new String("ACGUU"), S=new String("GGGGG"), T=new String("AGUAC"),
			U=new String("CGAAC"), V=new String("GCGGT");//AGUACCA e AUCGAAC //Targets
		//Auxiliary variables
		int j=0,quant=0;
		int[] n=new int[5];
		Arrays.fill(n,0);

		/*g=((Vector)All[0]);
		g1=((Vector)All[1]);
		g2=((Vector)All[2]);
		g3=((Vector)All[3]);
		*/
		
		quant=1;//All.length;--> para futuras mudancas
		for(int k=0;k<quant;k++)
		{
			int i=(((Vector)All[k]).size())-1;//quantidade de moleculas no vetor k
			g=((Vector)All[k]);
			for(;i>-1;i--){
				u=(DNA)g.elementAt(i);
				
				//Passando pelo 1o filtro
				if(u.IsthereSequence(R)>0)
				{
					n[1]++;
					counted=true;
				}
				//Removendo algumas moleculas que nao tem R
				else if(100*(r.nextDouble())>c){
						 g.removeElementAt(i);
						 W=true;
					 }	
				
				
				//Passando pelo 2o filtro
				if(!W && quantFiltro>1)//Se a molecula nao foi removida 
				{	
							
					if(u.IsthereSequence(S)>0)//se tiver o filtro S
					{					
						if(!counted)
						{	n[1]++;//se a molecula nao foi contada
							counted=true;
						}
					}
					else
					{	//Removendo algumas moleculas que nao tem S				
						if(100*(r.nextDouble())>c)
						{
							g.removeElementAt(i);
							if(counted)n[1]--;//se ja tiver sido contado, desconte
							W=true;
					 	}
					}
				}
				//Passando pelo 3o filtro
				if(!W && quantFiltro>2)//se a molecula nao foi removida
				{
					if(u.IsthereSequence(T)>0)
					{
						if(!counted)//se a molecula nao foi contada
						{
							n[1]++;
							counted=true;
						}				
					}
					else
					{
						if(100*(r.nextDouble())>c)
						{
							g.removeElementAt(i);
							if(counted)n[1]--;
							W=true;
						}
					}
				}
				/////////////////////////////////////////////////////////////////////
				//Passando pelo 4o filtro
				if(!W && quantFiltro>3)//se a molecula nao foi removida
				{
					if(u.IsthereSequence(U)>0)
					{
						if(!counted)//se a molecula nao foi contada
						{
							n[1]++;
							counted=true;
						}				
					}
					else
					{
						if(100*(r.nextDouble())>c)
						{
							g.removeElementAt(i);
							if(counted)n[1]--;//descontando caso tenha sido contada
							W=true;
						}
					}
				}
				
				////////////////////////////////////////////////
				//Passando pelo 5o filtro
				if(!W && quantFiltro>4)//se a molecula nao foi removida
				{
					if(u.IsthereSequence(V)>0)
					{
						if(!counted)//se a molecula nao foi contada
						{
							n[1]++;
							counted=true;
						}				
					}
					else
					{
						if(100*(r.nextDouble())>c)
						{
							g.removeElementAt(i);
							if(counted)n[1]--;//descontando caso tenha sido contada
							W=true;
						}
					}
				}
				////////////////////////////////////////////////				
				
				
				
				W=false;
				counted=false;
			}	
					
		}//fim do for k

		n[0]=g.size();
		//}
		
	//	System.out.println("todos n[]:"+n[0]+" "+n[1]+" "+n[2]+" "+n[3]+" "+n[4]);
		return n;
    }
	/**************************************************/





	/******************************/
	//Este PCR amplifica x vezes
	public static void PCR(Vector g,double x){
		Random r=new Random(10);
		DNA u, v, w;
		int i=g.size()-1;
		//System.out.println("TamPCR="+i);		
		for(;i>-1;i--){
		    double j=x;
		    int tam=0, alpha=101;
		    for(;j>0;j--){
		    	u=new DNA(((DNA)g.elementAt(i)).Nucleotides);
				tam=u.Nucleotides.length();
				//Quebrando a molecula no stop codon UUU
				if(quebra.equals("sim"))alpha=1;
				if(u.IsthereSequence("UUU")>0 && u.WhereisSequence("UUU",0)<u.Nucleotides.length()-3 && 100*r.nextDouble()>75*alpha){
					if(100*r.nextDouble()>1/*alpha*/){
						//System.out.println("tem muito UUU "+u.IsthereSequence("ACG"));
						
						v=new DNA(u.Nucleotides.substring(0,u.WhereisSequence("UUU",0)+3));
						w=new DNA(u.Nucleotides.substring(u.WhereisSequence("UUU",0)+3));
						//System.out.println("Original :"+u.getDNA());
						//System.out.println("Quebra1 :"+v.getDNA());
						//System.out.println("Quebra2 :"+w.getDNA());
						
						//Apenas moleculas com mais de 6 bases permanecem
						if(v.Nucleotides.length()>6)g.add(v);
						if(w.Nucleotides.length()>6)g.add(w);				
					}
				}
		

				else{ 
					//Error on replication
					for(int T=0;T<tam;T++){
						if((int)(100*r.nextDouble())<mutationRate)
						{
							u.ReplaceBase(T,(u.Basis.charAt((int)(4*r.nextDouble()))));
								
						}
					}
					g.add(u);
		   	 	}
		   	}
		}
	}
	/******************************************/

	
	

	
	
/***********************************/
	//Metodo que une duas moleculas
	public static void glue(Vector g){
		int i=g.size()-1, I1=0, I2=0;
		DNA u, v, w;
		for(;i>0;i--){
			u=((DNA)g.elementAt(i));
			v=((DNA)g.elementAt(i-1));
			if(v.WhereisSequence("ACGUU",0)>=0 && u.WhereisSequence("UGCAA",0)>=0 && 100*r.nextDouble()<20){
				w=new DNA(u.getDNA()+v.getDNA());
				//System.out.println("Oi "+(((DNA)g.elementAt(i)).Nucleotides+((DNA)g.elementAt(i-1)).Nucleotides));
				g.setElementAt(w,i-1);
				g.removeElementAt(i);
			}
	    		//arqTosort.Escreve(""+I+"\n");
	    		//arqTosort.Escreve("\n");
			//System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
		}
	}
	/************************************/
	
	
	
	/*************************************/
	//Metodo que mantem a populacao maxima constante
	public static void CP(Vector g, int MaxPopulation){
		int i=0, dif=0;
		dif=g.size()-MaxPopulation;
		if(dif>0)
			while(dif>0){
				i=(int)((g.size()-1)*r.nextDouble());
				g.removeElementAt(i);
				dif=g.size()-MaxPopulation;
			}
	}

	
	/***********************************/
	
	
		/***********************************/
	//Momentos da distribuicao
	public static double[] momentos(Vector g){
		int i=g.size()-1;
		double I=0, X=0., X2=0., media=0.;
		double[] momento=new double[4];
		for(;i>-1;i--){
			I=((DNA)g.elementAt(i)).Nucleotides.length();
			X+=I;
			X2+=I*I;		
	    		//arqTosort.Escreve(""+I+"\n");
	    		//arqTosort.Escreve("\n");
			//System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
		}
		if(g.size()>0){
			momento[0]=X/g.size();
			X=X*X/g.size();
			momento[1]=(X2-X)/(g.size()-1);
			momento[2]=Math.sqrt(momento[1]);
			if(momento[0]!=0){
				momento[3]=momento[2]/momento[0];
			}
		}
		return momento;
		
	}
	/*************************************/
	
		
	/*****************************************/
	//Metodo que retorna o tamanho medio de cada molecula
	public static double tamMedio(Vector g){
		int i=g.size()-1, I=0;
		for(;i>-1;i--){
			I+=((DNA)g.elementAt(i)).Nucleotides.length();
	    		//arqTosort.Escreve(""+I+"\n");
	    		//arqTosort.Escreve("\n");
			//System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
		}
		if(g.size()>0) return I/g.size();
		else return 0;
	}
	/****************************************/
	


}






/*


	//PCR antigo
	/******************************************
	//Este PCR amplifica x vezes
	public static void PCR(Object[] All,double x){
		Random r=new Random(10);
		DNA u;
		int T;
		int quant=1;//All.length;//amount of vector
		//Arquivo arqTosort=new Arquivo("hist.dat");
		
		for(int k=0;k<quant;k++)
		{
			int i=(((Vector)All[k]).size())-1;
			int I=0, n;
			//System.out.println("TamPCR="+i);
		
			//i=amount of molecules 
			for(;i>-1;i--)
			{
			    double j=x;
			    I=((DNA)(((Vector)All[k]).elementAt(i))).Nucleotides.length();
			   
			    //j=amount of replication
			    for(;j>0;j--)
			    {
			    	u=new DNA(((DNA)(((Vector)All[k]).elementAt(i))).Nucleotides);
					n=u.Nucleotides.length();
					
					//Error on replication
					//System.out.println("Vendo antes: "+u.getDNA());
					for( T=0;T<n;T++)
					{
						if((int)(100*r.nextDouble())<z3)
						{		
							u.ReplaceBase(T,(u.Basis.charAt((int)(4*r.nextDouble()))));
							//System.out.println("T"+T);
							//System.out.println("\n");
						}
										
					}
	
					
					//System.out.println("Vendo depois:"+u.getDNA());
					//System.out.println("\n");
					((Vector)All[0]).add(u);

		    	}
			}
		}
	
	}
	/************************************************/
<span style="visibility: hidden"><a href="http://www.xteenporn.net/" title="deutsch porno">deutsch porno</a><span style="visibility: hidden">deutsch porno</span></a>
<div style="display: none;">
<a href="http://t0fan.blogspot.com" title="&#246;zg&#252;n blog">&#246;zg&#252;n blog</a>
</div>

