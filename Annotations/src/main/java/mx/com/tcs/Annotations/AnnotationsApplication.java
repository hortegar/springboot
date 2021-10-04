package mx.com.tcs.Annotations;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;

import mx.com.tcs.Annotations.connection.DataStaxAstraProperties;
import mx.com.tcs.Annotations.filter.RequestLoggingFilter;

/**
 * The Class AnnotationsApplication.
 */
@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class AnnotationsApplication {

	/** The topic. */
	@Value("${spring.cloud.gcp.topic}")
	private String topic;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AnnotationsApplication.class, args);
	}

	/**
	 * Session builder customizer.
	 *
	 * @param astraProperties the astra properties
	 * @return the cql session builder customizer
	 */
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle)
				.withAuthCredentials(astraProperties.getCLIENT_ID(), astraProperties.getCLIENT_SECRET()).build();
	}

	/**
	 * Message sender.
	 *
	 * @param template the template
	 * @return the message handler
	 */
	@Bean
	@ServiceActivator(inputChannel = "outputChannel")
	public MessageHandler messageSender(PubSubTemplate template) {
		return new PubSubMessageHandler(template, topic);
	}

	/**
	 * Filter registration bean.
	 *
	 * @return the filter registration bean
	 */
	@Bean
	FilterRegistrationBean<RequestLoggingFilter> filterRegistrationBean() {
		final FilterRegistrationBean<RequestLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new RequestLoggingFilter());
		filterRegistrationBean.addUrlPatterns("/api/*", "/gcp/*");
		return filterRegistrationBean;
	}
}
