package com.example.opensourcegio.client;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OneSignalClientTest {

    @Test
    public void it_should_send_notification() {

        NotificationRequest request = new NotificationRequest();
        request.setAppId("af625c7b-ddf2-48be-9d44-1c364c1d952f");
        request.setTemplateId("8d9bddfc-c4bf-413e-88d1-16e7aa43e1ae");
        request.setAnyWeb(true);
        request.setContents(new HashMap<>(){{
            put("base_url","http://localhost:4200");
            put("issue_title","Produciton Kubernetes cluster is deleted accidentally");
            put("challenge_id","123123");
        }});
        request.setContents(new HashMap<>() {{
            put("en", "asdasdasdasdasd");
        }});

        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl("http://localhost:4200/challenges/5/accept");
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        rejectButton.setUrl("http://localhost:4200/challenges/5/accept/reject");

        request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users");}});
        OneSignal.createNotification("YjY3ZTI2NTktNmNiOC00M2Q3LWJiYWEtYzcyNjY2ZTU3ZmI1", request);
    }

}