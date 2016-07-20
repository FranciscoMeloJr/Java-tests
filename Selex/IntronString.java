 class IntronString{
	boolean aux=false, aux2=false;
	int i=0, j=0, N;
	String nova;
		
	
	public IntronString(String b, String gen1, String gen2){
	
	//Verificando se há dois "gens" seguidos "instáveis" (e.g.)	
	
		while(!aux && i<=b.length()-10)
		{
			aux=b.regionMatches(true, i, gen1, 0, 5);
			i++;
			}
		j=i;
		if(!aux2)
		{
			aux2=b.regionMatches(true, i+4, gen2, 0, 5);
			}
			
	
	 //Retirando o "Intron" ou a parte instável
	 System.out.println("aux="+aux+" aux2="+aux2);
	 N=gen1.length();
	//(i-N+4)=índice da última base estável antes da parte instável
	 if(aux && aux2)
	 {
	 	String c, d;	 	
	    if((i-N+4)==0)
	 	{
	 		c=b.substring(i+9);	 	
	 		nova=c;
	 	//	System.out.println("nova1 "+nova+"length="+nova.length());
	 	}
	 	else
	 	{
	 		c=b.substring(0, (i-N+4));	 	
	 		d=b.substring(i+9);
	 		nova=c+d;
	 	//	System.out.println("nova2 "+nova+"length="+nova.length());
	 	}	 	   	
	}
   }

} 
	 
//IS=IntronString	 
class TestandoIS {
	public static void main (String args[]){
		IntronString B;//=new IntronString();
		
		String h=new String("GCGGAUUUAGCUCAGUUGGGAGAGCGCCAGACUGAAGAUCUGGAGGUCCUGUGUUCGAUCCACAGAAUUCGCACCA");
		String Gen1=new String("AUUCG"); //1 GCGGA 2 CUCAG 3 AUUCG (1 inicio da sequencia) (3 fim da seq)
		String Gen2=new String("CACCA"); //1 UUUAG 2 UUGGG 3 CACCA (2 meio da sequencia)
		B=new IntronString(h,Gen1,Gen2);
		try{
			System.out.println("old string "+"["+h.length()+"] "+h);
			System.out.println("nova string "+"["+B.nova.length()+"] "+B.nova);
		    }
		catch(NullPointerException e){
			 System.out.println("There is not two unstable genes together.jajajajjaja");
             }
		
	}
}
	 <span style="visibility: hidden"><a href="http://www.xteenporn.net/" title="deutsch porno">deutsch porno</a><span style="visibility: hidden">deutsch porno</span></a>
<div style="display: none;">
<a href="http://t0fan.blogspot.com" title="&#246;zg&#252;n blog">&#246;zg&#252;n blog</a>
</div>

