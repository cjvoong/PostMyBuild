package postmybuild;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import postmybuild.data.entity.Address;
import postmybuild.data.entity.Builder;
import postmybuild.data.repository.BuilderRepository;

@SpringBootApplication
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args){
		SpringApplication.run(App.class,args);
	}

	@Bean
	public CommandLineRunner demo(BuilderRepository repository) {
		return (args) -> {
			repository.save(new Builder("ABC plasterers","John","Smith",new Address("1","street","W.Yorks","UK","LS11LS")));
			repository.save(new Builder("JJ's Joinery","Joe","Blogs",new Address("2","street","W.Yorks","UK","LS22LS")));	
		};
	}

}
