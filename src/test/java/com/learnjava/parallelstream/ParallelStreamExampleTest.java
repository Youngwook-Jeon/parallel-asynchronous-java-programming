package com.learnjava.parallelstream;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamExampleTest {

    ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

    @Test
    void stringTransform() {
        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> resultList = parallelStreamExample.stringTransform(inputList);
        timeTaken();

        assertEquals(4, resultList.size());
        resultList.forEach(name -> {
            assertTrue(name.contains("-"));
        });
    }
}