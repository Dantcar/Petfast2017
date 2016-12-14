/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.AnimalCtrl;
import controle.Util;
import controle.ValidaCampos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import modelo.Animal;

/**
 *
 * @author Décio
 */
//public class TelaAnimal extends javax.swing.JInternalFrame {
public class TelaAnimal extends javax.swing.JFrame {

    /**
     * Creates new form TelaAnimal
     */
    private Date dataNasc, dataInit, dtPet, hojePet;
    private Date calNascimentoPet;
    private String dataHojePet, dataNascimentoPetAnimal;
    private SimpleDateFormat sdfNascimentoPet;
    private int dataIntNascimentoPet;
    private javax.swing.JFileChooser jFileChooserFoto;

    public TelaAnimal(String nomeCliente, int id, String operacao, String nomeAnimal) {
        initComponents();
        String pathProjeto = System.getProperty("user.dir") + "\\";
        String iconPetfast = pathProjeto + "src\\Icones\\petfastIcone.png";

        String fotoPet = System.getProperty("user.dir") + "\\ImagensPet\\templateFoto1.png";

        setIconImage(Toolkit.getDefaultToolkit().getImage(iconPetfast));
        this.setLocation(250, 100); //(ponto inicial apartir lateral,altura)
        AnimalCtrl cAnimal = new AnimalCtrl();
        desabilitarBotoesAnimal();
        tctPetAnimalId.setEditable(false);
        dtPet = new Date();

        jspNascimentoPet.setValue(dtPet);
        hojePet = new Date();
        dataHojePet = Util.DataFormatada(hojePet);
        sdfNascimentoPet = new SimpleDateFormat("dd/MM/yyyy");

        try {
            hojePet = sdfNascimentoPet.parse(dataHojePet);
        } catch (ParseException ex) {
            Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (operacao == "i") {
            tctPetAnimalCliente.setText(nomeCliente);
            tctPetAnimalClienteId.setText(id + "");
            tctPetAnimalClienteId.setEditable(false);
            //habilitar botoes incluir
            btnPetSalvar.setEnabled(true);
            btnPetBuscarFoto.setEnabled(true);
            int idAnimal = cAnimal.receberIdAnimalAtual(); //pega o próximo id para cadastro
            tctPetAnimalId.setText(idAnimal + 1 + "");
            colocarFotoLabelUrl(fotoPet);
            /**
             * Botão Alterar
             */
        } else if (operacao == "a") {
            Animal animal = new Animal();
            animal = cAnimal.receberAnimalNome(nomeAnimal);

            if (animal != null) {
                btnPetBuscarFoto.setEnabled(true);
                tctPetAnimalCliente.setText(nomeCliente);
                tctPetAnimalClienteId.setText(id + "");
                tctPetAnimalClienteId.setEditable(false);
                tctPetAnimalNome.setText(nomeAnimal);
                colocarFotoLabelUrl(fotoPet);

                if (animal.getSexo() == "M") {
                    rbMacho.setSelected(true);
                } else {
                    rbFemea.setSelected(true);
                }

                tctPetAnimalId.setText(animal.getIdAnimal());

                //tratamento data
                String sdataNascimento = animal.getNascimento();
                try {
                    calNascimentoPet = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimentoPet.setValue(calNascimentoPet);
                //fim tratamento data

                tctPetCor.setText(animal.getCor());
                tctPetEspecie.setText(animal.getEspecie());
                colocarFotoLabel();

                if (verificarFotoExiste(animal.getFoto())) {
                    tctPetFoto.setText(animal.getFoto());
                    System.out.println("retorno consulta if: " + verificarFotoExiste(animal.getFoto()));
                    colocarFotoLabel();
                } else {
                    // fotoPet = System.getProperty("user.dir") + "\\ImagensPet\\templateFoto1.png";
                    colocarFotoLabelUrl(fotoPet);
                    System.out.println("retorno consulta else: " + verificarFotoExiste(animal.getFoto()));
                    System.out.println(fotoPet);
                }

                tctPetFoto.setEditable(false);
                tctPetRaca.setText(animal.getRaca());
                tftAlturaPet.setText(animal.getAltura());
                tftPesoPet.setText(animal.getPeso());
                txaPetCaracteristica.setText(animal.getCaracteristica());
                //colocarFotoLabel();
                btnPetAlterar.setEnabled(true);
            } else {
                limparTelaAnimal();
            }

            /**
             * Botao Excluir
             */
        } else if (operacao == "e") { //eliminar
            Animal animal = new Animal();
            animal = cAnimal.receberAnimalNome(nomeAnimal);

            if (animal != null) {

                tctPetAnimalCliente.setText(nomeCliente);
                tctPetAnimalClienteId.setText(id + "");
                tctPetAnimalNome.setText(nomeAnimal);

                if (animal.getSexo().trim().equals("M")) {
                    rbMacho.setSelected(true);
                } else {
                    rbFemea.setSelected(true);
                }

                tctPetAnimalId.setText(animal.getIdAnimal());

                //tratamento data
                String sdataNascimento = animal.getNascimento();
                try {
                    calNascimentoPet = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimentoPet.setValue(calNascimentoPet);
                jspNascimentoPet.setEnabled(false);
                //fim tratamento data

                tctPetCor.setText(animal.getCor());
                tctPetEspecie.setText(animal.getEspecie());
                tctPetFoto.setText(animal.getFoto());
                tctPetRaca.setText(animal.getRaca());
                tftAlturaPet.setText(animal.getAltura());
                tftPesoPet.setText(animal.getPeso());
                txaPetCaracteristica.setText(animal.getCaracteristica());

                desabilitarEdiçãoTelaAnimal();
                btnPetExcluir.setEnabled(true);
                colocarFotoLabel();
            } else {
                limparTelaAnimal();
            }

            /**
             * Botão Consultar
             */
        } else if (operacao == "c") {
            Animal animal = new Animal();
            animal = cAnimal.receberAnimalNome(nomeAnimal);

            if (animal != null) {

                tctPetAnimalCliente.setText(nomeCliente);
                tctPetAnimalClienteId.setText(id + "");
                tctPetAnimalNome.setText(nomeAnimal);

                //System.out.println("Este é o sexo: "+ animal.getSexo());
                if (animal.getSexo().trim().equals("M")) {
                    rbMacho.setSelected(true);
                } else {
                    rbFemea.setSelected(true);
                }

                tctPetAnimalId.setText(animal.getIdAnimal());

                //tratamento data
                String sdataNascimento = animal.getNascimento();
                try {
                    calNascimentoPet = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimentoPet.setValue(calNascimentoPet);
                jspNascimentoPet.setEnabled(false);
                //fim tratamento data

                tctPetCor.setText(animal.getCor());
                tctPetEspecie.setText(animal.getEspecie());
                tctPetFoto.setText(animal.getFoto());
                tctPetRaca.setText(animal.getRaca());
                tftAlturaPet.setText(animal.getAltura());
                tftPesoPet.setText(animal.getPeso());
                txaPetCaracteristica.setText(animal.getCaracteristica());
                colocarFotoLabel();
                desabilitarEdiçãoTelaAnimal();
                //btnPetSalvar.setEnabled(true);

            } else {
                limparTelaAnimal();
            }

        } else {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSexo = new javax.swing.ButtonGroup();
        jPanelPetAnimalTitulo1 = new javax.swing.JPanel();
        lblTituloPet1 = new javax.swing.JLabel();
        lblTelaPetNomeCliente = new javax.swing.JLabel();
        tctPetAnimalCliente = new javax.swing.JTextField();
        lblTelaPetNomeClienteId = new javax.swing.JLabel();
        tctPetAnimalClienteId = new javax.swing.JTextField();
        lblTelaPetNomePet = new javax.swing.JPanel();
        lblTelaPetId = new javax.swing.JLabel();
        tctPetAnimalId = new javax.swing.JTextField();
        lblTelaPetNascimento = new javax.swing.JLabel();
        tctPetCor = new javax.swing.JTextField();
        lblTelaPetCor = new javax.swing.JLabel();
        lblTelaPetEspecie = new javax.swing.JLabel();
        tctPetEspecie = new javax.swing.JTextField();
        tctPetRaca = new javax.swing.JTextField();
        lblTelaPetRaca = new javax.swing.JLabel();
        lblTelaPetPeso = new javax.swing.JLabel();
        lblTelaPetAltura = new javax.swing.JLabel();
        lblTelaPetSexo = new javax.swing.JLabel();
        lblTelaPetCaracteristica = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaPetCaracteristica = new javax.swing.JTextArea();
        lblFotoPet = new javax.swing.JLabel();
        tctPetFoto = new javax.swing.JTextField();
        lblTelaPetPesoUnidade = new javax.swing.JLabel();
        lblTelaPetAlturaUnidade = new javax.swing.JLabel();
        rbMacho = new javax.swing.JRadioButton();
        rbFemea = new javax.swing.JRadioButton();
        btnPetSalvar = new javax.swing.JButton();
        btnPetExcluir = new javax.swing.JButton();
        btnPetVoltar = new javax.swing.JButton();
        btnPetBuscarFoto = new javax.swing.JButton();
        Date dtIntegradaNP = new Date();

        SpinnerDateModel smIntegradaNP = new 
        SpinnerDateModel(dtIntegradaNP,null,null,Calendar.MONTH + Calendar.DAY_OF_MONTH 
            + Calendar.YEAR );
        jspNascimentoPet = 
        jspNascimentoPet = new javax.swing.JSpinner(smIntegradaNP);
        JSpinner.DateEditor deIntegradaNP = new 
        JSpinner.DateEditor(jspNascimentoPet,"dd/MM/yyyy");
        jspNascimentoPet.setEditor(deIntegradaNP);
        tftPesoPet = new javax.swing.JFormattedTextField();
        tftAlturaPet = new javax.swing.JFormattedTextField();
        lblTelaPetNome1 = new javax.swing.JLabel();
        tctPetAnimalNome = new javax.swing.JTextField();
        btnPetAlterar = new javax.swing.JButton();

        jPanelPetAnimalTitulo1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblTituloPet1.setBackground(new java.awt.Color(102, 102, 102));
        lblTituloPet1.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        lblTituloPet1.setForeground(new java.awt.Color(102, 102, 102));
        lblTituloPet1.setText("Pet-Animal");

        lblTelaPetNomeCliente.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetNomeCliente.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetNomeCliente.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetNomeCliente.setText("Cliente:");

        tctPetAnimalCliente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetAnimalCliente.setForeground(new java.awt.Color(51, 51, 51));
        tctPetAnimalCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tctPetAnimalCliente.setEnabled(false);

        lblTelaPetNomeClienteId.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetNomeClienteId.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetNomeClienteId.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetNomeClienteId.setText("Cliente Id:");

        tctPetAnimalClienteId.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetAnimalClienteId.setForeground(new java.awt.Color(51, 51, 51));
        tctPetAnimalClienteId.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tctPetAnimalClienteId.setEnabled(false);

        javax.swing.GroupLayout jPanelPetAnimalTitulo1Layout = new javax.swing.GroupLayout(jPanelPetAnimalTitulo1);
        jPanelPetAnimalTitulo1.setLayout(jPanelPetAnimalTitulo1Layout);
        jPanelPetAnimalTitulo1Layout.setHorizontalGroup(
            jPanelPetAnimalTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPetAnimalTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPet1)
                .addGap(103, 103, 103)
                .addComponent(lblTelaPetNomeCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tctPetAnimalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTelaPetNomeClienteId)
                .addGap(18, 18, 18)
                .addComponent(tctPetAnimalClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelPetAnimalTitulo1Layout.setVerticalGroup(
            jPanelPetAnimalTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPetAnimalTitulo1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelPetAnimalTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloPet1)
                    .addComponent(lblTelaPetNomeCliente)
                    .addComponent(tctPetAnimalCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelaPetNomeClienteId)
                    .addComponent(tctPetAnimalClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblTelaPetNomePet.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblTelaPetId.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetId.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetId.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetId.setText("Id Pet:");

        tctPetAnimalId.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetAnimalId.setForeground(new java.awt.Color(51, 51, 51));
        tctPetAnimalId.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblTelaPetNascimento.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetNascimento.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetNascimento.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetNascimento.setText("Nascimento:");

        tctPetCor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetCor.setForeground(new java.awt.Color(51, 51, 51));
        tctPetCor.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblTelaPetCor.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetCor.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetCor.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetCor.setText("Cor:");

        lblTelaPetEspecie.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetEspecie.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetEspecie.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetEspecie.setText("Espécie:");

        tctPetEspecie.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetEspecie.setForeground(new java.awt.Color(51, 51, 51));
        tctPetEspecie.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tctPetRaca.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetRaca.setForeground(new java.awt.Color(51, 51, 51));
        tctPetRaca.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblTelaPetRaca.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetRaca.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetRaca.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetRaca.setText("Raça:");

        lblTelaPetPeso.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetPeso.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetPeso.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetPeso.setText("Peso:");

        lblTelaPetAltura.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetAltura.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetAltura.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetAltura.setText("Altura:");

        lblTelaPetSexo.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetSexo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetSexo.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetSexo.setText("Sexo:");

        lblTelaPetCaracteristica.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetCaracteristica.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetCaracteristica.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetCaracteristica.setText("Características:");

        txaPetCaracteristica.setColumns(20);
        txaPetCaracteristica.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        txaPetCaracteristica.setRows(5);
        jScrollPane1.setViewportView(txaPetCaracteristica);

        lblFotoPet.setText(" ");
        lblFotoPet.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tctPetFoto.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        tctPetFoto.setForeground(new java.awt.Color(51, 51, 51));
        tctPetFoto.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblTelaPetPesoUnidade.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetPesoUnidade.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetPesoUnidade.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetPesoUnidade.setText("kg.");

        lblTelaPetAlturaUnidade.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetAlturaUnidade.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetAlturaUnidade.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetAlturaUnidade.setText("cm");

        buttonGroupSexo.add(rbMacho);
        rbMacho.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        rbMacho.setForeground(new java.awt.Color(102, 102, 102));
        rbMacho.setText("Macho");

        buttonGroupSexo.add(rbFemea);
        rbFemea.setForeground(new java.awt.Color(102, 102, 102));
        rbFemea.setText("Fêmea");

        btnPetSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnPetSalvar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnPetSalvar.setForeground(new java.awt.Color(0, 102, 0));
        btnPetSalvar.setText("Salvar Novo");
        btnPetSalvar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnPetSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetSalvarActionPerformed(evt);
            }
        });

        btnPetExcluir.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnPetExcluir.setForeground(new java.awt.Color(102, 0, 0));
        btnPetExcluir.setText("Excluir");
        btnPetExcluir.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnPetExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetExcluirActionPerformed(evt);
            }
        });

        btnPetVoltar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnPetVoltar.setForeground(new java.awt.Color(0, 0, 102));
        btnPetVoltar.setText("Voltar");
        btnPetVoltar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnPetVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetVoltarActionPerformed(evt);
            }
        });

        btnPetBuscarFoto.setBackground(new java.awt.Color(255, 255, 255));
        btnPetBuscarFoto.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnPetBuscarFoto.setForeground(new java.awt.Color(102, 102, 0));
        btnPetBuscarFoto.setText("Localizar Foto");
        btnPetBuscarFoto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPetBuscarFoto.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnPetBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetBuscarFotoActionPerformed(evt);
            }
        });

        jspNascimentoPet.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jspNascimentoPet.setToolTipText("Escolha Dia, Mês e Ano");
        jspNascimentoPet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspNascimentoPetMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jspNascimentoPetMouseReleased(evt);
            }
        });

        tftPesoPet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tftPesoPet.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tftAlturaPet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tftAlturaPet.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblTelaPetNome1.setBackground(new java.awt.Color(102, 102, 102));
        lblTelaPetNome1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblTelaPetNome1.setForeground(new java.awt.Color(102, 102, 102));
        lblTelaPetNome1.setText("Nome Pet:");

        tctPetAnimalNome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctPetAnimalNome.setForeground(new java.awt.Color(51, 51, 51));
        tctPetAnimalNome.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnPetAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnPetAlterar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnPetAlterar.setForeground(new java.awt.Color(0, 102, 0));
        btnPetAlterar.setText("Alterar");
        btnPetAlterar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnPetAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lblTelaPetNomePetLayout = new javax.swing.GroupLayout(lblTelaPetNomePet);
        lblTelaPetNomePet.setLayout(lblTelaPetNomePetLayout);
        lblTelaPetNomePetLayout.setHorizontalGroup(
            lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTelaPetNascimento)
                        .addComponent(lblTelaPetRaca)
                        .addComponent(lblTelaPetCor)
                        .addComponent(lblTelaPetPeso)
                        .addComponent(lblTelaPetCaracteristica))
                    .addComponent(lblTelaPetEspecie, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelaPetId, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelaPetNome1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblTelaPetNomePetLayout.createSequentialGroup()
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(tftPesoPet, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelaPetPesoUnidade)
                                .addGap(41, 41, 41)
                                .addComponent(lblTelaPetAltura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tftAlturaPet, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelaPetAlturaUnidade)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                        .addComponent(btnPetSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(btnPetAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPetExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(34, 34, 34))
                    .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tctPetEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tctPetCor, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                .addComponent(tctPetRaca)
                                .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                    .addComponent(jspNascimentoPet, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66)
                                    .addComponent(lblTelaPetSexo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbMacho)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbFemea)))
                            .addComponent(tctPetAnimalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tctPetAnimalId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)))
                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                        .addComponent(btnPetBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(btnPetVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tctPetFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFotoPet, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        lblTelaPetNomePetLayout.setVerticalGroup(
            lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblFotoPet, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tctPetFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPetBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPetVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelaPetId)
                            .addComponent(tctPetAnimalId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelaPetNome1)
                            .addComponent(tctPetAnimalNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tctPetEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelaPetEspecie))
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblTelaPetNascimento))
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTelaPetSexo)
                                    .addComponent(rbMacho)
                                    .addComponent(rbFemea)))
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jspNascimentoPet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tctPetRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelaPetRaca))
                        .addGap(18, 18, 18)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tctPetCor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelaPetCor))
                        .addGap(23, 23, 23)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelaPetPeso)
                            .addComponent(lblTelaPetAltura)
                            .addComponent(lblTelaPetPesoUnidade)
                            .addComponent(lblTelaPetAlturaUnidade)
                            .addComponent(tftPesoPet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftAlturaPet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblTelaPetCaracteristica))
                            .addGroup(lblTelaPetNomePetLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblTelaPetNomePetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPetExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(btnPetAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(btnPetSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 70, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPetAnimalTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTelaPetNomePet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPetAnimalTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTelaPetNomePet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jspNascimentoPetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspNascimentoPetMouseClicked
        jspNascimentoPet.getEditor().setBackground(Color.black);
        jspNascimentoPet.getEditor().setForeground(Color.black);
    }//GEN-LAST:event_jspNascimentoPetMouseClicked

    private void jspNascimentoPetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspNascimentoPetMouseReleased
        // TODO add your handling code here:
        jspNascimentoPet.getEditor().setBackground(Color.black);
        jspNascimentoPet.getEditor().setForeground(Color.black);
    }//GEN-LAST:event_jspNascimentoPetMouseReleased

    private void btnPetVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();


    }//GEN-LAST:event_btnPetVoltarActionPerformed

    private void btnPetSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetSalvarActionPerformed

        Animal animal = new Animal();
        AnimalCtrl cAnimal = new AnimalCtrl();
        // animalc = new AnimalCtrl();
        animal.setIdCliente(tctPetAnimalClienteId.getText());
        animal.setIdAnimal(tctPetAnimalId.getText());
        animal.setNome(tctPetAnimalNome.getText());
        animal.setEspecie(tctPetEspecie.getText());

        dataNascimentoPetAnimal = Util.DataFormatadaS(jspNascimentoPet.getValue().toString());
        animal.setNascimento(dataNascimentoPetAnimal);
        animal.setRaca(tctPetRaca.getText());
        animal.setPeso(tftPesoPet.getText());
        animal.setAltura(tftAlturaPet.getText());
        animal.setCaracteristica(txaPetCaracteristica.getText());
        animal.setCor(tctPetCor.getText());
        animal.setFoto(tctPetFoto.getText());

        if (rbMacho.isSelected()) {
            //JOptionPane.showMessageDialog(null,"O sexo Masculino foi selecionado");
            animal.setSexo("M");
        }
        if (rbFemea.isSelected()) {
            //JOptionPane.showMessageDialog(null,"O sexo Feminino foi selecionado");
            animal.setSexo("F");
        }

        boolean resp = validarTelaAnimal(animal);

        if (resp) {
            cAnimal.cInserirAnimal(animal);
            this.dispose();
        }
        //limparTelaAnimal();
    }//GEN-LAST:event_btnPetSalvarActionPerformed

    private void btnPetAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetAlterarActionPerformed
        // Botao alterar animal
        Animal animal = new Animal();
        AnimalCtrl cAnimal = new AnimalCtrl();
        // animalc = new AnimalCtrl();
        animal.setIdCliente(tctPetAnimalClienteId.getText());
        animal.setIdAnimal(tctPetAnimalId.getText());
        animal.setNome(tctPetAnimalNome.getText());
        animal.setEspecie(tctPetEspecie.getText());

        dataNascimentoPetAnimal = Util.DataFormatadaS(jspNascimentoPet.getValue().toString());
        animal.setNascimento(dataNascimentoPetAnimal);
        animal.setRaca(tctPetRaca.getText());
        animal.setPeso(tftPesoPet.getText());
        animal.setAltura(tftAlturaPet.getText());
        animal.setCaracteristica(txaPetCaracteristica.getText());
        animal.setCor(tctPetCor.getText());
        animal.setFoto(tctPetFoto.getText());

        if (rbMacho.isSelected()) {
            //JOptionPane.showMessageDialog(null,"O sexo Masculino foi selecionado");
            animal.setSexo("M");
        }
        if (rbFemea.isSelected()) {
            //JOptionPane.showMessageDialog(null,"O sexo Feminino foi selecionado");
            animal.setSexo("F");
        }

        boolean resp = validarTelaAnimal(animal);

        if (resp) {
            cAnimal.cAlterarAnimal(animal, animal.getIdAnimal());
            this.dispose();
        }


    }//GEN-LAST:event_btnPetAlterarActionPerformed

    private void btnPetExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetExcluirActionPerformed
        // Código Botão excluir
        AnimalCtrl cAnimal = new AnimalCtrl();
        String nomeAnimal = tctPetAnimalNome.getText();
        int idAnimal = Integer.parseInt(tctPetAnimalId.getText());
        cAnimal.cDelerarAnimal(nomeAnimal, idAnimal);
        this.dispose();
    }//GEN-LAST:event_btnPetExcluirActionPerformed

    private void btnPetBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetBuscarFotoActionPerformed
        // metodo para buscar um arquivo de foto
        jFileChooserFoto = new javax.swing.JFileChooser();
        String fotoSource = null;
        
        String fotoDestino = System.getProperty("user.dir") + "\\ImagensPet\\";
        String fotoNome = tctPetAnimalCliente.getText() + "-" + tctPetAnimalClienteId.getText();
       
        int retVal;
        jFileChooserFoto.addChoosableFileFilter(new TextFilter());

        retVal = jFileChooserFoto.showOpenDialog(this);

        if (retVal == JFileChooser.APPROVE_OPTION) {
            
            fotoSource = jFileChooserFoto.getSelectedFile().getAbsolutePath();
            
            fotoNome = fotoNome + jFileChooserFoto.getSelectedFile().getName();
           
        }

        //System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        //System.out.println(fotoDestino+fotoNome);
        jFileChooserFoto.setVisible(false);
        copiarFotoToPetfast(fotoSource, fotoDestino, fotoNome);
        tctPetFoto.setText(fotoDestino + fotoNome);
        colocarFotoLabel();

    }//GEN-LAST:event_btnPetBuscarFotoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPetAlterar;
    private javax.swing.JButton btnPetBuscarFoto;
    private javax.swing.JButton btnPetExcluir;
    private javax.swing.JButton btnPetSalvar;
    private javax.swing.JButton btnPetVoltar;
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JPanel jPanelPetAnimalTitulo1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jspNascimentoPet;
    private javax.swing.JLabel lblFotoPet;
    private javax.swing.JLabel lblTelaPetAltura;
    private javax.swing.JLabel lblTelaPetAlturaUnidade;
    private javax.swing.JLabel lblTelaPetCaracteristica;
    private javax.swing.JLabel lblTelaPetCor;
    private javax.swing.JLabel lblTelaPetEspecie;
    private javax.swing.JLabel lblTelaPetId;
    private javax.swing.JLabel lblTelaPetNascimento;
    private javax.swing.JLabel lblTelaPetNome1;
    private javax.swing.JLabel lblTelaPetNomeCliente;
    private javax.swing.JLabel lblTelaPetNomeClienteId;
    private javax.swing.JPanel lblTelaPetNomePet;
    private javax.swing.JLabel lblTelaPetPeso;
    private javax.swing.JLabel lblTelaPetPesoUnidade;
    private javax.swing.JLabel lblTelaPetRaca;
    private javax.swing.JLabel lblTelaPetSexo;
    private javax.swing.JLabel lblTituloPet1;
    private javax.swing.JRadioButton rbFemea;
    private javax.swing.JRadioButton rbMacho;
    private static javax.swing.JTextField tctPetAnimalCliente;
    private static javax.swing.JTextField tctPetAnimalClienteId;
    private static javax.swing.JTextField tctPetAnimalId;
    private static javax.swing.JTextField tctPetAnimalNome;
    private static javax.swing.JTextField tctPetCor;
    private static javax.swing.JTextField tctPetEspecie;
    private static javax.swing.JTextField tctPetFoto;
    private static javax.swing.JTextField tctPetRaca;
    private javax.swing.JFormattedTextField tftAlturaPet;
    private javax.swing.JFormattedTextField tftPesoPet;
    private javax.swing.JTextArea txaPetCaracteristica;
    // End of variables declaration//GEN-END:variables

    private void desabilitarBotoesAnimal() {
        //desabilitar os botoes da tela no método construtor
        btnPetSalvar.setEnabled(false);
        btnPetExcluir.setEnabled(false);
        btnPetAlterar.setEnabled(false);
        btnPetBuscarFoto.setEnabled(false);
    }

    private void limparTelaAnimal() {
        rbFemea.setSelected(false);
        rbMacho.setSelected(false);
        tctPetAnimalCliente.setText("");
        tctPetAnimalClienteId.setText("");
        tctPetAnimalId.setText("");
        tctPetAnimalNome.setText("");

        try {
            hojePet = sdfNascimentoPet.parse(dataHojePet);
        } catch (ParseException ex) {
            Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tctPetCor.setText("");
        tctPetEspecie.setText("");
        tctPetFoto.setText("");
        tctPetRaca.setText("");
        tftAlturaPet.setText("");
        tftPesoPet.setText("");
        txaPetCaracteristica.setText("");
    }

    private void desabilitarEdiçãoTelaAnimal() {
        rbFemea.setEnabled(false);
        rbMacho.setEnabled(false);
        tctPetAnimalCliente.setEnabled(false);
        tctPetAnimalClienteId.setEnabled(false);
        tctPetAnimalId.setEnabled(false);
        tctPetAnimalNome.setEnabled(false);

        tctPetCor.setEnabled(false);
        tctPetEspecie.setEnabled(false);
        tctPetFoto.setEnabled(false);
        tctPetRaca.setEnabled(false);
        tftAlturaPet.setEnabled(false);
        tftPesoPet.setEnabled(false);
        txaPetCaracteristica.setEnabled(false);
    }

    private boolean validarTelaAnimal(Animal animal) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //String dataNascimentoPet = Util.DataFormatadaS(jspNascimentoPet.getValue().toString());
        int dataIntNascimentoPet = Util.DtAmericana(jspNascimentoPet.getValue().toString());
        boolean resposta = false;
        String msg;
        msg = "";

        boolean validaNome = ValidaCampos.validaVazio(tctPetAnimalNome.getText());
        boolean validaDatajsp = ValidaCampos.validaDataNascimento(dataIntNascimentoPet);
        boolean validaSexo = buttonGroupSexo.isSelected(null); //ValidaCampos.validaVazioComboBox(animal.getSexo());

        //testes da validacao
        if (validaNome) {
        } else {
            msg = msg + "Campo Nome Vazio" + "\n";
        }

        if (validaDatajsp) {
        } else {
            msg = msg + "Campo Nascimento Inválido" + "\n";
        }

        if (validaSexo) {
            msg = msg + "Campo Sexo não selecionado" + "\n";
        } else {
        }

        if ("".equals(msg)) {
            resposta = true;
        } else {
            JOptionPane.showMessageDialog(this, msg, "Campo Inválido ou vazio", JOptionPane.ERROR_MESSAGE);
        }

        return resposta;
    }

    private void colocarFotoLabel() {
        lblFotoPet.setIcon(null);
        Dimension d = lblFotoPet.getSize();
        //int width = tctPetFoto.getWidth();
        //int height = tctPetFoto.getHeight();
        //System.out.println("width: "+d.width + " height: "+d.height);
        String urlFoto = tctPetFoto.getText();
        ImageIcon foto;
        foto = new ImageIcon(urlFoto);

        foto.setImage(foto.getImage().getScaledInstance((d.width - 20), (d.height - 20), 100));
        //img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
        lblFotoPet.setIcon(foto);
        //lblFotoPet.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlFoto)));
    }

    private void colocarFotoLabelUrl(String urlFoto) {
        lblFotoPet.setIcon(null);
        Dimension d = lblFotoPet.getSize();
        //int width = tctPetFoto.getWidth();
        //int height = tctPetFoto.getHeight();
        //System.out.println("width: "+d.width + " height: "+d.height);
        //String urlFoto = tctPetFoto.getText();
        ImageIcon foto;
        foto = new ImageIcon(urlFoto);

        foto.setImage(foto.getImage().getScaledInstance((d.width - 20), (d.height - 20), 100));
        //img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
        lblFotoPet.setIcon(foto);
        //lblFotoPet.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlFoto)));
    }

    private void copiarFotoToPetfast(String fonte, String destino, String nomeArquivo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //long start = new Date().getTime();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fonte);
            fos = new FileOutputStream(destino + nomeArquivo);
            int i;
            while ((i = fis.read()) != -1) {
                fos.write(i);
            }
            // System.out.println("Arquivo copiado!"); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }	//long stop = new Date().getTime();
        //System.out.println("Tempo de copia:" + (stop - start) + "ms");

    }//fimal método copiarFotoToPetfast

    private boolean verificarFotoExiste(String url) {
        int i = url.lastIndexOf('.');
        boolean resp = false;
        Image image = null;
        File f = new File(url);
        BufferedImage img = null;

        if (f.exists()) {

            try {
                image = ImageIO.read(f);
                resp = true;
            } catch (IOException ex) {
                System.out.println("" + ex);
                resp = false;
                Logger.getLogger(TelaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (i > 0 && i < url.length() - 1) {
                if (url.substring(i + 1).toLowerCase().equals("jpg")) {
                    return true;
                }
            }

            //filtra para abrir somente arquivos jpeg
            if (i > 0 && i < url.length() - 1) {
                if (url.substring(i + 1).toLowerCase().equals("jpeg")) {
                    return true;
                }
            }

            //filtra para abrir somente arquivos jpg
            if (i > 0 && i < url.length() - 1) {
                if (url.substring(i + 1).toLowerCase().equals("png")) {
                    return true;
                }
            }

        } else {
            resp = false;
        }

        return resp;

        /**
         *
         * File f = new File(petfast/imagensPet""); if(f.exists()){
         * System.out.println("Arquivo encontrado!");
         * System.out.println("Arquivo:"+ f.getPAth());
         * System.out.println("Arquivo:"+ f.getAbsolutePAth());
         * System.out.println("Arquivo:"+ f.getName());
         * System.out.println("Arquivo:"+ f.canExecute());
         * System.out.println("Arquivo:"+ f.canRead());
         * System.out.println("Arquivo:"+ f.canWrite()); }else{
         * System.out.println("Arquivo nao encontrado!");
         *
         * }
         */

        /*
         try {
         // Lendo um arquivo
         File sourceimage = new File("source.gif");
         image = ImageIO.read(sourceimage);
         */
        /*
         try {
         img = ImageIO.read(TelaAnimal.class.getResourceAsStream(url));
         } catch (FileNotFoundException e) {
         resp=false;//Mostre o erro e faça alguma coisa!
         } catch (Exception e) {
         resp=false;//Mostre o erro e faça alguma coisa!
         }
         if (img != null) {
         //Continue
         } else {
         resp = true;
         }
         */
    }//final método verificarfotoExiste

}
