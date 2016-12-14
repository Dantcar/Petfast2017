/*
 * Este Software tem Objetivo Educacional
 * Para fins de aprendizagem e avaliacao na
 * Na Disciplina de Laboratório de Engenharia
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembro
 * Petfast
 * Aluno Decio Antonio de Carvalho  * 
 */
package controle;
import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.EndCep;




public class Util {
    private static String resultado="<html><font color='Red'size='4'><strong>";
    private static String vdia, vmes, vano;
    private static Date vhoje;
    public static ArrayList<modelo.EndCep> arrayEnd;
    public static String vEnd, vNum, vBairro, vCidade, vUf, vCep, vComplemento;
    /**
     * falta explicar a funcao do temCep
     */
    public static boolean temCep;
    private static ViaCEP vCEP;

    
    public ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Não foi possível encontrar: " + path);
        return null;
    }
}
    
    public static String retiraHora(String arg) {
       //int data;
       //int inicio, fim;
       String horaFormatada=null;
        
        if (!arg.equals(null)){
            //fim = arg.length();
            //inicio = fim-4;
            //data = null;
            
            horaFormatada = arg.substring(11,16);
            //horaFormatada = vhora+":"+vminuto;
            
        } else {
                 horaFormatada = null;
        }
        return horaFormatada;
    }

    public static String retiraPonto(String arg) {
        //arg = arg.replace( " " , ""); //tira espaço em branco
        arg = arg.replace( "." , ""); //tira ponto
         arg = arg.replace( "," , "."); //troca virgula por ponto
        //arg = arg.replace( "/" , ""); //tira barra
        //arg = arg.replace( "-" , ""); //tira hífen
        return arg;
    }
    
    public Util() {
    vCEP = null;
    vEnd = null;
    vNum = null;
    vBairro = null;
    vCidade = null;
    vUf = null;
    vCep = null;
    vComplemento = null;
    }

        
    
    
    
    /**
     *
     * @param arg : Data completa
     * @return String da data no padrão dd/MM/yyyy
     */
    public static String DataFormatada(Date arg){
            String data="";
            vdia = arg.toString().substring(8,10);
            vmes = arg.toString().substring(4,7);
            vano = arg.toString().substring(24,28);
            
            if (vmes.equals("Jan")){
              vmes = "01";   
            }
            if (vmes.equals("Feb")){
              vmes = "02";   
            }
            if (vmes.equals("Mar")){
              vmes = "03";   
            }
            if (vmes.equals("Apr")){
              vmes = "04";   
            }
            if (vmes.equals("May")){
              vmes = "05";   
            }
            if (vmes.equals("Jun")){
              vmes = "06";   
            }
            if (vmes.equals("Jul")){
              vmes = "07";   
            }
            if (vmes.equals("Aug")){
              vmes = "08";   
            }
            if (vmes.equals("Sep")){
              vmes = "09";   
            }
            if (vmes.equals("Oct")){
              vmes = "10";   
            }
            if (vmes.equals("Nov")){
              vmes = "11";   
            }
            if (vmes.equals("Dec")){
              vmes = "12";   
            }
            
            data = vdia+"/"+vmes+"/"+vano;
        return data;
    }
    /**
     * 
     * 
     * @param msg
     * @return 
     */
    public static String reduzString(String msg){
              
        int tamanho = msg.length();
        if (tamanho<80){
        resultado = resultado+ msg;
        }else
       {
       resultado = resultado+msg.substring(0,69)+"\n";
       msg = msg.substring(69,tamanho);
       reduzString(msg);
       }
        return resultado;
    }
    
    public static String myString(String msg){
        int tamanho = msg.length();
        if (tamanho>75){
            msg = "<html><font color='Red'size='3'><strong>"+msg.substring(0, 76)+"</strong></font></html>"+"\n"
                   +"<html><font color='green'size='3'><strong>" +msg.substring(76, tamanho)+"</strong></font></html>";
        
        }
        return msg;
    }
    
    /**
	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
	 * retorna null - para facilitar em casos onde formulários podem ter campos
	 * de datas vazios.
	 * @param data String no formato dd/MM/yyyy a ser formatada
	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
	 * @throws Exception Caso a String esteja no formato errado
	 */
	public static Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
               
                if (!ValidaCampos.validaData(data))
                        return null;
                
                Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
   
     /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios. 
     * @param arg0 String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou Nulo
     * @throws ParseException Caso a String esteja no formato errado
     */    
    public static Date retornaData(String arg0) throws ParseException{
        if (arg0==null|| arg0.equals(""))
            return null;
                       
        Date dt = null;
        
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dt = (java.util.Date)sdf.parse(arg0);
        } catch (ParseException e) {
            throw e;
        }
        return dt;
    }
                
    /**
     * 
     * 
     * @param arg
     * @return 
     */
    public static String DataFormatada1(Date arg){
            String data="";
            vdia = arg.toString().substring(8,10);
            vmes = arg.toString().substring(4,7);
            vano = arg.toString().substring(24,28);
            
            
            
            if (vmes.equals("Jan")){
              vmes = "01";   
            }
            if (vmes.equals("Feb")){
              vmes = "02";   
            }
            if (vmes.equals("Mar")){
              vmes = "03";   
            }
            if (vmes.equals("Apr")){
              vmes = "04";   
            }
            if (vmes.equals("May")){
              vmes = "05";   
            }
            if (vmes.equals("Jun")){
              vmes = "06";   
            }
            if (vmes.equals("Jul")){
              vmes = "07";   
            }
            if (vmes.equals("Aug")){
              vmes = "08";   
            }
            if (vmes.equals("Sep")){
              vmes = "09";   
            }
            if (vmes.equals("Oct")){
              vmes = "10";   
            }
            if (vmes.equals("Nov")){
              vmes = "11";   
            }
            if (vmes.equals("Dec")){
              vmes = "12";   
            }
           // System.out.println(vano +"-->"+arg.toString() );
            
            data = vdia+"/"+vmes+"/"+vano;
        return data;
    }
    
    
    /**
     * 
     * @param arg
     * @return 
     */
    public static String DataFormatadaS(String arg){
             if (!arg.equals(null)){
             } else {
                 return null;
            }
        
            String data;
            int inicio, fim;
            fim = arg.length();
            inicio = fim-4;
            //data = null;
            
            vdia = arg.substring(8,10);
            vmes = arg.substring(4,7);
            vano = arg.substring(inicio,fim);
            //System.out.println(arg+"-"+vmes);
            if (vmes.equals("Jan")){
              vmes = "01";   
            }
            if (vmes.equals("Feb")){
              vmes = "02";   
            }
            if (vmes.equals("Mar")){
              vmes = "03";   
            }
            if (vmes.equals("Apr")){
              vmes = "04";   
            }
            if (vmes.equals("May")){
              vmes = "05";   
            }
            if (vmes.equals("Jun")){
              vmes = "06";   
            }
            if (vmes.equals("Jul")){
              vmes = "07";   
            }
            if (vmes.equals("Aug")){
              vmes = "08";   
            }
            if (vmes.equals("Sep")){
              vmes = "09";   
            }
            if (vmes.equals("Oct")){
              vmes = "10";   
            }
            if (vmes.equals("Nov")){
              vmes = "11";   
            }
            if (vmes.equals("Dec")){
              vmes = "12";   
            }
            
            data = vdia+"/"+vmes+"/"+vano;
        return data;
    }
    
    /**
     * 
     * @param arg
     * @return 
     */
     public static int DtAmericana(String arg){
         int data;
            int inicio, fim;
            fim = arg.length();
            inicio = fim-4;
            //data = null;
            
            vdia = arg.substring(8,10);
            vmes = arg.substring(4,7);
            vano = arg.substring(inicio,fim);
            //System.out.println(arg+"-"+vmes);
            if (vmes.equals("Jan")){
              vmes = "01";   
            }
            if (vmes.equals("Feb")){
              vmes = "02";   
            }
            if (vmes.equals("Mar")){
              vmes = "03";   
            }
            if (vmes.equals("Apr")){
              vmes = "04";   
            }
            if (vmes.equals("May")){
              vmes = "05";   
            }
            if (vmes.equals("Jun")){
              vmes = "06";   
            }
            if (vmes.equals("Jul")){
              vmes = "07";   
            }
            if (vmes.equals("Aug")){
              vmes = "08";   
            }
            if (vmes.equals("Sep")){
              vmes = "09";   
            }
            if (vmes.equals("Oct")){
              vmes = "10";   
            }
            if (vmes.equals("Nov")){
              vmes = "11";   
            }
            if (vmes.equals("Dec")){
              vmes = "12";   
            }
            
            data = Integer.valueOf(vano+vmes+vdia);
         return data;
     }
     
     
     //
     /*
     MÉTODOS PARA TRATAMENTO DE DATAS EM PTBR
     */
     
    /**
     * getDiaSemanaCurto
     * @param date
     * @return Dia da semana em português
     */
    public static String getDiaSemanaCurto(GregorianCalendar date){
	String ret = "";
	
	switch(date.get(Calendar.DAY_OF_WEEK)){
	case Calendar.SUNDAY:
	    ret = ret.concat("Dom");
	    break;
	case Calendar.MONDAY:
	    ret = ret.concat("Seg");
	    break;
	case Calendar.TUESDAY:
	    ret = ret.concat("Ter");
	    break;
	case Calendar.WEDNESDAY:
	    ret = ret.concat("Qua");
	    break;
	case Calendar.THURSDAY:
	    ret = ret.concat("Qui");
	    break;
	case Calendar.FRIDAY:
	    ret = ret.concat("Sex");
	    break;
	case Calendar.SATURDAY:
	    ret = ret.concat("Sab");
	    break;
	default:
	    System.out.println("Error!");
	}
	return ret;
    }
   

    /**
     * getDiaSemanaLongo
     * @param date
     * @return Dia completo da semana em português
     */
    public static String getDiaSemanaLongo(GregorianCalendar date){
	String ret = "";
	
	switch(date.get(Calendar.DAY_OF_WEEK)){
	case Calendar.SUNDAY:
	    ret = ret.concat("Domingo");
	    break;
	case Calendar.MONDAY:
	    ret = ret.concat("Segunda-Feira");
	    break;
	case Calendar.TUESDAY:
	    ret = ret.concat("Terça-Feira");
	    break;
	case Calendar.WEDNESDAY:
	    ret = ret.concat("Quarta-Feira");
	    break;
	case Calendar.THURSDAY:
	    ret = ret.concat("Quinta-Feira");
	    break;
	case Calendar.FRIDAY:
	    ret = ret.concat("Sexta-Feira");
	    break;
	case Calendar.SATURDAY:
	    ret = ret.concat("Sabado");
	    break;
	default:
	    System.out.println("------");
	}
	return ret;
    }
    /**
     * Método que retorna o dia do mês com o formato de dois digitos se o
     * segundo parametro enviado for 'true' 
     * @param date
     * @param two
     * @return dia modificado sempre com dois dias ou sem modificar 
     */
     public static String getDia(GregorianCalendar date, boolean two){
	String ret = "";

	int day = date.get(Calendar.DAY_OF_MONTH);

	if(day < 10 && two){
	    ret = ret.concat("0");
	} else {
	    ret = ret.concat(" ");
	}

	ret = ret.concat((new Integer(day)).toString());

	return ret;
    }
     
     /**
      * Funcão que retorna o mês curto em português
      * @param date
      * @return mês
      */
     public static String getMes(GregorianCalendar date){

	String ret = "";

	switch(date.get(Calendar.MONTH)){
	case Calendar.JANUARY:
	    ret = ret.concat("Jan");
	    break;
	case Calendar.FEBRUARY:
	    ret = ret.concat("Fev");
	    break;
	case Calendar.MARCH:
	    ret = ret.concat("Mar");
	    break;
	case Calendar.APRIL:
	    ret = ret.concat("Abr");
	    break;
	case Calendar.MAY:
	    ret = ret.concat("Mai");
	    break;
	case Calendar.JUNE:
	    ret =ret.concat("Jun");
	    break;
	case Calendar.JULY:
	    ret = ret.concat("Jul");
	    break;
	case Calendar.AUGUST:
	    ret = ret.concat("Ago");
	    break;
	case Calendar.SEPTEMBER:
	    ret = ret.concat("Set");
	    break;
	case Calendar.OCTOBER:
	    ret = ret.concat("Out");
	    break;
	case Calendar.NOVEMBER:
	    ret = ret.concat("Nov");
	    break;
	case Calendar.DECEMBER:
	    ret = ret.concat("Dez");
	    break;
	default:
	    ret = ret.concat("Jan");

	}

	return ret;
    }
     
     /**
      * Funcão que retorna o mês longo em português
      * @param date
      * @return mês
      */
     public static String getMesLongo(GregorianCalendar date){

	String ret = "";

	switch(date.get(Calendar.MONTH)){
	case Calendar.JANUARY:
	    ret = ret.concat("Janeiro");
	    break;
	case Calendar.FEBRUARY:
	    ret = ret.concat("Fevereiro");
	    break;
	case Calendar.MARCH:
	    ret = ret.concat("Março");
	    break;
	case Calendar.APRIL:
	    ret = ret.concat("Abril");
	    break;
	case Calendar.MAY:
	    ret = ret.concat("Maio");
	    break;
	case Calendar.JUNE:
	    ret =ret.concat("Junho");
	    break;
	case Calendar.JULY:
	    ret = ret.concat("Julho");
	    break;
	case Calendar.AUGUST:
	    ret = ret.concat("Agosto");
	    break;
	case Calendar.SEPTEMBER:
	    ret = ret.concat("Setembro");
	    break;
	case Calendar.OCTOBER:
	    ret = ret.concat("Outubro");
	    break;
	case Calendar.NOVEMBER:
	    ret = ret.concat("Novembro");
	    break;
	case Calendar.DECEMBER:
	    ret = ret.concat("Dezembro");
	    break;
	default:
	    ret = ret.concat("Janeiro");

	}

	return ret;
    }
     /**
      * 
      * Método que retorna o ano com 4 digitos se o segundo parametro for 'true'
      * @param date
      * @param four
      * @return 
      */
     public static String getAno(GregorianCalendar date, boolean four){
	String ret = "";

	int year = date.get(Calendar.YEAR);

	if(year < 10){
	    if(four){
		ret = ret.concat("000");
	    } else {
		ret =ret.concat("0");
	    }
	    ret =ret.concat(""+year);
	} else if (year < 100){
	    if(four){
		ret = ret.concat("00"+year);
	    } else {
		ret =ret.concat(""+(year%100));
	    }
	} else if (year < 1000){
	    if(four){
		ret = ret.concat("0"+year);
	    } else {
		ret = ret.concat(""+(year%100));
	    }
	} else {
	    if(four){
		ret = ret.concat(""+year);
	    } else {
		ret = ret.concat(""+(year%100));
	    }
	}

	return ret;
    }
     
     /**
      * 
      * @param date
      * @return 
      */
      public static String getHora(GregorianCalendar date){
	String ret = "";

	int sec = date.get(Calendar.SECOND);
	int min = date.get(Calendar.MINUTE);
	int hour = date.get(Calendar.HOUR_OF_DAY);

	if(hour < 10){
	    ret = ret.concat("0");
	}
	ret = ret.concat(hour+":");

	if(min < 10){
	    ret = ret.concat("0");
	}

	ret = ret.concat(min+":");

	if(sec < 10){
	    ret = ret.concat("0");
	}

	ret = ret.concat(""+sec);

	return ret;
    }
     
      /**
       * retorna 'Fri, 05 Jul 1963 08:49:37 GMT'
       * @param date
       * @return 'Sex, 05 Jul 1963 08:49: GMT'
       */
     public static String dateRFC1123PTBR(GregorianCalendar date){
	
	String ret = "";

	/* Date */
	ret = ret.concat(Util.getDiaSemanaCurto(date)+", ");
	ret = ret.concat(Util.getDia(date, true)+" ");
	ret = ret.concat(Util.getMes(date)+" ");
	ret = ret.concat(Util.getAno(date, true)+" ");

	/* Time */
	ret = ret.concat(Util.getHora(date)+" GMT");

	return ret;
    }
     
     /**
      * 
      * @param date
      * @return 'Sexta-Feira, 05-Jul-63 08:49:37 GMT'
      */
     public static String dateRFC850PTBR(GregorianCalendar date){

	String ret = "";

	ret = ret.concat(Util.getDiaSemanaLongo(date)+", ");
	ret = ret.concat(Util.getDia(date, true)+"-");
	ret = ret.concat(Util.getMes(date)+"-");
	ret = ret.concat(Util.getAno(date, false)+" ");
	
	ret = ret.concat(Util.getHora(date)+" GMT");

	return ret;
    }
     
     /**
     * 
     * @param date
     * @return 
     */
    public static String dateAsctimePTBR(GregorianCalendar date){

	String ret = "";

	ret = ret.concat(Util.getDiaSemanaCurto(date)+" ");
	ret = ret.concat(Util.getMes(date)+" ");
	ret = ret.concat(Util.getDia(date, false)+" ");

	ret = ret.concat(Util.getHora(date)+" ");
	ret = ret.concat(Util.getAno(date, true));

	return ret;
    }

     
     
     
     
     
     public static ArrayList retornaEndereco(String cep){
       EndCep objEndCep;
       objEndCep = new EndCep();  
       Pattern pattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
       Matcher matcher = pattern.matcher(cep);
       String msg="";
       if (matcher.find()) {
            try {
                if (vCEP == null) {
                    vCEP = new ViaCEP(cep);
                } else {
                    if (!vCEP.getCep().equals(cep)) {
                        vCEP.buscar(cep);
                    } else {
                        JOptionPane.showMessageDialog(null, "O CEP já foi consultado, favor informar um CEP diferente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
                    }
                } 
                msg = msg + "Gia: " + vCEP.getGia() +"\n";
               
                msg = msg + "Ibge: "+ vCEP.getIbge() + "\n";
                
                objEndCep.setEndereco(vCEP.getLogradouro());
                msg = msg + "Endereço: " + vCEP.getLogradouro() +"\n";
                
                objEndCep.setBairro(vCEP.getBairro());
                msg = msg + "Bairro: " + vCEP.getBairro() +"\n";
                
                objEndCep.setComplemento(vCEP.getComplemento());
                msg = msg + "Complemento: " + vCEP.getComplemento() +"\n";
                
                objEndCep.setCidade(vCEP.getLocalidade());
                msg = msg + "Localidade: " + vCEP.getLocalidade() +"\n";
                
                objEndCep.setUf(vCEP.getUf());
                vUf = vCEP.getUf();
               
                msg = msg + "UF: " + vCEP.getUf() +"\n";
                
                
            } catch (ViaCEPException ex) {
                String titulo = (ex.hasCEP() ? "Erro ao buscar o CEP " + ex.getCEP() + "!" : "Ocorreu um erro!");
                
                JOptionPane.showMessageDialog(null, ex.getMessage(), titulo, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Favor informar um Cep válido!", "Aviso!", JOptionPane.WARNING_MESSAGE);
        }
       JOptionPane.showMessageDialog(null, "Funcionou!"+"\n"+msg, "Aviso!", JOptionPane.WARNING_MESSAGE);        
       
       
       return arrayEnd;
     }
     
     public static boolean VerificaEndereco(String cep){
       Pattern pattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
       Matcher matcher = pattern.matcher(cep);
       String msg="";
       if (matcher.find()) {
            try {
                if (vCEP == null) {
                    vCEP = new ViaCEP(cep);
                } else {
                    if (!vCEP.getCep().equals(cep)) {
                        vCEP.buscar(cep);
                    } 
                } 
               
               //msg = msg + "Endereço: " + vCEP.getLogradouro() +"\n";
               //msg = msg + "Bairro: " + vCEP.getBairro() +"\n"; 
               //msg = msg + "Localidade: " + vCEP.getLocalidade() +"\n"; 
               //msg = msg + "UF: " + vCEP.getUf() +"\n"; 
                
                vEnd = vCEP.getLogradouro();
                vBairro = vCEP.getBairro();            
                vCidade = vCEP.getLocalidade();
                vUf = vCEP.getUf();
                  
            } catch (ViaCEPException ex) {
                String titulo = (ex.hasCEP() ? "Erro ao buscar o CEP " + ex.getCEP() + "!" : "Ocorreu um erro!");
                JOptionPane.showMessageDialog(null, ex.getMessage(), titulo, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "<html><font face="+"Georgia "+"size="+"5 "+"color="+"Purple"+">Favor informar um Cep válido!</font></html>", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
       }
     
       // JOptionPane.showMessageDialog(null, "Funcionou!"+"\n"+msg, "Aviso!", JOptionPane.WARNING_MESSAGE);        
       
       return true;
     }
     
     
}


