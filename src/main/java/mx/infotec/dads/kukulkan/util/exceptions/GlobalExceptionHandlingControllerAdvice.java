package mx.infotec.dads.kukulkan.util.exceptions;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * GlobalExceptionHandling
 * 
 * @author Daniel Cortes Pichardo
 * @since essence 1.1
 * @version 1.1
 */
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlingControllerAdvice.class);

    /**
     * Convert a predefined exception to an HTTP Status code
     */
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do.
    }

    /**
     * Convert a predefined exception to an HTTP Status code and specify the
     * name of a specific view that will be used to display the error.
     * 
     * @return Exception view.
     */
    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError(Exception exception) {
        LOGGER.error("SQLException or DataAccessException", exception);
        // Nothing to do. Return value 'databaseError' used as logical view name
        // of an error page, passed to view-resolver(s) in usual way.
        return "databaseError";
    }

    /**
     * Demonstrates how to take total control - setup a model, add useful
     * information and return the "support" view name. This method explicitly
     * creates and returns
     * 
     * @param req
     *            Current HTTP request.
     * @param exception
     *            The exception thrown - always {@link SupportInfoException}.
     * @return The model and view used by the DispatcherServlet to generate
     *         output.
     * @throws Exception
     */
    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date().toString());
        mav.addObject("status", 500);
        mav.addObject("error", "hola munodo");
        return mav;
    }
}