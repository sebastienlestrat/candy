package com.candy.processor.factory;

import com.candy.processor.ProcessorInterface;

public abstract class AbstractProcessorFactory {
    public ProcessorInterface create() {
        ProcessorInterface processor = createProcessor();
        processor.build();
        return processor;
    }
    protected abstract ProcessorInterface createProcessor();

}