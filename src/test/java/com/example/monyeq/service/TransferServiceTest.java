package com.example.monyeq.service;

import com.example.monyeq.repository.TransferRepository;
import com.example.monyeq.request.ConfirmOperationRequest;
import com.example.monyeq.request.TransferRequest;
import com.example.monyeq.request.object.Amount;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;


class TransferServiceTest {
    private final AtomicLong idTransfer = new AtomicLong();

    TransferRepository repository = new TransferRepository();

    TransferService service = new TransferService(repository);

    @Test
    void testTransfer() {
        TransferRequest request = new TransferRequest();
        request.setCardFromNumber("4444444444444444");
        request.setCardFromValidTill("333");
        request.setCardFromCVV("12/33");
        request.setCardToNumber("3333333333333333");
        request.setAmount(new Amount(4500L, "RUR"));

        String expected = Long.toString(idTransfer.incrementAndGet());
        String actual = service.transfer(request);
        assertEquals(expected, actual);
    }

    @Test
    void testTransferSecondTransfer() {
        TransferRequest request = new TransferRequest();
        request.setCardFromNumber("4444444444444444");
        request.setCardFromValidTill("333");
        request.setCardFromCVV("12/33");
        request.setCardToNumber("3333333333333333");
        request.setAmount(new Amount(4500L, "RUR"));

        String expected = Long.toString(idTransfer.incrementAndGet());
        String actual = service.transfer(request);
        assertEquals(expected, actual);
    }

    @Test
    void testConfirmOperationPositive() {
        ConfirmOperationRequest request = new ConfirmOperationRequest();
        request.setOperationId("1");
        request.setCode("0000");
        assertThrows(RuntimeException.class, () -> service.confirmOperation(request));
    }

    @Test
    void testConfirmOperationNegative() {
        ConfirmOperationRequest request = new ConfirmOperationRequest();
        request.setOperationId("1");
        request.setCode("000");
        assertThrows(RuntimeException.class, () -> service.confirmOperation(request));
    }
}
