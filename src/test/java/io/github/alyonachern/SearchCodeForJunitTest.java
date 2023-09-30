package io.github.alyonachern;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchCodeForJunitTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void searchCodeForJUnit5Test() {
        // открыть страницу Selenide в Github
        open("/selenide/selenide");
        // перейти в раздел Wiki проекта
        $("#wiki-tab").click();
        // раскрыть полностью Pages
        $("#wiki-pages-box").$("li.wiki-more-pages-link").$("[type=button]").click();
        // в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        // открыть страницу SoftAssertions
        $(byText("SoftAssertions")).click();
        // проверить, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave((text("@ExtendWith({SoftAssertsExtension.class})\n" +
        "class Tests {\n" +
            "@Test\n" +
            "void test() {\n" +
                "Configuration.assertionMode = SOFT;\n" +
                "open(\"page.html\");\n" +
                "\n" +
                "$(\"#first\").should(visible).click();\n" +
                "$(\"#second\").should(visible).click();\n" +
            "}\n" +
                "}\n"
                )));
        $("#wiki-body").shouldHave(text("class Tests {\n" +
                "  @RegisterExtension \n" +
                "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                "\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
