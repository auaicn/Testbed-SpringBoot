package japbook.japshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JapshopApplication {

	public static void main(String[] args) {

		// Application 이 돌아가도록하고, iOS 처럼 루프를 도는것이 아니라, 뒤의 작성줄들도 실행이 된다.
		SpringApplication.run(JapshopApplication.class, args);

		// Lombok Example
		// 설명과는 다르게 IDE 상의 Annotation processor 는 설정을 꼭 해줄 필요는 없는것 같다.
		LombokExample lombokExample = new LombokExample();
		lombokExample.setData("my data");
		System.out.println("data : " + lombokExample.getData());

	}

}
