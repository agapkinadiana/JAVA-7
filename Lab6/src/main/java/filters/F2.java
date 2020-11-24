package filters;


import javax.servlet.*;
import java.io.IOException;

public class F2 implements Filter {

    public void init(FilterConfig filterConfig) {
        System.out.println("F2 is initializing...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        System.out.println("F2 is executing...");

        try {
            String value1 = servletRequest.getParameter("value1");
            String value2 = servletRequest.getParameter("value2");
            String value3 = servletRequest.getParameter("value3");

            if (
                    (value1 != null && !value1.equals("") && Integer.parseInt(value1) < 0)
                            || (value2 != null && !value2.equals("") && Integer.parseInt(value2) < 0)
                            || (value3 != null && !value3.equals("") && Integer.parseInt(value3) < 0)
            ) {
                throw new Exception();
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception error) {
            servletResponse.getWriter().println("<h1>F2 has been blocked the request</h1>");
        }
    }

    public void destroy() {
        System.out.println("F2 is destroying...");
    }
}
