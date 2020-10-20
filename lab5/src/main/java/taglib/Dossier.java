package taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class Dossier extends TagSupport {

    private String endpoint;

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }


    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(String.format("<form action='%s' method='POST' enctype='application/x-www-form-urlencoded'>", endpoint));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        try {
            pageContext.getOut().write("</form>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return EVAL_PAGE;
    }
}
