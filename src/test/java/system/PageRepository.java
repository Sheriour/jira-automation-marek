package system;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;

import static org.openqa.selenium.support.PageFactory.initElements;

@Slf4j
public class PageRepository {

    private static ArrayList<Object> pages = new ArrayList<>();

    /**
     * Create or retrieve (if one already exists) a page object from the repository.
     * Only one instance of a given page is allowed.
     *
     * @param pageClass Class of the page
     * @return          Page instance
     * @param <T>       Class of the page
     */
    public static <T> T getPage(Class<T> pageClass){
        try {
            Optional<Object> pageOptional = pages
                    .stream()
                    .filter(x -> x.getClass() == pageClass)
                    .findFirst();
            if (pageOptional.isEmpty()) {
                T page = pageClass.getConstructor().newInstance();
                initElements(DriverCoordinator.getWebDriver(), page);
                pages.add(page);
                return page;
            }
            else {
                return (T)pageOptional.get();
            }
        }
        catch (Exception e){
            log.error("Could not create page " + pageClass.getName());
            return null;
        }
    }
}
