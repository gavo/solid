package soe.mdeis.m7.solid;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

class AppTests {

	@Test
	void main() {
		try (MockedStatic<SpringApplication> springApplicationMock = Mockito.mockStatic(SpringApplication.class)) {
			App.main(new String[] {});
			springApplicationMock.verify(() -> SpringApplication.run(App.class, new String[] {}), Mockito.times(1));
		}
	}

}
