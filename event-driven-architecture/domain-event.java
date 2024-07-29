// 이벤트 알림 메세지
eventNotification = {
        "type": "marriage-recorded",
        "person-id": "01b9a761",
        "payload": {
        "person-id": "126a7b61",
        "details": "/01b9a761/marriage-data"
        }
        };

// 이벤트를 통한 상태 전송 메세지
ecst = {
        "type": "personal-details-changed",
        "person-id": "01b9a761",
        "payload": {
        "new-last-name": "Williams"
        }
        };

// 도메인 이벤트
domainEvent = {
        "type": "married",
        "person-id": "01b9a761",
        "payload": {
        "person-id": "126a7b61",
        "assumed-partner-last-name": true
        }
        };