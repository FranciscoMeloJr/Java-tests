 	

public class TextoPL {
   
    public static void main(String[] args) {
       String texto;
       Arquivo text = new Arquivo("result/savePL.txt");
       text.Escritura();
       String a="";
       
       System.out.println(incluiTitle(a));
       text.Escreve(incluiTitle(a));
       //text.Escreve(incluiTitle("alexandre"));
       
       
    }
	public static  String incluiTitle(String text){
   	
		String texto = "set grid;\nset xrange [0:];\nset size 1,1;\n#set logscale x;\n#set xrange[1:];\nset multiplot;\nset size 0.5,0.5;\nset origin 0,0.5;\nset key right bottom;\nload \"plot_afinidade.txt\" ;\nset size 0.5,0.5;\nset origin 0,0;\n#set ylabel \"ENTROPIA [BIT]\";\n	set key right bottom;\nload \"plot_entropia.txt\" ;\nset size 0.5,0.5;\nset origin 0.5,0.5;\nset ylabel \"\";\nset key right bottom;\n#set logscale x;\nload \"plot_qde.txt\";\nset size 0.5,0.5;\nset origin 0.5,0;\nset ylabel \"\";\nset key left top;\nunset logscale;\nload \"plot_tam.txt\";\nunset multiplot";

		return texto;
	}
}

