package conduit;

/* import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;  */
import com.intuit.karate.junit5.Karate;



class conduitTest {

    /* @Test
    void testParallel() {
        Results results = Runner.path("classpath:conduit")
                //.outputCucumberJson(true)
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    } */

    @Karate.Test
    Karate TestAll(){
        return Karate.run().relativeTo(getClass());

    }

}
