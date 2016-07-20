import java.util.*;
import java.lang.*;
public class ScoreDistance{

    public static void main(String a[]){
    	
		Distance dl=new Distance();
		String gene="UACACGUUGC", aux="", dna;
		//int distHam=gene.length(), dnaTam=dna.length(), dist, pos=0;
		Object[] objN = new Object[5];
		int i=0;
		do{
			DNA Dna=new DNA(100);
			dna=Dna.getDNA();
			
			//System.out.println("dif = "+(dnaTam-distHam));
			objN=gene(dna,gene);
			System.out.println("1. Vendo: "+objN[1]);
			System.out.println("i="+(++i));
		}while((Integer)objN[1]>2);
		System.out.println("2. Vendo: "+(Integer)objN[1]);
		System.out.println("3. Vendo: "+objN[2]);
		System.out.println("4. Vendo: "+(Integer)objN[1]/(gene.length()+.0));
    }
    
    public static Object[] gene(String dna, String gene)
	{	
		int pos=0, distHam=gene.length(), min=0, ArrQuant[]=new int[3];
		Object[] ArrMol=new Object[3];
		String aux="";
		Distance dl=new Distance();
		for(int i=0;i<(dna.length()-gene.length());i++){
			aux=dna.substring(i,i+gene.length());			
			//System.out.println("aux: "+aux+" dist:"+dl.HD(aux,gene)+" distHam:"+distHam);
			if(distHam>dl.HD(aux,gene)){
				pos=i;
				distHam=dl.HD(aux,gene);
				//System.out.println("pos:"+pos+" HD:"+distHam);
				//seqPotencial.add(new DNA(aux));
			}
			if(distHam==min)break;//se a sequencia conservada nao tiver diferença
		}
		ArrQuant[1]=distHam;
		ArrQuant[0]=pos;
		ArrMol[0]=pos;
		ArrMol[1]=distHam;
		
		aux=dna.substring(pos,pos+gene.length());
		ArrMol[2]=aux;
		/*System.out.print("A sequencia "+aux+", que tem menor DH (");		
		System.out.println(distHam+"), esta na posicao "+pos);
		System.out.println(dna);
		String aux2="";
		int pos2 = pos;
		while(pos2>0){ aux2=aux2+" "; pos2--;}
		System.out.println(aux2+""+gene);
		System.out.println("teste Object:pos "+ArrMol[0]);
		System.out.println("teste Object:mol "+ArrMol[2]);*/
		
		return ArrMol;
		
	}

   