
package agenda;


public class Persona extends Contacto {

    public Persona(String name, String number) {
        super(name, number);
    }
    
    public String getDireccion(){
        return super.getValor("direccion");
    }
    
    public boolean setDireccion(String direccion){
        return super.setAtributte("direccion", direccion);
    }
    
    
}
