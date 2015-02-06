package org.exoplatform.support;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.mail.MailService;
import org.exoplatform.services.mail.Message;
import org.exoplatform.services.organization.OrganizationConfig;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class NotifyJob implements Job {

    private static final Log LOG = ExoLogger.getLogger(NotifyJob.class);
    ConversationState conv;
    Identity user;

    OrganizationService service = (OrganizationService)
    ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(OrganizationService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Message msg = new Message();
        MailService mailService = null;
        try {
            ExoContainer container = ExoContainerContext.getCurrentContainer();
            mailService = (MailService) container.getComponentInstanceOfType(MailService.class);

            conv = ConversationState.getCurrent();
            user = conv.getIdentity();
            OrganizationConfig.User user = orgService.getUserHandler().findUserByName(userId) ;

            msg.setFrom("exo.test10@gmail.com");
            msg.setTo("exo.test10@gmail.com");
            msg.setSubject("this is a test");

            mailService.sendMessage(msg);

            LOG.info("NotifyJob is executed...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
