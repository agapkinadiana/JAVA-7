package taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class Lastname extends TagSupport {

    private String name;
    private String initValue;

    public void setName(String name) {
        this.name = name;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }


    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(String.format("<input type='text' name='%s' value='%s'/><br/>", name, initValue));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return SKIP_BODY;
    }
}
