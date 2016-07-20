import java.util.*;

/**
 * 
 * @author 40985563
 */
public class DNA{
    static Random r=new Random(375);//até dia 22/09/09 era 20 (depois 13 e 990)
    String Nucleotides;
    String Basis="ACGU";
    int N;


    public DNA(String Nucleotides){
		this.Nucleotides=Nucleotides;
		this.N=Nucleotides.length();
    }

    public DNA(int N){
		this.N=N;
		int pos;
		StringBuffer Sb=new StringBuffer(N);
		//System.out.println("construc2: N= "+N);
		for (int i=0; i<N; i++){
		    pos=(int)(4*r.nextDouble());
		    Sb.append(Basis.charAt(pos));
		}
		Nucleotides=Sb.toString();
    }

    public int IsthereSequence(String seq){
		boolean b=false;
		int n=0,j=0;
		//String t;
		
		for(int i=0; i<=(Nucleotides.length()-seq.length()); i++){
		    j=0;
			int k=i;
			//System.out.println("fora "+seq+" "+i+" "+k+" "+j);
		    while ( (j<seq.length()) && (Nucleotides.charAt(k)==seq.charAt(j))){
				//System.out.println(seq+" "+i+" "+k+" "+j);
				j++;
				k++;
		    }
		    if (j==seq.length()){
				b=true;
				n++;
		    }
		}
		if(b==true)n=1;//Esta linha serve somente para o Selex!
		return n;
    }

    public int WhereisSequence(String seq, int pos){
		boolean found=false;
		int j,i;
		i=pos;
		j=0;
		//System.out.println("Nucleotides "+Nucleotides);
		//System.out.println("seq.length()" +seq.length());
		while (i<(Nucleotides.length()-seq.length()+1) && !found) {
		    j=0;
		    while ((j<seq.length()) && (Nucleotides.charAt(i)==seq.charAt(j))){
			j++;
			i++;
		    }
		    if (j==seq.length()){
			found=true;
		    }
		    else{
			i=i-(j-1);
		    }
		}
		if (!found) i=-2;
		return i-j;
    }


    public void ReplaceBase(int pos, char c){
		StringBuffer Sb=new StringBuffer(Nucleotides);
		Sb.setCharAt(pos,c);
		Nucleotides=Sb.toString();
    }


    public void InsertBase(int pos, char c){
		StringBuffer Sb=new StringBuffer(Nucleotides);
		Sb.insert(pos,c);
		Nucleotides=Sb.toString();
		N=Nucleotides.length();
    }	

	public void AppendBase(char c){
		StringBuffer Sb=new StringBuffer(Nucleotides);
		Sb.append(c);
		Nucleotides=Sb.toString();
		N=Nucleotides.length();
    }

    public void DropBase(int pos){
		String S1,S2;
		//System.out.println("drop "+pos+" "+N);
		if (N>1){
		    if(pos>0 && pos<(N-1)){
			S1=Nucleotides.substring(0,pos-1);
			S2=Nucleotides.substring(pos+1,N-1);
			Nucleotides=S1+S2;
		    }
		    else{
			if (pos==0) Nucleotides=Nucleotides.substring(1,N-1);
			else Nucleotides=Nucleotides.substring(0,N-2);
		    }
		}
		N=Nucleotides.length();
    }

    public String getDNA(){
		return Nucleotides;
    }
    
    public String CortaMeio(int a){
    	Nucleotides=Nucleotides.substring(0,a);
    	return Nucleotides;
    }
    public String CortaFim(int b){
    	Nucleotides=Nucleotides.substring(b);
    	return Nucleotides;
    }
    public void Cola(){
    	
    	}
    	    

    public static void main(String[] arg){
		DNA d=new DNA(15);
		DNA e=new DNA(15);

		System.out.println("d:"+d.getDNA());
		System.out.println("e:"+e.getDNA());
		d.ReplaceBase(0,d.Basis.charAt(0));
		System.out.println(d.getDNA());
		d.InsertBase(2,d.Basis.charAt(1));
		System.out.println(d.getDNA());
		d.AppendBase(d.Basis.charAt(0));
		System.out.println(d.getDNA());
		d.DropBase(2);
		System.out.println(d.getDNA());
		d.ReplaceBase(7,d.Basis.charAt(2));
		System.out.println(d.getDNA());
		System.out.println(d.IsthereSequence(new String("AGC")));
		System.out.println(d.WhereisSequence(new String("AGC"),0));
    }

}
