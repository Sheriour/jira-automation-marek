package system;

import models.JiraProject;

public class EntityManager {

    static JiraProject createdProject;

    /**
     * Register a new project to be monitored
     *
     * @param project   Project object to be added
     */
    public static void registerProject(JiraProject project) {
        createdProject = project;
    }

    /**
     * Delete all Jira entities stored in the Manager
     */
    public static void deleteEntities(){
        deleteProject();
    }

    /**
     * Deletes stored project. Will retrieve project ID if only Name is available.
     */
    private static void deleteProject() {
        if (createdProject != null){
            if (createdProject.Id == null) {
                createdProject.Id = JiraApi.GetInstance().getProjectIdByName(createdProject.Name);
            }
            JiraApi.GetInstance().sendDelete("/3/project/" +createdProject.Id);
        }
    }

    /**
     * Get the name of stored project
     *
     * @return Name of stored project
     */
    public static String getProjectName(){
        return createdProject.Name;
    }
}
