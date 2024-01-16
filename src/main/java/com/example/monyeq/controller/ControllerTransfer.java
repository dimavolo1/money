package com.example.monyeq.controller;
import com.example.monyeq.model.request.ConfirmRequest;
import com.example.monyeq.model.request.TransferRequest;
import com.example.monyeq.model.response.TransferAndConfirmResponse;
import com.example.monyeq.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@CrossOrigin
public class ControllerTransfer {
    private final Service service;

    @PostMapping("/transfer")
    public TransferAndConfirmResponse postTransfer(@RequestBody TransferRequest transferRequest) {
        return service.postTransfer(transferRequest);
    }

    @PostMapping("/confirmOperation")
    public TransferAndConfirmResponse postConfirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return service.postConfirmOperation(confirmRequest);
    }
}