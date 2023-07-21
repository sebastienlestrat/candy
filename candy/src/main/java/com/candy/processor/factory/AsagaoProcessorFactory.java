package com.candy.processor.factory;

import com.candy.processor.AsagaoProcessor;
import com.candy.processor.ProcessorInterface;

public class AsagaoProcessorFactory extends AbstractProcessorFactory {
    @Override
    protected ProcessorInterface createProcessor() {
        return new AsagaoProcessor();
    }
}