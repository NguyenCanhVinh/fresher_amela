package vn.amela.fresher;

import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


@MapperScan("vn.amela.fresher.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TaskManagerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerWebApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}
}
