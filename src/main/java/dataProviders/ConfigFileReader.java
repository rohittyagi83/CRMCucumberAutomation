package dataProviders;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Parameters;

import enums.DriverType;
import enums.EnvironmentType;
 
public class ConfigFileReader {	
	private Properties properties;
	private final String propertyFilePath= "configs//Configuration.properties";
 
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
 
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null){
			driverPath = System.getProperty("user.dir")+driverPath;
			return driverPath;
		}
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");		
	}
 
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}
 
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
	
	public DriverType getBrowser(String browserName) {
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
 
	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
		else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
		else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}
 
	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null){
			reportConfigPath = System.getProperty("user.dir")+reportConfigPath;
			return reportConfigPath;
		}
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public String getExcelPath(){
		String excelSheetPath = properties.getProperty("excelPath");
		if(excelSheetPath!= null){
			return excelSheetPath;
		}
		else throw new RuntimeException("excel Sheet Path not specified in the Configuration.properties file for the Key:excelSheetPath");		
	}
	
	public String getParallelRun(){
		String parallelRun = properties.getProperty("parallelRun");
		if(parallelRun!= null){
			return parallelRun;
		}
		else throw new RuntimeException("Parallel Run not specified in the Configuration.properties file for the Key:parallelRun");		
	}
	
	public String getHubIP(){
		String hubIP = properties.getProperty("hubIP");
		if(hubIP!= null){
			return hubIP;
		}
		else throw new RuntimeException("hubIP not specified in the Configuration.properties file for the Key:hubIP");		
	}
	
	public String getPlatform(){
		String platform = properties.getProperty("platform");
		if(platform!= null){
			return platform;
		}
		else throw new RuntimeException("Platform not specified in the Configuration.properties file for the Key:platform");		
	}
 
}