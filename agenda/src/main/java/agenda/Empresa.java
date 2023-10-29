/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

/**
 *
 * @author nhale
 */
public class Empresa extends Contacto{

    public Empresa(String name, String number) {
        super(name, number);
    }
    
    public String getDireccion(){
        return super.getValor("direccion");
    }
    
    public boolean setDireccion(String direccion){
        return super.setAtributte("direccion", direccion);
    }
    
    
}
