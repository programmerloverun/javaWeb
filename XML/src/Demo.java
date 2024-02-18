import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * @author leijiong
 * @version 1.0
 */
public class Demo {

    @Test
    public void loadXML() throws DocumentException {
        // 得到一个解析器
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));
        List<Element> students = document.getRootElement().elements("student");
        for (Element student : students) {
            Element name = student.element("name");
            Element age = student.element("age");
            System.out.println(name.getText() + " " + age.getText() + " ");
        }
    }
}
