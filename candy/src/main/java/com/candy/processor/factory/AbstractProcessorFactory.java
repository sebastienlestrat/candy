package com.candy.processor.factory;

import com.candy.model.CandyTagEnum;
import com.candy.processor.AsagaoProcessor;
import com.candy.processor.ProcessorInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@Component
public class AbstractProcessorFactory {

    public ProcessorInterface createProcessor(CandyTagEnum candyTagName) {
        switch (candyTagName) {
            case ASAGAO -> {
                return new AsagaoProcessor();
            }
            default ->
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Le tag commandé n'existe pas");
        }
    }


}