package transfermoneyservice;


import com.example.monyeq.model.Amount;
import com.example.monyeq.model.Card;
import com.example.monyeq.model.request.ConfirmRequest;
import com.example.monyeq.model.request.TransferRequest;

public class TestData {

    public static final String CARD_NUMBER_1 = "1000000000000000";
    public static final String CARD_VALID_TILL_1 = "10/24";
    public static final String CARD_CVV_1 = "111";
    public static final Card CARD_1 = new Card(CARD_NUMBER_1, CARD_VALID_TILL_1, CARD_CVV_1, new Amount(100_000, "руб"));
    public static final String CARD_NUMBER_2 = "2000000000000000";
    public static final String CARD_VALID_TILL_2 = "10/24";
    public static final String CARD_CVV_2 = "222";
    public static final Card CARD_2 = new Card(CARD_NUMBER_2, CARD_VALID_TILL_2, CARD_CVV_2, new Amount(100_000, "руб"));

    public static final String OPERATION_ID = "1";
    public static final String CODE = "0000";

    public static final TransferRequest TRANSFER_REQUEST_1 = new TransferRequest (CARD_NUMBER_1, CARD_VALID_TILL_1, CARD_CVV_1, CARD_NUMBER_2, new Amount(1_000, "руб"));
    public static final TransferRequest TRANSFER_REQUEST_2 = new TransferRequest (CARD_NUMBER_2, CARD_VALID_TILL_2, CARD_CVV_2, CARD_NUMBER_2, new Amount(100, "руб"));

    public static final ConfirmRequest CONFIRM_REQUEST = new ConfirmRequest(OPERATION_ID, CODE);
}
