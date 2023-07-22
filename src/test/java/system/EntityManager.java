package system;

import java.util.ArrayList;

public class EntityManager {

    static ArrayList<String> createdProjects = new ArrayList<>();

    public static void registerProject(String projectId) {
        createdProjects.add(projectId);
    }

    public static void deleteEntities(){
        for (String id : createdProjects)
            JiraApi.GetInstance().sendDelete("/3/project/" + id);
    }
}
