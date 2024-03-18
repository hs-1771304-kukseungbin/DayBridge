package DayBridge.Daybridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@EntityScan(basePackageClasses = {DaybridgeApplication.class, Jsr310Converters.class})

@SpringBootApplication
public class DaybridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaybridgeApplication.class, args);
	}

}
