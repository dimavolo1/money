package com.example.monyeq.repository;


import com.example.monyeq.request.ConfirmOperationRequest;
import com.example.monyeq.request.TransferRequest;
import org.springframework.stereotype.Repository;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransferRepository {
    private final ConcurrentHashMap<String, TransferRequest> transferMap = new ConcurrentHashMap<>();
    private final AtomicLong idTransfer = new AtomicLong();

    private final ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();

    public String tranfer(TransferRequest request) {
        idTransfer.incrementAndGet();
        transferMap.put(idTransfer.toString(), request);
        return idTransfer.toString();
    }

    public String confirmOperation(ConfirmOperationRequest request) {
        String operationId = request.getOperationId();
        if (transferMap.containsKey(operationId)) {
            return operationId;
        } else {
            return null;
        }
    }

    public void putCode(String idTransfer, String code) {
        codeMap.put(idTransfer, code);
    }

    public String getCode(String idTransfer) {
        return codeMap.get(idTransfer);
    }

}
