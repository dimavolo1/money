package transfermoneyservice;

import com.example.monyeq.model.Amount;
import com.example.monyeq.model.Card;
import com.example.monyeq.model.request.ConfirmRequest;
import com.example.monyeq.model.request.TransferRequest;
import com.example.monyeq.model.response.TransferAndConfirmResponse;
import com.example.monyeq.repository.CardRepository;
import com.example.monyeq.service.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class ServiceTest {
    static Service sut;
    CardRepository cardRepository = Mockito.mock(CardRepository.class);

    @BeforeEach
    public void InitAndStart() {
        sut = new Service(cardRepository);
    }

    @AfterEach
    public void finished() {
        sut = null;
    }

    @Test
    public void testPostTransfer() {
        Card cardFromNumber = new Card("1000000000000000", "1025", "123",
                new Amount(100, "руб"));
        Card cardToNumber = new Card("2000000000000000", "1025", "123",
                new Amount(10, "руб"));

        long transferAmount = 100L;

        Mockito.when(cardRepository.getCard("1000000000000000"))
                .thenReturn(cardFromNumber);

        Mockito.when(cardRepository.getCard("2000000000000000"))
                .thenReturn(cardToNumber);

        TransferRequest transfer = new TransferRequest("1000000000000000", "1025", "123",
                "2000000000000000", new Amount(transferAmount, "RUR"));

        final TransferAndConfirmResponse expected = new TransferAndConfirmResponse("1");

        Mockito.when(cardRepository.incrementAndGetOperationID()).thenReturn(Integer.valueOf("1"));

        TransferAndConfirmResponse actual = sut.postTransfer(transfer);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPostConfirmOperation(){
        final ConfirmRequest confirmRequest = new ConfirmRequest("1", "0000");
        final String operationID = confirmRequest.getOperationId();
        long transferAmount = 100L;
        TransferRequest transfer = new TransferRequest("1000000000000000", "1025", "123",
                "2000000000000000", new Amount(transferAmount, "RUR"));

        Mockito.when(cardRepository.removeTransfer(operationID)).thenReturn(transfer);

        Card cardFromNumber = new Card("1000000000000000", "1025", "123",
                new Amount(100, "руб"));
        Card cardToNumber = new Card("2000000000000000", "1025", "123",
                new Amount(10, "руб"));

        Mockito.when(cardRepository.getCard("1000000000000000"))
                .thenReturn(cardFromNumber);

        Mockito.when(cardRepository.getCard("2000000000000000"))
                .thenReturn(cardToNumber);


        Mockito.when(cardRepository.removeCode("1")).thenReturn("0000");

        final TransferAndConfirmResponse expected = new TransferAndConfirmResponse("1");

        TransferAndConfirmResponse actual = sut.postConfirmOperation(confirmRequest);


        Assertions.assertEquals(expected, actual);

    }


}