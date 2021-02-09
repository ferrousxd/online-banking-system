package kz.edu.astanait.bankingsystem.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    private final static Path LOGS_FILE = Path.of("src/main/resources/static/logs.txt");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(LogFilter.class + " has been initialised");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String message = String.format(
                "User \"%s\" accessed \"%s\" at %s\n",
                SecurityContextHolder.getContext().getAuthentication().getName(),
                request.getRequestURI(),
                LocalDateTime.now().format(DATE_TIME_FORMATTER)
        );
        Files.write(LOGS_FILE, message.getBytes(), StandardOpenOption.APPEND);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        LOGGER.info(LogFilter.class + " has been destroyed");
    }
}