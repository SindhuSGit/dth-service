package com.sample.project.serviceprovider;

import static com.sample.project.serviceprovider.manager.ChannelManager.CHANNEL_LIST;
import static com.sample.project.serviceprovider.manager.UserSubscriptionManager.USERS_MAP;

import com.opencsv.CSVWriter;
import com.sample.project.serviceprovider.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ServiceProviderApplication {
	private final static String FILE_PATH = "src/main/java/com/sample/project/serviceprovider/response/BillReport.csv";
	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderApplication.class, args);
	}

	/**
	 * Generated the bill for all user as scheduled at the beginning of the month and exports the same to a csv file.
	 */
	@Scheduled(cron = "0 0 0 1 * ?")
	public void generateBill() {
		try {
			File file = new File(FILE_PATH);
			FileWriter outputFile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputFile);
			String[] header = { "Id", "Name", "Total Bill(Rs)" };
			writer.writeNext(header);

			for (Map.Entry<String, User> userEntry : USERS_MAP.entrySet()) {
			User user = userEntry.getValue();
			int totalBill = 0;
				for (Long channelId : user.getSubscribedChannels()) {
					if (CHANNEL_LIST.containsKey(channelId)) {
					totalBill = totalBill + CHANNEL_LIST.get(channelId).getPrice();
					}
				}
				String[] data = {user.getId(), user.getName() , String.valueOf(totalBill)};
				writer.writeNext(data);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
