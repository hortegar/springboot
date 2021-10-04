package mx.com.tcs.Annotations.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class RequestLoggingFilter.
 */
public class RequestLoggingFilter implements Filter {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

	/**
	 * Do filter.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param chain    the chain
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.info("Request - doFilter");
		log.info("RequestURI - " + httpRequest.getRequestURI());
		chain.doFilter(request, response);

	}

}
