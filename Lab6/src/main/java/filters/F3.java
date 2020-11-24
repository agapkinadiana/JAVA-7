package filters;

import javax.servlet.*;
import java.io.IOException;

public class F3 implements Filter {

    public void init(FilterConfig filterConfig) {
        System.out.println("F3 is initializing...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        System.out.println("F3 is executing...");

        try {
            int length = 0;
            String value1 = servletRequest.getParameter("value1");
            String value2 = servletRequest.getParameter("value2");
            String value3 = servletRequest.getParameter("value3");

            if (value1 != null && !value1.equals("")) {
                length += Integer.parseInt(value1);
            }
            if (value2 != null && !value2.equals("")) {
                length += Integer.parseInt(value2);
            }
            if (value3 != null && !value3.equals("")) {
                length += Integer.parseInt(value3);
            }

            if (length >= 10) {
                throw new Exception();
            }
            filterChain.doFilter(servletRequest,servletResponse);
        } catch (Exception error) {
            servletResponse.getWriter().println("<h1>F3 has been blocked the request</h1>");
        }
    }

    public void destroy() {
        System.out.println("F3 is destroying...");
    }
}
