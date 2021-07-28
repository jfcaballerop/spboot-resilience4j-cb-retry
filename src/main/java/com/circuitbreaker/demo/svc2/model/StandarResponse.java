package com.circuitbreaker.demo.svc2.model;

public class StandarResponse<T> {
    
    public String error;
    public int codigo;
    public String mensaje;
    public T datos;
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public T getDatos() {
        return datos;
    }
    public void setDatos(T datos) {
        this.datos = datos;
    }
    public StandarResponse() {
    }
    public StandarResponse(String error, int codigo, String mensaje, T datos) {
        this.error = error;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.datos = datos;
    }
    
    


}
