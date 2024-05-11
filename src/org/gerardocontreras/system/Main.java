/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerardocontreras.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.InputStream;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.gerardocontreras.controller.FormClienteController;
import org.gerardocontreras.controller.MenuClienteController;
import org.gerardocontreras.controller.MenuEncargadoController;
import org.gerardocontreras.controller.MenuPrincipalController;
import org.gerardocontreras.controller.MenuTicketSoporteController;

/**
 *
 * @author gerar
 */
public class Main extends Application {
    private final String URLVIEW = "/org/gerardocontreras/view/";
    private Stage stage;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal APP");
        Image icon = new Image("org/gerardocontreras/image/Icono.png");
        stage.getIcons().add(icon);
        menuPrincipalView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int height)throws Exception{
        Initializable resultado  = null;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable) loader.getController();
        
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
           MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 950, 700);
           menuPrincipalView.setStage(this); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuClienteView(){
        try{
            MenuClienteController menuClienteView = (MenuClienteController)switchScene("MenuClienteView.fxml", 1200, 750);
            menuClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClienteView(int op){
        try{
            FormClienteController formClienteView = (FormClienteController) switchScene("FormClienteView.fxml", 500, 750);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController) switchScene("MenuTicketSoporteView.fxml", 1200, 750);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuEncargadoView(){
        try{
            MenuEncargadoController menuEncargadoView = (MenuEncargadoController) switchScene("MenuEncargadoView.fxml", 1200, 750);
            menuEncargadoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);       
    }
    
}
