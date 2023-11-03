
package agenda;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import tda.ArrayList;
import tda.LinkedList;


public abstract class Contacto implements Serializable {
    private HashSet tags;
    private HashMap<String, String> atributos;
    private LinkedList<String> photos;

    public Contacto(String name, String number) {
        tags = new HashSet<String>();
        atributos = new HashMap<>();
        photos = new LinkedList();
        atributos.put("number", number);
        atributos.put("name", name);       
    }
    
    public boolean addTag(String tag){
        if(!tag.equals("") && tag != null){
            tags.add(tag);
            return true;
        }
        return false;
    }
    
    public boolean removeTag(String tag){
        if(tags.contains(tag)){
            tags.remove(tag);
            return true;
        }
        return false;
    }
    public boolean setTag(String oldTag, String newTag){
        if(!removeTag(oldTag)){
            return false;
        }
        return addTag(newTag);
    }
    
    public HashSet getTags(){
        return tags;
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
        if(key.equals("") || key == null){
            return false;
        }
        if(valor == null){
            return false;
        }
        System.out.println(atributos);
        System.out.println("Fuera KEY: " + key + " valor: " + atributos.get(key));
        if(atributos.containsKey(key)){
            this.atributos.put(key, valor);
            System.out.println(atributos);
            System.out.println("Dentro KEY: " + key + " valor: " + atributos.get(key));
            return true;
        }
        return false;
    }
    
    public boolean putAtributte(String key, String valor){
        if(key.equals("") || key == null){
            return false;
        }
        if( valor == null){
            return false;
        }
        
        this.atributos.put(key, valor);
        return true;
    }
    
    public boolean updateKeyAtributte(String oldKey, String newKey){
        if(this.hasAtributte(oldKey) && !this.hasAtributte(newKey)){
            this.putAtributte(newKey, this.getValorAtributte(oldKey));
            this.atributos.remove(oldKey);
            return true;
        }
        return false;
    }
    
    public String getValorAtributte(String key){
        if (hasAtributte(key)){
            return this.atributos.get(key);
        }
        return null;
    }
    
    public Set<String> getKeysAtributtes(){
        return this.atributos.keySet();
    }
    
    public boolean removeKeyAtributte(String key){
        if(atributos.containsKey(key)){
            atributos.remove(key);
            return true;
        }
        return false;
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
