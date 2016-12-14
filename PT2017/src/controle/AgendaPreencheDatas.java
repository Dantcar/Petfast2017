package controle;

// Class that contains methods for Database manipulation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class AgendaPreencheDatas {

    public static String local;

    /**
     * Metodo que le um arquivo txt com uma relacao textos separados com ';'e
     * retorna um array cada um destes textos entre ';' para um Array
     *
     * @param filename nome do arquivo que contem o array de assentos
     * @return
     */
    // Method for returning an array of the available seats, for passing into the Main class
    public static ArrayList<String> RetornaListaArquivoTxt(String filename) {
        ArrayList<String> temp = new ArrayList<>();
        String msg = "";
        local = ""; //diretorio ondes serao gravados/lidos os arquivos gerados pelo gerenciador de assentos.
        @SuppressWarnings("UnusedAssignment")
        String name = filename;
        name = local + filename;
        String arquivoSelecionado = retornaNomeArquivo(name);
        File arquivoTxtSelecionado = new File(arquivoSelecionado);

        if (arquivoTxtSelecionado.exists()) // msg = "Encontrei Arquivo "+arquivoSelecionado + "\n";
        {

            try {

                FileInputStream fs = new FileInputStream(arquivoTxtSelecionado.toString());
                try (DataInputStream in = new DataInputStream(fs)) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String stringLine;

                    while ((stringLine = br.readLine()) != null) {
                        String[] array = stringLine.split(";");

                        // For every object in the array, built from items in the text file
                        for (String texto : array) {
                            // Add Item to arraylist to be rerturned
                            temp.add(texto + "");
                        }
                    }

                    in.close();
                    // msg = msg + "Array gerado com sucesso! \n";
                }
            } catch (Exception ex) {
                msg = msg + "Erro 1 Metodo - RetornaListaArquivoTxt()\n";
                msg = msg + ex + "\n";
                System.err.println(ex.getMessage());
            } finally {
                if (!msg.equals("")) {
                    JOptionPane.showMessageDialog(null, msg);
                }

            }

        }
        return temp;
    }

    /**
     * metodo que gera um arquivo txt a lista de assentos de um voo.
     *
     *
     * @param filename
     * @param vtexto
     */
    @SuppressWarnings("empty-statement")
    public static void GeraNovaBaseTextos(String filename, String[] vtexto) {
        // Nome da base de dados (calculada pelo metodo 'retornaNomeArquivo')
        local = ""; //diretorio ondes serao gravados/lidos os arquivos gerados pelo gerenciador de assentos.
        String name = filename;
        name = local + filename;
        //String vtexto ="teste";
        String msg = "";
        // Get ArrayList contendo valores para cada assento da aeronave
        ArrayList<String> input = criaArrayListString(vtexto); //cria um arrayList com um Array de String fornecida.

        // Nome da base de dados
        String textoSelecionado = retornaNomeArquivo(name);

        File arquivoTextoSelecionado = new File(textoSelecionado);

        try {
            // if the file exists, do not create a new file (leave existing file alone)
            if (arquivoTextoSelecionado.exists() == true) {
                // msg = "Arquivo: "+textoSelecionado+" ja¡ existe" +"\n";
                return;
            }

        } catch (Exception ex) {
            msg = msg + "Erro 2 Metodo - GeraNovaBaseTextos() 1 \n";
            msg = msg + ex + "\n";
            System.err.println(ex.getMessage());
        } finally {
            if (!msg.equals("")) {

                JOptionPane.showMessageDialog(null, msg);
            }
        }
        // if the file doesnt exist..

        try {

            arquivoTextoSelecionado.createNewFile(); // cria um novo arquivo com o nome selecionado

            // Inicio de dependencias para leitura do arquivo.
            FileInputStream fs = new FileInputStream(arquivoTextoSelecionado.toString());
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // Inicio de dependencias para gravacao do arquivo.
            String stringLine;
            // Write a ; to the file (this is needed to add some content to replace)
            try (BufferedWriter fw1 = new BufferedWriter(new FileWriter(textoSelecionado))) {
                // Write a ; to the file (this is needed to add some content to replace)
                fw1.write(";");
                // Close this write dependancy
            }

            // While there are Lines left to be read
            while ((stringLine = br.readLine()) != null) {
                try ( // Create dependencies for writing to same file
                        BufferedWriter fw = new BufferedWriter(new FileWriter(textoSelecionado))) {
                    int x = 0;
                    // Iterate through the new edited array (orginal array minus selected seat)
                    while (x < input.size()) {
                        // Rewrite every line of the text file with each entry in the new array
                        String line = input.get(x);
                        fw.write(line + ";");
                        x++;
                    }
                }
            }
            // msg = "Arquivo: "+textoSelecionado+" criado com sucesso!" + "\n";

        } catch (Exception ex) {
            msg = msg + "Erro 3 Metodo - GeraNovaBaseTextos() 2 \n";
            msg = msg + ex + "\n";
            System.err.println(ex.getMessage());
        } finally {
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg);
            }

        }

    }//Fim metodo GeraNovaBaseTextos().

    /*

     public static void main (String[] args)     // Main Method Declaration
     {
     ArrayList arrayTxt;
     ArrayList arrayNovo;
     arrayTxt = criaNumeroAssento(10, 20, 30);  // Criar assentos conforme os parametros classe economica, empresarial e primeira classe
     System.out.println(retornaNomeArquivo("Voo-1041-Data-10082016-Hora-2030"));
     GeraNovaBaseAssentos("Voo", e, b, f);

     arrayNovo = RetornaAssentosDisponiveisVoo("Voo");
     //System.out.println("inicio: \n"+arrayTxt.get(0).toString());
     System.out.print("inicio\n"+Arrays.toString( arrayNovo.toArray() )+"fim\n");
     }

     */
    /**
     * Metodo que retorna o nome para um arquivo txt que devera¡ ser utilizado
     * para preenchimento dos assentos do referido voo
     *
     * @param input
     * @return
     * @
     */
    // Method that takes a String (from the film time cb)
    // And returns a String of the name of the text file it belongs to
    public static String retornaNomeArquivo(String input) {
        String arquivoVooDescricao = input;

        arquivoVooDescricao = arquivoVooDescricao + ".txt";

        return arquivoVooDescricao;
    }//fim metodo retornaNomeArquivo().

    /**
     * Metodo para criar uma base de dados com todos os numeros de assentos
     * disponiveis.
     *
     * @param nE
     * @param nB
     * @param nP
     * @return
     */
    public static ArrayList<String> criaNumeroAssento(Integer nE, Integer nB, Integer nP) {
        // Variables to values for each block of seats
        int A = 0;
        int B = 0;
        int C = 0;
        //VariÃ¡veis para criacao de assentos no formato '0001'.
        String Am;
        String Bm;
        String Cm;
        Am = "";
        Bm = "";
        Cm = "";

        String D, E, F;
        D = "";
        E = "";
        F = "";
        int totalAssentos = nP + nB + nE;

        // ArrayList to hold the values
        ArrayList<String> a1S;
        a1S = new ArrayList<>();
        ArrayList<Integer> a1;
        a1 = new ArrayList<>();

        // Add zero at the start of the array to act as a defauilt value for the cbs
        //a1.add(0);
        // Calculate the seatnumbers and add them into the array
        for (int i = 0; i < nE; i++) {
            A = 1 + i;
            Am = ((A + 1000) + "").substring(1);
            a1.add(A);
            a1S.add("E" + Am);
        }

        //B = A;
        for (int i = 0; i < nB; i++) {
            B = nE + 1 + i;
            Bm = ((B + 1000) + "").substring(1);
            a1S.add("B" + Bm);
        }

        C = B + 1;
        for (int i = 0; i < nP; i++) {
            C = nE + nB + 1 + i;
            Cm = ((C + 1000) + "").substring(1);
            a1S.add("P" + Cm);
        }
        //System.out.print(Arrays.toString( a1S.toArray() )+"\n"); 
        //metodo 'toString' da classe Arrays que retorna uma String com os elementos de um Array
        //System.out.println(a1S.size());
        int contador = 0;

        for (String i : a1S) {

            System.out.print(i);
            contador = contador + 1;
            //System.out.print("contador:"+contador+" ");
            if (contador < a1S.size()) {

                System.out.print(", ");
            } else {
                System.out.print("\n");
            }
        }

        return a1S;
    }// fim metodo criaNumeroAssento().

    /**
     * metodo que gera um arquivo txt a lista de assentos de um voo.
     *
     * @param filename
     * @param E // quantidade de assentos classe Economica.
     * @param B // quantidade de assentos classe Bussines.
     * @param P // quantidade de assentos classe primeira. Metodo que gera uma
     * nova base de dados txt para ser utilizada no controle de assentos das
     * aeronaves do Sistema Aerofast.
     *
     *
     */
    public static void GeraNovaBaseAssentos(String filename, Integer E, Integer B, Integer P) {
        // Nome da base de dados (calculada pelo metodo 'retornaNomeArquivo')
        local = ""; //diretorio ondes serao gravados/lidos os arquivos gerados pelo gerenciador de agenda.
        String name = filename;
        name = local + filename;
        String msg = "";
        // Get ArrayList contendo valores para cada assento da aeronave
        ArrayList<String> input = criaNumeroAssento(E, B, P); //criaNumeroAssento(Integer nE, Integer nB, Integer nP)

        // Nome da base de dados
        //String agendaSelecionada = retornaNomeArquivo(name);
        String agendaSelecionada = name;

        File arquivoVooSelecionado = new File(agendaSelecionada);

        try {
            // if the file exists, do not create a new file (leave existing file alone)
            if (arquivoVooSelecionado.exists() == true) {
                //   msg = "Arquivo: "+agendaSelecionada+" ja existe" +"\n";
                return;
            }

        } catch (Exception ex) {
            msg = msg + "Erro 4 Metodo - GeraNovaBaseAssentos() \n";
            msg = msg + ex + "\n";
            System.err.println(ex.getMessage());
        } finally {
            if (!msg.equals("")) {

                JOptionPane.showMessageDialog(null, msg);
            }
        }
        // if the file doesnt exist..
        try {

            arquivoVooSelecionado.createNewFile(); // cria um novo arquivo com o nome selecionado

            // Inicio de dependencias para leitura do arquivo.
            FileInputStream fs = new FileInputStream(arquivoVooSelecionado.toString());
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // Inicio de dependencias para gravacao do arquivo.
            String stringLine;
            // Write a ; to the file (this is needed to add some content to replace)
            try (BufferedWriter fw1 = new BufferedWriter(new FileWriter(agendaSelecionada))) {
                // Write a ; to the file (this is needed to add some content to replace)
                fw1.write(";");
                // Close this write dependancy
            }

            // While there are Lines left to be read
            while ((stringLine = br.readLine()) != null) {
                try ( // Create dependencies for writing to same file
                        BufferedWriter fw = new BufferedWriter(new FileWriter(agendaSelecionada))) {
                    int x = 0;
                    // Iterate through the new edited array (orginal array minus selected seat)
                    while (x < input.size()) {
                        // Rewrite every line of the text file with each entry in the new array
                        String line = input.get(x);
                        fw.write(line + ";");
                        x++;
                    }
                    //Close the file writing dependency
                }
            }
            msg = "Arquivo: " + agendaSelecionada + " criado com sucesso!" + "\n";

        } catch (Exception ex) {
            msg = msg + "Erro 5 Metodo - GeraNovaBaseAssentos()\n";
            msg = msg + ex + "\n";
            System.err.println(ex.getMessage());
        } finally {
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg);
            }

        }

    }//Fim metodo GeraNovaBaseAssentos().

    /**
     * Metodo que le um arquivo txt com uma relacao de assentos e retorna um
     * array com estes assentos
     *
     * @param filename nome do arquivo que contem o array de assentos
     * @return
     */
    // Method for returning an array of the available seats, for passing into the Main class
    public static ArrayList<String> RetornaBoxDisponiveisAgenda(String filename) {
        ArrayList<String> temp = new ArrayList<>();
        String msg = "";
        local = ""; //diretorio ondes serao gravados/lidos os arquivos gerados pelo gerenciador de assentos.
        String name = filename;
        name = local + filename;
        String agendaSelecionada = retornaNomeArquivo(name);
        File arquivoVooSelecionado = new File(agendaSelecionada);

        if (arquivoVooSelecionado.exists()) {
           // msg = "Encontrei Arquivo " + agendaSelecionada + "\n";

            try {
                FileInputStream fs = new FileInputStream(arquivoVooSelecionado.toString());
                try (DataInputStream in = new DataInputStream(fs)) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String stringLine;

                    while ((stringLine = br.readLine()) != null) {
                        String[] array = stringLine.split(";");

                        // For every object in the array, built from items in the text file
                        for (String assento : array) {
                            // Add Item to arraylist to be rerturned
                            temp.add(assento + "");

                            // System.out.println(assento+"\n");
                        }
                    }
                }
            //    msg = msg + "Array gerado com sucesso! \n";
            } catch (Exception ex) {
                msg = msg + "Erro  6 Metodo - RetornaAssentosDisponiveisVoo()\n";
                msg = msg + ex + "\n";
                System.err.println(ex.getMessage());
            } finally {

                if (!msg.equals("")) {
                    JOptionPane.showMessageDialog(null, msg);

                }

            }

        }

        return temp;
    }

    /**
     * Metodo para transformar um array String em ArrayList
     *
     * @param filename
     * @return ArrayList
     *
     */
    public static ArrayList<String> criaArrayListString(String[] filename) {
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(Arrays.asList(filename));

        return temp;
    }

    // ArrayList <String> input = criaArrayListString(vtexto); //cria um arrayList com um Array de String fornecida.
    /**
     * Metodo para calcular o numero de linhas com a entrada de informacoes de
     * total assentos e total de colunas requeridas obs: confere se o divisor
     * nao esta com valor zero para evitar mensagem de erros
     *
     * @param total - numero total de assentos.
     * @param col - numero de colunas requeridas.
     * @return
     */
    public static int calcRow(int total, int col) {
        int vRow = 0;
        if (col != 0) {
            vRow = (total / col);

            if ((total % col) != 0) {
                vRow = vRow + 1;
            }
        }
        return vRow;
    }

    /**
     * Metodo para calcular o numero de colunas com a entrada de informacoes de
     * total assentos e total de linhas requeridas obs: confere se o divisor nao
     * esta com valor zero para evitar mensagem de erros
     *
     * @param total - numero total de assentos.
     * @param row - numero de colunas requeridas.
     * @return
     */
    public static int calcCol(int total, int row) {
        int vCol = 0;

        if (row != 0) {
            vCol = (total / row);

            if ((total % row) != 0) {
                vCol = vCol + 1;
            }
        }
        return vCol;
    }

    /**
     * Metodo que le um arquivo txt com uma relacao de assentos e retorna uma
     * Sting com estes assentos
     *
     * @param filename nome do arquivo que contem o array de assentos
     * @return
     */
    // Method for returning an array of the available seats, for passing into the Main class
    public static String RetornaStringArquivoVoo(String filename) {
        String temp;
        String var = "{--;";
        String msg = "";
        local = ""; //diretorio ondes serao gravados/lidos os arquivos gerados pelo gerenciador de assentos.
        String name = filename;
        name = local + filename;
        //String agendaSelecionada = retornaNomeArquivo(name);
        File arquivoVooSelecionado = new File(name);

        if (arquivoVooSelecionado.exists()) //  msg = "Encontrei Arquivo "+ name + "\n";
        {

            try {

                FileInputStream fs = new FileInputStream(name);
                try (DataInputStream in = new DataInputStream(fs)) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String stringLine;

                    while (br.readLine() != null) {

                        var = ";" + var + br.readLine() + "";

                    }
                    var = var + "}";
                    in.close();
                   // msg = msg + "String[] temp gerado com sucesso! \n"; //comentar depois
                }
            } catch (Exception ex) {
                msg = msg + "Erro 7 no Metodo - RetornaStringArquivoVoo()\n";
                msg = msg + ex + "\n";
                System.err.println(ex.getMessage());
            } finally {
                if (!msg.equals("")) {
                    JOptionPane.showMessageDialog(null, msg);
                }

            }

        }
        temp = var;
        return temp;
    }
}
