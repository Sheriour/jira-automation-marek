package system;

import lombok.extern.slf4j.Slf4j;
import pages.AbstractPage;
import pages.JiraProjectsPage;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class PageRepository {

    private static ArrayList<AbstractPage> pages = new ArrayList<>();

    public static <T> T getPage(Class<T> pageClass){
        Optional<AbstractPage> pageOptional = pages
                .stream()
                .filter(x -> x.getClass() == pageClass)
                .findFirst();

        if (pageOptional.isEmpty()) {
         AbstractPage page = new JiraProjectsPage();
         pages.add(page);
         return (T)page;
        }
        else {
            return (T)pageOptional.get();
        }
    }
}
