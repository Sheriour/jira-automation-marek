package system;

import models.JiraProject;
import utils.GeneralUtils;

public class EntityManager {

    static ThreadLocal<JiraProject> createdProject = new ThreadLocal<>();

    /**
     * Register a new project to be monitored
     *
     * @param project   Project object to be added
     */
    public static void registerProject(JiraProject project) {
        createdProject.set(project);
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
        JiraProject project = createdProject.get();
        if (project != null){
            int iterator = 0, maxRetries = 3;
            while (project.Id == null && iterator < maxRetries){
                project.Id = JiraApi.GetInstance().getProjectIdByName(project.Name);
                iterator++;
                GeneralUtils.waitForSeconds(1);
            }
            JiraApi.GetInstance().sendDelete("/3/project/" +project.Id);
        }
    }

    /**
     * Get the name of stored project
     *
     * @return Name of stored project
     */
    public static String getProjectName(){
        return createdProject.get().Name;
    }
}
