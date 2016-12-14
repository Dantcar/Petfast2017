/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ProfissionalCtrl;
import java.awt.Color;
import modelo.Profissional;
import controle.Util;
import controle.ValidaCampos;
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
/**
 *
 * @author Décio
 */
public class TelaCadastroProfissional extends javax.swing.JFrame {

    private Date dataNasc, dataInit, dtProf, hoje;
    private Date calNascimento;
    private String dataHoje, dataNascimentoProfissional;
    private SimpleDateFormat sdfNascimento;
    private int dataIntNascimento;
    private javax.swing.JFileChooser jFileChooserFoto;
    
    
    /**
     * Creates new form TelaCadastroProfissional
     */
    public TelaCadastroProfissional(String nomeProfissional, int id, String operacao) {
        initComponents();
        String pathProjeto = System.getProperty("user.dir") + "\\";
        String iconfast = pathProjeto + "src\\Icones\\petfastIcone.png";
        String foto = System.getProperty("user.dir") + "\\ImagensPet\\templateFoto1.png";
    
        setIconImage(Toolkit.getDefaultToolkit().getImage(iconfast));
        this.setLocation(250, 100); //(ponto inicial apartir lateral,altura)
        
        ProfissionalCtrl cProfissional = new ProfissionalCtrl();
        desabilitarBotoesProfissional();
    
        tctProfissionalId.setEditable(false);
        dtProf = new Date();
        jspNascimento.setValue(dtProf);
    
        hoje = new Date();
        dataHoje = Util.DataFormatada(hoje);
        sdfNascimento = new SimpleDateFormat("dd/MM/yyyy");
    
         try {
            hoje = sdfNascimento.parse(dataHoje);
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         /**
         * Botão Incluir
         */  
       if (operacao == "i") {
            tctNomeProfissional.setText(nomeProfissional);            
            tctProfissionalId.setEditable(false);
                       //habilitar botoes incluir
            btnSalvar.setEnabled(true);
            btnBuscarFoto.setEnabled(true);
            int idProfissional = cProfissional.receberIdProfissionalAtual(); //pega o próximo id para cadastro
            tctProfissionalId.setText(idProfissional + 1 + "");
            colocarFotoLabelUrl(foto);
            /**
             * Botão Alterar
             */  
       }else if (operacao == "a") {
            Profissional profissional = new Profissional();
            profissional = cProfissional.receberProfissionalNome(nomeProfissional);

            if (profissional != null) {
                btnBuscarFoto.setEnabled(true);
                tctProfissionalId.setText(id + "");
                tctProfissionalId.setEditable(false);
                tctProfissionalId.setText(profissional.getIdProfissional());
                tctNomeProfissional.setText(nomeProfissional);
                tftCelular.setText(profissional.getCelular());
                tftCPF.setText(profissional.getCpf());
                tftRG.setText(profissional.getRg());
                tftEmail.setText(profissional.getEmail());
                
                //tratamento data
                String sdataNascimento = profissional.getNascimento();
                try {
                    calNascimento = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimento.setValue(calNascimento);
                //fim tratamento data
                
                tctNomeContato.setText(profissional.getContato());
                tftTelefoneContato.setText(profissional.getTelefoneContato());
                
                colocarFotoLabel();

                if (verificarFotoExiste(profissional.getFotoProfissional())) {
                    tctFoto.setText(profissional.getFotoProfissional());
                    System.out.println("retorno consulta if: " + verificarFotoExiste(profissional.getFotoProfissional()));
                    colocarFotoLabel();
                } else {
                    // foto = System.getProperty("user.dir") + "\\Imagens\\templateFoto1.png";
                    colocarFotoLabelUrl(foto);
                    System.out.println("retorno consulta else: " + verificarFotoExiste(profissional.getFotoProfissional()));
                    System.out.println(foto);
                }

                tctFoto.setEditable(false);
                
                //colocarFotoLabel();
                btnAlterar.setEnabled(true);
            } else {
                limparTelaProfissional();
            }

            /**
             * Botao Excluir
             */
        } else if (operacao == "e") { //eliminar
            Profissional profissional = new Profissional();
            profissional = cProfissional.receberProfissionalNome(nomeProfissional);

            if (profissional != null) {

                
                tctProfissionalId.setText(id + "");
                tctNomeProfissional.setText(nomeProfissional);
                tftCelular.setText(profissional.getCelular());
                tftCPF.setText(profissional.getCpf());
                tftRG.setText(profissional.getRg());
                tftEmail.setText(profissional.getEmail());
                

                tctProfissionalId.setText(profissional.getIdProfissional());

                //tratamento data
                String sdataNascimento = profissional.getNascimento();
                try {
                    calNascimento = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaProfissional.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimento.setValue(calNascimento);
                jspNascimento.setEnabled(false);
                //fim tratamento data
                
                tctNomeContato.setText(profissional.getContato());
                tftTelefoneContato.setText(profissional.getTelefoneContato());
                
                tctFoto.setText(profissional.getFotoProfissional());
                
                colocarFotoLabel();
                desabilitarEdiçãoTelaProfissional();
                //btnSalvar.setEnabled(true);

                
                tctFoto.setText(profissional.getFotoProfissional());
               
                desabilitarEdiçãoTelaProfissional();
                btnExcluir.setEnabled(true);
                colocarFotoLabel();
            } else {
                limparTelaProfissional();
            }

            /**
             * Botão Consultar
             */
        } else if (operacao == "c") {
            Profissional profissional = new Profissional();
            profissional = cProfissional.receberProfissionalNome(nomeProfissional);

            if (profissional != null) {

                
                tctProfissionalId.setText(id + "");
                tctNomeProfissional.setText(nomeProfissional);
                tftCelular.setText(profissional.getCelular());
                tftCPF.setText(profissional.getCpf());
                tftRG.setText(profissional.getRg());
                tftEmail.setText(profissional.getEmail());
                

                tctProfissionalId.setText(profissional.getIdProfissional());

                //tratamento data
                String sdataNascimento = profissional.getNascimento();
                try {
                    calNascimento = Util.retornaData(sdataNascimento);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaProfissional.class.getName()).log(Level.SEVERE, null, ex);
                }
                jspNascimento.setValue(calNascimento);
                jspNascimento.setEnabled(false);
                //fim tratamento data
                
                
                tctNomeContato.setText(profissional.getContato());
                tftTelefoneContato.setText(profissional.getTelefoneContato());
                
                tctFoto.setText(profissional.getFotoProfissional());
                
                colocarFotoLabel();
                desabilitarEdiçãoTelaProfissional();
                desabilitarBotoesProfissional();
                //btnSalvar.setEnabled(true);

            } else {
                limparTelaProfissional();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTituloProf = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tctProfissionalId = new javax.swing.JTextField();
        lbProfissional = new javax.swing.JLabel();
        lblNomeProfissional = new javax.swing.JLabel();
        tctNomeProfissional = new javax.swing.JTextField();
        lblCelular = new javax.swing.JLabel();
        tftCelular = new javax.swing.JFormattedTextField();
        lblCpf = new javax.swing.JLabel();
        tftCPF = new javax.swing.JFormattedTextField();
        lblRG = new javax.swing.JLabel();
        tftRG = new javax.swing.JFormattedTextField();
        lblEmail = new javax.swing.JLabel();
        tftEmail = new javax.swing.JFormattedTextField();
        lblProfissionalNascimento = new javax.swing.JLabel();
        Date dtIntegradaNP = new Date();

        SpinnerDateModel smIntegradaNP = new 
        SpinnerDateModel(dtIntegradaNP,null,null,Calendar.MONTH + Calendar.DAY_OF_MONTH 
            + Calendar.YEAR );
        jspNascimento = 
        jspNascimento = new javax.swing.JSpinner(smIntegradaNP);
        JSpinner.DateEditor deIntegradaNP = new 
        JSpinner.DateEditor(jspNascimento,"dd/MM/yyyy");
        jspNascimento.setEditor(deIntegradaNP);
        lblContato = new javax.swing.JLabel();
        tctNomeContato = new javax.swing.JTextField();
        lbltelefoneContato = new javax.swing.JLabel();
        tftTelefoneContato = new javax.swing.JFormattedTextField();
        lblFotoProfissional = new javax.swing.JLabel();
        tctFoto = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnBuscarFoto = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(228, 196, 154));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 140, 140), 2, true));

        lblTituloProf.setBackground(new java.awt.Color(102, 102, 102));
        lblTituloProf.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        lblTituloProf.setForeground(new java.awt.Color(102, 102, 102));
        lblTituloProf.setText("Profissional Pet");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(lblTituloProf, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lblTituloProf)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 234, 207));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 140, 140), 2, true));

        tctProfissionalId.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctProfissionalId.setForeground(new java.awt.Color(51, 51, 51));
        tctProfissionalId.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lbProfissional.setBackground(new java.awt.Color(102, 102, 102));
        lbProfissional.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lbProfissional.setForeground(new java.awt.Color(102, 102, 102));
        lbProfissional.setText("Id:");

        lblNomeProfissional.setBackground(new java.awt.Color(102, 102, 102));
        lblNomeProfissional.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblNomeProfissional.setForeground(new java.awt.Color(102, 102, 102));
        lblNomeProfissional.setText("Nome:");

        tctNomeProfissional.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctNomeProfissional.setForeground(new java.awt.Color(51, 51, 51));
        tctNomeProfissional.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lblCelular.setBackground(new java.awt.Color(102, 102, 102));
        lblCelular.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblCelular.setForeground(new java.awt.Color(102, 102, 102));
        lblCelular.setText("Celular:");

        try {
            tftCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tftCelular.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblCpf.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblCpf.setForeground(new java.awt.Color(102, 102, 102));
        lblCpf.setText("CPF:");

        try {
            tftCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tftCPF.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tftCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftCPFActionPerformed(evt);
            }
        });

        lblRG.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblRG.setForeground(new java.awt.Color(102, 102, 102));
        lblRG.setText("RG:");

        try {
            tftRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-A")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tftRG.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblEmail.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(102, 102, 102));
        lblEmail.setText("Email:");

        tftEmail.setForeground(new java.awt.Color(51, 51, 51));
        tftEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tftEmail.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblProfissionalNascimento.setBackground(new java.awt.Color(102, 102, 102));
        lblProfissionalNascimento.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblProfissionalNascimento.setForeground(new java.awt.Color(102, 102, 102));
        lblProfissionalNascimento.setText("Nascimento:");

        jspNascimento.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jspNascimento.setToolTipText("Escolha Dia, Mês e Ano");
        jspNascimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspNascimentoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jspNascimentoMouseReleased(evt);
            }
        });

        lblContato.setBackground(new java.awt.Color(102, 102, 102));
        lblContato.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblContato.setForeground(new java.awt.Color(102, 102, 102));
        lblContato.setText("Nome Contato:");

        tctNomeContato.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tctNomeContato.setForeground(new java.awt.Color(51, 51, 51));
        tctNomeContato.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        lbltelefoneContato.setBackground(new java.awt.Color(102, 102, 102));
        lbltelefoneContato.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lbltelefoneContato.setForeground(new java.awt.Color(102, 102, 102));
        lbltelefoneContato.setText("Telefone Contato:");

        try {
            tftTelefoneContato.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tftTelefoneContato.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        lblFotoProfissional.setText(" ");
        lblFotoProfissional.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 124), 2, true));

        tctFoto.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        tctFoto.setForeground(new java.awt.Color(51, 51, 51));
        tctFoto.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(156, 116, 64));
        btnSalvar.setText("Salvar Novo");
        btnSalvar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(156, 116, 64));
        btnAlterar.setText("Alterar");
        btnAlterar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(156, 116, 64));
        btnExcluir.setText("Excluir");
        btnExcluir.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnBuscarFoto.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarFoto.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnBuscarFoto.setForeground(new java.awt.Color(156, 116, 64));
        btnBuscarFoto.setText("Localizar Foto");
        btnBuscarFoto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarFoto.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFotoActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(156, 116, 64));
        btnVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(0, 0, 51));
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbProfissional, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNomeProfissional, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCelular, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCpf, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRG, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblProfissionalNascimento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblContato, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbltelefoneContato, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tctProfissionalId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tctNomeProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftRG, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jspNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftTelefoneContato, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tctNomeContato, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFotoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tctFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblFotoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tctFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbProfissional)
                            .addComponent(tctProfissionalId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomeProfissional)
                            .addComponent(tctNomeProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tftCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCelular))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCpf)
                            .addComponent(tftCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRG)
                            .addComponent(tftRG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tftEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jspNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProfissionalNascimento))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblContato)
                            .addComponent(tctNomeContato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tftTelefoneContato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltelefoneContato))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAlterar, btnExcluir, btnSalvar});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jspNascimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspNascimentoMouseClicked
        jspNascimento.getEditor().setBackground(Color.black);
        jspNascimento.getEditor().setForeground(Color.black);
    }//GEN-LAST:event_jspNascimentoMouseClicked

    private void jspNascimentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspNascimentoMouseReleased
        // TODO add your handling code here:
        jspNascimento.getEditor().setBackground(Color.black);
        jspNascimento.getEditor().setForeground(Color.black);
    }//GEN-LAST:event_jspNascimentoMouseReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        Profissional profissional = new Profissional();
        ProfissionalCtrl cProfissional = new ProfissionalCtrl();
        
        profissional.setIdProfissional(tctProfissionalId.getText());
        profissional.setNome(tctNomeProfissional.getText());
        profissional.setCelular(tftCelular.getText());
        profissional.setCpf(tftCPF.getText());
        profissional.setRg(tftRG.getText());
        profissional.setEmail(tftEmail.getText());
        
        dataNascimentoProfissional = Util.DataFormatadaS(jspNascimento.getValue().toString());
        //profissional.setNascimento(dataHoje);
        
        profissional.setNascimento(dataNascimentoProfissional);
        
        profissional.setContato(tctNomeContato.getText());
        profissional.setTelefoneContato(tftTelefoneContato.getText());
        
        profissional.setFotoProfissional(tctFoto.getText());

        boolean resp = validarTelaProfissional(profissional);

        if (resp) {
            cProfissional.cInserirProfissional(profissional);
            this.dispose();
        }
        //limparTelaProfissional();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // Botao alterar profissional
        Profissional profissional = new Profissional();
        ProfissionalCtrl cProfissional = new ProfissionalCtrl();
        
        profissional.setIdProfissional(tctProfissionalId.getText());
        profissional.setNome(tctNomeProfissional.getText());
        profissional.setCelular(tftCelular.getText());
        profissional.setCpf(tftCPF.getText());
        profissional.setRg(tftRG.getText());
        profissional.setEmail(tftEmail.getText());

        dataNascimentoProfissional = Util.DataFormatadaS(jspNascimento.getValue().toString());
        profissional.setNascimento(dataNascimentoProfissional);
        
        profissional.setContato(tctNomeContato.getText());
        profissional.setTelefoneContato(tftTelefoneContato.getText());
        
        profissional.setFotoProfissional(tctFoto.getText());

        
        boolean resp = validarTelaProfissional(profissional);

        if (resp) {
            cProfissional.cAlterarProfissional(profissional, profissional.getIdProfissional());
            this.dispose();
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Código Botão excluir
        ProfissionalCtrl cProfissional = new ProfissionalCtrl();
        String nomeProfissional = tctNomeProfissional.getText();
        int idProfissional = Integer.parseInt(tctProfissionalId.getText());
        cProfissional.cDelerarProfissional(nomeProfissional, idProfissional);
        this.dispose();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFotoActionPerformed
        // metodo para buscar um arquivo de foto
        jFileChooserFoto = new javax.swing.JFileChooser();
        String fotoSource = null;

        String fotoDestino = System.getProperty("user.dir") + "\\ImagensPet\\";
        String fotoNome = tctNomeProfissional.getText() + "-" + tctProfissionalId.getText();

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
        copiarFotoTofast(fotoSource, fotoDestino, fotoNome);
        tctFoto.setText(fotoDestino + fotoNome);
        colocarFotoLabel();
    }//GEN-LAST:event_btnBuscarFotoActionPerformed

    private void tftCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftCPFActionPerformed

    }//GEN-LAST:event_tftCPFActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProfissional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProfissional("Teste", 0, "c").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscarFoto;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner jspNascimento;
    private javax.swing.JLabel lbProfissional;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblContato;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFotoProfissional;
    private javax.swing.JLabel lblNomeProfissional;
    private javax.swing.JLabel lblProfissionalNascimento;
    private javax.swing.JLabel lblRG;
    private javax.swing.JLabel lblTituloProf;
    private javax.swing.JLabel lbltelefoneContato;
    private static javax.swing.JTextField tctFoto;
    private static javax.swing.JTextField tctNomeContato;
    private static javax.swing.JTextField tctNomeProfissional;
    private static javax.swing.JTextField tctProfissionalId;
    private javax.swing.JFormattedTextField tftCPF;
    private javax.swing.JFormattedTextField tftCelular;
    private javax.swing.JFormattedTextField tftEmail;
    private javax.swing.JFormattedTextField tftRG;
    private javax.swing.JFormattedTextField tftTelefoneContato;
    // End of variables declaration//GEN-END:variables

    private void desabilitarBotoesProfissional() {
         //desabilitar os botoes da tela no método construtor
        btnSalvar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnBuscarFoto.setEnabled(false);
    }

    private void colocarFotoLabelUrl(String urlFoto) {
        lblFotoProfissional.setIcon(null);
        Dimension d = lblFotoProfissional.getSize();
        //int width = tctPetFoto.getWidth();
        //int height = tctPetFoto.getHeight();
        //System.out.println("width: "+d.width + " height: "+d.height);
        //String urlFoto = tctPetFoto.getText();
        ImageIcon foto;
        foto = new ImageIcon(urlFoto);

        foto.setImage(foto.getImage().getScaledInstance((d.width - 20), (d.height - 20), 100));
        //img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
        lblFotoProfissional.setIcon(foto);
        //lblFotoPet.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlFoto)));
    }

    private void colocarFotoLabel() {
        lblFotoProfissional.setIcon(null);
        Dimension d = lblFotoProfissional.getSize();
        //int width = tctPetFoto.getWidth();
        //int height = tctPetFoto.getHeight();
        //System.out.println("width: "+d.width + " height: "+d.height);
        String urlFoto = tctFoto.getText();
        ImageIcon foto;
        foto = new ImageIcon(urlFoto);

        foto.setImage(foto.getImage().getScaledInstance((d.width - 20), (d.height - 20), 100));
        //img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
        lblFotoProfissional.setIcon(foto);
        //lblFotoPet.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlFoto)));
    }

    private void limparTelaProfissional() {
        tctProfissionalId.setText("");
       tctNomeProfissional.setText("");
       tftCelular.setText("");
       tftCPF.setText("");
       tftRG.setText("");
       tftEmail.setText("");
       jspNascimento.setValue(dtProf);
       tctNomeContato.setText("");
       tftTelefoneContato.setText("");
       tctFoto.setText("");
    }

    private void desabilitarEdiçãoTelaProfissional() {
       tctProfissionalId.setEnabled(false);
       tctNomeProfissional.setEnabled(false);
       tftCelular.setEnabled(false);
       tftCPF.setEnabled(false);
       tftRG.setEnabled(false);
       tftEmail.setEnabled(false);
       jspNascimento.setEnabled(false);
       tctNomeContato.setEnabled(false);
       tftTelefoneContato.setEnabled(false);
       tctFoto.setEnabled(false);
       
       
    }

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
    }

    private boolean validarTelaProfissional(Profissional profissional) {
        boolean resposta = true;
        
        
        
        
        return resposta;  
    }

    private void copiarFotoTofast(String fonte, String destino, String nomeArquivo) {
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
            Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(TelaCadastroProfissional.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }//long stop = new Date().getTime();
        //System.out.println("Tempo de copia:"}
}
