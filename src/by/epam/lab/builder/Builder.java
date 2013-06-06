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
      private String filename  = null;
   
    public Builder(String filename) {
        this.filename = filename;
      
    }

    public String getFileName() {
        return filename;
    }

       public abstract Medicine getMedicine();
}
