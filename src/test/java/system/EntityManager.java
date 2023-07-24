package system;

import models.JiraProject;

import java.util.ArrayList;

public class EntityManager {

    static ArrayList<JiraProject> createdProjects = new ArrayList<>();

    /**
     * Register a new project to be monitored
     *
     * @param project   Project object to be added
     */
    public static void registerProject(JiraProject project) {
        createdProjects.add(project);
    }

    /**
     * Delete all Jira entities stored in the Manager
     */
    public static void deleteEntities(){
        for (JiraProject project : createdProjects)
            JiraApi.GetInstance().sendDelete("/3/project/" + project.Id);
    }
}
