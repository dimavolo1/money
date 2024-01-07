package com.example.monyeq.service;

import com.example.monyeq.exception.InputDataException;
import com.example.monyeq.repository.TransferRepository;
import com.example.monyeq.request.ConfirmOperationRequest;
import com.example.monyeq.request.TransferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.example.monyeq.global.Messages.CONFIRM_CODE_IS_NOT_VALID;
import static com.example.monyeq.global.Messages.REQUEST_IS_NULL;


@Service
@Slf4j
public class TransferService {
    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public String transfer(TransferRequest request) {
        String idTransfer = repository.tranfer(request);
        repository.putCode(idTransfer, "0000");
        return idTransfer;
    }

    public String confirmOperation(ConfirmOperationRequest request) {
        if (request == null) {
            throw new InputDataException(REQUEST_IS_NULL);
        }

        String operationId = request.getOperationId();

        if (request.getCode().equals(repository.getCode(operationId))) {
            return repository.confirmOperation(request);
        } else {
            throw new InputDataException(CONFIRM_CODE_IS_NOT_VALID);
        }
    }
}
