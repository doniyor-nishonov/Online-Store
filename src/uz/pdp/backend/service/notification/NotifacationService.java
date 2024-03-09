package uz.pdp.backend.service.notification;

import java.util.List;

public interface NotifacationService {
    void checkData(List<?> list);
    void notificationMessage(String objName,String action,boolean isWorked);
}
