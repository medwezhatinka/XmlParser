/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.parser;

import by.epam.lab.builder.Builder;
import by.epam.lab.builder.DOMBuilder;
import by.epam.lab.builder.SAXBuilder;
import by.epam.lab.builder.STAXBuilder;
import by.epam.lab.model.Medicine;
import java.io.FileNotFoundException;

/**
 *
 * @author Soldier
 */
public class ParserManager {
 private String filename;
 
    public ParserManager(String filename) {
        this.filename = filename;
        
    }
    
   public  Medicine parseXml(String parser) throws IllegalArgumentException, FileNotFoundException{
       ParserType type = ParserType.valueOf(parser.toUpperCase());
       Builder builder= null;
       switch(type){
           case DOM:
               builder = new DOMBuilder(filename);
               break;
           case SAX:
               builder = new SAXBuilder(filename);
               break;
           case STAX:
               builder = new STAXBuilder(filename);
               break;
              
       }
       if (builder !=null) {
             return  builder.getMedicine();
       }
     return null;
   }
}
