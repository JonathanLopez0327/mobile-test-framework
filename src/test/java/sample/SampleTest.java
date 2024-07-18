package sample;

import base.BaseTest;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test(description = "Sample test")
    void sample_test() {
        System.out.println("Sample test");
    }
}
