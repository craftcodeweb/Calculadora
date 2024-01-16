import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class CalculadoraController {

    @FXML
    private TextField resultado;

    private String operacao = "";
    private double numero1;
    private double numero2;

    @FXML
    public void processarNumero(ActionEvent event) {
        String btnNumero = ((Button) event.getSource()).getText();
        resultado.setText(resultado.getText() + btnNumero);
    }

    @FXML
    public void processarOperacao(ActionEvent event) {
        String btnOperacao = ((Button) event.getSource()).getText();

        if (btnOperacao.equals("=")) {
            if (!operacao.equals("")) {
                numero2 = Double.parseDouble(resultado.getText());
                calcular(operacao, numero1, numero2);
                operacao = "";
            }
        } else {
            if (!operacao.equals("")) {
                return;
            }
            operacao = btnOperacao;
            numero1 = Double.parseDouble(resultado.getText());
            resultado.setText("");
        }
        exibirOperadorAtual();
    }

    @FXML
    public void limpar() {
        resultado.clear();
        operacao = "";
        numero1 = 0;
        numero2 = 0;
        exibirOperadorAtual();
    }

    private void calcular(String operacao, double numero1, double numero2) {
        try {
            double resultadoCalculado = 0.0;
            switch (operacao) {
                case "+":
                    resultadoCalculado = numero1 + numero2;
                    break;
                case "-":
                    resultadoCalculado = numero1 - numero2;
                    break;
                case "x":
                    resultadoCalculado = numero1 * numero2;
                    break;
                case "÷":
                    if (numero2 != 0) {
                        resultadoCalculado = numero1 / numero2;
                    } else {
                        resultado.setText("Divisão por zero");
                        exibirOperadorAtual();
                        return;
                    }
                    break;
                default:
                    break;
            }

            // Formata o resultado
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.##########");
            String resultadoFormatado = decimalFormat.format(resultadoCalculado);
            resultado.setText(resultadoFormatado);

            exibirOperadorAtual();
        } catch (ArithmeticException e) {
            resultado.setText("Erro");
            exibirOperadorAtual();
        }
    }

    private void exibirOperadorAtual() {
        if (!operacao.isEmpty()) {
            resultado.setPromptText(operacao);
        } else {
            resultado.setPromptText("");
        }
    }
}
