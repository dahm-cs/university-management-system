package interfaces;

public interface Payable {
    /**
     * Any class that implements this needs to define how money is calculated.
     */
    double calculatePayment();
}