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
public class STAXBuilder  extends Builder{

    public STAXBuilder(File fileXML) {
        super(fileXML);
    }

    
    
    @Override
    public Medicine getMedicine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
