package visao;

// TelaMostraAgenda Class File
//import java.awt.event.AdjustmentEvent;
//import java.awt.event.AdjustmentListener;
import controle.AgendaPreencheDatas;
import controle.Util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaMostraAgenda extends JFrame implements ActionListener {

    AgendaPreencheDatas util = new AgendaPreencheDatas();
    Util cUtil = new Util();
    
    //String
    private String local;
    private String filename = "agendaHoje";
    private String tipoFile = "txt";
    private String name = "";
    //C:\Documents and Settings\deciodecarvalho\Meus documentos\Google Drive\projetosJava\PetFast\petFast\Icones
    //C:\Documents and Settings\deciodecarvalho\Meus documentos\Google Drive\projetosJava\PetFast\petFastIcones\clientePet.jpg
    private String url = System.getProperty("user.dir") + "\\Icones\\pensandoRed.png";
    private java.net.URL imgURL = getClass().getResource(url);
    
     //lista do combox de Datas
    public String listaDatas[] = {"-----------", "2016-out-19", "2016-out-20", "2016-out-21", "2016-out-22", "2016-out-23"}; //pegar da agenda as datas incluidas
    //lista do combox de Horas
    public String listaHoras[] = {"-----", "09:00",
        "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
        "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"
    }; //pegar da agenda as datas incluidas
    
    private String msg = "";
    // Creates an icon, attached to a label to act as a banner  for the program
    // Get resource is required for finding the image within the JAR achive once packed

    
    
    //final public ImageIcon icon = createImageIcon(url);
    // Array para a quantidade agendamentos defini como um único
    Integer[] listaQuantidadeClientePets = {0, 1};

    //========= Elementos Gráficos da GUI =========
    //JComboBox
    
    JComboBox cbxBoxProfissional = new JComboBox();//combo box dos boxes/profissionais disponíveis no dia/hora da agenda
    public JComboBox cbxDataReservaCombo = new JComboBox(listaDatas); 
    public JComboBox cbxHoraReservaCombo = new JComboBox(listaHoras);
    // Comboboxes to hold the state of the desired quantity of each ticket type
    
    JComboBox cbxClientePetQuantidade = new JComboBox(listaQuantidadeClientePets);
    
    ImageIcon icon = cUtil.createImageIcon(url,
                                 "Cliente Petfast");
    
    //JLabel
    JLabel iconHolder = new JLabel(icon);
    JLabel lblDataAgendamento = new JLabel("Dia: ");
    JLabel lblHoraAgendamento = new JLabel("Horario: ");
    JLabel lblTipoDocumento = new JLabel("Agenda");
    JLabel lblServicoPrecoTotal = new JLabel("Preco Total: ");
    JLabel lblQuantidadeServicos = new JLabel("Total dos Serviços: ");
    JLabel lblClientePet = new JLabel("ClientePet");

    //Labels dos boxes/profissionais disponíveis no dia/hora da agenda
    JLabel lblAssento01 = new JLabel("Escolher o profissional / Box ");

    //JTextField
    JTextField tftServicoQuantidade = new JTextField(1);
    JTextField tftTipoCusto = new JTextField(4);
    JInternalFrame jintP0 = new JInternalFrame();

    // Button
    JButton btnReservaServico = new JButton("Reserva");
    JButton btnAgendaServico = new JButton("Agendar Serviço na Data");
    JButton resetButton = new JButton("Delete DB");

   

    
    //fundacao321@ wifi seade
    // ArrayList that holds the vaules of seats that are available
    ArrayList<String> seatArrayList = new ArrayList<>();

    String agenda = new String();

    //Creation of JPanels to be added to the frame
    JPanel bannerPanel = new JPanel();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();

    //Criando as barras de rolagem hbar e vbar
    //JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL);
    //JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL);
    // hbar.setUnitIncrement(2);
    // hbar.setBlockIncrement(1);
    //Dados para formatacao do layout dos blocos de assentos na aeronave.
    public boolean coluna = false;
    public int e;
    public int b;
    public int f;

    public int e_param;
    public int b_param;
    public int f_param;

    public int ecol;
    public int bcol;
    public int fcol;

    public int erow;
    public int brow;
    public int frow;

    public int ex;
    public int bx;
    public int fx;

    private int controleData = 0; //verificador se existe data completa data + hora

       
    /**
     *
     * Metodo construtor TelaMostraAssentos
     */
    public TelaMostraAgenda() {
           
        
        System.out.println(url);
        this.local = "";

        cbxDataReservaCombo = new JComboBox(listaDatas);
        cbxHoraReservaCombo = new JComboBox(listaHoras);
    }

    /**
     * Metodo para criar tela com a data e horário desejado.
     *
     * @param tipoLayout
     * @param ve
     * @param vb
     * @param vf
     * @param ex
     * @param bx
     * @param fx
     *
     */
    public TelaMostraAgenda(boolean tipoLayout, int ve, int vb, int vf, int ex, int bx, int fx) // Metodo construtor para a interface (GUI)
    {
        System.out.println(url);
        this.local = "";
        //GroupLayout layout = new GroupLayout(this);
        //FlowLayout layout;
        // layout = new FlowLayout();
        //Dados para formatacao do layout dos blocos de assentos na aeronave.
        coluna = tipoLayout;
        e = ve;
        b = vb;
        f = vf;

        e_param = ex;
        b_param = bx;
        f_param = fx;

        ecol = ex;
        bcol = bx;
        fcol = fx;

        erow = ex;
        brow = bx;
        frow = fx;

        //setLayout(layout);
        setLocationRelativeTo(null);     // Centers the Frame (NOTE: Multi-monitor setups may not center correctly depending on collective resolution

        setTitle("Reserva de Serviços Petfast versao 0.1"); // Set Title of Main Window
        setSize(800, 800);                               // Set frame resolution to [x,y] pixels
        //setResizable(false);                           // Keeps Frame a constant resolution (Stops Resizing of Frame by user)
        setResizable(true);
        setLocation(100, 100);                          //
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // Set frame to exit when 'CLOSE' window button is clicked

        // Add Panels to the Frame and state Layout Manager constructor arguments
        add(bannerPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.EAST);
        add(p2, BorderLayout.WEST);
        add(p3, BorderLayout.SOUTH);
        // add(p4, BorderLayout.SOUTH);

        // Addition of Content to respective Panel (Order determines position within panel)
        // bannerPanel.add(iconHolder); //icone recolocar
        bannerPanel.add(lblQuantidadeServicos);
        bannerPanel.add(tftServicoQuantidade);
        bannerPanel.add(lblServicoPrecoTotal);
        bannerPanel.add(tftTipoCusto);

        p1.add(resetButton); //painel 1 EAST

        //incluindo comboBox DATA
        p2.add(lblDataAgendamento); //painel 2 WEST
        p2.add(cbxDataReservaCombo); //painel 2 WEST
        //incluindo comboBox Hora
        p2.add(lblHoraAgendamento); //painel 2 WEST
        p2.add(cbxHoraReservaCombo); //painel 2 WEST
        //incluindo comboBoxQuantidade de agendamentos serviços
        p2.add(lblClientePet); //painel 2 WEST
        p2.add(cbxClientePetQuantidade);  //painel 2 WEST
        //incluindo botao para agendar evento
        p2.add(btnAgendaServico); // painel 2 WEST

        // Set number of visible entries when a combobox is selected
        cbxClientePetQuantidade.setMaximumRowCount(3);

        cbxDataReservaCombo.setMaximumRowCount(3);
        cbxHoraReservaCombo.setMaximumRowCount(3);

        cbxBoxProfissional.setMaximumRowCount(3);

        // Makes textfields non-editable, so that they can be used to display content
        tftServicoQuantidade.setEditable(false);
        tftTipoCusto.setEditable(false);

        // Addition of Action Listeners to Objects
        cbxHoraReservaCombo.addActionListener(this);
        cbxClientePetQuantidade.addActionListener(this);
        btnReservaServico.addActionListener(this);
        resetButton.addActionListener(this);
        cbxBoxProfissional.addActionListener(this);

        setVisible(true);   // Set frame to be Visible, thus updating frame with all the selected elements
        repaint();
    }

    /**
     * Metodo para interacao com as acoes da tela
     *
     * A C T I O N - E V E N T
     *
     * @param action
     */
    //STARTOF ACTIONEVENTS
    @Override
    public void actionPerformed(ActionEvent action) // Method that contain all conditions where an ActionEvent is needed
    {
        /*
         if(action.getSource() == p1 ||
         action.getSource() == p2 ||
         action.getSource() == p3){
         repaint();
         */

        String msg = "";
        // ActionListener Combobox reserva hora

        if (action.getSource() == cbxHoraReservaCombo) {
            if (cbxHoraReservaCombo.isValid()) {
                controleData = controleData + 1;
            }
        }

        if (action.getSource() == cbxDataReservaCombo) {
            if (cbxDataReservaCombo.isValid()) {
                controleData = controleData + 2;
            }
        }

        System.out.println("Valor de controleData: " + controleData);

        if (action.getSource() == cbxHoraReservaCombo) {
            if ((cbxDataReservaCombo.getSelectedItem().toString() != "-----------")) {

                if ((cbxHoraReservaCombo.getSelectedItem().toString() != "-----")) {

                    // Create New Instance of the Database class
                    AgendaPreencheDatas db = new AgendaPreencheDatas();

                    // Get Nome da Base a ser utilizada
                    String dataHoraSelecionada = cbxDataReservaCombo.getSelectedItem().toString() + "-"
                            + cbxHoraReservaCombo.getSelectedItem().toString();

                    System.out.println("Ponto verificado 1 - " + dataHoraSelecionada);

                    // Make Name of Database global
                    agenda = dataHoraSelecionada + ".txt";

                // Call DataBase Generator (will generate fresh database for that time if one does not  ist)
                    //db.FullDataBaseGeneration(dataHoraSelecionada);
                    //db.GeraNovaBaseAssentos(dataHoraSelecionada, 40, 30, 20);
                    AgendaPreencheDatas.GeraNovaBaseAssentos(agenda, e, b, f);

                //Fetch array of available seats and pass it to the global ArrayList 'seatArrayList'
                    //ArrayList<Integer> agendaArray  = db.AvailableAssentosArrayReturn(dataHoraSelecionada);
                    ArrayList<String> agendaArray = AgendaPreencheDatas.RetornaBoxDisponiveisAgenda(dataHoraSelecionada);
                    seatArrayList = agendaArray;

                    //Reset any user selection of tickets when a new database is selected
                    cbxClientePetQuantidade.setSelectedIndex(0);

                //Repaint the Frame
                }

            }

            repaint();
        }

        // ActionListener for all ticket type comboboxes collectively
        if (action.getSource() == cbxClientePetQuantidade) //|| action.getSource()
        // == cbxCriancaQuantidade|| action.getSource() == cbxIdosoQuantidade)
        {
            // Remove all existing items from each cb
            cbxBoxProfissional.removeAllItems();

            // If the arraylist no longer contains a zero (default answer)
            // Add a zero at the beginning of the array
            if ("000".equals(seatArrayList.get(0))) {

            } else {
                seatArrayList.add(0, "000");
            }

            // Add contents of the ArrayList to each combobox that display available seats
            //estudar sobre stream e map ->
            seatArrayList.stream().map((seatArrayList1) -> {
                cbxBoxProfissional.addItem(seatArrayList1);
                return seatArrayList1;
            }).forEach((seatArrayList1) -> {
                //cbxBoxProfissional.addItem(seatArrayList1);
            });

            // Get new total price as a String and affix to a Label for display on Frame
            String totalString = getTotal();
            tftTipoCusto.setText("R$ " + totalString);

            //Calculo total quantidade de agendamentos e e preenche a lbl na frame
            Integer adultCounter = Integer.parseInt((cbxClientePetQuantidade.getSelectedItem().toString()));

            Integer countTotal = adultCounter;//+ childCounter + oapCounter);
            tftServicoQuantidade.setText(countTotal.toString());

            repaint();

            p3.add(lblAssento01);
            p3.add(cbxBoxProfissional);

            // Only 5 tickets can be ordered at one time. Returns error message if more are selected
            if (countTotal > 5) {
                cbxClientePetQuantidade.setSelectedIndex(0);
                tooManyTickets();
                return;
            }

            // Following 6 if statements state what labels/comboboxes should show when the ticket number changes:
            // E.G. if no tickets are selected then no labels/cbs show
            // if 3 tickets are chosen, then the labels/cbs for Ticket 1,2 and 3 are shown
            // if 1 ticket is then chosen, cb/label for Ticket 2 and 3 dissapear by 1 stays
            if (countTotal == 0) {
                p3.remove(lblAssento01);
                p3.remove(cbxBoxProfissional);
                p3.remove(btnReservaServico);
            }

            // if more tickets than available seats remaining is selected
            // Then an error message states this face
            if (countTotal > seatArrayList.size() - 1) {
                notEnoughAssentos();
                return;
            }

            // if no tickets are selected, then the 'order' button does not appear
            if (countTotal > 0) {
                p3.add(btnReservaServico);
            }

            // repaint();
            setVisible(true);
        }

        // ActionListener for btnReservaServico
        if (action.getSource() == btnReservaServico) {

            // Validation for purchase of ticket(s)
            Integer orderConfirm = JOptionPane.showConfirmDialog(getContentPane(),
                    "Voce confirma este agendamento?",
                    "Confirmar agendamento Servico?",
                    JOptionPane.YES_NO_OPTION);
            if (orderConfirm == 1) {
                return;
            }

            // Get the Values of Each Ticket Quantity ComboBox (e.g. 102, 301, etc)..
            String seat1Store = cbxBoxProfissional.getSelectedItem().toString();

            //Create an array to hold theese values
            Integer[] proceedArray = new Integer[5];
            proceedArray[0] = Integer.parseInt(seat1Store.substring(1));

            // Repeat code to get value for number of Tickets
            Integer adultCounter = Integer.parseInt((cbxClientePetQuantidade.getSelectedItem().toString()));
            Integer countTotal = adultCounter;

            // Create a boolean that when true carries out the database portion of this ActionEvent
            boolean proceed = true;

            if (proceed == true) {

                // Remove the send values from the array
                seatArrayList.remove(seat1Store);

                try { // Start try/catch
                    // State dependables for reading the database
                    agenda = local + agenda;
                    System.out.println(agenda);
                    FileInputStream fs = new FileInputStream(agenda);
                    DataInputStream in = new DataInputStream(fs);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    // While there are Lines left to be read
                    String stringLine;
                    while ((stringLine = br.readLine()) != null) {
                        try ( // Create dependencies for writing to same file
                                BufferedWriter fw = new BufferedWriter(new FileWriter(agenda))) {
                            int x = 0;
                            // Iterate through the new edited array (orginal array minus selected seat)
                            while (x < seatArrayList.size()) {
                                // Rewrite every line of the text file with each entry in the new array
                                String line = seatArrayList.get(x);
                                fw.write(line + ";");
                                x++;
                            }
                        }

                    }

                    //try/catch end, if error- prints message to command line followed by error code
                } catch (Exception ex) {
                    msg = msg + "Erro c1 Metodo - actionPerformed() -  \n";
                    msg = msg + ex;
                    System.err.println("Erro de abertura de arquivo de dados, code: " + ex.getMessage());
                } finally {
                    if (!msg.equals("")) {

                        JOptionPane.showMessageDialog(null, msg);
                    }
                }

                // Call Pop-up asking user if they want to restart the program for another transaction
                ticketBought();
            }
        }

        // ActionListener for database reset button
        if (action.getSource() == resetButton) {
            // When button is selected, A YES/NO messagebox displays
            Integer end = JOptionPane.showConfirmDialog(getContentPane(),
                    "(NOTE: You can only delete databases upon initially loading"
                    + " the program and before selecting any databases).\n"
                    + "If you haven't already done so, please re-run the program and"
                    + " select this option again if you wish to delete the databases.\n"
                    + "The Command will still run regardless, but will not work without"
                    + " the the above steps\n\n"
                    + "Would you like to Reset all the Databases?\n",
                    "Delete Databases?",
                    JOptionPane.YES_NO_OPTION);

            // Is selected answer is YES
            if (end == 0) {
                // Ask for validation of deltion
                Integer yesno1 = JOptionPane.showConfirmDialog(getContentPane(),
                        "Você tem certeza que que apagar toda as bases de dados de datas agendamento?",
                        "Apaga base de Dados?",
                        JOptionPane.YES_NO_OPTION);

                // if selected yes
                if (yesno1 == 0) {
                    // Delete the current instance of the program
                    TelaMostraAgenda.this.dispose();

                    //Delete Current database
                    File fileToDelete = new File(agenda);
                    fileToDelete.delete();

                    // Delete all the databases (stated by name)
                    //int size = toppings.length;
                    for (String listaAgenda : listaDatas) {
                        File file = new File(listaAgenda + ".txt"); //
                        file.delete();
                        //file.getName().toString()
                        System.out.println("Arquivo :" + listaAgenda + ".txt" + " deletado!");
                    }

                    // Create new instance of the program (hence restart it)
                    TelaMostraAgenda telaMostraAssentos = new TelaMostraAgenda(coluna, e, b, f, e_param, b_param, f_param);
                }//fim if
            }//fim if
        }//fim action resetButton

    }

    //END OF ACTIONEVENTS
    /**
     * Metodo retorna o valor total da compra.
     *
     *
     * @return
     */
    // Method that returns total price as as String
    public String getTotal() {
        // Get current value of each selected option that effects the price

        Integer adultInput = Integer.parseInt(cbxClientePetQuantidade.getSelectedItem().toString());

        // Pass this value to 'Ticket.getSeatPrice' method to obtain price
        //int childTicketPrice = Ticket.getSeatPrice(childInput, "child");
        //int adultTicketPrice = Ticket.getSeatPrice(adultInput, "adult");
        //int oapTicketPrice = Ticket.getSeatPrice(oapInput, "oap");
        // Calculate total
        //int total = (childTicketPrice +adultTicketPrice+oapTicketPrice);
        //Convert this integer value to a string in the correct format
        //String totalString = Ticket.calculateStringTotal(total);
        // return totalString;
        return "0";
    }

    /**
     * Metodo que confirma a compra.
     *
     *
     */
    public void ticketBought() {
        // Display Message Stating the price of the ordered tickets
        JOptionPane.showMessageDialog(getContentPane(),
                "O Custo total das passagens ..R$: " + getTotal(), "Custo Total", JOptionPane.PLAIN_MESSAGE);

        // Ask user if they want to restart the program
        Integer a = JOptionPane.showConfirmDialog(getContentPane(),
                "Agendamento efetuado com sucesso\n Deseja realizar no agendamento?",
                "Agendamento Cadastrado", JOptionPane.YES_NO_OPTION);

        // If yes, deletes current instance of program then creates a new one
        if (a == 0) {
            System.out.println("Program Restart Initiated");
            TelaMostraAgenda.this.dispose();
            TelaMostraAgenda telaMostraAssentos = new TelaMostraAgenda(coluna, e, b, f, e_param, b_param, f_param);
            System.out.println("Nova instancia com os parametros: " + coluna + " " + e + " " + b + " " + f + " " + e_param + " " + b_param + " " + f_param);
        }
        // If no, then instance of program is deleted but no new instance is created, hence ending the program
        if (a == 1) {
            this.dispose();
            //System.exit(0);
        }
    }

    // Methods for displaying error messages
    public void tooManyTickets() {
        JOptionPane.showMessageDialog(getContentPane(), "Você não pode comprar mais que 5 passagens por vez!", "Erro Quantidade de passagens", JOptionPane.ERROR_MESSAGE);
    }

    public void notEnoughAssentos() {
        JOptionPane.showMessageDialog(getContentPane(), "Não existe nenhumaagendaselecionado para compra das passagens selecionadas!", "Erro Quantidade de passagens", JOptionPane.ERROR_MESSAGE);
    }

    public void duplicateAssentos() {

        JOptionPane.showMessageDialog(getContentPane(), "Existe uma escolha duplicada de assentos!", "Erro na seleção de assentos", JOptionPane.ERROR_MESSAGE);
    }

    public void notSelectedAllAssentos() {
        JOptionPane.showMessageDialog(getContentPane(), "Não existe assento selecionado!", "Erro na seleção de assentos", JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * M E T O D O G R A F I C O Q U E D E S E N H A O L A Y O U T
     *
     * D A C O N F I G U R A C A O D O S A S S E N T O S D A
     *
     * A E R O N A V E
     *
     */
    @Override
    public void paint(Graphics g) {
        //ArrayList<Integer> list = new ArrayList<Integer>(array);

        super.paint(g);                         // Clears the frame when method is called
        //paint(g);
        //JScrollPane jsp = new JScrollPane(p4);
        //super.add(hbar, BorderLayout.SOUTH);
        //super.add(vbar, BorderLayout.EAST);

        /**
         *
         * D E F I N I C A O D E V A R I A V E I S P A R A O
         * D E S E N H O D O L A Y O U T D O S A S S E N T O S
         *
         */
        int width = 90;                         // DEFINICAO DA LARGURA DE CADA RETANGULO
        int height = 25;                        // DEFINICAO DA ALTURA DE CADA RETANGULO

        int classeEconomicaAssentosRow = 0;     // VARIAVEL LINHAS DA CLASSE ECONOMICA
        int classeEconomicaAssentosCol = 0;     // VARIAVEL COLUNAS DA CLASSE ECONOMICA

        int classeEmpresarialAssentosRow = 0;   // VARIAVEL LINHAS DA CLASSE EMPRESARIAL
        int classeEmpresarialAssentosCol = 0;   // VARIAVEL COLUNAS DA CLASSE EMPRESARIAL

        int classePrimeiraAssentosRow = 0;      // VARIAVEL LINHAS DA FIRST CLASSE
        int classePrimeiraAssentosCol = 0;      // VARIAVEL COLUNAS DA FIRST CLASSE

        int classeE = 0;
        int classeB = 0;
        int classeF = 0;

        String colString = "";
        String rowString = "";

        /**
         *
         * R O T I N A P A R A C A L C U L O D A C O L U N A S / L I N H A S
         *
         * C O N F O R M E O P A R A M E T R O C O L U N A R E C E B I D O
         *
         *
         */
        if (coluna) {
            /**
             * C O L U N A = T R U E
             */

            classeEconomicaAssentosRow = AgendaPreencheDatas.calcRow(e, ecol);       // NUMERO DE LINHAS DA CLASSE ECONOMICA
            classeEconomicaAssentosCol = ecol;                                          // NUMERO DE COLUNAS DA CLASSE ECONOMICA

            classeEmpresarialAssentosRow = AgendaPreencheDatas.calcRow(b, bcol);    // NUMERO DE LINHAS DA CLASSE EMPRESARIAL
            classeEmpresarialAssentosCol = bcol;                                        // NUMERO DE COLUNAS DA CLASSE EMPRESARIAL

            classePrimeiraAssentosRow = AgendaPreencheDatas.calcRow(f, fcol);       // NUMERO DE LINHAS DA CLASSE PRIMEIRA
            classePrimeiraAssentosCol = fcol;                                           // NUMERO DE COLUNAS DA CLASSE PRIMEIRA

        } else {
            /**
             * C O L U N A = F A L S E
             */

            classeEconomicaAssentosRow = erow;                                          // NUMERO DE LINHAS DA CLASSE ECONOMICA
            classeEconomicaAssentosCol = AgendaPreencheDatas.calcCol(e, erow);       // NUMERO DE COLUNAS DA CLASSE ECONOMICA

            classeEmpresarialAssentosRow = brow;                                        // NUMERO DE LINHAS DA CLASSE EMPRESARIAL
            classeEmpresarialAssentosCol = AgendaPreencheDatas.calcCol(b, brow);    // NUMERO DE COLUNAS DA CLASSE EMPRESARIAL

            classePrimeiraAssentosRow = frow;                                           // NUMERO DE LINHAS DA CLASSE PRIMEIRA
            classePrimeiraAssentosCol = AgendaPreencheDatas.calcCol(f, frow);       // NUMERO DE COLUNAS DA CLASSE PRIMEIRA

        }

        /**
         * estabelece a quantidade de assento por classe
         */
        classeE = e;
        classeB = b;
        classeF = f;

        System.out.println("Economica: " + e + " Empresarial: " + b + " Primeira" + f);

        System.out.println("Total classe economica = " + classeE + "\nTotal classe economica = " + classeB + "\nTotal classe economica = " + classeF);
        /**
         *
         * C A B E C A L H O D O M A P A D E A S S E N T O S D A A E R O N A V E
         *
         *
         */
        int classeEconomicaPosX = 20;                                                     // Sets Left Block X-axis Position (in Pixels) 
        int classeEconomicaPosY = 225;                                                    // Sets Left Block Y-axis Position (in Pixels)

        int classeEmpresarialPosX = (classeEconomicaPosX + (classeEconomicaAssentosCol * width)) + 20;        // Sets Center Block X-axis Position (in Pixels)
        int classeEmpresarialPosY = 225;                                                  // Sets Center Block Y-axis Position (in Pixels)

        int classePrimeiraPosX = (classeEmpresarialPosX + (classeEmpresarialAssentosCol * width)) + 20;    // Sets Right Block X-axis Position (in Pixels)
        int classePrimeiraPosY = 225;                                                   // Sets Right Block Y-axis Position (in Pixels)

        g.setColor(Color.black);        // Set Default Draw Color to black

        //PRIMEIRA LINHA DE TITULOS
        g.drawString("Agenda Diária de Serviços", (classeEconomicaPosX), (classeEconomicaPosY - 30));              //TITULO 1
        //TITULO 3

        //SEGUNDA LINHA DE TITULOS
        g.drawString("Profissional / Box ", (classeEconomicaPosX), (classeEconomicaPosY - 10));           //SUB-TITULO 1

        //Color custom_grey = new Color(175,175,175);
        Color custom_grey = new Color(0, 0, 0); //alterado para black
        Color yellow = new Color(255, 255, 0); //cor amarela
        Color blue = new Color(0, 0, 255); //cor azul

        /**
         *
         * C L A S S E E C O N O M I C A G R A F I C O S
         *
         *
         */
        for (int i = 0; i < classeEconomicaAssentosCol; i++) // Continua enquanto houver colunas
        {

            for (int x = 0; x < classeEconomicaAssentosRow; x++) // For each column, loop while there are Rows..
            {
                // [As above]
                rowString = Integer.toString((i + (classeEconomicaAssentosCol * x)) + 1001);
                rowString = "E" + rowString.substring(1);

                if (Integer.parseInt(rowString.substring(1)) <= e) {
                    //Coloca o numero do assento
                    g.drawString(rowString, classeEconomicaPosX + 5 + (i * width), classeEconomicaPosY + (x * height) + 20);
                } else {
                    // Fill in the currently iterated rectangle
                    g.drawString("<---->", classeEconomicaPosX + 5 + (i * width), classeEconomicaPosY + (x * height) + 20);
                    // g.fillRect(classeEconomicaPosX+(i*width), classeEconomicaPosY+(x*height), width, height);
                }

                // Draw A rectangle exactly like before but with Y-Pos + (height * vertical postition)
                g.drawRect(classeEconomicaPosX + (i * width), classeEconomicaPosY + (x * height), width, height);

                if (seatArrayList.contains(rowString) != true) // If the Array of available seats does not contain the relevent seat number...
                {

                    //g.setColor(Color.red);
                    //g.setColor(Color.green);
                    g.setColor(yellow);

                    // Fill in the currently iterated rectangle
                    g.fillRect(classeEconomicaPosX + (i * width), classeEconomicaPosY + (x * height), width, height);

                    // Change draw color back to default
                    g.setColor(Color.black);

                    // Redraw outline of rectangle
                    g.drawRect(classeEconomicaPosX + (i * width), classeEconomicaPosY + (x * height), width, height);

                    // Set Color to Custom
                    g.setColor(custom_grey);

                    if (Integer.parseInt(rowString.substring(1)) <= e) { // Redraw number
                        g.drawString(rowString, classeEconomicaPosX + 5 + (i * width), classeEconomicaPosY + (x * height) + 20);
                    } else {
                        // Fill in the currently iterated rectangle
                        g.drawString("<---->", classeEconomicaPosX + 5 + (i * width), classeEconomicaPosY + (x * height) + 20);
                        // g.fillRect(classeEconomicaPosX+(i*width), classeEconomicaPosY+(x*height), width, height);
                    }

                    // Change color back to default
                    g.setColor(Color.black);

                    //System.out.println("Economica: "+e+" Empresarial: "+b+" Primeira"+f);
                }

            }

        }
        /**
         *
         * C L A S S E E M P R E S A R I A L G R A F I C O S
         *
         *
         */
        for (int i = 0; i < classeEmpresarialAssentosCol; i++) // [Refer to Left Block Code comments]
        {

            for (int x = 0; x < classeEmpresarialAssentosRow; x++) {
                rowString = Integer.toString((classeE + i + (classeEmpresarialAssentosCol * x)) + 1001);
                rowString = "B" + rowString.substring(1);

                if (Integer.parseInt(rowString.substring(1)) <= (b + e)) {
                    //Coloca o numero do assento
                    g.drawString(rowString, classeEmpresarialPosX + 5 + (i * width), classeEmpresarialPosY + (x * height) + 20);
                } else {
                    // Fill in the currently iterated rectangle
                    g.drawString("<---->", classeEmpresarialPosX + 5 + (i * width), classeEmpresarialPosY + (x * height) + 20);
                    //g.fillRect(classeEmpresarialPosX+(i*width), classeEmpresarialPosY+(x*height), width, height);
                }

                g.drawRect(classeEmpresarialPosX + (i * width), classeEmpresarialPosY + (x * height), width, height);

                System.out.println("For PosX:" + classeEmpresarialPosX + 5 + (i * width) + " posY: " + classeEmpresarialPosY + (x * height) + 20 + " rowString:  " + rowString);
                if (seatArrayList.contains(rowString) != true) {
                    /**
                     * Opcoes de cores para indicar assento indisponivel:
                     * g.setColor(Color.red), g.setColor(Color.green),
                     * g.setColor(yellow).
                     */
                    g.setColor(Color.green);

                    g.fillRect(classeEmpresarialPosX + (i * width), classeEmpresarialPosY + (x * height), width, height);
                    g.setColor(Color.black);
                    g.drawRect(classeEmpresarialPosX + (i * width), classeEmpresarialPosY + (x * height), width, height);
                    //g.setColor(custom_grey);

                    if (Integer.parseInt(rowString.substring(1)) <= (b + e)) {
                        //Coloca o numero do assento
                        g.drawString(rowString, classeEmpresarialPosX + 5 + (i * width), classeEmpresarialPosY + (x * height) + 20);
                    } else {
                        g.drawString("<---->", classeEmpresarialPosX + 5 + (i * width), classeEmpresarialPosY + (x * height) + 20);
                        // Fill in the currently iterated rectangle
                        //g.fillRect(classeEmpresarialPosX+(i*width), classeEmpresarialPosY+(x*height), width, height);
                    }

                    g.setColor(Color.red);
                    System.out.println("\nVerde row PosX:" + classeEmpresarialPosX + 5 + (i * width) + " posY: " + classeEmpresarialPosY + (x * height) + 20 + " rowString:  " + rowString + "\n");
                    g.setColor(Color.black);
                }

            }

        }

        /**
         *
         * C L A S S E F I R S T G R A F I C O S
         *
         *
         */
        for (int i = 0; i < classePrimeiraAssentosCol; i++) // [Refer to Left Block Code comments]
        {

            for (int x = 0; x < classePrimeiraAssentosRow; x++) {
                rowString = Integer.toString(((classeE + classeB) + i + (classePrimeiraAssentosCol * x) + 1001));
                rowString = "P" + rowString.substring(1);

                if (Integer.parseInt(rowString.substring(1)) <= (b + e + f)) {
                    //Coloca o numero do assento
                    g.drawString(rowString, classePrimeiraPosX + 5 + (i * width), classePrimeiraPosY + (x * height) + 20);
                } else {
                    // Fill in the currently iterated rectangle
                    g.drawString("<---->", classePrimeiraPosX + 5 + (i * width), classePrimeiraPosY + (x * height) + 20);
                    //g.fillRect(classePrimeiraPosX+(i*width), classePrimeiraPosY+(x*height), width, height);
                }

                g.drawRect(classePrimeiraPosX + (i * width), classePrimeiraPosY + (x * height), width, height);
                if (seatArrayList.contains(rowString) != true) {
                    //g.setColor(Color.red);
                    //g.setColor(Color.green);
                    // g.setColor(yellow);
                    g.setColor(blue);

                    g.fillRect(classePrimeiraPosX + (i * width), classePrimeiraPosY + (x * height), width, height);
                    g.setColor(Color.black);
                    g.drawRect(classePrimeiraPosX + (i * width), classePrimeiraPosY + (x * height), width, height);
                    g.setColor(custom_grey);

                    if (Integer.parseInt(rowString.substring(1)) <= (b + e + f)) {
                        //Coloca o numero do assento
                        g.drawString(rowString, classePrimeiraPosX + 5 + (i * width), classePrimeiraPosY + (x * height) + 20);
                    } else {
                        // Fill in the currently iterated rectangle
                        g.drawString("<---->", classePrimeiraPosX + 5 + (i * width), classePrimeiraPosY + (x * height) + 20);
                        //g.fillRect(classePrimeiraPosX+(i*width), classePrimeiraPosY+(x*height), width, height);
                    }

                    g.setColor(Color.black);
                }
            }
        }
        /**
         *
         * F I N A L D O S M E T O D O S G R A F I C O S
         *
         */

    }

    public static void main(String[] args) // Main Method Declaration
    {
        TelaMostraAgenda telaMostraAgenda = new TelaMostraAgenda(true, 20, 0, 0, 1, 0, 0); // Cria uma nova instancia de TelaMostraAgenda.
    }
}
