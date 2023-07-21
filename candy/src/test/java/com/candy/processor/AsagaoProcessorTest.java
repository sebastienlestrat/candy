package com.candy.processor;

import com.candy.model.CandyBox;
import com.candy.model.CandyTagEnum;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class AsagaoProcessorTest {

    @Autowired
    AsagaoProcessor asagaoProcessor;

    @Autowired
    ProcessorInterface processorInterface;

    @BeforeEach
    void setUp() {
        MockedStatic<LocalDateTime> mockLocalDateTime= mockStatic(LocalDateTime.class, CALLS_REAL_METHODS );
        String str = "2016-03-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        mockLocalDateTime.when(LocalDateTime::now).thenReturn(dateTime);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOrder() {
        // Arrange

        // Act
        CandyBox candyBox = this.processorInterface.createOrder(100, CandyTagEnum.ASAGAO);

        // Assert
        Assertions.assertNotNull(candyBox);
    }

    @Test
    void getCandyBoxes() {
    }
}