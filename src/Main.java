public class Main {
    public static void main(String[] args) {

        RabinView rabinView = new RabinView();
        RabinModel rabinModel = new RabinModel();
        RabinController rabinController = new RabinController(rabinView, rabinModel);
        rabinView.setTitle("Rabin Crypto-system - Cryptography Project");
        rabinView.setVisible(true);

    }
}