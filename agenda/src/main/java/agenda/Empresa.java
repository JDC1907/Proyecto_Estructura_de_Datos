/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

import java.util.HashMap;

/**
 *
 * @author nhale
 */
public class Empresa extends Contacto{
    
    private HashMap<String, Contacto> empleados;
    
    public Empresa(String name, String number, String userName) {
        super(name, number, userName);
    }
    
    public String getDireccion(){
        return super.getValorAtributte("direccion");
    }
    
    public boolean setDireccion(String direccion){
        return super.setAtributte("direccion", direccion);
    }
    
    
}
