/**
 * 이벤트 소싱 애그리게이트 예제
 */
public class TicketAPI {
    private ITicketsRepository ticketsRepository;

    public TicketAPI(ITicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public void requestEscalation(TicketId id) { //특정 티켓에 대한 에스컬레이션 요청을 처리
        List<Event> events = ticketsRepository.loadEvents(id); //해당 티켓의 이벤트를 로드
        Ticket ticket = new Ticket(events); //로드된 이벤트를 기반으로 티켓 객체를 생성
        int originalVersion = ticket.getVersion(); //티켓의 현재 버전을 가져옴
        RequestEscalation cmd = new RequestEscalation();
        ticket.execute(cmd); //티켓 객체에 명령을 실행하여 상태를 변경
        ticketsRepository.commitChanges(ticket, originalVersion); //티켓의 변경 사항을 원본 버전과 함께 커밋
    }

}
