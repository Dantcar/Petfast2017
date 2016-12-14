/*
 * Este Software tem Objetivo Educacional
 * Para fins de TCC
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembro  
 * Aluno Decio Antonio de Carvalho  * 
 */

package modelo;

/**
 *
 * @author Décio
 */
public class Agendamento {

    
    private int idAgendamento;
    private String dataAgendamento;
    private String horaAgendamento;
    private int animalId;
    private int clienteId;
    private String servico;
    private int idServico;
    private int idProfissional;
    

    public Agendamento() {
    }

    /**
     * método construtor
     * @param idAgendamento
     * @param dataAgendamento
     * @param horaAgendamento
     * @param animalId
     * @param clienteId
     * @param servico
     * @param idServico
     * @param idProfissional 
     */
    public Agendamento(int idAgendamento, String dataAgendamento, String horaAgendamento, int animalId, int clienteId, String servico, int idServico, int idProfissional) {
        this.idAgendamento = idAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.animalId = animalId;
        this.clienteId = clienteId;
        this.servico = servico;
        this.idServico = idServico;
        this.idProfissional = idProfissional;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "idAgendamento=" + idAgendamento + ", dataAgendamento=" + dataAgendamento + ", horaAgendamento=" + horaAgendamento + ", animalId=" + animalId + ", clienteId=" + clienteId + ", servico=" + servico + ", idServico=" + idServico + ", idProfissional=" + idProfissional + '}';
    }

    
    
    
    
    
}//final classe Agendamento

