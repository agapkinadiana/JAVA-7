package taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class Submit extends TagSupport {

    private String type;

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int doStartTag() {
        try {
            if (type != null) {
                if (type.equals("ok")) {
                    pageContext.getOut().write("<input type='submit' value='Ok'/>");
                } else if (type.equals("cancel")) {
                    pageContext.getOut().write("<input type='reset' value='Cancel'/>");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return SKIP_BODY;
    }
}
