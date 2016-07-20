import java.util.*;
//import javax.swing.JOptionPane;

public class Selex_const_Airton{
	static Random r=new Random(10);
	//enviroment (global static) variables
	static double b;
	static double c;
	static int length;
	static int quantMol;
	static double mutationRate;
	static int maxRound;
	static int maxMol;
	static String quebra="sim";
	static int quantFiltro=1 ;
	static Vector vecToEntropy=new Vector();
	static Vector vecToEntropy2=new Vector();
	static Vector vecToEntropy3=new Vector();
	static Vector vecToEntropy4=new Vector();
	static Vector vecToEntropy5=new Vector();
	static Vector hamDist=new Vector();
	static long totalNucleotideos=0;
	static long totalParcialNucleotideos=0;
	static long reservatorio;
//	static double z5;   
//	static double z6;5


	public static void main(String []arg){		
		DNA D;
		Scanner sc=new Scanner(System.in);
		//TextoPL savePl = new TextoPL();
		//-->Mudando o fim do nome dos arquivos 
		String dir="temp/", end=""; 
		System.out.println("com ou sem?");	
		end=sc.next();
		if(end.equals("sem"))end="";
		Entropy Hl=new Entropy();
		//Opening or creating files
		Arquivo arq=new Arquivo(dir+"afinidade"+end+".txt");
		arq.Escritura();
		Arquivo G=new Arquivo(dir+"Qde_per_round"+end+".txt");
		G.Escritura();
		//Arquivo arqTosort=new Arquivo(dir+"hist"+end+".txt");	
		//arqTosort.Escritura();
		Arquivo tamMedio=new Arquivo(dir+"tamMedio"+end+".txt");
		tamMedio.Escritura();
		Arquivo entropia=new Arquivo (dir+"entropia"+end+".txt");
		entropia.Escritura();
		//Arquivo shannon=new Arquivo (dir+"shannon"+end+".txt");
		//shannon.Escritura();
		Arquivo last=new Arquivo(dir+"lastConfig"+end+".txt");
		last.Escritura();
		Arquivo text = new Arquivo(dir+"savePL"+end+".txt");
       	text.Escritura();
		Arquivo distHamMedia = new Arquivo(dir+"HamMedia"+end+".txt");
       	distHamMedia.Escritura();       	
       	Arquivo arqHist=new Arquivo(dir+"histBruto"+end+".txt");
		arqHist.Escritura();
		Arquivo arqSequencias=new Arquivo(dir+"sequencias"+end+".txt");
		arqSequencias.Escritura();	
		Arquivo desvioPadrao=new Arquivo(dir+"desvio"+end+".txt");
		desvioPadrao.Escritura();	
		Arquivo arqHistHam=new Arquivo(dir+"histHam"+end+".txt");
		arqHistHam.Escritura();
		Arquivo arqReservatorio =new Arquivo(dir+"Reservatorio"+end+".txt");
		arqReservatorio.Escritura();
		//Creating variables
		double A=0., B, a, maxEntropy=0.;
		int k=0, n=0, P1=0, P2=0, P3=0, I=0, P=0, taxClasse=0;
		int[] H=new int[5];
		double[] moment=new double[5];
		double[] arrHam=new double[30];
		String j=new String("ACGU"), texto;
		Vector v=new Vector();
		Vector v1=new Vector(), v2=new Vector(), v3=new Vector();
		Object ALL[]={v};
//para acessar um elemento: 
//((DNA)(((Vector)ALL[# do vetor]).elementAt(# do elemento dentro do vetor))).Nucleotides
		
		//External values 
		System.out.print("Digite: 1) A eficiencia do filtro ");// 2) Tamanho de cada molecula");
		System.out.println(" 2)Qde inicial de moleculas 3)Taxa de mutacao (%) ");
		System.out.println(" 4)O numero maximo de iteracoes (round ou ciclos)");
		System.out.println(" 5) Tamanho da classe ");
	//	System.out.println( " 5)Quantidade  maxima de moleculas");
		System.out.println(" 6)Quantos filtros? (maximo 5)");
		//System.out.println(" 8)Qual sera o titulo dos graficos?");
		c=sc.nextDouble();//% of mol. that stayed on the target
		length=100;//sc.nextInt();//length of each molecules
		quantMol=sc.nextInt();//amount of initial molecules
		mutationRate=sc.nextDouble();// mutation rate in % 	
		maxRound=sc.nextInt();//numero maximo de iteracoes 
		taxClasse=sc.nextInt();
	///	maxMol=sc.nextInt();
		quebra="nao";//sc.next();//Allowing or not break
		quantFiltro=sc.nextInt();//quantidade de filtros
		texto="";//sc.next()+" ";//Titulo dos graficos
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next()+" ";
		//texto=texto+sc.next();
		last.Escreve("Digite: 1) A eficiencia do filtro ");//2)Tamanho de cada molecula 3)");
		last.Escreve(" 2) Qde inicial de moleculas 3) Taxa de mutacao (%) 4) O numero maximo de iteracoes (round ou ciclos)");
		last.Escreve(" 5) Tamanho da classe 6) Quantidade de filtros");
	//	last.Escreve(" 5)Quantidade  maxima de moleculas");
		//last.Escreve(" 6)Com quebra? 7)A qde de filtros 8)Qual sera o titulo dos graficos?");
		last.Escreve("\n");
		last.Escreve(c+"\t"+quantMol+"\t"+mutationRate+"\t"+maxRound+"\t"+taxClasse+"\t"+quantFiltro);
		text.Escreve(TextoPL.incluiTitle(texto)); 
	//	z5=0;//Double.valueOf(arg[5]).doubleValue();//% q nao entra no filtro A
	//	z6=0;//Double.valueOf(arg[6]).doubleValue();//% q nao entra no filtro B

      	b=100.;
		a=1;
		totalNucleotideos=0;
		//Creating DNA molecules
		for(int i=0;i<quantMol;i++){ //quantMol is the amount of inicial molecules
			if(i<quantMol){
				D=new DNA(length);//100 is the length of molecules (int)(z)
				//D=new DNA("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
				v.add(D);
				totalNucleotideos+=length;
			} 
			else {
				D=new DNA("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
				
				v.add(D);
				totalNucleotideos+=D.getDNA().length();
			/*	D=new DNA("AAAAAAAAAUACACGUUGCACAXGGGAAAAAAAAAACGUUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
										
				v.add(D);
				totalNucleotideos+=D.getDNA().length();*/
					
					
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
		moment=momentos(((Vector)ALL[0]),taxClasse);
		tamMedio.Escreve(moment[0]+"\n");
		desvioPadrao.Escreve(moment[3]+"\n");
		arrHam=DistMedia(hamDist);
		for(int i=0;i<=15;i++){
			if(i==4)System.out.println("moment1["+(i)+"]="+moment[i]);
			if(i>4 && i<=15)arqHist.Escreve(moment[i]+"\t");
			if(i<=9)arqHistHam.Escreve(arrHam[i]+"\t");
		}
		totalParcialNucleotideos=(long)moment[4];
		arqHist.Escreve("\n");
		Arrays.fill(moment,0);
		//Evolution taking place
		while(P<maxRound && A<1. /*&& P1<(int)(1*quantMol)*/){//A is percentage of population with affinity
			k++;
			//System.out.println("MolPCR_antes: "+(n));
			PCR(((Vector)ALL[0]),a);
			/*if(quebra.equals("sim"))*/glue((Vector)ALL[0]);	
			//CP((Vector)ALL[0],maxMol);	
			
			cleanVector(vecToEntropy);
			cleanVector(vecToEntropy2);
			cleanVector(vecToEntropy3);
			cleanVector(vecToEntropy4);
			cleanVector(vecToEntropy5);
			cleanVector(hamDist);
			Arrays.fill(arrHam,0);
			G.Escreve(""+((Vector)ALL[0]).size()+"\t");
			H=Selex(ALL);
			//System.out.println("checando o tamanho: "+vecToEntropy.size());
				
			
			//n=((Vector)ALL[0]).size();
			//System.out.println("VENDO N FROM ALL= "+n);
			//System.out.println("TESTE:"+P);
			//H=Selex(ALL);			
			n=H[0];			
			//System.out.println("VENDO N FROM H= "+n);
			P1=H[1];
			moment=momentos(((Vector)ALL[0]),taxClasse);
			System.out.println("reservatorio:"+reservatorio);
			arrHam=DistMedia(hamDist);
			tamMedio.Escreve(moment[0]+"\n");
			desvioPadrao.Escreve(moment[3]+"\n");
			distHamMedia.Escreve(arrHam[10]+"\n");
			totalParcialNucleotideos=(long)moment[4];
			for(int i=5;i<=15;i++){
				arqHist.Escreve(moment[i]+"\t");
				if((i-5)<=9)arqHistHam.Escreve(arrHam[i]+"\t");
			}
			arqHist.Escreve("\n");
			//System.out.println("Qde vec entropy1: "+vecToEntropy.size()+" vec to entropy2:"+vecToEntropy2.size());
			
			try{
				double aux=0;/*=((Hl.EntropyValue(vecToEntropy3))+(Hl.EntropyValue(vecToEntropy2))+(Hl.EntropyValue(vecToEntropy)));
				aux+=(Hl.EntropyValue(vecToEntropy5))+(Hl.EntropyValue(vecToEntropy4));*/
				
				aux=Hl.EntropyValue(vecToEntropy);
			 	if(quantFiltro>=2){aux+=Hl.EntropyValue(vecToEntropy2);} //aux=aux2
				if(quantFiltro>=3){aux+=Hl.EntropyValue(vecToEntropy3);}//aux+=20;
				if(quantFiltro>=4){aux+=Hl.EntropyValue(vecToEntropy4);}//aux+=20;
				if(quantFiltro>=5){aux+=Hl.EntropyValue(vecToEntropy5);}//aux+=20;
				
			
				if(H[1]>0){
					//System.out.println("MaxEntropy:"+(-quantFiltro*10.)*log2((1./4)));
					//System.out.println("Real Entropy:"+aux);
					entropia.Escreve(aux/((-quantFiltro*10)*log2((1./4)))+"\n");	
					//((Vector)ALL[0])
				}
						
				
			}
			
			catch(NullPointerException e){
				entropia.Escreve(0+"\n");
				
			}
			
			
				
			//maxEntropy=Hl.Hg;		
			//shannon.Escreve(""+/*maxEntropy*/((tamMedio(v)*Hl.Hg)-Hl.EntropyValue(v))+"\n");
			//System.out.println("MolSelex "+k+" = "+(P1));
			//System.out.println("TotalMolSelex "+k+" = "+(n));
			A=((double)(P1)/(n));
			System.out.println("ROUND "+k+" A= "+A);
			arq.Escreve(""+k+"\t"+A+"\n");
			G.Escreve(""+n+"\n");
			
			if(P==0)
			{
				
				for(int J=0;J<n;J++){
					if (J==0)arqSequencias.Escreve("\n MOLÉCULA INÍCIO\n\n\n\n");
					arqSequencias.Escreve(((DNA)((Vector)ALL[0]).elementAt(J)).getDNA()+"\n");
				}
			}	
			P++;
			arqReservatorio.Escreve(reservatorio+"\n");
		
			
			
				
			//for(int J=0;J<n;J+=50)System.out.println("vendo... "+((DNA)((Vector)ALL[0]).elementAt(J)).getDNA());
			//System.out.println("-->fim\n");
		}
		arqSequencias.Escreve("\n MOLÉCULA última\n\n\n\n");
		for(int J=0;J<n;J++){
			arqSequencias.Escreve(((DNA)((Vector)ALL[0]).elementAt(J)).getDNA()+"\n");
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
		Vector g=new Vector()/*, vecToEntropy=new Vector()*/;	
		//Alvo, Filtro (aim)
		String R=new String("ACGUU"), S=new String("GGGGG"), T=new String("AGUAC"),
			U=new String("CGAAC"), V=new String("GCGGT");//AGUACCA e AUCGAAC //Targets
		String seqDNA="", gens="UAACGGCAAU", gens2="CCGAUUAGCC", gens3="AGCUCCUCGA";        //"UACACGUUGCACAU";
		String gens4="GUUGAAGUUG", gens5="UAUACCAUAU";
		//Auxiliary variables
		Object[] ObjN, ObjN2, ObjN3, ObjN4, ObjN5;
		int j=0,quant=0;
		int[] n=new int[5], ArrN=new int[3];
		Arrays.fill(n,0);
		
		Arrays.fill(ArrN,0);


		
		quant=1;//All.length;--> para futuras mudanças
		for(int k=0;k<quant;k++)
		{
			int i=(((Vector)All[k]).size())-1;//quantidade de moleculas no vetor k
			g=((Vector)All[k]);
			W=false;
			
			for(;i>-1;i--){
				W=false;
				seqDNA=((DNA)(g.elementAt(i))).getDNA();
				ObjN=ScoreDistance.gene(seqDNA,gens);
				ObjN2=ScoreDistance.gene(seqDNA,gens2);
				ObjN3=ScoreDistance.gene(seqDNA,gens3);
				ObjN4=ScoreDistance.gene(seqDNA,gens4);
				ObjN5=ScoreDistance.gene(seqDNA,gens5);
				//System.out.println("Vendo o erro..."+ObjN2[2]);
				
				
				
				//********* Primeiro filtro *********************//
				//Se houver a sequencia inteira conservada na molecula
				if((Integer)ObjN[1]==0)
				{
					//System.out.println("Primeiro filtro"+i);
					n[1]++;
					counted=true;
					vecToEntropy.add(new DNA((String)ObjN[2]));		
					hamDist.add((Integer)(ObjN[1]));
					
					//System.out.println("Tem Molecula SIM!!!"+ObjN[2]);
					//System.out.println("Eh a molecula "+((DNA)(vecToEntropy.elementAt(vecToEntropy.size()-1))).getDNA());
				}
				//Caso não seja encontrado a sequencia conservada exatamente
				//mas umas partes dela
				else
				{
					//System.out.println("Primeiro filtro"+i);
				
					if(100*((((Integer)ObjN[1])/(gens.length()+.0)))<100*r.nextDouble())
					{	
						try{
							if((Integer)(ObjN[1])<(gens.length()/2.))n[1]++;//Contagem das mol. afins
							vecToEntropy.add(new DNA((String)(ObjN[2])));
							hamDist.add((Integer)(ObjN[1]));
							counted=true;
							//System.out.println("Sera que esta la? "+((DNA)(vecToEntropy.elementAt(0))).getDNA());
						}
						catch(NullPointerException e){
			 				//System.out.println("A JSJSKSL JS FWSDEJF GWKJF .");
			 				n[1]--;
			 				counted=false;
			 				hamDist.removeElementAt(hamDist.size()-1);
			 				vecToEntropy.removeElementAt(vecToEntropy.size()-1);
             			}										
					}
					else
					{
						if(100*(r.nextDouble())>c)//removendo algumas moleculas nao afins
						{
							//System.out.println("Selex antes1:"+reservatorio);
							//if(reservatorio<0)reservatorio=0;
						//	System.out.println("Selex antes2:"+reservatorio);
							reservatorio+=((DNA)(g.elementAt(i))).getDNA().length();
							g.removeElementAt(i);
							W=true;
							//System.out.println("Selex depois:"+reservatorio);
						}
					}
				}
				//*************** Fim primeiro filtro ******************//
				
				
				
					//********    Segundo filtro  *****************///
				if(!W && quantFiltro>=2)		
				{
					//System.out.println("Segundo filtro"+i);
					//ObjN2=ScoreDistance.gene(seqDNA,gens2);
					//System.out.println("Vendo o outro vec..."+ObjN[1]);
					//Se houver a sequencia conservada na molecula
					if((Integer)ObjN2[1]==0)
					{
						if(!counted)
						{
							n[1]++;
							counted=true;
							vecToEntropy2.add(new DNA((String)ObjN2[2]));		
							hamDist.add((Integer)(ObjN2[1]));
						}
						else vecToEntropy2.add(new DNA((String)ObjN2[2]));
						
						
						//System.out.println("Tem Molecula SIM!!!"+ObjN[2]);
						//System.out.println("Eh a molecula "+((DNA)(vecToEntropy.elementAt(vecToEntropy.size()-1))).getDNA());
					}
					//Caso não seja encontrado a sequencia conservada exatamente
					//mas umas partes dela
					else
					{
					
						if(100*((((Integer)ObjN2[1])/(gens2.length()+.0)))<100*r.nextDouble())
						{	
							try{
												
									if(!counted)
									{
										if((Integer)(ObjN2[1])<(gens2.length()/2.))n[1]++;
										counted=true;
										vecToEntropy2.add(new DNA((String)(ObjN2[2])));
										hamDist.add((Integer)(ObjN2[1]));
									}
									else vecToEntropy2.add(new DNA((String)ObjN2[2]));				
								
								
								//System.out.println("Sera que esta la? "+((DNA)(vecToEntropy.elementAt(0))).getDNA());
							}
							catch(NullPointerException e){
				 				//System.out.println("A JSJSKSL JS FWSDEJF GWKJF .");
				 				n[1]--;
				 				hamDist.removeElementAt(hamDist.size()-1);
				 				vecToEntropy2.removeElementAt(vecToEntropy2.size()-1);
	             			}										
						}
						else
						{
							if(100*(r.nextDouble())>c)//removendo algumas moleculas nao afins
							{
								//System.out.println("Selex antes1:"+reservatorio);
								//if(reservatorio<0)reservatorio=0;
							//	System.out.println("Selex antes2:"+reservatorio);
								reservatorio+=((DNA)(g.elementAt(i))).getDNA().length();
								g.removeElementAt(i);
								W=true;
							}
						}
					}
				}// fim if w
					//************** fim segundo filtro ************************///
					
					//**************** terceiro filtro************************//
					
				if(!W&& quantFiltro>=3)		
				{
					//System.out.println("Terceiro filtro"+i);
					//ObjN2=ScoreDistance.gene(seqDNA,gens2);
					//System.out.println("Vendo o outro vec..."+ObjN[1]);
					//Se houver a sequencia conservada na molecula
					if((Integer)ObjN3[1]==0)
					{
						if(!counted)
						{
							n[1]++;
							counted=true;
							vecToEntropy3.add(new DNA((String)ObjN3[2]));		
							hamDist.add((Integer)(ObjN3[1]));
						}
						else vecToEntropy3.add(new DNA((String)ObjN3[2]));
						
						
						//System.out.println("Tem Molecula SIM!!!"+ObjN[2]);
						//System.out.println("Eh a molecula "+((DNA)(vecToEntropy.elementAt(vecToEntropy.size()-1))).getDNA());
					}
					//Caso não seja encontrado a sequencia conservada exatamente
					//mas umas partes dela
					else
					{
					
						if(100*((((Integer)ObjN3[1])/(gens3.length()+.0)))<100*r.nextDouble())
						{	
							try{
												
									if(!counted)
									{
										if((Integer)(ObjN3[1])<(gens3.length()/2.))n[1]++;
										counted=true;
										vecToEntropy3.add(new DNA((String)(ObjN3[2])));
										hamDist.add((Integer)(ObjN3[1]));
									}
									else vecToEntropy3.add(new DNA((String)ObjN3[2]));													
								
								
								//System.out.println("Sera que esta la? "+((DNA)(vecToEntropy.elementAt(0))).getDNA());
							}
							catch(NullPointerException e){
				 				//System.out.println("A JSJSKSL JS FWSDEJF GWKJF .");
				 				n[1]--;
				 				hamDist.removeElementAt(hamDist.size()-1);
				 				vecToEntropy3.removeElementAt(vecToEntropy3.size()-1);
	             			}										
						}
						else
						{
							if(100*(r.nextDouble())>c) //removendo algumas moleculas nao afins
							{
								//System.out.println("Selex antes1:"+reservatorio);
								//if(reservatorio<0)reservatorio=0;
							//	System.out.println("Selex antes2:"+reservatorio);
								reservatorio+=((DNA)(g.elementAt(i))).getDNA().length();
								g.removeElementAt(i);
								W=true;
							}
						}
					}
				}
					
					//*************** fim terceiro filtro ********************//
					
					//*********** quarto filtro *********************//
			    if(!W&& quantFiltro>=4)		
				{
					//System.out.println("Quarto filtro"+i);
					if((Integer)ObjN4[1]==0)
					{
						if(!counted)
						{
							n[1]++;
							counted=true;
							vecToEntropy4.add(new DNA((String)ObjN4[2]));		
							hamDist.add((Integer)(ObjN4[1]));
						}
						else vecToEntropy4.add(new DNA((String)ObjN4[2]));
					}
					//Caso não seja encontrado a sequencia conservada exatamente
					//mas umas partes dela
					else
					{
						if( 100*((((Integer)ObjN4[1])/(gens4.length()+.0)))<100*r.nextDouble())
						{	
							try{				
								if(!counted)
								{
									if((Integer)(ObjN4[1])<(gens4.length()/2.)) n[1]++;
									counted=true;
									vecToEntropy4.add(new DNA((String)(ObjN4[2])));
									hamDist.add((Integer)(ObjN4[1]));
								}
								else vecToEntropy4.add(new DNA((String)ObjN4[2]));				
							}
							catch(NullPointerException e){
				 				n[1]--;
				 				hamDist.removeElementAt(hamDist.size()-1);
				 				vecToEntropy4.removeElementAt(vecToEntropy4.size()-1);
	             			}										
						}
						else
						{
							if(100*(r.nextDouble())>c) //removendo algumas moleculas nao afins
							{
								reservatorio+=((DNA)(g.elementAt(i))).getDNA().length();
								g.removeElementAt(i);
								W=true;
							}
						}
					}//else
				}//fim w
				
					//********** fim do quarto filtro ***************//
					
					///////////////////////////////
						//*********** quinto filtro *********************//
			    if(!W&& quantFiltro>=5)		
				{
					//System.out.println("Quinto filtro"+i);
					if((Integer)ObjN5[1]==0)
					{
						if(!counted)
						{
							n[1]++;
							counted=true;
							vecToEntropy5.add(new DNA((String)ObjN5[2]));		
							hamDist.add((Integer)(ObjN5[1]));
						}
						else vecToEntropy5.add(new DNA((String)ObjN5[2]));
					}
					//Caso não seja encontrado a sequencia conservada exatamente
					//mas umas partes dela
					else
					{
						if(100*((((Integer)ObjN5[1])/(gens5.length()+.0)))<100*r.nextDouble())
						{	
							try{				
								if(!counted)
								{
									if((Integer)(ObjN5[1])<(gens5.length()/2.)) n[1]++;
									counted=true;
									vecToEntropy5.add(new DNA((String)(ObjN5[2])));
									hamDist.add((Integer)(ObjN5[1]));
								}
								else vecToEntropy5.add(new DNA((String)ObjN5[2]));				
							}
							catch(NullPointerException e){
				 				n[1]--;
				 				hamDist.removeElementAt(hamDist.size()-1);
				 				vecToEntropy5.removeElementAt(vecToEntropy5.size()-1);
	             			}										
						}
						else
						{
							if(100*(r.nextDouble())>c) //removendo algumas moleculas nao afins
							{
								reservatorio+=((DNA)(g.elementAt(i))).getDNA().length();
								g.removeElementAt(i);
								W=true;
							}
						}
					}//else
				}//fim w
				
					//********** fim do quinto filtro ***************//
					/////////////////////////////////
					
			}//fim do for i	
					
		}//fim do for k
			
		n[0]=g.size();
		
		
		
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
		boolean W=false, W2=false;
		//System.out.println("TamPCR="+i);		
		for(;i>-1;i--){
		    double j=x;
		    int tam=0, alpha=101;
		    //totalParcialNucleotideos=0;
		    for(;j>0;j--){
		    	u=new DNA(((DNA)g.elementAt(i)).Nucleotides);
				tam=u.Nucleotides.length();
				//Quebrando a molecula no stop codon UUU
				if(quebra.equals("sim"))alpha=1;
				if(u.IsthereSequence("UUU")>0 && u.WhereisSequence("UUU",0)<u.Nucleotides.length()-3 /*&& 100*r.nextDouble()>75*alpha*/){
					//System.out.println("tem muito UUU "+u.IsthereSequence("ACG"));
					v=new DNA(u.Nucleotides.substring(0,u.WhereisSequence("UUU",0)+3));
					w=new DNA(u.Nucleotides.substring(u.WhereisSequence("UUU",0)+3));
					//System.out.println("Original :"+u.getDNA());
					//System.out.println("Quebra1 :"+v.getDNA());
					//System.out.println("Quebra2 :"+w.getDNA());
					
					//Apenas moleculas com mais de 10 bases permanecem
					if(v.getDNA().length()>=10 )
					{
						if(v.getDNA().length()<=reservatorio)
						{
							g.add(v);
							reservatorio-=v.getDNA().length();
						}
						//if(reservatorio<=0)reservatorio=0;
					}
				//	else if((totalParcialNucleotideos+v.getDNA().length())<totalNucleotideos) reservatorio+=v.getDNA().length();
					if(w.getDNA().length()>=10 )
					{
						if(w.getDNA().length()<=reservatorio)
						{
							g.add(w);	
							reservatorio-=w.getDNA().length();
						}
																//if(reservatorio<=0)reservatorio=0;
					}
				 	reservatorio+=u.getDNA().length();
					g.removeElementAt(i);								
					
				}
		

				else{ 
					
					/*if(totalParcialNucleotideos)*/
					if(tam<=reservatorio /*&& totalNucleotideosa>totalParcialNucleotideos*/)
					{
					//	System.out.println("Entrei! reserv:"+reservatorio+"tam:"+tam);
							
						//Error on replication
						for(int T=0;T<tam;T++){
							if((int)(100*r.nextDouble())<mutationRate)
							{
								u.ReplaceBase(T,(u.Basis.charAt((int)(4*r.nextDouble()))));
									
							}
						}
						g.add(u);
						reservatorio-=tam;	
						//System.out.println("Sai! reserv:"+reservatorio);
					}
					else break;
					
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
			v=((DNA)g.elementAt(i-1)); //gene=UAACGGCAAU
			if(v.WhereisSequence("U",0)>=0 && u.WhereisSequence("C",0)>=0 && 100*r.nextDouble()<30){
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
	/*public static void CP(Vector g, int MaxPopulation){
		int i=0, dif=0;
		dif=g.size()-MaxPopulation;
		if(dif>0)
			while(dif>0){
				i=(int)((g.size()-1)*r.nextDouble());
				g.removeElementAt(i);
				dif=g.size()-MaxPopulation;
			}
	}*/

	
	/***********************************/
	
	
		/***********************************/
	//Momentos da distribuicao
	public static double[] momentos(Vector g, int n){
		//System.out.println("a\n");
		int i=g.size()-1;
		double I=0, X=0., X2=0., media=0.,aux;
		double[] momento=new double[30];
		for(;i>-1;i--){
			I=((DNA)g.elementAt(i)).Nucleotides.length();
			X+=I;
			X2+=I*I;
			if(I>=0 && I<10*n)momento[5]++;
			if(I>=10*n && I<20*n)momento[6]++;
			if(I>=20*n && I<30*n)momento[7]++;
			if(I>=30*n && I<40*n)momento[8]++;
			if(I>=40*n && I<50*n)momento[9]++;
			if(I>=50*n && I<60*n)momento[10]++;
			if(I>=60*n && I<70*n)momento[11]++;
			if(I>=70*n && I<80*n)momento[12]++;
			if(I>=80*n && I<90*n)momento[13]++;
			if(I>=90*n && I<100*n)momento[14]++;			
			if(I>=100*n)momento[15]++;		
	    		//arqTosort.Escreve(""+I+"\n");
	    		//arqTosort.Escreve("\n");
			//System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
		}

		if(g.size()>0){
			momento[4]=X;
			momento[0]=X/g.size();
			X=X*X/g.size();
			momento[1]=(X2-X)/(g.size()-1);
			momento[2]=Math.sqrt(momento[1]);
			if(momento[0]!=0){
				momento[3]=momento[2]/momento[0];
			}
		}
		//for(int uu=0;uu<=15;uu++){
		System.out.println(" momento["+(4)+"]="+momento[4]);
		//System.out.println("reservatorio dentro:"+reservatorio);
		//}
		return momento;
		
	}
	/*************************************/
	
		
	/*****************************************/
	//Metodo que retorna o tamanho medio de cada molecula
	/*public static double tamMedio(Vector g){
		Arquivo arqHist=new Arquivo("histBruto_teste.txt");
		arqHist.Escritura();
		int[] arrHist = new int[20];
		Arrays.fill(arrHist,0);		
		System.out.println("Legalzin");
		int i=g.size()-1, I=0, aux=0;
		for(;i>-1;i--){
			aux=((DNA)g.elementAt(i)).Nucleotides.length();
			I+=aux;
			if(aux>0 && aux<=10)arrHist[0]++;
			if(aux>10 && aux<=20)arrHist[1]++;
			if(aux>20 && aux<=30)arrHist[2]++;
			if(aux>30 && aux<=40)arrHist[3]++;
			if(aux>40 && aux<=50)arrHist[4]++;
			if(aux>50 && aux<=60)arrHist[5]++;
			if(aux>60 && aux<=70)arrHist[6]++;
			if(aux>70 && aux<=80)arrHist[7]++;
			if(aux>80 && aux<90)arrHist[8]++;
			if(aux>100)arrHist[9]++;
	    		//arqTosort.Escreve(""+I+"\n");
	    		//arqTosort.Escreve("\n");
			//System.out.println("vendo "+k2+""+i+" "+((DNA)(((Vector)ALL[k2]).elementAt(i))).Nucleotides);
		}
		arqHist.Escreve(arrHist[0]+"\n");
		arqHist.Escreve(arrHist[1]+"\n");
		arqHist.Escreve(arrHist[2]+"\n");
		arqHist.Escreve(arrHist[3]+"\n");
		arqHist.Escreve(arrHist[4]+"\n");
		arqHist.Escreve(arrHist[5]+"\n");
		arqHist.Escreve(arrHist[6]+"\n");
		arqHist.Escreve(arrHist[7]+"\n");
		arqHist.Escreve(arrHist[8]+"\n");
		arqHist.Escreve(arrHist[9]+"\n");
		//arqHist.Escreve(arrHist[]+"\n");
		//arqHist.Escreve(arrHist[]+"\n");
		if(g.size()>0) return I/g.size();
		else return 0;
	}*/
	/****************************************/
	
	/***************************************/
	//Metodo que remove todos elementos de um Vector
	public static void cleanVector(Vector g){
	
			
		try{
			for(int i=g.size()-1;i>-1;i--)g.removeElementAt(i);	
		}
		catch(NullPointerException e){
			 //System.out.println("There is not two unstable genes together.");
        }
		
	}
	
	public static double[] DistMedia(Vector g){
		int j=g.size(), I=0;
		int aux=0;
		//Arquivo arqHistHam=new Arquivo("histHam.txt");
		//arqHistHam.Escritura();
		double[] arrHam=new double[30];
		for(int i=j-1;i>-1;i--){
			aux=((Integer)(g.elementAt(i)));
			I+=aux;
			if(aux>0 && aux<=1)arrHam[0]++;
			if(aux>1 && aux<=2)arrHam[1]++;
			if(aux>2 && aux<=3)arrHam[2]++;
			if(aux>3 && aux<=4)arrHam[3]++;
			if(aux>4 && aux<=5)arrHam[4]++;
			if(aux>5 && aux<=6)arrHam[5]++;
			if(aux>6 && aux<=7)arrHam[6]++;
			if(aux>7 && aux<=8)arrHam[7]++;
			if(aux>8 && aux<=9)arrHam[8]++;
			if(aux>10)arrHam[9]++;
	    	
		}
		if (j>0)arrHam[10]=(I+0.)/j;
		else arrHam[10]=0.;
		return arrHam;
	}
	
	
	public static double log2(double x){
		return Math.log(x)/Math.log(2.);
	}

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

