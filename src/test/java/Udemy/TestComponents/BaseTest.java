package Udemy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.AfterMethod;

import Udemy.SeleniumFrameworkDesign.pageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		// this properties class will help to get the data from the file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Udemy\\resources\\GolbalData.properties");
		// a FileInputStream Creates a FileInputStream by opening a connection to an
		// actual file,
		// the file named by the path name name in the file system.
		// A new FileDescriptor object is created to represent this file connection.

		prop.load(fis);
		// .load ->Reads a property list (key and element pairs) from the input byte
		// stream.

		// String browserName = prop.getProperty("browser"); ->if fetches the value in
		// the following key pair

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		//to get system level data we use the method getProperty() which is then used in the code
		// the above code will fetch data from the cmd to select the browser by using
		// System.getProperty("browser")

		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup(); // driver manager

			if (browserName.contains("headless")) {
				options.addArguments("headless"); // this will set the browser in headless mode
			}

			driver = new ChromeDriver(options); // passing the browser mode to the driver
			driver.manage().window().setSize(new Dimension(1440, 900)); // manually driving to full screenmode
			// as window().maximize will not work in headless mode

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// implicit wait

		return driver;
	}

	@BeforeMethod(alwaysRun = true) // these methods should be generic for all groups as tieing to one group will
									// not allow to access by other
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();

		landingPage = new LandingPage(driver);

		landingPage.goTo();

		driver.manage().window().fullscreen();

		return landingPage;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// 1) read json to string
		File file = new File(filePath);
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		// here the utf 8 is the String encoding type
		// 2)String to HashMap (add dependencies)-> Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver; // casting the driver
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}

	@AfterMethod(alwaysRun = true) // alwaysRun attribute will make it run every time of execution
	public void tearDown() {

		driver.close();

	}

	// to run jenkins -->F:\Jenkins>java -jar jenkins.war -httpPort=8080
	// jenkins secret code ->9900077ba04541f4a0eac2a835e19228 -->adminstrator
	// password
	// localhost:8080
	// jenkins credintials - username - ShivKumar , password- 9337080935
	//yes

}
