import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;


public class RabinController {

    private final RabinView theView;
    private final RabinModel theModel;

    public RabinController(RabinView theView, RabinModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addGenerateKeysListener(new GenerateKeysListener());
        this.theView.addEncryptKeyListener(new EncryptListener());
        this.theView.addDecryptKeyListener(new DecryptListener());
    }


    class GenerateKeysListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked Generate Keys");
            theModel.generateKey(1024);
            theView.setPrivateKey1(theModel.getPrivateKey1().toString());
            theView.setPrivateKey2(theModel.getPrivateKey2().toString());
            theView.setPublicKey(theModel.getPublicKey().toString());

            theView.setResult("");
            theView.setPlainText("");
            theView.setCipherText("");
        }
    }

    class EncryptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked Encrypt");

            String privateKey1 = theView.getPrivateKey1();
            String privateKey2 = theView.getPrivateKey2();
            String plainText = theView.getPlainText();

            if (plainText == null || plainText.isEmpty()) {
                theView.displayErrorMessage("Please enter a plain text");
                return;
            }
            if(privateKey1 == null || privateKey1.isEmpty() || privateKey2 == null || privateKey2.isEmpty()) {
                theView.displayErrorMessage("Please generate or enter keys first");
                return;
            }

            theModel.setKeys(new BigInteger(privateKey1), new BigInteger(privateKey2));
            BigInteger cipherText = theModel.encrypt(plainText);
            theView.setResult(cipherText.toString());
        }
    }

    class DecryptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked Decrypt");
            String privateKey1 = theView.getPrivateKey1();
            String privateKey2 = theView.getPrivateKey2();
            String cipherText = theView.getCipherText();

            try {

                theModel.setKeys(new BigInteger(privateKey1), new BigInteger(privateKey2));
                BigInteger[] plainTexts = theModel.decrypt(new BigInteger(cipherText));

                StringBuilder s = new StringBuilder();
                s.append("Decoded messages: \n");
                s.append("Most Probable: ").append(theModel.mostProbable(plainTexts)).append("\n\n");

                s.append("Other possible messages: \n");
                int i = 1;

                for (BigInteger plainText : plainTexts) {
                    s.append("Message ").append(i).append(": ");
                    String dec = new String(plainText.toByteArray());
                    if (dec.contains(RabinModel.getPaddingText())) {
                        s.append(dec.substring(RabinModel.getPaddingText().length())).append("\n");
                    } else {
                        s.append(dec).append("\n");
                    }
                    i++;
                    s.append("\n");

                }

                theView.setResult(s.toString());

            } catch (Exception ex) {
                theView.displayErrorMessage("Please enter a valid cipher text");
            }

        }
    }
}
