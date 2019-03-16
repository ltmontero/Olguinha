/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olguinha;

import database.SelectApp;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author lmleo
 */
public class FXMLPesquisaController implements Initializable {

    @FXML
    private Button btnPesquisarTodos;
    @FXML
    private TableView<UserDetail> tableResults;
    @FXML
    private TableColumn<UserDetail, String > colunaID;
    @FXML
    private TableColumn<UserDetail, String> colunaNome;
    @FXML
    private TableColumn<UserDetail, String> colunaCpf;
    @FXML
    private TableColumn<UserDetail, String> colunaSexo;
    @FXML
    private TableColumn<UserDetail, String> colunaTel;
    @FXML
    private TableColumn<UserDetail, String> colunaNasc;
    @FXML
    private TableColumn<UserDetail, String> colunaTelegram;
    @FXML
    private TableColumn<UserDetail, String> colunaEndereco;
    @FXML
    private TableColumn<UserDetail, String> colunaObs;

    private ObservableList<UserDetail>data;
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicouPesquisarTodos(ActionEvent event) {
        data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM "+olguinha.Olguinha.TABLE;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            data.add(new UserDetail(rs.getInt("id"), rs.getString("name"), rs.getString("cpf"), rs.getString("sexo"), rs.getString("tel"), rs.getString("nasc"), rs.getString("telegram"), rs.getString("endereco"),rs.getString("obs")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colunaTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colunaNasc.setCellValueFactory(new PropertyValueFactory<>("nasc"));
        colunaTelegram.setCellValueFactory(new PropertyValueFactory<>("telegram"));
        colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaObs.setCellValueFactory(new PropertyValueFactory<>("obs"));
        
        tableResults.setItems(null);
        tableResults.setItems(data);
        
    }
    
    private Connection connect() {
    // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(olguinha.Olguinha.URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    

}
