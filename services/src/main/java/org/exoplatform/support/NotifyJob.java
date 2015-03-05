package org.exoplatform.support;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class NotifyJob implements Job {

    private static final Log LOG = ExoLogger.getLogger(NotifyJob.class);

    OrganizationService service = (OrganizationService)
    PortalContainer.getInstance().getComponentInstanceOfType(OrganizationService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Message msg = new Message();
        MailService mailService = null;
        TaskService task = null;
        try {
            ExoContainer container = ExoContainerContext.getCurrentContainer();
            mailService = (MailService) container.getComponentInstanceOfType(MailService.class);
            task = (TaskService) container.getComponentInstanceOfType(TaskService.class);

            ListAccess<User> users = service.getUserHandler().findAllUsers();

            for (User user : users.load(0, users.getSize())) {
                if (user != null) {
                    String body;
                    int j = 0;
                    int size = task.getTasksByUser(user.getUserName()).size();

                    body = "Hi " + user.getUserName()+",";
                    if (size > 0) {
                        body = body.concat("\n\n you have the following tasks: \n");
                        while (j < size) {
                            body = body.concat("\n  - " + task.getTasksByUser(user.getUserName()).get(j));
                            j++;
                        }
                    }
                    else {
                        body = body.concat("\n\n you have no tasks for instance!");
                    }

                    msg.setFrom("noreply@exoplatform.com");
                    msg.setTo(user.getEmail());
                    msg.setSubject("Daily tasks reminder");
                    msg.setBody(body);

                    mailService.sendMessage(msg);
                }
            }

            LOG.info("NotifyJob is executed...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
