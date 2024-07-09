package continentsManager.errorHandling;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HandlerExceptionToViewResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exceptionType", e);
        mav.addObject("exceptionMessage", e.getMessage());
        mav.addObject("handlerMethod", o);
        return mav;
    }
}
