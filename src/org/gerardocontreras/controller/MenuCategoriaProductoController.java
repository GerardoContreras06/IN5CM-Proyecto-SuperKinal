/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerardocontreras.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gerardocontreras.dao.Conexion;
import org.gerardocontreras.model.CategoriaProducto;
import org.gerardocontreras.model.Encargado;
import org.gerardocontreras.system.Main;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class MenuCategoriaProductoController implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colCategoria, colNombre, colDescripcion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }  
    
     public void cargarDatos(){
        tblCategorias.setItems(listarCategoriaProductos());
        colCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, Integer>("categoriaProductoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("nombreCategoria"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<CategoriaProducto, String>("descripcionCategoria"));

    }
    
    public ObservableList<CategoriaProducto> listarCategoriaProductos(){
        ArrayList<CategoriaProducto> CategoriaProductos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCategoriaproductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaProductoId = resultSet.getInt("categoriaProductoId");
                String nombreCategoria = resultSet.getString("nombreCategoria");
                String descripcionCategoria = resultSet.getString("descripcionCategoria");
                
                CategoriaProductos.add(new CategoriaProducto(categoriaProductoId, nombreCategoria, descripcionCategoria));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(CategoriaProductos);
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
