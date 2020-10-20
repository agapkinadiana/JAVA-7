package taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class Sex extends TagSupport {

    private String name;

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(String.format("<input type='radio' name='%s' value='male'>Male</input><br/>", name));
            pageContext.getOut().write(String.format("<input type='radio' name='%s' value='female'>Female</input><br/>", name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return SKIP_BODY;
    }
}
