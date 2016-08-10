package net.therap.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;

/**
 * @author jahangir
 * @since 8/10/16
 */
public class FormCodeGenAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);

        if (project == null) {
            return;
        }

        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);

        StringBuilder sb = new StringBuilder();

        if (psiFile instanceof PsiJavaFile) {
            PsiJavaFile javaFile = (PsiJavaFile) psiFile;
            PsiClass[] classes = javaFile.getClasses();
            PsiClass psiClass = classes[0];

            String className = psiClass.getName();
            String packageName = javaFile.getPackageName();

            sb.append("Domain Class: ").append(className);
            sb.append("Package: ").append(packageName);
//            sb.append("\nController Class: ").append(name).append("Controller")
//                    .append("\nHelper Class: ").append(name).append("Helper")
//                    .append("\nService Class: ").append(name).append("Service");
//
//            for (PsiField field : psiClass.getFields()) {
//                sb.append("\nName: ").append(field.getName()).append(" type: ").append(field.getType().getCanonicalText());
//            }
        }

        String message = sb.toString();

        Messages.showMessageDialog(project, message, "Alert", Messages.getInformationIcon());
    }
}
