
package agenda;


public class Persona extends Contacto {

    public Persona(String name, String number, String userName) {
        super(name, number, userName);
    }
    
    public String getDireccion(){
        return super.getValorAtributte("direccion");
    }
    
    public boolean setDireccion(String direccion){
        return super.setAtributte("direccion", direccion);
    }
    
    
}
