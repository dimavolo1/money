package com.example.monyeq.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorReponse {
    private String message;
    private int id;
}
