
package agenda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import tda.ArrayList;
import tda.LinkedList;


public abstract class Contacto {
    private HashSet tags;
    private HashMap<String, String> atributos;
    private LinkedList<String> photos;

    public Contacto(String name, String number) {
        tags = new HashSet();
        atributos = new HashMap<>();
        photos = new LinkedList();
        atributos.put("number", number);
        atributos.put("name", name);       
    }

    public String getName() {
        return atributos.get("name");
    }

    public void setName(String name) {
        this.atributos.replace("name", name);
    }

    public String getNumber() {
        return atributos.get("number");
    }

    public void setNumber(String number) {
        this.atributos.replace("number", number);
    }
    
    public boolean hasAtributte(String key){
        return this.atributos.containsKey(key);
    }
    
    public boolean setAtributte(String key, String valor){
        if(hasAtributte(key) || key.equals("") || key == null){
            return false;
        }
        if(valor.equals("") || valor == null){
            return false;
        }
        
        this.atributos.put(key, valor);
        return true;
    }
    
    public String getValor(String key){
        if (hasAtributte(key)){
            return this.atributos.get(key);
        }
        return null;
    }
    
    public Set<String> getKeysAtributtes(){
        return this.atributos.keySet();
    }
    
    public String getFirstPhoto(){
        return photos.get(0);
    }
    
    public void addPhoto(String photo){
        photos.addLast(photo);
    }
    
    public boolean hasPhotos(){
        return photos.size() > 0;
    }
    
}
