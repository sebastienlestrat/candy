package com.candy.processor.factory;

import com.candy.model.CandyTagEnum;
import com.candy.processor.AsagaoProcessor;
import com.candy.processor.ProcessorInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public abstract class AbstractProcessorFactory {


    public ProcessorInterface createProcessor(String candyTagName) {
        switch (candyTagName) {
            case "ASAGAO" -> {
                return new AsagaoProcessor();
            }
            default ->
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Le tag commandé n'existe pas");
        }
    }

    protected abstract ProcessorInterface createProcessor();

}