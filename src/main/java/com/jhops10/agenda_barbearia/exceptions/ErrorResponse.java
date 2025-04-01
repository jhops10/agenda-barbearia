package com.jhops10.agenda_barbearia.exceptions;

public class ErrorResponse {

    private String erro;
    private String mensagem;

    public ErrorResponse(String erro, String mensagem) {
        this.erro = erro;
        this.mensagem = mensagem;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
