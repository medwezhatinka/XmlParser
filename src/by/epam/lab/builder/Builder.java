/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.epam.lab.builder;

import by.epam.lab.model.Medicine;
import java.io.File;

/**
 *
 * @author Soldier
 */
public abstract class Builder {
      private File fileXML = null;
   
    public Builder(File fileXML) {
        this.fileXML = fileXML;
      
    }

    public File getFileXML() {
        return fileXML;
    }

       public abstract Medicine getMedicine();
}
