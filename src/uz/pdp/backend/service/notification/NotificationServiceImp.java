package uz.pdp.backend.service.notification;

import static uz.pdp.frontend.tools.COLOR.*;

import java.util.List;
import java.util.Objects;

public class NotificationServiceImp implements NotifacationService{
    @Override
    public void checkData(List<?> list) {
        System.out.println(Objects.isNull(list) || list.isEmpty()? RED+"Data base is empty"+STOP:GREEN+"----DATA----"+STOP);
    }

    @Override
    public void notificationMessage(String objName, String action, boolean isWorked) {
        String message = isWorked?" successfully ":" failed ";
        String color = isWorked?GREEN:RED;
        System.out.println(color+objName+message+ action + STOP);
    }
}
