package system;

import models.JiraProject;

import java.util.ArrayList;

public class EntityManager {

    static ArrayList<JiraProject> createdProjects = new ArrayList<>();

    public static void registerProject(JiraProject project) {
        createdProjects.add(project);
    }

    public static void deleteEntities(){
        for (JiraProject project : createdProjects)
            JiraApi.GetInstance().sendDelete("/3/project/" + project.Id);
    }
}
