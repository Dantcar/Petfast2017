/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.AgendamentoCtrl;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Décio
 */
public class AgendamentoTableModel extends AbstractTableModel {

    private List<Agendamento> dados;
    private String[] colunas = {"id", "Data", "Hora", "idPet","idCliente", "IdServico", "Servico", "idProfissional"};

    public AgendamentoTableModel(){
        AgendamentoCtrl agendamentoCtrl = new AgendamentoCtrl();
        dados = agendamentoCtrl.listarAgendamentosCtrl();
    }
    public void addRow(Agendamento a) {
        this.dados.add(a);
        this.fireTableDataChanged();
    }
    
    public String getColumnName(int num){
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        
        return dados.size();
        
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return dados.get(linha).getIdAgendamento();
            case 1: return dados.get(linha).getDataAgendamento();
            case 2: return dados.get(linha).getHoraAgendamento();
            case 3: return dados.get(linha).getAnimalId();
            case 4: return dados.get(linha).getClienteId();
            case 5: return dados.get(linha).getIdServico();
            case 6: return dados.get(linha).getServico();
            case 7: return dados.get(linha).getIdProfissional();
        }
        return null;
    }

}
