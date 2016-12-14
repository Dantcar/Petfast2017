/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Décio
 */
public class ModeloCbxHorario extends  AbstractListModel implements ComboBoxModel {

        String[] horarioServ = {"08:00",
            "09:00",
            "10:00",
            "11:00",
            "12:00",
            "13:00",
            "14:00",
            "15:00",
            "16:00",
            "17:00",
            "18:00",            
        }; //horários disponíveis
        
        String selection = null;
    
    @Override
    public int getSize() {
        return horarioServ.length;
        
    }

    @Override
    public Object getElementAt(int index) {
      return horarioServ[index];
    }

    @Override
    public void setSelectedItem(Object anItem) {
     selection = (String) anItem;   
    }

    @Override
    public Object getSelectedItem() {
        return selection; // to add the selection to the combo box}
    } 
    
    
}
