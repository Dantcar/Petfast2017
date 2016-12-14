/*
 * Este Software tem Objetivo Educacional
 * Para fins de TCC
 *  do Curso de Analise de Sistemas da Fatec - Ipiranga
 * Ano 2016 - Julho a Dezembnro  
 * Aluno Decio Antonio de Carvalho  * 
 */
package modelo;

/**
 *
 * @author Décio
 */
public class Profissional {
    
   private String idProfissional; 
   private String nome;
   private String FotoProfissional;
   private String celular;
   private String email;
   private String cpf;
   private String rg;
   private String nascimento;
   private String contato;
   private String telefoneContato;

    public Profissional() {
    }
    /**
     * 
     * @param idProfissional
     * @param nome
     * @param FotoProfissional
     * @param celular
     * @param email
     * @param cpf
     * @param rg
     * @param nascimento
     * @param contato
     * @param telefoneContato 
     */
    public Profissional(String idProfissional, String nome, String FotoProfissional, String celular, String email, String cpf, String rg, String nascimento, String contato, String telefoneContato) {
        this.idProfissional = idProfissional;
        this.nome = nome;
        this.FotoProfissional = FotoProfissional;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.contato = contato;
        this.telefoneContato = telefoneContato;
    }

    public String getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(String idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoProfissional() {
        return FotoProfissional;
    }

    public void setFotoProfissional(String FotoProfissional) {
        this.FotoProfissional = FotoProfissional;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    @Override
    public String toString() {
        return "Profissional{" 
                + "idProfissional=" + idProfissional 
                + ", nome=" + nome 
                + ", FotoProfissional=" + FotoProfissional 
                + ", celular=" + celular 
                + ", email=" + email 
                + ", cpf=" + cpf 
                + ", rg=" + rg 
                + ", nascimento=" + nascimento 
                + ", contato=" + contato 
                + ", telefoneContato=" + telefoneContato 
                + "}";
    }
    
    
   
   
}
