package kz.com;

import kz.com.entities.TaskStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@SpringBootApplication
public class ComApplication {

	public static void main(String[] args) throws ParseException {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC+5:00"));
		SpringApplication.run(ComApplication.class, args);
	}

}
