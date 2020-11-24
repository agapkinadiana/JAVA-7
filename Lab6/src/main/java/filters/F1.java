package filters;

import javax.servlet.*;
import java.io.IOException;

public class F1 implements Filter {

    public F1() {
        System.out.println("F1 is constructing...");
    }


    public void init(FilterConfig filterConfig) {
        System.out.println("F1 is initializing...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        System.out.println("F1 is executing...");

        try {
            int param = 0;
            String value1 = servletRequest.getParameter("value1");
            String value2 = servletRequest.getParameter("value2");
            String value3 = servletRequest.getParameter("value3");

            if (value1 != null && !value1.equals("")) {
                param = Integer.parseInt(value1);
            }
            if (value2 != null && !value2.equals("")) {
                param = Integer.parseInt(value2);
            }
            if (value3 != null && !value3.equals("")) {
                param = Integer.parseInt(value3);
            }

            servletResponse.getWriter().println("<h2>param: " + param + "</h2>");

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception error) {
            servletResponse.getWriter().println("<h1>F1 has been blocked the request</h1>");
        }
    }

    public void destroy() {
        System.out.println("F1 is destroying...");
    }
}
