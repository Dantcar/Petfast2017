/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.AgendamentoCtrl;
import controle.AnimalCtrl;
import controle.ClienteCtrl;
import controle.Util;
import static controle.Util.DataFormatadaS;
import controle.ValidaCampos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import modelo.Agendamento;
import modelo.Animal;
import modelo.Cliente;

/**
 *
 * @author deciodecarvalho
 */
public class TelaAgendaServico extends javax.swing.JFrame {

    private static final int DT_WIDTH = 700;
    private static final int DT_HEIGHT = 500;
    private static final Dimension DESKTOP_SIZE = new Dimension(DT_WIDTH, DT_HEIGHT);
    private JDesktopPane desktop = new JDesktopPane();

    private Animal animal;
    private Cliente cliente;
    private Agendamento agendamento;
    private String urlMiniFoto, nomeAnimal;
    private int idServico;
    
    private int setPb = 0;
    
    private static Date hojeAgendamento;
    private static SimpleDateFormat sdfDataAgendamento, sdfHojeAgendamento;
    private static String dataAgendamento, dataHojeAgendamento;

    private static int openFrameCount = 0; //teste
    private static final int xOffset = 30, yOffset = 30; //teste

    /*
     private static String[] horarioServ = {"08:00",
     "09:00",
     "10:00",
     "11:00",
     "12:00",
     "13:00",
     "14:00",
     "15:00",
     "16:00",
     "17:00",
     "18:00",            
     }; //horários disponíveis
     */
    /**
     * Creates new form TelaAgendaServico
     */
    public TelaAgendaServico() {
        UIManager.put("jbpDisponibilidade.background", Color.orange);
        UIManager.put("jbpDisponibilidade.foreground", Color.blue);
        UIManager.put("jbpDisponibilidade.selectionBackground", Color.red);
        UIManager.put("jbpDisponibilidade.selectionForeground", Color.green);
        
        initComponents();

        // Center in the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        //setLocation(new Point((screenSize.width - frameSize.width) / 2,
        //                     (screenSize.height - frameSize.width) / 2));

        //this.setLocation(50, 100); //(ponto inicial apartir lateral,altura)
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        setLocation(new Point((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.width) / 2));
        this.repaint();

        
        
       
        
        /*
         configurando formato do calendário
         */
        jdpAgendamento.setDate(Calendar.getInstance().getTime());
        jdpAgendamento.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

        hojeAgendamento = new Date();
        dataHojeAgendamento = Util.DataFormatada(hojeAgendamento);
        sdfHojeAgendamento = new SimpleDateFormat("dd/MM/yyyy");

        try {
            hojeAgendamento = sdfHojeAgendamento.parse(dataHojeAgendamento);
        } catch (ParseException ex) {
            Logger.getLogger(TelaAgendamentoClientePetOld.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TelaAgendaServico(int vidAnimal, int vidCliente) {
        initComponents();
        desktop.setPreferredSize(DESKTOP_SIZE);
        String pathProjeto = System.getProperty("user.dir") + "//";
        String iconPetfast = pathProjeto + "src//Icones//petfastIcone.png";
        //System.out.println(iconPetfast);
        setIconImage(Toolkit.getDefaultToolkit().getImage(iconPetfast));
        // Center in the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        //setLocation(new Point((screenSize.width - frameSize.width) / 2,
        //                     (screenSize.height - frameSize.width) / 2));

        //this.setLocation(50, 100); //(ponto inicial apartir lateral,altura)
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        setLocation(new Point((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.width) / 2));
        this.repaint();

        /*
         configurando formato do calendário
         */
        jdpAgendamento.setDate(Calendar.getInstance().getTime());
        jdpAgendamento.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

        hojeAgendamento = new Date();
        dataHojeAgendamento = Util.DataFormatada(hojeAgendamento);
        sdfHojeAgendamento = new SimpleDateFormat("dd/MM/yyyy");

        try {
            hojeAgendamento = sdfHojeAgendamento.parse(dataHojeAgendamento);
        } catch (ParseException ex) {
            Logger.getLogger(TelaAgendamentoClientePetOld.class.getName()).log(Level.SEVERE, null, ex);
        }

        //montar os dados iniciais da tela agendamento.
        montaTelaAGenda(vidAnimal, vidCliente);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAgendaTitulo = new javax.swing.JPanel();
        pnlAgenda = new javax.swing.JPanel();
        pnlAbasAgenda = new javax.swing.JTabbedPane();
        jpnlMesAtual = new javax.swing.JPanel();
        lblClienteAgenda = new javax.swing.JLabel();
        lbltCliente = new javax.swing.JLabel();
        lblPetClienteAgenda = new javax.swing.JLabel();
        lbltPetCliente = new javax.swing.JLabel();
        lbltServiço = new javax.swing.JLabel();
        cbxServico = new javax.swing.JComboBox();
        lblfotoPetAgenda = new javax.swing.JLabel();
        lbltIdPetClienteAgenda = new javax.swing.JLabel();
        lblIdClienteAgenda = new javax.swing.JLabel();
        lbltidPet = new javax.swing.JLabel();
        lblIdPetAgenda = new javax.swing.JLabel();
        jdpAgendamento = new org.jdesktop.swingx.JXDatePicker();
        lblDataAgendamento = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        cbxHoraAgendamento = new javax.swing.JComboBox();
        lbHoraAgendamento = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        lblAgendamentoId = new javax.swing.JLabel();
        tctIdAgendamento = new javax.swing.JLabel();
        lblServicoId = new javax.swing.JLabel();
        tctIdServico = new javax.swing.JLabel();
        lblDisponibilidade = new javax.swing.JLabel();
        lbHoraAgendamentoOcupacao = new javax.swing.JLabel();
        jbpDisponibilidade = new javax.swing.JProgressBar();
        lblTituloAgendaPet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlAgendaTitulo.setBackground(new java.awt.Color(229, 255, 255));
        pnlAgendaTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        pnlAgendaTitulo.setInheritsPopupMenu(true);

        pnlAgenda.setBackground(new java.awt.Color(204, 229, 229));
        pnlAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        pnlAgenda.setInheritsPopupMenu(true);

        pnlAbasAgenda.setBackground(new java.awt.Color(229, 255, 255));
        pnlAbasAgenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jpnlMesAtual.setBackground(new java.awt.Color(255, 255, 255));

        lblClienteAgenda.setBackground(new java.awt.Color(204, 204, 204));
        lblClienteAgenda.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        lblClienteAgenda.setForeground(new java.awt.Color(102, 102, 102));
        lblClienteAgenda.setText(" ");
        lblClienteAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        lbltCliente.setBackground(new java.awt.Color(204, 204, 204));
        lbltCliente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbltCliente.setForeground(new java.awt.Color(102, 102, 102));
        lbltCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltCliente.setText("Cliente:");

        lblPetClienteAgenda.setBackground(new java.awt.Color(204, 204, 204));
        lblPetClienteAgenda.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        lblPetClienteAgenda.setForeground(new java.awt.Color(102, 102, 102));
        lblPetClienteAgenda.setText(" ");
        lblPetClienteAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        lbltPetCliente.setBackground(new java.awt.Color(204, 204, 204));
        lbltPetCliente.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbltPetCliente.setForeground(new java.awt.Color(102, 102, 102));
        lbltPetCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltPetCliente.setText("Animal Pet:");

        lbltServiço.setBackground(new java.awt.Color(204, 204, 204));
        lbltServiço.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbltServiço.setForeground(new java.awt.Color(102, 102, 102));
        lbltServiço.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltServiço.setText("Serviço Agendado:");

        cbxServico.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        cbxServico.setForeground(new java.awt.Color(102, 102, 102));
        cbxServico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Banho", "Tosagem", "Higienização", " " }));
        cbxServico.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        cbxServico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicoItemStateChanged(evt);
            }
        });
        cbxServico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxServicoPropertyChange(evt);
            }
        });
        cbxServico.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                cbxServicoVetoableChange(evt);
            }
        });

        lblfotoPetAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        lbltIdPetClienteAgenda.setBackground(new java.awt.Color(204, 204, 204));
        lbltIdPetClienteAgenda.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbltIdPetClienteAgenda.setForeground(new java.awt.Color(102, 102, 102));
        lbltIdPetClienteAgenda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltIdPetClienteAgenda.setText("Cliente id:");

        lblIdClienteAgenda.setBackground(new java.awt.Color(204, 204, 204));
        lblIdClienteAgenda.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        lblIdClienteAgenda.setForeground(new java.awt.Color(102, 102, 102));
        lblIdClienteAgenda.setText(" ");
        lblIdClienteAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        lbltidPet.setBackground(new java.awt.Color(204, 204, 204));
        lbltidPet.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbltidPet.setForeground(new java.awt.Color(102, 102, 102));
        lbltidPet.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltidPet.setText("Pet id");

        lblIdPetAgenda.setBackground(new java.awt.Color(204, 204, 204));
        lblIdPetAgenda.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        lblIdPetAgenda.setForeground(new java.awt.Color(102, 102, 102));
        lblIdPetAgenda.setText(" ");
        lblIdPetAgenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        jdpAgendamento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jdpAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdpAgendamentoActionPerformed(evt);
            }
        });
        jdpAgendamento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdpAgendamentoPropertyChange(evt);
            }
        });
        jdpAgendamento.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jdpAgendamentoVetoableChange(evt);
            }
        });

        lblDataAgendamento.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblDataAgendamento.setForeground(new java.awt.Color(102, 102, 102));
        lblDataAgendamento.setText("Data Agendamento : ");

        btnConfirmar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnConfirmar.setText("Agendar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        cbxHoraAgendamento.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        cbxHoraAgendamento.setModel(new modelo.ModeloCbxHorario());
        cbxHoraAgendamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHoraAgendamentoItemStateChanged(evt);
            }
        });
        cbxHoraAgendamento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxHoraAgendamentoPropertyChange(evt);
            }
        });

        lbHoraAgendamento.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lbHoraAgendamento.setForeground(new java.awt.Color(102, 102, 102));
        lbHoraAgendamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHoraAgendamento.setText("Hora Agendamento : ");

        btnVoltar.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblAgendamentoId.setBackground(new java.awt.Color(204, 204, 204));
        lblAgendamentoId.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAgendamentoId.setForeground(new java.awt.Color(102, 102, 102));
        lblAgendamentoId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAgendamentoId.setText("Agendamento id:");

        tctIdAgendamento.setBackground(new java.awt.Color(204, 204, 204));
        tctIdAgendamento.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        tctIdAgendamento.setForeground(new java.awt.Color(102, 102, 102));
        tctIdAgendamento.setText(" ");
        tctIdAgendamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));

        lblServicoId.setBackground(new java.awt.Color(204, 204, 204));
        lblServicoId.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblServicoId.setForeground(new java.awt.Color(140, 140, 140));
        lblServicoId.setText("Serviço id:");

        tctIdServico.setBackground(new java.awt.Color(51, 51, 51));
        tctIdServico.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        tctIdServico.setForeground(new java.awt.Color(140, 140, 140));

        lblDisponibilidade.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblDisponibilidade.setForeground(new java.awt.Color(102, 102, 102));
        lblDisponibilidade.setText("Disponibilidade:");

        lbHoraAgendamentoOcupacao.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lbHoraAgendamentoOcupacao.setForeground(new java.awt.Color(102, 102, 102));
        lbHoraAgendamentoOcupacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbHoraAgendamentoOcupacao.setText("Ocupação deste horário:");

        jbpDisponibilidade.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jbpDisponibilidade.setMaximum(8);

        javax.swing.GroupLayout jpnlMesAtualLayout = new javax.swing.GroupLayout(jpnlMesAtual);
        jpnlMesAtual.setLayout(jpnlMesAtualLayout);
        jpnlMesAtualLayout.setHorizontalGroup(
            jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addComponent(lbltidPet, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIdPetAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addComponent(lbltPetCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(lblPetClienteAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addComponent(lbltCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblClienteAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                    .addComponent(lblAgendamentoId, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tctIdAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                    .addComponent(lbltIdPetClienteAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblIdClienteAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(lblfotoPetAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                    .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addComponent(lblDataAgendamento)
                                .addGap(18, 18, 18)
                                .addComponent(jdpAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbHoraAgendamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxHoraAgendamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbHoraAgendamentoOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbpDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(lblDisponibilidade))))
                    .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                        .addComponent(lbltServiço, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxServico, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblServicoId)
                        .addGap(18, 18, 18)
                        .addComponent(tctIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnlMesAtualLayout.setVerticalGroup(
            jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tctIdAgendamento)
                            .addComponent(lblAgendamentoId))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdClienteAgenda)
                            .addComponent(lbltIdPetClienteAgenda))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClienteAgenda)
                            .addComponent(lbltCliente))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdPetAgenda)
                            .addComponent(lbltidPet))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltPetCliente)
                            .addComponent(lblPetClienteAgenda)))
                    .addComponent(lblfotoPetAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tctIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbltServiço, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxServico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblServicoId)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataAgendamento)
                    .addComponent(jdpAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxHoraAgendamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoraAgendamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlMesAtualLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblDisponibilidade)
                        .addGap(42, 42, 42))
                    .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                        .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnlMesAtualLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbpDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnlMesAtualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoraAgendamentoOcupacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        jpnlMesAtualLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxHoraAgendamento, jbpDisponibilidade, jdpAgendamento});

        jpnlMesAtualLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbxServico, tctIdServico});

        pnlAbasAgenda.addTab("Mês Atual", jpnlMesAtual);

        lblTituloAgendaPet.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        lblTituloAgendaPet.setForeground(new java.awt.Color(102, 102, 102));
        lblTituloAgendaPet.setText("Agenda de Serviços Petfast");

        javax.swing.GroupLayout pnlAgendaLayout = new javax.swing.GroupLayout(pnlAgenda);
        pnlAgenda.setLayout(pnlAgendaLayout);
        pnlAgendaLayout.setHorizontalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(lblTituloAgendaPet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAbasAgenda)
                .addContainerGap())
        );
        pnlAgendaLayout.setVerticalGroup(
            pnlAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloAgendaPet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlAbasAgenda)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAgendaTituloLayout = new javax.swing.GroupLayout(pnlAgendaTitulo);
        pnlAgendaTitulo.setLayout(pnlAgendaTituloLayout);
        pnlAgendaTituloLayout.setHorizontalGroup(
            pnlAgendaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgendaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAgendaTituloLayout.setVerticalGroup(
            pnlAgendaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAgendaTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAgendaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlAgendaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        Agendamento agendar = new Agendamento();
        AgendamentoCtrl agendarCtrl = new AgendamentoCtrl();

        boolean resp = validarTelaAgendamento();

        if (resp) {

            //montando o objeto agendamento
            agendar.setIdAgendamento(Integer.parseInt(tctIdAgendamento.getText()));

            //dataAgendamento
            String datap = null;
            datap = DataFormatadaS(jdpAgendamento.getDate().toString());
            agendar.setDataAgendamento(datap);
            //final dataAgendamento
            agendar.setHoraAgendamento(cbxHoraAgendamento.getSelectedItem().toString());
            agendar.setAnimalId(Integer.parseInt(lblIdPetAgenda.getText()));
            agendar.setClienteId(Integer.parseInt(lblIdClienteAgenda.getText()));
            agendar.setServico(cbxServico.getSelectedItem().toString());
            agendar.setIdServico(Integer.parseInt(tctIdServico.getText()));
            agendar.setIdProfissional(0);

            agendarCtrl.inserirAgendamentoCtrl(agendar);
            this.dispose();
        }


    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbxHoraAgendamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxHoraAgendamentoPropertyChange


    }//GEN-LAST:event_cbxHoraAgendamentoPropertyChange

    private void cbxServicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxServicoPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxServicoPropertyChange

    private void cbxServicoVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_cbxServicoVetoableChange
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxServicoVetoableChange

    private void cbxServicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicoItemStateChanged
        // TODO add your handling code here:
        if (cbxServico.getSelectedIndex() == 0) {
            tctIdServico.setText("1");
        } else if (cbxServico.getSelectedIndex() == 1) {
            tctIdServico.setText("2");
        } else if (cbxServico.getSelectedIndex() == 2) {
            tctIdServico.setText("3");
        }
    }//GEN-LAST:event_cbxServicoItemStateChanged

    private void cbxHoraAgendamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxHoraAgendamentoItemStateChanged
        verificarDisponibilidade();

    }//GEN-LAST:event_cbxHoraAgendamentoItemStateChanged

    private void jdpAgendamentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdpAgendamentoPropertyChange
        // TODO add your handling code here:
        //        verificarDisponibilidade();
    }//GEN-LAST:event_jdpAgendamentoPropertyChange

    private void jdpAgendamentoVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jdpAgendamentoVetoableChange
        // TODO add your handling code here:
        verificarDisponibilidade();
    }//GEN-LAST:event_jdpAgendamentoVetoableChange

    private void jdpAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdpAgendamentoActionPerformed
        // TODO add your handling code here:
        verificarDisponibilidade();
    }//GEN-LAST:event_jdpAgendamentoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAgendaServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAgendaServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAgendaServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAgendaServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAgendaServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox cbxHoraAgendamento;
    private javax.swing.JComboBox cbxServico;
    private javax.swing.JProgressBar jbpDisponibilidade;
    private org.jdesktop.swingx.JXDatePicker jdpAgendamento;
    private javax.swing.JPanel jpnlMesAtual;
    private javax.swing.JLabel lbHoraAgendamento;
    private javax.swing.JLabel lbHoraAgendamentoOcupacao;
    private javax.swing.JLabel lblAgendamentoId;
    private javax.swing.JLabel lblClienteAgenda;
    private javax.swing.JLabel lblDataAgendamento;
    private javax.swing.JLabel lblDisponibilidade;
    private javax.swing.JLabel lblIdClienteAgenda;
    private javax.swing.JLabel lblIdPetAgenda;
    private javax.swing.JLabel lblPetClienteAgenda;
    private javax.swing.JLabel lblServicoId;
    private javax.swing.JLabel lblTituloAgendaPet;
    private javax.swing.JLabel lblfotoPetAgenda;
    private javax.swing.JLabel lbltCliente;
    private javax.swing.JLabel lbltIdPetClienteAgenda;
    private javax.swing.JLabel lbltPetCliente;
    private javax.swing.JLabel lbltServiço;
    private javax.swing.JLabel lbltidPet;
    private javax.swing.JTabbedPane pnlAbasAgenda;
    private javax.swing.JPanel pnlAgenda;
    private javax.swing.JPanel pnlAgendaTitulo;
    private javax.swing.JLabel tctIdAgendamento;
    private javax.swing.JLabel tctIdServico;
    // End of variables declaration//GEN-END:variables

    private void montaTelaAGenda(int vidAnimal, int vidCliente) {
        AnimalCtrl animalCtrl = new AnimalCtrl();
        ClienteCtrl clienteCtrl = new ClienteCtrl();
        AgendamentoCtrl agendarCtrl = new AgendamentoCtrl();
        String idAgendamento = Integer.toString(agendarCtrl.receberIdAgendamentoAtual() + 1);
        tctIdAgendamento.setText(idAgendamento);
        animal = animalCtrl.receberAnimaId(vidAnimal);
        cliente = clienteCtrl.buscarNomeIdInt(vidCliente);
        colocarMiniFotoLabel(animal.getFoto());
        System.out.println(animal.getFoto());
        lblIdClienteAgenda.setText(cliente.getIdCliente());
        lblClienteAgenda.setText(cliente.getNome());
        lblIdPetAgenda.setText(animal.getIdAnimal());
        lblPetClienteAgenda.setText(animal.getNome());
        cbxServico.setSelectedIndex(-1);

    }

    private void colocarMiniFotoLabel(String urlMiniFoto) {
        nomeAnimal = lblPetClienteAgenda.getText();
        Dimension d = lblfotoPetAgenda.getSize();
        //int width = lblfotoPetAgenda.getWidth();
        // int height = lblfotoPetAgenda.getHeight();

        String urlFoto = urlMiniFoto; //pegar do combobox
        System.out.println(urlFoto);
        ImageIcon foto;
        foto = new ImageIcon(urlFoto);

        foto.setImage(foto.getImage().getScaledInstance(d.width, d.height, 100));
        //img.setImage(img.getImage().getScaledInstance(xLargura, yAltura, 100));
        lblfotoPetAgenda.setIcon(foto);
        //lblFotoPet.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlFoto)));
    }

    private boolean validarTelaAgendamento() {
        boolean resposta = true;
        String msg = "";
        String vdata = DataFormatadaS(jdpAgendamento.getDate().toString());
        String vhora ="";
        int quantidadeAgendamentoHora = 0;
        AgendamentoCtrl agendaCtrl = new AgendamentoCtrl();

        if (cbxHoraAgendamento.getSelectedIndex() != -1) {
            vhora = cbxHoraAgendamento.getSelectedItem().toString();
            quantidadeAgendamentoHora = agendaCtrl.contarAgendamentoHorarioCtrl(vdata, vhora);
        }

        String datap = null;
        datap = DataFormatadaS(jdpAgendamento.getDate().toString());
        String jdpAgendamento = datap;

        boolean validaDataAgendamento = ValidaCampos.validaVazio(jdpAgendamento);

        if (cbxHoraAgendamento.getSelectedIndex() == -1) {
            msg = msg + "Campo Hora agendamento Vazio" + "\n";
            resposta = false;
        } else {
            if (quantidadeAgendamentoHora >= 8) {
                msg = msg + "Todos as vagas esgotadas para este horário \n"
                        + "Agendamento para o horário: " + vhora + " indisponível" + "\n ";
                resposta = false;
            }
        }

        if (cbxServico.getSelectedIndex() == -1) {
            msg = msg + "Campo Servico Vazio" + "\n";
            resposta = false;
        }

        if (validaDataAgendamento) {
        } else {
            msg = msg + "Campo Data agendamento Vazio" + "\n";
            resposta = false;
        }

        if (resposta == false) {
            JOptionPane.showMessageDialog(this, msg);
            msg = "";
        }

        return resposta;
    }

    private void verificarDisponibilidade() {
        if (cbxHoraAgendamento.getSelectedIndex() != -1) {
            

            String vdata = DataFormatadaS(jdpAgendamento.getDate().toString());
            String vhora = cbxHoraAgendamento.getSelectedItem().toString();
            int totalAgendamentosHora = 0;
            AgendamentoCtrl agendaCtrl = new AgendamentoCtrl();
            totalAgendamentosHora = agendaCtrl.contarAgendamentoHorarioCtrl(vdata, vhora);
            setPb = totalAgendamentosHora;
            jbpDisponibilidade.setValue(setPb);
            jbpDisponibilidade.setStringPainted(true);
            
            criarBarraProgressoPet(setPb);
            
            switch (setPb) {
                case 0:
                    lblDisponibilidade.setText("Horários Disponíveis: 8");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;

                case 1:
                    lblDisponibilidade.setText("Horários Disponíveis: 7");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 2:
                    lblDisponibilidade.setText("Horários Disponíveis: 6");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 3:
                    lblDisponibilidade.setText("Horários Disponíveis: 5");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 4:
                    lblDisponibilidade.setText("Horários Disponíveis: 4");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 5:
                    lblDisponibilidade.setText("Horários Disponíveis: 3");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 6:
                    lblDisponibilidade.setText("Horários Disponíveis: 2");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 7:
                    
                    lblDisponibilidade.setText("Horários Disponíveis: 1");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;
                case 8:
                    lblDisponibilidade.setText("Horário Indisponível");
                    /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/
                    break;

                default:
                    lblDisponibilidade.setText("Horários Disponíveis");
                   /*elemento removido jbpDisponibilidade.setBackground(Color.green);*/

            }
                
                
            /*elemento removido jbpDisponibilidade.setValue(setPb); */

        }
    }

    private void criarBarraProgressoPet(int value) {
        JProgressBar jPBar_barra;
                
        jPBar_barra = new javax.swing.JProgressBar(0, 8);
        
        //jPBar_barra.setBackground(Color.red);
        jPBar_barra.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N  
        jPBar_barra.setForeground(new java.awt.Color(0, 0, 153));
        jPBar_barra.setOpaque(true);
        jPBar_barra.setStringPainted(true);
        
        jPBar_barra.setValue(value); //determina o valor da barra de progresso//
       
        getContentPane().add(jPBar_barra);
        jPBar_barra.setBounds(185, 430, 200, 20);
        this.repaint();
    }

}
