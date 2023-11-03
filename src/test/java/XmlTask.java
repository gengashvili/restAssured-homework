import org.testng.annotations.Test;
import steps.ContinentsApiSteps;
import steps.FindPersonApiSteps;

import java.io.IOException;

public class XmlTask {

    ContinentsApiSteps continentsApiSteps = new ContinentsApiSteps();
    FindPersonApiSteps findPersonApiSteps = new FindPersonApiSteps();

    @Test
    public void continentsTest() {
        continentsApiSteps
                .getAndSetResponseXmlPath()
                .validateNameCount()
                .validateNameValues()
                .validateANContinentName()
                .validateLastContinentName();
    }

    @Test
    public void findPersonTest() throws IOException {
        findPersonApiSteps
                .getAndSetResponseXmlPath()
                .validateHomeStreet()
                .validateOfficeZip();
    }

}
