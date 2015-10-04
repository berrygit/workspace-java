package elevator;

import java.util.ArrayList;
import java.util.List;

public class Client
{

    public static void main(String[] args)
    {
        Elevator elevator = new Elevator();

        List<Request[]> requests = new ArrayList<Request[]>(16);

        // 某一时刻可能有多个请求，也可能没有请求，requests链表将他们按照时间顺序全部封装起来，发给电梯执行。
        for (int time = 0; time < requests.size(); time++)
        {
            elevator.dealRequests(requests.get(time));
        }
    }
}
