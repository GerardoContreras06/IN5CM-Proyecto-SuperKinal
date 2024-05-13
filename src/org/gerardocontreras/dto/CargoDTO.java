/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerardocontreras.dto;

import org.gerardocontreras.model.Encargado;

/**
 *
 * @author gerar
 */
public class CargoDTO {
    private static CargoDTO instance;
    private Encargado cargo;
    
    private CargoDTO(){
    }
    
    public static CargoDTO getCargoDTO(){
        if(instance == null){
            instance = new CargoDTO();
        }
        return instance;
    }

    public Encargado getCargo() {
        return cargo;
    }

    public void setCargo(Encargado cargo) {
        this.cargo = cargo;
    }
    
}
