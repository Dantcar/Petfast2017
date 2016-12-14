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
public class Animal {

    public static int getVidAnimal() {
        return vidAnimal;
    }

    public static void setVidAnimal(int aVidAnimal) {
        vidAnimal = aVidAnimal;
    }
    private String idCliente; //chave estrangeira do dono do animal
    private String idAnimal;
    private String nome;
    private String especie;
    private String nascimento;
    private String raca;
    private String peso;
    private String altura;
    private String cor;
    private String caracteristica;
    private String sexo;
    private static int vidAnimal;
    private String Foto;
    
    public Animal() {
    }

   
    /**
     * 
     * @param idCliente
     * @param idAnimal
     * @param nome
     * @param especie
     * @param nascimento
     * @param raca
     * @param peso
     * @param altura
     * @param cor
     * @param caracteristica
     * @param sexo
     * @param Foto 
     */
    public Animal(String idCliente, String idAnimal, String nome, String especie, String nascimento, String raca, String peso, String altura, String cor, String caracteristica, String sexo, String Foto) {
        this.idCliente = idCliente;
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.especie = especie;
        this.nascimento = nascimento;
        this.raca = raca;
        this.peso = peso;
        this.altura = altura;
        this.cor = cor;
        this.caracteristica = caracteristica;
        this.sexo = sexo;
        this.Foto = Foto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

   

}//final classe Animal

