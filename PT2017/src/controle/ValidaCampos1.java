/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Programacao Orientada a Objetos - Avancada
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Janeiro a Junho 
 * Aluno Decio Antonio de Carvalho  * 
 */
package controle;


import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author deciodecarvalho
 */
public class ValidaCampos1 {
  
    //private static Date dataAtual;
    private static int ano,mes,dia;
    private static Calendar c;
    /**
     * Construtor
     */   

    public ValidaCampos1() {
    
    }
    
    public static boolean validaStatus (boolean arg0, boolean arg1, boolean arg2) {
    int cont=0;
       
    
        if (arg0){
            cont++;
        }
        
        if (arg1){
            cont++;
        }
        
        if (arg2){
            cont++;
        }
        
        
        if (cont != 1 ){
            return false;
        }
        else{
            return true;
        }
    
    }
    
    public static boolean validaNome (String texto) {
    Pattern p;
    p = Pattern.compile("^[A-Za-zÀ-ü.-_ ]{4,50}$");
    Matcher m = p.matcher(texto);
    return m.find();
    }
    
    public static boolean validaVazio(String texto) {
    Pattern p;
    p = Pattern.compile("[^$]");
    Matcher m = p.matcher(texto);
    return m.find();
    }
    
    public static boolean validaStringNumero(String texto) {
    Pattern p;
    p = Pattern.compile("[^[0-9.,]{1,4}$]");
    Matcher m = p.matcher(texto+"");
    return m.find();
    }
    
    public static boolean validaNumeroVazio(int texto) {
    Pattern p;
    p = Pattern.compile("[^[0-9][0-9][0-9]$]");
    Matcher m = p.matcher(texto+"");
    return m.find();
    }
    
    public static boolean validaVazioComboBox(String uf) {
    Pattern p;
    p = Pattern.compile("[^\\[\\]$]");
    Matcher m = p.matcher(uf);
    return m.find();
    }
    
    public static boolean validaEmail(String email) {
    Pattern p;
    p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    Matcher m = p.matcher(email);
    return m.find();
    }
    
    public static boolean validaRG(String RG) {
    Pattern p;
    //p = Pattern.compile  ("^[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]$");
    p = Pattern.compile("^[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]|[A-Z]$");
    Matcher m = p.matcher(RG);
    return m.find();
    }
    
    public static boolean validaTelefone(String telefone) {
    Pattern p;
    //p = Pattern.compile  ("^[0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]$");
    p = Pattern.compile("^([0-9][0-9])[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9]|[0-9]$");
    Matcher m = p.matcher(telefone);
    return m.find();
    }
    
    public static boolean validaCPF(String CPF) {
    Pattern p;
    p = Pattern.compile("^[0-9][0-9][0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9][0-9]$");
    Matcher m = p.matcher(CPF);
    return m.find();
    }
    public static boolean validaCEP(String CEP) {
    Pattern p;
    p = Pattern.compile("^[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]$");
    Matcher m = p.matcher(CEP);
    return m.find();
    }
   
    public static boolean validaData(String nasc) {
    Pattern p;
    //p = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
    p = Pattern.compile("^[0123][0-9]/[0123][0-9]/[12][0-9][0-9][0-9]$");
    Matcher m = p.matcher(nasc);//System.out.println("O campo nascimento: "+nasc+" é inválido");
    return m.find();
    }
    public static boolean validaDataNascimento(int nasc) {
    boolean x = false;
    int dataIntAtual;
    c = Calendar.getInstance();
    ano = c.get(Calendar.YEAR);
    mes = c.get(Calendar.MONTH)+1;
    dia = c.get(Calendar.DAY_OF_MONTH);
    
    dataIntAtual = ((ano *10000)+(mes *100)+(dia *1));
    
        if(nasc < dataIntAtual){
            x = true;
        }
      
      return x;   
        
    }
    
    
    public static boolean validaHora(String nasc) {
    Pattern p;
    //p = Pattern.compile("^[0-9]{2}:[0-9]{2}/[0-9]{4}$");
    p = Pattern.compile("^[012][0-9]:[012345][0-9]$");
    Matcher m = p.matcher(nasc);//System.out.println("O campo nascimento: "+nasc+" é inválido");
    return m.find();
    }

}
