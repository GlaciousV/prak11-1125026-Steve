import view.PaymentView;
import controller.PaymentController;

public class App {
    public static void main(String[] args) {
        PaymentView view = new PaymentView();
        PaymentController controller = new PaymentController(view);
        controller.start();
    }
}