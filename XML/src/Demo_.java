import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author leijiong
 * @version 1.0
 */
public class Demo_ {

    @Test
    public void loadXML() throws DocumentException {
        // 得到一个解析器
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));
        System.out.println(document);
    }

    @Test
    public void listStus() throws DocumentException {
        // 得到一个解析器
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));

        // 得到rootElement
        Element rootElement = document.getRootElement();

        // 得到rootElemnt下的所有子元素
        List<Element> students = rootElement.elements("student");

        for (Element student : students) {
            Element name = student.element("name");
            Element age = student.element("age");
            // gender
            Element gender = student.element("gender");
            System.out.println(name.getText() + " " + age.getText() + " ");
        }

    }

    @Test
    public void readOne() {
        // 得到一个解析器
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("src/Students.xml"));
            Element rootElement = document.getRootElement();
            Element student = (Element) rootElement.elements("student").get(0);
            Element name = student.element("name");
            Element age = student.element("age");
            System.out.println(name.getText() + " " + age.getText());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void add() throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));

        // 1. 创建成一个学生节点对象
        Element newStu = DocumentHelper.createElement("student");
        Element newStu_name = DocumentHelper.createElement("name");

        // 添加属性
        newStu.addAttribute("id", "004");
        newStu_name.setText("宋江");

        // 创建 age 元素
        Element newStu_age = DocumentHelper.createElement("age");
        newStu_age.setText("30");

        // 创建resume元素
        Element newStu_rumse = DocumentHelper.createElement("resume");
        newStu_age.setText("宋江是个好人");

        newStu.add(newStu_name);
        newStu.add(newStu_age);
        newStu.add(newStu_rumse);

        // 将newStu节点添加到root
        document.getRootElement().add(newStu);

        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("UTF-8");

        // 保存
        XMLWriter writer = new XMLWriter(new FileOutputStream("src/Students.xml"), output);
        writer.write(document);
        writer.close();


    }

    @Test
    public void del() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));
        // 得到第一个学生信息
        Element student = (Element) document.getRootElement().elements("student").get(2);
        // 删除元素
        student.getParent().remove(student);
        // 删除元素的某个属性
        student.remove(student.attribute("id"));
        // 更新xml
        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("UTF-8");
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream("src/Students.xml"), output);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void update() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Students.xml"));
        List<Element> students = document.getRootElement().elements("student");
        for (Element student : students) {
            Element age = student.element("age");
            age.setText(Integer.parseInt(age.getText()) + 1 + "");
        }
        OutputFormat output = OutputFormat.createPrettyPrint();
        output.setEncoding("UTF-8");
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream("src/Students.xml"), output);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
