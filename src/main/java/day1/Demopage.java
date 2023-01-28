package day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Demopage {

	
	@Test
	public void login() throws InterruptedException, BiffException, IOException {
		
		String data [][] = null;
		
		String excelpath = "C:\\Users\\HP\\Documents\\Orangehrmtesting.xls";
		
		FileInputStream fis = new FileInputStream(excelpath);

		Workbook wb = Workbook.getWorkbook(fis);

		Sheet sheet = wb.getSheet("Demopage");

		int row = sheet.getRows();
		int column = sheet.getColumns();

		data = new String[row][column];

		Cell cell;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {

				cell = sheet.getCell(j, i);

				String celldata = cell.getContents();

				data[i][j] = celldata;

				System.out.print(data[i][j] + " ");

			}

			System.out.println();
		}

		Demopage obj = new Demopage();
		for (int i = 1; i < data.length; i++) {
			obj.demologin(data[i][0], data[i][1], data[i][2]);
	
	}
	}
	
	void demologin(String fullname, String businessemail, String phonenumber) throws InterruptedException {

		int sleeptime = 1000;

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://www.orangehrm.com/");

		Thread.sleep(sleeptime);
		driver.manage().window().maximize();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[1]/a/button")).click();

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_FullName")).sendKeys(fullname);

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Email")).sendKeys(businessemail);

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"Form_getForm_Country\"]")).click();

		Thread.sleep(sleeptime);
//		driver.findElement(By.xpath("//*[@id=\"Form_getForm_Country\"]")).click();
		driver.findElement(By.id("Form_getForm_Country")).click();

		WebElement el = driver.findElement(By.id("Form_getForm_Country"));
		Select obj = new Select(el);
		obj.selectByVisibleText("United States");

		Thread.sleep(sleeptime);
		driver.findElement(By.id("Form_getForm_Contact")).sendKeys(phonenumber);

//		Thread.sleep(sleeptime);
//		driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).isSelected();

		Thread.sleep(sleeptime);
		driver.findElement(By.xpath("//*[@id=\"Form_getForm_action_submitForm\"]")).click();

		Thread.sleep(sleeptime);
		driver.close();
}
}