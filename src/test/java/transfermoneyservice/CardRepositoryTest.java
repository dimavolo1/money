package transfermoneyservice;

import com.example.monyeq.model.Card;
import com.example.monyeq.model.request.TransferRequest;
import com.example.monyeq.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static transfermoneyservice.TestData.*;

@DisplayName("Тестирование функциональности CardRepository")
public class CardRepositoryTest {
    @Mock
    private CardRepository repository;
    private final Map<String, Card> testCards = new ConcurrentHashMap<String, com.example.monyeq.model.Card>();

    @BeforeEach
    void setUp() {
        repository = new CardRepository();

        testCards.put(CARD_NUMBER_1, CARD_1);
        testCards.put(CARD_NUMBER_2, CARD_2);
    }

    @ParameterizedTest
    @DisplayName("Проверка правильности хранения доступных карт")
    @ValueSource(strings = {CARD_NUMBER_1, CARD_NUMBER_2})
    void getCard(String cardNumber) {
        Assertions.assertEquals(testCards.get(cardNumber), repository.getCard(cardNumber));
    }

    @Test
    @DisplayName("Проверка корректности роста номера операции")
    void incrementAndGet() {
        int operationId = 1;
        assertEquals(repository.incrementAndGetOperationID(), operationId);
    }

    @Test
    @DisplayName("Проверка корректности сохранения и получения номера операции и тела трансфера")
    public void putAndRemoveTransfer() {
        TransferRequest before = repository.removeTransfer(OPERATION_ID);
        assertNull(before);
        repository.putTransfer(OPERATION_ID, TRANSFER_REQUEST_1);
        TransferRequest after = repository.removeTransfer(OPERATION_ID);
        assertEquals(after, TRANSFER_REQUEST_1);
    }

    @Test
    @DisplayName("Проверка сохранения и получения кода для совершения операции")
    public void putAndRemoveCode() {
        String before = repository.removeCode(OPERATION_ID);
        assertNull(before);
        repository.putCodes(OPERATION_ID, CODE);
        String after = repository.removeCode(OPERATION_ID);
        assertEquals(after, CODE);
    }
}