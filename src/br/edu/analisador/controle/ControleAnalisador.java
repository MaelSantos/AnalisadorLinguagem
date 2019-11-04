package br.edu.analisador.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ControleAnalisador {

    @FXML
    private TextArea tfdEntrada;

    @FXML
    private Button btnGerarAnalises;

    @FXML
    private Button btnLimparLexico;

    @FXML
    private Button btnLimparSintatico;

    @FXML
    private TextArea tfdLexico;

    @FXML
    private TextArea tfdSintatico;

    @FXML
    void actionButton(ActionEvent event) {

    }

}
