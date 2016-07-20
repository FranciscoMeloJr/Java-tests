import java.io.*;
import java.util.zip.*;

/**
 * 
 * @author 40985563
 */
public class Arquivo{
    File arq;
    boolean legivel,zip,gzip,escritura,leitura;
    FileOutputStream arqout;
    FileInputStream arqin;
    ZipOutputStream zipout;
    ZipInputStream zipin;
    ZipEntry zipentry;
    OutputStreamWriter escrivao;
    InputStreamReader leitorstr;
    StreamTokenizer leitor;
    boolean fim;
    int charespecial;
    


    public Arquivo(String nome){
		arq=new File(nome);
		//(arq.exists()? legivel=true:legivel=false);
		if (arq.exists()) legivel=true;
		else legivel=false;
		zip=false;
		fim=false;
    }

    public void setZip(boolean zip){
		this.zip=zip;
    }

    public void Leitura(){
	if(legivel){
	    try{
		arqin=new FileInputStream(arq);
		if (!zip) leitorstr=new InputStreamReader(arqin);
		if (zip) {
		    zipin=new ZipInputStream(arqin);
		    leitorstr=new InputStreamReader(zipin);
		}
		leitor=new StreamTokenizer(leitorstr);
		leitor.eolIsSignificant(true);	    }
	    catch(IOException e){
		System.out.println("Erro na criação do Stream "+e);
	    }
	}
    }

    public void Escritura(){
	try{
	    arqout=new FileOutputStream(arq);
	    if (!zip) escrivao=new OutputStreamWriter(arqout);
	    if (zip) {
		zipout=new ZipOutputStream(arqout);
		escrivao=new OutputStreamWriter(zipout);
	    }
	}
	catch(IOException e){
	    System.out.println("Erro na criação do Stream "+e);
	}

    }

    public void AbreEntradaZip(String nome){
	try{
	    zipentry=new ZipEntry(nome);
	    zipout.putNextEntry(zipentry);
	}
	catch(IOException e){ System.out.println("Erro na entrada zip "+e);}
    }

    public void FechaEntradaZip(){
	try{
	    zipin.closeEntry();
	}
	catch(IOException e){System.out.println("Erro no fechamento de entrada zip "+e);}
    }


    public void Escreve(String texto){
	try{
	    escrivao.write(texto);
	    escrivao.flush();
	}
	catch(IOException e){System.out.println("Erro enquanto escrevia "+e);}
    }


    public String Le(){
	String texto="";
	try{
	    int nt=leitor.nextToken();
	    if(nt==leitor.TT_EOL) charespecial=nt;
	    if(nt==leitor.TT_WORD){
	    	charespecial=nt;
			texto=leitor.sval;
	    }
	    else{
			if(nt==leitor.TT_NUMBER){
	    		charespecial=nt;
		    	texto=(new Double(leitor.nval)).toString();
			}
			else if(nt==leitor.TT_EOF) fim=true;
	    }
	}
	catch(IOException e){System.out.println("Erro na leitura : "+e);}
	//System.out.println("Arquivo.Le: fim= "+fim);
	return texto;
    }
    
    public void Fecha()
    {
    	try{arqin.close();arqout.close();}
    	catch(IOException e){System.out.println("Erro para fechar arquivo");}
    }
    
    public static void main(String arg[]){
	Arquivo a=new Arquivo("teste1.txt");
	a.Escritura();
	a.Escreve("Este é um teste \n");
	//a.Escreve("mais um pouco \n");
	System.out.println("O texto escrito e'"+a);
    }
	

}





