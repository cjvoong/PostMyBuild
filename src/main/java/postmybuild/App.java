package postmybuild;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.AddressRepository;
import postmybuild.data.repository.BuilderRepository;

@SpringBootApplication
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private BuilderRepository builderRepo;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			List<Address> b1Addresses = new ArrayList<Address>();
			b1Addresses.add(new Address("9999", "street", "W.Yorks", "UK", "LS11LS"));
			b1Addresses.add(new Address("2", "street", "W.Yorks", "UK", "LS22LS"));

			List<Address> b2Addresses = new ArrayList<Address>();
			b2Addresses.add(new Address("2", "street", "W.Yorks", "UK", "LS22LS"));

			addressRepo.saveAll(b1Addresses);
			addressRepo.saveAll(b2Addresses);

			builderRepo.save(new Builder("ABC plasterers", "John", "Smith", b1Addresses));
			builderRepo.save(new Builder("JJ's Joinery", "Joe", "Blogs", b2Addresses));
		};
	}

}
